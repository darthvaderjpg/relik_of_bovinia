//Name: Siddarth Iyer
//Date: 10/13/2026
//Rev:02
//Notes: These are the weapons that the player can use.
// Base weapon damage is 2. Then damage scales with the weapon type.

import java.awt.Image;

public class Weapon extends Item {
    public static final Image ENEMY_DAGGER = Tools.getNewImageIcon("enemy-dagger-pixilart.png");
    public static final Image STARTER_SWORD = Tools.getNewImageIcon("simple-sword-pixilart.png");
    public static final Image SOLDIER_SWORD = Tools.getNewImageIcon("Common Sword.png");
    public static final Image ELITE_GUARD_SWORD = Tools.getNewImageIcon("Elite Sword.png");
    public static final Image ARTIFACT_SWORD = Tools.getNewImageIcon("shift-sword-pixilart.png");
        
    //Special Gear
    public static final Image MOSQUITO_SWORD = Tools.getNewImageIcon("Slash Sword.png");
    public static final Image GREAT_CLUB = Tools.getNewImageIcon("great-club-pixilart.png");
    
    private int dmg;

    public Weapon(Image weaponType, int dmg, String type) {
        super(type, weaponType);
        this.dmg = dmg;
    }

    public Weapon(String weaponName) {
        super("Weapon", Tools.getNewImageIcon(weaponName.toUpperCase().replace(" ", "_") + ".png"));
        this.dmg = 5;
    }

    @Override
    public int getFactor() {
        return dmg;
    }

    public int getDmg() {
        return dmg;
    }

    public void setDmg(int dmg) {
        this.dmg = dmg;
    }

    static Weapon infarctus = new Weapon(ENEMY_DAGGER, 2, "Weapon");
    static Weapon starterSword = new Weapon(STARTER_SWORD, 2, "Weapon");
    static Weapon soldierSword = new Weapon(SOLDIER_SWORD, 5, "Weapon");
    static Weapon mirrorSword = new Weapon(ELITE_GUARD_SWORD, 10, "Weapon");
    static Weapon artifactInfusedSword = new Weapon(ARTIFACT_SWORD, 20, "Weapon");
    static Weapon mosquitoSword = new Weapon(MOSQUITO_SWORD, 3, "Weapon");
    static Weapon greatClub = new Weapon(GREAT_CLUB, 30, "Weapon");
}