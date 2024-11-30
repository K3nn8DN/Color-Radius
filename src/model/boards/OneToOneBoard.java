package model.boards;

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

}
