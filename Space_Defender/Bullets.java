package Space_Defender;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

public class Bullets
{
ArrayList<Bullet> bullets;
ArrayList<Missile> missiles;
ArrayList<Laser> lasers;
ArrayList<AlienGun> aGuns;
private int Bammo;
private int Mammo;
private int Lammo;
private int Aammo;
private int Ydir=4;
private int Xdir=-1;
private int moveLast=1;
	
	public Bullets(int Bammo, int Mammo, int Lammo, int Aammo, int a1Xpos, int b1Xpos, int L1Ypos,  int a2Xpos, int b2Xpos, int L2Ypos, int misY, int misX, int lasY, int lasX, int aX, int aY)
	{
	
		
		bullets=new ArrayList<Bullet>();
		missiles=new ArrayList<Missile>();
		lasers= new ArrayList<Laser>();
		aGuns= new ArrayList<AlienGun>();
		
		
		this.Bammo=Bammo;
		this.Mammo=Mammo;
		this.Lammo=Lammo;
		this.Aammo=Aammo;
		
		for(int i=0; i<Bammo; i++)
		{
			bullets.add(new Bullet(a1Xpos, b1Xpos, L1Ypos, a2Xpos, b2Xpos, L2Ypos));
		}
		for(int i=0; i<Mammo; i++)
		{
			missiles.add(new Missile(misX, misY));
		}
		for(int i=0; i<Lammo; i++)
		{
			lasers.add(new Laser(lasX, lasY));
		}
		
		for(int i=0; i<3; i++)
		{
			for(int j=0; j<7; j++)
			{
				for(int k=0; k<100; k++)
				{
					int pos=(i+1)*(j+1);
					aGuns.add(new AlienGun(aX+(j*50)+15, aY+(i*40)+10, pos));
				}
			}
		}
	}
	
	public ArrayList<Bullet> getBullets()
	{
		return bullets;
	}
	/*public void drawBullets(Graphics g)
	{
		for(int i=0; i<bullets.size(); i++)
		{
		bullets.get(i).paint(g);
		}
	}*/
	public ArrayList<Missile> getMissiles()
	{
		return missiles;
	}
	public void drawMissiles(Graphics g)
	{
		for(int i=0; i<missiles.size(); i++)
		{
		missiles.get(i).paint(g);
		}
	}
	public ArrayList<Laser> getLaser()
	{
		return lasers;
	}
	public void drawLaser(Graphics g)
	{
		for(int i=0; i<lasers.size(); i++)
		{
		lasers.get(i).paint(g);
		}
	}
	public ArrayList<AlienGun> getAlienGun()
	{
		return aGuns;
	}
	public void drawAlienGun(Graphics g)
	{
		for(int i=0; i<aGuns.size(); i++)
		{
		aGuns.get(i).paint(g);
		}
	}
	public void moveLeft()
	{
		moveLast=1;
		for(int i=0; i<aGuns.size(); i++)
		{
			if(aGuns.get(i).getShot()==false)
			{	
				aGuns.get(i).setaX(aGuns.get(i).getaX()+Xdir);
			}	
		}
		
	}
	public void moveRight()
	{
		moveLast=2;
		for(int i=0; i<aGuns.size(); i++)
		{
			if(aGuns.get(i).getShot()==false)
			{
		aGuns.get(i).setaX(aGuns.get(i).getaX()+Xdir);
			}
		}
		
	}
	public void setXdir(int i)
	{
		Xdir=i;
	}
	public int getXdir()
	{
		return Xdir;
	}
	
	
	public void moveDown()
	{
		for(int i=0; i<aGuns.size(); i++)
		{
		aGuns.get(i).setaY(aGuns.get(i).getaY()+Ydir);;
		}
	}
	public void setYdir(int i)
	{
		Ydir=i;
	}
	
	public int getYdir()
	{
		return Ydir;
	}
}



