package com.assignment.ui.main;

import com.assignment.common.rx.SchedulerProvider;
import com.assignment.data.remote.ApiHelper;
import com.assignment.ui.base.BaseViewModel;

/**
 *  Created by Neelam Saxena on 6/9/18.
 */
public class MainViewModel extends BaseViewModel {

    public MainViewModel(ApiHelper apiHelper,
            SchedulerProvider schedulerProvider) {
        super(apiHelper, schedulerProvider);
    }
}
