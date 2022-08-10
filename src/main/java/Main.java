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

    boolean run = true;
    while (run) {
      c.setBet();
      c.setAmount();
      c.startGame();
      run = continueGame();
    }
    System.out.println(
        "Thank you for playing Baccarat. Your result is : Balance: " + Balance.getBalance()
            + " total play, win, lose");
  }


    public static boolean continueGame () throws IOException {
      System.out.println("Do you want to play again? 1.Yes 2.No");
      BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
      String userInput = reader.readLine();
      boolean gameContinue;
      if (userInput.equals("1")) {
        gameContinue = true;
      } else if (userInput.equals("2")) {
        gameContinue = false;
      } else {
//        throw new NoValidInputException("please, put the valid number. 1.Yes 2.No");
      }
      return gameContinue;
    }
  }







