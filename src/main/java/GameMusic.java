//Name: Siddarth Iyer
//Date May 24, 2026
//Rev:01
//Notes: This Class was designed to handle the specifics of the music. All of the musics share these things in common on their functionality
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

public class GameMusic implements ActionListener {
	

	    private final int ACTION_TIMEOUT = 500;
	    private BossMusic music; // reusable for ANY song
	    private Timer actionTimer;
	    private boolean musicStarted = false;

	    public GameMusic(String fileName) // pass in ANY song file
	    {
	    	//just a variable to store the music name in boss music
	    	
	        music = new BossMusic(fileName);
	        //loops music
	        music.setLoop(true);
	    }
	    
	    //Starts music
	    public void startMusic()
	    {
	        if (music.isLoaded() && !music.isPlaying())
	        {
	            music.play();
	            musicStarted = true;
	        }
	    }

	    @Override
	    
	    //If the music has not started and has also been loaded (like file is there and everything is written in properly
	    //then it's okay to start playing music
	    public void actionPerformed(ActionEvent e)
	    {
	        if (!musicStarted && music.isLoaded())
	        {
	            music.play();
	            musicStarted = true;
	        }
	    }


	    //if action timer is not null, meaning that everything is working properly, then it's okay to stop playing music
	    //otherwise, there will be an exception thrown
	    public void pauseMusic()
	    {
	        if (actionTimer != null)
	        {
	            actionTimer.stop();
	        }
	        music.stop();
	        musicStarted = false;
	    }

	}
