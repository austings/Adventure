package people;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import library.QuestLibrary;
import model.Quest;
//tree structure
//https://stackoverflow.com/questions/3522454/java-tree-data-structure


public class ChatTree
{
	private BufferedReader br;
	private Creature c;//id
	private String response;
	private int optionMenu = 1;
	private QuestLibrary ql = new QuestLibrary();
	private Quest log;

	//#t((\d)?(t)?)+# expression to find opener
    public ChatTree(Creature d) {
    	
    	c=d;
    	d.setTalking(true);
    	
    	response = readFromFile("@GREETINGS@");
    }
    
    //dummy chatTree
    public ChatTree(int i)
    {
    	response = "NOT A RESPONSE";
    }
    
    public String getResponse()
    {
    	return response;
    }
    
    public Creature getCreature()
    {
    	return c;
    }

    public void setOptions(int i)
    {
    	optionMenu = i;
    }
    
    public ArrayList<String> getChoices()
    {
    	ArrayList<String> choices = new ArrayList<String>();
    	switch(optionMenu)
    	{
    	//option 1: default choices
    	case 1:
    		choices.add("Goodbye.");
    		choices.add("Can you trade with me?");
    		choices.add("Do you have any work for me?");
    		choices.add("Tell me about this area.");
    		choices.add("Tell me about yourself.");
    		break;
    	case 2://option 2: learning about the NPC "Tell me about yourself"
    		choices.add("Nevermind, goodbye.");
    		choices.add("Do you have any family?");
    		choices.add("Do you have any advice?");
    		choices.add("What do you dislike?");
    		choices.add("What do you like?");
    		choices.add("Where you are from?");
    		choices.add("What do you do?");
    		choices.add("Who are you?");
    		break;
    	case 3:
    		choices.add("I'll do it.");
    		choices.add("I don't have time for this.");
    		break;
    	default:
    		choices.add("Goodbye.");
    		break;
    	}
    	return choices;
    }
    
    
    public String readFromFile(String ident)
    {
    	//choices contains all the available options of things this character can say.
    	ArrayList<String> choices = new ArrayList<String>();
    	
    	//s is the fileline to be read.
    	String s ="";

    	try {
    		br = new BufferedReader(new FileReader("src/resources/text/conversation.txt"));
    		//while we can read a line
    	    while (((s = br.readLine()) != null)) {
    	    	if(s.contains(ident))//if this is the topic we are interested in,
    	    	{
    	    		while (((s = br.readLine()) != null)){//read the responses, and look at the attribute of the response.
	    	    		Pattern p = Pattern.compile("\\[[A-Za-z]+\\]");
	    	    		Matcher m = p.matcher(s);
	    	    		
	    	    		if(m.find()) {//take the attribute and see if our creature has it. If it does, add it to the list of possible responses.
	    	    			String attribute = s.substring(m.start()+1,m.end()-1);
	    	    			if(c.hasAttribute(attribute)|attribute.equals("Default")) {
	    	    				s = s.substring(m.end());
	    	    				choices.add(s);
	    	    			}
	    	    			else {//otherwise, if this is a generated response, generate the response then add it to the list of choices
	    	    			if(attribute.equals("Generate")) {
	    	    				s = s.substring(m.end());
	    	    				choices.add(generateResponse(s));
	    	    			}}
	    	    		}	
	    	    		else
	    	    		{//if we have a line without an attribute, we've hit the end of the list of responses so we can just stop reading.
	    	    			if(!s.startsWith("@"))//if this is the start, we don't break yet
	    	    				break;
	    	    		}
    	    		}
    	    		break;
    	    	}
    	    }
    	    br.close();
    	} 
    	catch (Exception e)
    	{
    		System.out.println("FILE NOT FOUND");
    	}
    	
    	Random r = new Random();
    	s=choices.get(r.nextInt(choices.size()));
    	response = s;
    	return s;
    }
    
    private String generateResponse(String type)
    {
    	StringBuilder buildResponse = new StringBuilder("");
    	String response = "Hm I'm not sure";
    	System.out.println(type);
    	if(type.equals("WHORU"))
    	{
    		Random r = new Random();
    		int switcher = r.nextInt(2);
    		switch(switcher)
    		{
    		case 0:
    			buildResponse.append("I am ");
    			break;
    		case 1:
    			buildResponse.append("My name is ");
    			break;
    		default:
    			break;
    		}
    		buildResponse.append(c.getName()+" ");
    		response = buildResponse.toString();
    		return response;
    	}
    	if(type.equals("WHEREUFROM"))
    	{
    		return response;
    	}
    	if(type.equals("WHATUDO"))
    	{
    		return response;
    	}
    	if(type.equals("WHATULIK"))
    	{
    		return response;
    	}
    	if(type.equals("WHATUDISLIK"))
    	{
    		return response;
    	}
    	if(type.equals("DOHAVFAM"))
    	{
    		return response;
    	}
    	if(type.equals("ADVICE"))
    	{
    		return response;
    	}
    	if(type.equals("RUMOR"))//not implemented
    	{
    		return response;
    	}
    	if(type.equals("QUEST"))
    	{
    		log = ql.collectQuestHearts(c, "Wolf");
    		response = "Yes, actually. Some wolves have been plaguing this community for awhile. If you could bring me back five wolf hearts, I will reward you.";
    		return response;
    	}
    	return response;
    }
    
    public Quest getQuest()
    {
    	return log;
    }

    /**
    public static class Node 
    {
    	private String dialogue;
    	private String tag;
    	private ArrayList<String> responses = new ArrayList<String>();
    	private ArrayList<Node> children = new ArrayList<Node>();
    	
    	public Node(String dialogue, String tag)
    	{
    		this.dialogue = dialogue;
    		this.tag = tag;
    	}
    	
    	public void setTag(String tag)
    	{
    		this.tag = tag;
    	}
    	public String getTag()
    	{
    		return tag;
    	}
    	

    	public ArrayList<Node> getChildren()
    	{
    		return children;
    	}
    	public void addChild(Node a)
    	{
    		children.add(a);
    	}
    	
    	public String getDialogue()
    	{
    		return dialogue;
    	}
    	
    	public ArrayList<String> getResponses()
    	{
    		return responses;
    	}
    	
    	
    	public void addResponse(String s)
    	{
    		responses.add(s);
    	}
    	
    }*/
}
