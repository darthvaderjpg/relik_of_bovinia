// Author : Ofer Koren
// Date   : 5/19/2026
// Rev    : 01
// Notes  : 

import java.awt.Image;
//import javax.swing.ImageIcon;

public class Relik extends NPC 
{
	// Fields.
	
	private final static Image RELIK_MODEL = Tools.getNewImageIcon("RELIK_MODEL.gif");
	private final static Image RELIK_INTERACT_MODEL = Tools.getNewImageIcon("RELIK_INTERACT_MODEL.gif");
	private final static int X = 400;
	private final static int Y = 300;
	private final static int COLLISION_RADIUS = 100;
	private final static int ROOM = 5;
	private final static boolean CAN_INTERACT = false;
	private final static int OFFSET_X = -25;
	private final static int OFFSET_Y = -25;
	
	// Constructors
	
	public Relik()
	{
		super(RELIK_MODEL, RELIK_INTERACT_MODEL, X, Y, COLLISION_RADIUS, ROOM, CAN_INTERACT, OFFSET_X, OFFSET_Y);
	}
}