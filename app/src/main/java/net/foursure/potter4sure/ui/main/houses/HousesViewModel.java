package net.foursure.potter4sure.ui.main.houses;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import net.foursure.potter4sure.api.MainApi;
import net.foursure.potter4sure.models.House;
import net.foursure.potter4sure.util.Constants;
import net.foursure.potter4sure.util.Resource;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class HousesViewModel extends ViewModel {
    public static final String TAG = "HousesViewModel";

    private final MainApi mainApi;

    private MediatorLiveData<Resource<List<House>>> houses;
    
    @Inject
    public HousesViewModel(MainApi mainApi) {
        this.mainApi = mainApi;
    }

    public LiveData<Resource<List<House>>> observeHouses() {
        if(houses == null) {
            houses = new MediatorLiveData<>();
            houses.setValue(Resource.loading((List<House>)null));

            final LiveData<Resource<List<House>>> source = LiveDataReactiveStreams.fromPublisher(
                    mainApi.getAllHouses(Constants.API_Key)
                    .onErrorReturn(new Function<Throwable, List<House>>() {
                        @Override
                        public List<House> apply(Throwable throwable) throws Exception {
                            Log.d(TAG, "Errrrror ===== " + throwable);
                            House house = new House();
                            house.setId("-1");
                            ArrayList<House> houses = new ArrayList<>();
                            houses.add(house);
                            return houses;
                        }
                    })
                    .map(new Function<List<House>, Resource<List<House>>>() {
                        @Override
                        public Resource<List<House>> apply(List<House> houses) throws Exception {
                            if(houses.size() > 0) {
                                if(houses.get(0).getName().equals("-1")) {
                                    return Resource.error("Something went wrong", null);
                                }
                            }

                            Log.d(TAG, "apply: "+ houses.size());
                            return Resource.success(houses);
                        }
                    })
                    .subscribeOn(Schedulers.io())
            );

            houses.addSource(source, new Observer<Resource<List<House>>>() {
                @Override
                public void onChanged(Resource<List<House>> listResource) {
                    houses.setValue(listResource);
                    houses.removeSource(source);
                }
            });
        }

        return houses;
    }
}
