package com.joe.snapgame.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

/**
 * Created by doneg on 01/05/2016.
 */
public class SnapGame {
    private static final int NUM_PLAYERS = 2;

    private Stack<Card> deck;
    private List<Player> players;

    private int currentPlayerIndex = -1;

    public interface GameObserver {
        void onNewTurnStarted(int playerIndex);

        void onPlayerDeckChanged(int playerIndex);

        void onMainDeckChanged(int playerIndex);

        void onSnapCalledInError(int playerIndex);

        void onSnapCalledSuccessfully(int playerIndex);

        void onGameEnded(int winningPlayerIndex);
    }

    private List<GameObserver> observers = new ArrayList<>();

    public void initialiseNewGame() {
        //Create a new deck
        deck = new Stack<>();
        for (Card.Suit suit : Card.Suit.values()) {
            for (int value = 0; value <= Card.MAX_VALUE; ++value) {
                deck.add(new Card(suit, value));
            }
        }

        //Shuffle the deck
        Collections.shuffle(deck);

        //Split the deck equally per player
        players = new ArrayList<>(NUM_PLAYERS);
        int playerDeckSize = deck.size() / NUM_PLAYERS;
        for (int playerIndex = 0; playerIndex < NUM_PLAYERS; ++playerIndex) {
            Stack<Card> playerDeck = new Stack<>();
            int startIndex = playerIndex * playerDeckSize;
            int endIndex = startIndex + playerDeckSize;
            for (int cardIndex = startIndex; cardIndex < endIndex; ++cardIndex) {
                playerDeck.push(deck.get(cardIndex));
            }
            players.add(new Player(playerDeck));
        }

        //Start a new turn
        startNewTurn();
    }

    private void startNewTurn() {
        ++currentPlayerIndex;
        if (currentPlayerIndex >= players.size()) {
            currentPlayerIndex = 0;
        }

        for (GameObserver observer : observers) {
            observer.onNewTurnStarted(currentPlayerIndex);
        }
    }

    public void registerObserver(GameObserver observer) {
        if (!observers.contains(observer)) {
            observers.add(observer);
        }
    }

    public void removeObserver(GameObserver observer) {
        observers.remove(observer);
    }

    public Stack<Card> getDeck() {
        return deck;
    }

    public Player getPlayer(int playerIndex) {
        return players.get(playerIndex);
    }

    public Card dealCard(int playerIndex) {
        Player player = players.get(playerIndex);
        return player.drawCard();
    }

    public void callSnap(int playerIndex) {

    }
}
