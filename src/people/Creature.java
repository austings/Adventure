package people;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;
import java.util.Stack;

import model.TileNode;
import model.TileThing;
import tools.Dice;
import tools.Spellbook;

public class Creature extends TileThing implements Runnable
{
	//stats
	private int STR;
	private int INT;
	private int DEX;
	private int CON;
	private int CHR;
	private int level = 1;
	private int currentXP = 0;
	private int XPtoLvl = 100;
	private int XPworth = 25;
	private int HP;
	private int currentHP;
	private int x;
	private int y;
	//traits
	public String race;
	public String job;
	
	//AI BEHAVIOUR
	private AIBehaviour aib = new AIBehaviour();
	private boolean talking = false; //toggle for talking or not
	private boolean dead = false;// dead or not
	private String assocQuestID = "";
	//private Creature target;//if needs to attack someone
	
	//WALKING AROUND
	public String walkingBehaviour ="random";
	public Boolean slowWalkEnabled;
	public Dimension destination;
	public Dimension lastPositionStood;
	
	private String lastLookedDirection="up";
	private TileNode n;
	private int failure=0;//retries until giving up on destination

	
	private boolean walking = false;
	private LinkedList<TileNode> path = new LinkedList<TileNode>();
	private TileNode[][] world;
	
	//inventory
	private LinkedList<TileThing> inventory = new LinkedList<TileThing>();
	private LinkedList<TileThing> organs = new LinkedList<TileThing>();
	private int weaponmodifier = 0;
	private int gold = 0;
	
	
	private boolean hasSpellbook(String name){
		
		return false;
	}
	
	public int getGold()
	{
		return gold;
	}
	
	public void addGold(int g)
	{
		gold = gold+g;
	}
	
	public void setGold(int g)
	{
		gold = g;
	}
	
	public void subtractHP(int value)
	{
		currentHP = currentHP-value;
	}
	
	public int getCurrentHP()
	{
		return currentHP;
	}
	
	public int getMaxHP()
	{
		return HP;
	}

	public Creature(String name,Dimension d, int id)
	{
		super(name,id);
		super.setWeight(150);
		STR = 5;
		INT = 5;
		DEX = 5;
		CON = 5;
		CHR = 5;
		HP=25;
		currentHP = HP;
		
		talking = false;
		slowWalkEnabled = false;
		
		x=d.width;
		y=d.height;
		
	}
	
	public LinkedList<TileThing> die()
	{
		dead = true;
		LinkedList<TileThing> items = new LinkedList<TileThing>();
		items = inventory;
		for(TileThing t: organs)
		{
			items.add(t);
		}
		
		walkingBehaviour = "stay";
		setImageID(0);
		return items;
	}
	
	public boolean isDead()
	{
		return dead;
	}
	
	public int getMeleeAttackValue()
	{
		int val;
		Dice d = new Dice(STR);
		val = (int)(d.roll(STR)/2)+weaponmodifier;
		return val;
	}
	
	public String attacked(int opDex)
	{	
		//destination = c.getPosition();
		//target = c;
		Dice od = new Dice(opDex*2);
		Dice d = new Dice(DEX*2);
		if(od.roll()>d.roll())
			return "hit";
		else
			return "miss";
	}
	
	//AoR -> Add or remove. True to add, false to remove
	public void addToInventory(boolean AoR,TileThing t)
	{
		if(AoR)
			inventory.add(t);
		else
			inventory.remove(t);
	}
	
	//AoR -> Add or remove. True to add, false to remove
	public void addToOrgans(boolean AoR,TileThing t)
	{
		if(AoR)
			organs.add(t);
		else
			organs.remove(t);
	}
	
	public LinkedList<TileThing> getInventory()
	{
		return inventory;
	}
	
	public void setInventory(LinkedList<TileThing> t)
	{
		inventory = t;
	}
	
	
	public Dimension getPosition()
	{
		return new Dimension(x,y);
	}
	public void setPosition(Dimension d)
	{
		x = (int) d.getWidth();
		y = (int) d.getHeight();
	}
	
	public Boolean swapSlowWalkBool()
	{
		if(slowWalkEnabled == true)
			slowWalkEnabled= false;
		else
			slowWalkEnabled= true;
		return slowWalkEnabled;
	}
	
	public void setRace(String r)
	{
		race = r;
	}
	
	public String getRace()
	{
		return race;
	}
	
	public void setJob(String j)
	{
		job = j;
	}
	
	public String getJob()
	{
		return job;
	}
	
	public void moveRight()
	{
		lastLookedDirection="right";
		x=x+1;
	}
	
	public void moveLeft()
	{
		lastLookedDirection="left";
		x=x-1;
	}
	
	public void moveUp()
	{
		lastLookedDirection="up";
		y=y-1;
	}
	
	public void moveDown()
	{
		lastLookedDirection="down";
		y=y+1;
	}
	
	public int getSTR()
	{
		return STR;
	}
	
