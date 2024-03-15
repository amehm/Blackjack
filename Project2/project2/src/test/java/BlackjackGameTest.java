import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BlackjackGameTest {

//    @Test
//    public void testStartNewRound() {
//        BlackjackGame game = new BlackjackGame();
//        game.startNewRound(10);
//        assertEquals(10, game.getCurrentBet());
//        // Assert that player's and banker's hands are initialized, assuming they are non-empty
//        assertTrue(game.playerHand != null && !game.playerHand.isEmpty());
//        assertTrue(game.bankerHand != null && !game.bankerHand.isEmpty());
//    }
//
//    @Test
//    public void testHit() {
//        BlackjackGame game = new BlackjackGame();
//        game.startNewRound(10);
//        int initialPlayerHandSize = game.playerHand.size();
//        game.hit();
//        assertTrue(game.playerHand.size() > initialPlayerHandSize);
//    }
//
//    @Test
//    public void testStand() {
//        BlackjackGame game = new BlackjackGame();
//        game.startNewRound(10);
//        game.stand();
//        assertTrue(game.playerHand.isEmpty());
//        assertTrue(game.bankerHand.isEmpty());
//    }
//
//    @Test
//    public void testStartNewRound_Many() {
//        BlackjackGame game = new BlackjackGame();
//        game.startNewRound(10);
//        game.startNewRound(20);
//        assertEquals(20, game.getCurrentBet()); // Latest bet is considered
//        assertTrue(game.playerHand != null && !game.playerHand.isEmpty());
//        assertTrue(game.bankerHand != null && !game.bankerHand.isEmpty());
//    }
//
//    @Test
//    public void testStartNewRound_None() {
//        BlackjackGame game = new BlackjackGame();
//        assertThrows(IllegalArgumentException.class, () -> {
//            game.startNewRound(0);
//        });
//        assertTrue(game.playerHand.isEmpty());
//        assertTrue(game.bankerHand.isEmpty());
//    }
//
//    @Test
//    public void testStartNewRound_One() {
//        BlackjackGame game = new BlackjackGame();
//        game.startNewRound(10);
//        assertEquals(10, game.getCurrentBet());
//        assertTrue(game.playerHand != null && !game.playerHand.isEmpty());
//        assertTrue(game.bankerHand != null && !game.bankerHand.isEmpty();
//}
}
