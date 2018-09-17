package com.assignment.ui.main.alertdialoge;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by Neelam Saxena on 7/9/18.
 */
@Module
public abstract class AlertDialogProvider {

    @ContributesAndroidInjector(modules = AlertDialogModule.class)
    abstract AlertDialog provideRateUsDialogFactory();
}
