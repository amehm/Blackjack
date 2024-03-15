import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
public class CardTest {

    // test single card constructor
    @Test
    public void TestConstructorSingle(){
        Card card = new Card("Hearts", 10);
        assertEquals("Hearts", card.suit);
        assertEquals(10, card.value);
    }

    // test multiple card constructor
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
    public void TestEmptyConstructor() {
        Card card = new Card("", 0);
        assertEquals("", card.suit);
        assertEquals(0, card.value);
    }

    // test card path as string for Jack
    @Test
    public void testCardPathAsStringJack() {
        Card card = new Card("hearts", 11);
        assertEquals("jack_of_hearts", card.CardPathAsString());
    }

    // test card path as string for Queen
    @Test
    public void testCardPathAsStringQueen() {
        Card card = new Card("diamonds", 12);
        assertEquals("queen_of_diamonds", card.CardPathAsString());
    }

    // test card path as string for King
    @Test
    public void testCardPathAsStringKing() {
        Card card = new Card("clubs", 13);
        assertEquals("king_of_clubs", card.CardPathAsString());
    }

    // test card path as string for number cards
    @Test
    public void testCardPathAsStringNumber() {
        Card card = new Card("hearts", 2);
        assertEquals("2_of_hearts", card.CardPathAsString());
    }

    // test card path as string for number 10 card
    @Test
    public void testCardPathAsStringNumber10() {
        Card card = new Card("diamonds", 10);
        assertEquals("10_of_diamonds", card.CardPathAsString());
    }
}
