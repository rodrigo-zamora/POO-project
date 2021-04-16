package com.game.juanito.enemy.enemies;

import com.game.juanito.enemy.Enemy;
import com.game.juanito.handler.CollisionHandler;
import com.game.juanito.main.Game;
import com.game.juanito.main.ID;

import java.awt.*;
import java.net.URL;

public class Deidamia extends Enemy {

    Toolkit toolkit = Toolkit.getDefaultToolkit();

    URL deidamiaLeft = ClassLoader.getSystemResource("enemies/DeidamiaL.gif");
    Image deidamiaLeftImage = toolkit.getImage(deidamiaLeft);
    URL deidamiaIdle = ClassLoader.getSystemResource("enemies/DeidamiaIdle.png");
    Image deidamiaIdleImage = toolkit.getImage(deidamiaIdle);

    CollisionHandler collisionHandler = new CollisionHandler(150, 40); // 149, 50

    /**
     * Constructor for Deidamia class
     *
     * @param x
     * @param y
     * @param id
     */
    public Deidamia(int x, int y, ID id) {
        super(x, y, id);
        collisionHandler.setY(y); // 45 / 56
        collisionHandler.setX(x);
    }

    @Override
    public boolean tick() {
        collisionHandler.setX(x);
        collisionHandler.setY(y+57);
        collisionCheck(collisionHandler);
        return x >= -200;
    }

    @Override
    public void render(Graphics graphics) {
        graphics.drawImage(
                getImage(Game.isMoving),
                getX(),
                getY(),
                null
        );
    }

    @Override
    public void collision(Rectangle rectangle) {
        if (rectangle.intersects(collisionHandler.getRectangle())) {
            collisionIntersect();
        }
    }

    /**
     * Method to get the image of Deidamia depending in the player movement
     *
     * @param isMoving receives a boolean
     * @return an Image
     */
    private Image getImage(boolean isMoving) {
        if (isMoving) {
            return deidamiaLeftImage;
        } else return deidamiaIdleImage;
    }
}
