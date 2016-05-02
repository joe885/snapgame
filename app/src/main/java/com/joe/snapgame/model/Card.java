package com.joe.snapgame.model;

/**
 * Created by Joseph Donegan.
 */
public class Card {
    public static final int MAX_VALUE = 12;

    public enum Suit {
        HEART,
        DIAMOND,
        SPADE,
        CLUB
    }

    private Suit suit;
    private int value;

    public Card(Suit suit, int value) {
        this.suit = suit;
        this.value = value;
    }

    public Suit getSuit() {
        return suit;
    }

    public int getValue() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Card)) {
            return false;
        }
        Card card = (Card) other;
        return super.equals(card) ||
                ((card.suit == this.suit) &&
                        (card.value == this.value));
    }
}
