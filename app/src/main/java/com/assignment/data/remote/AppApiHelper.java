package com.assignment.data.remote;

import com.assignment.data.model.api.Data;
import com.assignment.repository.ProjectRepository;
import com.assignment.utility.Debug;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Single;

/**
 * Created by Neelam Saxena on 8/8/18.
 */
@Singleton
public class AppApiHelper implements ApiHelper {

    private static final String TAG = AppApiHelper.class.getName();

    @Inject
    public AppApiHelper() {
    }

    @Override
    public Single<Data> getListApi() {
        Single<Data> dataSingle = ProjectRepository.getInstance().getmRestInterface().getListData();
        Debug.d(TAG, "Api Helper response " + dataSingle.toString());
        return dataSingle;

    }
}
