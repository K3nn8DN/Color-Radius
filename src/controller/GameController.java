package controller;

import java.util.List;

import model.boards.Board;
import model.cards.Card;
import model.Model;
import view.Panels.Panel;
import view.View;

public class GameController implements Controller {
  Model model;
  View view;
  Object currentClick;

  GameController(Model model, View view) {
    this.model = model;
    this.view = view;
    this.model.setController(this);
    this.view.setController(this);
    this.view.setModel(model);
    view.renderStart();
  }


  @Override
  public void startGame(List<Panel> panels, Board board) {
    this.model.setUp(panels, board);
  }

  @Override
  public void updateGrid(Panel panel) {
    if (panel != null && panel.isActive()) {
      if (currentClick == null) {
        if (panel.getPebble() == null) {
          currentClick = panel;
        }
        else {
          throw new IllegalArgumentException("cannot play to a spot with a pebble");
        }
      }
      else {
        switch (model.getPhase()) {
          case PLACEPEBBLE:
            model.placePebble((int) currentClick, panel);
            currentClick = null;
            break;
          case PLAYPEBBLE:
            if (currentClick.equals(panel)) {
              currentClick = null;
            }
            else {
              model.movePebble((Panel) currentClick, panel);
              currentClick = null;
            }
            break;
          case PLAYCARD:
            model.playCard((Card) currentClick, panel);
            currentClick = null;
            break;
          default:
            throw new IllegalStateException("game has to be playing");
        }

      }

    }
    else {
      throw new IllegalArgumentException("must play to an active panel");
    }
  }


  @Override
  public void updateClick(Object click) {
    if (this.currentClick == click) {
      this.currentClick = null;
    }
    else {
      this.currentClick = click;
    }

  }

  @Override
  public void update() {

    switch (model.getPhase()) {
      case PLACEPEBBLE:
        view.renderPlacePebble();
        break;
      case PLAYPEBBLE:
        view.renderPlayPebble();

        break;
      case PLAYCARD:
        view.renderPlayCard();

        break;
      case ENDGAME:
        view.renderEnd();
        break;
    }
  }

  @Override
  public void handleErrorMessage(String error) {

  }
}
