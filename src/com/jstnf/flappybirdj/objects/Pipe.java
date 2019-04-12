package com.jstnf.flappybirdj.objects;

import com.jstnf.flappybirdj.main.Assets;
import com.jstnf.flappybirdj.main.Entity;

import java.awt.*;

public class Pipe extends GameObject
{
	protected int upperGap, lowerGap, speed;
	private final int WIDTH = 72, BIRD_WIDTH = 44, PIPE_GAP = 150, PIPE_RANDOM = 10;

	public Pipe(int x, int upperGap, Entity id, int speed)
	{
		super(x, 0, id);
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
		return this.x < WINDOW_WIDTH / 4 + BIRD_WIDTH / 2 && this.x > WINDOW_WIDTH / 4 - (WIDTH + BIRD_WIDTH / 2);
	}

	// Getters/Setters
	public int getUpperGap()
	{
		return upperGap;
	}

	public int getLowerGap()
	{
		return lowerGap;
	}
}