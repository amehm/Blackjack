import java.util.ArrayList;

public class BlackjackGame {
    ArrayList<Card> playerHand;
    ArrayList<Card> bankerHand;
    BlackjackDealer theDealer;
    BlackjackGameLogic gameLogic;
    double currentBet;
    double totalWinnings;

    // Constructor to initialize a new game
    public BlackjackGame() {
        playerHand = new ArrayList<>();
        bankerHand = new ArrayList<>();
        theDealer = new BlackjackDealer();
        theDealer.generateDeck();
        gameLogic = new BlackjackGameLogic();
        totalWinnings = 0;
    }

    public int getHandTotal(ArrayList<Card> hand){
        return gameLogic.handTotal(hand);
    }

    public void setCurrentBet(double bet) {
        this.currentBet = bet;
    }

    public void setCurrentValue(double val){
        this.totalWinnings = val;
    }

    // Method to evaluate winnings/losses based on the outcome of the round
    // return the amount won or lost based on the value in currentBet
    public double evaluateWinnings() {
        String winner = gameLogic.whoWon(playerHand, bankerHand);
        if (winner.equals("player")) {
            // Player wins
            double winnings = currentBet;
            if (gameLogic.handTotal(playerHand) == 21 && playerHand.size() == 2) { // blackjack
                winnings = currentBet + currentBet * 1.5; // return bet plus 150% of what they bet
            } else {
                winnings = (currentBet * 2); // return twice of what they bet
            }
            totalWinnings += winnings;
            return winnings;
        } else if (winner.equals("dealer")) {
            // Dealer wins
            return totalWinnings; // get nothing back, lost money they bet
        } else {
            // draw
            totalWinnings += currentBet; // tied so return what they originally bet
            return currentBet;
        }
    }
    public double getCurrentBet() {
        return currentBet;
    }
    public double getCurrentValue() {
        return totalWinnings;
    }
}