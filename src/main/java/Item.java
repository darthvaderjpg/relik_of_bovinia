// Author : Siddarth Iyer
// Notes  : Base item class handling factors, images, types, and routing enemy drops to Inventory/Hotbar.

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
        } else if (roll4 < 0.15){
            Hotbar.updateHotbar(Weapon.mosquitoSword);
        }
        
        Hotbar.addCoins(1);
    }           

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
}