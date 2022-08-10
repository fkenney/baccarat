package com.teambaccrat.controller;

import com.teambaccrat.model.Balance;
import com.teambaccrat.model.Game;
import com.teambaccrat.model.exception.IllegalBetException;
import com.teambaccrat.model.exception.IllegalWagerAmountException;
import com.teambaccrat.view.View;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Controller {

  private static Game game;
  private View view;

  public Controller(Game game, View view) {
    super();
    this.game = game;
    this.view = view;
  }

  public static void setBet() throws IOException {
    System.out.println(
        " Who do you want to put the bet on? 1. Banker 2. Player 3. Tie. Press the number, please");
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    String bet = reader.readLine();

    if (bet.equals("1")) {
      System.out.println("You made a bet on Banker");
    } else if (bet.equals("2")) {
      System.out.println("You made a bet on Player");

    } else if (bet.equals("3")) {
      System.out.println("You made a bet on Tie");

    } else {
      throw new IllegalBetException(
          "Please, place a valid bet of '1' for Banker, '2' for Player, or '3' for Tie");
    }
    game.setBet(bet);
    updateView();
  }

  public void setAmount () throws IllegalWagerAmountException{
    try(BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))){
      System.out.println("How much do you want to bet?");
      String userAmount = reader.readLine();
      game.setAmount(Integer.parseInt(userAmount));
      //presentAmount();
    }catch(IllegalWagerAmountException e){
      System.out.println(e.getMessage());
    }catch(IOException e){
      throw new RuntimeException(e);
    }




    updateView();
  }

  public void startGame() {
    game.start((hand,isPlayer) -> {
      System.out.println("dealt to " + (isPlayer? "player" : "banker") + ": " + hand.getLastCard() + "\n" + hand);
      try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        // ignore exception
      }
    });
  }

  public static void updateView() {

  }


}
