package model;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
  private List<Color> removeColors = new ArrayList<>();

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
    for (int i=0; i<2; i++) {
      cards.add(new AdjacentCard());
      cards.add(new CornersCard());
      if(i==1) {
        cards.add(new FullRadiusCard());
      }
    }
    Collections.shuffle(cards);
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
    phase = Phase.PLAYCARD;
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
        Collections.shuffle(cards);
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
    removeColors.addAll(card.getColors());
    for(int i=0; i<cards.size(); i++) {
      if(cards.get(i).equals(card)) {
        cards.remove(i);
      }
    }
    for (Color color : card.getColors()) {
      if(removeColors.contains(color)) {
        removeColor(color);
      }
      else{
        if(mathColorInGrid(color)) {
          removeColors.add(color);
        }
      }
    }
    phase = Phase.REMOVE;
    if(removeColors.isEmpty()){
      phase = Phase.PLAYPEBBLE;
      reorderGrid();
    }
    isGameOver();
    sendUpdate();
  }

  private void removeColor(Color color) {
    for (int i=0; i< removeColors.size(); i++) {
      if (removeColors.get(i) == color) {
        removeColors.remove(i);
      }
    }
  }

  boolean mathColorInGrid(Color color){
    for(Panel p : grid.values()) {
      if(p.isActive() && p.getColor().equals(color)) {
        return true;
      }
    }
    return false;
  }

  @Override
  public List<Card> getCards() {
    return cards;
  }


  @Override
  public void reorderGrid() {
    if(grid.size() > 10) {
      // Collect active panels
      List<Panel> activePanels = new ArrayList<>();
      for (Map.Entry<Coordinate, Panel> entry : grid.entrySet()) {
        if (entry.getValue().isActive()) {
          activePanels.add(entry.getValue());
        }
      }

      // Clear the grid
      grid.clear();

      // Calculate new dimensions for the rectangle
      int newSize = activePanels.size();
      int newWidth = (int) Math.ceil(Math.sqrt(newSize));
      int newHeight = (int) Math.ceil((double) newSize / newWidth);

      // Rebuild the grid
      int counter = 0;
      for (int row = 1; row <= newHeight; row++) {
        for (int col = 1; col <= newWidth; col++) {
          if (counter < activePanels.size()) {
            Coordinate coord = new Coordinate(row, col);
            grid.put(coord, activePanels.get(counter));
            counter++;
          }
        }
      }
    }
    sendUpdate();


  }

  @Override
  public boolean isGameOver() {
    if(phase == Phase.ENDGAME ){
      phase= Phase.ENDGAME;
      return true;
    }
    if(cards.isEmpty()){
      phase= Phase.ENDGAME;
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
  public void setPhase(Phase phase) {
    this.phase=phase;
  }

  @Override
  public String endPhase() {
    if(phase == Phase.ENDGAME){
      if(cards.isEmpty()){
        return "Game Won";
      }
      else{
        return "Game Lost";
      }
    }
    return "Game Not Over";
  }

  @Override
  public List<Panel> getPanels() {
    List<Panel> panels = new ArrayList<Panel>();
    panels.addAll(grid.values());
    return panels;
  }
}
