
package com.assignment.ui.base;

import android.support.v7.widget.RecyclerView;
import android.view.View;


/**
 * Created by Neelam Saxena on 6/9/18.
 */
public abstract class BaseViewHolder extends RecyclerView.ViewHolder {

    public BaseViewHolder(View itemView) {
        super(itemView);
    }

    public abstract void onBind(int position);
}
