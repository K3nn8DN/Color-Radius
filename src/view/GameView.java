package view;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import controller.Controller;
import model.Model;
import model.boards.OneHalfBoard;
import model.boards.OneToOneBoard;
import model.boards.ThreeToFiveBoard;
import view.Panels.BoardPanel;
import view.Panels.CardPanel;
import view.Panels.GridPanel;
import view.Panels.Panel;

import javax.swing.*;

public class GameView extends JFrame implements View {
  Model model;
  Controller controller;
  BoardPanel activeBoard;
  CardPanel activeCard;
  int activeHand;

  public GameView(){
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setSize(400, 300);


  }

  @Override
  public void setModel(Model model) {
    this.model = model;
  }

  @Override
  public void setController(Controller controller) {
    this.controller = controller;
  }

  private List<Panel> makePannels(){
    List<Panel> panList = new ArrayList<Panel>();
    for(int i=0;i<activeBoard.getBoard().getSize() ;i++){
      panList.add(new GridPanel(controller));
    }
     return panList;
  }

  @Override
  public void renderStart() {
    // Create a panel for the cards
    this.getContentPane().removeAll();
    int width=this.getWidth();
    this.setLayout(new GridLayout(0, 1));
    JPanel cardPanel = new JPanel();
    cardPanel.setLayout(new FlowLayout());
    JPanel boardOneHalf = new BoardPanel(new OneHalfBoard(), this);
    JPanel boardOneOne = new BoardPanel(new OneToOneBoard(), this);
    JPanel boardThreeFive = new BoardPanel(new ThreeToFiveBoard(), this);
    boardOneHalf.setPreferredSize(new Dimension(width/4, width/4));
    boardOneOne.setPreferredSize(new Dimension(width/4, width/4));
    boardThreeFive.setPreferredSize(new Dimension(width/4, width/4));
    boardThreeFive.setBackground(Color.BLACK);
    boardOneOne.setBackground(Color.BLACK);
    boardOneHalf.setBackground(Color.BLACK);
    cardPanel.add(boardOneHalf);
    cardPanel.add(boardOneOne);
    cardPanel.add(boardThreeFive);

    // Create a panel for the buttons
    JPanel buttonPanel = new JPanel();
    buttonPanel.setLayout(new FlowLayout());
    JButton button1 = new JButton("Button 1");
    JButton button2 = new JButton("Button 2");
    JButton button3 = new JButton("Button 3");
    buttonPanel.add(button1);
    buttonPanel.add(button2);
    buttonPanel.add(button3);

    // Create a panel for the start game button
    JPanel startGamePanel = new JPanel();
    JButton startGameButton = new JButton("Start Game");
    startGamePanel.add(startGameButton);

    // Add the panels to the frame
    this.add(cardPanel, BorderLayout.NORTH);
    this.add(buttonPanel, BorderLayout.CENTER);
    this.add(startGamePanel, BorderLayout.SOUTH);


    button1.addActionListener(e -> activeHand = 1);
    button2.addActionListener(e -> activeHand = 3);
    button3.addActionListener(e -> activeHand = 5);
    startGameButton.addActionListener(e -> controller.startGame(makePannels(),
            activeBoard.getBoard()));

    // Make the frame visible
    this.setVisible(true);

    this.revalidate();
    this.repaint();

  }

  @Override
  public void renderPlacePebble() {
    this.getContentPane().removeAll();
    int width=this.getWidth();
    this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));


    JPanel gridPanel = new JPanel();
    gridPanel.setLayout(new GridLayout(5,5));
    for (int i = 0; i < activeBoard.getBoard().getSize(); i++) {
      JPanel panel2 = (GridPanel) model.getPanels().get(i);
      panel2.setPreferredSize(new Dimension(width / 4, width / 4));
      panel2.setBackground(model.getPanels().get(i).getColor());
      panel2.setBorder(BorderFactory.createLineBorder(Color.black));
      gridPanel.add(panel2);
    }
    this.add(gridPanel);

    JPanel cardPanel = new JPanel();
    cardPanel.setLayout(new FlowLayout());
    cardPanel.setBackground(Color.black);
    cardPanel.setPreferredSize(new Dimension(width/3, width/3));
    for (int i = 0; i < model.getPebbles().size(); i++) {
      Color color = model.getPebbles().get(i);
      int num = i;
      Button button = new Button();
      button.setBackground(color);
      button.setPreferredSize(new Dimension(width / 20, width / 20));
      button.addActionListener(e -> controller.updateClick(num))  ;
      cardPanel.add(button);

    }

    this.add(cardPanel);



    // Make the frame visible
    this.setVisible(true);
    this.revalidate();
    this.repaint();
  }

  @Override
  public void renderPlayCard() {
    this.getContentPane().removeAll();
    this.setBackground(Color.BLACK);
    int width=this.getWidth();
    this.setLayout(new GridLayout(0, 1));
    JPanel cardPanel = new JPanel();
    cardPanel.setLayout(new FlowLayout());

//
//    this.add(cardPanel, BorderLayout.NORTH);
//    this.add(buttonPanel, BorderLayout.CENTER);
//    this.add(startGamePanel, BorderLayout.SOUTH);
//





    this.revalidate();
    this.repaint();




  }

  @Override
  public void renderPlayPebble() {
    this.getContentPane().removeAll();
    this.setBackground(Color.BLACK);
    JPanel panle = new JPanel();
    panle.setBackground(Color.BLACK);
    this.add(panle, BorderLayout.CENTER);
    this.setBackground(Color.RED);

    this.revalidate();
    this.repaint();
  }

  @Override
  public void renderEnd() {
    this.getContentPane().removeAll();
    this.setBackground(Color.BLACK);

    this.revalidate();
    this.repaint();
  }

  @Override
  public void handleErrorMessage(String error) {
    JOptionPane.showMessageDialog(null, error,
            "Information", JOptionPane.INFORMATION_MESSAGE);
  }

  public void setActiveBoard(BoardPanel board){
    this.activeBoard = board;
  }
  public BoardPanel getActiveBoard(){
    return activeBoard;
  }
  public void setActiveCard(CardPanel card){
    this.activeCard = card;
  }
  public CardPanel getActiveCard(){
    return activeCard;
  }



}
