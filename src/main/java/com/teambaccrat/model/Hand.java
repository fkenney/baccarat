package com.teambaccrat.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Creates the hands that will be used to hold dealt cards.
 */
public class Hand {

   private final List<Card> cards;


   public Hand(){
      cards = new ArrayList<>();
   }

   public int size(){
      return cards.size();
   }


   /***
    * The pointValue() returned to the {@link com.teambaccrat.model.Game} clas.
    * @return point values calculated for each player in the game.
    */
   public int pointValue(){
      int points = 0;
      int rem= 0;
      for(Card card: cards){
        points += card.getRank().getPoint();
      }
      if(points >= 10){
         points = points%10;
      }
      return points;
   }

   @Override
   public String toString() {
      return cards.toString();
   }

   public void add (Card card){
      cards.add(card);
   }

   public Card getLastCard(){
      return cards.get(cards.size()-1);
   }
}
