package com.assignment.ui.main.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.assignment.R;
import com.assignment.databinding.ItemEmptyBinding;
import com.assignment.databinding.ItemRowBinding;
import com.assignment.ui.base.BaseViewHolder;
import com.assignment.ui.main.fragments.ItemEmptyViewModel;
import com.assignment.ui.main.fragments.ItemRowViewModel;
import com.assignment.utility.Debug;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Neelam Saxena on 6/9/18.
 */
public class ListAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    public static final int VIEW_TYPE_EMPTY = 0;

    public static final int VIEW_TYPE_NORMAL = 1;
    private static final String TAG = ListAdapter.class.getSimpleName();
    private ArrayList<ItemRowViewModel> mRowList;
    private Context context;
    private AdapterListener mListener;

    public ListAdapter() {
        this.mRowList = new ArrayList<ItemRowViewModel>();
    }

    @Override
    public int getItemViewType(int position) {
        if (!mRowList.isEmpty()) {
            return VIEW_TYPE_NORMAL;
        } else {
            return VIEW_TYPE_EMPTY;
        }
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context=parent.getContext();
        switch (viewType) {
            case VIEW_TYPE_NORMAL:
                ItemRowBinding itemRowBinding=ItemRowBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
                return new ItemViewHolder(itemRowBinding);
            case VIEW_TYPE_EMPTY:
            default:
                ItemEmptyBinding itemEmptyBinding=ItemEmptyBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
                return new EmptyViewHolder(itemEmptyBinding);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        holder.onBind(position);
    }

    public void setListener(AdapterListener listener) {
        this.mListener = listener;
    }

    public class EmptyViewHolder extends BaseViewHolder implements
            ItemEmptyViewModel.ReadingEmptyItemViewModelListener {

        private final ItemEmptyBinding mBinding;

        public EmptyViewHolder(ItemEmptyBinding binding) {
            super(binding.getRoot());
            this.mBinding = binding;
        }

        @Override
        public void onBind(int position) {
            ItemEmptyViewModel emptyItemViewModel =
                    new ItemEmptyViewModel(this);
            mBinding.setViewModel(emptyItemViewModel);
        }

        @Override
        public void onRetryClick() {
            mListener.onRetryClick();
        }
    }

    public class ItemViewHolder extends BaseViewHolder {

        private final ItemRowBinding mBinding;

        public ItemViewHolder(ItemRowBinding binding) {
            super(binding.getRoot());
            this.mBinding = binding;
        }

        @Override
        public void onBind(int position) {
            final ItemRowViewModel itemRowViewModel = mRowList.get(position);
            mBinding.setViewModel(itemRowViewModel);
            mBinding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Debug.d(TAG, "view click" + view + " pos " + position);
                    mListener.onItemClick(position);
                }
            });

            // Immediate Binding
            // When a variable or observable changes, the binding will be scheduled to change before
            // the next frame. There are times, however, when binding must be executed immediately.
            // To force execution, use the executePendingBindings() method.
            mBinding.executePendingBindings();
        }

//
    }

    @Override
    public int getItemCount() {
        if (mRowList != null && mRowList.size() > 0) {
            return mRowList.size();
        } else {
            return 1;
        }
    }

    public void addItems(List<ItemRowViewModel> repoList) {
        mRowList.addAll(repoList);
        notifyDataSetChanged();
    }

    public void clearItems() {
        mRowList.clear();
    }


    public interface AdapterListener {
        void onRetryClick();

        void onItemClick(int position);
    }
}
