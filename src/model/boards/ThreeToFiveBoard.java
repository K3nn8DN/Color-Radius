package model.boards;

import java.awt.*;

import javax.swing.*;

public class ThreeToFiveBoard extends Board {
  @Override
  public float getwidth() {
    return 6;
  }

  @Override
  public float getheight() {
    return 5;
  }

  @Override
  protected float getStop() {
    return 3;
  }

  @Override
  public Image getImage() {
    Image image = new ImageIcon(getClass().getResource("/ThreeFive.png")).getImage();
    return image;
  }
}
