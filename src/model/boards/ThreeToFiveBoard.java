package model.boards;

public class ThreeToFiveBoard extends Board {
  @Override
  protected float getwidth() {
    return 6;
  }

  @Override
  protected float getheight() {
    return 5;
  }

  @Override
  protected float getStop() {
    return 3;
  }
}
