package people;

import java.util.ArrayList;

public class AIBehaviour 
{
	private ArrayList<String> attributes;
	private int playerDisposition = 100;
	
	//Attributes
	/*
	 * Friendly- Non Hostile
	 * Hostile- Aggressive
	 * 
	 * Personality
	 * Noble
	 * Shy
	 * Apathetic
	 * Funny
	 * Rude
	 * 
	 * Job
	 * 
	 */
	
	public AIBehaviour()
	{
		attributes = new ArrayList<String>();
	}
	
	private int getDisposition()
	{
		return playerDisposition;
	}
	
	public boolean haveIThisAttribute(String a)
	{
		for(String att: attributes)
		{
			if(att.equals(a))
			{
				return true;
			}
		}
		return false;
	}
	
	public void addAttribute(String a)
	{
		attributes.add(a);
	}
}
