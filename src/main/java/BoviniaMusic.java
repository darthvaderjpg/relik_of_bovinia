//Siddarth Iyer

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComponent;
import javax.swing.Timer;

public class BoviniaMusic implements ActionListener {
     
    private final int ACTION_TIMEOUT = 500;
    
   // Music for different parts of the game
    private final MusicPlayer bossFight = new MusicPlayer("sounds/BOVINE.wav");
    private final MusicPlayer templeMusic = new MusicPlayer("sounds/Sorrow.wav");
    private final MusicPlayer overworld = new MusicPlayer("sounds/Rumination.wav");

    // Track which music is active and current room state
    private MusicPlayer currentTrack;
    private String currentRoom = "";
    private boolean musicStarted = false;
    private Timer actionTimer;

    public BoviniaMusic(int x, int y, JComponent surface) {
        // Set all tracks to loop by default. This makes it so that once the music finishes, it'll restart and you won't just be in silence
        bossFight.setLoop(true);
        templeMusic.setLoop(true);
        overworld.setLoop(true);

        actionTimer = new Timer(ACTION_TIMEOUT, this);
        actionTimer.setRepeats(false);
        actionTimer.start();
    }
    
    
    public void changeRoom(String roomType) {
        // Avoid restarting if the player is already in this room type
        if (roomType.equalsIgnoreCase(currentRoom)) {
            return;
        }

        currentRoom = roomType;

        // Determine which track to play based on room
        MusicPlayer targetTrack = null;
        switch (roomType.toLowerCase()) {
            case "boss":
                targetTrack = bossFight;
                break;
            case "temple":
                targetTrack = templeMusic;
                break;
            case "overworld":
                targetTrack = overworld;
                break;
            default:
                System.out.println("Unknown room type: " + roomType);
                break;
        }

        if (targetTrack != null) {
            playTrack(targetTrack);
        }
    }

    private void playTrack(MusicPlayer newTrack) {
        // Stop current music if it is playing
        if (currentTrack != null) {
            currentTrack.stop();
        }

        currentTrack = newTrack;

        if (currentTrack != null && currentTrack.isLoaded()) {
            currentTrack.play();
            musicStarted = true;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        if (!musicStarted && currentRoom.isEmpty()) {
            changeRoom("overworld"); 
        }
    }
    //method to pause the music
    public void pauseMusic() {
        actionTimer.stop();
        if (currentTrack != null) {
            currentTrack.stop();
        }
        musicStarted = false;
    }
    
    // method to resume music
    public void resumeMusic() {
        if (!musicStarted && currentTrack != null && currentTrack.isLoaded()) {
            currentTrack.play();
            musicStarted = true;
        }
    }
}