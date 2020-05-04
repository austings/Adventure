package tools;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.Random;

public class NameGenerator 
{
	private ArrayList<String> generatedNames = new ArrayList<String>();
	private ArrayList<String> mentionedNamesToUse = new ArrayList<String>();
	
	public void NameGenerator()
	{
		
	}
	
	public String getHumanBoyName()
	{
		String name = "Joe Smith";
		try (LineNumberReader rdr = new LineNumberReader(new FileReader("src/resources/text/names.txt"))) 
		{
			String first = "";
			String second = "";
			Random r = new Random();
			int firstNameInt = r.nextInt(1000)+3;
			int secondNameInt = r.nextInt(202)+2004;
			for (String line = null; (line = rdr.readLine()) != null;) 
			{
				if (rdr.getLineNumber() ==firstNameInt ) 
				{
					first = line;
				}
				else
				{
					if(rdr.getLineNumber() ==secondNameInt)
					{
						second = line;
					}
				}
			}
			name = first+" "+second;
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return name;
	}
	
	public String getHumanGirlName()
	{
		String name = "Jane Smith";
		try (LineNumberReader rdr = new LineNumberReader(new FileReader("src/resources/text/names.txt"))) 
		{
			String first = "";
			String second = "";
			Random r = new Random();
			int firstNameInt = r.nextInt(1000)+1003;
			int secondNameInt = r.nextInt(202)+2005;
			for (String line = null; (line = rdr.readLine()) != null;) 
			{
				if (rdr.getLineNumber() ==firstNameInt ) 
				{
					first = line;
				}
				else
				{
					if(rdr.getLineNumber() ==secondNameInt)
					{
						second = line;
					}
				}
			}
			name = first+" "+second;
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return name;
	}
	
	public String getTownName()
	{
		String name = "Town";
		Random r= new Random();
		//If we have no previously mentioned Names to use, or if we do and randomly 25% chance not to select it
		//(This prevents all previously mentioned towns from suddenly appearing next to each other. Most mentioned towns will appear near the location they
		//are mentioned but this allows some degree of range on where people are from.)
		if(!mentionedNamesToUse.isEmpty()&r.nextInt(4+1)==1|mentionedNamesToUse.isEmpty())
		{	
			
			String prefix = "";
			String suffix = "";
			String quantifier = "";
			switch(r.nextInt(30)+1)
			{
			case 1:
				prefix = "Aero";
				break;
			case 22:
				prefix = "Apple";
				break;
			case 7:
				prefix = "Bal";
				break;
			case 24:
				prefix = "Bash";
				break;
			case 12:
				prefix = "Cull";
				break;
			case 13:
				prefix = "Deep";
				break;
			case 3:
				prefix = "Der";
				break;
			case 16:
				prefix = "Dun";
				break;
			case 15:
				prefix = "Erin";
				break;
			case 6:
				prefix = "Fal";
				break;
			case 17:
				prefix = "Fend";
				break;
			case 25:
				prefix = "Grow";
				break;
			case 4:
				prefix = "Hen";
				break;
			case 26:
				prefix = "Jolly";
				break;
			case 9:
				prefix = "Karrin";
				break;
			case 2:
				prefix = "Lum";
				break;
			case 14:
				prefix = "Mast";
				break;
			case 10:
				prefix = "Nas";
				break;
			case 11:
				prefix = "Nurin";
				break;
			case 27:
				prefix = "Oval";
				break;
			case 18:
				prefix = "Quill";
				break;
			case 21:
				prefix = "Roc";
				break;
			case 5:
				prefix = "Rom";
				break;
			case 28:
				prefix = "Sorrow";
				break;
			case 29:
				prefix = "Sen";
				break;
			case 30:
				prefix = "Tor";
				break;
			case 20:
				prefix = "Und";
				break;
			case 19:
				prefix = "Varo";
				break;
			case 8:
				prefix = "Zorro";
				break;
			case 23:
				prefix = "Zul";
				break;
			}
			switch(r.nextInt(30)+1)
			{
			case 11:
				suffix = "ad";
				break;
			case 6:
				suffix = "ador";
				break;
			case 2:
				suffix = "barrow";
				break;
			case 30:
				suffix = "bend";
				break;
			case 1:
				suffix = "burg";
				break;
			case 7:
				suffix = "chester";
				break;
			case 18:
				suffix = "crevase";
				break;
			case 28:
				suffix = "den";
				break;
			case 3:
				suffix = "dor";
				break;
			case 23:
				suffix = "en";
				break;
			case 24:
				suffix = "fen";
				break;
			case 20:
				suffix = "grad";
				break;
			case 15:
				suffix = "hall";
				break;
			case 29:
				suffix = "hallow";
				break;
			case 16:
				suffix = "hill";
				break;
			case 5:
				suffix = "in";
				break;
			case 10:
				suffix = "keep";
				break;
			case 4:
				suffix = "land";
				break;
			case 13:
				suffix = "let";
				break;
			case 25:
				suffix = "nast";
				break;
			case 12:
				suffix = "rium";
				break;
			case 9:
				suffix = "run";
				break;
			case 27:
				suffix = "sin";
				break;
			case 19:
				suffix = "sky";
				break;
			case 8:
				suffix = "stead";
				break;
			case 26:
				suffix = "tin";
				break;
			case 21:
				suffix = "turn";
				break;
			case 17:
				suffix = "valley";
				break;
			case 22:
				suffix = "watch";
				break;
			case 14:
				suffix = "zum";
				break;
			}
			name = prefix+suffix+quantifier;
			generatedNames.add(name);
		}
		else
		{
			name = mentionedNamesToUse.get(0);
			generatedNames.add(name);
			mentionedNamesToUse.remove(0);
		}
		return name;
		
	}
	
	public String getForestName()
	{
		String name ="";
		Random r = new Random();
		switch(r.nextInt(5))
		{
		case 0:
			name ="Forest";
			break;
		case 1:
			name= "Forest";
			break;
		case 2:
			name= "Forest";
			break;
		case 3:
			name= "Forest";
			break;
		case 4:
			name= "Forest";
			break;
		}
		return name;
	}
	
	public static void main(String [] args)
	{
		NameGenerator ng= new NameGenerator();
		for(int i = 0;i<20;i++)
			System.out.println(ng.getHumanGirlName());
	}
}
