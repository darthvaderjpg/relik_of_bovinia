//Name: Siddarth Iyer
//Date: 5/3/26
//Rev:01
//Notes:
//This is the class that stores all the items as images. 
// Basically if they have the images, that's how they'll access the items. 
//import javax.swing.*;
//import java.awt.Graphics;

import java.awt.Image;
import java.awt.image.ImageObserver;
import java.util.Random;

/* 
 * This class will store everything that is needed
 *
 * 
*/

/* 

//This is an abstract class because Item doesn't really know about what the Armor or Weapon's functionality and purpose are. ONly Weapon and Armor know that
public abstract class Item implements ImageObserver {
	
	    //Items
	public static final Image   ARTIFACT = Tools.getNewImageIcon("-----"); // <- need ofer to import the artifact into the folder
	public static final Image   REJUVINATING_SPIRIT = Tools.getNewImageIcon("rejuvenating-spirit-pixilart.png");
	
	public static final Image   CHEST = Tools.getNewImageIcon("chest-pixilart.png");
	
	
	private Image image;
	private int factor;
	private String type = "";
	
	private static Random random = new Random();
	
	
	public Item(Image image, int factor, String type) 
	{
		this.image = image;
		this.factor = factor;
		this.type = type;
	}
	
	public Image getImage()
	{
		return image;
	}
	
	public int getFactor()
	{
		return factor;
	}
	
	public String getType()
	{
		return type;
	}
	
	public int getX()
	{
		switch (type)
		{
			case "Weapon":
				return 0;
			
			case "Helmet":
				return 20;
				
			case "Chestplate":
				return 40;
				
			case "Leggings":
				return 60;
				
			case "Boots":
				return 80;
				
			case "Robes":
				return 90;
				
			default:
				return 0;
			
		}
	}
	
	public int getY()
	{
		switch (type)
		{
			case "Weapon":
				return 0;
			
			case "Helmet":
				return 20;
				
			case "Chestplate":
				return 40;
				
			case "Leggings":
				return 60;
				
			case "Boots":
				return 80;
				
			case "Robes":
				return 90;
				
			default:
				return 0;
			
		}
	}

	//Determines the drop rates 
	
	 //Infarctus Item Drop Rates
	 public static void getDroppedInfarctus() {
		 
		double roll1 = random.nextDouble();
		double roll2 = random.nextDouble();
		double roll3 = random.nextDouble();
		double roll4 = random.nextDouble();
			
			if (roll1 < 0.75) {
			    Hotbar.setLeggings(Armor.explorerPants);
			} else if (roll2 < 0.75) {
	            Hotbar.setBoots(Armor.dustyBoots);
			} else if (roll3 < 0.75) {
	            Hotbar.addCoins(1);
			} else if (roll4 < 0.15){
				Hotbar.setWeapon(Weapon.mosquitoSword);
			}
			
			Hotbar.addCoins(1);
	}	        

	 //Soldier Item Drop Rates
	public static void getDroppedSoldier() {
		
		double roll1 = random.nextDouble();
		double roll2 = random.nextDouble();
		double roll3 = random.nextDouble();
		double roll4 = random.nextDouble();	
	
		
		if (roll1 < 0.5) {
		    Hotbar.setWeapon(Weapon.soldierSword);
		} else if (roll2 < 0.5) {
            Hotbar.setBoots(Armor.soldierBoots);
		} else if (roll3 < 0.5) {
            Hotbar.setChestplate(Armor.soldierChestplate);
		} else if (roll4 < 0.5) {
            Hotbar.setHelmet(Armor.soldierHelm);
        }
		
		Hotbar.addCoins(3);
	}
		 //Elite Guard Item Drop rates
	public static void getDroppedEliteGuard() {
		 double roll1 = random.nextDouble();
		 double roll2 = random.nextDouble();
		 double roll3 = random.nextDouble();
		 double roll4 = random.nextDouble();
		 double roll5 = random.nextDouble();
		
		 
			if (roll1 < 0.25) {
			    Hotbar.setWeapon(Weapon.mirrorSword);
			} else if (roll2 < 0.25) {
	            Hotbar.setBoots(Armor.eliteBoots);
			} else if (roll3 < 0.25) {
	            Hotbar.setChestplate(Armor.eliteChestplate);
			} else if (roll4 < 0.25) {
	            Hotbar.setRobes(Armor.eliteRobeBottoms);
			} else if (roll5 < 0.05) {
				Hotbar.setWeapon(Weapon.greatClub);
			}
			
			Hotbar.addCoins(5);
	}
	
	// Unused but necessary method for ImageObserver.
	@Override
	public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) 
	{
		return false;
	}
}
	*/
