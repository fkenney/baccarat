import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {


  //getting bet input from the user. possibly move to Dealer class in controller
  public String getBet (String[] args) throws IOException {
    System.out.println(
        " Who do you want to put the bet on? 1. Banker 2. Player 3. Tie. Press the number, please");
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    String bet = reader.readLine();

    if (bet.equals("1")) {
      System.out.println("You made a bet on Banker");
    } else if (bet.equals("2")) {
      System.out.println("You made a bet on Player");

    } else if (bet.equals("3")) {
      System.out.println("You made a bet on Tie");

    } else {
      throw new IOException("please, choose number between 1-3 (1. Banker 2.Player, 3.Tie");
    }
    return bet;
  }

    // Welcome Baccarat
    // Please enter your name

    // Build Deck
    // Repeat the following as user continues to play
    // Shuffle Deck with Random
    // Create Player, Banker Hand
    // Get Bets, 1 Player, 2 Banker, 3 Tie
    // Deal one card to Player Hand
    // Banker
    // Player
    // Banker
    // Calculate points to determine 3rd Card
    // Calculate points to determine winner
    // Tell user if they won and give points.
    // Would you like to play again?// Thanks for playing
  }

