package view;

import controller.Controller;
import model.Model;

public interface View {

   void setModel(Model model);
   void setController(Controller controller);
   void renderStart();
   void renderPlacePebble();
   void renderPlayCard();
   void renderPlayPebble();
   void renderEnd();
   void handleErrorMessage(String error);

}
