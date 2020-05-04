package tools;

import model.TileNode;
import model.TileThing;

public class Spell extends TileThing implements Runnable
{
	private int intensity;
	private String direction;
	private TileNode[][] world;
	private int x;
	private int y;
	
	public Spell(String name, int ImageID)
	{
		super(name,ImageID);
	}
	
	public Spell(String name, int ImageID,int i, int radius,int x, int y)
	{
		super(name,ImageID);
		intensity = i;
		this.x = x;
		this.y = y;
	}
	
	public void fire(TileNode[][] world, int x, int y, String direction)
	{
		this.world = world;
		this.x = x;
		this.direction = direction;
		this.y=y;
	}

	public void run() {
		/*
		if(this.getName().equals("Fireball"))
		{
			world.
		}*/
		
	}
}