//Name: Siddarth Iyer
//Date: 10/13/2026
//Rev:03
//Notes: Base class for all items in Relik of Bovinia. Handles asset images, positioning offsets, and enemy drop routing.

import java.awt.Image;
import java.awt.image.ImageObserver;
import java.util.Random;

public abstract class Item implements ImageObserver {
    
    // Items
    public static final Image ARTIFACT = Tools.getNewImageIcon("-----");
    public static final Image REJUVINATING_SPIRIT = Tools.getNewImageIcon("rejuvenating-spirit-pixilart.png");
    public static final Image CHEST = Tools.getNewImageIcon("chest-pixilart.png");
    
    private Image image;
    private int factor;
    private String type = "";
    
    private static Random random = new Random();
    
    // Constructor 1: Full initialization (Image, factor, String type)
    public Item(Image image, int factor, String type) {
        this.image = image;
        this.factor = factor;
        this.type = type;
    }

    // Constructor 2: Standard (String type, Image image)
    public Item(String type, Image image) {
        this.type = type;
        this.image = image;
        this.factor = 1;
    }
    
    public Image getImage() {
        return image;
    }
    
    public int getFactor() {
        return factor;
    }
    
    public String getType() {
        return type;
    }
    
    public int getX() {
        switch (type) {
            case "Weapon": return 0;
            case "Helmet": return 20;
            case "Chestplate": return 40;
            case "Leggings": return 60;
            case "Boots": return 80;
            case "Robes": return 90;
            default: return 0;
        }
    }
    
    public int getY() {
        switch (type) {
            case "Weapon": return 0;
            case "Helmet": return 20;
            case "Chestplate": return 40;
            case "Leggings": return 60;
            case "Boots": return 80;
            case "Robes": return 90;
            default: return 0;
        }
    }

    // --- Enemy Drop Handlers that route directly into Hotbar / Inventory / Coins ---
    
    // Infarctus Item Drop Rates
    public static void getDroppedInfarctus() {
        double roll1 = random.nextDouble();
        double roll2 = random.nextDouble();
        double roll3 = random.nextDouble();
        double roll4 = random.nextDouble();
            
        if (roll1 < 0.75) {
            Hotbar.updateHotbar(Armor.explorerPants);
        } else if (roll2 < 0.75) {
            Hotbar.updateHotbar(Armor.dustyBoots);
        } else if (roll3 < 0.75) {
            Hotbar.addCoins(1);
        } else if (roll4 < 0.15) {
            Hotbar.updateHotbar(Weapon.mosquitoSword);
        }
        
        Hotbar.addCoins(1);
    }           

    // Soldier Item Drop Rates
    public static void getDroppedSoldier() {
        double roll1 = random.nextDouble();
        double roll2 = random.nextDouble();
        double roll3 = random.nextDouble();
        double roll4 = random.nextDouble(); 
    
        if (roll1 < 0.5) {
            Hotbar.updateHotbar(Weapon.soldierSword);
        } else if (roll2 < 0.5) {
            Hotbar.updateHotbar(Armor.soldierBoots);
        } else if (roll3 < 0.5) {
            Hotbar.updateHotbar(Armor.soldierChestplate);
        } else if (roll4 < 0.5) {
            Hotbar.updateHotbar(Armor.soldierHelm);
        }
        
        Hotbar.addCoins(3);
    }

    // Elite Guard Item Drop rates
    public static void getDroppedEliteGuard() {
        double roll1 = random.nextDouble();
        double roll2 = random.nextDouble();
        double roll3 = random.nextDouble();
        double roll4 = random.nextDouble();
        double roll5 = random.nextDouble();
        
        if (roll1 < 0.25) {
            Hotbar.updateHotbar(Weapon.mirrorSword);
        } else if (roll2 < 0.25) {
            Hotbar.updateHotbar(Armor.eliteBoots);
        } else if (roll3 < 0.25) {
            Hotbar.updateHotbar(Armor.eliteChestplate);
        } else if (roll4 < 0.25) {
            Hotbar.updateHotbar(Armor.eliteRobeBottoms);
        } else if (roll5 < 0.05) {
            Hotbar.updateHotbar(Weapon.greatClub);
        }
        
        Hotbar.addCoins(5);
    }
    
    @Override
    public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
        return false;
    }
}