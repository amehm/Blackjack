import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
public class BlackjackDealerTest {
    @Test
    public void TestGenerateDeckSingle(){
        BlackjackDealer dealer = new BlackjackDealer();
        dealer.generateDeck();
        assertEquals(52, dealer.deckSize());
    }

    @Test
    public void TestDealHandSingle() {
        BlackjackDealer dealer = new BlackjackDealer();
        dealer.generateDeck();
        assertEquals(52, dealer.deckSize());
        ArrayList<Card> hand = dealer.dealHand();
        assertEquals(2, hand.size());
        assertEquals(50, dealer.deckSize());
    }

    @Test
    public void TestDrawOneSingle() {
        BlackjackDealer dealer = new BlackjackDealer();
        dealer.generateDeck();
        assertEquals(52, dealer.deckSize());
        Card card = dealer.drawOne();
        assertNotNull(card);
        assertEquals(51, dealer.deckSize());
    }

    @Test
    public void TestShuffleDeckSingle() {
        BlackjackDealer dealer = new BlackjackDealer();
        dealer.generateDeck();
        dealer.shuffleDeck();
        assertEquals(52, dealer.deckSize());
    }
}
