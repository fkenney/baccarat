package com.teambaccrat.model;

import static org.junit.jupiter.api.Assertions.*;

import com.teambaccrat.model.Game.Result;
import com.teambaccrat.model.exception.IllegalBetException;
import org.junit.jupiter.api.Test;

public class TestGame {

  @Test
  public void testThrowsIllegalBet() {
    try {
      int amount = 50;
      String bet = "8";
      Game game = new Game(amount, bet);
    } catch (IllegalBetException e) {
       assertInstanceOf(IllegalBetException.class, e);
    }
  }

  @Test
  void playerGetsThirdCard() {
    Balance b = new Balance(1000);
    int amount = 100;
    String bet = "1";
    Game game = new Game(amount, bet);
    Card card1 = new Card(Rank.ACE, Suit.CLUBS);
    Card card2 = new Card(Rank.THREE, Suit.SPADES);
    Hand playerHand = new Hand();
    playerHand.add(card1);
    playerHand.add(card2);
   // assertTrue(game.playerGetsThirdCard(playerHand));
  }

  @Test
  void bankerGetsThirdCard() {
    Balance b = new Balance(1000);
    int amount = 100;
    String bet = "1";
    Game game = new Game(amount, bet);
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
   // assertTrue(game.bankerGetsThirdCard(playerHand, bankerHand));
  }

  @Test
  void updateBalance(){
    Balance b = new Balance(1000);
    int amount = 100;
    String userBet = "1";
    Game game = new Game(amount, userBet);

    Bet bet = game.

    Result result = Result.BANKER;


    updateBalance(result, bet);
  }

}
