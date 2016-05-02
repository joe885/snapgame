package com.joe.snapgame.model;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Created by Joseph Donegan.
 */
@RunWith(MockitoJUnitRunner.class)
public class SnapGameTest {

    private static final int EXPECTED_DECK_SIZE = 52;
    private static final int EXPECTED_PER_PLAYER_DECK_SIZE = 26;

    private static final Card[] SNAP_SUCCESS_DECK = new Card[]{
            new Card(Card.Suit.CLUB, 0),
            new Card(Card.Suit.DIAMOND, 0),
            new Card(Card.Suit.HEART, 0),
            new Card(Card.Suit.SPADE, 0)
    };

    private static final Card[] SNAP_FAILED_DECK = new Card[]{
            new Card(Card.Suit.CLUB, 0),
            new Card(Card.Suit.DIAMOND, 1),
            new Card(Card.Suit.HEART, 2),
            new Card(Card.Suit.SPADE, 3)
    };


    private class TestSnapGame extends SnapGame {
        public void setDeck(Card[] testDeck) {
            deck = new Stack<>();
            deck.addAll(Arrays.asList(testDeck));
            initialisePlayers();
        }
    }

    private TestSnapGame game;

    private SnapGame.IGameObserver observer = mock(SnapGame.IGameObserver.class);

    @Before
    public void setUp() throws Exception {
        game = new TestSnapGame();
        game.registerObserver(observer);
        game.initialiseNewGame();
    }

    @Test
    public void shouldCreate52CardDeck() {
        Stack<Card> deck = game.getDeck();

        assertTrue("Deck should contain 52 cards", deck.size() == EXPECTED_DECK_SIZE);
    }

    @Test
    public void shouldCreateDeckOfUniqueCardsWithinValidRange() {
        Stack<Card> deck = game.getDeck();
        List<Card> checkedCards = new ArrayList<>(deck.size());

        for (Card card : deck) {
            assertFalse("All cards should be unique", checkedCards.contains(card));
            assertTrue("Cards should be within valid range", card.getValue() <= Card.MAX_VALUE);
            checkedCards.add(card);
        }
    }

    @Test
    public void shouldCreate2PlayersWithEqualSizedDecks() {
        Player player1 = game.getPlayer(0);
        Player player2 = game.getPlayer(1);

        assertTrue("Player 1 should have 26 cards", player1.getFaceDownDeckSize() == EXPECTED_PER_PLAYER_DECK_SIZE);
        assertTrue("Player 2 should have 26 cards", player2.getFaceDownDeckSize() == EXPECTED_PER_PLAYER_DECK_SIZE);

        assertTrue("Player 1 should have empty face up deck", player1.getFaceUpDeckSize() == 0);
        assertTrue("Player 2 should have empty face up deck", player2.getFaceUpDeckSize() == 0);
    }

    @Test
    public void shouldStartNewTurnForPlayer0() {
        verify(observer).onNewTurnStarted(0);
    }

    @Test
    public void shouldReducePlayerFaceDownDeckWhenDealingCard() {
        game.dealCard(0);
        assertTrue("Player deck should be reduced by 1", game.getPlayer(0).getFaceDownDeckSize() == 25);
    }

    @Test
    public void shouldAddDealtCardToFaceUpDeck() {
        Player player = game.getPlayer(0);
        Card dealtCard = game.dealCard(0);
        assertTrue("Face up deck should be increased by 1", player.getFaceUpDeckSize() == 1);
        assertEquals("Player's top card should be top card of main deck", dealtCard, player.getTopFaceUpCard());
    }

    @Test
    public void shouldNotifySuccessfulSnapWhenSnapSuccessful() {
        game.setDeck(SNAP_SUCCESS_DECK);
        game.dealCard(0);
        game.dealCard(1);
        game.callSnap(0);

        verify(observer).onSnapCalledSuccessfully(0);
    }

    @Test
    public void shouldAddOtherPlayersDeckToBottomOfCallingPlayersDeckWhenSnapSuccessful() {
        game.setDeck(SNAP_SUCCESS_DECK);
        game.dealCard(0);
        game.dealCard(1);
        game.callSnap(0);

        Player callingPlayer = game.getPlayer(0);
        List<Card> callingPlayerDeck = callingPlayer.getFaceDownDeck();

        Player otherPlayer = game.getPlayer(1);
        List<Card> otherPlayerDeck = otherPlayer.getFaceDownDeck();

        assertEquals(callingPlayerDeck.size(), 2);
        assertEquals(callingPlayerDeck.get(0), SNAP_SUCCESS_DECK[3]);
        assertEquals(callingPlayerDeck.get(1), SNAP_SUCCESS_DECK[0]);

        assertEquals(otherPlayerDeck.size(), 1);
        assertEquals(otherPlayerDeck.get(0), SNAP_SUCCESS_DECK[2]);
    }

    @Test
    public void shouldNotifySnapErrorWhenSnapUnsuccessful() {
        game.setDeck(SNAP_FAILED_DECK);
        game.dealCard(0);
        game.dealCard(1);
        game.callSnap(0);

        verify(observer).onSnapCalledInError(0);
    }

    @Test
    public void shouldAwardCallingPlayerDeckToOtherPlayerWhenSnapUnsuccessful() {
        game.setDeck(SNAP_FAILED_DECK);
        game.dealCard(0);
        game.dealCard(1);
        game.callSnap(0);

        Player callingPlayer = game.getPlayer(0);
        List<Card> callingPlayerDeck = callingPlayer.getFaceDownDeck();

        Player otherPlayer = game.getPlayer(1);
        List<Card> otherPlayerDeck = otherPlayer.getFaceDownDeck();

        assertEquals(callingPlayerDeck.size(), 1);
        assertEquals(callingPlayerDeck.get(0), SNAP_FAILED_DECK[0]);

        assertEquals(otherPlayerDeck.size(), 2);
        assertEquals(otherPlayerDeck.get(0), SNAP_FAILED_DECK[1]);
        assertEquals(otherPlayerDeck.get(1), SNAP_FAILED_DECK[2]);
    }

    @Test
    public void shouldEndGameWhenOnePlayerRunsOutOfCards() {
        game.setDeck(SNAP_FAILED_DECK);
        game.dealCard(0);
        game.dealCard(1);
        game.dealCard(0);
        game.dealCard(1);
        game.callSnap(0);

        verify(observer).onGameEnded(1);
    }
}