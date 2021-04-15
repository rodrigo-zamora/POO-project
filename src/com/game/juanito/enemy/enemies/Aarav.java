package com.game.juanito.enemy.enemies;

import com.game.juanito.enemy.Enemy;
import com.game.juanito.handler.CollisionHandler;
import com.game.juanito.main.Game;
import com.game.juanito.main.ID;
import com.game.juanito.map.Chunk;
import com.game.juanito.player.Player;

import java.awt.*;
import java.net.URL;

public class Aarav extends Enemy {

    Toolkit toolkit = Toolkit.getDefaultToolkit();

    URL aarav = ClassLoader.getSystemResource("enemies/Aarav.gif");
    Image aaravLeftImage = toolkit.getImage(aarav);
    URL aaravIdle = ClassLoader.getSystemResource("enemies/AaravIdle.png");
    Image aaravIdleImage = toolkit.getImage(aaravIdle);

    CollisionHandler collisionHandler = new CollisionHandler(210, 75);

    /**
     * Constructor for Aarav class
     *
     * @param x
     * @param y
     * @param id
     */
    public Aarav(int x, int y, ID id) {
        super(x, y, id);
        collisionHandler.setY(y + 75);
        collisionHandler.setX(x);
    }

    /**
     * Method to get the image of Aarav depending in the player movement
     *
     * @param isMoving receives a boolean
     * @return an Image
     */
    private Image getImage(boolean isMoving) {
        if (isMoving) {
            return aaravLeftImage;
        } else return aaravIdleImage;
    }

    @Override
    public boolean tick() {
        collisionCheck(collisionHandler);
        return x >= -150;
    }

    @Override
    public void render(Graphics graphics) {
        graphics.drawImage(
                getImage(Game.isMoving),
                getX(),
                getY(),
                null
        );
        graphics.setColor(Color.RED);
        graphics.drawRect(
                collisionHandler.getX(),
                collisionHandler.getY(),
                collisionHandler.getWidth(),
                collisionHandler.getHeight());
    }

    @Override
    public void collision(Rectangle rectangle) {
        if (rectangle.intersects(collisionHandler.getRectangle())) {
            collisionIntersect();
        }
    }

}
