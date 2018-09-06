package com.assignment.repository;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Neelam Saxena on 6/9/18.
 */
public class ProjectRepository {

    public static RestInterface mRestInterface;
    private static ProjectRepository projectRepository;

    private ProjectRepository() {
        //TODO this gitHubService instance will be injected using Dagger in part #2 ...
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(mRestInterface.HTTPS_API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        mRestInterface = retrofit.create(RestInterface.class);
    }

    public RestInterface getmRestInterface() {
        return mRestInterface;
    }

    public synchronized static ProjectRepository getInstance() {
        //TODO No need to implement this singleton in Part #2 since Dagger will handle it ...
        if (projectRepository == null) {
            if (projectRepository == null) {
                projectRepository = new ProjectRepository();
            }
        }
        return projectRepository;
    }
}
