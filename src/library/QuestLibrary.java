package library;

import java.util.ArrayList;
import java.util.HashMap;

import model.Quest;
import people.Creature;

public class QuestLibrary 
{
	//private HashMap<String,Quest> questList = new HashMap<String,Quest>();
	private ArrayList<Quest> questList = new ArrayList<Quest>();
	
	public QuestLibrary() {
	}
	
	/*
	 * PLAYER HAS X Y
		CREATURE X TALKING
		CREATURE X DIED
		TRIGGER X TRUE

	 */
	public Quest collectQuestHearts(Creature giver, String animal)
	{
		Quest collectCreatureHearts = new Quest(giver, "Collect "+animal+" Hearts");
		collectCreatureHearts.addStage("PLAYER HAS 5 "+animal+" Heart"
				,giver.getName()+" has asked that I retrieve 5 "+animal+" hearts. These animals have been plaguing the community for weeks now and have become a serious problem. "
				,"As the sheriff of this town, I'm responsible for these people's safety. If you can bring me five "+animal+" hearts, I'll pay you.");
		collectCreatureHearts.addStage("CREATURE TALKING", "I have the hearts I need. I should return to "+giver.getName()+" for my reward."
				,"Thanks for taking care of that for me. If you want to make some more money, there are always monsters to slay.");
		//questList.put("hearts", collectCreatureHearts);
		questList.add(collectCreatureHearts);
		return collectCreatureHearts;
	}
	
	
}
