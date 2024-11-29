package model;

import java.util.List;

import controller.Controller;
import view.Panel;

public interface Model extends ReadOnlyModel{
   void setController(Controller controller);
   void sendUpdate();
   void setUp(List<Panel> panels, Board board);
   void movePebble(Panel from, Panel to);
   void placePebble(int index, Panel to);
   void playCard(Card card, Panel panel);
   void reorderGrid();


}
