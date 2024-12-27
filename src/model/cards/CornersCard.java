package model.cards;


import java.awt.*;

import javax.swing.*;

import model.Coordinate;

public class CornersCard extends Card {
  @Override
  protected void addTargetCoords() {
    targetCoords.add(new Coordinate(originalCoord.getX() + 1, originalCoord.getY()+1));
    targetCoords.add(new Coordinate(originalCoord.getX() + 1, originalCoord.getY()-1));
    targetCoords.add(new Coordinate(originalCoord.getX()-1, originalCoord.getY() + 1));
    targetCoords.add(new Coordinate(originalCoord.getX()-1, originalCoord.getY() - 1));
  }
  @Override
  public String toString() {
    return "Corner Card";
  }

  @Override
  public String description() {
    return "Removes all pebbles in the four corners of a piece";
  }

  @Override
  public Image getImage() {
    Image corner = new ImageIcon(getClass().getResource("/CornersCard.png")).getImage();
    return corner;
  }

}
