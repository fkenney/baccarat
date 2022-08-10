package com.teambaccrat.controller;

import com.teambaccrat.model.Game;
import com.teambaccrat.view.TextGamePresentation;
import org.w3c.dom.Text;

public class Controller {

  private Game game;
  private TextGamePresentation presentation;

  public Controller (Game game, TextGamePresentation presentation){
    super();
    this.game = game;
    this. presentation = presentation;
  }


}
