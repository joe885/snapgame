package com.joe.snapgame.ui.core;

import android.support.v7.app.AppCompatActivity;

import com.joe.snapgame.dagger.InjectableApplication;
import com.joe.snapgame.dagger.component.ActivityComponent;

/**
 * Created by Joseph Donegan.
 */
public abstract class InjectableActivity extends AppCompatActivity {

    private ActivityComponent activityComponent;

    @Override
    protected void onDestroy() {
        super.onDestroy();
        activityComponent = null;
    }

    protected  ActivityComponent createActivityComponent() {
        activityComponent = InjectableApplication.get(this).createActivityComponent(this);
        return activityComponent;
    }

    public ActivityComponent getActivityComponent() {
        return activityComponent;
    }
}
