package com.assignment.repository;

import com.assignment.data.model.api.Data;

import io.reactivex.Single;
import retrofit2.http.GET;

/**
 * Created by Neelam Saxena on 6/9/18.
 */
public interface RestInterface {
    //Base Url
    String HTTPS_API_URL = "https://dl.dropboxusercontent.com/s/2iodh4vg0eortkl/";

    @GET("facts.json/")
    Single<Data> getListData();

}
