package net.foursure.potter4sure.di.main;

import androidx.lifecycle.ViewModel;

import net.foursure.potter4sure.di.ViewModelKey;
import net.foursure.potter4sure.ui.main.characters.CharactersViewModel;
import net.foursure.potter4sure.ui.main.houses.HousesViewModel;
import net.foursure.potter4sure.ui.main.spells.SpellsViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class MainViewModelsModule {
    @Binds
    @IntoMap
    @ViewModelKey(HousesViewModel.class)
    public abstract ViewModel bindsHousesViewModel(HousesViewModel viewModel);

    @Binds
    @IntoMap
    @ViewModelKey(CharactersViewModel.class)
    public abstract ViewModel bindsCharactersModel(CharactersViewModel viewModel);

    @Binds
    @IntoMap
    @ViewModelKey(SpellsViewModel.class)
    public abstract ViewModel bindsSpellsModel(SpellsViewModel viewModel);
}
