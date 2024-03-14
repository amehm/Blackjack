import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
public class CardTest {
    @Test
    public void TestConstructorSingle(){
        Card card = new Card("Hearts", 10);
        assertEquals("Hearts", card.suit);
        assertEquals(10, card.value);
    }

    @Test
    public void TestConstructorMany() {
        ArrayList<Card> hand = new ArrayList<>();
        hand.add(new Card("Hearts", 10));
        hand.add(new Card("Spades", 5));
        hand.add(new Card("Diamonds", 8));

        String[] expectedSuits = {"Hearts", "Spades", "Diamonds"};
        int[] expectedValues = {10, 5, 8};

        for (int i = 0; i < hand.size(); i++) {
            assertEquals(expectedSuits[i], hand.get(i).suit);
            assertEquals(expectedValues[i], hand.get(i).value);
        }
    }

    @Test
    public void testCardPathAsStringJack() {
        Card card = new Card("hearts", 11);
        assertEquals("jack_of_hearts", card.CardPathAsString());
    }

    @Test
    public void testCardPathAsStringQueen() {
        Card card = new Card("diamonds", 12);
        assertEquals("queen_of_diamonds", card.CardPathAsString());
    }

    @Test
    public void testCardPathAsStringKing() {
        Card card = new Card("clubs", 13);
        assertEquals("king_of_clubs", card.CardPathAsString());
    }

    @Test
    public void testCardPathAsStringNumber() {
        Card card = new Card("hearts", 2);
        assertEquals("2_of_hearts", card.CardPathAsString());
    }

    @Test
    public void testCardPathAsStringNumber10() {
        Card card = new Card("diamonds", 10);
        assertEquals("10_of_diamonds", card.CardPathAsString());
    }

}
