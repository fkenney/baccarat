package com.teambaccrat.model;

import static org.junit.jupiter.api.Assertions.*;

import com.teambaccrat.model.exception.IllegalBetException;
import org.junit.jupiter.api.Test;

public class TestGame {

  @Test
  public void testThrowsIllegal() {
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
    int amount = 50;
    String bet = "1";
    Game game = new Game(amount, bet);
    game.start();

  }

  @Test
  void bankerGetsThirdCard() {
  }

  @Test
  void whoWon() {
  }

  @Test
  void start() {

  }
}
