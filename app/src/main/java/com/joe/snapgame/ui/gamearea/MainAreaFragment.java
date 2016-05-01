package com.joe.snapgame.ui.gamearea;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.joe.snapgame.R;
import com.joe.snapgame.ui.core.BaseFragment;
import com.joe.snapgame.ui.core.InjectableActivity;

import javax.inject.Inject;

/**
 * Created by doneg on 30/04/2016.
 */
public class MainAreaFragment extends BaseFragment implements IMainAreaView {

    @Inject
    protected IMainAreaPresenter presenter;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        InjectableActivity activity;
        try {
            activity = (InjectableActivity) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must extend InjectableActivity");
        }
        createFragmentComponent(activity).inject(this);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter.initialiseData();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View rootView = inflater.inflate(R.layout.fragment_main_area, container, false);

        presenter.onViewReady();
        return rootView;
    }
}
