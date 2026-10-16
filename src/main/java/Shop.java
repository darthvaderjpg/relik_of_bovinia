// Author : Ofer Koren
// Date   : 5/14/2026
// Rev    : 01
// Notes  : 

import java.awt.Image;
//import javax.swing.ImageIcon;

public class Shop extends NPC
{
	// Fields.
	
	private final static Image SHOP_KEEPER_MODEL = Tools.getNewImageIcon("SHOP_KEEPER_MODEL.gif");
	private final static Image SHOP_KEEPER_INTERACT_MODEL = Tools.getNewImageIcon("SHOP_KEEPER_INTERACT_MODEL.gif");
	private final static int X = 150;
	private final static int Y = 321;
	private final static int COLLISION_RADIUS = 150;
	private final static int ROOM = 3;
	private final static boolean CAN_INTERACT = false;
	private final static int OFFSET_X = 0;
	private final static int OFFSET_Y = 0;
	private static int potionStock = 99;
	
	// Constructors.
	
	public Shop() 
	{
		super(SHOP_KEEPER_MODEL, SHOP_KEEPER_INTERACT_MODEL, X, Y, COLLISION_RADIUS, ROOM, CAN_INTERACT, OFFSET_X, OFFSET_Y); 
	}
	
	public static int getPotionStock()
	{
		return potionStock;
	}
	
	public static void buyPotion()
	{
		if (potionStock >= 1 && Hotbar.getCoins() >= 5 && Dialogue.getCurrentNPC().equals("shopkeeper"))
		{
			potionStock--;
			Hotbar.addCoins(-5);
			Hotbar.increasePotionCount();
			
		}
	}
}
