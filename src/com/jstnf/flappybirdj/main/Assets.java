package com.jstnf.flappybirdj.main;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import java.awt.image.BufferedImage;
import java.io.File;

public class Assets
{
	public static BufferedImage bird, pipeTop, pipeBottom, bg, grass, directions;
	public static String die, hit, point, swooshing, wing;
	public static BufferedImage[] number;

	public static void init()
	{
		number = new BufferedImage[10];

		bird = ImageLoader.loadImage("res/textures/bird.png");
		pipeTop = ImageLoader.loadImage("res/textures/pipe-down.png");
		pipeBottom = ImageLoader.loadImage("res/textures/pipe-up.png");
		bg = ImageLoader.loadImage("res/textures/bg.png");
		grass = ImageLoader.loadImage("res/textures/grass.png");
		directions = ImageLoader.loadImage("res/textures/dir.png");

		die = "res/sounds/sfx_die.wav";
		hit = "res/sounds/sfx_hit.wav";
		point = "res/sounds/sfx_point.wav";
		swooshing = "res/sounds/sfx_swooshing.wav";
		wing = "res/sounds/sfx_wing.wav";

		number[0] = ImageLoader.loadImage("res/textures/number/zero.png");
		number[1] = ImageLoader.loadImage("res/textures/number/one.png");
		number[2] = ImageLoader.loadImage("res/textures/number/two.png");
		number[3] = ImageLoader.loadImage("res/textures/number/three.png");
		number[4] = ImageLoader.loadImage("res/textures/number/four.png");
		number[5] = ImageLoader.loadImage("res/textures/number/five.png");
		number[6] = ImageLoader.loadImage("res/textures/number/six.png");
		number[7] = ImageLoader.loadImage("res/textures/number/seven.png");
		number[8] = ImageLoader.loadImage("res/textures/number/eight.png");
		number[9] = ImageLoader.loadImage("res/textures/number/nine.png");
	}

	public static void playSound(String path)
	{
		try
		{
			AudioInputStream ais = AudioSystem.getAudioInputStream(new File(path));
			DataLine.Info info = new DataLine.Info(Clip.class, ais.getFormat());
			Clip test = (Clip) AudioSystem.getLine(info);
			test.open(ais);
			test.start();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}