package com.jstnf.flappybirdj.main;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import com.jstnf.flappybirdj.objects.*;

public class KeyInput extends KeyAdapter {

	private Handler handler;

	public KeyInput(Handler handler) {
		this.handler = handler;
	}

	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		int state = handler.game.getState();

		if (state == 2) { // game is active
			for (int i = 0; i < handler.object.size(); i++) {
				GameObject obj = handler.object.get(i);
				if (obj.getId() == Entity.BIRD) {
					if (key == KeyEvent.VK_SPACE) {
						((Bird) obj).jump();
					}
				}
			}
		} else if (state == 0) { // menu state
			if (key == KeyEvent.VK_R) {
				handler.game.reset();
			}
		} else { // we're just hovering, lol
			if (key == KeyEvent.VK_SPACE) {
				handler.game.setState(2);
				handler.game.getPlayer().jump();
			}
		}
	}

	public void keyReleased(KeyEvent e) {

	}

}
