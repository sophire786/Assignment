package com.assignment.ui.main.fragments;

import com.assignment.common.rx.SchedulerProvider;
import com.assignment.data.remote.ApiHelper;
import com.assignment.ui.base.BaseViewModel;

/**
 * Created by Neelam Saxena on 6/9/18.
 */
public class HomeViewModel extends BaseViewModel<HomeNavigator> {

    public HomeViewModel(ApiHelper apiHelper,
            SchedulerProvider schedulerProvider) {
        super(apiHelper, schedulerProvider);
    }

    public void getListData() {
        setIsLoading(true);
        getCompositeDisposable().add(getApiHelper().getListApi()
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(response -> {
                    setIsLoading(false);
                    getNavigator().upDateList(response);
                }, throwable -> {
                    setIsLoading(false);
                    getNavigator().handleError(throwable);
                }));

    }
}
