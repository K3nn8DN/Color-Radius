package view;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import controller.Controller;
import model.Model;
import model.boards.OneHalfBoard;
import model.boards.OneToOneBoard;
import model.boards.ThreeToFiveBoard;
import model.cards.Card;
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
  int activeHand=0;

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
    this.getContentPane().removeAll();
    int width=this.getWidth();
    this.setLayout(new GridLayout(0, 1));

    JPanel boardPanel = new JPanel();
    boardPanel.setLayout(new FlowLayout());
    JPanel boardOneHalf = new BoardPanel(new OneHalfBoard(), this);
    JPanel boardOneOne = new BoardPanel(new OneToOneBoard(), this);
    JPanel boardThreeFive = new BoardPanel(new ThreeToFiveBoard(), this);
    boardOneHalf.setPreferredSize(new Dimension(width/4, width/4));
    boardOneOne.setPreferredSize(new Dimension(width/4, width/4));
    boardThreeFive.setPreferredSize(new Dimension(width/4, width/4));
    boardThreeFive.setBackground(Color.BLACK);
    boardOneOne.setBackground(Color.BLACK);
    boardOneHalf.setBackground(Color.BLACK);
    boardPanel.add(boardOneHalf);
    boardPanel.add(boardOneOne);
    boardPanel.add(boardThreeFive);

    JPanel buttonPanel = new JPanel();
    buttonPanel.setLayout(new FlowLayout());
    JButton button1 = new JButton("1");
    JButton button2 = new JButton("3");
    JButton button3 = new JButton("5");
    buttonPanel.add(button1);
    buttonPanel.add(button2);
    buttonPanel.add(button3);

    JPanel startGamePanel = new JPanel();
    JButton startGameButton = new JButton("Start Game");
    startGamePanel.add(startGameButton);

    this.add(boardPanel, BorderLayout.NORTH);
    this.add(buttonPanel, BorderLayout.CENTER);
    this.add(startGamePanel, BorderLayout.SOUTH);


    button1.addActionListener(e -> activeHand = 1);
    button2.addActionListener(e -> activeHand = 3);
    button3.addActionListener(e -> activeHand = 5);
    startGameButton.addActionListener(e -> controller.startGame(makePannels(),
            activeBoard.getBoard(),activeHand));

    this.setVisible(true);
    this.revalidate();
    this.repaint();

  }

  @Override
  public void renderPlacePebble() {
    renderPlay();
    int width=this.getWidth();

    JPanel pebblePanel = new JPanel();
    pebblePanel.setLayout(new FlowLayout());
    pebblePanel.setBackground(Color.black);
    pebblePanel.setPreferredSize(new Dimension(width/3, width/3));
    for (int i = 0; i < model.getPebbles().size(); i++) {
      Color color = model.getPebbles().get(i);
      int num = i;
      Button button = new Button();
      button.setBackground(color);
      button.setPreferredSize(new Dimension(width / 20, width / 20));
      button.addActionListener(e -> controller.updateClick(num))  ;
      pebblePanel.add(button);

    }
    this.add(pebblePanel);

    render();
  }

  @Override
  public void renderPlayCard() {
    renderPlay();
    int width=this.getWidth();


    JPanel cardPanel = new JPanel();
    cardPanel.setLayout(new FlowLayout());
    cardPanel.setBackground(Color.black);
    cardPanel.setPreferredSize(new Dimension(width/3, width/3));
    Collections.shuffle(model.getCards());
    for (int i = 0; i < activeHand; i++) {
      int num = i;
      JPanel card = new CardPanel(model.getCards().get(i),this,controller,i);
      card.setBackground(Color.blue);
      card.setPreferredSize(new Dimension(width / 4, width / 4));
      card.setBorder(BorderFactory.createBevelBorder(2));
      cardPanel.add(card);
    }
    this.add(cardPanel);

    render();
  }

  public void renderRemovePhase(){
    renderPlay();
    int width=this.getWidth();

    JPanel colorPanel = new JPanel();
    colorPanel.setLayout(new FlowLayout());
    colorPanel.setBackground(Color.black);
    colorPanel.setPreferredSize(new Dimension(width/3, width/3));
    Collections.shuffle(model.getCards());
    for (int i = 0; i < model.getRemoveColors().size(); i++) {
      JPanel removeColor = new JPanel();
      removeColor.setBackground(model.getRemoveColors().get(i));
      removeColor.setPreferredSize(new Dimension(width / 6, width / 6));
      removeColor.setBorder(BorderFactory.createBevelBorder(2));
      colorPanel.add(removeColor);
    }
    this.add(colorPanel);

    render();
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

  private void renderPlay(){
    this.getContentPane().removeAll();
    int width=this.getWidth();
    this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));

    JPanel gridPanel = new JPanel();
    gridPanel.setLayout(new GridLayout((int)activeBoard.getBoard().getheight(),
            (int)activeBoard.getBoard().getwidth()));
    for (Panel panel : model.getPanels()) {
      ((GridPanel)panel).setPreferredSize(new Dimension(width / 4, width / 4)); // Optional
      ((GridPanel)panel).setBorder(BorderFactory.createLineBorder(Color.black));
      ((GridPanel)panel).setBackground(panel.getColor());
      gridPanel.add(((GridPanel)panel));
    }
    this.add(gridPanel);


  }

  private void render(){
    this.setVisible(true);
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
