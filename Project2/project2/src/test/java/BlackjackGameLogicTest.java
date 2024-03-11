import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
public class BlackjackGameLogicTest {
    @Test
    public void TestWhoWonSingle_Player() {
        ArrayList<Card> playerHand = new ArrayList<>();
        ArrayList<Card> dealerHand = new ArrayList<>();

        playerHand.add(new Card("Hearts", 10)); // player should win, higher hand
        dealerHand.add(new Card("Spades", 9));

        BlackjackGameLogic game = new BlackjackGameLogic();
        assertEquals("player", game.whoWon(playerHand, dealerHand));
    }
    @Test
    public void TestWhoWonSingle_Dealer() {
        ArrayList<Card> playerHand = new ArrayList<>();
        ArrayList<Card> dealerHand = new ArrayList<>();

        playerHand.add(new Card("Hearts", 10));
        dealerHand.add(new Card("Spades", 10));
        dealerHand.add(new Card("Diamonds", 10)); // dealer should win

        BlackjackGameLogic game = new BlackjackGameLogic();
        assertEquals("dealer", game.whoWon(playerHand, dealerHand));
    }
    @Test
    public void TestWhoWonSingle_Draw() {
        ArrayList<Card> playerHand = new ArrayList<>();
        ArrayList<Card> dealerHand = new ArrayList<>();

        playerHand.add(new Card("Hearts", 10));
        dealerHand.add(new Card("Spades", 10)); // should tie, same hand

        BlackjackGameLogic game = new BlackjackGameLogic();
        assertEquals("push", game.whoWon(playerHand, dealerHand));
    }
    @Test
    public void TestHandTotalSingle() {
        ArrayList<Card> hand = new ArrayList<>();
        hand.add(new Card("Hearts", 10));
        hand.add(new Card("Spades", 5));
        BlackjackGameLogic game = new BlackjackGameLogic();
        assertEquals(15, game.handTotal(hand));
    }
    @Test
    public void TestHandTotalSingle_Ace1() {
        ArrayList<Card> hand = new ArrayList<>();
        hand.add(new Card("Hearts", 1)); // should stay ace as 1 to win
        hand.add(new Card("Spades", 10));
        hand.add(new Card("Clubs", 10));
        BlackjackGameLogic game = new BlackjackGameLogic();
        assertEquals(21, game.handTotal(hand));
    }
    @Test
    public void TestHandTotalSingle_Ace11() {
        ArrayList<Card> hand = new ArrayList<>();
        hand.add(new Card("Hearts", 1)); // should change ace to 11 to win
        hand.add(new Card("Spades", 10));
        BlackjackGameLogic game = new BlackjackGameLogic();
        assertEquals(21, game.handTotal(hand));
    }
    @Test
    public void TestEvaluateBankerDrawSingle_Draw() {
        ArrayList<Card> hand = new ArrayList<>();
        hand.add(new Card("Hearts", 5));
        hand.add(new Card("Spades", 6)); // under 16, should draw
        BlackjackGameLogic game = new BlackjackGameLogic();
        assertTrue(game.evaluateBankerDraw(hand));
    }
    @Test
    public void TestEvaluateBankerDrawSingle_DontDraw() {
        ArrayList<Card> hand = new ArrayList<>();
        hand.add(new Card("Hearts", 10));
        hand.add(new Card("Spades", 10)); // above 16, should not draw
        BlackjackGameLogic game = new BlackjackGameLogic();
        assertFalse(game.evaluateBankerDraw(hand));
    }

    @Test
    public void TestHandTotalMany() {
        BlackjackGameLogic gameLogic = new BlackjackGameLogic();

        ArrayList<Card> hand1 = new ArrayList<>();
        hand1.add(new Card("hearts", 2));
        hand1.add(new Card("diamonds", 5));
        assertEquals(7, gameLogic.handTotal(hand1));

        ArrayList<Card> hand2 = new ArrayList<>();
        hand2.add(new Card("hearts", 10)); // face card, should be 10
        hand2.add(new Card("diamonds", 11)); // face card, should be 10
        assertEquals(20, gameLogic.handTotal(hand2));

        ArrayList<Card> hand3 = new ArrayList<>();
        hand3.add(new Card("hearts", 1));
        hand3.add(new Card("diamonds", 6));
        assertEquals(17, gameLogic.handTotal(hand3)); // ace counts as 11

        ArrayList<Card> hand4 = new ArrayList<>();
        hand4.add(new Card("hearts", 1));
        hand4.add(new Card("diamonds", 11)); // ace should count as 11
        assertEquals(21, gameLogic.handTotal(hand4));


        ArrayList<Card> hand5 = new ArrayList<>();
        hand5.add(new Card("hearts", 1)); // ace should count as 11
        hand5.add(new Card("diamonds", 1)); // ace should count as 1
        hand5.add(new Card("spades", 1)); // ^
        hand5.add(new Card("clubs", 1)); // ^
        assertEquals(14, gameLogic.handTotal(hand5));

        ArrayList<Card> hand6 = new ArrayList<>();
        hand6.add(new Card("hearts", 1)); // should count as 1
        hand6.add(new Card("diamonds", 1)); // ^
        hand6.add(new Card("spades", 13)); // should count as 10
        assertEquals(12, gameLogic.handTotal(hand6));
    }
}
