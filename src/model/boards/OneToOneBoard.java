package model.boards;

import java.util.List;
import java.util.Map;

import model.Coordinate;
import view.Panels.Panel;

public class OneToOneBoard implements Board {
  @Override
  public Map<Coordinate, Panel> createBoard(List<Panel> panels) {
    return Map.of();
  }
}
