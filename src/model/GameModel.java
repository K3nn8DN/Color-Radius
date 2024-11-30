package model;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import controller.Controller;
import model.boards.Board;
import model.cards.AdjacentCard;
import model.cards.Card;
import model.cards.CornersCard;
import model.cards.FullRadiusCard;
import view.Panels.Panel;

public class GameModel implements Model {
  private Map<Coordinate, Panel> grid;
  private Phase phase= Phase.SETUP;
  private Controller controller;
  private List<Color> unusedPebbles;
  private List<Coordinate> inactive = new ArrayList<Coordinate>();
  private List<Card> cards = new ArrayList<Card>();
  List<Color> removeColors = new ArrayList<Color>();

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
    grid = board.createBoard(panels);
    unusedPebbles = board.getPebbles();
    for (int i=0; i<4; i++) {
      cards.add(new AdjacentCard());
      cards.add(new CornersCard());
      if(i>2) {
        cards.add(new FullRadiusCard());
      }
    }
    phase = Phase.PLACEPEBBLE;
    sendUpdate();
  }

  @Override
  public void movePebble(Panel from, Panel to) {
    //check for color match
    if(from.getPebble() == to.getColor() || to.getColor() == Color.white) {
      to.setPebble(from.getPebble());
      from.setPebble(null);
    }
    else{
      throw new IllegalArgumentException("colors must match");
    }
    sendUpdate();
  }

  @Override
  public List<Color> getPebbles(){
    return unusedPebbles;
  }

  @Override
  public void placePebble(int index, Panel to) {
    //check for color match
    if (to.getPebble() == null) {
      if (unusedPebbles.get(index) == to.getColor() || to.getColor() == Color.white) {
        to.setPebble(unusedPebbles.get(index));
        to.setPebble(unusedPebbles.get(index));
        unusedPebbles.remove(index);
      }
      else {
        throw new IllegalArgumentException("colors must match");
      }
      if (unusedPebbles.isEmpty()) {
        phase = Phase.PLAYCARD;
      }
      sendUpdate();
    }
    else{
      throw new IllegalArgumentException("only one pebble per spot");
    }
  }

  @Override
  public void removePannel(Panel panel) {
    if(removeColors.contains(panel.getColor()) || panel.getColor() == Color.white) {
      panel.setActive(false);
      removeColors.remove(panel.getColor());
      panel.setColor(Color.black);
      panel.setPebble(null);
    }
    else{
      throw new IllegalArgumentException("colors must match");
    }
    if(removeColors.isEmpty()){
      phase = Phase.PLAYPEBBLE;
      reorderGrid();
    }
    sendUpdate();
  }

  @Override
  public List<Color> getRemoveColors() {
    return removeColors;
  }

  @Override
  public void playCard(Card card, Panel panel) {
    inactive.addAll(card.Play(panel,grid));
    removeColors = card.getColors();
    phase = Phase.REMOVE;
    isGameOver();
    sendUpdate();
  }

  @Override
  public List<Card> getCards() {
    return cards;
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
    sendUpdate();


  }

  @Override
  public boolean isGameOver() {
    if(phase == Phase.ENDGAME ){
      return true;
    }
    for (Panel value : grid.values()) {
      if (value.getPebble() != null){
        return false;
      }
    }
    phase= Phase.ENDGAME;
    return true;
  }

  @Override
  public Phase getPhase() {
    return phase;
  }

  @Override
  public List<Panel> getPanels() {
    List<Panel> panels = new ArrayList<Panel>();
    panels.addAll(grid.values());
    return panels;
  }
}
