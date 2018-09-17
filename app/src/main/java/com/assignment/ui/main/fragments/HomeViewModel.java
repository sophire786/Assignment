package com.assignment.ui.main.fragments;

import android.arch.lifecycle.MutableLiveData;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;

import com.assignment.common.rx.SchedulerProvider;
import com.assignment.data.remote.ApiHelper;
import com.assignment.ui.base.BaseViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Neelam Saxena on 6/9/18.
 */
public class HomeViewModel extends BaseViewModel<HomeNavigator> {

    private final ObservableList<ItemRowViewModel> itemReadingViewModels =
            new ObservableArrayList<>();
    private final MutableLiveData<List<ItemRowViewModel>> mMutableLiveData;

    public HomeViewModel(ApiHelper apiHelper,
            SchedulerProvider schedulerProvider) {
        super(apiHelper, schedulerProvider);
        mMutableLiveData = new MutableLiveData<>();
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

    public ObservableList<ItemRowViewModel> getItemViewModels() {
        return itemReadingViewModels;
    }

    public void addItemsToList(List<ItemRowViewModel> openSourceItems) {
        itemReadingViewModels.clear();
        itemReadingViewModels.addAll(openSourceItems);
    }

    public MutableLiveData<List<ItemRowViewModel>> getOpenSourceRepos() {
        return mMutableLiveData;
    }

    public List<ItemRowViewModel> getViewModelList() {
        List<ItemRowViewModel> sourceItemViewModels = new ArrayList<>();

        return sourceItemViewModels;
    }
}
