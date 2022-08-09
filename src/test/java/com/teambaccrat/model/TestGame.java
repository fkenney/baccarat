package com.teambaccrat.model;

import static org.junit.jupiter.api.Assertions.*;

import com.teambaccrat.model.exception.IllegalBetException;
import org.junit.jupiter.api.Test;

public class TestGame {

  @Test
  public void testThrowsIllegal( ){
   try{
     int amount = 50;
     String bet = "8";
     Game game = new Game(amount, bet);
   }catch(IllegalBetException e){
      assertInstanceOf(IllegalArgumentException.class, e.getCause());
   }

  }

}
