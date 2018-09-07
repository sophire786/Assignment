package com.assignment.ui.main.fragments;

import android.databinding.BindingAdapter;
import android.databinding.ObservableField;
import android.widget.ImageView;
import android.widget.TextView;

import com.assignment.R;
import com.assignment.utility.Debug;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

/**
 * Created by Neelam Saxena on 6/9/18.
 */
public class ItemRowViewModel {

    public final ObservableField<String> mTittle = new ObservableField<>();
    public final ObservableField<String> mDesc = new ObservableField<>();
    public final ObservableField<Object> imageUrl = new ObservableField<>();
    public static RequestOptions requestOptions = new RequestOptions();


    public ItemRowViewModel(Object mImageUrl, String tittle, String desc) {
        if (mImageUrl instanceof String) {
            this.imageUrl.set(mImageUrl);
        } else {
            this.imageUrl.set(null);
        }
        this.mTittle.set(tittle);
        this.mDesc.set(desc);
        requestOptions.placeholder(R.drawable.placeholder);
        requestOptions.error(R.drawable.placeholder);
    }

    @BindingAdapter({"imageUrl"})
    public static void setImageUrl(ImageView imageView, String url) {
        if (url == null) {
            imageView.setBackgroundResource(R.drawable.placeholder);
        } else {
            Glide.with(imageView.getContext())
                    .setDefaultRequestOptions(requestOptions)
                    .load(url)
                    .into(imageView);
        }
    }

    @BindingAdapter("desc")
    public static void setDescription(TextView view, String desc) {
        Debug.d("Viewmodel","Desc "+desc);
        if(desc!=null) {
            view.setText(desc);
        }else {
            view.setText(R.string.no_desc);
            view.setTextColor(R.color.colorgray);
        }
    }


}
