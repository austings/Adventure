package model;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

import people.*;


public class TileNode 
{
	private int x;
	private int y;
	private int ImageID;
	private int lowerID=0;//if there is an object on this tile w/image
	private LinkedList<TileThing> items;
	private boolean hasCreature = false;
	private Creature creature;
	
	/**
	 * Constructor
	 * @param id the id of that image file
	 * @param x the x coordinate in the world
	 * @param y the y coordinate in the world
	 */
	public TileNode(int id,int x,int y)
	{
		ImageID = id;
		this.x = x;
		this.y = y;
		items = new LinkedList<TileThing>();
	}
	
	
	
	public TileThing removeItem(String UUID)
	{
		int pos = 0;
		TileThing returnT = null;
		
		Iterator<TileThing> iter = items.iterator();

		while (iter.hasNext()) 
		{
		    TileThing str = iter.next();

		    if (str.getCallNum()==UUID)
		    {
		    	returnT = str;
		    	if(returnT instanceof Creature)
		    	{
		    		hasCreature = false;
			    	if(ImageID==20)
			    	{
			    		setImage(-20);
			    	}
			    }
			        iter.remove();
			        creature = null;
		    }
		}

		return returnT;
	}
	
	/**
	 * Replace the current image with a new one
	 * @param image
	 * @param id
	 */
	public void setImage(int id)
	{
		ImageID = id;
	}

	/**
	 * Get the image id
	 * @return
	 */
	public int getID() 
	{
		if(hasCreature)
		{
			return creature.getImageID();
		}
		if(items.size()>0){
			for(TileThing t : items)
			{
				if(t.getImageID()!=0)
				{
					
					lowerID = t.getImageID();
					return lowerID;
				}
			}
		}
		return ImageID;
		
	}

	/**
	 * Get X Coord
	 * @return
	 */
	public int getX()
	{
		return x;
	}
	
	/**
	 * Get Y Coord
	 * @return
	 */
	public int getY()
	{
		return y;
	}
	
	/**
	 * Looks at the current image and if its ID is postiive, we know its
	 * a type of tile which cannot be walked onto.
	 * @return
	 */
	public boolean isSolid()
	{
		if(ImageID>0)
		{
				return true;
		}
		return false;
	}

	/**
	 * Adds this item to the item list;
	 * @param i
	 */
	public void addItem(TileThing i)
	{
		if(i instanceof Creature)
		{
			creature = (Creature)i;
			hasCreature = true;
		}
		items.add(i);
	}
	
	public Creature getCreature()
	{
		if(hasCreature)
			return creature;
		return null;
	}
	
	/**
	 * Does something when another creature trys to go on this one's tile
	 * @param c
	 */
	public int poke(Creature c)
	{
		//-20 is the door open code. This switches if the door is open or not.
		if(ImageID==20)
		{
			ImageID=-20;
		}
		//this is a combat string. if a player runs into a hostile character we roll to attack it, and return 
		//whether or not we hit it
		if(hasCreature)
		{
			if(creature.hasAttribute("Hostile"))
			{
				if(creature.attacked(c.getDEX()).equals("hit"))
				{
					int hit = c.getMeleeAttackValue();
					creature.subtractHP(hit);
					if(creature.getCurrentHP()<=0)
					{
						LinkedList<TileThing> newItems = creature.die();
						for(TileThing t : newItems)
						{
							addItem(t);
						}
						creature = null;
						hasCreature = false;
						return(Integer.parseInt("984400"+hit));
					}
					return(Integer.parseInt("994400"+hit));
				}
				else
					return(-994400);
			}

		}
		return getID();
	}
	
	public LinkedList<TileThing> getItems()
	{
		
		return items;
	}
	
	/**
	 * Returns and removes the item with the same name
	 * @param name
	 * @return return the item with the name given, otherwise give null
	 */
	public TileThing getItem(String name)
	{
		for(TileThing z: items)
		{
			if(z.getName()==name)
			{
				TileThing r = z;
				items.remove(z);
				return r;
			}
		}
		return null;
	}

	/////////
	////////Pathfinding Stuff
	////////
	private int heuristic=0;
	private int movement=0;
	public int f=0;
	public TileNode parent;
		
	public int getF()
	{
		f = heuristic+movement;
		return f;
	}
	
	public int getMovement() {
		return movement;
	}

	public void addMovement(int movement) {
		this.movement = this.movement+movement;
	}
	public void setMovement(int movement) {
		this.movement = movement;
	}

	public int getHeuristic() {
		return heuristic;
	}

	public void setHeuristic(int heuristic) {
		this.heuristic = heuristic;
	}


	
	
	
	
	
	
	
}
