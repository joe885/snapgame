package com.joe.snapgame.ui.core;

import com.joe.snapgame.utils.DataError;

/**
 * Created by Joseph Donegan.
 */
public abstract class BasePresenter<V extends  IBaseView> implements IBasePresenter {

    protected boolean isDataReady;
    protected boolean isViewReady;

    protected final V view;

    protected DataError lastError;

    public BasePresenter(V view) {
        this.view = view;
    }

    @Override
    public void onDataReady() {
        lastError = null;
        isDataReady = true;
        if (isViewReady) {
            populateView(lastError);
        }
    }

    @Override
    public void onViewReady() {
        isViewReady = true;
        if (isDataReady) {
            populateView(lastError);
        }
    }

    @Override
    public void onDataError(DataError error) {
        this.lastError = error;
        isDataReady = true;
        if (isViewReady) {
            populateView(lastError);
        }
    }


    @Override
    public void reset() {
        isDataReady = false;
        isViewReady = false;
        lastError = null;
    }

    /**
     * Called when both the Data and the View have completed and are ready for use
     */
    protected abstract void populateView(DataError error);
}
