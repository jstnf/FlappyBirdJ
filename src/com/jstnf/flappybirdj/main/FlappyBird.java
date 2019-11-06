package com.jstnf.flappybirdj.main;

import com.jstnf.flappybirdj.objects.Bird;
import com.jstnf.flappybirdj.objects.Grass;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;

public class FlappyBird extends Canvas implements Runnable
{
    private final boolean DEBUG_WINDOW_VISIBLE = false;
    public static final int WIDTH = 400;
    public static final int HEIGHT = 600;

    private Handler handler;
    private Thread thread;
    private boolean running;
    private int state; // 0 dead or menu, 1 hover, 2 playing
    private Options options;

    private Bird player;
    private int score;

    public FlappyBird()
    {
        state = 1;
        score = 0;
        handler = new Handler(this);
        this.addKeyListener(new KeyInput(handler));
        new Window(WIDTH, HEIGHT, "FlappyBirdJ", this);

        player = new Bird(WIDTH / 4, (HEIGHT - 124) / 2, handler);
        handler.addObject(player);
        handler.addObject(new Grass(0, (int) (HEIGHT * (200.0 / 256.0)), handler));

        options = new Options(this);
        options.setVisible(DEBUG_WINDOW_VISIBLE);
    }

    public synchronized void start()
    {
        if (!running)
        {
            thread = new Thread(this);
            thread.start();
            running = true;
        }
        Assets.init();
    }

    private synchronized void stop()
    {
        try
        {
            thread.join();
            running = false;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void run()
    {
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        while (running)
        {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while (delta >= 1)
            {
                tick();
                delta--;
            }
            if (running)
                render();
            frames++;

            if (System.currentTimeMillis() - timer > 1000)
            {
                timer += 1000;
                System.out.println("FPS: " + frames);
                frames = 0;
            }
        }
        stop();
    }

    private void tick()
    {
        handler.tick();
        Assets.tick();
    }

    private void render()
    {
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null)
        {
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();
        Graphics g2d = bs.getDrawGraphics();

        g.setColor(Color.black);
        g.fillRect(0, 0, WIDTH, HEIGHT);
        g.drawImage(Assets.bg, 0, 0, WIDTH, HEIGHT, null);

        handler.render(g, (Graphics2D) g2d);

        if (state == 1)
        {
            g.drawImage(Assets.directions, WIDTH / 2 - 63, HEIGHT / 2, null);
        }

        drawScore(g);

        g.dispose();
        bs.show();
    }

    public static void main(String[] args)
    {
        new FlappyBird();
    }

    public void reset()
    {
        setScore(0);
        setState(1);
        Assets.playSound(Assets.SoundEffect.SWOOSHING);
        handler.reset();
        player.reset();
    }

    private void drawScore(Graphics g)
    {
        int toDraw = score;
        int scoreLength = ("" + toDraw).length();
        switch (scoreLength)
        {
            case 1:
                g.drawImage(Assets.number[toDraw], WIDTH / 2 - 10, HEIGHT / 10 - 14, null);
                break;
            case 2:
                int tens = toDraw / 10;
                int ones = toDraw % 10;
                g.drawImage(Assets.number[tens], WIDTH / 2 - 21, HEIGHT / 10 - 14, null);
                g.drawImage(Assets.number[ones], WIDTH / 2 + 1, HEIGHT / 10 - 14, null);
                break;
            case 3:
                int hundreds = toDraw / 100;
                int tens2 = toDraw % 100 / 10;
                int ones2 = toDraw % 10;
                g.drawImage(Assets.number[hundreds], WIDTH / 2 - 32, HEIGHT / 10 - 14, null);
                g.drawImage(Assets.number[tens2], WIDTH / 2 - 10, HEIGHT / 10 - 14, null);
                g.drawImage(Assets.number[ones2], WIDTH / 2 + 12, HEIGHT / 10 - 14, null);
                break;
        }
    }

    /* ----- GETTERS / SETTERS ----- */
    public Bird getPlayer()
    {
        return player;
    }

    public int getScore()
    {
        return score;
    }

    public void setScore(int newScore)
    {
        if (newScore > 999)
        { // hard 999 score limit
            score = 999;
        }
        else
        {
            score = newScore;
        }
        System.out.println("Score: " + score);
    }

    public int getState()
    {
        return state;
    }

    public void setState(int newState)
    {
        state = newState;
    }

    public Bird getBird()
    {
        return player;
    }

    public Handler getHandler()
    {
        return handler;
    }
}