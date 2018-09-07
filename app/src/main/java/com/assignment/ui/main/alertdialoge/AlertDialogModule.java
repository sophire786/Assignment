package com.assignment.ui.main.alertdialoge;

import com.assignment.common.rx.SchedulerProvider;
import com.assignment.data.remote.ApiHelper;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Neelam Saxena on 7/9/18.
 */
@Module
public class AlertDialogModule {

    @Provides
    AlertViewModel provideRateUsViewModel(ApiHelper apiHelper, SchedulerProvider schedulerProvider) {
        return new AlertViewModel(apiHelper, schedulerProvider);
    }
}
