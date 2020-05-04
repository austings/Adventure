package model;

import java.util.UUID;

public class TileThing 
{
	private String name;
	private String callNum;
	private int baseValue;
	private int weight =0;
	private int id = -25; //an id is for the image, the callNum is the unique identifier.
	private String ToolTipText = "";
	public boolean equippable = false;
	public boolean readable = false;
	private String type = "object";
	
	public TileThing(String name)
	{
		this.name = name;
		callNum = UUID.randomUUID().toString();
	}
	public TileThing(String name, int id)
	{
		this.name = name;
		callNum = UUID.randomUUID().toString();
		this.id = id;
	}
	
	public void setEquippable(String type)
	{
		this.type = type;
		if(equippable)
			equippable = false;
		else
			equippable = true;
	}
	
	public void setReadable(String type)
	{
		this.type = type;
		if(readable)
			readable = false;
		else
			readable = true;
	}
	
	public String getType()
	{
		return type;
	}
	
	//text must contain html
	public void setToolTipText(String text)
	{
		ToolTipText=
				"<html><font size=\"15\" color=\"black\">"+name
				+"</font><font size=\"12\" color=\"gold\"><br><br>Base Price:"+baseValue +"<br></font>"+
				"Weight:"+weight +"<br>"+
				"Type:"+type +"<br><br>"+
				text+"</html>";
	}
	
	
	public int getImageID()
	{
		return id;
	}
	
	public void setImageID(int id)
	{
		this.id = id;
	}
	
	public String getName()
	{
		return name;
	}
	
	public String getCallNum()
	{
		return callNum;
	}
	
	public void setName(String name)
	{
		this.name =name;
	}
	
	public void setWeight(int s)
	{
		weight = s;
	}
	
	public int getWeight()
	{
		return weight;
	}
	
	public void setValue(int value)
	{
		baseValue = value;
	}
	
	public int getValue()
	{
		return baseValue;
	}

	public String getToolTipText()
	{
		if(ToolTipText=="")
		{
			return name;
		}
		else
		{
			return ToolTipText;
		}
	}
}
