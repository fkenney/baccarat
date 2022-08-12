import com.teambaccrat.controller.Controller;
import com.teambaccrat.model.Balance;
import com.teambaccrat.model.Game;
import com.teambaccrat.view.View;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


/**
 * Creates game,
 * {@link com.teambaccrat.model.Game}
 * view,
 * {@link com.teambaccrat.view.View}
 * controller
 * {@link com.teambaccrat.controller.Controller}
 * and take care of overall game flows
 *
 *
 */

public class Main {

  private static final int DEFAULT_INITIAL_BALANCE = 1000;

  /**
   * Creates a game, set the bet, set the bet amount, and start the game
   * until the user choose not to play, or user doesn't have enough balance.
   *
   * @param args String[]
   */

  public static void main(String[] args) throws IOException {

    Game game = new Game((args.length > 0) ? Integer.parseInt(args[0]) : DEFAULT_INITIAL_BALANCE);
    View view = new View();
    Controller c = new Controller(game, view);
    c.presentGreeting();
    boolean run;

    do {
      c.setBet();
      c.setAmount();
      c.startGame();
      run = c.isValidBalance() && continueGame();
    } while (run);
    System.out.println(
        "Thank you for playing Baccarat. Your Balance: " + Balance.getBalance()
    );
  }

  /**
   * Determines whether to exit the game by user input
   *
   * @throws IOException Thrown if user input cannot be read;
   * @return boolean
   *
   */

  public static boolean continueGame() throws IOException {

    String userInput;
    boolean gameContinue = false;
    do {
      System.out.println("Dealer: Do you want to play again? 1.Yes 2.No");
      BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
      userInput = reader.readLine();
    } while (!isValidInput(Integer.parseInt(userInput)));
    if (userInput.equals("1")) {
      gameContinue = true;
    } else if (userInput.equals("2")) {
      gameContinue = false;
    }
    return gameContinue;
  }



  /**
   * Checks if user input is valid
   *
   * @param userInput int
   * @return boolean
   *
   */
  private static boolean isValidInput(int userInput) {

    if (userInput == 1 || userInput == 2) {
      return true;
    } else {
      System.out.println("please, put the valid number.");
      return false;
    }
  }

}







