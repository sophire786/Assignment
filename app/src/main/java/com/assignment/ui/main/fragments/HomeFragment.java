package com.assignment.ui.main.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Menu;

import com.assignment.BR;
import com.assignment.R;
import com.assignment.data.model.api.Data;
import com.assignment.databinding.FragmentHomeBinding;
import com.assignment.ui.base.BaseFragment;
import com.assignment.utility.Debug;

import javax.inject.Inject;

/**
 * Created by Neelam Saxena on 6/9/18.
 */
public class HomeFragment extends BaseFragment<FragmentHomeBinding, HomeViewModel> implements
        HomeNavigator {

    public static final String TAG = HomeFragment.class.getSimpleName();

    @Inject
    HomeViewModel mHomeViewModel;

    FragmentHomeBinding mFragmentHomeBinding;

    public static HomeFragment newInstance() {
        Bundle args = new Bundle();
        HomeFragment fragment = new HomeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    public HomeViewModel getViewModel() {
        return mHomeViewModel;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mHomeViewModel.setNavigator(this);
        mHomeViewModel.getListData();
        mFragmentHomeBinding=getViewDataBinding();
        setHasOptionsMenu(true);
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        if (menu.findItem(R.id.action_settings) != null) {
            menu.findItem(R.id.action_settings).setVisible(false);
        }
    }

    @Override
    public void upDateList(Data data) {
        Debug.d(TAG, "Gettong data from " + data.getRows().size());
    }

    @Override
    public void handleError(Throwable throwable) {
        Debug.d(TAG, "throwable" + throwable.getMessage());
    }
}
