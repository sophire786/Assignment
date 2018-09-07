package com.assignment.ui.main.alertdialoge;

import com.assignment.common.rx.SchedulerProvider;
import com.assignment.data.remote.ApiHelper;
import com.assignment.ui.base.BaseViewModel;

/**
 * Created by Neelam Saxena on 7/9/18.
 */

public class AlertViewModel extends BaseViewModel<AlertCallback> {

    public AlertViewModel(ApiHelper apiHelper, SchedulerProvider schedulerProvider) {
        super(apiHelper, schedulerProvider);
    }

    public void okClick() {
        getNavigator().dismissDialog();
    }

}
