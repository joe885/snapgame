package com.joe.snapgame.model;

import java.util.Stack;

/**
 * Created by Joseph Donegan.
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
        if (faceDownDeck.empty()) {
            return null;
        }
        Card card = faceDownDeck.pop();
        faceUpDeck.push(card);
        return card;
    }

    public Card getTopFaceUpCard() {
        if (faceUpDeck.empty()) {
            return null;
        }
        return faceUpDeck.peek();
    }

    public Stack<Card> stealFaceUpDeck() {
        Stack<Card> deck = faceUpDeck;
        faceUpDeck = new Stack<>();
        return deck;
    }

    public void addToBottomOfDeck(Stack<Card> deck) {
        faceDownDeck.addAll(0, deck);
    }

    public void addFaceUpDeckToFaceDownDeck() {
        Stack<Card> deck = faceUpDeck;
        faceUpDeck = new Stack<>();
        addToBottomOfDeck(deck);
    }

    public Stack<Card> getFaceDownDeck() {
        return faceDownDeck;
    }
}
