package view.Panels;

import java.awt.*;

public interface Panel {
  void sendController();
  Color getPebble();
  void setPebble(Color color);
  Color getColor();
  void setColor(Color color);
  boolean isActive();
  void setActive(boolean active);
}
