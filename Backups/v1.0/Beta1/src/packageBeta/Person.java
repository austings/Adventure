package packageBeta;

public class Person 
{
	public int STR;
	public int INT;
	public int DEX;
	
	public String name;
	
	public Person(String name)
	{
		this.name = name;
		STR = 5;
		INT = 5;
		DEX = 5;
	}
	
	public int getSTR()
	{
		return STR;
	}
	
	public void addSTR(int i)
	{
		STR = STR+i;
	}
	
	public int getINT()
	{
		return INT;
	}
	
	public void addINT(int i)
	{
		INT = INT+i;
	}
	
	public int getDEX()
	{
		return DEX;
	}
	
	public void addDEX(int i)
	{
		DEX = DEX+i;
	}
}
