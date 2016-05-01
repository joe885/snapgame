package com.joe.snapgame.ui.playerarea;

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
public class PlayerAreaFragment extends BaseFragment implements IPlayerAreaView {

    private static final String PLAYER_INDEX_KEY = "PLAYER_INDEX";

    @Inject
    protected IPlayerAreaFragmentPresenter presenter;

    public static PlayerAreaFragment newInstance(int playerIndex) {
        Bundle bundle = new Bundle();
        bundle.putInt(PLAYER_INDEX_KEY, playerIndex);
        PlayerAreaFragment fragment = new PlayerAreaFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

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
        int playerIndex = getArguments().getInt(PLAYER_INDEX_KEY, 0);
        presenter.initialiseData(playerIndex);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View rootView = inflater.inflate(R.layout.fragment_player_area, container, false);

        presenter.onViewReady();
        return rootView;
    }
}
