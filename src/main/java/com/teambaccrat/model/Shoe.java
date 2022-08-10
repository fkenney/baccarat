package com.teambaccrat.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class Shoe {

  private final List<Card> cards;
  private final Random random;
  private final int reshuffleCount;

  private Iterator<Card> iterator;
  private int dealCount = Integer.MAX_VALUE;
  private boolean reshuffleNeeded = true;

  public Shoe(int numDecks, Random random, double markerPoint) {
    this.cards = new ArrayList<>(Suit.values().length * Rank.values().length * numDecks);
    this.random = random;

    for (int i = 0; i < numDecks; i++) {
      for (Suit suit : Suit.values()) {
        for (Rank rank : Rank.values()) {
          Card card = new Card(rank, suit);
          cards.add(card);
        }
      }
    }

    reshuffleCount = (int) (cards.size() * markerPoint);
  }

  public void startGame() {
    if (dealCount > reshuffleCount) {
      Collections.shuffle(cards, random);
      dealCount = 0;
      iterator = cards.iterator();
    }
  }

  @Override
  public String toString() {
    return cards.toString();
  }

  public int size() {
    return cards.size();
  }

  public boolean isEmpty() {
    return iterator != null && !iterator.hasNext();
  }

  public Card draw() {
    dealCount++;
    return iterator.next();
  }

}
