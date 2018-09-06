package com.assignment.di.builder;

import com.assignment.ui.main.MainActivity;
import com.assignment.ui.main.MainActivityModule;
import com.assignment.ui.main.fragments.HomeProvider;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by Neelam Saxena on 6/9/18.
 */
@Module
public abstract class ActivityBuilder {
    @ContributesAndroidInjector(modules = {MainActivityModule.class, HomeProvider.class
    })
    abstract MainActivity bindMainActivity();
}
