package model;

import java.awt.*;
import java.util.List;

import controller.Controller;
import model.boards.Board;
import model.cards.Card;
import view.Panels.Panel;

public interface Model extends ReadOnlyModel{
   void setController(Controller controller);
   void sendUpdate();
   void setUp(List<Panel> panels, Board board);
   void movePebble(Panel from, Panel to);
   List<Color> getPebbles();
   void placePebble(int index, Panel to);
   void removePannel(Panel panel);
   List<Color> getRemoveColors();
   void playCard(Card card, Panel panel);
   List<Card> getCards();
   void reorderGrid();


}
