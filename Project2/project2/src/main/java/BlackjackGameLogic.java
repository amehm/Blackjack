import java.util.ArrayList;

public class BlackjackGameLogic {
    public String whoWon(ArrayList<Card> playerHand1, ArrayList<Card> dealerHand){
        return null;
    }
    public int handTotal(ArrayList<Card> hand){
        int total = 0;
        for (Card card : hand){
            total += card.getValue();
        }
        return total;
    }
    public boolean evaluateBankerDraw(ArrayList<Card> hand){
        return false;
    }
}
