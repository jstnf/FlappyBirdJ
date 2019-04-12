package com.jstnf.flappybirdj.main;

import com.jstnf.flappybirdj.objects.Bird;
import com.jstnf.flappybirdj.objects.GameObject;
import com.jstnf.flappybirdj.objects.Pipe;

import java.awt.*;
import java.util.LinkedList;

public class Handler
{
	LinkedList<GameObject> object = new LinkedList<GameObject>();
	protected int pipeTimer, defaultPipeSpeed;
	public FlappyBird game;
	private boolean deathSoundPlayed;

	public Handler(FlappyBird game)
	{
		pipeTimer = 0;
		deathSoundPlayed = false;
		this.game = game;
		defaultPipeSpeed = 3;
	}

	public void tick()
	{
		for (int i = 0; i < object.size(); i++)
		{
			GameObject obj = object.get(i);
			obj.tick();

			if (obj.getId() == Entity.BIRD)
			{
				if (((Bird) obj).onTheGround())
				{
					obj.setY(((Bird) obj).getLOWER_LIM());
					kill();
				}
			}

			if (game.getState() == 2)
			{
				if (obj.getId() == Entity.PIPE)
				{
					if (((Pipe) obj).isWithinBird())
					{
						if (game.getPlayer().isColliding((Pipe) obj))
						{
							kill();
						}
						if (game.getPlayer().checkScore((Pipe) obj))
						{
							game.setScore(game.getScore() + 1);
							Assets.playSound(Assets.point);
						}
					}

					if (((Pipe) obj).isOffscreen())
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

		/* Prints number of objects loaded. */
		// System.out.println(object.size());
	}

	public void render(Graphics g, Graphics2D g2d)
	{
		for (int i = object.size() - 1; i > -1; i--)
		{
			GameObject obj = object.get(i);
			obj.render(g, g2d);
		}
	}

	public void addObject(GameObject object)
	{
		this.object.add(object);
	}

	public void removeObject(GameObject object)
	{
		this.object.remove(object);
	}

	private boolean spawnPipe(int pipeTimer)
	{
		if (pipeTimer > 80)
		{
			addObject(new Pipe(400, 50 + (int) (215 * Math.random()), Entity.PIPE, defaultPipeSpeed));
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
			Assets.playSound(Assets.hit);
			Assets.playSound(Assets.die);
		}
		for (int i = object.size() - 1; i > -1; i--)
		{
			if (object.get(i).getId() == Entity.PIPE)
			{
				((Pipe) object.get(i)).stopFunction();
			}
		}
	}

	public void reset()
	{
		deathSoundPlayed = false;
		for (int i = object.size() - 1; i > -1; i--)
		{
			if (object.get(i).getId() == Entity.PIPE)
			{
				object.remove(i);
			}
		}
	}

	public int getSpeed()
	{
		return defaultPipeSpeed;
	}

	public void setSpeed(int speed)
	{
		defaultPipeSpeed = speed;
	}
}