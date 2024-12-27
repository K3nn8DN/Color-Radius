package model.cards;


import java.awt.*;

import javax.swing.*;

import model.Coordinate;


public class FullRadiusCard extends Card {
  @Override
  protected void addTargetCoords() {
    targetCoords.add(new Coordinate(originalCoord.getX() + 1, originalCoord.getY()));
    targetCoords.add(new Coordinate(originalCoord.getX() - 1, originalCoord.getY()));
    targetCoords.add(new Coordinate(originalCoord.getX(), originalCoord.getY() + 1));
    targetCoords.add(new Coordinate(originalCoord.getX(), originalCoord.getY() - 1));
    targetCoords.add(new Coordinate(originalCoord.getX() + 1, originalCoord.getY()+1));
    targetCoords.add(new Coordinate(originalCoord.getX() + 1, originalCoord.getY()-1));
    targetCoords.add(new Coordinate(originalCoord.getX()-1, originalCoord.getY() + 1));
    targetCoords.add(new Coordinate(originalCoord.getX()-1, originalCoord.getY() - 1));
  }
  @Override
  public String toString() {
    return "Full Radius";
  }

  @Override
  public String description() {
    return "Remove all pebbles around a piece";
  }

  @Override
  public Image getImage() {
    Image fullRadius =
            new ImageIcon(getClass().getResource("/FullRadiusCard.png")).getImage();
    return fullRadius;
  }
}
