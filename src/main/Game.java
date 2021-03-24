package main;

import TileMap.TileMap;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.io.Serial;

public class Game extends Canvas implements Runnable {

    @Serial
    private static final long serialVersionUID = 2717367914577165013L;
    
    public static final int WIDTH = 1080;
    public static final int HEIGHT = 640;
    public static final String TITLE = "El escape de Juanito";

    private Thread thread;
    private boolean isRunning = false;

    private final Handler handler;

    private int FPS = 60;

    Player player;

    Image backgroundImage = Toolkit.getDefaultToolkit().getImage("res/map/Test2.png");

    /**
     *
     */
    public Game() {
        handler = new Handler();
        this.addKeyListener(new KeyboardInput(handler));
        new Window(WIDTH, HEIGHT, TITLE, this);
        player = new Player(WIDTH/2 - 32, HEIGHT/2 - 32, ID.Player);
        handler.addObject(player);

    }

    /**
     *
     */
    public synchronized void start() {
        thread = new Thread(this);
        thread.start();
        isRunning = true;
    }

    /**
     *
     */
    public synchronized void stop() {
        try{
            thread.join();
            isRunning = false;
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    /**
     *
     */
    public void run() {
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        while(isRunning) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while(delta >= 1) {
                tick();
                delta--;
            }
            if(isRunning) {
                render();
            }
            frames++;
            //System.out.println("X: " + player.getX() + " Y: " + player.getY());
            if(System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                FPS = frames;
                frames = 0;
            }
        }
        stop();
    }

    /**
     *
     */
    private void tick() {
        handler.tick();
    }

    /**
     *
     */
    private void render() {
        BufferStrategy bufferStrategy = this.getBufferStrategy();
        if(bufferStrategy == null) {
            this.createBufferStrategy(3);
            return;
        }

        Graphics graphics = bufferStrategy.getDrawGraphics();

        // Clear screen
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, WIDTH, HEIGHT);

        graphics.drawImage(
                backgroundImage,
                -player.getX(),
                -200,
                null
        );

        graphics.setColor(Color.BLACK);
        graphics.setFont(new Font("TimesRoman", Font.PLAIN, 20));
        graphics.drawString("FPS: " + FPS, 15, 20);

        handler.render(graphics);

        graphics.dispose();
        bufferStrategy.show();
    }

    public static void main(String[] args) {
        new Game();
    }
}
