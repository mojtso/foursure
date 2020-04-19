package net.foursure.potter4sure.di;

import androidx.lifecycle.ViewModelProvider;

import net.foursure.potter4sure.util.ViewModelProviderFactory;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class ViewModelFactoryModule {

    @Binds
    public abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelProviderFactory modelProviderFactory);
}
