package com.jstnf.flappybirdj.objects;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import com.jstnf.flappybirdj.main.Assets;
import com.jstnf.flappybirdj.main.GameObject;
import com.jstnf.flappybirdj.main.Handler;
import com.jstnf.flappybirdj.main.Entity;

public class Grass extends GameObject {

	private BufferedImage sprite;
	private Handler handler;

	public Grass(int x, int y, Entity id, Handler handler) { // y = 124
		super(x, y, id);
		sprite = Assets.grass;
		this.handler = handler;
	}

	public void tick() {
		
	}

	public void render(Graphics g, Graphics2D g2d) {
		g.drawImage(sprite, x, y, handler.game.getWidth(), (int)(handler.game.getHeight() * (56.0 / 256.0)), null);
		if (sprite == null)
			System.out.println("GRASS IS NULL!");
	}

}
