package com.teambaccrat.view;


public class View {

  private final String betPrompt = "Dealer: Please place a bet, enter 1-Banker, 2-Player, 3-Tie";
  private final String betInfoPresentation = "You made a bet on %s%n";
  private final String wagerAmountPresentation = "You wagered %d%n";
  private final String greeting = "Dealer: Welcome to the Baccarat table!";
  private final String wagerPrompt = "Dealer: How much do you want to bet on %s? %nCurrent Balance is : %s, Min Bet = %d, Max Bet = %d %n";

  private final String cardPresentation = "Dealer dealt card to %s : %s (%s)%n %s (%s)%n ";

  private final String finalHandValuePresentation = "%nPlayer Hand %s  = %s points%nBanker Hand %s = %s points%n";
  private final String gameStartPresentation =
      "%n============================================%n"
          + "Wager: %s \t\t Bet: %s \t\t Balance: %s%n"
          + "============================================%n ";

  private final String gameResultPresentation =
      "============================================%n"
          + "Result:%n%s, %s%s) \tBalance: %s%n"
          + "============================================%n";

  public String betPrompt() {
    return betPrompt;
  }

  public String wagerPrompt() {
    return wagerPrompt;
  }

  public String betInfoPresentation() {
    return betInfoPresentation;
  }

  public String wagerAmountPresentation() {
    return wagerAmountPresentation;
  }

  public String greeting() {
    return greeting;
  }

  public String gameStartPresentation() {
    return gameStartPresentation;
  }

  public String getGameResultPresentation() {
    return gameResultPresentation;
  }

  public String cardPresentation() {
    return cardPresentation;
  }

  public String getFinalHandValuePresentation() {
    return finalHandValuePresentation;
  }
}

