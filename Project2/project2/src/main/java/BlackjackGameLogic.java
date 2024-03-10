import java.util.ArrayList;
import java.util.Objects;


public class BlackjackGameLogic {
    // given two hands this should return either player or dealer or push depending on who wins.
    public String whoWon(ArrayList<Card> playerHand1, ArrayList<Card> dealerHand){
        int playerTotal = handTotal(playerHand1);
        int dealerTotal = handTotal(dealerHand);

        if (dealerTotal >= 22) { // dealer bust
            return "player";
        } else if (playerTotal >= 22){ // player bust
            return "dealer";
        } else if (dealerTotal > playerTotal) { // dealer has a higher hand
            return "dealer";
        } else if (playerTotal > dealerTotal){ // player has a higher hand
            return "player";
        } else { // draw
            return "push";
        }
    }

    //return the total value of all cards in the hand.
    public int handTotal(ArrayList<Card> hand){
        int total = 0;
        int acesCount = 0;

        for (Card card : hand){
            total += card.getValue();
            if (Objects.equals(card.getSuit(), "ace")){
                acesCount++;
            }
        }
        // changing ace to either 1 or 11 depending on what is more beneficial to played
        while (acesCount > 0 && total <= 11){
            total += 10;
            acesCount--;
        }
        return total;
    }

    // return true if the dealer should draw another card, i.e. if the value is 16 or less.
    public boolean evaluateBankerDraw(ArrayList<Card> hand){
        int bankerHandTotal = handTotal(hand);
        return bankerHandTotal <= 16;
    }
}
