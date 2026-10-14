// Author : Ofer Koren
// Co Editor: Jake Jiang SUB TO SCRAPNEL!!!
// Editor 3: Siddarth Iyer
// Date   : 5/19/2026
// Rev    : 06
// Notes  : Manages background, player, NPCs, safe room spawner (skipping Room 0 & Artifact Room), and Boss.

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
    
    Numbers numbers1 = new Numbers(949, 16, "Ruby");
    Numbers numbers2 = new Numbers(590, 250, "Shop");
    
    // Dynamic Enemy List for Random Spawning
    private ArrayList<Enemy> enemies = new ArrayList<Enemy>();
    
    // Boss in Room 8 (Guaranteed every time)
    public static BovisFight bovis = new BovisFight(800, 400, 8);
    
    // Define the Artifact room number here so enemies never spawn in it (change if needed)
    private static final int ARTIFACT_ROOM = 1; 
    
    public GamePanel() {
        spawnRandomEnemies();
    }
    
    // Spawns enemies safely in Rooms 1-7, strictly skipping Room 0 (Grandpa) and the Artifact Room
    private void spawnRandomEnemies() {
        Random rand = new Random();
        int totalEnemies = rand.nextInt(7) + 8; // 8 to 14 enemies
        
        for (int i = 0; i < totalEnemies; i++) {
            int randomRoom;
            
            // Loop ensures randomRoom is NEVER Room 0 (Grandpa's room) and NEVER the Artifact Room
            do {
                randomRoom = rand.nextInt(7) + 1; // Generates 1 through 7
            } while (randomRoom == ARTIFACT_ROOM);
            
            int randomX = rand.nextInt(500) + 150;
            int randomY = rand.nextInt(400) + 150;
            
            int enemyTypeChoice = rand.nextInt(3); // 0: Infarctus, 1: Knight, 2: EliteGuard
            
            if (enemyTypeChoice == 0) {
                enemies.add(new Infarctus(randomX, randomY, randomRoom));
            } else if (enemyTypeChoice == 1) {
                enemies.add(new Knight(randomX, randomY, randomRoom));
            } else {
                enemies.add(new EliteGuard(randomX, randomY, randomRoom));
            }
        }
    }
    
    @Override
    public void paintComponent(Graphics g) 
    {
        super.paintComponent(g);
        
        background.draw(g);
        
        if (Hotbar.getNullUIModel() != null) {
            g.drawImage(Hotbar.getNullUIModel(), 0, 0, null);
        }
        
        dude.draw(g);
        grandpa.draw(g);
        shopkeeper.draw(g);
        relik.draw(g);
        bovisTalk.draw(g);
        
        // Draw all dynamically spawned random enemies polymorphically
        for (Enemy e : enemies) {
            e.draw(g);
        }

        // Draw Boss and Boss Minions in Room 8
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
        
        // Draw Side UI background container precisely at X = 800
        if (Hotbar.getSideUIModel() != null) {
            g.drawImage(Hotbar.getSideUIModel(), 800, 0, null);
        }
        
        Hotbar.draw(g);
        Inventory.draw(g); // Renders centered items into the 4 vertical armor slots
        Dialogue.draw(g);
        numbers1.draw(g);
        numbers2.draw(g);

        dude.testInteraction(Player.getX(), Player.getY(), dude.getX(), dude.getY(), dude.getCollisionRadius(), "dude");
        grandpa.testInteraction(Player.getX(), Player.getY(), grandpa.getX(), grandpa.getY(), grandpa.getCollisionRadius(), "grandpa");
        shopkeeper.testInteraction(Player.getX(), Player.getY(), shopkeeper.getX(), shopkeeper.getY(), shopkeeper.getCollisionRadius(), "shopkeeper");
        relik.testInteraction(Player.getX(), Player.getY(), relik.getX(), relik.getY(), relik.getCollisionRadius(), "relik");
        bovisTalk.testInteraction(Player.getX(), Player.getY(), bovisTalk.getX(), bovisTalk.getY(), bovisTalk.getCollisionRadius(), "bovisTalk");
        
        // Update room availability check and AI tracking for all dynamically spawned random enemies
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