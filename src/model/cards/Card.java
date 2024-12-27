package model.cards;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import model.Coordinate;
import view.Panels.Panel;

public abstract class Card {
  protected Coordinate originalCoord;
  protected List<Color> colors = new ArrayList<Color>();
  protected List<Coordinate> targetCoords = new ArrayList<Coordinate>();
  protected List<Coordinate> returnCoords = new ArrayList<Coordinate>();


  protected abstract void addTargetCoords();

  public abstract String toString();

  public abstract String description();

  public abstract Image getImage();

  public List<Coordinate> Play(Panel panel, Map<Coordinate, Panel> grid) {

    for (Map.Entry<Coordinate, Panel> entry : grid.entrySet()) {
      if (entry.getValue().equals(panel)) {
        originalCoord = entry.getKey();
        break;
      }
    }
    addTargetCoords();

    for (Coordinate coord : grid.keySet()) {
      if (targetCoords.contains(coord) && grid.get(coord).getPebble() != null) {
          returnCoords.add(coord);
          colors.add(grid.get(coord).getColor());
          grid.get(coord).setActive(false);
          grid.get(coord).setColor(Color.black);
          grid.get(coord).setPebble(null);

      }
    }

    return returnCoords;
  }

  public List<Color> getColors() {
    return colors;
  }


}
