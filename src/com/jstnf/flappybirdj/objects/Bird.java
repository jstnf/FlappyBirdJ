package com.jstnf.flappybirdj.objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

import com.jstnf.flappybirdj.main.Assets;
import com.jstnf.flappybirdj.main.Entity;
import com.jstnf.flappybirdj.main.GameObject;
import com.jstnf.flappybirdj.main.Handler;

public class Bird extends GameObject {

	private int velocity, rotation;
	protected final int GRAVITY_SCALE = 1, UPWARD_VELOCITY = -11, WIDTH = (int) (400 * (17.0 / 154.0)),
			HEIGHT = (int) (600 * (12.0 / 256.0)), UPWARD_ROTATION_VELOCITY = 15, ROTATION_LIMIT = 40; // 44 x 28
	protected double GRAVITY = 0.75;
	protected double gravityCounter;
	protected final int UPPER_LIM = HEIGHT / 2, LOWER_LIM = WINDOW_HEIGHT - (124 + HEIGHT / 2);
	private Handler handler;
	protected BufferedImage sprite;
	private boolean hoverDirection;

	public Bird(int x, int y, Entity id, Handler handler) {
		super(x, y, id);
		gravityCounter = 0;
		hoverDirection = true;
		velocity = 0;
		this.handler = handler;
		sprite = Assets.bird;
	}

	public void tick() {

		int state = handler.game.getState();
		if (state == 2 || state == 0) {
			
			gravityCounter += GRAVITY;
			if (gravityCounter > 1.0) {
				velocity += GRAVITY_SCALE;
				gravityCounter -= 1.0;
			}
			
			if (gravityCounter < -1.0) {
				velocity -= GRAVITY_SCALE;
				gravityCounter += 1.0;
			}
			
			y += velocity;
			if (velocity > 10) {
				velocity = 10;
			}

			if (this.y < UPPER_LIM) {
				y = UPPER_LIM;
				velocity *= 0.5;
			}
			rotation = (int) (ROTATION_LIMIT * (velocity / 15.0));
			if (velocity > UPWARD_ROTATION_VELOCITY) {
				rotation = (int) (ROTATION_LIMIT * (UPWARD_ROTATION_VELOCITY / 15.0));
			}
		} else {
			if (hoverDirection) {
				this.y -= 1;
				if (this.y < LOWER_LIM / 2 - 20)
					hoverDirection = false;
			} else {
				this.y += 1;
				if (this.y > LOWER_LIM / 2 + 20)
					hoverDirection = true;
			}
		}
	}

	public void render(Graphics g, Graphics2D g2d) {

		// HITBOX
//		 g.setColor(Color.yellow);
//		 g.fillRect(x - WIDTH / 2, y - HEIGHT / 2, WIDTH, HEIGHT);

		int drawLocationX = x - WIDTH / 2;
		int drawLocationY = y - HEIGHT / 2;

		double rotationRequired = Math.toRadians(rotation);
		double locationX = sprite.getWidth() / 2;
		double locationY = sprite.getHeight() / 2;
		AffineTransform tx = AffineTransform.getRotateInstance(rotationRequired, locationX, locationY);
		AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);

		g2d.drawImage(op.filter(sprite, null), drawLocationX, drawLocationY, null);

		// STATIC, NO ROTATION
//		 g2d.drawImage(sprite, x - WIDTH / 2, y - HEIGHT / 2, null);

	}

	public void jump() {
		velocity = UPWARD_VELOCITY;
		Assets.play(Assets.wing);
	}

	public boolean isColliding(Pipe p) {
		return this.y - HEIGHT / 2 < p.getUpperGap() || this.y + HEIGHT / 2 > p.getLowerGap();
	}

	public boolean checkScore(Pipe p) {
		return this.x == p.getX();
	}

	public boolean onTheGround() {
		return this.y > LOWER_LIM;
	}

	public void reset() {
		velocity = 0;
		y = LOWER_LIM / 2;
		rotation = 0;
	}

	// Getters/Setters
	public int getLOWER_LIM() {
		return LOWER_LIM;
	}
	
	public double getGRAVITY() {
		return GRAVITY;
	}
	
	public void setGRAVITY(double newGrav) {
		GRAVITY = newGrav;
	}

}
