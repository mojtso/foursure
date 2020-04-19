package net.foursure.potter4sure.ui.main.spells;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import net.foursure.potter4sure.api.MainApi;
import net.foursure.potter4sure.models.Spell;
import net.foursure.potter4sure.util.Constants;
import net.foursure.potter4sure.util.Resource;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class SpellsViewModel extends ViewModel {

    private static final String TAG = "SpellsViewModel";

    private final MainApi mainApi;

    private MediatorLiveData<Resource<List<Spell>>> spells;

    @Inject
    public SpellsViewModel(MainApi mainApi) {
        this.mainApi = mainApi;
    }

    public LiveData<Resource<List<Spell>>> getAllSpells() {

        if(spells == null) {
            spells = new MediatorLiveData<>();
            spells.setValue(Resource.loading((List<Spell>) null));

            final LiveData<Resource<List<Spell>>> source = LiveDataReactiveStreams.fromPublisher(
                    mainApi.getAllSpells(Constants.API_Key)
                    .onErrorReturn(new Function<Throwable, List<Spell>>() {
                        @Override
                        public List<Spell> apply(Throwable throwable) throws Exception {
                            Log.d(TAG, "apply: " + throwable);
                            Spell spell = new Spell();
                            spell.setSpell("-1");
                            ArrayList<Spell> spells = new ArrayList<>();
                            spells.add(spell);
                            return spells;
                        }
                    })
                    .map(new Function<List<Spell>, Resource<List<Spell>>>() {
                        @Override
                        public Resource<List<Spell>> apply(List<Spell> spells) throws Exception {
                            if(spells.size() > 0){
                                if(spells.get(0).getSpell().equals("-1")) {
                                    return Resource.error("Something went wrong", null);
                                }
                            }
                            return Resource.success(spells);
                        }
                    })
                    .subscribeOn(Schedulers.io())
            );

            spells.addSource(source, new Observer<Resource<List<Spell>>>() {
                @Override
                public void onChanged(Resource<List<Spell>> listResource) {
                    spells.setValue(listResource);
                    spells.removeSource(source);
                }
            });
        }

        return spells;
    }


}
