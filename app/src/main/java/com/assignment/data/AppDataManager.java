/*
 *  Copyright (C) 2017 MINDORKS NEXTGEN PRIVATE LIMITED
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      https://mindorks.com/license/apache-v2
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License
 */

package com.assignment.data;

import android.content.Context;

import com.assignment.data.model.api.Data;
import com.assignment.data.remote.ApiHelper;
import com.assignment.utility.Debug;
import com.google.gson.Gson;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Single;

/**
 * Created by Neelam Saxena.
 */
@Singleton
public class AppDataManager implements ApiHelper {

    private final ApiHelper mApiHelper;

    private final Context mContext;


    private final Gson mGson;

    @Inject
    public AppDataManager(Context context, Gson gson, ApiHelper apiHelper) {
        mContext = context;
        mApiHelper = apiHelper;
        mGson = gson;
    }

    @Override
    public Single<Data> getListApi() {
        Single<Data> mApiHelperListApi = mApiHelper.getListApi();
        Debug.d("AppDataManager", "Response " + mApiHelperListApi.toString());
        return mApiHelperListApi;
    }
}
