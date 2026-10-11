//Name: Siddarth Iyer
//Editor: Ofer Koren
//Date: 5/14/2026
//Rev:01
//Notes:
/*
 * This class Stores everything the hotbar needs: 
 * 1. The Minimap
 * 2. The Armor Display
 * 3. The Key display
 * 4. The Health Bar
 * 5. The potion's display and functionality
 */
import java.awt.Image;
import java.awt.image.ImageObserver;
import javax.swing.ImageIcon;
import java.awt.Graphics;

// Unused Graphics
//import java.awt.Color;
//import java.io.File;
//import java.net.URL;
//import javax.swing.*;




public class Hotbar implements ImageObserver {
	
	// Player healthbar models (png).

	
	// Side and top UI models.
	private final static Image SIDE_UI_MODEL = Tools.getNewImageIcon("SIDE_UI_MODEL.gif");
	private final static Image NULL_UI_MODEL = Tools.getNewImageIcon("NULL_UI_MODEL.png");
	public final static Image POTION_MODEL = Tools.getNewImageIcon("revised-b-to-buy-potions-pixilart.png");
	
	// Mini-map models (gif).
	private final static Image MINI_MAP_ROOM_0 = Tools.getNewImageIcon("MAP_ROOM_0.gif");
	private final static Image MINI_MAP_ROOM_1 = Tools.getNewImageIcon("MAP_ROOM_1.gif");
	private final static Image MINI_MAP_ROOM_2 = Tools.getNewImageIcon("MAP_ROOM_2.gif");
	private final static Image MINI_MAP_ROOM_3 = Tools.getNewImageIcon("MAP_ROOM_3.gif");
	private final static Image MINI_MAP_ROOM_4 = Tools.getNewImageIcon("MAP_ROOM_4.gif");
	private final static Image MINI_MAP_ROOM_5 = Tools.getNewImageIcon("MAP_ROOM_5.gif");
	private final static Image MINI_MAP_ROOM_6 = Tools.getNewImageIcon("MAP_ROOM_6.gif");
	private final static Image MINI_MAP_ROOM_7 = Tools.getNewImageIcon("MAP_ROOM_7.gif");
	private final static Image MINI_MAP_ROOM_8 = Tools.getNewImageIcon("MAP_ROOM_8.gif");
	
	//Player starts out with these things. Essentially a blank slate for the player
	private static int coins = 0;
	private static int potionCount = 0;
	//private static boolean hasKey1 = false;
	//private static boolean hasKey2 = false;
	// private static final Image LOWER_UI_MODEL = (new ImageIcon("LOWER_UI_MODEL.gif")).getImage();
	
	//private HotbarSlot[] hotbar;
	private static Weapon weapon;
	private static Armor helmet;
	private static Armor chestplate;
	private static Armor leggings; 
	private static Armor boots;
	private static Armor robes;
	
	

	//The hot bar slots are an array because it's easier to store things in a list
	//Which is essentially what an array does. 
	//You put in 
	/*public Hotbar(){
		hotbar = new HotbarSlot[8];
		hotbar[0] = new HotbarSlot(x0,y0);
		hotbar[1] = new HotbarSlot(x1,y1);
		hotbar[2] = new HotbarSlot(x2,y2);
		hotbar[3] = new HotbarSlot(x3,y3);
		hotbar[4] = new HotbarSlot(x4,y4);
		hotbar[5] = new HotbarSlot(x5,y5);
		hotbar[6] = new HotbarSlot(x6,y6);
		hotbar[7] = new HotbarSlot(x7,y7);
	}
	*/
	//this is how to update the hotbar. 
	//public void updateHotbar(Item i) {
	//	if(i instanceof Weapon) {
	//		hotbar[0].updateItem(i);
	//	} else if(i instanceof Armor) {
	//		int idx = 1 + ((Armor)i).getType();
	//		hotbar[idx].updateItem(i);
	//	}else if(i instanceof Key){
	//		if(hotbar[5].getItem() != null) 
	//			hotbar[6].updateItem(i);
	//		else
	//			hotbar[5].updateItem(i);			
	//	}
	//
	//}
	
	public static int getCoins()
	{
		return coins;
	}
	
