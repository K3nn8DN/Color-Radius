package view.Panels;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;

import controller.Controller;
import model.Coordinate;
import view.GameView;

public class GridPanel extends JPanel implements Panel {
  Color pebble;
  Controller controller;
  Color color;
  boolean isActive=true;
  Coordinate coord;
  GameView frame;
  boolean clicked=false;


  public GridPanel(GameView view) {
    this.controller=view.getController();
    this.addMouseListener(new GridPanel.GridClickListener(this));
    this.color=Color.BLACK;
    this.setPreferredSize(new Dimension(200, 200));
    this.frame=view;
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);

    // Get the dimensions of the panel
    int width = getWidth();
    int height = getHeight();

    // Set the size of the dot
    int dotSize = 15;

    // Calculate the position to center the dot
    int x = (width - dotSize) / 2;
    int y = (height - dotSize) / 2;

    if(pebble != null) {
      if(clicked && color != Color.white) {
        g.setColor(Color.white);// Border color
      }
      if(clicked && color == Color.white ) {
        g.setColor(Color.black);
      }
      else{
        g.setColor(Color.BLACK);// Border color
      }
        g.fillOval(x - 2, y - 2,
                dotSize + 2 * 2,
                dotSize + 2 * 2);


      // Draw the dot (a filled circle)
      g.setColor(pebble); // Dot color
      g.fillOval(x, y, dotSize, dotSize);
    }


  }

  @Override
  public void sendController() {
    controller.updateGrid(this);
  }

  @Override
  public Color getPebble() {
    return pebble;
  }

  @Override
  public void setPebble(Color color) {
    this.pebble = color;
  }

  @Override
  public Color getColor() {
    return color;
  }

  @Override
  public void setColor(Color color) {
    this.color = color;
  }

  @Override
  public boolean isActive() {
    return isActive;
  }

  @Override
  public void setActive(boolean active) {
    this.isActive = active;
  }

  @Override
  public void setClicked(boolean clicked) {
    this.clicked=clicked;
  }


  static class GridClickListener implements MouseListener {
    private final GridPanel panel;

    public GridClickListener(GridPanel panel) {
      this.panel = panel;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
      panel.sendController();
      panel.frame.setactivePebble(panel);
      panel.controller.update();

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
