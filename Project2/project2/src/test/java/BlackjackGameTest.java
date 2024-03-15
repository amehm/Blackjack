import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BlackjackGameTest {
    // test winnings when dealer wins
    @Test
    public void testEvaluateWinnings_DealerWins() {
        BlackjackGame game = new BlackjackGame();
        game.playerHand.add(new Card("Hearts", 7));
        game.bankerHand.add(new Card("Diamonds", 10));
        assertEquals(0, game.evaluateWinnings());
    }

    // test winnings for a single round
    @Test
    public void testEvaluateWinnings_OneRound() {
        BlackjackGame game = new BlackjackGame();
        game.setCurrentBet(10);
        game.playerHand.add(new Card("Hearts", 10));
        game.bankerHand.add(new Card("Diamonds", 5));
        double winnings = game.evaluateWinnings();
        assertEquals(20, winnings);
    }

    // test winnings when no rounds are played
    @Test
    public void testEvaluateWinnings_NoneRound() {
        BlackjackGame game = new BlackjackGame();
        game.setCurrentBet(10);
        double winnings = game.evaluateWinnings();
        assertEquals(10, winnings);
    }

    // test winnings for multiple rounds
    @Test
    public void testEvaluateWinnings_ManyRounds() {
        BlackjackGame game = new BlackjackGame();
        game.setCurrentBet(10);
        game.playerHand.add(new Card("Hearts", 10));
        game.bankerHand.add(new Card("Diamonds", 7));
        game.evaluateWinnings();
        game.setCurrentBet(20);
        game.playerHand.clear();
        game.bankerHand.clear();
        game.playerHand.add(new Card("Clubs", 9));
        game.bankerHand.add(new Card("Spades", 10));
        double secondRoundWinnings = game.evaluateWinnings();
        assertEquals(20, secondRoundWinnings);
    }

    // test winnings in a push scenario
    @Test
    public void testEvaluateWinnings_Push() {
        BlackjackGame game = new BlackjackGame();
        game.setCurrentBet(10);
        game.playerHand.add(new Card("Hearts", 10));
        game.playerHand.add(new Card("Clubs", 2));
        game.bankerHand.add(new Card("Diamonds", 9));
        game.bankerHand.add(new Card("Spades", 3));
        double winnings = game.evaluateWinnings();
        assertEquals(10, winnings);
    }

    // test winnings for player blackjack
    @Test
    public void testEvaluateWinnings_PlayerBlackjack() {
        BlackjackGame game = new BlackjackGame();
        game.setCurrentBet(10);
        game.playerHand.add(new Card("Hearts", 1));
        game.playerHand.add(new Card("Clubs", 10));
        double winnings = game.evaluateWinnings();
        assertEquals(25, winnings);
    }

    // test winnings for dealer blackjack
    @Test
    public void testEvaluateWinnings_DealerBlackjack() {
        BlackjackGame game = new BlackjackGame();
        game.setCurrentBet(10);
        game.playerHand.add(new Card("Hearts", 9));
        game.bankerHand.add(new Card("Diamonds", 1));
        game.bankerHand.add(new Card("Spades", 10));
        double winnings = game.evaluateWinnings();
        assertEquals(0, winnings);
    }

    // test winnings when no cards are dealt
    @Test
    public void testEvaluateWinnings_EmptyHands() {
        BlackjackGame game = new BlackjackGame();
        game.setCurrentBet(10);
        double winnings = game.evaluateWinnings();
        assertEquals(10, winnings);
    }
}
