// Author : Ofer Koren
// Date   : 5/19/2026
// Rev    : 01
// Notes  : 

import java.awt.Image;
//import javax.swing.ImageIcon;

public class KeyNPC extends NPC 
{
	// Fields.
	
	private final static Image KEY_MODEL = Tools.getNewImageIcon("TEMPLE_KEY_MODEL.gif");
	private final static Image KEY_INTERACT_MODEL = Tools.getNewImageIcon("TEMPLE_KEY_INTERACT_MODEL.gif");
	private final static int X = 380;
	private final static int Y = 300;
	private final static int COLLISION_RADIUS = 100;
	private final static int ROOM = 7;
	private final static boolean CAN_INTERACT = false;
	private final static int OFFSET_X = 0;
	private final static int OFFSET_Y = 0;
	
	// Constructors
	
	public KeyNPC()
	{
		super(KEY_MODEL, KEY_INTERACT_MODEL, X, Y, COLLISION_RADIUS, ROOM, CAN_INTERACT, OFFSET_X, OFFSET_Y);
	}

	

}