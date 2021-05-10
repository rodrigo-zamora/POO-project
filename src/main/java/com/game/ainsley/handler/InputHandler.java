package com.game.ainsley.handler;

import com.game.ainsley.data.Load;
import com.game.ainsley.data.Save;
import com.game.ainsley.main.Game;
import com.game.ainsley.screen.Screen;

import java.awt.*;
import java.io.IOException;

public class InputHandler {

    private final Rectangle gameFromMenu = new Rectangle(155, 285, 230, 70);
    private final Rectangle creditsFromMenu = new Rectangle(695, 285, 230, 70);
    private final Rectangle mainMenuFromDeath = new Rectangle(425, 512, 230, 70);
    private final Rectangle gameFromDeath = new Rectangle(425, 384, 230, 70);
    private final Rectangle mainMenuCredits = new Rectangle(425, 512, 230, 70);
    private final Rectangle saveFromPause = new Rectangle(405, 250, 230, 70);
    private final Rectangle loadFromPause = new Rectangle(405, 355, 270, 70);
    private final Rectangle mainMenuFromPause = new Rectangle(405, 460, 270, 70);
    private Rectangle rectangle = new Rectangle(0, 0, 1, 1);

    /**
     * Method to check collision between the mouse and the buttons
     *
     * @throws IOException                   if an I/O exception occurs
     */
    public void checkCollision() throws IOException {

        switch (Game.getScreen()) {
            case MAIN_MENU -> {
                if (rectangle.intersects(gameFromMenu)) {
                    Game.setScreen(Screen.GAME);
                    Game.setPaused(false);

                } else if (rectangle.intersects(creditsFromMenu)) {
                    Game.setScreen(Screen.CREDITS);
                }
            }

            case DEATH -> {
                Game.reset();
                if (rectangle.intersects(mainMenuFromDeath)) {
                    Game.setScreen(Screen.MAIN_MENU);
                } else if (rectangle.intersects(gameFromDeath)) {
                    Game.setScreen(Screen.GAME);
                }

            }

            case CREDITS -> {
                if (rectangle.intersects(mainMenuCredits)) {
                    Game.setScreen(Screen.MAIN_MENU);
                }
            }

            case GAME -> {
                if (Game.isPaused()) {
                    if (rectangle.intersects(saveFromPause)) {
                        Save.saveGame();
                        Game.setPaused(false);
                    } else if (rectangle.intersects(loadFromPause)) {
                        Load.loadGame();
                        Game.setPaused(false);
                    } else if (rectangle.intersects(mainMenuFromPause)) {
                        Game.setScreen(Screen.MAIN_MENU);
                        Game.reset();
                        Game.setPaused(false);
                    }
                }
            }
        }
    }

    public void setRectangle(Rectangle rectangle) {
        this.rectangle = rectangle;
    }
}

