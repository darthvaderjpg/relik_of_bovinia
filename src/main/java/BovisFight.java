/*
Author: Jake Jiang -- Subscribe to Scrapnel on YT!
Date  : 05/19/26
Notes : Boss fight class. Fixed HUD health bar positioned at (100, 300). Fully expanded formatting.
 */

import java.awt.Graphics;
import java.awt.Image;
import java.util.HashMap;

public class BovisFight extends Enemy 
{
    private int stage = 0;
    private int speed = 1;
    private int tokenS2 = -1;
    private int minionSpawnS3 = 500;
    private int downTimer = 167;
    private static boolean bossToken = false;
    
    // Cache for percentage health bar assets
    private static final HashMap<Integer, Image> healthBarImages = new HashMap<>();
    
    public Infarctus infarctus01 = new Infarctus(650, 200, 8);
    public Infarctus infarctus02 = new Infarctus(500, 400, 8);
    public Infarctus infarctus03 = new Infarctus(650, 600, 8);
    
    public Knight knight01 = new Knight(200, 230, 8);
    public Knight knight02 = new Knight(700, 230, 8);
    public Knight knight03 = new Knight(200, 700, 8);
    public Knight knight04 = new Knight(700, 700, 8);
    
    public Infarctus infarctus04 = new Infarctus(800, 400, 8); 
    public Infarctus infarctus05 = new Infarctus(800, 400, 8);
    public Infarctus infarctus06 = new Infarctus(800, 400, 8); 
    public Infarctus infarctus07 = new Infarctus(800, 400, 8); 
    public Infarctus infarctus08 = new Infarctus(800, 400, 8); 

    public BovisFight(int x, int y, int roomID) 
    {
        super(x, y, 8, "bovisfight");
        loadHealthBarImages();
    }
    
    private void loadHealthBarImages() 
    {
        if (healthBarImages.isEmpty()) 
        {
            for (int p = 100; p >= 0; p -= 10) 
            {
                String fileName = "BOVIS_HEALTH_" + p + "_PERCENT.png";
                Image img = Tools.getNewImageIcon(fileName);
                healthBarImages.put(p, img);
            }
        }
    }
    
    public static void startBoss() 
    {
        bossToken = true;
        Map.enableBossMusic();
        System.out.println(bossToken);
    }
    
    public void trackPlayer(int px, int py, int pRoomID) 
    {
        if (super.getTargetAvailability(pRoomID) && super.getMoveToken() && bossToken) 
        {
            super.release();
            super.proximityHit(px, py);
            super.checkIfAttacked();
            holdMinions();
            
            switch (stage) 
            {
                case 0: 
                    runStage0(); 
                    break;
                case 1: 
                    runStage1(px, py); 
                    break;
                case 2: 
                    runStage2(px, py); 
                    break;
                case 3: 
                    runStage3(px, py); 
                    break;
                case 4: 
                    runStage4(px, py); 
                    break;
                case 5: 
                    runStage5(); 
                    break;
                case 6: 
                    runStage6(); 
                    stage = 1; 
                    break;
            }   
        } 
        else 
        {
            super.hold();
            holdMinions();
        }
    } 
    
    public void runStage0() 
    {
        if (super.getX() != 600) 
        {
            super.setSpeed(1);
            super.moveLeft();
        } 
        else 
        {
            stage = 1;
        }
    }
    
    public void runStage1(int px, int py) 
    {
        infarctus01.release();
        infarctus02.release();
        infarctus03.release();
        
        infarctus01.trackPlayer(px, py, 8);
        infarctus02.trackPlayer(px, py, 8);
        infarctus03.trackPlayer(px, py, 8);
        
        if (infarctus01.getEnemyType().equals("dead") && 
            infarctus02.getEnemyType().equals("dead") && 
            infarctus03.getEnemyType().equals("dead")) 
        {
            stage = 2;
        }
    }
    
    public void runStage2(int px, int py) 
    {
        if (tokenS2 == -1) 
        {
            knight01.setCoordinates(200, 230);
            knight02.setCoordinates(700, 230);
            knight03.setCoordinates(200, 700);
            knight04.setCoordinates(700, 700);
            tokenS2 = 0;
        }
        if (super.getX() > 75 && tokenS2 == 0) 
        {
            moveLeft();
            setSpeed(speed);
            speed++;
        } 
        else if (super.getX() <= 75) 
        {
            tokenS2 = 1;
        }
        
        if (super.getX() < 650 && tokenS2 == 1) 
        {
            super.setSpeed(2);
            moveRight();
        }
        
        if (knight01.getEnemyType().equals("dead") && 
            knight02.getEnemyType().equals("dead") && 
            knight03.getEnemyType().equals("dead") && 
            knight04.getEnemyType().equals("dead")) 
        {
            stage = 3;
        }
        
        knight01.release();
        knight02.release();
        knight03.release();
        knight04.release();
        
        knight01.setDashTime(0);
        knight02.setDashTime(-15);
        knight03.setDashTime(-7);
        knight04.setDashTime(-24);
        
        knight01.trackPlayer(px, py, 8);
        knight02.trackPlayer(px, py, 8);
        knight03.trackPlayer(px, py, 8);
        knight04.trackPlayer(px, py, 8);
    }
    
