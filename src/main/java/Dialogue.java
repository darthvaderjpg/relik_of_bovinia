/*
Author: Jake Jiang
Co-Editor: Ofer Koren
Date  : 05/15/26
Notes : This class will store all the dialogue images here
 		and is capable of returning which dialogue to use
 		based off of how many dialogues the player has gone through. 
 		Restarting the game will currently not reset the dialogues.
 */

import java.awt.*;
import java.awt.image.ImageObserver;

//import javax.swing.*;

public class Dialogue implements ImageObserver 
{
	// Fields.
	
	// Dude's dialogue animations (gif).
	private static final Image DUDE_DIALOGUE_ANIMATION_1 = Tools.getNewImageIcon("DUDE_DIALOGUE_ANIMATION_1.gif");
	
	// Grandpa's dialogue animations (gif).
	private static final Image GRANDPA_DIALOGUE_ANIMATION_1 = Tools.getNewImageIcon("GRANDPA_DIALOGUE_ANIMATION_1.gif"); 
	private static final Image GRANDPA_DIALOGUE_ANIMATION_2 = Tools.getNewImageIcon("GRANDPA_DIALOGUE_ANIMATION_2.gif"); 
	private static final Image GRANDPA_DIALOGUE_ANIMATION_3 = Tools.getNewImageIcon("GRANDPA_DIALOGUE_ANIMATION_3.gif"); 
	private static final Image GRANDPA_DIALOGUE_ANIMATION_4 = Tools.getNewImageIcon("GRANDPA_DIALOGUE_ANIMATION_4.gif"); 
	private static final Image GRANDPA_DIALOGUE_ANIMATION_5 = Tools.getNewImageIcon("GRANDPA_DIALOGUE_ANIMATION_5.gif"); 
	private static final Image GRANDPA_DIALOGUE_ANIMATION_6 = Tools.getNewImageIcon("GRANDPA_DIALOGUE_ANIMATION_6.gif"); 
	private static final Image GRANDPA_DIALOGUE_ANIMATION_7 = Tools.getNewImageIcon("GRANDPA_DIALOGUE_ANIMATION_7.gif"); 
	private static final Image GRANDPA_DIALOGUE_ANIMATION_8 = Tools.getNewImageIcon("GRANDPA_DIALOGUE_ANIMATION_8.gif"); 
	private static final Image GRANDPA_DIALOGUE_ANIMATION_9 = Tools.getNewImageIcon("GRANDPA_DIALOGUE_ANIMATION_9.gif"); 

	// Shop's dialogue animations (gif).
	private static final Image SHOP_DIALOGUE_ANIMATION_1 = Tools.getNewImageIcon("SHOP_DIALOGUE_ANIMATION_1.gif");
	
	// Relik's description animations (gif).
	private static final Image RELIK_DIALOGUE_ANIMATION_1 = Tools.getNewImageIcon("RELIK_DIALOGUE_ANIMATION_1.gif");
	
	// Bovis' dialogue animations (gif).
	private static final Image BOVIS_DIALOGUE_ANIMATION_1 = Tools.getNewImageIcon("BOVIS_DIALOGUE_ANIMATION_1.gif");
	private static final Image BOVIS_DIALOGUE_ANIMATION_2 = Tools.getNewImageIcon("BOVIS_DIALOGUE_ANIMATION_2.gif");
	private static final Image BOVIS_DIALOGUE_ANIMATION_3 = Tools.getNewImageIcon("BOVIS_DIALOGUE_ANIMATION_3.gif");
	private static final Image BOVIS_DIALOGUE_ANIMATION_4 = Tools.getNewImageIcon("BOVIS_DIALOGUE_ANIMATION_4.gif");
	private static final Image BOVIS_DIALOGUE_ANIMATION_5 = Tools.getNewImageIcon("BOVIS_DIALOGUE_ANIMATION_5.gif");
	
	// Error model in case anything goes wrong.
	//private final static Image ERROR = (new ImageIcon("ERROR.png")).getImage();

	private static int currentDudeLine = 0;
	private static int currentGrandpaLine = 0;
	private static int currentShopLine = 0;
	private static int currentRelikLine = 0;
	private static int currentBovisLine = 0;
	private static String currentNPC = "";
		
	// Methods.
	
	// Returns the appropriate gif given the current NPCthe player is talking to.
	public static Image getDialogueModel()
	{
		switch (currentNPC)
		{
			case "dude":
				return getDudeDialogueModel();
				
			case "grandpa":
				return getGrandpaDialogueModel();
			
			case "shopkeeper":
				return getShopDialogueModel();
				
			case "relik":
				return getRelikDialogueModel();
			
			case "bovisTalk":
				return getBovisDialogueModel();
				
			default:
				return null;
		}
	}
	
