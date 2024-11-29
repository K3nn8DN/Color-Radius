package model;

import java.util.Objects;

public class Coordinate {
  private final int x;
  private final int y;

  public Coordinate(int x, int y) {
    this.x = x;
    this.y = y;
  }

  public int getX() {
    return x;
  }
  public int getY() {
    return y;
  }

  // Override equals to compare Coordinate objects based on x and y values
  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null || getClass() != obj.getClass()) return false;
    Coordinate that = (Coordinate) obj;
    return x == that.x && y == that.y;
  }

  // Override hashCode to ensure consistent hashing based on x and y
  @Override
  public int hashCode() {
    return Objects.hash(x, y);
  }

  //Overrides the toString so that it can be correctly displayed
  @Override
  public String toString() {
    return "(" + x + ", " + y + ")";
  }

}