class Bullet
{
	private int gun1aXpos, gunL1Ypos, gun1bXpos, gun2aXpos, gun2bXpos, gunL2Ypos;
	private int guno1aXpos, gunoL1Ypos, guno1bXpos, guno2aXpos, guno2bXpos, gunoL2Ypos;
	private int a1xdamage=5;
	private int b1xdamage=5;
	private int a2xdamage=5;
	private int b2xdamage=5;
	private int a1xhealth=5;
	private int b1xhealth=5;
	private int a2xhealth=5;
	private int b2xhealth=5;
	private boolean exists1ax=true;
	private boolean exists1bx=true;
	private boolean exists2ax=true;
	private boolean exists2bx=true;
	private boolean shot=false;
	private int Ydir=-3;
	private boolean contact1ax=false;
	private boolean contact1bx=false;
	private boolean contact2ax=false;
	private boolean contact2bx=false;
	private boolean Damage2x=false;
	
	
	public Bullet(int a1Xpos, int b1Xpos, int L1Ypos,  int a2Xpos, int b2Xpos, int L2Ypos)
	{
		gun1aXpos=a1Xpos;
		gun1bXpos=b1Xpos;
		gunL1Ypos=L1Ypos;
		gun2aXpos=a2Xpos;
		gun2bXpos=b2Xpos;
		gunL2Ypos=L2Ypos;
		
		guno1aXpos=a1Xpos;
		guno1bXpos=b1Xpos;
		gunoL1Ypos=L1Ypos;
		guno2aXpos=a2Xpos;
		guno2bXpos=b2Xpos;
		gunoL2Ypos=L2Ypos;
	}
	
	public void paint1ax(Graphics g)
	{
		if(Damage2x==false)
		{
		g.setColor(Color.RED);
		}
		if(Damage2x==true)
		{
			g.setColor(Color.GREEN);
		}
		g.fillRect(gun1aXpos, gunL1Ypos, 2, 4);
		
		
		
	}
	public void paint1bx(Graphics g)
	{
		if(Damage2x==false)
		{
		g.setColor(Color.RED);
		}
		if(Damage2x==true)
		{
			g.setColor(Color.GREEN);
		}
		g.fillRect(gun1bXpos, gunL1Ypos, 2, 4);
		
	}
	public void paint2ax(Graphics g)
	{
		if(Damage2x==false)
		{
		g.setColor(Color.RED);
		}
		if(Damage2x==true)
		{
			g.setColor(Color.GREEN);
		}
		g.fillRect(gun2aXpos, gunL2Ypos, 2, 4);
		
	}
	public void paint2bx(Graphics g)
	{
		if(Damage2x==false)
		{
		g.setColor(Color.RED);
		}
		if(Damage2x==true)
		{
			g.setColor(Color.GREEN);
		}
	g.fillRect(gun2bXpos, gunL2Ypos, 2, 4);
	}
	
	public int get1aX()
	{
		return gun1aXpos;
	}
	public int get1bX()
	{
		return gun1bXpos;
	}
	public int get2aX()
	{
		return gun2aXpos;
	}
	public int get2bX()
	{
		return gun2bXpos;
	}
	public int getL1Y()
	{
		return gunL1Ypos;
	}
	public int getL2Y()
	{
		return gunL2Ypos;
	}
	
	public int geto1aX()
	{
		return guno1aXpos;
	}
	public int geto1bX()
	{
		return guno1bXpos;
	}
	public int geto2aX()
	{
		return guno2aXpos;
	}
	public int geto2bX()
	{
		return guno2bXpos;
	}
	public int getoL1Y()
	{
		return gunoL1Ypos;
	}
	public int getoL2Y()
	{
		return gunoL2Ypos;
	}
	
	
	public void set1aX(int i)
	{
		 gun1aXpos=i;
	}
	public void set1bX(int i)
	{
		 gun1bXpos=i;
	}
	public void set2aX(int i)
	{
		 gun2aXpos=i;
	}
	public void set2bX(int i)
	{
		 gun2bXpos=i;
	}
	public void setL1Y(int i)
	{
		 gunL1Ypos=i;
	}
	public void setL2Y(int i)
	{
		 gunL2Ypos=i;
	}
	
	public boolean getShot()
	{
		 return shot;
	}
	public void setShot(boolean i)
	{
		 shot=i;
	}
	public int getYdir()
	{
		 return Ydir;
	}
	public void setYdir(int i)
	{
		Ydir=i;
	}
	
	public boolean getContact1ax()
	{
		return contact1ax;
	}
	public void setContact1ax(boolean i)
	{
		contact1ax=i;
	}
	public boolean getContact1bx()
	{
		return contact1bx;
	}
	public void setContact1bx(boolean i)
	{
		contact1bx=i;
	}
	public boolean getContact2ax()
	{
		return contact2ax;
	}
	public void setContact2ax(boolean i)
	{
		contact2ax=i;
	}
	public boolean getContact2bx()
	{
		return contact2bx;
	}
	public void setContact2bx(boolean i)
	{
		contact2bx=i;
	}
	
	
	