	public void addSTR(int i)
	{
		STR = STR+i;
	}
	
	public int getSTRMod(int numberOfSidesOnDice)
	{
		int result = 0;
		
		
		return result;
	}
	
	public void setSTR(int i)
	{
		STR = i;
	}
	
	public int getINT()
	{
		return INT;
	}
	
	public void addINT(int i)
	{
		INT = INT+i;
	}
	
	public void setINT(int i)
	{
		INT = i;
	}
	
	public int getDEX()
	{
		return DEX;
	}
	
	public void addDEX(int i)
	{
		DEX = DEX+i;
	}
	
	public void setDEX(int i)
	{
		DEX = i;
	}
	
	public int getCON()
	{
		return CON;
	}
	
	public void addCON(int i)
	{
		CON = CON+i;
		HP = CON*5;
		currentHP = HP;
	}
	
	public void setCON(int i)
	{
		CON = i;
		HP = CON*5;
		currentHP = HP;
	}
	
	public int getCHR()
	{
		return CHR;
	}
	
	public void addCHR(int i)
	{
		CHR = CHR+i;
	}
	
	public void setCHR(int i)
	{
		CHR = i;
	}
	
	public int recalculateXPWorth()
	{
		XPworth = STR+DEX+CON+INT+CHR;
		return XPworth;
	}
	
	
	public String getLastLookedDirection()
	{
		return lastLookedDirection;
	}
	

