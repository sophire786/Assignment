package com.assignment.ui.main.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
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
import com.assignment.ui.main.adapter.ListAdapter;
import com.assignment.utility.Debug;

import java.util.ArrayList;

import javax.inject.Inject;

/**
 * Created by Neelam Saxena on 6/9/18.
 */
public class HomeFragment extends BaseFragment<FragmentHomeBinding, HomeViewModel> implements
        HomeNavigator, ListAdapter.AdapterListener {

    public static final String TAG = HomeFragment.class.getSimpleName();

    @Inject
    HomeViewModel mHomeViewModel;

    @Inject
    ListAdapter mListAdapter;

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

    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mFragmentHomeBinding = getViewDataBinding();
        setHasOptionsMenu(true);
        setUp();
        mListAdapter.setListener(this);
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        if (menu.findItem(R.id.action_settings) != null) {
            menu.findItem(R.id.action_settings).setVisible(false);
        }
    }

    private void setUp() {
        LinearLayoutManager mLayoutManger = new LinearLayoutManager(getActivity());
        mLayoutManger.setOrientation(LinearLayoutManager.VERTICAL);
        mFragmentHomeBinding.rvRow.setLayoutManager(mLayoutManger);
        mFragmentHomeBinding.rvRow.setItemAnimator(new DefaultItemAnimator());
        mFragmentHomeBinding.rvRow.setAdapter(mListAdapter);
    }

    @Override
    public void upDateList(Data data) {
        Debug.d(TAG, "Gettong data from " + data.getRows().size());

        ArrayList<ItemRowViewModel> mList = new ArrayList<ItemRowViewModel>();
        for (int i = 0; i < data.getRows().size(); i++) {
            Row row = data.getRows().get(i);
            ItemRowViewModel itemViewModel = new ItemRowViewModel(row.getImageHref(),
                    row.getTitle(), row.getDescription());
            mList.add(itemViewModel);
        }

        mHomeViewModel.getOpenSourceRepos().observe(this,
                openSourceItemViewModels -> mHomeViewModel.addItemsToList(mList));

        mListAdapter.addItems(mList);
    }

    @Override
    public void handleError(Throwable throwable) {
        Debug.d(TAG, "throwable" + throwable.getMessage());
    }

    @Override
    public void onRetryClick() {

    }

    @Override
    public void onItemClick(int position) {

    }


}
