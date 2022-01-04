package Space_Defender;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;


public class Controls extends JPanel implements KeyListener, ActionListener{

	private Timer timer;
	private double delay=6;
	private int shipHealth=300;
	private int lives=3;
	private int score=0;
	private int endScore;
	private int level=1;//Level limit=74
	private boolean bulletHealth=false;//CRASHES GAME 
	private boolean damagePotion=false;
	private boolean healthPotion=false;
	private boolean slowPotion=false;
	private boolean slowPotionUsed=false;
	private boolean missilePotion=false;
	private int makePotion=0;
	private boolean potionExists=false;
	private int xPotion=30;
	private int yPotion=50;
	int potion=0;
	private boolean potionCollected=false;
	
	private int damagePotions=0;
	private int healthPotions=0;
	private int slowPotions=0;
	private int missilePotions=0;
	
	private boolean choice=false;
	private boolean frenzy=false;
	private boolean normal=true;
	
	private boolean play=false;
	private boolean stars=true;
	private boolean shipExists=true;
	private boolean allAdead=false;
	
	private boolean aGun1Shoot=false;
	private boolean aGun2Shoot=false;
	private boolean aGun3Shoot=false;
	private boolean shipJustDied=false;
	
	private int shipXpos=250;
	private int shipYpos=600;
	private int shipXdir=1;
	private int shipYdir=-2;
	
	private int gunL1Ypos=shipYpos-14;
	private int gunL2Ypos=shipYpos-26;
	private int gun1aXpos=shipXpos;
	private int gun1bXpos=shipXpos+38;
	private int gun2aXpos=shipXpos+8;
	private int gun2bXpos=shipXpos+30;
	
	private int cannonYpos=shipYpos-15;
	private int cannonXpos=shipXpos+17;
	
	private int laserYpos=shipYpos-750;
	private int laserXpos=shipXpos+19;
	
	private int bDamageAdd=0;
	private int Bammo=10001;
	private int Mammo=7;
	private int Lammo=1;
	private int oBammo=10001;
	private int oMammo=7;
	private int oLammo=1;
	private int Aammo=100000;
	private int aGunYdir=2;
	
	
	private int Bshotcount=0;
	private int Mshotcount=0;
	private int Lshotcount=0;
	
	private boolean Bfire=false;
	private boolean Mfire=false;
	private boolean mFireLimit=false;///not currently working
	private boolean cantFireM=false;
	private boolean Lfire=false;
	
	private Bullets bullets;
	private Aliens alien;
	
	private int aXpos=100;
	private int aYpos=50;
	private int aYdir=0;
	
	private int aShots=1;
	private int wallhits=0;
	
	private Player[] records=new Player[6];
	private boolean seeRecords=false;
	private int scorePos=0;
	private boolean highScores=false;
	private int bestScore=0;
	private int highestLevel=0;
	private boolean HTP=false;
	
	
	public Controls()
	{
		bullets=new Bullets(Bammo, Mammo+10, Lammo, Aammo, gun1aXpos, gun1bXpos, gunL1Ypos,  gun2aXpos, gun2bXpos, gunL2Ypos, cannonYpos, cannonXpos, laserYpos, laserXpos, aXpos, aYpos);
		alien=new Aliens(21, aXpos, aYpos);
		
		Random randomm=new Random();
		potion=randomm.nextInt(4)+1;
		if(potion==1)
		{
			damagePotion=true;
			makePotion=1;
		}
		if(potion==2)
		{
			healthPotion=true;
			makePotion=2;
		}
		if(potion==3)
		{
			slowPotion=true;
			makePotion=3;
		}
		if(potion==4)
		{
			missilePotion=true;
			makePotion=4;
		}
		
		Random random=new Random();
		xPotion=random.nextInt(460)+20;
		yPotion=random.nextInt(150)+50;
		
		
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		
		
		/*records[0]=new Player("Kaden", 56595);
		records[1]=new Player("Cruz", 0);
		records[2]=new Player("Kennedy", 0);
		records[3]=new Player("Rachael", 0);
		records[4]=new Player("Chad", 0);
		records[5]=new Player("Reese", 0);*/
		
	
		
		
		timer= new Timer((int)delay, this);
		timer.start();
		
	
		
		
	}
	
