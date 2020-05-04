package library;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

import model.TileThing;
import people.Creature;
import tools.Dice;
import tools.NameGenerator;

public class CreatureLibrary
{
	ArrayList<Creature> hostileCreatures = new ArrayList<Creature>();
	ArrayList<Creature> npcs = new ArrayList<Creature>();
	NameGenerator ng = new NameGenerator();
	private ItemLibrary il;
	
	public CreatureLibrary(ItemLibrary il)
	{
		this.il = il;
	}
	
	public void populateNPCs()
	{
		Creature eldwir = new Creature("Eldwir", new Dimension(45,45),1000);
		eldwir.addAttribute("friendly");
		//eldwir.addToInventory(true, il.getItem("Deremor Vol I"));
		eldwir.addGold(1000);
		Creature pahlin = new Creature("Princess Pahlin", new Dimension(60,60),2001);
		pahlin.addAttribute("friendly");
		Creature cowboy = new Creature("Sheriff Wilson",new Dimension(52,52),1002);
		cowboy.addAttribute("friendly");
		//pahlin.walkingBehaviour = new String("true random");
		Creature wolf = new Creature("Wolf", new Dimension(51,51),1003);
		wolf.addAttribute("hostile");

	}
	
	public Creature generateRandomNPC(Dimension spawn)
	{
		Random r = new Random();
		ItemLibrary il = new ItemLibrary();
		Creature generated;
		//gender
		if(r.nextInt(2)==1)
		{
			generated = new Creature(ng.getHumanBoyName(),spawn,r.nextInt(3)+1000);
		}
		else
		{
			generated = new Creature(ng.getHumanGirlName(),spawn,r.nextInt(1)+2000);
		}
		//generate attributes
		Dice d = new Dice(20);
		generated.addGold(r.nextInt(1000));
		generated.recalculateXPWorth();
		return generated;
	}
	
	public Creature generateRandomEnemy(Dimension spawn)
	{
		Random r = new Random();
		Creature generated;
		int index =r.nextInt(1);
		//creatureName
		String creatureName;
		//imageid
		int id;
		//objects to add to inv
		LinkedList<TileThing> objsToAdd = new LinkedList<TileThing>();
		//objects to add to person
		BodyPartLibrary bpl = new BodyPartLibrary(il);
		LinkedList<TileThing> organsToAdd = new LinkedList<TileThing>();
		//switch index to get creature generated
		switch(index)
		{
			case 0:
				index =r.nextInt(5);
				switch(index)
				{
				case 0:
					creatureName = "Young Wolf";	
					break;
				case 1:
					creatureName = "Dire Wolf";
					break;
				case 2:
					creatureName = "Pack Wolf";
					break;
				case 3:
					creatureName = "Old Wolf";
					break;
				case 4:
					creatureName = "Wolf Mother";
				default:
					creatureName = "Young Wolf";	
				}
				id = 3001;
				organsToAdd = bpl.getWolfGuts();
				break;
			default:
				id = 3001;
				creatureName = "Young Wolf";
				organsToAdd = bpl.getWolfGuts();
				break;
		}
			
		generated = new Creature(creatureName,spawn,id);
		//generate attributes
		Dice d = new Dice(20);
		generated.addAttribute("Hostile");
		for(TileThing t :objsToAdd)
			generated.addToInventory(true, t);
		for(TileThing t: organsToAdd)
			generated.addToOrgans(true, t);
		generated.recalculateXPWorth();
		return generated;
	}
}
