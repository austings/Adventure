package library;

import java.util.HashMap;
import java.util.LinkedList;

import model.TileThing;


public class BodyPartLibrary 
{
	private ItemLibrary il;
	private LinkedList<TileThing> objsToAdd;
	
	public BodyPartLibrary(ItemLibrary il)
	{
		this.il = il;
		objsToAdd = new LinkedList<TileThing>();
	}
	
	public LinkedList<TileThing> getWolfGuts()
	{
		objsToAdd = new LinkedList<TileThing>();
		objsToAdd.add(il.getBodyPart("Eye", "Wolf", 1));
		objsToAdd.add(il.getBodyPart("Eye", "Wolf", 1));
		objsToAdd.add(il.getBodyPart("Liver", "Wolf", 10));
		objsToAdd.add(il.getBodyPart("Heart", "Wolf", 15));
		objsToAdd.add(il.getBodyPart("Brain", "Wolf", 10));
		objsToAdd.add(il.getBodyPart("Pelt", "Wolf", 50));
		objsToAdd.add(il.getBodyPart("Claw", "Wolf", 5));
		objsToAdd.add(il.getBodyPart("Claw", "Wolf", 5));
		return objsToAdd;
	}

}
