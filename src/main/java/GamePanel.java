// Author : Ofer Koren & Siddarth Iyer
// Co Editor: Jake Jiang SUB TO SCRAPNEL!!!
// Date: 10/26/2026
// Rev: 49
// Notes: None

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JPanel;

public class GamePanel extends JPanel
{
    private static final long serialVersionUID = 1L;
    
    Map background = new Map();
    
    NPC dude = new Dude();
    NPC grandpa = new Grandpa();
    NPC shopkeeper = new Shop();
    NPC relik = new Relik();
    NPC bovisTalk = new BovisTalk();
    NPC keyNPC = new KeyNPC();
    NPC lock = new Lock();
    
    Numbers numbers1 = new Numbers(949, 16, "Ruby");
    Numbers numbers2 = new Numbers(590, 250, "Shop");
    
    // Death & Victory screen images using exact filenames
    private final static Image STANDARD_DEATH_SCREEN = Tools.getNewImageIcon("you-died-pixilart.png");
    private final static Image BOSS_DEATH_SCREEN = Tools.getNewImageIcon("yougotcorrupted.png");
    private final static Image VICTORY_SCREEN = Tools.getNewImageIcon("victory.png");
    
    // Enemy Array
    private ArrayList<Enemy> enemies = new ArrayList<Enemy>();
    
    // Bovis Fight Instance
    public static BovisFight bovis = new BovisFight(800, 400, 8);
    
    // Tracks previous room to detect room transitions for respawning
    private int lastRoom = -1;
    
    // Secret Mechanic for Dude & Artifact Armor evolution
    private static int dudeHP = 12;
    private static boolean dudeDefeated = false;
    private static boolean artifactEvolved = false;
    private static long lastHitTime = 0; // Cooldown timer for holding spacebar
    private static boolean wasDeadLastFrame = false; // Tracks death transition for resetting Dude
    
    public GamePanel() {
        spawnAllRooms();
    }
    
    // Resets Dude back to life upon player respawn
    public static void resetDude() {
        dudeHP = 12;
        dudeDefeated = false;
    }
    
    private void spawnAllRooms() {
        for (int room = 2; room <= 7; room++) {
            spawnEnemiesForRoom(room);
        }
    }
    
    private void spawnEnemiesForRoom(int roomID) {
        Random rand = new Random();
        int count = 0;
        
        switch (roomID) {
            case 2:
                count = rand.nextInt(4) + 3;
                for (int i = 0; i < count; i++) {
                    enemies.add(new Infarctus(rand.nextInt(600) + 100, rand.nextInt(350) + 200, roomID));
                }
                break;
                
            case 3:
                count = 3;
                for (int i = 0; i < count; i++) {
                    enemies.add(new Infarctus(rand.nextInt(600) + 100, rand.nextInt(350) + 200, roomID));
                }
                break;
                
            case 4: 
                count = rand.nextInt(3) + 5;
                for (int i = 0; i < count; i++) {
                    int x = rand.nextInt(600) + 100;
                    int y = rand.nextInt(350) + 200;
                    int roll = rand.nextInt(10);
                    if (roll < 6) {
                        enemies.add(new Knight(x, y, roomID)); 
                    } else if (roll < 9) {
                        enemies.add(new Infarctus(x, y, roomID));
                    } else {
                        enemies.add(new EliteGuard(x, y, roomID));
                    }
                }
                break;
                
            case 5: 
                break;
                
            case 6: 
                count = rand.nextInt(3) + 5; 
                for (int i = 0; i < count; i++) {
                    int x = rand.nextInt(600) + 100;
                    int y = rand.nextInt(350) + 200;
                    int type = rand.nextInt(3);
                    if (type == 0) enemies.add(new Infarctus(x, y, roomID));
                    else if (type == 1) enemies.add(new Knight(x, y, roomID));
                    else enemies.add(new EliteGuard(x, y, roomID));
                }
                break;
                
            case 7: 
                count = rand.nextInt(3) + 5; 
                for (int i = 0; i < count; i++) {
                    int x = rand.nextInt(600) + 100;
                    int y = rand.nextInt(350) + 200;
                    int roll = rand.nextInt(10);
                    if (roll < 4) enemies.add(new Infarctus(x, y, roomID));
                    else if (roll < 7) enemies.add(new Knight(x, y, roomID));
                    else enemies.add(new EliteGuard(x, y, roomID));
                }
                break;
                
            case 8: 
                count = rand.nextInt(3) + 5; 
                for (int i = 0; i < count; i++) {
                    int x = rand.nextInt(600) + 100;
                    int y = rand.nextInt(350) + 200;
                    int roll = rand.nextInt(10);
                    if (roll < 7) {
                        enemies.add(new EliteGuard(x, y, roomID)); 
                    } else if (roll < 9) {
                        enemies.add(new Knight(x, y, roomID));
                    } else {
                        enemies.add(new Infarctus(x, y, roomID));
                    }
                }
                break;
        }
    }
    
