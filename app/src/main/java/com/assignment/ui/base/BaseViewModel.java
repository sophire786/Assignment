package com.assignment.ui.base;

import android.arch.lifecycle.ViewModel;
import android.databinding.ObservableBoolean;

import com.assignment.common.rx.SchedulerProvider;
import com.assignment.data.remote.ApiHelper;

import java.lang.ref.WeakReference;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by Neelam Saxena on 6/9/18.
 */
public class BaseViewModel<N> extends ViewModel {

    private final ObservableBoolean mIsLoading = new ObservableBoolean(false);
    private final ApiHelper mApiHelper;

    private WeakReference<N> mNavigator;
    private final SchedulerProvider mSchedulerProvider;
    private CompositeDisposable mCompositeDisposable;

    public BaseViewModel(ApiHelper apiHelper, SchedulerProvider schedulerProvider) {
        this.mApiHelper = apiHelper;
        this.mSchedulerProvider = schedulerProvider;
        this.mCompositeDisposable = new CompositeDisposable();
    }

    public ObservableBoolean getIsLoading() {
        return mIsLoading;
    }

    public void setIsLoading(boolean isLoading) {
        mIsLoading.set(isLoading);
    }

    public N getNavigator() {
        return mNavigator.get();
    }

    public void setNavigator(N navigator) {

        this.mNavigator = new WeakReference<>(navigator);
    }

    public ApiHelper getApiHelper() {
        return mApiHelper;
    }

    public SchedulerProvider getSchedulerProvider() {
        return mSchedulerProvider;
    }

    public CompositeDisposable getCompositeDisposable() {
        return mCompositeDisposable;
    }

    @Override
    protected void onCleared() {
        mCompositeDisposable.dispose();
        super.onCleared();
    }
}
