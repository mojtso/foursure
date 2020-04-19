package net.foursure.potter4sure.api;

import net.foursure.potter4sure.models.Characteri;
import net.foursure.potter4sure.models.House;
import net.foursure.potter4sure.models.Spell;

import java.util.List;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MainApi {

    @GET("houses")
    Flowable<List<House>> getAllHouses(@Query("key") String key);

    @GET("characters")
    Flowable<List<Characteri>> getAllCharacters(@Query("key") String key);

    @GET("spells")
    Flowable<List<Spell>> getAllSpells(@Query("key") String key);
}
