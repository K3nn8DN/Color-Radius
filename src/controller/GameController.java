package controller;

import java.util.List;

import model.Phase;
import model.boards.Board;
import model.cards.Card;
import model.Model;
import view.Panels.Panel;
import view.View;

public class GameController implements Controller {
  private final Model model;
  private final View view;
  private Object currentClick;

  public GameController(Model model, View view) {
    this.model = model;
    this.view = view;
    this.model.setController(this);
    this.view.setController(this);
    this.view.setModel(model);
    view.renderStart();
  }


  @Override
  public void startGame(List<Panel> panels, Board board, int handSize) {
    if (handSize != 0) {
      this.model.setUp(panels, board);
      currentClick = null;
    }
    else {
      throw new IllegalArgumentException("Hand size must be greater than 0");
    }

  }

  @Override
  public void updateGrid(Panel panel) {
    if (panel == null || !(panel.isActive())) {
      throw new IllegalArgumentException("must be an active panel");
    }
    if (currentClick == null) {
      handeInitialClick(panel);
    }
    else {
      handleSecondClick(panel);
    }

  }

  private void handeInitialClick(Panel panel) {
    switch (model.getPhase()) {
      case PLAYPEBBLE:
        currentClick = panel;
        panel.setClicked(true);
        break;
      case PLACEPEBBLE:
        throw new IllegalArgumentException("click a pebble first");
      case REMOVE:
        model.removePannel(panel);
        break;
      case PLAYCARD:
        throw new IllegalArgumentException("click a card first");
      default:
        throw new IllegalArgumentException("cannot play to a spot with a pebble");
    }
  }

  private void handleSecondClick(Panel panel) {
    switch (model.getPhase()) {
      case PLACEPEBBLE:
        model.placePebble((int) currentClick, panel);
        currentClick = null;
        break;
      case PLAYPEBBLE:
        if (panel.getPebble() != null) {
          ((Panel) currentClick).setClicked(false);
          currentClick = panel;
          panel.setClicked(true);
        }
        else {
          model.movePebble((Panel) currentClick, panel);
          currentClick = null;
        }
        break;
      case PLAYCARD:
        if(panel.getPebble() != null) {
          model.playCard((Card) currentClick, panel);
          currentClick = null;
        }
        else{
          throw new IllegalArgumentException("must play a card to a place with a pebble");
        }
        break;
      default:
        throw new IllegalStateException("game has to be playing");
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
    update();

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
      case REMOVE:
        view.renderRemovePhase();
        break;
      case ENDGAME:
        view.renderEnd();
        break;
    }
  }

  @Override
  public void handleErrorMessage(String error) {
    view.handleErrorMessage(error);
  }
}
