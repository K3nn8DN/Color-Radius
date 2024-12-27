package model.boards;


import java.awt.*;

import javax.swing.*;

public class OneHalfBoard extends Board {
  @Override
  public float getwidth() {
    return 6;
  }

  @Override
  public float getheight() {
    return 4;
  }

  @Override
  protected float getStop() {
    return 2;
  }

  @Override
  public Image getImage() {
    Image image = new ImageIcon(getClass().getResource("/onehalf.png")).getImage();
    return image;
  }

}