	public boolean getExists1ax()
	{
		return exists1ax;
	}
	public void setExists1ax(boolean i)
	{
		exists1ax=i;
	}
	public boolean getExists1bx()
	{
		return exists1bx;
	}
	public void setExists1bx(boolean i)
	{
		exists1bx=i;
	}
	public boolean getExists2ax()
	{
		return exists2ax;
	}
	public void setExists2ax(boolean i)
	{
		exists2ax=i;
	}
	public boolean getExists2bx()
	{
		return exists2bx;
	}
	public void setExists2bx(boolean i)
	{
		exists2bx=i;
	}
	
	
	
	
	
	public int get1axDamage()
	{
		return a1xdamage;
	}
	public void set1axDamage(int i)
	{
		a1xdamage=i;
		a1xhealth=i;
	}
	public int get1bxDamage()
	{
		return b1xdamage;
	}
	public void set1bxDamage(int i)
	{
		b1xdamage=i;
		b1xhealth=i;
	}
	public int get2axDamage()
	{
		return a2xdamage;
	}
	public void set2axDamage(int i)
	{
		a2xdamage=i;
		a2xhealth=i;
	}
	public int get2bxDamage()
	{
		return b2xdamage;
	}
	public void set2bxDamage(int i)
	{
		b2xdamage=i;
		b2xhealth=i;
	}
	
	
	public boolean getDamage2x()
	{
		 return Damage2x;
	}
	public void setDamage2x(boolean i)
	{
		 Damage2x=i;
	}
	
	
	
	
	
	public int get1axHealth()
	{
		return a1xhealth;
	}
	public void set1axHealth(int i)
	{
		a1xhealth=i;
		a1xdamage=i;
	}
	public int get1bxHealth()
	{
		return b1xhealth;
	}
	public void set1bxHealth(int i)
	{
		b1xhealth=i;
		b1xdamage=i;
	}
	public int get2axHealth()
	{
		return a2xhealth;
	}
	public void set2axHealth(int i)
	{
		a2xhealth=i;
		a2xdamage=i;
	}
	public int get2bxHealth()
	{
		return b2xhealth;
	}
	public void set2bxHealth(int i)
	{
		b2xhealth=i;
		b2xdamage=i;
	}
}

class Missile
{
	private int misXpos, misYpos;
	private int misoXpos, misoYpos;
	private int damage=1001;
	private boolean shot=false;
	private int Ydir=-3;
	private boolean contact=false;

	
	
	public Missile(int missileXpos, int missileYpos)
	{
		misXpos=missileXpos;
		misYpos=missileYpos;
		
		misoXpos=missileXpos;
		misoYpos=missileYpos;
		
	}
	
	public void paint(Graphics g)
	{
		g.setColor(Color.BLUE);
		g.fillRect(misXpos, misYpos, 9, 3);
		g.fillRect(misXpos+3, misYpos-7, 3, 13);
		
	}
	
	
	public int getmX()
	{
		return misXpos;
	}
	public int getmY()
	{
		return misYpos;
	}
	public int getmoX()
	{
		return misoXpos;
	}
	public int getmoY()
	{
		return misoYpos;
	}
	
	
	public void setmX(int i)
	{
		 misXpos=i;
	}
	public void setmY(int i)
	{
		 misYpos=i;
	}
	
	public boolean getShot()
	{
		 return shot;
	}
	public void setShot(boolean i)
	{
		 shot=i;
	}
	
	public int getYdir()
	{
		 return Ydir;
	}
	public void setYdir(int i)
	{
		Ydir=i;
	}
	
	public boolean getContact()
	{
		return contact;
	}
	public void setContact(boolean i)
	{
		contact=i;
	}
	
	
	public int getDamage()
	{
		return damage;
	}
	public void setDamage(int i)
	{
		damage=i;
	}
}
class Laser
{
	private int lasXpos, lasYpos;
	private int lasoXpos, lasoYpos;
	private int damage=10;
	private int Ydir=-2;
	private boolean exists=false;
	
	public Laser(int laserXpos, int laserYpos)
	{
		lasXpos=laserXpos;
		lasYpos=laserYpos;
		
		lasoXpos=laserXpos;
		lasoYpos=laserYpos;
		
	}
	
	public void paint(Graphics g)
	{
		g.setColor(Color.RED);
		g.fillRect(lasXpos, lasYpos, 2, 700);
		
		
	}
	
	
	public int getlX()
	{
		return lasXpos;
	}
	public int getlY()
	{
		return lasYpos;
	}
	public int getloX()
	{
		return lasoXpos;
	}
	public int getloY()
	{
		return lasoYpos;
	}
	
	
	public void setlX(int i)
	{
		 lasXpos=i;
	}
	public void setlY(int i)
	{
		 lasYpos=i;
	}
	
