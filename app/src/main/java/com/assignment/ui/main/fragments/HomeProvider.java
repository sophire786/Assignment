package com.assignment.ui.main.fragments;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by Neelam Saxena on 6/9/18.
 */
@Module
public abstract class HomeProvider {

    @ContributesAndroidInjector(modules = HomeModule.class)
    abstract HomeFragment provideHomeFragmentFactory();
}
