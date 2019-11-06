package com.jstnf.flappybirdj.main;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.LinkedList;

public class Assets
{
    private static LinkedList<Clip> clipQueue;

    public static BufferedImage bird;
    public static BufferedImage pipeTop;
    public static BufferedImage pipeBottom;
    public static BufferedImage bg;
    public static BufferedImage grass;
    public static BufferedImage directions;
    public static BufferedImage[] number;

    public enum SoundEffect
    {
        DIE,
        HIT,
        POINT,
        SWOOSHING,
        WING;

        private Clip clip;
        private String path;

        private void setClip(Clip clip)
        {
            this.clip = clip;
        }

        private Clip getClip()
        {
            return clip;
        }

        private void setPath(String path)
        {
            this.path = path;
        }

        private String getPath()
        {
            return path;
        }
    }

    public static void tick()
    {
        for (int i = 0; i < clipQueue.size(); ++i)
        {
            if (!clipQueue.get(i).isRunning())
            {
                clipQueue.get(i).close();
                clipQueue.remove(i);
                i--;
            }
        }
    }

    public static void init()
    {
        number = new BufferedImage[10];
        clipQueue = new LinkedList<>();

        bird = ImageLoader.loadImage("res/textures/bird.png");
        pipeTop = ImageLoader.loadImage("res/textures/pipe-down.png");
        pipeBottom = ImageLoader.loadImage("res/textures/pipe-up.png");
        bg = ImageLoader.loadImage("res/textures/bg.png");
        grass = ImageLoader.loadImage("res/textures/grass.png");
        directions = ImageLoader.loadImage("res/textures/dir.png");

        try
        {
            AudioInputStream aisDie = AudioSystem.getAudioInputStream(new File("res/sounds/sfx_die.wav"));
            DataLine.Info infoDie = new DataLine.Info(Clip.class, aisDie.getFormat());
            Clip clipDie = (Clip) AudioSystem.getLine(infoDie);
            clipDie.open(aisDie);
            SoundEffect.DIE.setClip(clipDie);
            SoundEffect.DIE.setPath("res/sounds/sfx_die.wav");

            AudioInputStream aisHit = AudioSystem.getAudioInputStream(new File("res/sounds/sfx_hit.wav"));
            DataLine.Info infoHit = new DataLine.Info(Clip.class, aisHit.getFormat());
            Clip clipHit = (Clip) AudioSystem.getLine(infoHit);
            clipHit.open(aisHit);
            SoundEffect.HIT.setClip(clipHit);
            SoundEffect.HIT.setPath("res/sounds/sfx_hit.wav");

            AudioInputStream aisPoint = AudioSystem.getAudioInputStream(new File("res/sounds/sfx_point.wav"));
            DataLine.Info infoPoint = new DataLine.Info(Clip.class, aisPoint.getFormat());
            Clip clipPoint = (Clip) AudioSystem.getLine(infoPoint);
            clipPoint.open(aisPoint);
            SoundEffect.POINT.setClip(clipPoint);
            SoundEffect.POINT.setPath("res/sounds/sfx_point.wav");

            AudioInputStream aisSwooshing = AudioSystem.getAudioInputStream(new File("res/sounds/sfx_swooshing.wav"));
            DataLine.Info infoSwooshing = new DataLine.Info(Clip.class, aisSwooshing.getFormat());
            Clip clipSwooshing = (Clip) AudioSystem.getLine(infoSwooshing);
            clipSwooshing.open(aisSwooshing);
            SoundEffect.SWOOSHING.setClip(clipSwooshing);
            SoundEffect.SWOOSHING.setPath("res/sounds/sfx_swooshing.wav");

            AudioInputStream aisWing = AudioSystem.getAudioInputStream(new File("res/sounds/sfx_wing.wav"));
            DataLine.Info infoWing = new DataLine.Info(Clip.class, aisWing.getFormat());
            Clip clipWing = (Clip) AudioSystem.getLine(infoWing);
            clipWing.open(aisWing);
            SoundEffect.WING.setClip(clipWing);
            SoundEffect.WING.setPath("res/sounds/sfx_wing.wav");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

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

    public static void playSound(SoundEffect soundEffect)
    {
        /* If the clip is already playing, load another one. */
        if (soundEffect.getClip().isActive())
        {
            try
            {
                AudioInputStream ais = AudioSystem.getAudioInputStream(new File(soundEffect.getPath()));
                DataLine.Info info = new DataLine.Info(Clip.class, ais.getFormat());
                Clip clip = (Clip) AudioSystem.getLine(info);
                clip.open(ais);
                clip.start();
                clipQueue.add(clip); /* Queue the clip for closing later on. */
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        else
        {
            Clip c = soundEffect.getClip();
            c.setFramePosition(0);
            c.start();
        }
    }
}