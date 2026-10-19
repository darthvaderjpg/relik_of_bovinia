// Author : Ofer Koren & Siddarth Iyer
// Co Editor: Jake Jiang SUB TO SCRAPNEL!!!
// Date: 5/19/2026
// Rev: 24
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
    
    // Death screen images using exact filenames
    private final static Image STANDARD_DEATH_SCREEN = Tools.getNewImageIcon("you-died-pixilart.png");
    private final static Image BOSS_DEATH_SCREEN = Tools.getNewImageIcon("yougotcorrupted.png");
    
    // Enemy Array
    private ArrayList<Enemy> enemies = new ArrayList<Enemy>();
    
    // Bovis
    public static BovisFight bovis = new BovisFight(800, 400, 8);
    
    // Tracks previous room to detect room transitions for respawning
    private int lastRoom = -1;
    
    public GamePanel() {
        spawnAllRooms();
    }
    
    // Initial spawn across all rooms on game start
    private void spawnAllRooms() {
        for (int room = 2; room <= 7; room++) {
            spawnEnemiesForRoom(room);
        }
    }
    
    // Enemies Respawn
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
        
        // --- DEATH SCREEN CHECK ---
        if (Player.isDead()) {
            // Render room-specific death background
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
            
            // Render "Press space" prompt in Press Start 2P font
            g.setColor(Color.WHITE);
            g.setFont(new Font("Press Start 2P", Font.PLAIN, 18));
            g.drawString("Press space", getWidth() / 2 - 80, getHeight() - 100);

            repaint();
            return; // Skip normal gameplay updates while dead
        }
        
        int currentRoom = Player.getRoom();
        if (currentRoom != lastRoom) {
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
        
        dude.draw(g);
        grandpa.draw(g);
        shopkeeper.draw(g);
        relik.draw(g);
        bovisTalk.draw(g);
        keyNPC.draw(g);
        lock.draw(g);
        
        for (Enemy e : enemies) {
            e.draw(g);
        }

        // Draws the boss
        bovis.draw(g);
        bovis.trackPlayer(Player.getX(), Player.getY(), Player.getRoom());
        bovis.infarctus01.draw(g);
        bovis.infarctus02.draw(g);
        bovis.infarctus03.draw(g);
        bovis.knight01.draw(g);
        bovis.knight02.draw(g);
        bovis.knight03.draw(g);
        bovis.knight04.draw(g);
        bovis.infarctus04.draw(g);
        bovis.infarctus05.draw(g);
        bovis.infarctus06.draw(g);
        bovis.infarctus07.draw(g);
        bovis.infarctus08.draw(g);
        
        Player.draw(g);
        
        // Draws the side UI
        if (Hotbar.getSideUIModel() != null) {
            g.drawImage(Hotbar.getSideUIModel(), 800, 0, null);
        }
        
        Hotbar.draw(g);
        Inventory.draw(g);
        Dialogue.draw(g);
        numbers1.draw(g);
        numbers2.draw(g);

        dude.testInteraction(Player.getX(), Player.getY(), dude.getX(), dude.getY(), dude.getCollisionRadius(), "dude");
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

        // --- Solid Lock Collision Check ---
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