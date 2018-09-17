package com.assignment.ui.main.fragments;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.view.Menu;
import android.view.View;

import com.assignment.BR;
import com.assignment.R;
import com.assignment.data.model.api.Data;
import com.assignment.data.model.api.Row;
import com.assignment.databinding.FragmentHomeBinding;
import com.assignment.ui.base.BaseFragment;
import com.assignment.ui.main.MainActivity;
import com.assignment.ui.main.adapter.ListAdapter;
import com.assignment.ui.main.alertdialoge.AlertDialog;
import com.assignment.utility.CommonUtils;
import com.assignment.utility.Debug;

import java.util.ArrayList;

import javax.inject.Inject;

/**
 * Created by Neelam Saxena on 6/9/18.
 */
public class HomeFragment extends BaseFragment<FragmentHomeBinding, HomeViewModel> implements
        HomeNavigator, ListAdapter.AdapterListener, SwipeRefreshLayout.OnRefreshListener {

    public static final String TAG = HomeFragment.class.getSimpleName();

    @Inject
    HomeViewModel mHomeViewModel;

    @Inject
    ListAdapter mListAdapter;

    FragmentHomeBinding mFragmentHomeBinding;

    ArrayList<ItemRowViewModel> mList;

    private ProgressDialog mProgressDialog;

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
        if (isNetworkConnected()) {
            showLoading();
            mHomeViewModel.getListData();
        } else {
            AlertDialog.newInstance().show(getActivity().getSupportFragmentManager());
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mFragmentHomeBinding = getViewDataBinding();
        setHasOptionsMenu(true);
        setUp();
        refreshList();
        mListAdapter.setListener(this);
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        if (menu.findItem(R.id.action_settings) != null) {
            menu.findItem(R.id.action_settings).setVisible(false);
        }
    }

    /* Set Up RecycleView and set adapter */
    private void setUp() {
        LinearLayoutManager mLayoutManger = new LinearLayoutManager(getActivity());
        mLayoutManger.setOrientation(LinearLayoutManager.VERTICAL);
        mFragmentHomeBinding.rvRow.setLayoutManager(mLayoutManger);
        mFragmentHomeBinding.rvRow.setItemAnimator(new DefaultItemAnimator());
        mFragmentHomeBinding.rvRow.setAdapter(mListAdapter);
    }

    /* Refresh the list when called*/
    private void refreshList() {

        mFragmentHomeBinding.mSwipeRefreshLayout.setOnRefreshListener(HomeFragment.this);
        mFragmentHomeBinding.mSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary,
                android.R.color.holo_green_dark,
                android.R.color.holo_orange_dark,
                android.R.color.holo_blue_dark);

    }

    @Override
    public void upDateList(Data data) {
        hideLoading();
        if (data != null && data.getRows().size() > 0) {
            ((MainActivity) getActivity()).updateTittle(data.getTitle());
            mList = new ArrayList<ItemRowViewModel>();
            for (int i = 0; i < data.getRows().size(); i++) {
                Row row = data.getRows().get(i);
                if (row.getTitle() != null) {
                    ItemRowViewModel itemViewModel = new ItemRowViewModel(row.getImageHref(),
                            row.getTitle(), row.getDescription());
                    mList.add(itemViewModel);
                }
            }

            mHomeViewModel.getOpenSourceRepos().observe(this,
                    openSourceItemViewModels -> mHomeViewModel.addItemsToList(mList));

            mListAdapter.addItems(mList);
        } else if (data.getRows().size() == 0) {
        }

    }

    @Override
    public void handleError(Throwable throwable) {
        Debug.d(TAG, "throwable" + throwable.getMessage());
    }

    @Override
    public void onRetryClick() {
        mHomeViewModel.getListData();
    }

    @Override
    public void onItemClick(int position) {

    }

    /*Hide the progress bar */
    public void hideLoading() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.cancel();
        }
    }

    /*Show progress bar */
    public void showLoading() {
        hideLoading();
        mProgressDialog = CommonUtils.showLoadingDialog(getBaseActivity());
    }


    @Override
    public void onRefresh() {
        mFragmentHomeBinding.mSwipeRefreshLayout.setRefreshing(false);
        if (isNetworkConnected()) {
            showLoading();
            mHomeViewModel.getListData();
        } else {
            AlertDialog.newInstance().show(getActivity().getSupportFragmentManager());
        }
    }
}
