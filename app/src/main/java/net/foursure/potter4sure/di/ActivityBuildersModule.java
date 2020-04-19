package net.foursure.potter4sure.di;

import net.foursure.potter4sure.di.main.MainFragmentBuilersModule;
import net.foursure.potter4sure.di.main.MainModule;
import net.foursure.potter4sure.di.main.MainViewModelsModule;
import net.foursure.potter4sure.ui.main.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuildersModule {

    @ContributesAndroidInjector(
            modules = {
                    MainFragmentBuilersModule.class,
                    MainViewModelsModule.class,
                    MainModule.class
            }
    )
    abstract MainActivity contributeMainActivity();
}
