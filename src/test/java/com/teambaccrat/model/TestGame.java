package com.teambaccrat.model;

import static org.junit.jupiter.api.Assertions.*;

import com.teambaccrat.model.Game.Result;
import com.teambaccrat.model.exception.IllegalBetException;
import org.junit.jupiter.api.Test;

public class TestGame {

  @Test
  public void testThrowsIllegalBet() {
    try {
      Game game = new Game(500);
      game.setBet("8");
      game.setAmount(50);
    } catch (IllegalBetException e) {
       assertInstanceOf(IllegalBetException.class, e);
    }
  }

  @Test
  void playerGetsThirdCard() {
    Game game = new Game(500);
    game.setBet("1");
    game.setAmount(50);

    Card card1 = new Card(Rank.ACE, Suit.CLUBS);
    Card card2 = new Card(Rank.THREE, Suit.SPADES);
    Hand playerHand = new Hand();
    playerHand.add(card1);
    playerHand.add(card2);
 //   assertTrue(game.playerGetsThirdCard(playerHand));
  }

  @Test
  void bankerGetsThirdCard() {
     Game game = new Game(500);
     game.setBet("1");
     game.setAmount(50);

    // Player Cards
    Card card1 = new Card(Rank.ACE, Suit.CLUBS);
    Card card2 = new Card(Rank.THREE, Suit.SPADES);
    Card card3 = new Card(Rank.TWO, Suit.SPADES);
    // Banker Cards
    Card card4 = new Card(Rank.ACE, Suit.CLUBS);
    Card card5 = new Card(Rank.THREE, Suit.SPADES);

    Hand playerHand = new Hand();
    Hand bankerHand = new Hand();
    playerHand.add(card1);
    playerHand.add(card2);
    bankerHand.add(card3);
    bankerHand.add(card4);
//    assertTrue(game.bankerGetsThirdCard(playerHand, bankerHand));
  }

  @Test
  void updateBalance(){
    Game game = new Game(500);
    game.setBet("1");
    game.setAmount(50);
    Result result = Result.BANKER;
    Bet bet = Bet.BANKER;
   // game.updateBalance(result, bet);
    assertEquals(1050, game.getBalance());
  }

  @Test
  void whoWon_BANKER_WIN(){
    Game game = new Game(500);
    game.setBet("1");
    game.setAmount(50);

    // Player Cards
    Card card1 = new Card(Rank.JACK, Suit.CLUBS);
    Card card2 = new Card(Rank.JACK, Suit.SPADES);
    Card card3 = new Card(Rank.EIGHT, Suit.SPADES);
    // Banker Cards
    Card card4 = new Card(Rank.NINE, Suit.CLUBS);
    Card card5 = new Card(Rank.JACK, Suit.SPADES);

    Hand playerHand = new Hand();
    Hand bankerHand = new Hand();
    playerHand.add(card1);
    playerHand.add(card2);
    bankerHand.add(card3);
    bankerHand.add(card4);

   Result result = game.whoWon(playerHand, bankerHand);
   assertEquals(Result.BANKER, result);
  }

  @Test
  void testBalance(){
    Game game = new Game(500);
    updateBalance();
    game.setBet("1");
    game.setAmount(50);


  }

}
