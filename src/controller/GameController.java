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
    if(handSize != 0) {
      this.model.setUp(panels, board);
      currentClick = null;
    }
    else{
      throw new IllegalArgumentException("Hand size must be greater than 0");
    }

  }

  @Override
  public void updateGrid(Panel panel) {
    if (panel != null && panel.isActive()) {
      if (currentClick == null) {

        if (panel.getPebble() == null && model.getPhase() != Phase.PLACEPEBBLE) {
          currentClick = panel;
        }
        else if (model.getPhase() == Phase.PLACEPEBBLE) {
          throw new IllegalArgumentException("click a pebble first");
        }
        else if(model.getPhase() == Phase.REMOVE){
          model.removePannel(panel);
        }
        else if (model.getPhase() == Phase.PLAYCARD){
          throw new IllegalArgumentException("click a card first");
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
            model.playCard((Card)currentClick, panel);
            currentClick = null;
            break;
          default:
            throw new IllegalStateException("game has to be playing");
        }

      }

    }
    else {
      throw new IllegalArgumentException("must be an active panel");
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

  }
}
