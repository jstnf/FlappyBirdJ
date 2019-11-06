package com.jstnf.flappybirdj.objects;

import com.jstnf.flappybirdj.main.Assets;
import com.jstnf.flappybirdj.main.Handler;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class Grass extends GameObject
{
    private BufferedImage sprite;
    private Handler handler;

    public Grass(int x, int y, Handler handler)
    { // y = 124
        super(x, y);
        sprite = Assets.grass;
        this.handler = handler;
    }

    public void tick()
    {

    }

    public void render(Graphics g, Graphics2D g2d)
    {
        g.drawImage(sprite, x, y, handler.getGame().getWidth(), (int) (handler.getGame().getHeight() * (56.0 / 256.0)), null);
        if (sprite == null)
        {
            System.out.println("GRASS IS NULL!");
        }
    }
}