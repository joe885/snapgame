package com.joe.snapgame.ui.playerarea;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.StringRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.joe.snapgame.R;
import com.joe.snapgame.model.Card;
import com.joe.snapgame.ui.core.BaseFragment;
import com.joe.snapgame.ui.core.InjectableActivity;
import com.joe.snapgame.ui.views.CardView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by doneg on 30/04/2016.
 */
public class PlayerAreaFragment extends BaseFragment implements IPlayerAreaView {

    private static final String PLAYER_INDEX_KEY = "PLAYER_INDEX";

    @BindView(R.id.cvFaceUpDeck)
    protected CardView faceUpDeck;
    @BindView(R.id.tvDeckCount)
    protected TextView tvDeckCount;
    @BindView(R.id.btnAction)
    protected Button actionButton;
    @BindView(R.id.tvPlayerTitle)
    protected TextView playerTitle;

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
        ButterKnife.bind(this, rootView);

        presenter.onViewReady();
        return rootView;
    }

    @OnClick(R.id.btnAction)
    protected void actionButtonPressed() {
        presenter.actionButtonPressed();
    }

    @Override
    public void setActionButtonTitle(@StringRes int titleRes) {
        actionButton.setText(titleRes);
    }

    @Override
    public void setPlayerTitle(int playerIndex) {
        playerTitle.setText(getString(R.string.player_title, playerIndex));
    }

    @Override
    public void setDeckCount(int deckCount) {
        tvDeckCount.setText(String.valueOf(deckCount));
    }

    @Override
    public void setFaceUpDeckCardImage(Card card) {
        faceUpDeck.setCard(card);
    }

    @Override
    public void setActionButtonEnabled(boolean enabled) {
        actionButton.setEnabled(enabled);
    }
}
