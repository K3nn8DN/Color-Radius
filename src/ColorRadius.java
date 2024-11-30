import controller.Controller;
import controller.GameController;
import model.GameModel;
import model.Model;
import view.GameView;
import view.View;

public class ColorRadius {

  public static void main(String[] args) throws InterruptedException {
    Model model= new GameModel();
    View colorView = new GameView();
    Controller controller = new GameController(model, colorView);




  }
}
