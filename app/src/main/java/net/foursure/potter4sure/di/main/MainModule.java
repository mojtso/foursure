package net.foursure.potter4sure.di.main;

import net.foursure.potter4sure.api.MainApi;
import net.foursure.potter4sure.ui.main.characters.CharactersRecyclerAdapter;
import net.foursure.potter4sure.ui.main.houses.HousesRecyclerAdapter;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class MainModule {
    @Provides
    static MainApi providesMainApi(Retrofit retrofit) { return retrofit.create(MainApi.class); }

    @Provides
    static HousesRecyclerAdapter providesHousesAdapter() {
        return new HousesRecyclerAdapter();
    }

    @Provides
    static CharactersRecyclerAdapter providesCharactersAdapter() { return new CharactersRecyclerAdapter(); }
}