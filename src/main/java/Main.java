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
//      String bet = getBet();
//      int amount = getAmount();
//      System.out.println("You bet $" + amount + " on " + Bet.getName(bet) + ".");
//      Game game = new Game(amount, bet);
//      game.start();
      run = continueGame();
    }
    System.out.println("Thank you for playing Baccarat. Your result is : Balance: " + Balance.getBalance() + " total play, win, lose" );
  // showing balance, total game played, wins, lose.

  }



  // need to work on maxBet and minBet.



  
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

