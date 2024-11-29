package model;

import java.util.List;
import java.util.Map;

import view.Panel;

public interface Board {
  Map<Coordinate, Panel> createBoard(List<Panel> panels);

}
