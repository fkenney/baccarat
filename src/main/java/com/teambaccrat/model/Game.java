package com.teambaccrat.model;

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
  private final Shoe shoe;
  private final Bet bet;
  private State state;


  public Game(Shoe shoe, Bet bet) {
    this.shoe = shoe;
    this.bet = bet;
    Hand playerHand = new Hand();
    Hand bankerHand = new Hand();
  }

  public State getState() {
    return state;
  }



  public State play (Hand playerHand, Hand bankerHand){
    // Create logic for each State

    // PLAYER_ADDITIONAL_CARD
    // TODO, Determine if player gets 3rd Card
    //  If playerHand total is < 6, Player gets 3rd Card


    // PLAYER_WIN or TIE
    // TODO, PLAYER_WINS / TIE
    //  If playerHand is 8 or 9 PLAYER_WINS OR TIE

    // BANKER_ADDITIONAL_CARD
    // TODO, IF player doesn't have 3 cards,
    //  If bankerHand total is < 6, Banker gets 3rd Card

    // BANKER_WINS or TIE
    // TODO, PLAYER_WINS/TIE
    //  If bankerHand is 8 or 9 BANKER_WINS OR TIE // TIE

    // TODO, ELSE IF ---------Player has 3 cards
    //  If PLAYER_ADDITIONAL_CARD is 2 or 3, BANKER Gets Card  if  CurrentTotal < 5 (stays at 5 -7)
    //  If PLAYER_ADDITIONAL_CARD is 4 or 5, Banker Gets Card if CurrentTotal < 6. (stays at 6 - 7)
    //  If PLAYER_ADDITIONAL_CARD is 6 or 7, Banker Gets Card if CurrentTotal < 7. (stays at 7)
    //  If PLAYER_ADDITIONAL_CARD is 8, Banker Gets Card if CurrentTotal < 3. (stays at 3 - 7)
    //  If PLAYER_ADDITIONAL_CARD is 9, 10, J, Q, K, A, Banker Gets Card if CurrentTotal < 4. (stays at 4 - 7)

    // TODO WIN_STATE
    return State.PLAYER_WIN;
  }

  public enum State {
    PLAYER_ADDITIONAL_CARD,
    BANKER_ADDITIONAL_CARD,
    PLAYER_WIN,
    BANKER_WIN,
    TIE;

  }

}
