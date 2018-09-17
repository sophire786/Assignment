package com.assignment.ui.main.fragments;

/**
 * Created by Neelam Saxena on 6/9/18.
 */
public class ItemEmptyViewModel {

    private final ReadingEmptyItemViewModelListener mListener;

    public ItemEmptyViewModel(ReadingEmptyItemViewModelListener listener) {
        this.mListener = listener;
    }

    public void onRetryClick() {
        mListener.onRetryClick();
    }

    public interface ReadingEmptyItemViewModelListener {

        void onRetryClick();
    }
}
