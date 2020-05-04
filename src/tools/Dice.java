package tools;

import java.util.Random;
import people.Creature;

public class Dice 
{
	private int sides = 0;
	
	public Dice(int sides)
	{
		this.sides = sides;
		
	}
	
	//
	//Compares two rolls of a specified type
	//returns the winning creature.
	//
	public Creature compareTwoRolls(Creature a, Creature b,String type)
	{
		if(type=="NORMAL")
		{
			
			int aRoll;
			int bRoll;
			while( (aRoll = roll()) != (bRoll = roll()) )
			{
				if(aRoll>bRoll)
				{
					return a;
				}
				else
				{
					return b;
				}
			}
		
		}
		
		if(type=="DEX")
		{
			
			int aRoll;
			int bRoll;
			while( (aRoll = roll()) != (bRoll = roll()) )
			{
				if(aRoll>bRoll)
				{
					return a;
				}
				else
				{
					return b;
				}
			}
		}
		
		return null;
	}
	
	public int roll()
	{
		Random r = new Random();
		int result = r.nextInt(sides)+1;
		return result;
	}
	
	public int roll(int modifer)
	{
		Random r = new Random();
		int result = r.nextInt(sides)+1+modifer;
		return result;
	}
}