	public static Image getMapModel() 
	{
		switch (Player.getRoom())
		{
			case 0:
				return MINI_MAP_ROOM_0;
			
			case 1:
				return MINI_MAP_ROOM_1;
			
			case 2:
				return MINI_MAP_ROOM_2;
			
			case 3:
				return MINI_MAP_ROOM_3;
			
			case 4:
				return MINI_MAP_ROOM_4;
			
			case 5:
				return MINI_MAP_ROOM_5;
			
			case 6:
				return MINI_MAP_ROOM_6;
			
			case 7:
				return MINI_MAP_ROOM_7;
			
			case 8:
				return MINI_MAP_ROOM_8;
			
			default:
				return MINI_MAP_ROOM_0;
			
		}
	}
	
	public static Image getSideUIModel() 
	{
		return SIDE_UI_MODEL;
	}
	
	public static Image getNullUIModel()
	{
		return NULL_UI_MODEL;
	}
	
	// Rounds the player's health percentage to the nearest ten and returns the appropriate health bar image.
		public static Image getHealthModel(int health, int maxHealth, String type)
		{
			// Rounding and calculations. For some reason the ratios only work when I break stuff down like this.
			double ratio = (double)health / (double)maxHealth;
			int healthPercent = (int)(ratio * 100);
			int roundedHealthPercent = ((int)(healthPercent/10)) * 10;
			
			// Returns the appropriate image.
			
			Image healthbar = (new ImageIcon(type + roundedHealthPercent + "_PERCENT.png")).getImage();
			return healthbar;
		} 
	
	// Returns the number of health potions.
	public static int getPotionCount(){
		return potionCount;
	}

	public static Image getPotionModel(){
		return POTION_MODEL;
	}
	
	// Uses health potions.
	public static void usePotion() {
		if(potionCount >= 1) 
		{
			Player.setHealth(Player.getHealth() + ((Player.getMaxHealth() - Player.getHealth()) * 0.5));
			potionCount--;
		}
	}
	
	public static void increasePotionCount() {
		potionCount++;
	}
	
	public static void addCoins(int increase)
	{
		if (coins + increase > 99)
		{
			coins = 99;
		}
		else if (coins + increase < 0)
		{
			coins = 0;
		}
		else
		{
			coins += increase;
		}
	}
	
	public static void setWeapon(Weapon weapon)
	{
		Hotbar.weapon = weapon;
	}
	
	public static void setHelmet(Armor helmet)
	{
		Hotbar.helmet = helmet;
	}
	
	public static void setChestplate(Armor chestplate)
	{
		Hotbar.chestplate = chestplate;
	}
	
	public static void setLeggings(Armor leggings)
	{
		Hotbar.leggings = leggings;
	}
	
	public static void setBoots(Armor boots)
	{
		Hotbar.boots = boots;
	}
	
	public static void setRobes(Armor robes)
	{
		Hotbar.robes = robes;
	}
	
	public static Weapon getWeapon()
	{
		return weapon;
	}
	
	public static Armor getHelmet()
	{
		return helmet;
	}
	
	public static Armor getChestplate()
	{
		return chestplate;
	}
	
	public static Armor getLeggings()
	{
		return leggings;
	}
	
	public static Armor getBoots()
	{
		return boots;
	}
	
	public static Armor getRobes()
	{
		return robes;
	}
	
	public static void draw(Graphics g)
	{
		g.drawImage(Hotbar.getNullUIModel(), 0, 0, null);
		g.drawImage(Hotbar.getSideUIModel(), 800, -1, null);
		g.drawImage(Hotbar.getPotionModel(), 610, 120, null);
		g.drawImage(Hotbar.getHealthModel(Player.getHealth(), Player.getMaxHealth(), "PLAYER_HEALTH_"), 808, 208, null);
		
		if (BovisFight.getBossToken() && Player.getRoom() == 8)
		{
			g.drawImage(Hotbar.getHealthModel(GamePanel.bovis.getHealth(), GamePanel.bovis.getHealth2(), "BOVIS_HEALTH_"), 0, 0, null);
		}
		
		g.drawImage(Hotbar.getMapModel(), 804, 63, null);
	}
	
	// Unused but necessary method for ImageObserver.
	@Override
	public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) 
	{
		return false;
	}
	
}
