package model;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import controller.Controller;
import view.Panel;

public class GameModel implements Model {
  Map<Coordinate, Panel> grid;
  Phase phase= Phase.SETUP;
  Controller controller;
  List<Color> unusedPebbles;
  List<Coordinate> inactive;

  @Override
  public void setController(Controller controller) {
    this.controller = controller;
  }

  @Override
  public void sendUpdate() {
    this.controller.update();
  }

  @Override
  public void setUp(List<Panel> panels, Board board) {
    board.createBoard(panels);

  }

  @Override
  public void movePebble(Panel from, Panel to) {
    //check for color match
    if(from.getPebble() == to.getColor() || to.getColor() == Color.white) {
      to.setPebble(from.getPebble());
    }
    else{
      throw new IllegalArgumentException("colors must match");
    }

  }

  @Override
  public void placePebble(int index, Panel to) {
    //check for color match
    if(unusedPebbles.get(index) == to.getColor() || to.getColor() == Color.white) {
      to.setPebble(unusedPebbles.get(index));
    }
    else{
      throw new IllegalArgumentException("colors must match");
    }

  }

  @Override
  public void playCard(Card card, Panel panel) {
    card.Play(panel);
  }


  @Override
  public void reorderGrid() {
    int floor = (int)(Math.floor(Math.sqrt(grid.size())));
    if(inactive.size() < floor){
      if(grid.keySet().contains(new Coordinate(1, floor+1))){
        for (Coordinate value : grid.keySet()) {
          if(value.getX() == floor){
            if(grid.get(value).isActive()){
              grid.put(inactive.get(0), grid.get(value));
              grid.remove(value);
            }
            else{
              grid.remove(value);
            }
          }
        }
      }
      else{
        for (Coordinate value : grid.keySet()) {
          if(value.getY() == floor){
            if(grid.get(value).isActive()){
              grid.put(inactive.get(0), grid.get(value));
              grid.remove(value);
            }
            else{
              grid.remove(value);
            }
          }
        }
      }
    }


  }

  @Override
  public boolean isGameOver() {
    for (Panel value : grid.values()) {
      if (value.getPebble() != null){
        return false;
      }
    }
    return true;
  }

  @Override
  public Phase getPhase() {
    return phase;
  }

  @Override
  public List<Panel> getPanels() {
    List<Panel> panels = new ArrayList<Panel>();
    for (Panel value : grid.values()) {
      panels.add(value);
    }
    return panels;
  }
}
