package com.assignment.data.remote;


import com.assignment.data.model.api.Data;

import io.reactivex.Single;

/**
 * Created by Neelam Saxena on 8/8/18.
 */
public interface ApiHelper {
    Single<Data> getListApi();

}
