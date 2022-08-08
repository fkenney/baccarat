package com.teambaccrat.controller;

import com.teambaccrat.model.Card;
import java.util.ArrayList;


public class Dealer {
  ArrayList<Card> deck;
  Dealer(){
    deck = new ArrayList<Card>();
  }


  // Deal The Hand
  public ArrayList<Card> dealHand(){
    ArrayList<Card> newCard = new ArrayList<>();
    Card card1 = deck.get(0);
    Card card2 = deck.get(1);
    newCard.add(card1);
    newCard.add(card2);
    deck.remove(0);
    deck.remove(0);

    return newCard;
  }

  // return deck size
  public int deckSize(){
    return this.deck.size(); // return the deck size
  }
}
