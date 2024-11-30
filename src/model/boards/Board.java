package model.boards;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.Coordinate;
import view.Panels.Panel;

public abstract class Board {
  private Map<Coordinate, Panel> grid= new HashMap<Coordinate, Panel>();
  private List<Color> colors = new ArrayList<Color>();
  private List<Color> pebbles;
  protected abstract float getwidth();
  protected abstract float getheight();
  protected abstract float getStop();

  Board(){
    addColors();
  }
  void addColors(){
    int floor = (int) Math.floor((getwidth() * getheight()) / 6);
    for (int i = 0; i < floor; i++) {
      colors.add(Color.BLUE);
      colors.add(Color.YELLOW);
      colors.add(Color.GREEN);
      colors.add(Color.RED);
      colors.add(Color.PINK);
      colors.add(Color.MAGENTA);
      if(i == getStop()){
        pebbles = new ArrayList<Color>(colors);
      }
    }
    if((getwidth() * getheight()) % 6 != 0){
      colors.add(Color.white);
      colors.add(Color.white);
    }
  }
  public Map<Coordinate, Panel> createBoard(List<Panel> panels) {
    Collections.shuffle(colors);
    int counter = 0;
    for (int i = 1; i < getwidth()+1; i++) {
      for (int j = 1; j < getheight()+1; j++) {
        panels.get(counter).setColor(colors.get(counter));
        grid.put(new Coordinate(i,j),panels.get(counter));
        counter++;
      }
    }
    return grid;
  }

  public List<Color> getPebbles(){
    return pebbles;
  }

  public int getSize(){
    return (int)(getheight()*getwidth());
  }

}
