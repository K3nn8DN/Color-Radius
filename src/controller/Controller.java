package controller;

import java.util.List;

import model.boards.Board;
import view.Panels.Panel;

public interface Controller {
  void startGame(List<Panel> panels, Board board);
  void updateGrid(Panel panel);
  void updateClick(Object click);
  void update();
  void handleErrorMessage(String error);



}
