package com.joe.snapgame.ui.core;

import com.joe.snapgame.utils.DataError;

/**
 * Created by Joseph Donegan.
 */
public interface IBasePresenter {

    /**
     * Called by the view object (fragment or activity) once the view is ready to be updated.
     */
    void onViewReady();

    /**
     * Called by the data object once it has assembled all it's data
     */
    void onDataReady();

    /**
     * Called by the data object when it has had an error loading the data
     */
    void onDataError(DataError error);

    /**
     * Called by a fragment when a full refresh of the presenter and data is required.  Typically when
     * a fragment's underlying data may have changed, this forces both view and data initialisation to
     * be completed before populateView is called once more.
     */
    void reset();
}
