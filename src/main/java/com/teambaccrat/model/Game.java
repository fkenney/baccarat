package com.teambaccrat.model;

import com.teambaccrat.model.exception.GameFinishedException;
import com.teambaccrat.model.exception.IllegalBetException;
import java.lang.invoke.SwitchPoint;
import java.security.SecureRandom;
import java.util.List;
import java.util.Random;

public class Game {

  //Initialize in constructor
  // Player Hand
  // Banker Hand
  // Parameters
  // Shoe (parameter for Game)
  // Bet (parameter for game -- amount, who it's on)
  private final Hand player;
  private final Hand banker;

  private Shoe shoe;
  private final Bet bet;
  private State state;


  public Game(Shoe shoe, Bet bet) {
    this.shoe = shoe;
    this.bet = bet;
    player = new Hand();
    banker = new Hand();
  }

  public State getState() {
    return state;
  }

  public boolean playerGetsThirdCard(Hand player) {
    return player.pointValue() < 6;
  }

  public boolean bankerGetsThirdCard(Hand player, Hand banker) {
    boolean getThirdCard = false;
    int valueOfLastPlayerCard = player.getLastCard().getRank().getPoint();
    int bankerPoints = banker.pointValue();

    if (player.size() == 2 && bankerPoints < 6) {
      getThirdCard = true;
    } else if (player.size() == 3) {
      // If player has 3 Cards the value of the 3rd Card determines whether Banker gets 3rd Card.
      switch (valueOfLastPlayerCard) {
        case 2:
        case 3:
          getThirdCard = bankerPoints < 5;
          break;
        case 4:
        case 5:
          getThirdCard = bankerPoints < 6;
          break;
        case 6:
        case 7:
          getThirdCard = bankerPoints < 7;
          break;
        case 8:
          getThirdCard = bankerPoints < 3;
          break;
        case 0:
        case 1:
        case 9:
          getThirdCard = bankerPoints < 4;
      }
    }
    return getThirdCard;
  }

  public State whoWon(Hand player, Hand banker) {
    int playerPoints = player.pointValue();
    int bankerPoints = banker.pointValue();
    State winState = null;
    if ((playerPoints == 8 || playerPoints == 9) && playerPoints != bankerPoints) {
      winState = State.PLAYER_WIN;
    } else if ((bankerPoints == 8 || bankerPoints == 9) && playerPoints != bankerPoints) {
      winState = State.BANKER_WIN;
    } else if (playerPoints == bankerPoints) {
      winState = State.TIE;
    } else if (bankerPoints > playerPoints) {
      winState = State.BANKER_WIN;
    } else {
      winState = State.PLAYER_WIN;
    }
    return winState;
  }


  public enum State {
    START {
      @Override
      public boolean isTerminal() {
        return false;
      }
    },
    INITIAL_CARDS_DEALT {
      @Override
      public boolean isTerminal() {
        return false;
      }
    },
    PLAYER_ADDITIONAL_CARD {
      @Override
      public boolean isTerminal() {
        return false;
      }
    },
    BANKER_ADDITIONAL_CARD {
      @Override
      public boolean isTerminal() {
        return false;
      }
    },
    PLAYER_WIN,
    BANKER_WIN,
    TIE;

    public boolean isTerminal() {
      return true;
    }


    public State play(Bet bet, Shoe shoe) throws GameFinishedException {
      // Create logic for each State
      State nextState = null;

      return nextState;
    }


  }


}
