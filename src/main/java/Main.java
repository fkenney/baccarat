import com.teambaccrat.model.Balance;
import com.teambaccrat.model.Bet;
import com.teambaccrat.model.Game;
import com.teambaccrat.model.exception.IllegalBetException;
import com.teambaccrat.model.exception.NoBalanceException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

  public static void main(String[] args) throws IOException {
    boolean run = true;
    while (run) {
/*      String bet = getBet();
      int amount = getAmount();
      System.out.println("You bet $" + amount + " on " + Bet.getName(bet) + ".");
      Game game = new Game(amount, bet);
      game.start();
      run = continueGame();*/
    }
    System.out.println("Thank you for playing Baccarat. Your result is : Balance: " + Balance.getBalance() + " total play, win, lose" );
  // showing balance, total game played, wins, lose.

  }

  public static String getBet() throws IOException {
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
      throw new IllegalBetException("Please, place a valid bet of '1' for Banker, '2' for Player, or '3' for Tie");
    }
    return bet;
  }

  // need to work on maxBet and minBet.
  public static int getAmount() throws IOException {
    Balance balance = new Balance(Balance.getBalance());
    System.out.println("How much do you want to bet?");
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    String amount = reader.readLine();
    System.out.println(amount);
//    int betAmount = Integer.getInteger(amount);

    System.out.println("You put $" + amount);

    return Integer.parseInt(amount);
    }


  
  public static boolean continueGame() throws IOException {
    System.out.println("Do you want to play again? 1.Yes 2.No");
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    String userInput = reader.readLine();
    boolean gameContinue;
    if (userInput.equals("1")){
      gameContinue = true;

    }else if (userInput.equals("2")){
      gameContinue = false;
    }else {
      throw new IllegalArgumentException("please, put the valid number. 1.Yes 2.No");
    } return gameContinue;
  }
}

