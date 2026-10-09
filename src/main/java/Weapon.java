//Name: Siddarth Iyer
//Date: 4/30/26
//Rev:01
//Notes: These are the weapon that the player can use.
// Base weapon damage is 2. Then damage scales with the weapon type.


import javax.swing.*;

import java.awt.Graphics;
import java.awt.Image;

public class Weapon  extends Item {
	public static final Image    ENEMY_DAGGER = (new ImageIcon("enemy-dagger-pixilart.png")).getImage();
	public static final Image    STARTER_SWORD = (new ImageIcon("simple-sword-pixilart.png")).getImage();
	public static final Image    SOLDIER_SWORD = (new ImageIcon("normal-sword-pixilart.png")).getImage();
	public static final Image    ELITE_GUARD_SWORD = (new ImageIcon("mirror-sword-pixilart.png")).getImage();
	public static final Image    ARTIFACT_SWORD = (new ImageIcon("shift-sword-pixilart.png")).getImage();
	    
	    //Special Gear
	
	public static final Image    MOSQUITO_SWORD = (new ImageIcon("slash-sword-pixilart.png")).getImage();
	public static final Image    GREAT_CLUB = (new ImageIcon("great-club-pixilart.png")).getImage();
	
	private int damage;
	//How much damage a weapon can do
	//This is of type weapon
	//The weapon type is the different types of weapons. EX. enemy dagger, starter sword, etc.

	public Weapon(Image Weapontype, int hitPoints, String type) {
		super(Weapontype, hitPoints, type);
		
	}
	
	static Weapon infarctus = new Weapon(ENEMY_DAGGER, 2, "Weapon");
	
	static Weapon starterSword = new Weapon(STARTER_SWORD, 2, "Weapon");
	
	static Weapon soldierSword = new Weapon(SOLDIER_SWORD, 5, "Weapon");
	
	static Weapon mirrorSword = new Weapon(ELITE_GUARD_SWORD, 10, "Weapon");
	
	static Weapon artifactInfusedSword = new Weapon(ARTIFACT_SWORD, 20, "Weapon");
	
	static Weapon mosquitoSword = new Weapon(MOSQUITO_SWORD, 3, "Weapon");
	
	static Weapon greatClub = new Weapon(GREAT_CLUB, 30, "Weapon");
	
}
