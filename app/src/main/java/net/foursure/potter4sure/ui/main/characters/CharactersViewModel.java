package net.foursure.potter4sure.ui.main.characters;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import net.foursure.potter4sure.api.MainApi;
import net.foursure.potter4sure.models.Characteri;
import net.foursure.potter4sure.util.Constants;
import net.foursure.potter4sure.util.Resource;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class CharactersViewModel extends ViewModel {

    private static final String TAG = "CharactersViewModel";
    private final MainApi mainApi;

    private MediatorLiveData<Resource<List<Characteri>>> characters;

    @Inject
    public CharactersViewModel(MainApi mainApi) {
        this.mainApi = mainApi;
    }

    public LiveData<Resource<List<Characteri>>> observeCharacters() {

        if(characters == null) {
            characters = new MediatorLiveData<>();
            characters.setValue(Resource.loading((List<Characteri>)null));

            final LiveData<Resource<List<Characteri>>> source = LiveDataReactiveStreams.fromPublisher(
                    mainApi.getAllCharacters(Constants.API_Key)
                    .onErrorReturn(new Function<Throwable, List<Characteri>>() {
                        @Override
                        public List<Characteri> apply(Throwable throwable) throws Exception {
                            Log.d(TAG, "apply: "+ throwable);
                            Characteri characteri = new Characteri();
                            characteri.setName("-1");
                            ArrayList<Characteri> characteris = new ArrayList<>();
                            characteris.add(characteri);
                            return characteris;
                        }
                    })
                    .map(new Function<List<Characteri>, Resource<List<Characteri>>>() {
                        @Override
                        public Resource<List<Characteri>> apply(List<Characteri> characteris) throws Exception {
                            if(characteris.size() > 0) {
                                if(characteris.get(0).getName().equals("-1")) {
                                    return Resource.error("Something went wrong...", null);
                                }
                            }
                            return Resource.success(characteris);
                        }
                    })
                    .subscribeOn(Schedulers.io())
            );

            characters.addSource(source, new Observer<Resource<List<Characteri>>>() {
                @Override
                public void onChanged(Resource<List<Characteri>> listResource) {
                    characters.setValue(listResource);
                    characters.removeSource(source);
                }
            });
        }



        return characters;
    }
}
