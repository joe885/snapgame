package com.joe.snapgame.model;

import android.os.Handler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;
import java.util.concurrent.TimeUnit;

/**
 * Created by doneg on 01/05/2016.
 */
public class SnapGame {
    private static final int NUM_PLAYERS = 2;
    private static final long TURN_DELAY_MS = TimeUnit.SECONDS.toMillis(1);

    private Stack<Card> deck;
    private List<Player> players;

    private int currentPlayerIndex = -1;
    private Handler newTurnHandler = new Handler();

    public interface IGameObserver {
        void onNewTurnStarted(int playerIndex);

        void onPlayerDealt(int playerIndex);

        void onPlayerDeckChanged(int playerIndex);

        void onSnapCalledInError(int playerIndex);

        void onSnapCalledSuccessfully(int playerIndex);

        void onGameEnded(int winningPlayerIndex);
    }

    private List<IGameObserver> observers = new ArrayList<>();

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
            playerDeck.addAll(deck.subList(startIndex, endIndex));
            players.add(new Player(playerDeck));
        }

        for (int playerIndex = 0; playerIndex < players.size(); ++playerIndex) {
            for (IGameObserver observer : observers) {
                observer.onPlayerDeckChanged(playerIndex);
            }
        }

        //Start a new turn
        startNewTurn();
    }

    private void startNewTurn() {
        ++currentPlayerIndex;
        if (currentPlayerIndex >= players.size()) {
            currentPlayerIndex = 0;
        }

        for (IGameObserver observer : observers) {
            observer.onNewTurnStarted(currentPlayerIndex);
        }
    }

    public void registerObserver(IGameObserver observer) {
        if (!observers.contains(observer)) {
            observers.add(observer);
        }
    }

    public void removeObserver(IGameObserver observer) {
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
        Card card = player.drawCard();
        for (IGameObserver observer : observers) {
            observer.onPlayerDealt(playerIndex);
            observer.onPlayerDeckChanged(playerIndex);
        }
        checkPlayerDecks();
        newTurnHandler.postDelayed(newTurnRunnable, TURN_DELAY_MS);
        return card;
    }

    private final Runnable newTurnRunnable = new Runnable() {
        @Override
        public void run() {
            startNewTurn();
        }
    };

    public void callSnap(int playerIndex) {
        List<Card> cards = new ArrayList<>();
        boolean matchedValue = false;
        for (Player player : players) {
            Card playerTopCard = player.getTopFaceUpCard();
            if (playerTopCard != null) {
                for (Card otherCard : cards) {
                    if (otherCard.getValue() == playerTopCard.getValue()) {
                        matchedValue = true;
                    }
                }
                cards.add(playerTopCard);
            }
        }

        if (matchedValue) {
            successfulSnap(playerIndex);
        } else {
            failedSnap(playerIndex);
        }
    }

    public int getCurrentPlayerIndex() {
        return currentPlayerIndex;
    }

    private void successfulSnap(int callingPlayerIndex) {
        //The calling player steals the other players face up deck and adds it to their face down deck
        Player callingPlayer = players.get(callingPlayerIndex);
        Player otherPlayer = players.get(1 - callingPlayerIndex);
        Stack<Card> deck = otherPlayer.stealFaceUpDeck();
        callingPlayer.addToBottomOfDeck(deck);
        for (IGameObserver observer : observers) {
            observer.onSnapCalledSuccessfully(callingPlayerIndex);
            observer.onPlayerDeckChanged(callingPlayerIndex);
            observer.onPlayerDeckChanged(1 - callingPlayerIndex);
        }
        checkPlayerDecks();
    }

    private void failedSnap(int callingPlayerIndex) {
        //The calling player looses their face up pile and it goes to the other player
        Player callingPlayer = players.get(callingPlayerIndex);
        Player otherPlayer = players.get(1 - callingPlayerIndex);
        Stack<Card> deck = callingPlayer.stealFaceUpDeck();
        otherPlayer.addToBottomOfDeck(deck);
        for (IGameObserver observer : observers) {
            observer.onSnapCalledInError(callingPlayerIndex);
            observer.onPlayerDeckChanged(callingPlayerIndex);
            observer.onPlayerDeckChanged(1 - callingPlayerIndex);
        }
        checkPlayerDecks();
    }

    private void checkPlayerDecks() {
        for (int playerIndex = 0; playerIndex < players.size(); ++playerIndex) {
            Player player = players.get(playerIndex);
            if ((player.getFaceDownDeckSize() + player.getFaceDownDeckSize()) == 0) {
                gameOver(1 - playerIndex);
            } else if (player.getFaceDownDeckSize() == 0) {
                player.addFaceUpDeckToFaceDownDeck();
            }
        }
    }

    private void gameOver(int winningPlayerIndex) {
        for (IGameObserver observer : observers) {
            observer.onGameEnded(winningPlayerIndex);
        }
    }
}
