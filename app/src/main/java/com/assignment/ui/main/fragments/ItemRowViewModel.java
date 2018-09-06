package com.assignment.ui.main.fragments;

import android.databinding.BindingAdapter;
import android.databinding.ObservableField;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * Created by Neelam Saxena on 6/9/18.
 */
public class ItemRowViewModel {

    public final ObservableField<String> mTittle = new ObservableField<>();
    public final ObservableField<String> mDesc = new ObservableField<>();
    public final ObservableField<Object> imageUrl= new ObservableField<>();;

    public ItemRowViewModel(Object mImageUrl, String tittle,String desc) {
        if(mImageUrl instanceof String) {
            this.imageUrl.set(mImageUrl);
        }else{
            this.imageUrl.set(null);
        }
        this.mTittle.set(tittle);
        this.mDesc.set(desc);
    }

    @BindingAdapter({"imageUrl"})
    public static void setImageUrl(ImageView imageView, String url) {
        if (url == null) {
            imageView.setImageDrawable(null);
        } else {
            Glide.with(imageView.getContext()).load(url).into(imageView);
        }
    }

}
