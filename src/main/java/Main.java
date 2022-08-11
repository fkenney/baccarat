import com.teambaccrat.controller.Controller;
import com.teambaccrat.model.Balance;
import com.teambaccrat.model.Game;
import com.teambaccrat.view.View;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {


  public static void main(String[] args) throws IOException {

    Game game = new Game();
    View view = new View();
    Controller c = new Controller(game, view);
    c.presentGreeting();

    boolean run = true;
    while (run) {
      c.setBet();
      c.setAmount();
      c.startGame();
      run = c.isValidBalance() && continueGame();
    }
    System.out.println(
        "Thank you for playing Baccarat. Your result is : Balance: " + Balance.getBalance()
            + " total play, win, lose");
  }


  public static boolean continueGame() throws IOException {

    String userInput;
    boolean gameContinue = false;
    do {
      System.out.println("Dealer: Do you want to play again? 1.Yes 2.No");
      BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
      userInput = reader.readLine();
    } while (!isValid(Integer.parseInt(userInput)));
    if (userInput.equals("1")) {
      gameContinue = true;
    } else if (userInput.equals("2")) {
      gameContinue = false;
    }
    return gameContinue;
  }


  private static boolean isValid(int userInput) {

    if (userInput == 1 || userInput == 2) {
      return true;
    } else {
      System.out.println("please, put the valid number.");
      return false;
    }
  }

}







