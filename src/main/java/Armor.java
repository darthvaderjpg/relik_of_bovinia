//Name: Siddarth Iyer
//Date: 10/13/2026
//Rev:02
//Notes: This is the class that holds all the armor

import java.awt.Image;

public class Armor extends Item {

    //Starter Gear Images
    public static final Image CRACKED_HELMET = Tools.getNewImageIcon("worn-out-helmet-pixilart.png");
    public static final Image WORN_CLOAK = Tools.getNewImageIcon("old-cloak-pixilart.png");
    public static final Image EXPLORER_SWEATER = Tools.getNewImageIcon("old-explorer's-sweater-pixilart.png");
    public static final Image EXPLORER_PANTS = Tools.getNewImageIcon("protected-long-pants-pixilart.png");
    public static final Image DUSTY_BOOTS = Tools.getNewImageIcon("dusty-boots-pixilart.png");
    
    //Soldier Gear Images
    public static final Image SOLDIER_HELMET = Tools.getNewImageIcon("soldier-helmet-pixilart-gif.gif");
    public static final Image SOLDIER_CHESTPLATE = Tools.getNewImageIcon("soldier-chestplate-pixilart.png");
    public static final Image SOLDIER_LEGGINGS = Tools.getNewImageIcon("soldier-leggings-pixilart.png");
    public static final Image SOLDIER_BOOTS = Tools.getNewImageIcon("soldier-boots-pixilart.png");
    
    //Elite Guard Gear Images
    public static final Image ELITE_GUARD_HELMET = Tools.getNewImageIcon("elite-helmet-gif-pixilart.gif");
    public static final Image ELITE_GUARD_CHESTPLATE = Tools.getNewImageIcon("(newer)elite-guard-chestplate-pixilart.png");
    public static final Image ELITE_GUARD_ROBE_BOTTOMS = Tools.getNewImageIcon("elite-guard-leggings.png");
    public static final Image ELITE_GUARD_BOOTS = Tools.getNewImageIcon("elite-guard-boots-pixilart.png");
    
    // Artifact Gear Images
    public static final Image ARTIFACT_HELMET = Tools.getNewImageIcon("-------");
    public static final Image ARTIFACT_CHESTPLATE = Tools.getNewImageIcon("artifact-chestplate-pixilart.png");
    public static final Image ARTIFACT_ROBE_BOTTOMS = Tools.getNewImageIcon("artifact-leggings-pixilart.png");
    public static final Image ARTIFACT_BOOTS = Tools.getNewImageIcon("artifact-boots-pixilart.png");
    
    private int hitPoints;

    public Armor(Image armorTypeImage, int hitPoints, String type) {
        super(type, armorTypeImage);
        this.hitPoints = hitPoints;
    }

    public Armor(String armorName) {
        super("Armor", Tools.getNewImageIcon(armorName.toUpperCase().replace(" ", "_") + ".png"));
        this.hitPoints = 5;
    }

    @Override
    public int getFactor() {
        return hitPoints;
    }

    public int getHitPoints() {
        return hitPoints;
    }

    public void setHitPoints(int hitPoints) {
        this.hitPoints = hitPoints;
    }
    
    //Beginner Clothes Set
    static Armor crackedHelmet = new Armor(CRACKED_HELMET, 2, "Helmet");
    static Armor wornCloak = new Armor(WORN_CLOAK, 2, "Robes");
    static Armor explorerSweater = new Armor(EXPLORER_SWEATER, 2, "Chestplate");
    static Armor explorerPants = new Armor(EXPLORER_PANTS, 2, "Leggings");
    static Armor dustyBoots = new Armor(DUSTY_BOOTS, 2, "Boots");
    
    //Soldier Armor Set
    static Armor soldierHelm = new Armor(SOLDIER_HELMET, 7, "Helmet");
    static Armor soldierChestplate = new Armor(SOLDIER_CHESTPLATE, 7, "Chestplate");
    static Armor soldierLeggings = new Armor(SOLDIER_LEGGINGS, 7, "Leggings");
    static Armor soldierBoots = new Armor(SOLDIER_BOOTS, 7, "Boots");
    
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
}