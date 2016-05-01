package com.joe.snapgame.utils;

import android.content.res.Resources;
import android.support.annotation.StringRes;

/**
 * Created by Joseph Donegan.
 */
public class DataError {
    private
    @StringRes
    int errorMessageRes;
    private String errorMessage;
    private int errorCode;

    public static final DataError UNKNOWN_ERROR = new DataError("Unknown error");

    public DataError(int errorCode, @StringRes int errorMessageRes) {
        this.errorMessageRes = errorMessageRes;
        this.errorCode = errorCode;
    }

    public DataError(int errorCode, String errorMessage) {
        this.errorMessage = errorMessage;
        this.errorCode = errorCode;
    }

    public DataError(@StringRes int errorMessageRes) {
        this.errorMessageRes = errorMessageRes;
        this.errorCode = 0;
    }

    public DataError(String errorMessage) {
        this.errorMessage = errorMessage;
        this.errorCode = 0;
    }

    public String getErrorMessage(Resources resources) {
        if (errorMessage != null) {
            return errorMessage;
        }
        return resources.getString(errorMessageRes);
    }

    public int getErrorCode() {
        return errorCode;
    }

    public int getErrorMessageRes() {
        return errorMessageRes;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
