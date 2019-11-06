package com.jstnf.flappybirdj.main;

import com.jstnf.flappybirdj.objects.Bird;
import com.jstnf.flappybirdj.objects.GameObject;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter
{
    private Handler handler;

    public KeyInput(Handler handler)
    {
        this.handler = handler;
    }

    public void keyPressed(KeyEvent e)
    {
        int key = e.getKeyCode();
        int state = handler.getGame().getState();

        if (state == 2)
        { // game is active
            for (int i = 0; i < handler.getObjects().size(); i++)
            {
                GameObject obj = handler.getObjects().get(i);
                if (obj instanceof Bird)
                {
                    if (key == KeyEvent.VK_SPACE)
                    {
                        Bird bird = (Bird) obj;
                        bird.jump();
                    }
                }
            }
        }
        else if (state == 0)
        { // menu state
            if (key == KeyEvent.VK_R)
            {
                handler.getGame().reset();
            }
        }
        else
        { // we're just hovering, lol
            if (key == KeyEvent.VK_SPACE)
            {
                handler.getGame().setState(2);
                handler.getGame().getPlayer().jump();
            }
        }
    }

    public void keyReleased(KeyEvent e)
    {

    }
}