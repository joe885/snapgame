package com.joe.snapgame.model;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Created by doneg on 01/05/2016.
 */
@RunWith(MockitoJUnitRunner.class)
public class SnapGameTest {

    private static final int EXPECTED_DECK_SIZE = 52;

    private SnapGame game;
    private SnapGame.GameObserver observer = mock(SnapGame.GameObserver.class);

    @Before
    public void setUp() throws Exception {
        game = new SnapGame();
        game.registerObserver(observer);
        game.initialiseNewGame();
    }

    @Test
    public void shouldCreate52CardDeck() {
        Stack<Card> deck = game.getDeck();

        assertTrue("Deck should contain 52 cards", deck.size() == EXPECTED_DECK_SIZE);
    }

    @Test
    public void shouldCreateDeckOfUniquecardsWithinValidRange() {
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

        assertTrue("Player 1 should have 26 cards", player1.getFaceDownDeckSize() == 26);
        assertTrue("Player 2 should have 26 cards", player2.getFaceDownDeckSize() == 26);

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
    public void shouldStartNextTurnAfterDealingAfterDelay() {

    }

    @Test
    public void shouldNotifySuccessfulSnapWhenSnapSuccessful() {

    }

    @Test
    public void shouldAddMainDeckToBottomOfCallingPlayersDeckWhenSnapSuccessful() {

    }

    @Test
    public void shouldNotifySnapErrorWhenSnapUnsuccessful() {

    }

    @Test
    public void shouldAwardTopCardToDrawingPlayerWhenSnapUnsuccessful() {

    }
}