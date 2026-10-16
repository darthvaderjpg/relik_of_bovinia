// Author : Ofer Koren
// Date   : 5/08/2026
// Rev    : 02
// Notes  : This class is responsible for the background, music, and room spawn configurations.

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;

//import javax.swing.ImageIcon;

public class Map implements ImageObserver 
{
    private final static Image BACKGROUND_0 = Tools.getNewImageIcon("BACKGROUND_0.png"); // Title Screen
    private final static Image BACKGROUND_1 = Tools.getNewImageIcon("BACKGROUND_1.gif"); // Bedroom
    private final static Image BACKGROUND_2 = Tools.getNewImageIcon("starter-forest-pixilart.png"); // Starter Forest
    private final static Image BACKGROUND_3 = Tools.getNewImageIcon("npc-room-pixilart (1).png"); // NPC Room
    private final static Image BACKGROUND_4 = Tools.getNewImageIcon("TempRoom4.png"); // Soldier Room
    private final static Image BACKGROUND_5 = Tools.getNewImageIcon("snowy-background-place-holder-pixilart.png"); // Artifact Room
    private final static Image BACKGROUND_6 = Tools.getNewImageIcon("TempRoom6.png"); // Elite Guard Room
    private final static Image BACKGROUND_7 = Tools.getNewImageIcon("TempRoom3.png"); // Puzzle Room with key
    private final static Image BACKGROUND_8 = Tools.getNewImageIcon("BACKGROUND_8.png"); // Boss Room
    
    // Different types of music. This is in Map class because we want it to play based on a certain map. 
    private GameMusic bossMusic    = new GameMusic("BOVINE.wav");
    private GameMusic TempleMusic = new GameMusic("Sorrow.wav");
    private GameMusic Overworld   = new GameMusic("rumination.wav");
    
    private static boolean bossMusicEnabled = false;
    
    public static void enableBossMusic()
    {
        bossMusicEnabled = true;
    }
    
    // This delegates when the Boss Fight music should or shouldn't be played
    public void updateRoomMusic()
    {
        int room = Player.getRoom();
        
        if (bossMusicEnabled && room == 8)
        {
            bossMusic.startMusic();
        }
        else
        {
            bossMusic.pauseMusic();
        }
        
        if (isTempleRoom(room))
        {
            TempleMusic.startMusic();
        }
        else
        {
            TempleMusic.pauseMusic();
        }
        
        if (isOverworldRoom(room))
        {
            Overworld.startMusic();
        }
        else
        {
            Overworld.pauseMusic();
        }
    }
    
    // Dictates when the Overworld music should be played
    private boolean isOverworldRoom(int playerRoom)
    {
        return playerRoom >= 1 && playerRoom <= 3;
    }
    
    // Dictates when the Temple Room music should be played
    private boolean isTempleRoom(int playerRoom)
    {
        return playerRoom >= 4 && playerRoom <= 7;
    }
    
    // Helper data class for room mob spawn configuration
    public static class SpawnConfig {
        public final int minCount;
        public final int maxCount;
        public final double infarctusWeight;
        public final double soldierWeight;
        public final double eliteGuardWeight;

        public SpawnConfig(int minCount, int maxCount, double infarctusWeight, double soldierWeight, double eliteGuardWeight) {
            this.minCount = minCount;
            this.maxCount = maxCount;
            this.infarctusWeight = infarctusWeight;
            this.soldierWeight = soldierWeight;
            this.eliteGuardWeight = eliteGuardWeight;
        }
    }

