// Author : Ofer Koren
// Date   : 5/08/2026
// Rev    : 01
// Notes  : This class is responsible for the background.

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;

//import javax.swing.ImageIcon;

public class Map implements ImageObserver 
{
	private final static Image BACKGROUND_0 = Tools.getNewImageIcon("BACKGROUND_0.png"); // Title Screen
	private final static Image BACKGROUND_1 = Tools.getNewImageIcon("BACKGROUND_1.gif"); //Bedroom
	private final static Image BACKGROUND_2 = Tools.getNewImageIcon("starter-forest-pixilart.png"); //Starter Forest
	private final static Image BACKGROUND_3 = Tools.getNewImageIcon("npc-room-pixilart (1).png"); // NPC Room
	private final static Image BACKGROUND_4 = Tools.getNewImageIcon("TempRoom4.png"); // Soldier Room
	private final static Image BACKGROUND_5 = Tools.getNewImageIcon("snowy-background-place-holder-pixilart.png"); // Artifact Room
	private final static Image BACKGROUND_6 = Tools.getNewImageIcon("TempRoom6.png"); // Elite Guard Room
	private final static Image BACKGROUND_7 = Tools.getNewImageIcon("TempRoom3.png"); // Puzzle Room with key
	private final static Image BACKGROUND_8 = Tools.getNewImageIcon("BACKGROUND_8.png"); // Boos Room
	
	//Different types of music. This is in Map class because we want it to play based on a certain map. 
	private GameMusic bossMusic    = new GameMusic("BOVINE.wav");
	private GameMusic TempleMusic = new GameMusic("Sorrow.wav");
	private GameMusic Overworld = new GameMusic("rumination.wav");
	
	private static boolean bossMusicEnabled = false;
	
	public static void enableBossMusic()
	{
		bossMusicEnabled = true;
	}
	
	//This delegates when the Boss Fight music should or shouldn't be played
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
	
	//Dictates when the Overworld music should be played
	private boolean isOverworldRoom(int playerRoom)
	{
		return playerRoom >= 1 && playerRoom <= 3;
	}
	
	//Dicates when the Temple Room music should be played
	private boolean isTempleRoom(int playerRoom)
	{
		return playerRoom >= 4 && playerRoom <= 7;
	}
	
	//This dictates the specific background for the rooms
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
	
	// This method takes the parameters of the player's current room and coordinates, and outputs the direction the wall is
	// in. For example:
	// X -> Player
	// | -> Wall
	//        |
	//        |
	//       X|
	//        |
	//        |
	// Would return "East" since the wall is east of the player.
	// Keep in mind that this method detects extra objects in a room (like a bed, for example), not already existing walls 
	// since Player's doWallCollision() method already handles that.
	public String detectIfWall(int playerRoom, int playerX, int playerY)
	{
		switch (playerRoom)
		{
			case 0: // Room 0 (Main Screen).
				if (playerX > 1) // Insert condition here (This is an example.).
				{
					return "North";
				}
				else if (playerX > 1) // Insert condition here (This is an example.).
				{
					return "West";
				}
				else if (playerX > 1) // Insert condition here (This is an example.).
				{
					return "South";
				}
				else if (playerX > 1) // Insert condition here (This is an example.).
				{
					return "East";
				}
		
			case 1: // Room 1.
				if (playerX > 1) // Insert condition here (This is an example.).
				{
					return "North";
				}
				else if (playerX > 1) // Insert condition here (This is an example.).
				{
					return "West";
				}
				else if (playerX > 1) // Insert condition here (This is an example.).
				{
					return "South";
				}
				else if (playerX > 1) // Insert condition here (This is an example.).
				{
					return "East";
				}
	
			case 2: // Room 2.
				if (playerX > 1) // Insert condition here (This is an example.).
				{
					return "North";
				}
				else if (playerX > 1) // Insert condition here (This is an example.).
				{
					return "West";
				}
				else if (playerX > 1) // Insert condition here (This is an example.).
				{
					return "South";
				}
				else if (playerX > 1) // Insert condition here (This is an example.).
				{
					return "East";
				}
		
			case 3: // Room 3.
				if (playerX > 1) // Insert condition here (This is an example.).
				{
					return "North";
				}
				else if (playerX > 1) // Insert condition here (This is an example.).
				{
					return "West";
				}
				else if (playerX > 1) // Insert condition here (This is an example.).
				{
					return "South";
				}
				else if (playerX > 1) // Insert condition here (This is an example.).
				{
					return "East";
				}
	
			case 4: // Room 4.
				if (playerX > 1) // Insert condition here (This is an example.).
				{
					return "North";
				}
				else if (playerX > 1) // Insert condition here (This is an example.).
				{
					return "West";
				}
				else if (playerX > 1) // Insert condition here (This is an example.).
				{
					return "South";
				}
				else if (playerX > 1) // Insert condition here (This is an example.).
				{
					return "East";
				}
		
			case 5: // Room 5.
				if (playerX > 1) // Insert condition here (This is an example.).
				{
					return "North";
				}
				else if (playerX > 1) // Insert condition here (This is an example.).
				{
					return "West";
				}
				else if (playerX > 1) // Insert condition here (This is an example.).
				{
					return "South";
				}
				else if (playerX > 1) // Insert condition here (This is an example.).
				{
					return "East";
				}
	
			case 6: // Room 6.
				if (playerX > 1) // Insert condition here (This is an example.).
				{
					return "North";
				}
				else if (playerX > 1) // Insert condition here (This is an example.).
				{
					return "West";
				}
				else if (playerX > 1) // Insert condition here (This is an example.).
				{
					return "South";
				}
				else if (playerX > 1) // Insert condition here (This is an example.).
				{
					return "East";
				}
		
			case 7: // Room 7.
				if (playerX > 1) // Insert condition here (This is an example.).
				{
					return "North";
				}
				else if (playerX > 1) // Insert condition here (This is an example.).
				{
					return "West";
				}
				else if (playerX > 1) // Insert condition here (This is an example.).
				{
					return "South";
				}
				else if (playerX > 1) // Insert condition here (This is an example.).
				{
					return "East";
				}
	
			case 8: // Room 8.
				if (playerX > 1) // Insert condition here (This is an example.).
				{
					return "North";
				}
				else if (playerX > 1) // Insert condition here (This is an example.).
				{
					return "West";
				}
				else if (playerX > 1) // Insert condition here (This is an example.).
				{
					return "South";
				}
				else if (playerX > 1) // Insert condition here (This is an example.).
				{
					return "East";
				}
	
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