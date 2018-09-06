package com.assignment.di.component;

import android.app.Application;


import com.assignment.application.AssignmentApplication;
import com.assignment.di.builder.ActivityBuilder;
import com.assignment.di.module.AppModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;

/**
 * Created by Neelam Saxena on 6/9/18.
 */
@Singleton
@Component(modules = {AndroidInjectionModule.class, AppModule.class,
        ActivityBuilder.class})
public interface AppComponent {

    void inject(AssignmentApplication app);

    @Component.Builder
    interface Builder {

        @BindsInstance
        Builder application(Application application);

        AppComponent build();
    }
}
