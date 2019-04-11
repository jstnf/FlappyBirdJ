package com.jstnf.flappybirdj.main;

import java.awt.Graphics;
import java.awt.Graphics2D;

public abstract class GameObject {

	protected final int WINDOW_WIDTH = 400, WINDOW_HEIGHT = 600;
	protected int x, y;
	protected Entity id;

	public GameObject(int x, int y, Entity id) {
		this.x = x;
		this.y = y;
		this.id = id;
	}

	public abstract void tick();

	public abstract void render(Graphics g, Graphics2D g2d);

	// Getters/Setters
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void setId(Entity id) {
		this.id = id;
	}

	public Entity getId() {
		return id;
	}

}
