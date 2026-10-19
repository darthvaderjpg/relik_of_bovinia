// Author : Ofer Koren / Siddarth Iyer
// Date   : 10/18/2026
// Rev    : 06
// Notes  : KeyNPC class with disappearance support after collection.

import java.awt.Image;

public class KeyNPC extends NPC 
{
    private final static Image KEY_MODEL = Tools.getNewImageIcon("TEMPLE_KEY_MODEL.gif");
    private final static Image KEY_INTERACT_MODEL = Tools.getNewImageIcon("TEMPLE_KEY_INTERACT_MODEL.gif");
    private final static int X = 380;
    private final static int Y = 300;
    private final static int COLLISION_RADIUS = 120;
    private final static int ROOM = 7;
    private final static boolean CAN_INTERACT = false;
    private final static int OFFSET_X = 0;
    private final static int OFFSET_Y = 0;

    private static boolean collected = false;

    public KeyNPC()
    {
        super(KEY_MODEL, KEY_INTERACT_MODEL, X, Y, COLLISION_RADIUS, ROOM, CAN_INTERACT, OFFSET_X, OFFSET_Y);
    }

    public static void setCollected(boolean status)
    {
        collected = status;
    }

    public static boolean isCollected()
    {
        return collected;
    }

    @Override
    public Image getModel()
    {
        if (collected)
        {
            return null; // Disappears once collected!
        }
        return super.getModel();
    }

    public void update()
    {
        if (!collected)
        {
            testInteraction(Player.getX(), Player.getY(), getX(), getY(), getCollisionRadius(), "keynpc");
        }
    }
}