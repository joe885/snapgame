package com.joe.snapgame.ui.core;

import android.support.annotation.StringRes;

import com.joe.snapgame.utils.DataError;

/**
 * Created by Joseph Donegan.
 */
public interface IBaseView {

    void showProgressDialog();

    void hideProgressDialog();

    void showError(DataError error);

    void showError(@StringRes int resId);

    void showError(String message);
}
