package com.joe.snapgame.ui.core;

import android.support.v4.app.Fragment;

import com.joe.snapgame.dagger.InjectableApplication;
import com.joe.snapgame.dagger.component.FragmentComponent;

/**
 * Created by Joseph Donegan.
 */
public abstract class InjectableFragment extends Fragment {

    private FragmentComponent fragmentComponent;

    protected FragmentComponent createFragmentComponent(InjectableActivity activity) {
        fragmentComponent = InjectableApplication.get(activity).createFragmentComponent(activity, this);
        return fragmentComponent;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        fragmentComponent = null;
    }
}
