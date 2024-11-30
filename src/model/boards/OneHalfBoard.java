package model.boards;


public class OneHalfBoard extends Board {
  @Override
  protected float getwidth() {
    return 6;
  }

  @Override
  protected float getheight() {
    return 4;
  }

  @Override
  protected float getStop() {
    return 2;
  }

}
