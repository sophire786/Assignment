
package com.assignment.ui.main.alertdialoge;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.assignment.R;
import com.assignment.databinding.FragmentDialogeBinding;
import com.assignment.ui.base.BaseDialog;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;

/**
 * Created by Neelam Saxena on 7/9/18.
 */

public class AlertDialog extends BaseDialog implements AlertCallback {

    private static final String TAG = AlertDialog.class.getSimpleName();
    @Inject
    AlertViewModel mAlertViewModel;

    public static AlertDialog newInstance() {
        AlertDialog fragment = new AlertDialog();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void dismissDialog() {
        dismissDialog(TAG);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        FragmentDialogeBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_dialoge, container, false);
        View view = binding.getRoot();

        AndroidSupportInjection.inject(this);

        binding.setViewModel(mAlertViewModel);

        mAlertViewModel.setNavigator(this);

        return view;
    }

    public void show(FragmentManager fragmentManager) {
        super.show(fragmentManager, TAG);
    }
}
