package com.joe.snapgame.ui.playerarea;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.joe.snapgame.R;

/**
 * Created by doneg on 30/04/2016.
 */
public class PlayerAreaFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View rootView = inflater.inflate(R.layout.fragment_player_area, container, false);

        return rootView;
    }
}