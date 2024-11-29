package model;

import java.util.List;

import view.Panels.Panel;

public interface ReadOnlyModel {
  boolean isGameOver();
  Phase getPhase();
  List<Panel> getPanels();
}
