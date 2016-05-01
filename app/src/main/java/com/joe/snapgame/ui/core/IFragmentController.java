package com.joe.snapgame.ui.core;

import android.support.v4.app.Fragment;

/**
 * Created by Joseph Donegan.
 */
public interface IFragmentController {

    /**
     * Replaces the main fragment (fMainFragment) with the specified fragment. This will happen
     * with a right to left animation if animateTransition is true.
     *
     * @param fragment       The new fragment to be inserted
     * @param animateTransition If true the fragment will transition with a right to left animation
     * @param addToBackStack If true a the fragment will be added to the backstack with the name
     *                       fragment.getClass().getSimpleName()
     */
    void replaceMainFragment(Fragment fragment, boolean animateTransition, boolean addToBackStack);

    /**
     * Pops the back stack
     */
    void popBackStack();
}
