package view.Panels;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;

import model.boards.Board;
import view.GameView;

public class BoardPanel extends JPanel {
  Image image;
  Board board;
  GameView frame;


  public BoardPanel(Board board, GameView gameView) {
    this.board = board;
    this.frame = gameView;
    this.addMouseListener(new BoardPanel.BoardClickListener(this));
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    // Draw the background image, scaling it to fit the panel
    g.drawImage(board.getImage(), 0, 0, getWidth(), getHeight(), this);
  }

  public Board getBoard() {
    return board;
  }


  static class BoardClickListener implements MouseListener {
    private final BoardPanel panel;

    public BoardClickListener(BoardPanel panel) {
      this.panel = panel;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
      BoardPanel clickedPanel = (BoardPanel) e.getSource();
      if (panel.frame.getActiveBoard() != null) {
        panel.frame.getActiveBoard().setBorder(BorderFactory.createLineBorder(Color.GRAY));
      }
      panel.frame.setActiveBoard(clickedPanel);
      panel.frame.getActiveBoard().setBorder(BorderFactory.createLineBorder(Color.ORANGE, 3));


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


