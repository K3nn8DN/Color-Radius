package model.boards;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import model.Coordinate;
import view.Panels.Panel;

public abstract class Board {
  private Map<Coordinate, Panel> grid= new LinkedHashMap<Coordinate, Panel>();
  private List<Color> colors = new ArrayList<Color>();
  private List<Color> pebbles;
  public abstract float getwidth();
  public abstract float getheight();
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
    for (int row = 1; row <= getheight(); row++) {
      for (int col = 1; col <= getwidth(); col++) {
        Panel panel = panels.get(counter);
        Coordinate coord = new Coordinate(row, col); // Row-first assignment
        grid.put(coord, panel);
        panel.setColor(colors.get(counter));
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

  public abstract Image getImage();

}
