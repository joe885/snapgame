package com.joe.snapgame.ui.views;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.joe.snapgame.R;
import com.joe.snapgame.model.Card;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Joseph Donegan.
 */
public class CardView extends RelativeLayout {

    @BindView(R.id.tvCardValue)
    protected TextView cardValue;
    @BindView(R.id.ivCardSuit)
    protected ImageView cardSuit;

    public CardView(Context context) {
        super(context);
        init(context);
    }

    public CardView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public CardView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public CardView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    private void init(Context context) {
        inflate(context, R.layout.card_view, this);
        ButterKnife.bind(this, this);
    }

    public void setCard(Card card) {
        if (card == null) {
            cardSuit.setVisibility(View.INVISIBLE);
            cardValue.setVisibility(View.INVISIBLE);
        } else {
            cardSuit.setVisibility(View.VISIBLE);
            cardValue.setVisibility(View.VISIBLE);
            setSuit(card.getSuit());
            setValue(card.getValue());
        }
    }

    private void setSuit(Card.Suit suit) {
        switch (suit) {
            case HEART:
                cardSuit.setImageResource(R.drawable.suit_heart);
                break;
            case DIAMOND:
                cardSuit.setImageResource(R.drawable.suit_diamond);
                break;
            case SPADE:
                cardSuit.setImageResource(R.drawable.suit_spade);
                break;
            case CLUB:
                cardSuit.setImageResource(R.drawable.suit_club);
                break;
        }
    }

    private void setValue(int value) {
        if (value == 10) {
            cardValue.setText(getResources().getString(R.string.symbol_jack));
        } else if (value == 11) {
            cardValue.setText(getResources().getString(R.string.symbol_queen));
        } else if (value == 12) {
            cardValue.setText(getResources().getString(R.string.symbol_king));
        } else {
            cardValue.setText(String.valueOf(value + 1));
        }
    }


}
