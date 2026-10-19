// Author : Ofer Koren / Siddarth Iyer
// Date   : 10/18/2026
// Rev    : 03
// Notes  : Lock object positioned in Room 4 at (260, 600) requiring templeKey to unlock.

import java.awt.Image;

public class Lock extends NPC 
{
    private final static Image LOCK_MODEL = Tools.getNewImageIcon("Lock.png");
    private final static Image LOCK_INTERACT_MODEL = Tools.getNewImageIcon("Lock Interact.png");
    private final static int X = 370;
    private final static int Y = 670;
    private final static int COLLISION_RADIUS = 120;
    private final static int ROOM = 4;
    private final static boolean CAN_INTERACT = false;
    private final static int OFFSET_X = 0;
    private final static int OFFSET_Y = 0;

    private static boolean unlocked = false;

    public Lock()
    {
        super(LOCK_MODEL, LOCK_INTERACT_MODEL, X, Y, COLLISION_RADIUS, ROOM, CAN_INTERACT, OFFSET_X, OFFSET_Y);
    }

    public static void setUnlocked(boolean status)
    {
        unlocked = status;
    }

    public static boolean isUnlocked()
    {
        return unlocked;
    }

    @Override
    public Image getModel()
    {
        if (unlocked)
        {
            return null; // Lock vanishes once unlocked
        }
        return super.getModel();
    }
}