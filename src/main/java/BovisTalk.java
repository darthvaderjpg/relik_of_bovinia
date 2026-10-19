// Author: Ofer Koren
//CoEditor: Jake Jiang the GOAT
// Date: 05/25/2026
// Rev: 01
// Notes: this class withholds within it the data for bovis to talk right before the boss fight and is a child of NPC

import java.awt.Image;
//import javax.swing.ImageIcon;

public class BovisTalk extends NPC
{
	// Fields.
	
	private final static Image BOVIS_TALK_MODEL = Tools.getNewImageIcon("BOVIS_TALK_MODEL.gif");
	private final static Image BOVIS_TALK_INTERACT_MODEL = Tools.getNewImageIcon("BOVIS_TALK_INTERACT_MODEL.gif");
	private final static int X = 303; //not final to teleport NPS out of walking range after dialogue
	private final static int Y = 367;
	private final static int COLLISION_RADIUS = 150;
	private final static int ROOM = 8;
	private final static boolean CAN_INTERACT = false;
	private final static int OFFSET_X = 0;
	private final static int OFFSET_Y = 0;
	private static boolean dialogueCompletion = false;
		
	// Constructors.
		
	public BovisTalk()
	{
		super(BOVIS_TALK_MODEL, BOVIS_TALK_INTERACT_MODEL, X, Y, COLLISION_RADIUS, ROOM, CAN_INTERACT, OFFSET_X, OFFSET_Y); 
	}
	
	
	// gets if the dialogue is completed
	public static boolean getDialogueCompletion() {
		return dialogueCompletion;
	}
	
	// sets the dialogue to being completed
	public static void setDialogueCompleted() {
		dialogueCompletion = true;
	}
	
}
