package model;

import java.util.ArrayList;
import java.util.UUID;

import people.Creature;

public class Quest 
{
	private ArrayList<QuestStage> stages;
	private int currentStage = 0;
	private String giver;
	private String questID = "";
	private String questName;
	
	public Quest(Creature giver, String name)
	{
		setQuestName(name);
		stages = new ArrayList<QuestStage>();
		this.giver = giver.getName();
		questID = UUID.randomUUID().toString();
	}
	
	public void addStage(String goal)
	{
		stages.add(new QuestStage(goal));
	}
	
	public void addStage(String goal, String questText)
	{
		stages.add(new QuestStage(goal));
	}
	
	public void addStage(String goal, String questText, String dialogue)
	{
		stages.add(new QuestStage(goal));
	}

	/**
	 * @return the currentStage
	 */
	public int getCurrentStage() {
		return currentStage;
	}
	
	public ArrayList<QuestStage> getStages()
	{
		return stages;
	}

	public void incrementCurrentStage(int add) {
		currentStage = currentStage+add;
	}

	/**
	 * @return the giver
	 */
	public String getGiver() {
		return giver;
	}

	/**
	 * @param giver the giver to set
	 */
	public void setGiver(String giver) {
		this.giver = giver;
	}

	/**
	 * @return the questName
	 */
	public String getQuestName() {
		return questName;
	}

	/**
	 * @param questName the questName to set
	 */
	public void setQuestName(String questName) {
		this.questName = questName;
	}
}
