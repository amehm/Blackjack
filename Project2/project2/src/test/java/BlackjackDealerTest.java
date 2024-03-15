import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
public class BlackjackDealerTest {

    // test for deck generation
    @Test
    public void TestGenerateDeckSingle(){
        BlackjackDealer dealer = new BlackjackDealer();
        dealer.generateDeck();
        assertEquals(52, dealer.deckSize());
    }

    // test for confirming deck size stays the same after shuffle
    @Test
    void testDeckSizeAfterShuffle() {
        BlackjackDealer dealer = new BlackjackDealer();
        dealer.generateDeck();
        dealer.shuffleDeck();
        assertEquals(52, dealer.deckSize()); // checks if deck size stays 52
    }

    // test to check that deck size reduces by 2 after dealing a hand
    @Test
    void testDealHandReducesDeckSizeCorrectly() {
        BlackjackDealer dealer = new BlackjackDealer();
        dealer.generateDeck();
        dealer.dealHand();
        assertEquals(50, dealer.deckSize());
    }

    // test to check that deck size reduces by 1 after drawing one card
    @Test
    void testDrawOneReducesDeckSizeCorrectly() {
        BlackjackDealer dealer = new BlackjackDealer();
        dealer.generateDeck();
        dealer.drawOne();
        assertEquals(51, dealer.deckSize());
    }

    // test checks is deck size reduces after dealing a hand and returns size of 2
    @Test
    public void TestDealHandSingle() {
        BlackjackDealer dealer = new BlackjackDealer();
        dealer.generateDeck();
        assertEquals(52, dealer.deckSize());
        ArrayList<Card> hand = dealer.dealHand();
        assertEquals(2, hand.size());
        assertEquals(50, dealer.deckSize());
    }

    // Test case to check drawing one card can be done 52 times without encountering null
    @Test
    void testCanDraw52Times() {
        BlackjackDealer dealer = new BlackjackDealer();
        dealer.generateDeck();
        for (int i = 0; i < 52; i++) {
            assertNotNull(dealer.drawOne());
        }
    }

    // test check to verify drawing one card reduces the deck size and returns a non-null card
    @Test
    public void TestDrawOneSingle() {
        BlackjackDealer dealer = new BlackjackDealer();
        dealer.generateDeck();
        assertEquals(52, dealer.deckSize());
        Card card = dealer.drawOne();
        assertNotNull(card);
        assertEquals(51, dealer.deckSize());
    }

    // test: deck size remains unchanged after shuffling
    @Test
    public void TestShuffleDeckSingle() {
        BlackjackDealer dealer = new BlackjackDealer();
        dealer.generateDeck();
        dealer.shuffleDeck();
        assertEquals(52, dealer.deckSize());
    }

    // test drawing from an empty deck
    @Test
    void testDrawOneWhenDeckIsEmpty() {
        BlackjackDealer dealer = new BlackjackDealer();
        dealer.generateDeck();
        for (int i = 0; i < 52; i++) {
            dealer.drawOne(); // draw all cards to empty deck
        }
        assertNull(dealer.drawOne());
    }

    //  test for deckSize() after several operations
    @Test
    void testDeckSizeAfterMultipleOperations() {
        BlackjackDealer dealer = new BlackjackDealer();
        dealer.generateDeck();
        dealer.dealHand(); // deal one hand, remove two cards
        dealer.drawOne();
        assertEquals(49, dealer.deckSize()); // 49 cards left
    }
}