	public void paint(Graphics g)
	{
		//background
		g.setColor(Color.black);
		g.fillRect(0,0,500,700);
		
		//stars
		
		if(stars)
		{
		Random rand=new Random();
		for(int i=0; i<3; i++)
		{
			int x=rand.nextInt(440)+30;
			int y=rand.nextInt(600)+30;
			
			
			int[] Xs = {x+3, x+6, x+9, x, x+12};
			int[] Ys = {y+6, y-5, y+6, y, y};
			
			g.setColor(Color.white);
			g.drawPolygon(Xs, Ys, 5);
		}	
		}
		
		
		
		
		if(seeRecords==true)
		{
			if(records.length>0)
			{
			organizePlayers(records);
			}
			int y=150;
			
			
			
			
			
			g.setColor(Color.ORANGE);
			g.setFont(new Font("serif", Font.BOLD, 50));
			g.drawString("HIGH SCORES", 80, 150);
			
			g.setFont(new Font("serif", Font.PLAIN, 20));
			for(int i=0; i<records.length; i++)
			{
				y+=40;
				g.drawString(i+1+". "+records[i].getName(), 150, y);
				g.drawString("  "+records[i].getScore(), 300, y);
			}
			
			
			g.setFont(new Font("serif", Font.ITALIC, 25));
			g.drawString("Press Enter to Restart", 80, y+50);
		}
		
		if(choice==false)
		{
			
			g.setColor(Color.WHITE);
			g.setFont(new Font("comicSans", Font.ITALIC, 50));
			g.drawString("SPACE DEFENDER", 45, 80);
			g.setColor(Color.magenta);
			g.setFont(new Font("comicSans", Font.PLAIN, 25));
			g.drawString("Press N for Normal Mode", 110, 260);
			g.drawString("Press F for Frenzy Mode", 110, 305);
			g.setFont(new Font("comicSans", Font.PLAIN, 20));
			g.drawString("Press I for How to Play", 145, 670);
		
			
			g.setColor(Color.WHITE);
			
			g.fillRect(shipXpos, shipYpos, 40, 4);
			g.fillRect(shipXpos+5, shipYpos-5, 30, 5);
			g.fillRect(shipXpos+8, shipYpos-12, 24, 7);
			g.fillRect(shipXpos+12, shipYpos-19, 16, 7);
			g.fillRect(shipXpos+16, shipYpos-36, 8, 17);
			g.fillRect(shipXpos+8, shipYpos+4, 24, 5);
			g.fillRect(shipXpos+8, shipYpos+9, 6, 4);
			g.fillRect(shipXpos+26, shipYpos+9, 6, 4);
			g.fillRect(shipXpos+19, shipYpos+9, 2, 10);
			
			
			//guns on ship
			g.fillRect(shipXpos+0, shipYpos-14, 2, 14);
			g.fillRect(shipXpos+38, shipYpos-14, 2, 14);
			g.fillRect(shipXpos+8, shipYpos-26, 2, 14);
			g.fillRect(shipXpos+30, shipYpos-26, 2, 14);
			g.fillRect(shipXpos+19, shipYpos-50, 2, 14);
			
			g.setColor(Color.red);
			g.fillRect(shipXpos+0, shipYpos-14, 2, 4);
			g.fillRect(shipXpos+38, shipYpos-14, 2, 4);
			g.fillRect(shipXpos+8, shipYpos-26, 2, 4);
			g.fillRect(shipXpos+30, shipYpos-26, 2, 4);
			g.fillRect(shipXpos+19, shipYpos-50, 2, 4);
			g.fillRect(shipXpos+8, shipYpos+11, 6, 2);
			g.fillRect(shipXpos+26, shipYpos+11, 6, 2);
			
			//window
			g.fillRect(shipXpos+15, shipYpos-10, 2, 7);
			g.fillRect(shipXpos+23, shipYpos-10, 2, 7);
			g.fillRect(shipXpos+15, shipYpos-10, 10, 2);
			g.fillRect(shipXpos+19, shipYpos-15, 2, 5);
			
			shipExists=false;
			allAdead=true;
		}
		
		
	
		
		
		
		
		
		if(seeRecords==false)
		{
		
		g.setColor(Color.RED);
		g.drawRect(380, 590, 110, 80);
		
		g.setFont(new Font("comicSans", Font.PLAIN, 10));
		g.drawString("Bullet Ammo: "+(Bammo-Bshotcount-1), 390, 610);
	
		g.setFont(new Font("comicSans", Font.PLAIN, 10));
		g.drawString("Missile Ammo: "+(Mammo-Mshotcount-1), 390, 635);
	
		g.setFont(new Font("comicSans", Font.PLAIN, 10));
		g.drawString("Laser Ammo: "+(Lammo-Lshotcount), 390, 660);
		
		
		
		g.setColor(Color.RED);
		g.drawRect(10, 520, 110, 150);
		
		g.setFont(new Font("comicSans", Font.PLAIN, 10));
		g.drawString("Damage Potions: "+damagePotions, 20, 540);
		g.drawString("Press 1 to use", 20, 552);
	
		g.setFont(new Font("comicSans", Font.PLAIN, 10));
		g.drawString("Health Potions: "+healthPotions, 20, 577);
		g.drawString("Press 2 to use", 20, 589);
	
		g.setFont(new Font("comicSans", Font.PLAIN, 10));
		g.drawString("Time Potions: "+slowPotions, 20, 614);
		g.drawString("Press 3 to use", 20, 626);
		
		g.setFont(new Font("comicSans", Font.PLAIN, 10));
		g.drawString("Missile Potions: "+missilePotions, 20, 651);
		g.drawString("Press 4 to use", 20, 663);
		
		
		
		
		
		g.setFont(new Font("comicSans", Font.PLAIN, 15));
		g.drawString("Lives: "+lives, 5, 20);
		
		
		g.setFont(new Font("comicSans", Font.PLAIN, 15));
		g.drawString("Level: "+level, 225, 20);
		
		if(score<10)
		{
			scorePos=435;
		}
		if(score>=10 && score<100)
		{
			scorePos=425;
		}
		if(score>=100 && score<1000)
		{
			scorePos=415;
		}
		if(score>=1000 && score<10000)
		{
			scorePos=405;
		}
		if(score>=10000 && score<100000)
		{
			scorePos=395;
		}
		if(score>=100000 && score<1000000)
		{
			scorePos=385;
		}
		if(score>=1000000 && score<10000000)
		{
			scorePos=375;
		}
		if(score>=10000000 && score<100000000)
		{
			scorePos=365;
		}
		g.setFont(new Font("comicSans", Font.PLAIN, 15));
		g.drawString("Score: "+score, scorePos, 20);
		
		}
		
		
		
		
	
		
		
		
		
		
		
		
		
		
		if(shipExists==true && allAdead==false)
		{
			
			if(play==false)
			{
			g.setColor(Color.RED);
			g.setFont(new Font("comicSans", Font.BOLD, 25));
			g.drawString("Move or Shoot to Start", 110, 300);
			
			g.setColor(Color.MAGENTA);
			g.setFont(new Font("comicSans", Font.PLAIN, 20));
			g.drawString("Press I for How to Play", 145, 670);
			}
			
			if(makePotion==1 && potionExists==true && damagePotion==true)
			{
				
				g.setColor(Color.DARK_GRAY);
				g.fillRect(xPotion+4, yPotion-5, 8, 10);

				g.setColor(Color.RED);
				g.fillOval(xPotion, yPotion, 16, 16);	
				
			}
			
			if(makePotion==2 && potionExists==true && healthPotion==true)
			{
				
				g.setColor(Color.DARK_GRAY);
				g.fillRect(xPotion+4, yPotion-5, 8, 10);

				g.setColor(Color.CYAN);
				g.fillOval(xPotion, yPotion, 16, 16);	
				
			}
			if(makePotion==3 && potionExists==true && slowPotion==true)
			{
				
				g.setColor(Color.DARK_GRAY);
				g.fillRect(xPotion+4, yPotion-5, 8, 10);

				g.setColor(Color.GREEN);
				g.fillOval(xPotion, yPotion, 16, 16);	
				
			}
			if(makePotion==4 && potionExists==true && missilePotion==true)
			{
				
				g.setColor(Color.DARK_GRAY);
				g.fillRect(xPotion+4, yPotion-5, 8, 10);

				g.setColor(Color.MAGENTA);
				g.fillOval(xPotion, yPotion, 16, 16);	
				
			}
		//Missiles
		
		for(int i=0; i<Mammo; i++)
		{
		if(bullets.getMissiles().get(i).getContact()==false)
		{
		bullets.getMissiles().get(i).paint(g);
		}
		}	
			
			
		g.setColor(Color.WHITE);
		g.fillRect(shipXpos, shipYpos, 40, 4);
		g.fillRect(shipXpos+5, shipYpos-5, 30, 5);
		g.fillRect(shipXpos+8, shipYpos-12, 24, 7);
		g.fillRect(shipXpos+12, shipYpos-19, 16, 7);
		g.fillRect(shipXpos+16, shipYpos-36, 8, 17);
		g.fillRect(shipXpos+8, shipYpos+4, 24, 5);
		g.fillRect(shipXpos+8, shipYpos+9, 6, 4);
		g.fillRect(shipXpos+26, shipYpos+9, 6, 4);
		g.fillRect(shipXpos+19, shipYpos+9, 2, 10);
		
		
		//guns on ship
		g.fillRect(shipXpos+0, shipYpos-14, 2, 14);
		g.fillRect(shipXpos+38, shipYpos-14, 2, 14);
		g.fillRect(shipXpos+8, shipYpos-26, 2, 14);
		g.fillRect(shipXpos+30, shipYpos-26, 2, 14);
		g.fillRect(shipXpos+19, shipYpos-50, 2, 14);
		
		g.setColor(Color.red);
		g.fillRect(shipXpos+0, shipYpos-14, 2, 4);
		g.fillRect(shipXpos+38, shipYpos-14, 2, 4);
		g.fillRect(shipXpos+8, shipYpos-26, 2, 4);
		g.fillRect(shipXpos+30, shipYpos-26, 2, 4);
		g.fillRect(shipXpos+19, shipYpos-50, 2, 4);
		g.fillRect(shipXpos+8, shipYpos+11, 6, 2);
		g.fillRect(shipXpos+26, shipYpos+11, 6, 2);
		
		//window
		g.fillRect(shipXpos+15, shipYpos-10, 2, 7);
		g.fillRect(shipXpos+23, shipYpos-10, 2, 7);
		g.fillRect(shipXpos+15, shipYpos-10, 10, 2);
		g.fillRect(shipXpos+19, shipYpos-15, 2, 5);
		
		
		
		//bullets
		
		for(int i=0; i<Bammo; i++)
		{
		if(bullets.getBullets().get(i).getContact1bx()==false && bullets.getBullets().get(i).getExists1bx()==true)
		{
		bullets.getBullets().get(i).paint1bx(g);
		}
		}
		for(int i=0; i<Bammo; i++)
		{
		if(bullets.getBullets().get(i).getContact2ax()==false && bullets.getBullets().get(i).getExists2ax()==true)
		{
		bullets.getBullets().get(i).paint2ax(g);
		}
		}
		for(int i=0; i<Bammo; i++)
		{
		if(bullets.getBullets().get(i).getContact2bx()==false && bullets.getBullets().get(i).getExists2bx()==true)
		{
		bullets.getBullets().get(i).paint2bx(g);
		}
		}
		for(int i=0; i<Bammo; i++)
		{
		if(bullets.getBullets().get(i).getContact1ax()==false && bullets.getBullets().get(i).getExists1ax()==true)
		{
		bullets.getBullets().get(i).paint1ax(g);
		}
		}
		
		//laser
		if(Lfire==true)
		{
		if(bullets.getLaser().get(Lshotcount-1).getExists()==true)
					
		{
		bullets.getLaser().get(Lshotcount-1).paint(g);
		}
		}
		
		
		//alienGun
		
		for(int i=0; i<2100; i++)
		{
		if(bullets.getAlienGun().get(i).getExists()==true)
		{
		bullets.getAlienGun().get(i).paint(g);
		}
		}
		g.setColor(Color.YELLOW);
		g.setFont(new Font("comicSans", Font.PLAIN, 10));
		g.drawString("Health: "+shipHealth, shipXpos-7, shipYpos+30);
		
		
		}
		
		//Alien
				if(allAdead==false)
				{
					alien.drawAliens(g);
				}
		
		
		if(shipExists==false && lives>0  && choice==true && seeRecords==false)
		{
			play=false;
			g.setColor(Color.RED);
			g.setFont(new Font("comicSans", Font.BOLD, 30));
			g.drawString("Your Ship Was Destoyed!", 60, 300);
			g.setFont(new Font("comicSans", Font.ITALIC, 20));
			g.drawString("Press Enter to Continue", 140, 340);
		}
		
		if(shipExists==false && lives<=0  && choice==true && seeRecords==false)
		{
		
			if(score>bestScore)
			{
				
				g.setColor(Color.RED);
				g.setFont(new Font("comicSans", Font.BOLD, 30));
				g.drawString("NEW BEST SCORE!", 95, 470);
				bestScore=score;
			}
			play=false;
			g.setColor(Color.RED);
			g.setFont(new Font("comicSans", Font.BOLD, 50));
			g.drawString("GAME OVER", 95, 300);
			g.setFont(new Font("comicSans", Font.ITALIC, 20));
			g.drawString("Press Enter to Restart", 145, 340);
			//g.drawString("Press H to see High Scores", 128, 370);
			g.setFont(new Font("comicSans", Font.PLAIN, 20));
			g.drawString("Best Score: "+bestScore,170, 670);
		}
		
		
		boolean areDead=true;
		for(int i=0; i<alien.getAliens().size(); i++)
		{
			if(alien.getAliens().get(i).isAlive()==true)
			{
				areDead=false;
			}
		}
		if(areDead==true)
		{
			allAdead=true;
		}
		if(allAdead==true  && choice==true && seeRecords==false)
		{
			play=false;
			g.setColor(Color.RED);
			g.setFont(new Font("comicSans", Font.BOLD, 30));
			g.drawString("All Enemies Eliminated", 80, 300);
			g.setFont(new Font("comicSans", Font.ITALIC, 20));
			g.drawString("Press Enter for Next Level", 130, 340);
		}
		
		if(HTP==true) 
		{
			g.setColor(Color.BLACK);
			g.fillRect(0,0,500, 700);
			if(stars)
			{
			Random rand=new Random();
			for(int i=0; i<3; i++)
			{
				int x=rand.nextInt(440)+30;
				int y=rand.nextInt(600)+30;
				
				
				int[] Xs = {x+3, x+6, x+9, x, x+12};
				int[] Ys = {y+6, y-5, y+6, y, y};
				
				g.setColor(Color.white);
				g.drawPolygon(Xs, Ys, 5);
			}	
			}
			
			g.setColor(Color.RED);
			g.setFont(new Font("comicSans", Font.PLAIN, 40));
			g.drawString("HOW TO PLAY", 120, 60);
			
			g.setColor(Color.RED);
			g.setFont(new Font("comicSans", Font.PLAIN, 15));
			g.drawString("1. Use arrow keys or WASD to move.", 20, 100);
			g.drawString("2. Use SPACE BAR to shoot bullets.", 20, 125);
			g.drawString("3. Use M to shoot missiles.", 20, 150);
			g.drawString("4. Use L to shoot a laser.", 20, 175);
			g.drawString("5. Use 1,2,3, and 4 to use different potions.", 20, 200);
			g.drawString("6. Destroy all aliens to move to next level.", 20, 225);
			g.drawString("7. Aliens will shoot at you, so DODGE.", 20, 250);
			g.drawString("8. Once you destroy an alien, it can no longer shoot.", 20, 275);
			g.drawString("9. All bullets regenerate each round, except the laser.", 20, 300);
			g.drawString("10. Alien health and damage increase each round.", 20, 325);
			g.drawString("11. Speed increases periodically, but starts slow.", 20, 350);
			g.drawString("12. There are 4 Potions: damage, health, time, and missile.", 20, 375);
			g.drawString("13. A random potion spawns each round, run into it to collect.", 20, 400);
			g.drawString("14. Damage potions(red) double your bullet's damage.", 20, 425);
			g.drawString("15. Health potions(blue) set your health to 500.", 20, 450);
			g.drawString("16. Time potions(green) slow the aliens and their bullets.", 20, 475);
			g.drawString("17. Missile potions(purple) refill your missile ammo.", 20, 500);
			g.drawString("18. In frenzy mode, different bullets do different damages.", 20, 525);
			g.drawString("19. Health starts at 300 and does not reset each level.", 20, 550);
			g.drawString("20. You have 3 lives.", 20, 575);
			
			g.setFont(new Font("comicSans", Font.ITALIC, 25));
			g.drawString("Press Enter to Continue", 200, 640);
			
			
			
		}
		
		g.dispose();
		
	}
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) 
	{
	if(play==true && shipExists==true)
	{
		if(Bfire==true && Bshotcount<Bammo)
		{
			timer.start();
			for(int i=0; i<Bshotcount; i++)
			{
			bullets.getBullets().get(Bshotcount-i).setL1Y(bullets.getBullets().get(Bshotcount-i).getL1Y()+bullets.getBullets().get(Bshotcount-i).getYdir());
			bullets.getBullets().get(Bshotcount-i).setL2Y(bullets.getBullets().get(Bshotcount-i).getL2Y()+bullets.getBullets().get(Bshotcount-i).getYdir());
			}
		}
		if(Mfire==true && Mshotcount<Mammo)
		{
			timer.start();
			for(int i=0; i<Mshotcount; i++)
			{
			bullets.getMissiles().get(Mshotcount-i).setmY(bullets.getMissiles().get(Mshotcount-i).getmY()+bullets.getMissiles().get(Mshotcount-i).getYdir());
			}
		}
		if(Lfire==true && Lshotcount<Lammo)
		{
			timer.start();
			
			
			if(wallhits>5)
			{	
			bullets.getLaser().get(Lshotcount-1).setExists(false);
			Lfire=false;
			}
			
		}
		
		
		
		
		for(int i=0; i<=Bshotcount; i++)
		{
			for(int j=0; j<alien.getAliens().size(); j++)
			{
				timer.start();
				
				if(new Rectangle(bullets.getBullets().get(i).get1aX(), bullets.getBullets().get(i).getL1Y(), 2, 4).intersects(new Rectangle(alien.getAliens().get(j).getXpos(), alien.getAliens().get(j).getYpos(), 40, 25)))
				{
					alien.getAliens().get(j).setHealth(alien.getAliens().get(j).getHealth()-bullets.getBullets().get(i).get1axDamage());
					
					if(alien.getAliens().get(j).isAlive())
					{
					bullets.getBullets().get(i).setContact1ax(true);
					bullets.getBullets().get(i).set1axDamage(0);
					}
				}
				if(new Rectangle(bullets.getBullets().get(i).get1bX(), bullets.getBullets().get(i).getL1Y(), 2, 4).intersects(new Rectangle(alien.getAliens().get(j).getXpos(), alien.getAliens().get(j).getYpos(), 40, 25)))
				{
					alien.getAliens().get(j).setHealth(alien.getAliens().get(j).getHealth()-bullets.getBullets().get(i).get1bxDamage());
					
					if(alien.getAliens().get(j).isAlive())
					{
					bullets.getBullets().get(i).setContact1bx(true);
					bullets.getBullets().get(i).set1bxDamage(0);
					}
				}
				if(new Rectangle(bullets.getBullets().get(i).get2aX(), bullets.getBullets().get(i).getL2Y(), 2, 4).intersects(new Rectangle(alien.getAliens().get(j).getXpos(), alien.getAliens().get(j).getYpos(), 40, 25)))
				{
					alien.getAliens().get(j).setHealth(alien.getAliens().get(j).getHealth()-bullets.getBullets().get(i).get2axDamage());
					
					if(alien.getAliens().get(j).isAlive())
					{
					bullets.getBullets().get(i).setContact2ax(true);
					bullets.getBullets().get(i).set2axDamage(0);
					}
				}
				if(new Rectangle(bullets.getBullets().get(i).get2bX(), bullets.getBullets().get(i).getL2Y(), 2, 4).intersects(new Rectangle(alien.getAliens().get(j).getXpos(), alien.getAliens().get(j).getYpos(), 40, 25)))
				{
					alien.getAliens().get(j).setHealth(alien.getAliens().get(j).getHealth()-bullets.getBullets().get(i).get2bxDamage());
					
					if(alien.getAliens().get(j).isAlive())
					{
					bullets.getBullets().get(i).setContact2bx(true);
					bullets.getBullets().get(i).set2bxDamage(0);
					}
				}
				
				
				if(alien.getAliens().get(j).getHealth()<=0)
				{
					if(alien.getAliens().get(j).getCanAddScore()==true)
					{
						score+=(5*level);
					}
					alien.getAliens().get(j).setCanAddScore(false);
					alien.getAliens().get(j).setAlive(false);
				}
			}	
		
		}
		for(int i=0; i<=Mshotcount; i++)
		{
			for(int j=0; j<alien.getAliens().size(); j++)
			{
				timer.start();
				if(new Rectangle(bullets.getMissiles().get(i).getmX(), bullets.getMissiles().get(i).getmY(), 9, 13).intersects(new Rectangle(alien.getAliens().get(j).getXpos(), alien.getAliens().get(j).getYpos(), 40, 25)))
				{
					alien.getAliens().get(j).setHealth(alien.getAliens().get(j).getHealth()-bullets.getMissiles().get(i).getDamage());
					if(alien.getAliens().get(j).isAlive()==true)
					{
					bullets.getMissiles().get(i).setContact(true);
					bullets.getMissiles().get(i).setDamage(0);
					}
				}
				if(alien.getAliens().get(j).getHealth()<=0)
				{
					if(alien.getAliens().get(j).getCanAddScore()==true)
					{
						score+=(5*level);
					}
					alien.getAliens().get(j).setCanAddScore(false);
					alien.getAliens().get(j).setAlive(false);
					
				}
			}	
		
		}
		for(int i=0; i<Lshotcount; i++)
		{
			for(int j=0; j<alien.getAliens().size(); j++)
			{
				timer.start();
				if(bullets.getLaser().get(i).getExists()==true)
				{
				if(new Rectangle(bullets.getLaser().get(i).getlX(), bullets.getLaser().get(i).getlY(), 2, 700).intersects(new Rectangle(alien.getAliens().get(j).getXpos(), alien.getAliens().get(j).getYpos(), 40, 25)))
				{
					alien.getAliens().get(j).setHealth(alien.getAliens().get(j).getHealth()-bullets.getLaser().get(i).getDamage());
				}
				if(alien.getAliens().get(j).getHealth()<=0)
				{
					if(alien.getAliens().get(j).getCanAddScore()==true)
					{
						score+=(5*level);
					}
					alien.getAliens().get(j).setCanAddScore(false);
					alien.getAliens().get(j).setAlive(false);
				}
				}
			}	
		
		}
		
		
		for(int j=aShots; j>0; j--)
		{
			for(int i=j; i<2100; i++)
			{
				timer.start();
			if(bullets.getAlienGun().get(i).getExists()==true && shipExists==true)
			{
			if(new Rectangle(bullets.getAlienGun().get(i).getaX(), bullets.getAlienGun().get(i).getaY(), 10, 10).intersects(new Rectangle(shipXpos-1, shipYpos-14, 41, 18))
					|| new Rectangle(bullets.getAlienGun().get(i).getaX(), bullets.getAlienGun().get(i).getaY(), 10, 10).intersects(new Rectangle(shipXpos+8, shipYpos+4, 24, 8))
					|| new Rectangle(bullets.getAlienGun().get(i).getaX(), bullets.getAlienGun().get(i).getaY(), 10, 10).intersects(new Rectangle(shipXpos+8, shipYpos-26, 24, 11))
					|| new Rectangle(bullets.getAlienGun().get(i).getaX(), bullets.getAlienGun().get(i).getaY(), 10, 10).intersects(new Rectangle(shipXpos+15, shipYpos-50, 9, 24)))
			{
				shipHealth=shipHealth-bullets.getAlienGun().get(i).getDamage();
				bullets.getAlienGun().get(i).setExists(false);
				
				if(shipHealth<=0)
				{
					lives--;
					shipExists=false;
				}
			}
			}
		}
		}
		
		for(int j=0; j<alien.getAliens().size(); j++)
		{
			timer.start();
			if(alien.getAliens().get(j).isAlive()==true && shipExists==true)
			{
			if(new Rectangle(alien.getAliens().get(j).getXpos(), alien.getAliens().get(j).getYpos(), 40, 25).intersects(new Rectangle(shipXpos-1, shipYpos-14, 41, 18))
					|| new Rectangle(alien.getAliens().get(j).getXpos(), alien.getAliens().get(j).getYpos(), 40, 25).intersects(new Rectangle(shipXpos+8, shipYpos+4, 24, 8))
					|| new Rectangle(alien.getAliens().get(j).getXpos(), alien.getAliens().get(j).getYpos(), 40, 25).intersects(new Rectangle(shipXpos+8, shipYpos-26, 24, 11))
					|| new Rectangle(alien.getAliens().get(j).getXpos(), alien.getAliens().get(j).getYpos(), 40, 25).intersects(new Rectangle(shipXpos+15, shipYpos-50, 9, 24)))
			{
				shipHealth=0;
				alien.getAliens().get(j).setAlive(false);
				
				if(shipHealth<=0)
				{
					lives--;
					shipExists=false;
				}
			}
			}
		}
		
		///////////////////////////////////////Bullet health, no work :(
		if(level>=1 && bulletHealth==true)
		{
		for(int j=aShots; j>0; j--)
		{
			for(int i=j; i<2100; i++)
			{
				for(int k=0; k<bullets.getBullets().size(); k++)
				{
				
				timer.start();
				if(bullets.getBullets().get(k).getExists1ax()==true && bullets.getAlienGun().get(i).getExists()==true)
				{
				if(new Rectangle(bullets.getBullets().get(k).get1aX(), bullets.getBullets().get(k).getL1Y(), 2, 4).intersects(new Rectangle(bullets.getAlienGun().get(i).getaX(), bullets.getAlienGun().get(i).getaY(), 10, 10)))
				{
					bullets.getAlienGun().get(i).setHealth(bullets.getAlienGun().get(i).getHealth()-bullets.getBullets().get(k).get1axDamage());
					bullets.getBullets().get(k).set1axHealth(bullets.getBullets().get(k).get1axHealth()-bullets.getAlienGun().get(i).getDamage());
					if(bullets.getAlienGun().get(i).getHealth()<=0)
					{
						bullets.getAlienGun().get(i).setExists(false);
					}
					if(bullets.getBullets().get(i).get1axHealth()<=0)
					{
						bullets.getBullets().get(i).setExists1ax(false);
					}
	
					
				}
				}
				if(bullets.getBullets().get(k).getExists1bx()==true && bullets.getAlienGun().get(i).getExists()==true)
				{
				if(new Rectangle(bullets.getBullets().get(i).get1bX(), bullets.getBullets().get(i).getL1Y(), 2, 4).intersects(new Rectangle(bullets.getAlienGun().get(i).getaX(), bullets.getAlienGun().get(i).getaY(), 10, 10)))
				{
					bullets.getAlienGun().get(i).setHealth(bullets.getAlienGun().get(i).getHealth()-bullets.getBullets().get(k).get1bxDamage());
					bullets.getBullets().get(k).set1bxHealth(bullets.getBullets().get(k).get1bxHealth()-bullets.getAlienGun().get(i).getDamage());
					if(bullets.getAlienGun().get(i).getHealth()<=0)
					{
						bullets.getAlienGun().get(i).setExists(false);
					}
					if(bullets.getBullets().get(i).get1bxHealth()<=0)
					{
						bullets.getBullets().get(i).setExists1bx(false);
					}
				}
				}
				if(bullets.getBullets().get(k).getExists2ax()==true && bullets.getAlienGun().get(i).getExists()==true)
				{
				if(new Rectangle(bullets.getBullets().get(i).get2aX(), bullets.getBullets().get(i).getL2Y(), 2, 4).intersects(new Rectangle(bullets.getAlienGun().get(i).getaX(), bullets.getAlienGun().get(i).getaY(), 10, 10)))
				{
					bullets.getAlienGun().get(i).setHealth(bullets.getAlienGun().get(i).getHealth()-bullets.getBullets().get(k).get2axDamage());
					bullets.getBullets().get(k).set2axHealth(bullets.getBullets().get(k).get2axHealth()-bullets.getAlienGun().get(i).getDamage());
					if(bullets.getAlienGun().get(i).getHealth()<=0)
					{
						bullets.getAlienGun().get(i).setExists(false);
					}
					if(bullets.getBullets().get(i).get2axHealth()<=0)
					{
						bullets.getBullets().get(i).setExists2ax(false);
					}
				}
				}
				if(bullets.getBullets().get(k).getExists2bx()==true && bullets.getAlienGun().get(i).getExists()==true)
				{
				if(new Rectangle(bullets.getBullets().get(i).get2bX(), bullets.getBullets().get(i).getL2Y(), 2, 4).intersects(new Rectangle(bullets.getAlienGun().get(i).getaX(), bullets.getAlienGun().get(i).getaY(), 10, 10)))
				{
					bullets.getAlienGun().get(i).setHealth(bullets.getAlienGun().get(i).getHealth()-bullets.getBullets().get(k).get2bxDamage());
					bullets.getBullets().get(k).set2bxHealth(bullets.getBullets().get(k).get2bxHealth()-bullets.getAlienGun().get(i).getDamage());
					if(bullets.getAlienGun().get(i).getHealth()<=0)
					{
						bullets.getAlienGun().get(i).setExists(false);
					}
					if(bullets.getBullets().get(i).get2bxHealth()<=0)
					{
						bullets.getBullets().get(i).setExists2bx(false);
					}
				}
				}
				
			}	
		
		}
		}
		}
		
		
		
		for(int j=0; j<alien.getAliens().size(); j++)
		{
			if(alien.getAliens().get(j).getYpos()>=800 && alien.getAliens().get(j).isAlive()==true)
			{
				shipHealth=0;
				shipExists=false;
				lives=0;
			}
		}
		
		
		
		if(alien.getAliens().get(0).isAlive()==false)
		{
			for(int i=0; i<100; i++)
			{
			bullets.aGuns.get(i).setExists(false);
			}
		}
		if(alien.getAliens().get(1).isAlive()==false)
		{
			for(int i=100; i<200; i++)
			{
			bullets.aGuns.get(i).setExists(false);
			}
		}
		if(alien.getAliens().get(2).isAlive()==false)
		{
			for(int i=200; i<300; i++)
			{
			bullets.aGuns.get(i).setExists(false);
			}
		}
		if(alien.getAliens().get(3).isAlive()==false)
		{
			for(int i=300; i<400; i++)
			{
			bullets.aGuns.get(i).setExists(false);
			}
		}
		if(alien.getAliens().get(4).isAlive()==false)
		{
			for(int i=400; i<500; i++)
			{
			bullets.aGuns.get(i).setExists(false);
			}
		}
		if(alien.getAliens().get(5).isAlive()==false)
		{
			for(int i=500; i<600; i++)
			{
			bullets.aGuns.get(i).setExists(false);
			}
		}
		if(alien.getAliens().get(6).isAlive()==false)
		{
			for(int i=600; i<700; i++)
			{
			bullets.aGuns.get(i).setExists(false);
			}
		}
		if(alien.getAliens().get(7).isAlive()==false)
		{
			for(int i=700; i<800; i++)
			{
			bullets.aGuns.get(i).setExists(false);
			}
		}
		if(alien.getAliens().get(8).isAlive()==false)
		{
			for(int i=800; i<900; i++)
			{
			bullets.aGuns.get(i).setExists(false);
			}
		}
		if(alien.getAliens().get(9).isAlive()==false)
		{
			for(int i=900; i<1000; i++)
			{
			bullets.aGuns.get(i).setExists(false);
			}
		}
		if(alien.getAliens().get(10).isAlive()==false)
		{
			for(int i=1000; i<1100; i++)
			{
			bullets.aGuns.get(i).setExists(false);
			}
		}
		if(alien.getAliens().get(11).isAlive()==false)
		{
			for(int i=1100; i<1200; i++)
			{
			bullets.aGuns.get(i).setExists(false);
			}
		}
		if(alien.getAliens().get(12).isAlive()==false)
		{
			for(int i=1200; i<1300; i++)
			{
			bullets.aGuns.get(i).setExists(false);
			}
		}
		if(alien.getAliens().get(13).isAlive()==false)
		{
			for(int i=1300; i<1400; i++)
			{
			bullets.aGuns.get(i).setExists(false);
			}
		}
		if(alien.getAliens().get(14).isAlive()==false)
		{
			for(int i=1400; i<1500; i++)
			{
			bullets.aGuns.get(i).setExists(false);
			}
		}
		if(alien.getAliens().get(15).isAlive()==false)
		{
			for(int i=1500; i<1600; i++)
			{
			bullets.aGuns.get(i).setExists(false);
			}
		}
		if(alien.getAliens().get(16).isAlive()==false)
		{
			for(int i=1600; i<1700; i++)
			{
			bullets.aGuns.get(i).setExists(false);
			}
		}
		if(alien.getAliens().get(17).isAlive()==false)
		{
			for(int i=1700; i<1800; i++)
			{
			bullets.aGuns.get(i).setExists(false);
			}
		}
		if(alien.getAliens().get(18).isAlive()==false)
		{
			for(int i=1800; i<1900; i++)
			{
			bullets.aGuns.get(i).setExists(false);
			}
		}
		if(alien.getAliens().get(19).isAlive()==false)
		{
			for(int i=1900; i<2000; i++)
			{
			bullets.aGuns.get(i).setExists(false);
			}
		}
		if(alien.getAliens().get(20).isAlive()==false)
		{
			for(int i=2000; i<2100; i++)
			{
			bullets.aGuns.get(i).setExists(false);
			}
		}
		
		
		
		
		
		if(alien.getAliens().get(0).getXpos()>=160)
		{
			timer.start();	
			
		alien.setXdir(-alien.getXdir());
		bullets.setXdir(-bullets.getXdir());
		alien.moveDown();
		bullets.moveDown();
		wallhits++;
		
		}
		if(alien.getAliens().get(0).getXpos()<0)
		{
			timer.start();
		alien.setXdir(-alien.getXdir());
		bullets.setXdir(-bullets.getXdir());
		alien.moveDown();
		bullets.moveDown();
		wallhits++;
		}
		
		if(alien.getXdir()>0)
		{
			timer.start();
		alien.moveLeft();
		bullets.moveLeft();
		}
		if(alien.getXdir()<0)
		{
			timer.start();
		alien.moveRight();
		bullets.moveRight();
		}
		
		
		
		if(slowPotionUsed==false)
		{
		for(int i=0; i<800; i+=20)
		{
		if(level<5)
		{
		if(bullets.getAlienGun().get(99).getaX()==50 && bullets.getAlienGun().get(99).getaY()==i)
		{
			aGun1Shoot=true;
			aShots++;
		}
		}
		if(level>=5 && level<10)
		{
		if(bullets.getAlienGun().get(99).getaX()==50 && bullets.getAlienGun().get(99).getaY()==i || bullets.getAlienGun().get(99).getaX()==51 && bullets.getAlienGun().get(99).getaY()==i)
		{
			aGun1Shoot=true;
			aShots++;
		}
		}
		
		if(level>=10 && level<20)
		{
		if(bullets.getAlienGun().get(99).getaX()==50 && bullets.getAlienGun().get(99).getaY()==i || bullets.getAlienGun().get(99).getaX()==51 && bullets.getAlienGun().get(99).getaY()==i || bullets.getAlienGun().get(99).getaX()==52 && bullets.getAlienGun().get(99).getaY()==i)
		{
			aGun1Shoot=true;
			aShots++;
		}
		}
		
		if(level>=20 && level<30)
		{
		if(bullets.getAlienGun().get(99).getaX()==50 && bullets.getAlienGun().get(99).getaY()==i || bullets.getAlienGun().get(99).getaX()==51 && bullets.getAlienGun().get(99).getaY()==i || bullets.getAlienGun().get(99).getaX()==52 && bullets.getAlienGun().get(99).getaY()==i || bullets.getAlienGun().get(99).getaX()==53 && bullets.getAlienGun().get(99).getaY()==i)
		{
			aGun1Shoot=true;
			aShots++;
		}
		}
		
		if(level>=30 && level<40)
		{
		if(bullets.getAlienGun().get(99).getaX()==50 && bullets.getAlienGun().get(99).getaY()==i || bullets.getAlienGun().get(99).getaX()==51 && bullets.getAlienGun().get(99).getaY()==i || bullets.getAlienGun().get(99).getaX()==52 && bullets.getAlienGun().get(99).getaY()==i || bullets.getAlienGun().get(99).getaX()==53 && bullets.getAlienGun().get(99).getaY()==i || bullets.getAlienGun().get(99).getaX()==54 && bullets.getAlienGun().get(99).getaY()==i)
		{
			aGun1Shoot=true;
			aShots++;
		}
		}
		
		if(level>=40 && level<50)
		{
		if(bullets.getAlienGun().get(99).getaX()==50 && bullets.getAlienGun().get(99).getaY()==i || bullets.getAlienGun().get(99).getaX()==51 && bullets.getAlienGun().get(99).getaY()==i || bullets.getAlienGun().get(99).getaX()==52 && bullets.getAlienGun().get(99).getaY()==i || bullets.getAlienGun().get(99).getaX()==53 && bullets.getAlienGun().get(99).getaY()==i || bullets.getAlienGun().get(99).getaX()==54 && bullets.getAlienGun().get(99).getaY()==i || bullets.getAlienGun().get(99).getaX()==55 && bullets.getAlienGun().get(99).getaY()==i)
		{
			aGun1Shoot=true;
			aShots++;
		}
		}
		
		}
		}
		if(slowPotionUsed==true)
		{
			for(int i=0; i<800; i+=20)
			{
			if(level<5)
			{
			if(bullets.getAlienGun().get(99).getaX()==50 && bullets.getAlienGun().get(99).getaY()==i)
			{
				aGun1Shoot=true;
				aShots++;
			}
			}
			if(level>=5 && level<15)
			{
			if(bullets.getAlienGun().get(99).getaX()==50 && bullets.getAlienGun().get(99).getaY()==i)
			{
				aGun1Shoot=true;
				aShots++;
			}
			}
			
			if(level>=15 && level<30)
			{
			if(bullets.getAlienGun().get(99).getaX()==50 && bullets.getAlienGun().get(99).getaY()==i)
			{
				aGun1Shoot=true;
				aShots++;
			}
			}
			
			if(level>=30 && level<45)
			{
			if(bullets.getAlienGun().get(99).getaX()==50 && bullets.getAlienGun().get(99).getaY()==i)
			{
				aGun1Shoot=true;
				aShots++;
			}
			}
			
			if(level>=45 && level<60)
			{
			if(bullets.getAlienGun().get(99).getaX()==50 && bullets.getAlienGun().get(99).getaY()==i)
			{
				aGun1Shoot=true;
				aShots++;
			}
			}
			
			if(level>=60 && level<75)
			{
			if(bullets.getAlienGun().get(99).getaX()==50 && bullets.getAlienGun().get(99).getaY()==i)
			{
				aGun1Shoot=true;
				aShots++;
			}
			}
			
			}
		}
		
		/*	for(int q=20; q<800; q+=60)
		{
		
		if(bullets.getAlienGun().get(99).getaX()==150 && bullets.getAlienGun().get(99).getaY()==q)
		{
			
			aGun2Shoot=true;
			aShots++;
		}
		}
		
		for(int z=40; z<800; z+=60)
		{
		
		if(bullets.getAlienGun().get(99).getaX()==150 && bullets.getAlienGun().get(99).getaY()==z)
		{
			
			aGun3Shoot=true;
			aShots++;
		}
		}*/
		
		
		
		
	if(aGun1Shoot==true)
	{
	for(int x=aShots; x>0; x--)
	{
		for(int i=x; i<2100; i+=100)
		{
			timer.start();
			bullets.getAlienGun().get(i).setShot(true);
			
			if(bullets.getAlienGun().get(i).getaX()>495)
			{
				bullets.getAlienGun().get(i).changeXdir();
			}
			else if(bullets.getAlienGun().get(i).getaX()<=5)
			{
				bullets.getAlienGun().get(i).changeXdir();
			}
			if(frenzy==true)
			{
			bullets.getAlienGun().get(i).setType(alien.getAliens().get(i/100).getType());
			}
		
		bullets.getAlienGun().get(i).setaY(bullets.getAlienGun().get(i).getaY()+bullets.getAlienGun().get(i).getYdir());
		bullets.getAlienGun().get(i).setaX(bullets.getAlienGun().get(i).getaX()+bullets.getAlienGun().get(i).getXdir());
		
		}
	}
	}
	
	/*if(aGun2Shoot==true)
	{
	for(int w=aShots; w>0; w--)
	{
		for(int i=w+100; i<2100; i+=300)
		{
			bullets.getAlienGun().get(i).setShot(true);
			
			if(bullets.getAlienGun().get(i).getaX()>=500)
			{
				bullets.getAlienGun().get(i).changeXdir();
			}
			else if(bullets.getAlienGun().get(i).getaX()<=0)
			{
				bullets.getAlienGun().get(i).changeXdir();
			}
		
		bullets.getAlienGun().get(i).setaY(bullets.getAlienGun().get(i).getaY()+aGunYdir);
		bullets.getAlienGun().get(i).setaX(bullets.getAlienGun().get(i).getaX()+bullets.getAlienGun().get(i).getXdir());
		
		}
	}
	
	}
	
	
	if(aGun3Shoot==true)
	{
	for(int p=aShots; p>0; p--)
	{
		for(int i=p+200; i<2100; i+=300)
		{
			bullets.getAlienGun().get(i).setShot(true);
			
			if(bullets.getAlienGun().get(i).getaX()>=500)
			{
				bullets.getAlienGun().get(i).changeXdir();
			}
			else if(bullets.getAlienGun().get(i).getaX()<=0)
			{
				bullets.getAlienGun().get(i).changeXdir();
			}
		
		bullets.getAlienGun().get(i).setaY(bullets.getAlienGun().get(i).getaY()+aGunYdir);
		bullets.getAlienGun().get(i).setaX(bullets.getAlienGun().get(i).getaX()+bullets.getAlienGun().get(i).getXdir());
		
		}
	}
	
	}*/
	
	////////////////POTIONS/////////////////
	
	
	if(shipJustDied==false || potionCollected==false)////////possible issue
	{
	if(level<5)
	{
	if(bullets.getAlienGun().get(99).getaX()==50 && bullets.getAlienGun().get(99).getaY()==80)
	{
		
		
		if(damagePotion==true)
		{
			makePotion=1;
			potionExists=true;
		}
		if(healthPotion==true)
		{
			makePotion=2;
			potionExists=true;
		}
		if(slowPotion==true)
		{
			makePotion=3;
			potionExists=true;
		}
		if(missilePotion==true)
		{
			makePotion=4;
			potionExists=true;
		}
	}
	}
	if(level>=5 && level<15)
	{
	if(bullets.getAlienGun().get(99).getaX()==50 && bullets.getAlienGun().get(99).getaY()==80 || bullets.getAlienGun().get(99).getaX()==51 && bullets.getAlienGun().get(99).getaY()==80)
	{
		
		
		
		if(damagePotion==true)
		{
			makePotion=1;
			potionExists=true;
		}
		if(healthPotion==true)
		{
			makePotion=2;
			potionExists=true;
		}
		if(slowPotion==true)
		{
			makePotion=3;
			potionExists=true;
		}
		if(missilePotion==true)
		{
			makePotion=4;
			potionExists=true;
		}
	}
	}
	
	if(level>=15 && level<30)
	{
	if(bullets.getAlienGun().get(99).getaX()==50 && bullets.getAlienGun().get(99).getaY()==80 || bullets.getAlienGun().get(99).getaX()==51 && bullets.getAlienGun().get(99).getaY()==80 || bullets.getAlienGun().get(99).getaX()==52 && bullets.getAlienGun().get(99).getaY()==80)
	{
		
		
		
		if(damagePotion==true)
		{
			makePotion=1;
			potionExists=true;
		}
		if(healthPotion==true)
		{
			makePotion=2;
			potionExists=true;
		}
		if(slowPotion==true)
		{
			makePotion=3;
			potionExists=true;
		}
		if(missilePotion==true)
		{
			makePotion=4;
			potionExists=true;
		}
	}
	}
	
	if(level>=30 && level<45)
	{
	if(bullets.getAlienGun().get(99).getaX()==50 && bullets.getAlienGun().get(99).getaY()==80 || bullets.getAlienGun().get(99).getaX()==51 && bullets.getAlienGun().get(99).getaY()==80 || bullets.getAlienGun().get(99).getaX()==52 && bullets.getAlienGun().get(99).getaY()==80 || bullets.getAlienGun().get(99).getaX()==53 && bullets.getAlienGun().get(99).getaY()==80)
	{
		
		
		
		if(damagePotion==true)
		{
			makePotion=1;
			potionExists=true;
		}
		if(healthPotion==true)
		{
			makePotion=2;
			potionExists=true;
		}
		if(slowPotion==true)
		{
			makePotion=3;
			potionExists=true;
		}
		if(missilePotion==true)
		{
			makePotion=4;
			potionExists=true;
		}
	}
	}
	
	if(level>=45 && level<60)
	{
	if(bullets.getAlienGun().get(99).getaX()==50 && bullets.getAlienGun().get(99).getaY()==80 || bullets.getAlienGun().get(99).getaX()==51 && bullets.getAlienGun().get(99).getaY()==80 || bullets.getAlienGun().get(99).getaX()==52 && bullets.getAlienGun().get(99).getaY()==80 || bullets.getAlienGun().get(99).getaX()==53 && bullets.getAlienGun().get(99).getaY()==80 || bullets.getAlienGun().get(99).getaX()==54 && bullets.getAlienGun().get(99).getaY()==80)
	{
		
		if(damagePotion==true)
		{
			makePotion=1;
			potionExists=true;
		}
		if(healthPotion==true)
		{
			makePotion=2;
			potionExists=true;
		}
		if(slowPotion==true)
		{
			makePotion=3;
			potionExists=true;
		}
		if(missilePotion==true)
		{
			makePotion=4;
			potionExists=true;
		}
	}
	}
	
	if(level>=60 && level<75)
	{
	if(bullets.getAlienGun().get(99).getaX()==50 && bullets.getAlienGun().get(99).getaY()==80 || bullets.getAlienGun().get(99).getaX()==51 && bullets.getAlienGun().get(99).getaY()==80 || bullets.getAlienGun().get(99).getaX()==52 && bullets.getAlienGun().get(99).getaY()==80 || bullets.getAlienGun().get(99).getaX()==53 && bullets.getAlienGun().get(99).getaY()==80 || bullets.getAlienGun().get(99).getaX()==54 && bullets.getAlienGun().get(99).getaY()==80 || bullets.getAlienGun().get(99).getaX()==55 && bullets.getAlienGun().get(99).getaY()==80)
	{
		
		
		
		if(damagePotion==true)
		{
			makePotion=1;
			potionExists=true;
		}
		if(healthPotion==true)
		{
			makePotion=2;
			potionExists=true;
		}
		if(slowPotion==true)
		{
			makePotion=3;
			potionExists=true;
		}
		if(missilePotion==true)
		{
			makePotion=4;
			potionExists=true;
		}
	}
	}
	}
	
		
		if(allAdead==false && shipExists==true && potionExists==true)
		{
			timer.start();
		if(new Rectangle(xPotion, yPotion-4, 16, 20).intersects(new Rectangle(shipXpos-1, shipYpos-14, 41, 18))
				|| new Rectangle(xPotion, yPotion-4, 16, 20).intersects(new Rectangle(shipXpos+8, shipYpos+4, 24, 8))
				|| new Rectangle(xPotion, yPotion-4, 16, 20).intersects(new Rectangle(shipXpos+8, shipYpos-26, 24, 11))
				|| new Rectangle(xPotion, yPotion-4, 16, 20).intersects(new Rectangle(shipXpos+15, shipYpos-50, 9, 24)))
		{
			potionCollected=true;
			if(damagePotion==true && makePotion==1)
			{
				damagePotions++;
				potionExists=false;
			}
			
			if(healthPotion==true && makePotion==2)
			{
				healthPotions++;
				potionExists=false;
			}
			
			if(slowPotion==true && makePotion==3)
			{
				slowPotions++;
				potionExists=false;
			}
			if(missilePotion==true && makePotion==4)
			{
				missilePotions++;
				potionExists=false;
			}
			
			
		}
		}
	
	
	 
	
 	
	
	if(mFireLimit==true)
	{
		if(level<10)
		{
			if(alien.getAliens().get(20).getYpos()<=160)
			{
				cantFireM=true;
			}
			else if(alien.getAliens().get(20).getYpos()>160)
			{
				cantFireM=false;
			}
		}
	}
		
		}
		
		repaint();
		
	}
		
	

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e)
	{
		if(e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode()== KeyEvent.VK_W)
		{
			play=true;
			moveUp();
			repaint();
		}
		if(e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode()== KeyEvent.VK_S)
		{
			play=true;
			moveDown();
			repaint();
		}
		if(e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode()== KeyEvent.VK_A)
		{
			play=true;
			moveLeft();
			repaint();
		}
		if(e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode()== KeyEvent.VK_D)
		{
			play=true;
			moveRight();
			repaint();
		}
		if(e.getKeyCode()==KeyEvent.VK_SPACE)
		{ 
			if(allAdead==false && shipExists==true && choice==true)
			{
			play=true;
			Bfire=true;
			if(Bshotcount<Bammo-1)
			{
			Bshotcount++;
			}
			bullets.getBullets().get(Bshotcount).setShot(true);
			}
			
			
		}
		if(e.getKeyCode()==KeyEvent.VK_M)
		{ 
		if(allAdead==false && shipExists==true && choice==true)
		{
			
			if(cantFireM==false)
			{
			play=true;
			Mfire=true;
			if(Mshotcount<Mammo-1)
			{
			Mshotcount++;
			}
			
			bullets.getMissiles().get(Mshotcount).setShot(true);
			}
		}
			
		}
		if(e.getKeyCode()==KeyEvent.VK_L)
		{ 
			if(allAdead==false && shipExists==true && choice==true)
			{
			play=true;
			if(Lfire==false && Lshotcount<Lammo)
			{
			Lfire=true;
			bullets.getLaser().get(0).setExists(true);
			
			if(Lshotcount<Lammo)
			{
			Lshotcount++;
			}
			
			}
			
			
			else
			{
				Lfire=false;
				bullets.getLaser().get(0).setExists(false);
			}
			}
			
		  
		}
		if(e.getKeyCode()==KeyEvent.VK_ENTER)
		{
			if(HTP==false)
			{
			if(allAdead==true)
			{
				shipJustDied=false;
				seeRecords=false;
				level++;
				potionExists=false;
				damagePotion=false;
				healthPotion=false;
				slowPotion=false;
				missilePotion=false;
				potionCollected=false;
				slowPotionUsed=false;
				
				Random randooo=new Random();
				potion=randooo.nextInt(4)+1;
				if(potion==1)
				{
					damagePotion=true;
				}
				if(potion==2)
				{
					healthPotion=true;
				}
				if(potion==3)
				{
					slowPotion=true;
				}
				if(potion==4)
				{
					missilePotion=true;
				}
				
				
				Random rando=new Random();
				xPotion=rando.nextInt(460)+20;
				yPotion=rando.nextInt(150)+50;
				
				
				
				for(int i=0; i<bullets.getAlienGun().size(); i++)
				{
				bullets.getAlienGun().get(i).setYspeed(2+(level/6));
				bullets.getAlienGun().get(i).setXspeed(2+(level/6));
				
				}
				
				if(level==5)
				{
					alien.setYdir(5);
					bullets.setYdir(5);
					
				}
				
				if(level==20)
				{
					alien.setYdir(10);
					bullets.setYdir(10);
					
				}
				
				
				
				for(int i=0; i<bullets.getBullets().size(); i++)
				{
					bullets.getBullets().get(i).setYdir(-3-(level/6));
				}
				for(int i=0; i<bullets.getMissiles().size(); i++)
				{					
					bullets.getMissiles().get(i).setYdir(-3-(level/6));
				}
					
				
				
				if(level%2==0 && level>5)
				{
				bDamageAdd+=1;
				}
				
				
					
				
				if(level<5)
				{
					alien.setXdir(-1);
					bullets.setXdir(-1);
				}
				if(level>=5 && level<15)
				{
					alien.setXdir(-2);
					bullets.setXdir(-2);
				}
				if(level>=15 && level<30)
				{
					alien.setXdir(-3);
					bullets.setXdir(-3);
				}
				if(level>=30 && level<45)
				{
					alien.setXdir(-4);
					bullets.setXdir(-4);
				}
				if(level>=45 && level<60)
				{
					alien.setXdir(-5);
					bullets.setXdir(-5);
				}
				if(level>=60 && level<75)
				{
					alien.setXdir(-6);
					bullets.setXdir(-6);
				}
			
				play=false;
				Bammo=10001;
				Mammo=7;
				if(level>=15)
				{
					Mammo=11;
				}
				Bshotcount=0;
				Mshotcount=0;
				//shipHealth=300;
				aShots=0;
				shipXpos=250;
				shipYpos=600;
				bullets.getLaser().get(0).setExists(false);
				bullets.getLaser().get(0).setlX(bullets.getLaser().get(0).getloX());
				bullets.getLaser().get(0).setlY(bullets.getLaser().get(0).getloY());
				
				
				
				
				for(int i=0; i<alien.getAliens().size(); i++)
				{
					alien.getAliens().get(i).setAlive(true);
					alien.getAliens().get(i).setCanAddScore(true);
					alien.getAliens().get(i).setXpos(alien.getAliens().get(i).getoXpos());
					alien.getAliens().get(i).setYpos(alien.getAliens().get(i).getoYpos());
					alien.getAliens().get(i).setHealth(100+(level*10));
					alien.getAliens().get(i).setFrozen(false);
					
					
				}
				for(int j=0; j<bullets.getBullets().size(); j++)
				{
				bullets.getBullets().get(j).setContact1ax(false);
				bullets.getBullets().get(j).setContact1bx(false);
				bullets.getBullets().get(j).setContact2ax(false);
				bullets.getBullets().get(j).setContact2bx(false);
				bullets.getBullets().get(j).setExists1ax(true);
				bullets.getBullets().get(j).setExists1bx(true);
				bullets.getBullets().get(j).setExists2ax(true);
				bullets.getBullets().get(j).setExists2bx(true);
				bullets.getBullets().get(j).setShot(false);
				bullets.getBullets().get(j).setDamage2x(false);
				
				bullets.getBullets().get(j).set1aX(bullets.getBullets().get(j).geto1aX());
				bullets.getBullets().get(j).set1bX(bullets.getBullets().get(j).geto1bX());
				bullets.getBullets().get(j).set2aX(bullets.getBullets().get(j).geto2aX());
				bullets.getBullets().get(j).set2bX(bullets.getBullets().get(j).geto2bX());
				bullets.getBullets().get(j).setL1Y(bullets.getBullets().get(j).getoL1Y());
				bullets.getBullets().get(j).setL2Y(bullets.getBullets().get(j).getoL2Y());
				
				
				bullets.getBullets().get(j).set1axDamage(5+bDamageAdd);
				bullets.getBullets().get(j).set1bxDamage(5+bDamageAdd);
				bullets.getBullets().get(j).set2axDamage(5+bDamageAdd);
				bullets.getBullets().get(j).set2bxDamage(5+bDamageAdd);
				
				
				}
				for(int j=0; j<Mammo; j++)
				{
				bullets.getMissiles().get(j).setContact(false);
				bullets.getMissiles().get(j).setShot(false);
				bullets.getMissiles().get(j).setmX(bullets.getMissiles().get(j).getmoX());
				bullets.getMissiles().get(j).setmY(bullets.getMissiles().get(j).getmoY());
				bullets.getMissiles().get(j).setDamage(10000);
				}
				
				for(int i=0; i<2100; i++)
				{
					
					bullets.getAlienGun().get(i).setShot(false);
					bullets.getAlienGun().get(i).setaX(bullets.getAlienGun().get(i).getoaX());
					bullets.getAlienGun().get(i).setaY(bullets.getAlienGun().get(i).getoaY());
					bullets.getAlienGun().get(i).setExists(true);
					if(level<=15)
					{
					bullets.getAlienGun().get(i).setDamage(30+(level*2));
					}
					else if(level>15)
					{
						bullets.getAlienGun().get(i).setDamage(30+(level));
					}
					bullets.getAlienGun().get(i).setFrozen(false);
				}
				allAdead=false;
			}
			if(shipExists==false && lives>0)
			{
				seeRecords=false;
				potionExists=false;
				shipJustDied=true;
					Lfire=false;
					Bammo=10001;
					Mammo=7;
					if(level>=15)
					{
						Mammo=11;
					}
					Bshotcount=0;
					Mshotcount=0;
					Lshotcount=0;
					aShots=0;
					shipHealth=300;
					play=false;
					shipXpos=250;
					shipYpos=600;
					bullets.getLaser().get(0).setlX(bullets.getLaser().get(0).getloX());
					bullets.getLaser().get(0).setlY(bullets.getLaser().get(0).getloY());
					shipExists=true;
					
					Random randommm=new Random();
					xPotion=randommm.nextInt(460)+20;
					yPotion=randommm.nextInt(150)+50;
					
					
					for(int i=0; i<bullets.getAlienGun().size(); i++)
					{
					bullets.getAlienGun().get(i).setYspeed(2+(level/6));
					bullets.getAlienGun().get(i).setXspeed(2+(level/6));
					
					}
					
					for(int i=0; i<bullets.getBullets().size(); i++)
					{
						bullets.getBullets().get(i).setYdir(-3-(level/6));
					}
					for(int i=0; i<bullets.getMissiles().size(); i++)
					{					
						bullets.getMissiles().get(i).setYdir(-3-(level/6));
					}
				
					
					
					for(int i=0; i<alien.getAliens().size(); i++)
					{
						if(alien.getAliens().get(i).isAlive()==true)
						{
						alien.getAliens().get(i).setCanAddScore(true);
						alien.getAliens().get(i).setXpos(alien.getAliens().get(i).getoXpos());
						alien.getAliens().get(i).setYpos(alien.getAliens().get(i).getoYpos());
						alien.getAliens().get(0).setXpos(alien.getAliens().get(0).getoXpos());
						alien.getAliens().get(0).setYpos(alien.getAliens().get(0).getoYpos());
						}
						
						
					}
					for(int i=0; i<2100; i++)
					{
						if(bullets.getAlienGun().get(i).getExists()==true)
						{
						bullets.getAlienGun().get(i).setShot(false);	
						bullets.getAlienGun().get(i).setaX(bullets.getAlienGun().get(i).getoaX());
						bullets.getAlienGun().get(i).setaY(bullets.getAlienGun().get(i).getoaY());
						bullets.getAlienGun().get(99).setaX(bullets.getAlienGun().get(99).getoaX());
						bullets.getAlienGun().get(99).setaY(bullets.getAlienGun().get(99).getoaY());
						if(level<=15)
						{
						bullets.getAlienGun().get(i).setDamage(30+(level*2));
						}
						else if(level>15)
						{
							bullets.getAlienGun().get(i).setDamage(30+(level));
						}
						}
						
					}
					for(int j=0; j<bullets.getBullets().size(); j++)
					{
					bullets.getBullets().get(j).setContact1ax(false);
					bullets.getBullets().get(j).setContact1bx(false);
					bullets.getBullets().get(j).setContact2ax(false);
					bullets.getBullets().get(j).setContact2bx(false);
					bullets.getBullets().get(j).setExists1ax(true);
					bullets.getBullets().get(j).setExists1bx(true);
					bullets.getBullets().get(j).setExists2ax(true);
					bullets.getBullets().get(j).setExists2bx(true);
					bullets.getBullets().get(j).setShot(false);
					
					bullets.getBullets().get(j).set1aX(bullets.getBullets().get(j).geto1aX());
					bullets.getBullets().get(j).set1bX(bullets.getBullets().get(j).geto1bX());
					bullets.getBullets().get(j).set2aX(bullets.getBullets().get(j).geto2aX());
					bullets.getBullets().get(j).set2bX(bullets.getBullets().get(j).geto2bX());
					bullets.getBullets().get(j).setL1Y(bullets.getBullets().get(j).getoL1Y());
					bullets.getBullets().get(j).setL2Y(bullets.getBullets().get(j).getoL2Y());
					
					bullets.getBullets().get(j).set1axDamage(5+bDamageAdd);
					bullets.getBullets().get(j).set1bxDamage(5+bDamageAdd);
					bullets.getBullets().get(j).set2axDamage(5+bDamageAdd);
					bullets.getBullets().get(j).set2bxDamage(5+bDamageAdd);	

					
					}
					for(int j=0; j<Mammo; j++)
					{
					bullets.getMissiles().get(j).setContact(false);
					bullets.getMissiles().get(j).setShot(false);
					bullets.getMissiles().get(j).setmX(bullets.getMissiles().get(j).getmoX());
					bullets.getMissiles().get(j).setmY(bullets.getMissiles().get(j).getmoY());
					bullets.getMissiles().get(j).setDamage(10000);
					}
						
					
					
				
			}
			else if(shipExists==false && lives<=0)
			{
				seeRecords=false;
				potionExists=false;
				damagePotion=false;
				healthPotion=false;
				slowPotion=false;
				missilePotion=false;
				potionCollected=false;
				shipJustDied=false;
				slowPotionUsed=false;
				
				damagePotions=0;
				healthPotions=0;
				slowPotions=0;
				missilePotions=0;
				
				Random randoo=new Random();
				potion=randoo.nextInt(4)+1;
				if(potion==1)
				{
					damagePotion=true;
					
				}
				if(potion==2)
				{
					healthPotion=true;
					
				}
				if(potion==3)
				{
					slowPotion=true;
				}
				if(potion==4)
				{
					missilePotion=true;
				}
				
				Random rando=new Random();
				xPotion=rando.nextInt(460)+20;
				yPotion=rando.nextInt(150)+50;
				
				Lfire=false;
				Bammo=10001;
				Mammo=7;
				Lammo=1;
				choice=false;
				alien.setYdir(4);
				bullets.setYdir(4);
				alien.setXdir(-1);
				bullets.setXdir(-1);
				Bshotcount=0;
				Mshotcount=0;
				Lshotcount=0;
				aShots=0;
				lives=3;
				level=1;
				shipHealth=300;
				score=0;
				play=false;
				shipXpos=250;
				shipYpos=600;
				bullets.getLaser().get(0).setExists(false);
				bullets.getLaser().get(0).setlX(bullets.getLaser().get(0).getloX());
				bullets.getLaser().get(0).setlY(bullets.getLaser().get(0).getloY());
				shipExists=true;
				for(int i=0; i<alien.getAliens().size(); i++)
				{
					alien.getAliens().get(i).setAlive(true);
					alien.getAliens().get(i).setCanAddScore(true);
					alien.getAliens().get(i).setXpos(alien.getAliens().get(i).getoXpos());
					alien.getAliens().get(i).setYpos(alien.getAliens().get(i).getoYpos());
					alien.getAliens().get(i).setHealth(100);
					alien.getAliens().get(i).setFrozen(false);
					
					
				}
				for(int i=0; i<2100; i++)
				{
					bullets.getAlienGun().get(i).setShot(false);
					bullets.getAlienGun().get(i).setaX(bullets.getAlienGun().get(i).getoaX());
					bullets.getAlienGun().get(i).setaY(bullets.getAlienGun().get(i).getoaY());
					bullets.getAlienGun().get(i).setExists(true);
					bullets.getAlienGun().get(i).setXspeed(2);
					bullets.getAlienGun().get(i).setYspeed(2);
					bullets.getAlienGun().get(i).setFrozen(false);
					if(frenzy==true)
					{
					if(bullets.getAlienGun().get(i).getType()==1 || bullets.getAlienGun().get(i).getType()==4 || bullets.getAlienGun().get(i).getType()==7)
					{
					bullets.getAlienGun().get(i).setDamage(10);
					}
					if(bullets.getAlienGun().get(i).getType()==2 || bullets.getAlienGun().get(i).getType()==5 || bullets.getAlienGun().get(i).getType()==8)
					{
					bullets.getAlienGun().get(i).setDamage(30);
					}
					if(bullets.getAlienGun().get(i).getType()==3 || bullets.getAlienGun().get(i).getType()==6 || bullets.getAlienGun().get(i).getType()==9)
					{
					bullets.getAlienGun().get(i).setDamage(50);
					}
					}
					if(normal==true)
					{
						bullets.getAlienGun().get(i).setDamage(30);
					}
				}
				for(int j=0; j<bullets.getBullets().size(); j++)
				{
				bullets.getBullets().get(j).setContact1ax(false);
				bullets.getBullets().get(j).setContact1bx(false);
				bullets.getBullets().get(j).setContact2ax(false);
				bullets.getBullets().get(j).setContact2bx(false);
				bullets.getBullets().get(j).setExists1ax(true);
				bullets.getBullets().get(j).setExists1bx(true);
				bullets.getBullets().get(j).setExists2ax(true);
				bullets.getBullets().get(j).setExists2bx(true);
				bullets.getBullets().get(j).setShot(false);
				bullets.getBullets().get(j).setDamage2x(false);
				
				bullets.getBullets().get(j).set1aX(bullets.getBullets().get(j).geto1aX());
				bullets.getBullets().get(j).set1bX(bullets.getBullets().get(j).geto1bX());
				bullets.getBullets().get(j).set2aX(bullets.getBullets().get(j).geto2aX());
				bullets.getBullets().get(j).set2bX(bullets.getBullets().get(j).geto2bX());
				bullets.getBullets().get(j).setL1Y(bullets.getBullets().get(j).getoL1Y());
				bullets.getBullets().get(j).setL2Y(bullets.getBullets().get(j).getoL2Y());
				
				bullets.getBullets().get(j).set1axDamage(5);
				bullets.getBullets().get(j).set1bxDamage(5);
				bullets.getBullets().get(j).set2axDamage(5);
				bullets.getBullets().get(j).set2bxDamage(5);
				bullets.getBullets().get(j).setYdir(-3);
				
					

				
				}
				for(int j=0; j<Mammo; j++)
				{
				bullets.getMissiles().get(j).setContact(false);
				bullets.getMissiles().get(j).setShot(false);
				bullets.getMissiles().get(j).setmX(bullets.getMissiles().get(j).getmoX());
				bullets.getMissiles().get(j).setmY(bullets.getMissiles().get(j).getmoY());
				bullets.getMissiles().get(j).setDamage(10000);
				bullets.getMissiles().get(j).setYdir(-3);
				}
					
				
				
			}
			}
			if(HTP==true)
			{
				HTP=false;
			}
		}
			
			if(e.getKeyCode()==KeyEvent.VK_N)
			{
				if(allAdead==true && shipExists==false)
				{
					shipXpos=250;
					shipYpos=600;	
				choice=true;
				normal=true;
				allAdead=false;
				shipExists=true;
				play=false;
				
				
				
				for(int i=0; i<alien.getAliens().size(); i++)
				{
					alien.getAliens().get(i).setFrenzy(false);
				}
				Bammo=oBammo;
				Mammo=oMammo;
				Lammo=oLammo;
				
				for(int j=0; j<bullets.getBullets().size(); j++)
				{
					bullets.getBullets().get(j).set1aX(bullets.getBullets().get(j).geto1aX());
					bullets.getBullets().get(j).set1bX(bullets.getBullets().get(j).geto1bX());
					bullets.getBullets().get(j).set2aX(bullets.getBullets().get(j).geto2aX());
					bullets.getBullets().get(j).set2bX(bullets.getBullets().get(j).geto2bX());
					bullets.getBullets().get(j).setL1Y(bullets.getBullets().get(j).getoL1Y());
					bullets.getBullets().get(j).setL2Y(bullets.getBullets().get(j).getoL2Y());
				}
				for(int i=0; i<bullets.getMissiles().size(); i++)
				{
					bullets.getMissiles().get(i).setmX(bullets.getMissiles().get(i).getmoX());
					bullets.getMissiles().get(i).setmY(bullets.getMissiles().get(i).getmoY());
				}
				bullets.getLaser().get(0).setlX(bullets.getLaser().get(0).getloX());
				bullets.getLaser().get(0).setlY(bullets.getLaser().get(0).getloY());
				
				repaint();
				}
				
			}
			if(e.getKeyCode()==KeyEvent.VK_F)
			{
				if(allAdead==true && shipExists==false)
				{
					shipXpos=250;
					shipYpos=600;
				choice=true;
				frenzy=true;
				normal=false;
				allAdead=false;
				shipExists=true;
				play=false;
				
				
				
				for(int i=0; i<alien.getAliens().size(); i++)
				{
					alien.getAliens().get(i).setFrenzy(true);
				}
				Bammo=oBammo;
				Mammo=oMammo;
				Lammo=oLammo;
				for(int j=0; j<bullets.getBullets().size(); j++)
				{
					bullets.getBullets().get(j).set1aX(bullets.getBullets().get(j).geto1aX());
					bullets.getBullets().get(j).set1bX(bullets.getBullets().get(j).geto1bX());
					bullets.getBullets().get(j).set2aX(bullets.getBullets().get(j).geto2aX());
					bullets.getBullets().get(j).set2bX(bullets.getBullets().get(j).geto2bX());
					bullets.getBullets().get(j).setL1Y(bullets.getBullets().get(j).getoL1Y());
					bullets.getBullets().get(j).setL2Y(bullets.getBullets().get(j).getoL2Y());
				}
				for(int i=0; i<bullets.getMissiles().size(); i++)
				{
					bullets.getMissiles().get(i).setmX(bullets.getMissiles().get(i).getmoX());
					bullets.getMissiles().get(i).setmY(bullets.getMissiles().get(i).getmoY());
				}
				bullets.getLaser().get(0).setlX(bullets.getLaser().get(0).getloX());
				bullets.getLaser().get(0).setlY(bullets.getLaser().get(0).getloY());
				
				repaint();
				}
			}
		
			if(e.getKeyCode()==KeyEvent.VK_H)
			{
				if(highScores==true)
				{
				if(shipExists==false && lives<=0)
				{
					seeRecords=true;
					allAdead=true;
				}
				}
			}
			
			if(e.getKeyCode()==KeyEvent.VK_1)
			{
				if(damagePotions>0 && play==true)
				{
					for(int i=0; i<bullets.getBullets().size(); i++)
					{
					bullets.getBullets().get(i).set1axDamage(bullets.getBullets().get(i).get1axDamage()*2);
					bullets.getBullets().get(i).set1bxDamage(bullets.getBullets().get(i).get1bxDamage()*2);
					bullets.getBullets().get(i).set2axDamage(bullets.getBullets().get(i).get2axDamage()*2);
					bullets.getBullets().get(i).set2bxDamage(bullets.getBullets().get(i).get2bxDamage()*2);
					bullets.getBullets().get(i).setDamage2x(true);
					}
					damagePotions--;
				}
			}
			if(e.getKeyCode()==KeyEvent.VK_2)
			{
				if(healthPotions>0 && play==true)
				{
					shipHealth=500;
					healthPotions--;
				}
			}
			if(e.getKeyCode()==KeyEvent.VK_3)
			{
				if(slowPotions>0 && play==true)
				{
					if(alien.getXdir()>0)
					{
					bullets.setXdir(1);
					alien.setXdir(1);
					}
					if(alien.getXdir()<0)
					{
					bullets.setXdir(-1);
					alien.setXdir(-1);
					}
					
					for(int j=0; j<2100; j++)
					{
						bullets.getAlienGun().get(j).setXspeed(1);
						bullets.getAlienGun().get(j).setYspeed(1);
						bullets.getAlienGun().get(j).setFrozen(true);
					}
					for(int i=0; i<alien.getAliens().size(); i++)
					{
						alien.getAliens().get(i).setFrozen(true);
					}
					slowPotionUsed=true;
					slowPotions--;
				}
			}
			if(e.getKeyCode()==KeyEvent.VK_4)
			{
				if(missilePotions>0 && play==true)
				{
					Mshotcount=0;
					for(int i=0; i<Mammo; i++)
					{
						bullets.getMissiles().get(i).setShot(false);
						bullets.getMissiles().get(i).setContact(false);
						bullets.getMissiles().get(i).setDamage(10000);
						bullets.getMissiles().get(i).setmY(shipYpos-15);
						bullets.getMissiles().get(i).setmX(shipXpos+17);
					}
					
					missilePotions--;
				}
			}
			
			if(e.getKeyCode()==KeyEvent.VK_I)
			{
				if(play==false)
				{
				HTP=true;
				}
			}
		
}
		
		
		
		

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public void moveUp()
	{
		if(shipYpos>=70)
		{
		shipYpos-=20;
		for(int i=0; i<Bammo; i++)
		{
			if(bullets.getBullets().get(i).getShot()==false)
			{
		bullets.getBullets().get(i).setL1Y(bullets.getBullets().get(i).getL1Y()-20);
		bullets.getBullets().get(i).setL2Y(bullets.getBullets().get(i).getL2Y()-20);
			}
		}
		for(int j=0; j<Mammo; j++)
		{
			if(bullets.getMissiles().get(j).getShot()==false)
			{
		bullets.getMissiles().get(j).setmY(bullets.getMissiles().get(j).getmY()-20);
			}
		}
		for(int k=0; k<Lammo; k++)
		{
		bullets.getLaser().get(k).setlY(bullets.getLaser().get(k).getlY()-20);
		}
		}
	}
	public void moveDown()
	{
		if(shipYpos<=620)
		{
		shipYpos+=20;
		for(int i=0; i<Bammo; i++)
		{
			if(bullets.getBullets().get(i).getShot()==false)
			{
		bullets.getBullets().get(i).setL1Y(bullets.getBullets().get(i).getL1Y()+20);
		bullets.getBullets().get(i).setL2Y(bullets.getBullets().get(i).getL2Y()+20);
			}
		}
		for(int j=0; j<Mammo; j++)
		{
			if(bullets.getMissiles().get(j).getShot()==false)
			{
		bullets.getMissiles().get(j).setmY(bullets.getMissiles().get(j).getmY()+20);
			}
		}
		for(int k=0; k<Lammo; k++)
		{
		bullets.getLaser().get(k).setlY(bullets.getLaser().get(k).getlY()+20);
		}
		}
	}
	public void moveLeft()
	{
		if(shipXpos>=30)
		{
		shipXpos-=20;
		for(int i=0; i<Bammo; i++)
		{
			if(bullets.getBullets().get(i).getShot()==false)
			{	
		bullets.getBullets().get(i).set1aX(bullets.getBullets().get(i).get1aX()-20);
		bullets.getBullets().get(i).set1bX(bullets.getBullets().get(i).get1bX()-20);
		bullets.getBullets().get(i).set2aX(bullets.getBullets().get(i).get2aX()-20);
		bullets.getBullets().get(i).set2bX(bullets.getBullets().get(i).get2bX()-20);
			}
		}
		for(int j=0; j<Mammo; j++)
		{
			if(bullets.getMissiles().get(j).getShot()==false)
			{
		bullets.getMissiles().get(j).setmX(bullets.getMissiles().get(j).getmX()-20);
			}
		}
		for(int k=0; k<Lammo; k++)
		{
		bullets.getLaser().get(k).setlX(bullets.getLaser().get(k).getlX()-20);
		}
		}
	}
	public void moveRight()
	{
		if(shipXpos<=440)
		{
		shipXpos+=20;
		for(int i=0; i<Bammo; i++)
		{
		if(bullets.getBullets().get(i).getShot()==false)
		{
		bullets.getBullets().get(i).set1aX(bullets.getBullets().get(i).get1aX()+20);
		bullets.getBullets().get(i).set1bX(bullets.getBullets().get(i).get1bX()+20);
		bullets.getBullets().get(i).set2aX(bullets.getBullets().get(i).get2aX()+20);
		bullets.getBullets().get(i).set2bX(bullets.getBullets().get(i).get2bX()+20);
		}
		}
		for(int j=0; j<Mammo; j++)
		{
			if(bullets.getMissiles().get(j).getShot()==false)
			{
		bullets.getMissiles().get(j).setmX(bullets.getMissiles().get(j).getmX()+20);
			}
		}
		for(int k=0; k<Lammo; k++)
		{
		bullets.getLaser().get(k).setlX(bullets.getLaser().get(k).getlX()+20);
		}
		}
	}
	
	public Aliens getAliens()
	{
		return alien;
	}
	public void organizePlayers(Player[] players)
	{
		for(int i=0; i<players.length; i++)
		{
			int maxIndex=i;
			for(int j=i+1; j<players.length; j++)
			{
				if(players[maxIndex].getScore()<players[j].getScore())
				{
					maxIndex=j;
				}
				
			}
			Player temp=players[i];
			players[i]=players[maxIndex];
			players[maxIndex]=temp;
		}
	}
}
	
class Player
{
	private String name="";
	private int score=0;
	
	public Player(String n, int s)
	{
		name=n;
		score=s;
	}
	
	public String getName()
	{
		return name;
	}
	public int getScore()
	{
		return score;
	}

}

