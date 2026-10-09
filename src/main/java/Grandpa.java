// Author : Ofer Koren
// Date   : 5/14/2026
// Rev    : 01
// Notes  :

import java.awt.Image;
import javax.swing.ImageIcon;

public class Grandpa extends NPC
{
	// Fields.
	
	private final static Image GRANDPA_MODEL = (new ImageIcon("GRANDPA_MODEL.gif")).getImage();
	private final static Image GRANDPA_INTERACT_MODEL = (new ImageIcon("GRANDPA_INTERACT_MODEL.gif")).getImage();
	private final static int X = 160;
	private final static int Y = 223;
	private final static int COLLISION_RADIUS = 120;
	private final static int ROOM = 1;
	private final static boolean CAN_INTERACT = false;
	private final static int OFFSET_X = 0;
	private final static int OFFSET_Y = 0;
	
	// Constructors.
	
	public Grandpa()
	{
		super(GRANDPA_MODEL, GRANDPA_INTERACT_MODEL, X, Y, COLLISION_RADIUS, ROOM, CAN_INTERACT, OFFSET_X, OFFSET_Y); 
	}
}
