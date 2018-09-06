package com.assignment.ui.main;

import android.arch.lifecycle.ViewModelProvider;

import com.assignment.application.ViewModelProviderFactory;
import com.assignment.common.rx.SchedulerProvider;
import com.assignment.data.remote.ApiHelper;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Neelam Saxena on 6/9/18.
 */
@Module
public class MainActivityModule {
    @Provides
    ViewModelProvider.Factory mainViewModelProvider(MainViewModel mainViewModel) {
        return new ViewModelProviderFactory<>(mainViewModel);
    }

    @Provides
    MainViewModel provideMainViewModel(ApiHelper apiHelper,SchedulerProvider schedulerProvider) {
        return new MainViewModel(apiHelper,schedulerProvider);
    }
}
