package view.Panels;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;

import controller.Controller;
import model.boards.Board;
import model.cards.Card;
import view.GameView;

public class CardPanel  extends JPanel{
  Image image;
  Card card;
  GameView frame;
  Controller controller;


  public CardPanel(Image image, Card card, GameView gameView,Controller controller) {
    this.image = image;
    this.card = card;
    this.frame = gameView;
    this.controller = controller;
    this.addMouseListener(new CardPanel.CardClickListener(this));
  }
  public CardPanel(Card card, GameView gameView,Controller controller) {
    this.card = card;
    this.frame = gameView;
    this.controller = controller;
    this.addMouseListener(new CardPanel.CardClickListener(this));
  }

  public void sendController() {
    controller.updateClick(this.card);
  }


  static class CardClickListener implements MouseListener {
    private final CardPanel panel;

    public CardClickListener(CardPanel panel) {
      this.panel = panel;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
      CardPanel clickedPanel = (CardPanel) e.getSource();
      if (panel.frame.getActiveCard() != null) {
        panel.frame.getActiveCard().setBorder(BorderFactory.createLineBorder(Color.GRAY));
      }
      panel.frame.setActiveCard(clickedPanel);
      panel.frame.getActiveCard().setBorder(BorderFactory.createLineBorder(Color.ORANGE, 3));
      panel.sendController();
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
  }


}
