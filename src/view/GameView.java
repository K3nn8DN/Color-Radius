package view;

import controller.Controller;
import model.Model;
import javax.swing.JOptionPane;

public class GameView implements View {
  Model model;
  Controller controller;
  @Override
  public void setModel(Model model) {
    this.model = model;
  }

  @Override
  public void setController(Controller controller) {
    this.controller = controller;
  }

  @Override
  public void renderStart() {

  }

  @Override
  public void renderPlacePebble() {

  }

  @Override
  public void renderPlayCard() {

  }

  @Override
  public void renderPlayPebble() {

  }

  @Override
  public void renderEnd() {

  }

  @Override
  public void handleErrorMessage(String error) {
    JOptionPane.showMessageDialog(null, error,
            "Information", JOptionPane.INFORMATION_MESSAGE);
  }
}