	public void runTurn()
	{
		if(walkingBehaviour.equals("random"))
		{
		boolean fail = true;//fail is true when we don't have a valid destination
		//script for selecting a random tile in the world and moving to it.
		if(walking==false)
		{
			failure = 0;
			//set up destination
			while(fail)
			{
				int posX=x;//destination x
				int posY=y;//destination y
				if(walkingBehaviour.equals("random"))
				{
					Random r = new Random();
					
					
					//if we don't already have a destination
					//if(!(target instanceof Creature)) {
					do{
						posX = r.nextInt(world.length)+10;
						posY = r.nextInt(world.length)+10;
						if(posX>=world.length)
							posX = world.length-10;
						if(posY>=world[1].length-1)
							posY = world[1].length-10;
						}
					while(!(world[posX][posY].isSolid()));
					destination = new Dimension(posX,posY);
					//}
				}
				else
				{
					break;
				}
				//node 
				/*
				 * data 
				 * h value- called heuristic
				 * g value movement cost //for me, everyone is 15s
				 * f value g+h
				 * parent (a node to reach this node)
				 * 
				 * lists
				 * 
				 * open list (list of nodes taht need to be checked)
				 * closed list (nodes that have been checked)
				 * 
				 * 
				 * h- distance from node to target node
				 * 
				 */
				//
				int tempX = x;
				int tempY = y;
				TileNode n = world[x][y]; 		//current node
				TileNode parent = null;			//parent node
				n.parent = parent;				//set parent
				n.setHeuristic(0);				//set h
				n.addMovement(0);				//set g
				
				//set every tiles heuristic value and resets parameters
				
				for(TileNode[] tna: world)
				{
					for(TileNode tn: tna)
					{
						int a = Math.abs(posX-tn.getX());
						int b = Math.abs(posY-tn.getY());
						tn.setHeuristic(a+b);
						tn.setMovement(0);
						tn.parent=null;
					}
				}
				
				//update lists
				LinkedList<TileNode> o = new LinkedList<TileNode>();
				LinkedList<TileNode> c = new LinkedList<TileNode>();
				c.add(n);
				
				//while we are not right next to our destination
				while(world[tempX][tempY].getHeuristic()!=1)
				{
					TileNode nextToExamine = null;//tbd
					int i =0;
					//check up, down, left right
					while(i <4)
					{
						int xM = 0;
						int yM = 0;
						boolean t = true;
						switch(i)
						{
							case 0:
								xM= 0;
								yM=-1;
								if(tempY-1<0)
									t=false;
								break;
							case 1:
								xM=-1;
								yM=0;
								if(tempX-1<0)
									t=false;
								break;
							case 2:
								xM=1;
								yM=0;
								if(tempX+1>world.length-1)
									t=false;
								break;
							case 3:
								xM=0;
								yM=1;
								if(tempY+1>world[1].length-1)
									t=false;
								break;
						}
						//if its okay to check this one
						//i.e. not solid, parented, or closed
						try {
						if(t&!(world[tempX+xM][tempY+yM].isSolid())
								&world[tempX+xM][tempY+yM].parent==null
								&!(c.contains(world[tempX+xM][tempY+yM])))
						{
							world[tempX+xM][tempY+yM].parent= n;
							world[tempX+xM][tempY+yM].addMovement(1);
							o.add(world[tempX+xM][tempY+yM]);
							if(nextToExamine==null)
							{
								nextToExamine = world[tempX+xM][tempY+yM];
							}
							else 
							{
								for(TileNode tn : o)
								{
									if(tn.getF()<nextToExamine.getF())
										nextToExamine = tn;
								}
							}
						}
						else
						{
							if(world[tempX+xM][tempY+yM].isSolid())
							{
							}
							//in the exception where the tile already has a parent 
							if(o.contains((world[tempX+xM][tempY+yM])))
							{
								int add = world[tempX][tempY].getMovement();
								add++;
								//where we find a shorter path
								if(add<world[tempX+xM][tempY+yM].getMovement())
								{
									//change the parent
									world[tempX+xM][tempY+yM].parent = world[tempX][tempY];
									world[tempX+xM][tempY+yM].setMovement(add);
									world[tempX+xM][tempY+yM].getF();
									if(nextToExamine==null)
									{
										nextToExamine = world[tempX+xM][tempY+yM];
									}
									else 
									{
										for(TileNode tn : o)
										{
											if(tn.getF()<nextToExamine.getF())
												nextToExamine = tn;
										}
									}//else nextTo Exmine
									//i++;
								}//if add<
								
							}//if o contains
						}//if object is solid
						}catch(ArrayIndexOutOfBoundsException e)
						{
							
						}
						i++;
					}//while i <4
					if(nextToExamine == null){//failed to find a path, new random location
						n = null;
						break;}
					n=nextToExamine;
					o.remove(nextToExamine);
					c.add(nextToExamine);
					tempX = nextToExamine.getX();
					tempY = nextToExamine.getY();
				}//while not next to destination
				if(n !=null)
				{
					//on top
					if(tempY-posY==-1)
					{
						world[tempX][tempY+1].parent=world[tempX][tempY];
						path.push(world[tempX][tempY+1]);
					}
					//on bottom
					if(tempY-posY==1)
					{
						world[tempX][tempY-1].parent=world[tempX][tempY];
						path.push(world[tempX][tempY-1]);
					}
					//to the left of
					if(tempX-posX==-1)
					{
						world[tempX+1][tempY].parent=world[tempX][tempY];
						path.push(world[tempX+1][tempY]);
					}
					//to the right of
					if(tempX-posX==1)
					{
						world[tempX-1][tempY].parent=world[tempX][tempY];
						path.push(world[tempX-1][tempY]);
					}
					TileNode check = path.get(0);
					while(check.parent!=null)
					{
						path.push(check.parent);
						check = check.parent;
					}
					path.pop();
					walking = true;
					fail = false;
				}//if we didn't fail
			}//loop in case of failure
		}//if walking !true
		
		
		if(path.size()>0)
		{
			if(path.get(0).getID()<0)
			{
				n = path.pop();
				lastPositionStood = new Dimension(x,y);
				//on top
				if(y-n.getY()==-1)
				{
					moveDown();
				}
				//on bottom
				if(y-n.getY()==1)
				{
					moveUp();
				}
				//to the left of
				if(x-n.getX()==-1)
				{
					moveRight();
				}
				//to the right of
				if(x-n.getX()==1)
				{
					moveLeft();
				}
				if(path.size()==1)
				{
					path.clear();
					walking = false;
				}
			}
		
		}	
		}//if walk is random
		else{if(walkingBehaviour.equals("true random"))
		{
			Random r = new Random();
			int s =  r.nextInt(4);
				
			switch(s)
			{
				case 0:
					moveUp();
				case 1:
					moveDown();
				case 2:
					moveLeft();
				case 3:
					moveRight();
			}
		}else{if(walkingBehaviour.equalsIgnoreCase("stay"))
		{
			//do nothing
		}}}
	}


	
	public boolean hasAttribute(String a)
	{
		return aib.haveIThisAttribute(a);
	}
	public void addAttribute(String a)
	{
		aib.addAttribute(a);
	}
	
	
	public TileNode[][] getWorldView()
	{
		return world;
	}
	
	public void setWorldView(TileNode[][] world)
	{
		this.world = world;
	}

	public void run() 
	{
		if(!talking&!dead)
			runTurn();
	}

	public boolean getTalking() {
		return talking;
	}

	public void setTalking(boolean talk) {
		talking = talk;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}
	
	/**
	 * @return the currentXP
	 */
	public int getCurrentXP() {
		return currentXP;
	}

	public void addCurrentXP(int add)
	{
		currentXP = currentXP+add;
		while(currentXP>=XPtoLvl)
		{
			currentXP= currentXP-XPtoLvl;
			levelUp();
		}
			
	}

	/**
	 * @return the xPtoLvl
	 */
	public int getXPtoLvl() {
		return XPtoLvl;
	}

	
	private void levelUp()
	{
		level++;
		XPtoLvl = (int) (Math.pow(level,1.1)*100);
	}

	/**
	 * @return the assocQuestID
	 */
	public String getAssocQuestID() {
		return assocQuestID;
	}

	/**
	 * @param assocQuestID the assocQuestID to set
	 */
	public void setAssocQuestID(String assocQuestID) {
		this.assocQuestID = assocQuestID;
	}
	
	
}
