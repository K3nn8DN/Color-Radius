package model.cards;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import model.Coordinate;
import view.Panels.Panel;

public abstract class Card {
  protected Coordinate originalCoord;
  protected List<Coordinate> targetCoords = new ArrayList<Coordinate>();
  protected List<Coordinate> returnCoords = new ArrayList<Coordinate>();
  protected abstract void addTargetCoords();
  public abstract String toString();

  public List<Coordinate> Play(Panel panel, Map<Coordinate, Panel> grid) {
    for (Map.Entry<Coordinate, Panel> entry : grid.entrySet()) {
      if (entry.getValue().equals(panel)) {
        originalCoord = entry.getKey();
        break;
      }
    }

    addTargetCoords();

    for (Coordinate coord : grid.keySet()) {
      if (targetCoords.contains(coord)) {
        returnCoords.add(coord);
        grid.get(coord).setActive(false);
        grid.get(coord).setColor(Color.black);
        grid.get(coord).setPebble(null);
      }
    }

    return returnCoords;
  }


}