    public void runStage3(int px, int py) 
    {
        if (stage == 3) 
        {
            setSpeed(5);
            minionSpawnS3--;

            moveRadians360(px, py);
            if (minionSpawnS3 <= 400) 
            {
                infarctus04.release();
                infarctus04.trackPlayer(px, py, 8);
                if (minionSpawnS3 == 400) 
                {
                    infarctus04.setCoordinates(super.getX(), super.getY());
                }
            }
            if (minionSpawnS3 <= 300) 
            {
                infarctus05.release();
                infarctus05.trackPlayer(px, py, 8);
                if (minionSpawnS3 == 300) 
                {
                    infarctus05.setCoordinates(super.getX(), super.getY());
                }
            }
            if (minionSpawnS3 <= 200) 
            {
                infarctus06.release();
                infarctus06.trackPlayer(px, py, 8);
                if (minionSpawnS3 == 200) 
                {
                    infarctus06.setCoordinates(super.getX(), super.getY());
                }
            }
            if (minionSpawnS3 <= 100) 
            {
                infarctus07.release();
                infarctus07.trackPlayer(px, py, 8);
                if (minionSpawnS3 == 100) 
                {
                    infarctus07.setCoordinates(super.getX(), super.getY());
                }
            }
            if (minionSpawnS3 <= 0) 
            {
                infarctus08.release();
                infarctus08.trackPlayer(px, py, 8);
                if (minionSpawnS3 == 0) 
                {
                    infarctus08.setCoordinates(super.getX(), super.getY());
                }
            }
            if (minionSpawnS3 <= -100) 
            {
                stage = 4;
            }
        }
        
        knight01.release();
        knight02.release();
        knight03.release();
        knight04.release();
        
        knight01.trackPlayer(px, py, 8);
        knight02.trackPlayer(px, py, 8);
        knight03.trackPlayer(px, py, 8);
        knight04.trackPlayer(px, py, 8);
    }
    
    public void runStage4(int px, int py) 
    {
        super.setSpeed(1);
        int midX = 323;
        int midY = 387;
        
        if (stage == 4) 
        {
            moveRadians360(midX, midY);
        }
        
        downTimer--;
        if (getX() == midX && getY() == midY && downTimer > 0) 
        {
            downTimer--;
            super.setSpeed(0);
        }
        if (downTimer <= 0) 
        {
            downTimer--;
            super.allowMove();
            speed = 2;
            setSpeed(speed);
            stage = 5;
        }
        
        infarctus04.release();
        infarctus04.trackPlayer(px, py, 8);
        infarctus05.release();
        infarctus05.trackPlayer(px, py, 8);
        infarctus06.release();
        infarctus06.trackPlayer(px, py, 8);
        infarctus07.release();
        infarctus07.trackPlayer(px, py, 8);
        infarctus08.release();
        infarctus08.trackPlayer(px, py, 8);
    }
    
    public void runStage5() 
    {
        if (stage == 5) 
        {
            if (getY() < 400) 
            {
                setSpeed(1);
                super.moveDown();
            } 
            else if (getY() > 400) 
            {
                setSpeed(1);
                super.moveUp();
            } 
            else if (getX() <= 600) 
            {
                speed++;
                setSpeed(speed);
                moveRight();
            } 
            else if (getX() > 600) 
            {
                setCoordinates(600, 400);
                stage = 6;
            }
        }
    }
    
    public void runStage6() 
    {
        if (stage == 6) 
        {
            infarctus01.respawn();
            infarctus02.respawn();
            infarctus03.respawn();
            
            knight01.respawn();
            knight02.respawn();
            knight03.respawn();
            knight04.respawn();
            
            infarctus04.respawn();
            infarctus05.respawn();
            infarctus06.respawn();
            infarctus07.respawn();
            infarctus08.respawn();
            
            tokenS2 = 0;
            minionSpawnS3 = 500;
            downTimer = 167;
        }
    }
    
    public void holdMinions() 
    {
        infarctus01.hold();
        infarctus02.hold();
        infarctus03.hold();
        
        knight01.hold();
        knight02.hold();
        knight03.hold();
        knight04.hold();
        
        infarctus04.hold();
        infarctus05.hold();
        infarctus06.hold();
        infarctus07.hold();
        infarctus08.hold();
    }
    
    public void moveRadians360(int px, int py) 
    {
        double angle = Math.atan2(py - getY(), px - getX());
        if (angle < 0) 
        {
            angle += 2 * Math.PI;
        }
        
        double xMod = super.getX() + (0.5 * getSpeed() * Math.cos(angle));
        double yMod = super.getY() + (0.5 * getSpeed() * Math.sin(angle)); 

        super.setCoordinates((int)xMod, (int)yMod);
    }
    
    public static boolean getBossToken() 
    {
        return bossToken;
    }
    
    public void playBossMusic() 
    {
        Map.enableBossMusic();
    }
    
    public void stopBossMusic() 
    {
    }
    
    public int getStage() 
    {
        return stage;
    }

    public void sleep(int milliseconds) 
    {
        try 
        {
            Thread.sleep(milliseconds);
        } 
        catch (InterruptedException e) 
        {
            e.printStackTrace();
        }
    }

    @Override
    public void draw(Graphics g) 
    {
        // 1. Draw Bovis's main sprite normally
        super.draw(g);
        
        // 2. Render ONLY the PNG health bar asset at fixed screen coordinates (100, 300)
        if (bossToken && getHealth() > 0 && !getEnemyType().equals("dead")) 
        {
            int currentHp = Math.max(0, getHealth());
            int percent = (int) (((double) currentHp / 200) * 100);
            percent = (percent / 10) * 10;
            
            if (percent > 100) 
            {
                percent = 100;
            }
            if (percent < 0) 
            {
                percent = 0;
            }
            
            Image healthBarImage = healthBarImages.get(percent);
            
            if (healthBarImage != null) 
            {
                g.drawImage(healthBarImage, 100, 300, null);
            }
        }
    }
}