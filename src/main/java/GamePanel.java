// Author : Ofer Koren
// Co Editor: Jake Jiang SUB TO SCRAPNEL!!!
// Editor 3: Siddarth Iyer
// Date   : 5/19/2026
// Rev    : 10
// Notes  : Manages background, player, NPCs, dynamic room enemy respawning with random counts, and Boss.

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
    
    Numbers numbers1 = new Numbers(949, 16, "Ruby");
    Numbers numbers2 = new Numbers(590, 250, "Shop");
    
    //Enemy Array
    private ArrayList<Enemy> enemies = new ArrayList<Enemy>();
    
    // Boss in Room 8
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
    
    //Enemies Respawn
    private void spawnEnemiesForRoom(int roomID) {
        Random rand = new Random();
        int count = 0;
        
        switch (roomID) {
            case 2: // Room 2: Random 3 to 6 Infarctus each time they respawn
                count = rand.nextInt(4) + 3; // 3, 4, 5, or 6
                for (int i = 0; i < count; i++) {
                    enemies.add(new Infarctus(rand.nextInt(600) + 100, rand.nextInt(350) + 200, roomID));
                }
                break;
                
            case 3: // Room 3: Exactly 3 Infarctus
                count = 3;
                for (int i = 0; i < count; i++) {
                    enemies.add(new Infarctus(rand.nextInt(600) + 100, rand.nextInt(350) + 200, roomID));
                }
                break;
                
            case 4: 
                count = rand.nextInt(3) + 5; // 5, 6, or 7
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
        
        
        for (Enemy e : enemies) {
            e.draw(g);
        }

        //Draws the boss
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
        Inventory.draw(g); // puts the items in their slots
        Dialogue.draw(g);
        numbers1.draw(g);
        numbers2.draw(g);

        dude.testInteraction(Player.getX(), Player.getY(), dude.getX(), dude.getY(), dude.getCollisionRadius(), "dude");
        grandpa.testInteraction(Player.getX(), Player.getY(), grandpa.getX(), grandpa.getY(), grandpa.getCollisionRadius(), "grandpa");
        shopkeeper.testInteraction(Player.getX(), Player.getY(), shopkeeper.getX(), shopkeeper.getY(), shopkeeper.getCollisionRadius(), "shopkeeper");
        relik.testInteraction(Player.getX(), Player.getY(), relik.getX(), relik.getY(), relik.getCollisionRadius(), "relik");
        bovisTalk.testInteraction(Player.getX(), Player.getY(), bovisTalk.getX(), bovisTalk.getY(), bovisTalk.getCollisionRadius(), "bovisTalk");
        
        // FIXED: Changed "keyNPC" to lowercase "keynpc" to match Dialogue.java
        keyNPC.testInteraction(Player.getX(), Player.getY(), keyNPC.getX(), keyNPC.getY(), keyNPC.getCollisionRadius(), "keynpc");

        // Update room availability check and AI tracking for all enemies
        for (Enemy e : enemies) {
            e.getTargetAvailability(Player.getRoom());
            e.trackPlayer(Player.getX(), Player.getY(), Player.getRoom());
        }

        Player.run();
        
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