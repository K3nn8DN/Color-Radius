# Color Radius

A strategic grid-based card game built with Java Swing. Place your pebbles, play cards, and survive until the deck is depleted!

---
## How to Play

Objective:

    Survive the game by strategically placing and moving pebbles while playing cards that remove 
other pebbles from the grid.
    Win by successfully playing all 12 cards while keeping at least one pebble on the board.
    Lose if all your pebbles are removed before the deck runs out.

### Gameplay Overview

    Setup:
        Choose the hand size (number of cards) and board layout.
        Place all your pebbles on the grid.

    Playing:
        Pebble Placement Phase: Place all pebbles strategically on the grid.
        Card Phase: Play a card to apply its effect to the last moved pebble. Cards may remove 
pebbles in a radius, adjacency, or corners —there are also cards that offer helpful effects.
        Pebble Movement Phase: Move one pebble to an unoccupied panel.
        Repeat until:
            You’ve played all 12 cards and win.
            All pebbles are removed, and you lose.


---
### Installation and Running the Game

    Download:
        download the playable folder

    Run the Game:
        Double-click the .bat file to start the game.
        Note: This method may only works on Windows and with Java installed.

    System Requirements:
        Java Development Kit (JDK) or Runtime Environment (JRE).
        Compatible with Windows systems. Other OS users may need to manually run the .jar file.

---

### Repository Structure

    src/: Source code for the game.
    playable/: Folder containing the game’s executable .jar file and .bat script.

---

### Code Overview

This game uses a Model-View-Controller (MVC) structure:

    Model: Handles game logic, grid state, and rules.
    View: Renders the grid, cards, and pebbles using Java Swing.
    Controller: Manages user input, updates the model, and refreshes the view.



