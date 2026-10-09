//Name: Siddarth Iyer
//Date: 4/30/26
//Rev:01
//Notes: This is the class that holds all the armor
//They're all objects because they're really similar

//MESSAGE TO THE CODERS! 
//FIRST OF ALL WE NEED TO MAKE SEPARATE SLOTS
//EACH PIECE OF ARMOR WILL BE TIED TO A SLOT




import javax.swing.*;
import java.awt.Graphics;
import java.awt.Image;

public class Armor extends Item{

	private int hitPoints;
	
	
	//Starter Gear Images
	public static final Image 	 CRACKED_HELMET = (new ImageIcon("worn-out-helmet-pixilart.png")).getImage();
	public static final Image 	 WORN_CLOAK = (new ImageIcon("old-cloak-pixilart.png")).getImage();
	public static final Image    EXPLORER_SWEATER = (new ImageIcon("old-explorer's-sweater-pixilart.png")).getImage();
	public static final Image    EXPLORER_PANTS = (new ImageIcon("protected-long-pants-pixilart.png")).getImage();
	public static final Image    DUSTY_BOOTS = (new ImageIcon("dusty-boots-pixilart.png")).getImage();
	
    //Soldier Gear Images
	public static final Image    SOLDIER_HELMET = (new ImageIcon("soldier-helmet-pixilart-gif.gif")).getImage();
	public static final Image    SOLDIER_CHESTPLATE = (new ImageIcon("soldier-chestplate-pixilart.png")).getImage();
	public static final Image    SOLDIER_LEGGINGS = (new ImageIcon("soldier-leggings-pixilart.png")).getImage();
	public static final Image    SOLDIER_BOOTS = (new ImageIcon("soldier-boots-pixilart.png")).getImage();
	
	//Elite Guard Gear Images
	public static final Image    ELITE_GUARD_HELMET = (new ImageIcon("elite-helmet-gif-pixilart.gif")).getImage();
	public static final Image    ELITE_GUARD_CHESTPLATE = (new ImageIcon("(newer)elite-guard-chestplate-pixilart.png")).getImage();
	public static final Image    ELITE_GUARD_ROBE_BOTTOMS = (new ImageIcon("elite-guard-leggings.png")).getImage();
	public static final Image    ELITE_GUARD_BOOTS = (new ImageIcon("elite-guard-boots-pixilart.png")).getImage();
	
	// Artifact Gear Images
	public static final Image    ARTIFACT_HELMET = (new ImageIcon("-------")).getImage();  // <- Make sure to complete Elite Guard Helmet I left a mark to I can remember
	public static final Image    ARTIFACT_CHESTPLATE = (new ImageIcon("artifact-chestplate-pixilart.png")).getImage();
	public static final Image    ARTIFACT_ROBE_BOTTOMS = (new ImageIcon("artifact-leggings-pixilart.png")).getImage();
	public static final Image    ARTIFACT_BOOTS = (new ImageIcon("artifact-boots-pixilart.png")).getImage();
	
	public  final static Image    BOVINE_HELMET = (new ImageIcon("animal-helmet-pixilart.png")).getImage();
	public  final static Image    AZURE_SHROUD = (new ImageIcon("new-cloak-pixilart.png")).getImage();
	
	private String type;
	//The type is not just Armor but the different kinds of armor like chestplate, helmet, etc. 
	//Hit points is the amount of extra health that the armor adds to the player's health
	
public Armor(Image Armortype, int hitPoints, String type) {
		super(Armortype, hitPoints, type);
	}
	
	//Beginner Clothes Set


	
	static Armor crackedHelmet = new Armor(CRACKED_HELMET, 2, "Helmet");
	
	static Armor wornCloak = new Armor(WORN_CLOAK, 2, "Robes");
	
	static Armor explorerSweater = new Armor(EXPLORER_SWEATER, 2, "Chestplate");
	
	static Armor explorerPants= new Armor(EXPLORER_PANTS, 2, "Leggings");
	
	static Armor dustyBoots = new Armor(DUSTY_BOOTS, 2, "Boots");
	
	//Soldier Armor Set
	static Armor soldierHelm = new Armor(SOLDIER_HELMET, 7, "Helmet");
	
	static Armor soldierChestplate = new Armor(SOLDIER_CHESTPLATE, 7, "Chestplate");
	
	static Armor soldierLeggings = new Armor(SOLDIER_LEGGINGS,7, "Leggings");
	
	static Armor soldierBoots = new Armor(SOLDIER_BOOTS,7, "Boots");
	
	
	
	//Elite Armor set
	static Armor eliteHelmet = new Armor(ELITE_GUARD_HELMET, 10, "Helmet");
	
	static Armor eliteChestplate = new Armor(ELITE_GUARD_CHESTPLATE, 10, "Chestplate");
	
	static Armor eliteRobeBottoms = new Armor(ELITE_GUARD_ROBE_BOTTOMS, 10, "Leggings");
	
	static Armor eliteBoots = new Armor(ELITE_GUARD_BOOTS, 10, "Boots");
	
	
	
	//Artifact-Infused Armor Set
	static Armor artifactHelmet = new Armor(ARTIFACT_HELMET, 50, "Helmet");
	
	static Armor artifactChestplate = new Armor(ARTIFACT_CHESTPLATE, 50, "Chestplate");
	
	static Armor artifactRobeBottoms = new Armor(ARTIFACT_ROBE_BOTTOMS, 50, "Leggings");
	
	static Armor artifactBoots = new Armor(ARTIFACT_BOOTS, 50, "Boots");
	
	
	
	//Special items:
	static Armor AnimalHelmet = new Armor(BOVINE_HELMET, 5, "Helmet");
	
	static Armor azureShroud = new Armor(AZURE_SHROUD, 40, "Robes");
}
