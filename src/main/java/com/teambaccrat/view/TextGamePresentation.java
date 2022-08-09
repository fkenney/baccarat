package com.teambaccrat.view;

import com.teambaccrat.model.Game;
import com.teambaccrat.model.Hand;

public class TextGamePresentation implements GamePresentation<String> {

  public String stateRepresentation (Game game, Hand player, Hand banker){
    return String.format("Player hand = %s | Banker hand = %s%n Player Points %d | Banker Points %d",
        player.toString(), banker.toString(), player.pointValue(), banker.pointValue());

  }

  public String dealtExtraCardNotice (String user){
    // prints out if player or banker gets a new card
    return String.format("%s gets a third card", user);
  }


}
