package net.foursure.potter4sure.di.main;

import net.foursure.potter4sure.ui.main.characters.CharactersFragment;
import net.foursure.potter4sure.ui.main.houses.HousesFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class MainFragmentBuilersModule {

    @ContributesAndroidInjector
    abstract HousesFragment contributeHousesFragment();

    @ContributesAndroidInjector
    abstract CharactersFragment contributeCharactersFragment();

}
