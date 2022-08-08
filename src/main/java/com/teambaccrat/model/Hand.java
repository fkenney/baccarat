package com.teambaccrat.model;

import java.util.ArrayList;
import java.util.List;


public class Hand {

   private List<Card> cards;


   public Hand(){
      cards = new ArrayList<>();
   }

   public int size(){
      return cards.size();
   }

   public int pointValue(){
      int points = 0;
      for(Card card: cards){
         // TODO : Assign a point value to each card?
         // points += card.Rank.Value;
      }
      return points;
   }

   public String showHand(){
      return cards.toString();
   }
}
