package model.cards;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import model.Coordinate;
import view.Panels.Panel;

public class AdjacentCard extends Card {

  @Override
  protected void addTargetCoords() {
    targetCoords.add(new Coordinate(originalCoord.getX() + 1, originalCoord.getY()));
    targetCoords.add(new Coordinate(originalCoord.getX() - 1, originalCoord.getY()));
    targetCoords.add(new Coordinate(originalCoord.getX(), originalCoord.getY() + 1));
    targetCoords.add(new Coordinate(originalCoord.getX(), originalCoord.getY() - 1));
  }
  @Override
  public String toString() {
    return "Adjacent Card";
  }

  @Override
  public String description() {
    return "Removes all Adjacent Cards next to a piece";
  }
}

