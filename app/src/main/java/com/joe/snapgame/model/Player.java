package com.joe.snapgame.model;

import java.util.Stack;

/**
 * Created by doneg on 01/05/2016.
 */
public class Player {
    private Stack<Card> faceDownDeck;
    private Stack<Card> faceUpDeck;

    public Player(Stack<Card> deck) {
        this.faceDownDeck = deck;
        this.faceUpDeck = new Stack<>();
    }

    public int getFaceDownDeckSize() {
        return faceDownDeck.size();
    }

    public int getFaceUpDeckSize() {
        return faceUpDeck.size();
    }

    public Card drawCard() {
        Card card = faceDownDeck.pop();
        faceUpDeck.push(card);
        return card;
    }

    public Card getTopFaceUpCard() {
        return faceUpDeck.peek();
    }

    public Stack<Card> stealFaceUpDeck() {
        Stack<Card> deck = faceUpDeck;
        faceUpDeck = new Stack<>();
        return deck;
    }
}
