package view;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import controller.Controller;
import model.Model;
import model.Phase;
import model.boards.OneHalfBoard;
import model.boards.OneToOneBoard;
import model.boards.ThreeToFiveBoard;
import model.cards.Card;
import view.Panels.BoardPanel;
import view.Panels.CardPanel;
import view.Panels.GridPanel;
import view.Panels.MessagePanel;
import view.Panels.Panel;

import javax.swing.*;

public class GameView extends JFrame implements View {
  Model model;
  Controller controller;
  BoardPanel activeBoard;
  CardPanel activeCard;
  int activeHand=0;
  GridPanel activePebble =null;
  Image wood = new ImageIcon(getClass().getResource("/WoodBackground.png")).getImage();
  ImageIcon start = new ImageIcon(getClass().getResource("/Start.png"));


  public GameView(){
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    this.setSize(450, 450);

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
      panList.add(new GridPanel(this));
    }
     return panList;
  }

  @Override
  public void renderStart() {
    // Clear the existing content
    this.getContentPane().removeAll();

    // Load background image
    JLabel backgroundLabel = new JLabel(start);
    backgroundLabel.setLayout(new GridBagLayout()); // Allows adding components on top
    this.setContentPane(backgroundLabel);

    int width = this.getWidth();

    // Create a main panel with a BoxLayout for vertical alignment
    JPanel mainPanel = new JPanel();
    mainPanel.setOpaque(false); // Ensure background image is visible
    mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

    // Board Selection Section
    JLabel boardLabel = new JLabel("Pick a Board", JLabel.CENTER);
    boardLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

    JPanel boardPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
    boardPanel.setOpaque(false);

    JPanel boardOneHalf = new BoardPanel(new OneHalfBoard(), this);
    JPanel boardOneOne = new BoardPanel(new OneToOneBoard(), this);
    JPanel boardThreeFive = new BoardPanel(new ThreeToFiveBoard(), this);

    Dimension boardSize = new Dimension(width / 4, width / 4);
    boardOneHalf.setPreferredSize(boardSize);
    boardOneOne.setPreferredSize(boardSize);
    boardThreeFive.setPreferredSize(boardSize);

    boardOneHalf.setBackground(Color.BLACK);
    boardOneOne.setBackground(Color.BLACK);
    boardThreeFive.setBackground(Color.BLACK);

    boardPanel.add(boardOneHalf);
    boardPanel.add(boardOneOne);
    boardPanel.add(boardThreeFive);

    // Hand Size Section
    JLabel buttonLabel = new JLabel("Pick hand size", JLabel.CENTER);
    buttonLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

    JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
    buttonPanel.setOpaque(false);

    JButton button1 = new JButton("1");
    JButton button2 = new JButton("3");
    JButton button3 = new JButton("5");

    buttonPanel.add(button1);
    buttonPanel.add(button2);
    buttonPanel.add(button3);

    // Start Game Section
    JPanel startGamePanel = new JPanel();
    startGamePanel.setOpaque(false);

    JButton startGameButton = new JButton("Start Game");
    startGamePanel.add(startGameButton);

    // Add components to the main panel
    mainPanel.add(Box.createVerticalStrut(20)); // Spacer
    mainPanel.add(boardLabel);
    mainPanel.add(boardPanel);
    mainPanel.add(Box.createVerticalStrut(20));
    mainPanel.add(buttonLabel);
    mainPanel.add(buttonPanel);
    mainPanel.add(Box.createVerticalStrut(20));
    mainPanel.add(startGamePanel);

    // Add main panel to the frame
    this.add(mainPanel);

    // Add action listeners
    button1.addActionListener(e -> activeHand = 1);
    button2.addActionListener(e -> activeHand = 3);
    button3.addActionListener(e -> activeHand = 5);
    startGameButton.addActionListener(e -> renderMassage());

    // Finalize the frame
    this.setVisible(true);
    this.revalidate();
    this.repaint();
  }


  public void renderMassage(){
    this.getContentPane().removeAll();

    JPanel panel = new JPanel();
    panel.setLayout(new BorderLayout());
    JLabel label = new JLabel("<html>The peaceful pebbles are under attack by the cards. The cards, " +
            "confident in their damage and area of effect, think it doesn’t matter which pebble they aim for; " +
            "they will have victory. So they let you (the pebble king) pick the pebble to hit. " +
            "<br><br>" +
            "Their bombs can only hit around the target, so you can pick the pebble with the least amount of other pebbles around it to attack." +
            "<br><br>" +
            "They also have a land removal machine. In exchange for picking which pebbles to attack, you also have to remove a piece of land connected to the pebble if one wasn't already removed." +
            "<br><br> </html>");

    // Set alignment and font
    label.setHorizontalAlignment(SwingConstants.CENTER);
    label.setVerticalAlignment(SwingConstants.TOP);
    label.setFont(new Font("Arial", Font.PLAIN, 14));

    // Add label to the panel
    panel.add(label, BorderLayout.CENTER);
    panel.setPreferredSize(new Dimension(400, 300));

    // Add MouseListener to the JPanel
    panel.addMouseListener(new MouseListener() {
      @Override
      public void mouseClicked(MouseEvent e) {
        renderMassage2();
      }

      @Override
      public void mousePressed(MouseEvent e) {
      }

      @Override
      public void mouseReleased(MouseEvent e) {

      }

      @Override
      public void mouseEntered(MouseEvent e) {

      }

      @Override
      public void mouseExited(MouseEvent e) {

      }
    });

    // Add panel to the frame
    this.add(panel);
    this.pack();
    this.setLocationRelativeTo(null);
    this.setVisible(true);
  }

  public void renderMassage2(){
    this.getContentPane().removeAll();

    JPanel panel = new JPanel();
    panel.setLayout(new BorderLayout());
    JLabel label = new JLabel("<html> <b>To Play:</b><br>" +
            "1. Place all of your pebble people on the land spots that match their color, or in a white space (free for all).<br>" +
            "2. Pick a pebble to attack around and a card to attack with. It will remove the pieces around it.<br>" +
            "3. If the right conditions are met, you may be prompted to remove land of specific colors. " +
            "After removing land (or if none needs to be removed), the board may be reorganized to have no gaps. The cards allow no cheating by playing to a pebble without land next to it.<br>" +
            "4. After the board is free of holes, you can move one pebble to a place of its color or a white space to further strategize and avoid losing pebbles.<br>" +
            "5. If the cards run out of attacks and there are still pebbles on the field, you win. If there are fewer than 3 pebbles, you lose. Good luck!" +
            "</html>");

    // Set alignment and font
    label.setHorizontalAlignment(SwingConstants.CENTER);
    label.setVerticalAlignment(SwingConstants.TOP);
    label.setFont(new Font("Arial", Font.PLAIN, 14));

    // Add label to the panel
    panel.add(label, BorderLayout.CENTER);
    panel.setPreferredSize(new Dimension(400, 300));

    // Add MouseListener to the JPanel
    panel.addMouseListener(new MouseListener() {
      @Override
      public void mouseClicked(MouseEvent e) {
        controller.startGame(makePannels(),activeBoard.getBoard(), activeHand);
      }

      @Override
      public void mousePressed(MouseEvent e) {
      }

      @Override
      public void mouseReleased(MouseEvent e) {

      }

      @Override
      public void mouseEntered(MouseEvent e) {

      }

      @Override
      public void mouseExited(MouseEvent e) {

      }
    });

    // Add panel to the frame
    this.add(panel);
    this.pack();
    this.setLocationRelativeTo(null);
    this.setVisible(true);
  }


  @Override
  public void renderPlacePebble() {
    renderPlay();
    int width=this.getWidth();

    JPanel pebblePanel = new JPanel(){
      @Override
      protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Draw the background image, scaling it to fit the panel
        g.drawImage(wood, 0, 0, getWidth(), getHeight(), this);
      }
    };
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


    JPanel cardPanel = new JPanel()    {
      @Override
      protected void paintComponent(Graphics g) {
      super.paintComponent(g);
      // Draw the background image, scaling it to fit the panel
      g.drawImage(wood, 0, 0, getWidth(), getHeight(), this);
    }};

    cardPanel.setLayout(new FlowLayout());
    //cardPanel.setBackground(Color.black);

    cardPanel.setPreferredSize(new Dimension(width/3, width/3));
    for (int i = 0; i < activeHand; i++) {
      int num = i;
      if(i < model.getCards().size()) {
        JPanel card = new CardPanel(model.getCards().get(i), this, controller, i);
        card.setBackground(Color.blue);
        card.setPreferredSize(new Dimension(width / 5, width / 4));
        card.setBorder(BorderFactory.createBevelBorder(2));
        cardPanel.add(card);
      }
    }
    this.add(cardPanel);

    render();
  }

  public void renderRemovePhase(){
    renderPlay();
    int width=this.getWidth();

    JPanel colorPanel = new JPanel(){
      @Override
      protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Draw the background image, scaling it to fit the panel
        g.drawImage(wood, 0, 0, getWidth(), getHeight(), this);
      }
    };
    colorPanel.setLayout(new FlowLayout());
    colorPanel.setBackground(Color.black);
    colorPanel.setPreferredSize(new Dimension(width/3, width/3));

    for (Color color : model.getRemoveColors()) {
      JPanel removeColor = new JPanel();
      removeColor.setBackground(color);
      removeColor.setPreferredSize(new Dimension(width / 6, width / 6));
      removeColor.setBorder(BorderFactory.createBevelBorder(2));
      colorPanel.add(removeColor);
    }

    this.add(colorPanel);

    render();
  }


  @Override
  public void renderPlayPebble() {
    renderPlay();
    int width=this.getWidth();

    JPanel colorPanel = new JPanel(){
      @Override
      protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Draw the background image, scaling it to fit the panel
        g.drawImage(wood, 0, 0, getWidth(), getHeight(), this);
      }
    };
    colorPanel.setLayout(new FlowLayout());
    colorPanel.setPreferredSize(new Dimension(width/4, width/4));
    colorPanel.add(new JLabel("Move a Pebble") );
    colorPanel.add(new JLabel("out of moves?") );
    JButton forfit =new JButton("Forfit");
    forfit.setPreferredSize(new Dimension(100, 25));
    forfit.addActionListener(e -> model.setPhase(Phase.ENDGAME) );
    forfit.addActionListener(e ->controller.update());
    colorPanel.add(forfit);

    this.add(colorPanel);



    render();
  }

  @Override
  public void renderEnd() {
    getContentPane().removeAll();
    getContentPane().setLayout(new FlowLayout());

    getContentPane().setBackground(Color.black);
      JLabel label;
      label = new JLabel(""+model.endPhase(), SwingConstants.CENTER);
      label.setFont(new Font("Arial", Font.BOLD, 40));
      label.setBackground(Color.BLUE);
      getContentPane().add(label, BorderLayout.CENTER);

      revalidate();
      repaint();

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

  public void setactivePebble(GridPanel pebble){
    if(model.getPhase()== Phase.PLAYPEBBLE) {
      this.activePebble = pebble;
    }
  }
  public GridPanel getactivePebble(){
    return activePebble;
  }
  public Controller getController(){
    return controller;
  }




}
