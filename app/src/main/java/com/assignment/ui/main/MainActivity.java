package com.assignment.ui.main;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.assignment.BR;
import com.assignment.R;
import com.assignment.databinding.ActivityMainBinding;
import com.assignment.ui.base.BaseActivity;
import com.assignment.ui.main.fragments.HomeFragment;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;

/**
 * Created by Neelam Saxena on 6/9/18.
 */
public class MainActivity extends BaseActivity<ActivityMainBinding, MainViewModel> implements
        MainActivityNavigator, HasSupportFragmentInjector {

    private static String TAG = "MainActivity";

    @Inject
    MainViewModel mMainViewModel;

    @Inject
    DispatchingAndroidInjector<Fragment> fragmentDispatchingAndroidInjector;

    @Inject
    ViewModelProvider.Factory mViewModelFactory;

    ActivityMainBinding mActivityMainBinding;

    private Toolbar mToolbar;

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public MainViewModel getViewModel() {
        mMainViewModel = ViewModelProviders.of(this, mViewModelFactory).get(MainViewModel.class);
        return mMainViewModel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (mMainViewModel == null) {
            mMainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        }
        mActivityMainBinding = getViewDataBinding();
        mMainViewModel.setNavigator(this);
        setUp();
        showFragment(getResources().getString(R.string.strDashBoard),
                HomeFragment.newInstance(), HomeFragment.TAG);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return fragmentDispatchingAndroidInjector;
    }

    private void setUp() {
        mToolbar = mActivityMainBinding.toolbar;
        setSupportActionBar(mToolbar);
    }

    public void updateTittle(String tittle){
        mToolbar.setTitle(tittle);
    }

    public void showFragment(String title, Fragment fragment, String tag) {
        mToolbar.setTitle(title);
        if (fragment instanceof HomeFragment) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            getSupportFragmentManager()
                    .beginTransaction()
                    .setCustomAnimations(R.anim.slide_left, R.anim.slide_right)
                    .add(R.id.container, fragment, tag)
                    .commit();
        } else {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportFragmentManager()
                    .beginTransaction()
                    .addToBackStack(tag)
                    .setCustomAnimations(R.anim.slide_left, R.anim.slide_right)
                    .replace(R.id.container, fragment, tag)
                    .commit();
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {

        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {

        }
    }


}
