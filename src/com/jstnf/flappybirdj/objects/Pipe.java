package com.jstnf.flappybirdj.objects;

import com.jstnf.flappybirdj.main.Assets;

import java.awt.Graphics;
import java.awt.Graphics2D;

public class Pipe extends GameObject
{
    private final int WIDTH = 72;
    private final int BIRD_WIDTH = 44;
    private final int PIPE_GAP = 150;
    private final int PIPE_RANDOM = 10;

    private int upperGap;
    private int lowerGap;
    private int speed;

    public Pipe(int x, int upperGap, int speed)
    {
        super(x, 0);
        this.upperGap = upperGap;
        lowerGap = upperGap + ((int) (PIPE_GAP - Math.random() * PIPE_RANDOM));
        this.speed = speed;
    }

    public void tick()
    {
        x -= speed;
    }

    public void render(Graphics g, Graphics2D g2d)
    {
        /* Uncomment for hitbox */
        // g.setColor(Color.white);
        // g.fillRect(x, 0, 72, upperGap);
        // g.fillRect(x, lowerGap, 72, WINDOW_HEIGHT);

        g.drawImage(Assets.pipeTop, x, upperGap - 374, null); // 374 is height of image in sprites
        g.drawImage(Assets.pipeBottom, x, lowerGap, null);
    }

    public void stopFunction()
    {
        speed = 0;
    }

    public boolean isOffscreen()
    {
        return this.x < -72;
    }

    public boolean isWithinBird()
    {
        return x < WINDOW_WIDTH / 4 + BIRD_WIDTH / 2 && x > WINDOW_WIDTH / 4 - (WIDTH + BIRD_WIDTH / 2);
    }

    /* ----- GETTERS / SETTERS ----- */
    public int getUpperGap()
    {
        return upperGap;
    }

    public int getLowerGap()
    {
        return lowerGap;
    }
}