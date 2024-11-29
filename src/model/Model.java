package model;

public interface Model extends ReadOnlyModel{
   void addListener();
   void sendUpdate();
   void setUp();


}
