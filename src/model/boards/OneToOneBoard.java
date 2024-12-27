package model.boards;

import java.awt.*;

import javax.swing.*;

public class OneToOneBoard extends Board {
  @Override
  public float getwidth() {
    return 5;
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
    Image image = new ImageIcon(getClass().getResource("/oneone.png")).getImage();
    return image;
  }

}
