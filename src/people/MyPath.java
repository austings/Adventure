package people;

import java.util.ArrayList;

import model.TileNode;

public class MyPath 
{
	private ArrayList<TileNode> n = new ArrayList<TileNode>();
	private int cost;
	
	public MyPath()
	{
		cost = 0;
	}
	
	public void addNode(TileNode t)
	{
		n.add(t);
		cost++;
	}
	
	public int getCost()
	{
		return cost;
	}

}