    // Returns spawn rate configuration and enemy weight distribution based on room number
    public static SpawnConfig getRoomSpawnConfig(int room) {
        switch (room) {
            case 2: // Starter Forest: 3 to 6 Infarctus only
                return new SpawnConfig(3, 6, 1.0, 0.0, 0.0);

            case 3: // NPC Room: Exactly 3 Infarctus
                return new SpawnConfig(3, 3, 1.0, 0.0, 0.0);

            case 4: // Soldier Room: 5 to 7 mobs, primarily Soldiers, Infarctus > Elite Guard
                return new SpawnConfig(5, 7, 0.30, 0.60, 0.10);

            case 5: // Artifact Room: Completely empty
                return new SpawnConfig(0, 0, 0.0, 0.0, 0.0);

            case 6: // Elite Guard Room: 5 to 7 mobs
                return new SpawnConfig(5, 7, 0.20, 0.30, 0.50);

            case 7: // Puzzle Room: 5 to 7 mobs, introduces Elite Guards
                return new SpawnConfig(5, 7, 0.35, 0.35, 0.30);

            case 8: // Boss Room: 5 to 7 mobs, primarily Elite Guards, with Infarctus and Soldiers
                return new SpawnConfig(5, 7, 0.15, 0.25, 0.60);

            default: // Rooms 0, 1 (Title Screen, Bedroom, etc.)
                return new SpawnConfig(0, 0, 0.0, 0.0, 0.0);
        }
    }

    // This dictates the specific background for the rooms
    public Image getMap(int playerRoom)
    {
        switch (playerRoom)
        {
            case 0:
                return BACKGROUND_0;
                
            case 1:
                return BACKGROUND_1;
            
            case 2:
                return BACKGROUND_2;
                
            case 3:
                return BACKGROUND_3;
            
            case 4:
                return BACKGROUND_4;
                
            case 5:
                return BACKGROUND_5;
            
            case 6:
                return BACKGROUND_6;
                
            case 7:
                return BACKGROUND_7;
            
            case 8:
                return BACKGROUND_8;
     
            default:
                return null;
        }
    }
    
    public String detectIfWall(int playerRoom, int playerX, int playerY)
    {
        switch (playerRoom)
        {
            case 0: // Room 0 (Main Screen).
                if (playerX > 1) return "North";
                else if (playerX > 1) return "West";
                else if (playerX > 1) return "South";
                else if (playerX > 1) return "East";
        
            case 1: // Room 1.
                if (playerX > 1) return "North";
                else if (playerX > 1) return "West";
                else if (playerX > 1) return "South";
                else if (playerX > 1) return "East";
    
            case 2: // Room 2.
                if (playerX > 1) return "North";
                else if (playerX > 1) return "West";
                else if (playerX > 1) return "South";
                else if (playerX > 1) return "East";
        
            case 3: // Room 3.
                if (playerX > 1) return "North";
                else if (playerX > 1) return "West";
                else if (playerX > 1) return "South";
                else if (playerX > 1) return "East";
    
            case 4: // Room 4.
                if (playerX > 1) return "North";
                else if (playerX > 1) return "West";
                else if (playerX > 1) return "South";
                else if (playerX > 1) return "East";
        
            case 5: // Room 5.
                if (playerX > 1) return "North";
                else if (playerX > 1) return "West";
                else if (playerX > 1) return "South";
                else if (playerX > 1) return "East";
    
            case 6: // Room 6.
                if (playerX > 1) return "North";
                else if (playerX > 1) return "West";
                else if (playerX > 1) return "South";
                else if (playerX > 1) return "East";
        
            case 7: // Room 7.
                if (playerX > 1) return "North";
                else if (playerX > 1) return "West";
                else if (playerX > 1) return "South";
                else if (playerX > 1) return "East";
    
            case 8: // Room 8.
                if (playerX > 1) return "North";
                else if (playerX > 1) return "West";
                else if (playerX > 1) return "South";
                else if (playerX > 1) return "East";
    
            default:
                return null;
        }
    }
    
    // Plays music as the background gets drawn
    public void draw(Graphics g)
    {
        updateRoomMusic();
        g.drawImage(getMap(Player.getRoom()), 0, 171, null);
    }

    // Unused but necessary method for ImageObserver.
    @Override
    public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) 
    {
        return false;
    }
}