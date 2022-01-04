package Space_Defender;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

public class Aliens{

	private ArrayList<Alien> aliens;
	private int Aheight=25;
	private int Awidth=40;
	private int Xpos;
	private int Ypos;
	private int Ydir=4;
	private int Xdir=-1;
	private int moveLast=1;
	
	
	public Aliens(int numAliens, int aXpos, int aYpos)
	{
		aliens=new ArrayList<Alien>();
		Xpos=aXpos;
		Ypos=aYpos;
		
		
		if(numAliens==21)
		{
		for(int i=0; i<3; i++)
		{
			for(int j=0; j<7; j++)
			{
			int type1=0;
			int type2=0;
			while(type1==type2)
			{
			Random rand =new Random();
			type1=rand.nextInt(9)+1;
			Random rando =new Random();
			type2=rando.nextInt(9)+1;
			}
			
			aliens.add(new Alien(Aheight, Awidth, Xpos+(j*50), Ypos+(i*40), true, type1, type2));
			}
		}
		}
	}
	public ArrayList<Alien> getAliens()
	{
		return aliens;
	}
	public void drawAliens(Graphics g)
	{
		for(int i=0; i<aliens.size(); i++)
		{
		if(aliens.get(i).isAlive())
		{
			aliens.get(i).paint(g);
		}
		
		}
	}
	public void moveRight()
	{
		for(int i=0; i<aliens.size(); i++)
		{
			
				aliens.get(i).setXpos(aliens.get(i).getXpos()+Xdir);
				
			
		}
		moveLast=2;
	}
	public void moveLeft()
	{
		for(int i=0; i<aliens.size(); i++)
		{
			
				aliens.get(i).setXpos(aliens.get(i).getXpos()+Xdir);
				
			
		}
		moveLast=1;
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
		for(int i=0; i<aliens.size(); i++)
		{
			if(aliens.get(i).isAlive())
			{
				aliens.get(i).setYpos(aliens.get(i).getYpos()+Ydir);
				
			}
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

class Alien
{
	private int Aheight;
	private int Awidth;
	private int Xpos;
	private int Ypos;
	private int oXpos;
	private int oYpos;
	private int health=100;
	private boolean alive;
	private boolean canAddScore=true;
	private int type1;
	private int type2;
	private boolean frenzy=false;
	private boolean frozen=false;
	
	public Alien(int height, int width, int aXpos, int aYpos, boolean doa, int type1, int type2)
	{
		Aheight=height;
		Awidth=width;
		Xpos=aXpos;
		Ypos=aYpos;
		oXpos=aXpos;
		oYpos=aYpos;
		alive =doa;
		this.type1=type1;
		this.type2=type2;
		
	}
	public void paint(Graphics g)
	{
	if(frenzy==true)
	{
		if(type1==1)
		{
		g.setColor(Color.gray);
		g.fillOval(Xpos, Ypos, Awidth, Aheight);
		}
		if(type1==2)
		{
			g.setColor(Color.blue);
			g.fillOval(Xpos, Ypos, Awidth, Aheight);
		}
		if(type1==3)
		{
			g.setColor(Color.green);
			g.fillOval(Xpos, Ypos, Awidth, Aheight);
		}
		if(type1==4)
		{
		g.setColor(Color.cyan);
		g.fillOval(Xpos, Ypos, Awidth, Aheight);
		}
		if(type1==5)
		{
			g.setColor(Color.magenta);
			g.fillOval(Xpos, Ypos, Awidth, Aheight);
		}
		if(type1==6)
		{
			g.setColor(Color.orange);
			g.fillOval(Xpos, Ypos, Awidth, Aheight);
		}
		if(type1==7)
		{
		g.setColor(Color.pink);
		g.fillOval(Xpos, Ypos, Awidth, Aheight);
		}
		if(type1==8)
		{
			g.setColor(Color.yellow);
			g.fillOval(Xpos, Ypos, Awidth, Aheight);
		}
		if(type1==9)
		{
			g.setColor(Color.white);
			g.fillOval(Xpos, Ypos, Awidth, Aheight);
		}
		
		
		
		if(type2==1)
		{
		g.setColor(Color.gray);
		g.fillOval(Xpos+10, Ypos-4, Awidth-20, Aheight-10);
		}
		if(type2==2)
		{
			g.setColor(Color.blue);
			g.fillOval(Xpos+10, Ypos-4, Awidth-20, Aheight-10);
		}
		if(type2==3)
		{
			g.setColor(Color.green);
			g.fillOval(Xpos+10, Ypos-4, Awidth-20, Aheight-10);
		}
		if(type2==4)
		{
		g.setColor(Color.cyan);
		g.fillOval(Xpos+10, Ypos-4, Awidth-20, Aheight-10);
		}
		if(type2==5)
		{
			g.setColor(Color.magenta);
			g.fillOval(Xpos+10, Ypos-4, Awidth-20, Aheight-10);
		}
		if(type2==6)
		{
			g.setColor(Color.orange);
			g.fillOval(Xpos+10, Ypos-4, Awidth-20, Aheight-10);
		}
		if(type2==7)
		{
		g.setColor(Color.pink);
		g.fillOval(Xpos+10, Ypos-4, Awidth-20, Aheight-10);
		}
		if(type2==8)
		{
			g.setColor(Color.yellow);
			g.fillOval(Xpos+10, Ypos-4, Awidth-20, Aheight-10);
		}
		if(type2==9)
		{
			g.setColor(Color.white);
			g.fillOval(Xpos+10, Ypos-4, Awidth-20, Aheight-10);
		}
	}
	
	if(frenzy==false)
	{
		if(frozen==true)
		{
			g.setColor(Color.CYAN);
			g.fillOval(Xpos, Ypos, Awidth, Aheight);
			g.setColor(Color.green);
			g.fillOval(Xpos+10, Ypos-4, Awidth-20, Aheight-10);
		}
		if(frozen==false)
		{
		g.setColor(Color.GRAY);
		g.fillOval(Xpos, Ypos, Awidth, Aheight);
		g.setColor(Color.green);
		g.fillOval(Xpos+10, Ypos-4, Awidth-20, Aheight-10);
		}
	}
	}
	public int getXpos()
	{
		return Xpos;
	}
	public int getYpos()
	{
		return Ypos;
	}
	public int getoXpos()
	{
		return oXpos;
	}
	public int getoYpos()
	{
		return oYpos;
	}
	public int getType()
	{
		return type1;
	}
	
	
	public boolean isAlive()
	{
		return alive;
	}
	public void setAlive(boolean doa)
	{
		alive=doa;
	}
	public boolean getCanAddScore()
	{
		return canAddScore;
	}
	public void setCanAddScore(boolean cas)
	{
		canAddScore=cas;
	}
	
	public boolean getFrenzy()
	{
		return frenzy;
	}
	public void setFrenzy(boolean fren)
	{
		frenzy=fren;
	}
	public boolean getFrozen()
	{
		 return frozen;
	}
	public void setFrozen(boolean i)
	{
		 frozen=i;
	}
	
	
	public void setXpos(int i)
	{
		Xpos=i;
	}
	public void setYpos(int i)
	{
		Ypos=i;
	}
	
	public int getHealth()
	{
		return health;
	}
	public void setHealth(int i)
	{
		health=i;
	}
	
}
