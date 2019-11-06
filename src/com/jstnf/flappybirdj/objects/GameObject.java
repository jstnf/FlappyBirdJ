package com.jstnf.flappybirdj.objects;

import java.awt.Graphics;
import java.awt.Graphics2D;

public abstract class GameObject
{
    protected final int WINDOW_WIDTH = 400;
    protected final int WINDOW_HEIGHT = 600;

    protected int x;
    protected int y;

    public GameObject(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    public abstract void tick();

    public abstract void render(Graphics g, Graphics2D g2d);

    /* ----- GETTERS / SETTERS ----- */
    public int getX()
    {
        return x;
    }

    public void setX(int x)
    {
        this.x = x;
    }

    public int getY()
    {
        return y;
    }

    public void setY(int y)
    {
        this.y = y;
    }
}