package model.cards;


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
    return "AdjacentCard";
  }

}
