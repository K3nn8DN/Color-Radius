package view.Panels;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane;

import controller.Controller;
import model.cards.Card;
import view.GameView;

public class CardPanel  extends JPanel{
  Card card;
  GameView frame;
  Controller controller;


  public CardPanel(Card card, GameView gameView,Controller controller, int index) {
    this.card = card;
    this.frame = gameView;
    this.controller = controller;
    this.addMouseListener(new CardPanel.CardClickListener(this));

    this.add(new JLabel(card.toString()));
  }

  public void sendController() {
    controller.updateClick(this.card);
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    // Draw the background image, scaling it to fit the panel
    g.drawImage(card.getImage(), 0, 0, getWidth(), getHeight(), this);
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
