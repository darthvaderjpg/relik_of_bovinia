// Author : Ofer Koren
// Date   : 5/19/2026
// Rev    : 02
// Notes  : 

import java.awt.Image;

public class Dude extends NPC 
{
    // Fields.
    
    private final static Image DUDE_MODEL = Tools.getNewImageIcon("DUDE_MODEL.gif");
    private final static Image DUDE_INTERACT_MODEL = Tools.getNewImageIcon("DUDE_INTERACT_MODEL.gif");
    private final static int X = 32;
    private final static int Y = 507;
    private final static int COLLISION_RADIUS = 140;
    private final static int ROOM = 0;
    private final static boolean CAN_INTERACT = false;
    private final static int OFFSET_X = 0;
    private final static int OFFSET_Y = 0;
    
    // Constructors
    
    public Dude()
    {
        super(DUDE_MODEL, DUDE_INTERACT_MODEL, X, Y, COLLISION_RADIUS, ROOM, CAN_INTERACT, OFFSET_X, OFFSET_Y);
    }
}