package com.teambaccrat.model;

import static org.junit.jupiter.api.Assertions.*;

import com.teambaccrat.model.Game.Result;
import com.teambaccrat.model.exception.IllegalBetException;
import org.junit.jupiter.api.Test;

public class TestGame {

  @Test
  public void testThrowsIllegalBet() {
    try {
      Game game = new Game();
      game.setBet("8");
      game.setAmount(50);
    } catch (IllegalBetException e) {
       assertInstanceOf(IllegalBetException.class, e);
    }
  }

  @Test
  void playerGetsThirdCard() {
    Balance b = new Balance(1000);
    Game game = new Game();
    game.setBet("1");
    game.setAmount(50);

    Card card1 = new Card(Rank.ACE, Suit.CLUBS);
    Card card2 = new Card(Rank.THREE, Suit.SPADES);
    Hand playerHand = new Hand();
    playerHand.add(card1);
    playerHand.add(card2);
    assertTrue(game.playerGetsThirdCard(playerHand));
  }

  @Test
  void bankerGetsThirdCard() {
     Game game = new Game();
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
    assertTrue(game.bankerGetsThirdCard(playerHand, bankerHand));
  }

  @Test
  void updateBalance(){
    Game game = new Game();
    game.setBet("1");
    game.setAmount(50);
    Result result = Result.BANKER;
    Bet bet = Bet.BANKER;
    game.updateBalance(result, bet);
    assertEquals(1050, game.getBalance());
  }



}
