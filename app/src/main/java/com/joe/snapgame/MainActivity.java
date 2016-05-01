package com.joe.snapgame;

import android.annotation.SuppressLint;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.joe.snapgame.model.SnapGame;
import com.joe.snapgame.ui.core.InjectableActivity;
import com.joe.snapgame.ui.playerarea.PlayerAreaFragment;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class MainActivity extends InjectableActivity {

    @BindView(android.R.id.content)
    protected View contentView;
    @BindView(R.id.containerPlayer1)
    protected ViewGroup containerPlayer1;
    @BindView(R.id.containerPlayer2)
    protected ViewGroup containerPlayer2;

    @Inject
    protected SnapGame game;

    @SuppressLint("InlinedApi")
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            contentView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);}
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        createActivityComponent().inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        game.initialiseNewGame();

        containerPlayer2.setScaleX(-1);
        containerPlayer2.setScaleY(-1);

        PlayerAreaFragment player1Fragment = PlayerAreaFragment.newInstance(0);
        PlayerAreaFragment player2Fragment = PlayerAreaFragment.newInstance(1);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.containerPlayer1, player1Fragment);
        transaction.replace(R.id.containerPlayer2, player2Fragment);
        transaction.commit();
    }
}
