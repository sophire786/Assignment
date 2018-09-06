package com.assignment.ui.main.fragments;

import com.assignment.common.rx.SchedulerProvider;
import com.assignment.data.remote.ApiHelper;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Neelam Saxena on 6/9/18.
 */
@Module
public class HomeModule {

    @Provides
    HomeViewModel provideHomeViewModel(ApiHelper apiHelper, SchedulerProvider schedulerProvider) {
        return new HomeViewModel(apiHelper, schedulerProvider);
    }
}