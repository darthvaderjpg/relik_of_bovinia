//Name: Siddarth Iyer
//Date: May 23/ 2026
//Rev:01
//Notes: This was taken from Mr. Taylor's Sounds and Sprites lesson

import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
//import javax.sound.sampled.LineUnavailableException;

public class MusicPlayer implements Runnable
{
  private Clip clip = null;
  private boolean loaded = false;
  private boolean loop = true;

  
  //The try and catch statement makes sure that the music runs smoothly
  public MusicPlayer(String fileName)
  {
    File soundFile = new File(fileName);
    AudioInputStream audioInputStream = null;
    try
    {
      audioInputStream = AudioSystem.getAudioInputStream(soundFile);
      clip = AudioSystem.getClip();
      clip.open(audioInputStream);
      loaded = true;
    }
    catch (Exception ex)
    {
      System.out.println("*** Cannot load " + fileName + " ***");
    }
    finally
    {
      if (audioInputStream != null)
      {
        try
        {
          audioInputStream.close();
        }
        catch (IOException ex)
        {
          ex.printStackTrace();
        }
      }
    }
  }

  //whether the music is loaded or not
  public boolean isLoaded()
  {
    return loaded;
  }

  //The method that creates the loops 
  public void setLoop(boolean loop)
  {
    this.loop = loop;
  }

  // the conditions for playing the music and looping it
  public synchronized void play()
  {
    if (!loaded || clip.isRunning())
    {
      return;
    }
    clip.setFramePosition(0);
    if (loop)
    {
      clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
    else
    {
      clip.start();
    }
  }

  public synchronized void stop()
  {
    if (clip != null)
    {
      clip.stop();
      clip.setFramePosition(0);
    }
  }

  public boolean isPlaying()
  {
    return loaded && clip != null && clip.isRunning();
  }

  public void run()
  {
    play();
  }
}
