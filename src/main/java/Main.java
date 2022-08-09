import com.teambaccrat.model.Balance;
import com.teambaccrat.model.Game;
import com.teambaccrat.model.exception.IllegalBetException;
import com.teambaccrat.model.exception.NoBalanceException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

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

  String bet;
  String amount;



  //getting bet input from the user. possibly need to move to the Dealer class in controller later.
  public String getBet () throws IOException {
    System.out.println(
        " Who do you want to put the bet on? 1. Banker 2. Player 3. Tie. Press the number, please");
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    bet = reader.readLine();

    if (bet.equals("1")) {
      System.out.println("You made a bet on Banker");
    } else if (bet.equals("2")) {
      System.out.println("You made a bet on Player");

    } else if (bet.equals("3")) {
      System.out.println("You made a bet on Tie");

    } else {
      throw new IllegalBetException("Please place a valid bet of '1' for Banker, '2' for Player, or '3' for Tie");
    }
    return bet;
  }

  // need to work on maxBet and minBet.
  public int getAmount() throws IOException {
    System.out.println("How much do you want to bet on " + bet + "?");
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    amount = reader.readLine();
    int balance = Balance.getBalance();
    int betAmount = Integer.getInteger(amount);
    if (betAmount > balance){
      throw new NoBalanceException("Your balance is : " + balance + ". Please, bet under " + balance + ".");
    }return betAmount;
  }
}

