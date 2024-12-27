package view.Panels;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;

import view.GameView;

public class MessagePanel  extends JPanel{

public MessagePanel() {
  // Set layout to BorderLayout for full panel filling
  this.setLayout(new BorderLayout());

  // Create JLabel with HTML for wrapping text
  JLabel label = new JLabel("<html>The peaceful pebbles are under attack by the cards. The cards, " +
          "confident in their damage and area of effect, think it doesn’t matter which pebble they aim for; " +
          "they will have victory. So they let you (the pebble king) pick the pebble to hit. " +
          "<br><br>" +
          "Their bombs can only hit around the target, so you can pick the pebble with the least amount of other pebbles around it to attack." +
          "<br><br>" +
          "They also have a land removal machine. In exchange for picking which pebbles to attack, you also have to remove a piece of land connected to the pebble if one wasn't already removed." +
          "<br><br>" +
          "<b>To Play:</b><br>" +
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
  this.add(label, BorderLayout.CENTER);
}


}

