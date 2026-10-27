// Author: Ofer Koren
// CoEditor: Jake Jiang the GOAT
// Date: 05/25/2026
// Rev: 02
// Notes: None

import java.awt.Image;

public class BovisTalk extends NPC
{
    private final static Image BOVIS_TALK_MODEL = Tools.getNewImageIcon("BOVIS_TALK_MODEL.gif");
    private final static Image BOVIS_TALK_INTERACT_MODEL = Tools.getNewImageIcon("BOVIS_TALK_INTERACT_MODEL.gif");
    private final static int X = 303;
    private final static int Y = 367;
    private final static int COLLISION_RADIUS = 150;
    private final static int ROOM = 8;
    private final static boolean CAN_INTERACT = false;
    private final static int OFFSET_X = 0;
    private final static int OFFSET_Y = 0;
    private static boolean dialogueCompletion = false;
        
    public BovisTalk()
    {
        super(BOVIS_TALK_MODEL, BOVIS_TALK_INTERACT_MODEL, X, Y, COLLISION_RADIUS, ROOM, CAN_INTERACT, OFFSET_X, OFFSET_Y); 
    }
    
    @Override
    public Image getModel()
    {
        
        if (BovisFight.getBossToken())
        {
            return null;
        }
        return super.getModel();
    }
    
    public static boolean getDialogueCompletion() {
        return dialogueCompletion;
    }
    
    public static void setDialogueCompleted() {
        dialogueCompletion = true;
    }
}