import java.util.ArrayList;

public class BlackjackGame {
    ArrayList<Card> playerHand;
    ArrayList<Card> bankerHand;
    BlackjackDealer theDealer;
    BlackjackGameLogic gameLogic;
    double currentBet;
    double totalWinnings;

    // Constructor to initialize a new game
    public BlackjackGame(double startingTotalWinnings) {
        playerHand = new ArrayList<>();
        bankerHand = new ArrayList<>();
        theDealer = new BlackjackDealer();
        gameLogic = new BlackjackGameLogic();
        totalWinnings = startingTotalWinnings;
    }

    // Method to start a new round
    public void startNewRound(double bet) {
        if (bet > totalWinnings) {
            throw new IllegalArgumentException("Bet cannot exceed total winnings.");
        }
        currentBet = bet;
        // temp deduction from total winnings for the bet
        totalWinnings -= bet;

        playerHand.clear();
        bankerHand.clear();

        theDealer.shuffleDeck();

        // Deal two cards to each player
        playerHand.addAll(theDealer.dealHand());
        bankerHand.addAll(theDealer.dealHand());
    }

    // Method to let the player hit
    public void hit() {
        // adds card to player's hand
        playerHand.add(theDealer.drawOne());
        if (gameLogic.handTotal(playerHand) > 21) {
            endRound();
        }
    }

    // Method to let the player stand
    public void stand() {
        while (gameLogic.evaluateBankerDraw(bankerHand)) {
            // dealer draws until total is 17+
            bankerHand.add(theDealer.drawOne());
        }
        endRound();
    }

    // Method to evaluate winnings/losses at the end of the round
    private void endRound() {
        double roundWinnings = evaluateWinnings();
        if (roundWinnings > 0) {
            System.out.println("Player wins the round!");
        } else if (roundWinnings < 0) {
            System.out.println("Dealer wins the round!");
        } else {
            System.out.println("It's a draw!");
            totalWinnings += currentBet;
        }
    }

    // Method to evaluate winnings/losses based on the outcome of the round
    public double evaluateWinnings() {
        String winner = gameLogic.whoWon(playerHand, bankerHand);
        if (winner.equals("player")) {
            // Player wins
            double winnings = currentBet * 1.5;
            totalWinnings += winnings;
            return winnings;
        } else if (winner.equals("dealer")) {
            // Dealer wins
            totalWinnings -= currentBet;
            return -currentBet;
        } else {
            // draw
            totalWinnings += currentBet;
            return 0;
        }
    }
}

