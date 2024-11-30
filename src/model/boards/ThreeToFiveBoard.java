package model.boards;

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
}
