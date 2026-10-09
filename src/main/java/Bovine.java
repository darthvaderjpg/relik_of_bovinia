import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;
import javax.swing.JComponent;
import javax.swing.Timer;

public class Bovine implements ActionListener {
	 
	  private final int ACTION_TIMEOUT = 500;
	
	  // Load the sounds from the disk. These are the 3 pieces of music that we are using
	  private final BossMusic BossFight = new BossMusic("BOVINE.wav");
	  private final BossMusic TempleMusic = new BossMusic("Sorrow.wav");
	  private final BossMusic Overworld = new BossMusic("Rumination.wav");
	  {
		  BossFight.setLoop(true);
	  }
	 
	  private Timer actionTimer;
	  private boolean musicStarted = false;
	
	  public Bovine(int x, int y, JComponent surface) {
		  actionTimer = new Timer(ACTION_TIMEOUT, this);
		  actionTimer.setRepeats(false);
		  actionTimer.start();
	  }
	  
	  
	  public void actionPerformed(ActionEvent e) {
		  if (!musicStarted && BossFight.isLoaded())
		  {
			  BossFight.play();
			  musicStarted = true;
		  }
	  }
	  
	  //Stops music
	  public void pauseMusic()
	  {
		  actionTimer.stop();
		  BossFight.stop();
		  musicStarted = false;
	  }
	  
	  //If the music hasn't started yet and BossFight is there, then start 
	  public void resumeMusic()
	  {
		  if (!musicStarted && BossFight.isLoaded())
		  {
			  BossFight.play();
			  musicStarted = true;
		  }
	  }
}
