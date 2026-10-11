// Author : Ofer Koren
// Co-Editor: Jake Jiang
// Date   : 5/14/2026
// Rev    : 01
// Notes  : 

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;

public class NPC implements ImageObserver 
{
	// Fields.
	
	private int x = 0;
	private int y = 0;
	private int collisionRadius = 0;
	private int room = 0;
	private boolean canInteract = false;
	private Image model;
	private Image interactModel;
	private int offsetX = 0;
	private int offsetY = 0;
	
	// Constructors.
	
	public NPC(Image model, Image interactModel, int x, int y, int collisionRadius, int room, boolean canInteract, int offsetX, int offsetY)
	{
		this.model = model;
		this.interactModel = interactModel;
		this.x = x;
		this.y = y;
		this.collisionRadius = collisionRadius;
		this.room = room;
		this.canInteract = canInteract;
		this.offsetX = offsetX;
		this.offsetY = offsetY;
	}
	
	// Methods.
	
	// Returns the specific type of NPC model/animation depending if it is interactable or not.
	public Image getModel()
	{
		if (room == Player.getRoom())
		{
			if (canInteract)
			{
				return interactModel;
			}
			else
			{
				return model;
			}
		}
		else
		{
			return null;
		}
	}
	
	// Sets the NPC's X position.
	public void setX(int x) {
		this.x = x;
	}

	// Returns the NPC's current X position.
	public int getX()
	{
		return x;
	}
	
	// Returns the NPC's current Y position.
	public int getY()
	{
		return y;
	}
	
	// Sets the NPC's Interactability
	public void setCanInteract(boolean interactability) {
		canInteract = interactability;
	}
	// Returns the NPC's interaction collision radius.
	public int getCollisionRadius()
	{
		return collisionRadius;
	}
	
	// This method makes the NPC interactable if the player is within range and not if otherwise.
	public void testInteraction(int playerX, int playerY, int npcX, int npcY, int collisionRadius, String npcType)
	{
		// This uses the traditional distance formula.
		double term1 = npcX - playerX;
		double term2 = npcY - playerY;
		double squaredTerm1 = Math.pow(term1, 2);
		double squaredTerm2 = Math.pow(term2, 2);
		int distance = (int)Math.pow(squaredTerm1 + squaredTerm2, 0.5);
		
		if (distance <= collisionRadius && room == Player.getRoom())
		{
			canInteract = true;
		}
		else
		{
			canInteract = false;
		}
		
		if (canInteract && Main.getIsPressingE())
		{
			Dialogue.setCurrentNPC(npcType); 
		}
	}
	
	public void draw(Graphics g)
	{
		g.drawImage(getModel(), getX() + offsetX, getY() + offsetY, null);
	}

	// Unused but necessary method for ImageObserver.
	@Override
	public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) 
	{
		return false;
	}
}