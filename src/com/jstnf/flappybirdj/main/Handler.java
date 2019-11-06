package com.jstnf.flappybirdj.main;

import com.jstnf.flappybirdj.objects.Bird;
import com.jstnf.flappybirdj.objects.GameObject;
import com.jstnf.flappybirdj.objects.Pipe;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.LinkedList;

public class Handler
{
    private FlappyBird game;
    private LinkedList<GameObject> objects = new LinkedList<>();
    private int pipeTimer;
    private int pipeSpeed;
    private boolean deathSoundPlayed;

    public Handler(FlappyBird game)
    {
        pipeTimer = 0;
        deathSoundPlayed = false;
        this.game = game;
        pipeSpeed = 3;
    }

    public void tick()
    {
        for (int i = 0; i < objects.size(); i++)
        {
            GameObject obj = objects.get(i);
            obj.tick();

            if (obj instanceof Bird)
            {
                Bird bird = (Bird) obj;
                if (bird.onTheGround())
                {
                    obj.setY(bird.getLOWER_LIM());
                    kill();
                }
            }

            if (game.getState() == 2)
            {
                if (obj instanceof Pipe)
                {
                    Pipe pipe = (Pipe) obj;

                    if (pipe.isWithinBird())
                    {
                        if (game.getPlayer().isColliding(pipe))
                        {
                            kill();
                        }
                        if (game.getPlayer().checkScore(pipe))
                        {
                            game.setScore(game.getScore() + 1);
                            Assets.playSound(Assets.SoundEffect.POINT);
                        }
                    }

                    if (pipe.isOffscreen())
                    {
                        removeObject(obj);
                        i--;
                    }
                }
            }
        }

        if (game.getState() == 2)
        {
            pipeTimer++;
            if (spawnPipe(pipeTimer))
            {
                pipeTimer = 0;
            }
        }
    }

    public void render(Graphics g, Graphics2D g2d)
    {
        for (int i = objects.size() - 1; i > -1; i--)
        {
            GameObject obj = objects.get(i);
            obj.render(g, g2d);
        }
    }

    public void addObject(GameObject object)
    {
        objects.add(object);
    }

    public void removeObject(GameObject object)
    {
        objects.remove(object);
    }

    private boolean spawnPipe(int pipeTimer)
    {
        if (pipeTimer > 80)
        {
            addObject(new Pipe(400, 50 + (int) (215 * Math.random()), pipeSpeed));
            return true;
        }
        return false;
    }

    private void kill()
    {
        game.setState(0);
        if (!deathSoundPlayed)
        {
            deathSoundPlayed = true;
            Assets.playSound(Assets.SoundEffect.HIT);
            Assets.playSound(Assets.SoundEffect.DIE);
        }
        for (int i = objects.size() - 1; i > -1; i--)
        {
            if (objects.get(i) instanceof Pipe)
            {
                Pipe pipe = (Pipe) objects.get(i);
                pipe.stopFunction();
            }
        }
    }

    public void reset()
    {
        deathSoundPlayed = false;
        for (int i = objects.size() - 1; i > -1; i--)
        {
            if (objects.get(i) instanceof Pipe)
            {
                objects.remove(i);
            }
        }
    }

    /* ----- GETTERS / SETTERS ----- */
    public FlappyBird getGame()
    {
        return game;
    }

    public int getSpeed()
    {
        return pipeSpeed;
    }

    public void setSpeed(int speed)
    {
        pipeSpeed = speed;
    }

    public LinkedList<GameObject> getObjects()
    {
        return objects;
    }
}