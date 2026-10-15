// Author : Siddarth Iyer
// Notes  : Base item class handling factors, images, types, and routing enemy drops to Inventory/Hotbar. 
//          Updated drop tables to include Cloaks and fix drop rollout logic.

import java.awt.Image;
import java.util.Random;

public class Item {
    private static Random random = new Random();
    
    private String type;
    private Image image;
    private int factor;
    
    public Item(Image image, int factor, String type) {
        this.image = image;
        this.factor = factor;
        this.type = type;
    }
    
    public Item(String type, Image image) {
        this.type = type;
        this.image = image;
        this.factor = 0;
    }
    
    public String getType() { return type; }
    public Image getImage() { return image; }
    public int getFactor() { return factor; }
    
    // --- Enemy Drop Handlers routed through Hotbar.updateHotbar for Inventory registration ---
    
    public static void getDroppedInfarctus() {
        double roll = random.nextDouble();
            
        if (roll < 0.35) {
            Hotbar.updateHotbar(Armor.explorerPants);
        } else if (roll < 0.70) {
            Hotbar.updateHotbar(Armor.dustyBoots);
        } else if (roll < 0.85) {
            Hotbar.updateHotbar(Weapon.mosquitoSword);
        }
        
        Hotbar.addCoins(1);
    }           

    public static void getDroppedSoldier() {
        double roll = random.nextDouble();
        
        if (roll < 0.20) {
            Hotbar.updateHotbar(Weapon.soldierSword);
        } else if (roll < 0.40) {
            Hotbar.updateHotbar(Armor.soldierBoots);
        } else if (roll < 0.60) {
            Hotbar.updateHotbar(Armor.soldierChestplate);
        } else if (roll < 0.80) {
            Hotbar.updateHotbar(Armor.soldierHelm);
        } else {
            // 20% chance to drop a Cloak if initialized in Cloak class
            Hotbar.updateHotbar(Cloak.azureShroud);
        }
        
        Hotbar.addCoins(3);
    }

    public static void getDroppedEliteGuard() {
        double roll = random.nextDouble();
        
        if (roll < 0.20) {
            Hotbar.updateHotbar(Weapon.mirrorSword);
        }else if(roll <0.25){
            Hotbar.updateHotbar(Armor.eliteHelmet);
        } 
        else if (roll < 0.35) {
            Hotbar.updateHotbar(Armor.eliteBoots);
        } else if (roll < 0.60) {
            Hotbar.updateHotbar(Armor.eliteChestplate);
        } else if (roll < 0.80) {
            Hotbar.updateHotbar(Armor.eliteRobeBottoms);
        } else if (roll < 0.93) {
            // 13% chance to drop an Elite Cloak
            Hotbar.updateHotbar(Cloak.azureShroud);
        } else {
            // 7% chance to drop Great Club
            Hotbar.updateHotbar(Weapon.greatClub);
        }
        
        Hotbar.addCoins(5);
    }
}