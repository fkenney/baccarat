package com.teambaccrat.model;

import java.security.SecureRandom;
import java.util.Random;
import org.junit.jupiter.api.Test;

public class TestShoe {
  private static final double MARKER_MIN = 0.6667;
  private static final double MARKER_MAX = 0.8;

  @Test
  public void testMarkerPointIsRandom(){
    Random rnd = new SecureRandom();
    int numDecks = 8;
    double markerPoint = rnd.nextDouble() * (MARKER_MAX - MARKER_MIN) + MARKER_MIN;
    Shoe shoe = new Shoe(numDecks, rnd, markerPoint);
    System.out.println(markerPoint);
  }

}
