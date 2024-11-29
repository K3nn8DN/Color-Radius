package model.boards;

import java.util.List;
import java.util.Map;

import model.Coordinate;
import view.Panels.Panel;

public interface Board {
  Map<Coordinate, Panel> createBoard(List<Panel> panels);

}