    @Override
    public void paintComponent(Graphics g) 
    {
        super.paintComponent(g);
        
        // --- CHECK IF BOVIS IS DEFEATED (HEALTH <= 0) ---
        if (bovis != null && bovis.getHealth() <= 0) {
            bovis = null; //victory screen
        }

        // --- VICTORY SCREEN CHECK ---
        if (Player.getRoom() == 8 && bovis == null) {
            if (VICTORY_SCREEN != null) {
                g.drawImage(VICTORY_SCREEN, 0, 0, getWidth(), getHeight(), null);
            } else {
                g.setColor(Color.GREEN);
                g.fillRect(0, 0, getWidth(), getHeight());

                g.setColor(Color.GREEN);
                g.setFont(new Font("Press Start 2P", Font.PLAIN, 18));
                g.drawString("Press Space to play again", getWidth() / 2 - 210, getHeight() - 100);
            }
            
            if (Main.getIsPressingSpace()) {
                Hotbar.clear();
                Keybar.clear();
                Inventory.clear();
                resetDude();
                bovis = new BovisFight(800, 400, 8);
                Player.respawn();
            }
            
            repaint();
            return;
        }

        boolean currentlyDead = Player.isDead();
        
        if (wasDeadLastFrame && !currentlyDead) {
            resetDude();
        }
        wasDeadLastFrame = currentlyDead;
        
        // --- DEATH SCREEN CHECK ---
        if (currentlyDead) {
            resetDude();

            Hotbar.clear();
            Keybar.clear();
            Inventory.clear();

            if (Player.getRoom() == 8 && BOSS_DEATH_SCREEN != null) {
                g.drawImage(BOSS_DEATH_SCREEN, 0, 0, getWidth(), getHeight(), null);
            } else if (STANDARD_DEATH_SCREEN != null) {
                g.drawImage(STANDARD_DEATH_SCREEN, 0, 0, getWidth(), getHeight(), null);
            } else {
                g.setColor(Color.BLACK);
                g.fillRect(0, 0, getWidth(), getHeight());
                g.setColor(Color.RED);
                g.setFont(new Font("Press Start 2P", Font.BOLD, 32));
                g.drawString("YOU DIED", 280, 350);
            }
            
            g.setColor(Color.WHITE);
            g.setFont(new Font("Press Start 2P", Font.PLAIN, 18));
            g.drawString("Press space", getWidth() / 2 - 80, getHeight() - 100);

            if (Main.getIsPressingSpace()) {
                bovis = new BovisFight(800, 400, 8);
                resetDude();
                Player.respawn();
            }

            repaint();
            return;
        }
        
        int currentRoom = Player.getRoom();
        if (currentRoom != lastRoom) {
            // --- ARTIFACT ARMOR & WEAPON EVOLUTION CHECK (ROOM 8) ---
            if (currentRoom == 8 && !artifactEvolved) {
                boolean hasSpiritKey = Keybar.hasKey("Spirit Key") || Keybar.hasKey(Key.spiritKey);
                boolean hasFullEliteArmor = (Hotbar.getHelmet() == Armor.eliteHelmet &&
                                             Hotbar.getChestplate() == Armor.eliteChestplate &&
                                             Hotbar.getLeggings() == Armor.eliteRobeBottoms &&
                                             Hotbar.getBoots() == Armor.eliteBoots);
                boolean hasSwordToTransform = (Hotbar.getWeapon() == Weapon.mirrorSword || 
                                               Hotbar.getWeapon() == Weapon.soldierSword);

                if (hasSpiritKey && hasFullEliteArmor && hasSwordToTransform) {
                    Hotbar.setHelmet(Armor.artifactHelmet);
                    Hotbar.setChestplate(Armor.artifactChestplate);
                    Hotbar.setLeggings(Armor.artifactRobeBottoms);
                    Hotbar.setBoots(Armor.artifactBoots);
                    
                    Inventory.setSlot(0, Armor.artifactHelmet);
                    Inventory.setSlot(1, Armor.artifactChestplate);
                    Inventory.setSlot(2, Armor.artifactRobeBottoms);
                    Inventory.setSlot(3, Armor.artifactBoots);
                    
                    Hotbar.forceWeapon(Weapon.artifactInfusedSword);
                    artifactEvolved = true;
                }
            }

            if (lastRoom != -1) {
                enemies.removeIf(e -> e.getRoomID() == lastRoom);
                spawnEnemiesForRoom(lastRoom);
            }
            lastRoom = currentRoom;
        }
        
        background.draw(g);
        
        if (Hotbar.getNullUIModel() != null) {
            g.drawImage(Hotbar.getNullUIModel(), 0, 0, null);
        }
        
        if (!dudeDefeated) {
            dude.draw(g);
        }
        
        grandpa.draw(g);
        shopkeeper.draw(g);
        relik.draw(g);
        bovisTalk.draw(g);
        keyNPC.draw(g);
        lock.draw(g);
        
        for (Enemy e : enemies) {
            e.draw(g);
        }

        // Draws and updates Bovis fight if active
        if (bovis != null) {
            bovis.draw(g);
            bovis.trackPlayer(Player.getX(), Player.getY(), Player.getRoom());
            
            if (bovis.infarctus01 != null) bovis.infarctus01.draw(g);
            if (bovis.infarctus02 != null) bovis.infarctus02.draw(g);
            if (bovis.infarctus03 != null) bovis.infarctus03.draw(g);
            if (bovis.knight01 != null) bovis.knight01.draw(g);
            if (bovis.knight02 != null) bovis.knight02.draw(g);
            if (bovis.knight03 != null) bovis.knight03.draw(g);
            if (bovis.knight04 != null) bovis.knight04.draw(g);
            if (bovis.infarctus04 != null) bovis.infarctus04.draw(g);
            if (bovis.infarctus05 != null) bovis.infarctus05.draw(g);
            if (bovis.infarctus06 != null) bovis.infarctus06.draw(g);
            if (bovis.infarctus07 != null) bovis.infarctus07.draw(g);
            if (bovis.infarctus08 != null) bovis.infarctus08.draw(g);
        }
        
        Player.draw(g);
        
        if (Hotbar.getSideUIModel() != null) {
            g.drawImage(Hotbar.getSideUIModel(), 800, 0, null);
        }
        
        Hotbar.draw(g);
        Inventory.draw(g);
        Dialogue.draw(g);
        numbers1.draw(g);
        numbers2.draw(g);

        // --- RENDER HOVER TOOLTIPS ---
        Point mousePos = getMousePosition();
        if (mousePos != null) {
            Hotbar.drawTooltips(g, mousePos.x, mousePos.y);
        }

        // --- DUDE 12-HP COMBAT TRIGGER ---
        if (!dudeDefeated) {
            dude.testInteraction(Player.getX(), Player.getY(), dude.getX(), dude.getY(), dude.getCollisionRadius(), "dude");
            
            boolean currentAttackPressed = Main.getIsPressingSpace();
            long currentTime = System.currentTimeMillis();
            
            if (Player.getRoom() == 0 && currentAttackPressed && (currentTime - lastHitTime >= 200)) {
                double distance = Math.hypot(Player.getX() - dude.getX(), Player.getY() - dude.getY());
                if (distance <= 150) {
                    dudeHP--;
                    lastHitTime = currentTime;
                    
                    if (dudeHP <= 0) {
                        dudeDefeated = true;
                        
                        Keybar.addKey(Key.spiritKey);
                        Hotbar.forceWeapon(Weapon.pocketKnife);
                        Player.moveToRoom(1);
                    }
                }
            }
        }

        grandpa.testInteraction(Player.getX(), Player.getY(), grandpa.getX(), grandpa.getY(), grandpa.getCollisionRadius(), "grandpa");
        shopkeeper.testInteraction(Player.getX(), Player.getY(), shopkeeper.getX(), shopkeeper.getY(), shopkeeper.getCollisionRadius(), "shopkeeper");
        relik.testInteraction(Player.getX(), Player.getY(), relik.getX(), relik.getY(), relik.getCollisionRadius(), "relik");
        bovisTalk.testInteraction(Player.getX(), Player.getY(), bovisTalk.getX(), bovisTalk.getY(), bovisTalk.getCollisionRadius(), "bovisTalk");
        
        if (!KeyNPC.isCollected()) {
            keyNPC.testInteraction(Player.getX(), Player.getY(), keyNPC.getX(), keyNPC.getY(), keyNPC.getCollisionRadius(), "keynpc");
        }

        if (!Lock.isUnlocked()) {
            lock.testInteraction(Player.getX(), Player.getY(), lock.getX(), lock.getY(), lock.getCollisionRadius(), "locknpc");
        }

        for (Enemy e : enemies) {
            e.getTargetAvailability(Player.getRoom());
            e.trackPlayer(Player.getX(), Player.getY(), Player.getRoom());
        }

        int prevPlayerX = Player.getX();
        int prevPlayerY = Player.getY();

        Player.run();

        if (Player.getRoom() == 4 && !Lock.isUnlocked()) {
            double dx = Player.getX() - lock.getX();
            double dy = Player.getY() - lock.getY();
            double distance = Math.hypot(dx, dy);

            if (distance < 60) {
                Player.setX(prevPlayerX);
                Player.setY(prevPlayerY);
            }
        }
        
        try 
        {
            Thread.sleep(20);
        } 
        catch (InterruptedException e) 
        {
            e.printStackTrace();
        }   
    }
}