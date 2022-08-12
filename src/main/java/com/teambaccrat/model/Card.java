package com.teambaccrat.model;

import java.util.Comparator;
import java.util.Objects;

public class Card implements Comparable<Card> {

  private static final Comparator<Card> NATURAL_ORDER_COMPARATOR = Comparator
      .comparing(Card::getSuit)
      .thenComparing(Card::getRank);

  private final Rank rank;
  private final Suit suit;
  private final String representation;
  private final int hash;

  public Card(Rank rank, Suit suit) {
    this.rank = rank;
    this.suit = suit;
    representation = rank.getSymbol() + suit.getSymbol();
    hash = Objects.hash(rank, suit);
  }

  public Rank getRank() {
    return rank;
  }

  public Suit getSuit() {
    return suit;
  }

  @Override
  public int hashCode() {
    return hash;
  }

  @Override
  public boolean equals(Object obj) {
    boolean result;
    if (this == obj) {
      result = true;
    } else if (obj instanceof Card) {
      Card other = (Card) obj;
      result = (rank == other.rank && suit == other.suit);
    } else {
      result = false;
    }
    return result;
  }

  @Override
  public String toString() {
    return representation;
  }

  @Override
  public int compareTo(Card other) {
    return NATURAL_ORDER_COMPARATOR
        .compare(this, other);
  }

}