	// Increases the dialogue value by one.
	public static void transitionDialogueModel()
	{
		switch (currentNPC)
		{
			case "dude":
				currentDudeLine++;
				break;
				
			case "grandpa":
				currentGrandpaLine++;
				break;
			
			case "shopkeeper":
				currentShopLine++;
				break;
				
			case "relik":
				currentRelikLine++;
				break;
			
			case "bovisTalk":
				currentBovisLine++;
				break;
		}
	}
	
	// Returns dude's models and functionality.
	public static Image getDudeDialogueModel()
	{
		switch (currentDudeLine)
		{
			case 0:
				return null;
			
			case 1:
				Player.setMoveState(false);
				return DUDE_DIALOGUE_ANIMATION_1;
			
			default:
				Player.setMoveState(true);
				Player.moveToRoom(1);
				currentDudeLine = 0;
				currentNPC = "";
				return null;
		}
	}
	
	// Returns grandpa's models and functionality.
	public static Image getGrandpaDialogueModel() 
	{
		switch (currentGrandpaLine)
		{
			case 0:
				return null;
				
			case 1:
				Player.setMoveState(false);
				return GRANDPA_DIALOGUE_ANIMATION_1;
				
			case 2:
				return GRANDPA_DIALOGUE_ANIMATION_2;
				
			case 3:
				return GRANDPA_DIALOGUE_ANIMATION_3;
				
			case 4:
				return GRANDPA_DIALOGUE_ANIMATION_4;
				
			case 5:
				return GRANDPA_DIALOGUE_ANIMATION_5;
				
			case 6:
				return GRANDPA_DIALOGUE_ANIMATION_6;
				
			case 7:
				return GRANDPA_DIALOGUE_ANIMATION_7;
				
			case 8:
				return GRANDPA_DIALOGUE_ANIMATION_8;
			
			case 9:
				return GRANDPA_DIALOGUE_ANIMATION_9;
			default:
				Player.setMoveState(true);
				currentGrandpaLine = 0;
				currentNPC = "";
				return null;
		}
	}
	
	// Returns the shopkeeper's models and functionality.
	public static Image getShopDialogueModel()
	{
		switch (currentShopLine)
		{
			case 0:
				return null;
				
			case 1:
				Player.setMoveState(false);
				return SHOP_DIALOGUE_ANIMATION_1;
			
			default:
				Player.setMoveState(true);
				currentShopLine = 0;
				currentNPC = "";
				return null;
		}	
	}
	
	// Returns the relik's models and functionality.
	public static Image getRelikDialogueModel()
	{
		switch (currentRelikLine)
		{
			case 0:
				return null;
			
			case 1:
				Player.setMoveState(false);
				return RELIK_DIALOGUE_ANIMATION_1;
			
			default:
				Player.setMoveState(true);
				Player.moveToRoom(8);
				currentRelikLine = 0;
				currentNPC = "";
				return null;
		}
	}
	
	// Returns bovis' models and functionality.
	public static Image getBovisDialogueModel()
	{
		switch (currentBovisLine)
		{
			case 0:
				return null;
				
			case 1:
				Player.setMoveState(false);
				return BOVIS_DIALOGUE_ANIMATION_1;
				
			case 2:
				return BOVIS_DIALOGUE_ANIMATION_2;
				
			case 3:
				return BOVIS_DIALOGUE_ANIMATION_3;
				
			case 4:
				return BOVIS_DIALOGUE_ANIMATION_4;
				
			case 5:
				currentBovisLine = 6;
				return BOVIS_DIALOGUE_ANIMATION_5;	
			case 6:
				BovisFight.startBoss();
				
			default:
				Player.setMoveState(true);
				currentBovisLine = 0;
				currentNPC = "";
				return null;
		}
	}
	
	// Sets the current NPC to the parameter value.
	public static void setCurrentNPC(String currentNPC)
	{
		Dialogue.currentNPC = currentNPC;
	}
	
	// Returns the name of the current NPC the player is talking to.
	public static String getCurrentNPC()
	{
		return currentNPC;
	}
	
	// Draws the dialogue.
	public static void draw(Graphics g)
	{
		g.drawImage(Dialogue.getDialogueModel(), 0, -5, null);
	}

	// Unused but necessary method for ImageObserver.
	@Override
	public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) 
	{
		return false;
	}
}