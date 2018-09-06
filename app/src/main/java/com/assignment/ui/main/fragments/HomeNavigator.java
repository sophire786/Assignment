package com.assignment.ui.main.fragments;

import com.assignment.data.model.api.Data;

/**
 * Created by Neelam Saxena on 6/9/18.
 */
public interface HomeNavigator {
    void upDateList(Data data);

    void handleError(Throwable throwable);
}