	public int getYdir()
	{
		 return Ydir;
	}
	public void setYdir(int i)
	{
		Ydir=i;
	}
	
	public boolean getExists()
	{
		 return exists;
	}
	public void setExists(boolean i)
	{
		 exists=i;
	}
	
	
	
	public int getDamage()
	{
		return damage;
	}
	public void setDamage(int i)
	{
		damage=i;
	}
}

class AlienGun
{
	private int aX, aY;
	private int oaX, oaY;
	private int damage=30;
	private boolean shot=false;
	private int Xdir;
	private int Xspeed=2;
	private int Ydir;
	private int Yspeed=2;
	private boolean exists=true;
	private int type=3;
	private int pos;
	private int health=30;
	private boolean frozen=false;
	
	
	public AlienGun(int aXpos, int aYpos, int position)
	{
		
		
		aX=aXpos;
		aY=aYpos;
		oaX=aXpos;
		oaY=aYpos;
		pos=position;
		
				
		
		Random rand=new Random();
		int dir=rand.nextInt(3);
		if(dir==0)
		{
			Xdir=-Xspeed;
		}
		else if(dir==1)
		{
			Xdir=Xspeed;
		}
		if(dir==2)
		{
			Xdir=0;
		}
		Ydir=Yspeed;
		
		
		
	}
	
	public void paint(Graphics g)
	{
		if(frozen==false)
		{
		if(type==1)
		{
		g.setColor(Color.gray);
		g.fillOval(aX, aY, 10, 10);
		}
		if(type==2)
		{
		g.setColor(Color.blue);
		g.fillOval(aX, aY, 10, 10);
		}
		if(type==3)
		{
		g.setColor(Color.green);
		g.fillOval(aX, aY, 10, 10);
		
		}
		if(type==4)
		{
		g.setColor(Color.cyan);
		g.fillOval(aX, aY, 10, 10);
		}
		if(type==5)
		{
		g.setColor(Color.magenta);
		g.fillOval(aX, aY, 10, 10);
		}
		if(type==6)
		{
		g.setColor(Color.orange);
		g.fillOval(aX, aY, 10, 10);
		}
		if(type==7)
		{
		g.setColor(Color.pink);
		g.fillOval(aX, aY, 10, 10);
		}
		if(type==8)
		{
		g.setColor(Color.yellow);
		g.fillOval(aX, aY, 10, 10);
		}
		if(type==9)
		{
		g.setColor(Color.white);
		g.fillOval(aX, aY, 10, 10);
		}
		}
		if(frozen==true)
		{
			g.setColor(Color.CYAN);
			g.fillOval(aX, aY, 10, 10);
		}
	}
	
	public int getaX()
	{
		return aX;
	}
	public int getaY()
	{
		return aY;
	}
	public int getoaX()
	{
		return oaX;
	}
	public int getoaY()
	{
		return oaY;
	}
	
	
	public void setaX(int i)
	{
		 aX=i;
	}
	public void setaY(int i)
	{
		 aY=i;
	}
	
	public boolean getShot()
	{
		 return shot;
	}
	public void setShot(boolean i)
	{
		 shot=i;
	}
	
	public int getXdir()
	{
		 return Xdir;
	}
	public void setXspeed(int i)
	{
		 Xspeed=i;
	}
	public void changeXdir()
	{
		Xdir=-Xdir;
	}
	
	public int getYdir()
	{
		 return Ydir;
	}
	public void setYspeed(int i)
	{
		 Yspeed=i;
		 Ydir=Yspeed;
	}
	
	public boolean getExists()
	{
		 return exists;
	}
	public void setExists(boolean i)
	{
		 exists=i;
	}
	public boolean getFrozen()
	{
		 return frozen;
	}
	public void setFrozen(boolean i)
	{
		 frozen=i;
	}
	
	public int getType()
	{
		return type;
	}
	public void setType(int i)
	{
		type=i;
		if(type==1)
		{
			damage=10;
		}
		if(type==2)
		{
			damage=30;
		}
		if(type==3)
		{
			damage=50;
		}
		if(type==4)
		{
			damage=10;
		}
		if(type==5)
		{
			damage=30;
		}
		if(type==6)
		{
			damage=50;
		}
		if(type==7)
		{
			damage=10;
		}
		if(type==8)
		{
			damage=30;
		}
		if(type==9)
		{
			damage=50;
		}
	}
	public int getHealth()
	{
		return health;
	}
	public void setHealth(int i)
	{
		health=i;
		damage=i;
	}
	public int getDamage()
	{
		return damage;
	}
	public void setDamage(int i)
	{
		damage=i;
		health=i;
	}
}

