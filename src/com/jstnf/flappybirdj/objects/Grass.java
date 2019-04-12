package com.jstnf.flappybirdj.objects;

import com.jstnf.flappybirdj.main.Assets;
import com.jstnf.flappybirdj.main.Entity;
import com.jstnf.flappybirdj.main.Handler;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Grass extends GameObject
{
	private BufferedImage sprite;
	private Handler handler;

	public Grass(int x, int y, Entity id, Handler handler)
	{ // y = 124
		super(x, y, id);
		sprite = Assets.grass;
		this.handler = handler;
	}

	public void tick()
	{

	}

	public void render(Graphics g, Graphics2D g2d)
	{
		g.drawImage(sprite, x, y, handler.game.getWidth(), (int) (handler.game.getHeight() * (56.0 / 256.0)), null);
		if (sprite == null)
		{
			System.out.println("GRASS IS NULL!");
		}
	}
}