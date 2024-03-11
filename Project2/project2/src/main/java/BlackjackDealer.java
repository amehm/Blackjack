import java.util.ArrayList;
import java.util.Random;

public class BlackjackDealer {
    private ArrayList<Card> deck;
    // generate 52 cards, one for each of 13 faces and 4 suits.
    public void generateDeck(){
        deck = new ArrayList<>();
        String[] suits = {"hearts", "diamonds", "clubs", "spades"};
//        String[] values = {"ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "jack", "king", "queen"};

        for (String suit : suits) {
            for (int value = 1; value <= 13; value++) {
                deck.add(new Card(suit, value));
            }
        }
    }
    // return an Arraylist of two cards and leave the remainder of the deck able to be drawn later
    public ArrayList<Card> dealHand(){
        ArrayList<Card> hand = new ArrayList<>();
        hand.add(drawOne());
        hand.add(drawOne());
        return hand;
    }

    // return the next card on top of the deck
    public Card drawOne(){
        if (deck.isEmpty()) {
            return null;
        }
        return deck.remove(0);
    }
    // return all 52 cards to the deck and shuffle their order
    public void shuffleDeck(){
        Random random = new Random();
        for (int curr = deckSize() - 1; curr > 0; curr--) {
            int next = random.nextInt(curr + 1);
            // swap deck[i] with deck[j]
            Card temp = deck.get(curr);
            deck.set(curr, deck.get(next));
            deck.set(next, temp);
        }
    }

   // return the number of cards left in the deck. After a call to shuffleDeck() this should be 52.
    public int deckSize(){
        return deck.size();
    }
}
