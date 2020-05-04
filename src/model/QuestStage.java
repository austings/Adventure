package model;

import java.util.LinkedList;

import library.ItemLibrary;
import menu.Backpack;
import people.Creature;

public class QuestStage 
{
	private String goal;
	private String questText;
	private String dialogue;
	private boolean currentStage = false;
	private boolean complete = false;
	private boolean trigger = false;
	private ItemLibrary il = new ItemLibrary();
	
	public QuestStage(String goal)
	{
		this.goal = goal;
	}
	
	/**
	 * @return the currentStage
	 */
	public boolean isCurrentStage() {
		return currentStage;
	}

	/**
	 * @param currentStage the currentStage to set
	 */
	public void setCurrentStage(boolean currentStage) {
		this.currentStage = currentStage;
	}

	/**
	 * @return the complete
	 */
	public boolean isComplete() {
		return complete;
	}

	/**
	 * @param complete the complete to set
	 */
	public void setComplete(boolean complete) {
		this.complete = complete;
	}

	/*
	 * If the quest goal is "player has x item", must send backpack.
	 * If the quest goal is "Creature is dead", must send the target creature
	 * If the quest goal is "Creature is talking", must send target creature;
	 * else condition is void and trigger is only consideration.
	 * Must call checkSuccessCondition on new queststart to set the text of the quest.
	 */
	private boolean checkSuccessCondition(Object condition)
	{
		if(goal.startsWith("PLAYER"))
		{
			if(goal.contains("HAS")&condition instanceof Backpack)
			{
				Backpack bp = (Backpack)condition;
				LinkedList<TileThing> bag = bp.getBag();
				int number = Integer.parseInt(goal.substring(11,12));
				String item = goal.substring(13);
				int haveTotal= 0;
				for(TileThing t : bag) {
					if(t.getName().equals(item))
						haveTotal++;
						
				}
				if(haveTotal==number)
					complete = true;
				

			}
		}
		if(goal.startsWith("CREATURE")&condition instanceof Creature)
		{
			Creature c = (Creature) condition;
			if(goal.contains("DEAD"))
			{
				complete = c.isDead();
				
			}
			else {
				if(goal.contains("TALKING"))
				{
					complete = c.getTalking();
				}
			}
		}
		if(goal.startsWith("TRIGGER"))
		{
			complete = trigger;
		}
		return complete;
	}

	/**
	 * @return the dialogue
	 */
	public String getDialogue() {
		return dialogue;
	}

	/**
	 * @param dialogue the dialogue to set
	 */
	public void setDialogue(String dialogue) {
		this.dialogue = dialogue;
	}

	/**
	 * @return the questText
	 */
	public String getQuestText() {
		return questText;
	}

	/**
	 * @param questText the questText to set
	 */
	public void setQuestText(String questText) {
		this.questText = questText;
	}
}
