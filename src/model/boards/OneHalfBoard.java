package model.boards;


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

}
