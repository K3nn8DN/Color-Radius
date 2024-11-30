package model.boards;

public class OneToOneBoard extends Board {
  @Override
  protected float getwidth() {
    return 5;
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
