package controller;

import java.util.List;

import model.Board;
import model.Model;
import view.Panel;
import view.View;

public interface Controller {
  void startGame(List<Panel> panels, Board board);
  void updateGrid(Panel panel);
  void updateClick(Object click);
  void update();
  void handleErrorMessage(String error);



}
