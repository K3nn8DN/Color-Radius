package model;

import java.util.List;

import view.Panel;

public interface ReadOnlyModel {
  boolean isGameOver();
  Phase getPhase();
  List<Panel> getPanels();
}
