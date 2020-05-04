package tools;

import java.util.Date;

import javax.swing.JPanel;
import javax.swing.JTextPane;

public class Clock implements Runnable
{
	private boolean running = false;
	public int year;
	public String[] months = 
	{
		"New Dawn",
		"Brightus",
		"Lightus",
		"Sundor",
		"Julium",
		"Orious",
		"Helix",
		"Caractus",//named after caractacus from roman history
		"Moonlight",
		"Stardust",
		"Darkist",
		"Night's End"
	};
	
	public int month;
	public int day;
	
	private String date = "";
	
	private String time = "";
	public int hours;
	public int minutes;
	public int seconds;
	public boolean am=false;
	
	public Clock(int y,int m, int d)
	{
		year = y;
		day = d;
		month = m;
		date = d+" "+months[m]+", "+y+".";
		hours = 12;
		minutes = 0;
		seconds=0;
		time = "12:00:00PM";
		running = true;
	}
	
	//passes time
	public void passTime(String type, int intensity)
	{
		if(type=="s")
		{
			seconds = seconds+intensity;
			while (seconds>59)
			{
				seconds= seconds-60;
				minutes = minutes+1;
				if(minutes>59)
				{
					minutes= 0;
					hours++;
					if(hours==12)
					{
						if(am)
						{
							am = false;
						}
						else
						{
							am = true;
							day++;
							if(day>30)
							{
								day =1;
								month++;
								if(month>11)
								{
									month = 0;
									year++;
								}
							}
						}
					}
					if(hours>12)
					{
						hours = 1;
					}
				}
			}
		}
		
	}
	
	public String getTime()
	{
		String pm = "PM";
		if(am)
		{
			pm = "AM";
		}
		if(minutes<10|seconds<10)
		{
			if(minutes<10&seconds>9)
			{
				return time = hours+":0"+minutes+":"+seconds+" "+pm;
			}
			if(minutes>9&seconds<10)
			{
				return time = hours+":"+minutes+":0"+seconds+" "+pm;
			}
			if(minutes<10&seconds<10)
			{
				return time = hours+":0"+minutes+":0"+seconds+" "+pm;
			}
		}
		if(minutes==0|seconds==0)
		{
			if(minutes==0&seconds!=0)
			{
				return time = hours+":00:"+seconds+" "+pm;
			}
			if(minutes!=0&seconds==0)
			{
				return time = hours+":"+minutes+":00 "+pm;
			}
			if(minutes==0&seconds==0)
			{
				return time = hours+":00:00 "+pm;
			}
		}
		time = hours+":"+minutes+":"+seconds+" "+pm;
		return time;
	}
	
	public String getDate()
	{
		date = day+" "+months[month]+", "+year+".";
		return date;
	}
	
	public void run()
	{
		passTime("s",15);
	}

}
