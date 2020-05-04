package library;

import java.awt.Dimension;
import java.util.Random;

import model.TileNode;
import model.TileThing;

public class ModuleLibrary 
{
	//sets what current module to use
	private int currentModuleID = 0;
	private String moduleGroup = "town";
	//town
	//forest
	private ItemLibrary itemL = new ItemLibrary();
	
	//higher numbers mean more modules
	private final int moduleDensity = 10;
	//affects how dense modules spawn
	
	
	public ModuleLibrary()
	{
		
	}
	
	public int getDensity()
	{
		return moduleDensity;
	}
	
	public ModuleLibrary(String type)
	{
		moduleGroup = type;
	}
	
	public void setModule(int s)
	{
		currentModuleID = s;
	}
	
	public Dimension getModuleSize()
	{
		if(moduleGroup.equals("Village")) {
			switch(currentModuleID)
			{
			case 0://small house
				return new Dimension(10,10);
			case 1://large house
				return new Dimension(15,15);
			default://small house
				return new Dimension(10,10);
			}
		}
		if(moduleGroup.equals("Forest"))
		{
			switch(currentModuleID)
			{
			case 0://bush
				return new Dimension(4,4);
			case 1://log
				return new Dimension(4,4);
			default://bush
				return new Dimension(4,4);
			}
		}
		return new Dimension(10,10);
	}
	
	public TileNode[][] paintLargeHouse(TileNode[][] worldView, int resultx, int resulty)
	{
		//paint stuff
		TileThing[] houseClothingItem = itemL.getCommonEquipment();
		Random r = new Random();
		int result = r.nextInt(houseClothingItem.length);
				//top wall
				worldView[resultx+1][resulty+1].setImage(2);
				worldView[resultx+2][resulty+1].setImage(2);
				worldView[resultx+3][resulty+1].setImage(2);
				worldView[resultx+4][resulty+1].setImage(2);
				worldView[resultx+5][resulty+1].setImage(2);
				worldView[resultx+6][resulty+1].setImage(2);
				worldView[resultx+7][resulty+1].setImage(2);
				worldView[resultx+8][resulty+1].setImage(4);
				worldView[resultx+8][resulty+2].setImage(3);
				worldView[resultx+8][resulty+2].setImage(4);
				worldView[resultx+9][resulty+2].setImage(2);
				worldView[resultx+10][resulty+2].setImage(2);
				worldView[resultx+11][resulty+2].setImage(2);
				worldView[resultx+12][resulty+2].setImage(4);
				
				//side walls
				worldView[resultx][resulty+1].setImage(4);
				worldView[resultx][resulty+2].setImage(3);
				worldView[resultx][resulty+3].setImage(3);
				worldView[resultx][resulty+4].setImage(3);
				worldView[resultx][resulty+5].setImage(3);
				worldView[resultx][resulty+6].setImage(3);
				worldView[resultx][resulty+7].setImage(3);
				worldView[resultx][resulty+8].setImage(3);
				worldView[resultx][resulty+9].setImage(3);
				worldView[resultx][resulty+10].setImage(4);
				
				
				worldView[resultx+12][resulty+3].setImage(3);
				worldView[resultx+12][resulty+4].setImage(3);
				worldView[resultx+12][resulty+5].setImage(20);
				worldView[resultx+12][resulty+6].setImage(3);
				worldView[resultx+12][resulty+7].setImage(3);
				worldView[resultx+12][resulty+8].setImage(3);
				
				//bottom wall

				worldView[resultx+1][resulty+10].setImage(2);
				worldView[resultx+2][resulty+10].setImage(2);
				worldView[resultx+3][resulty+10].setImage(2);
				worldView[resultx+4][resulty+10].setImage(2);
				worldView[resultx+5][resulty+10].setImage(2);
				worldView[resultx+6][resulty+10].setImage(2);
				worldView[resultx+7][resulty+10].setImage(2);
				worldView[resultx+8][resulty+10].setImage(4);
				worldView[resultx+8][resulty+9].setImage(3);
				worldView[resultx+8][resulty+9].setImage(3);
				worldView[resultx+8][resulty+9].setImage(4);
				worldView[resultx+9][resulty+9].setImage(2);
				worldView[resultx+10][resulty+9].setImage(2);
				worldView[resultx+11][resulty+9].setImage(2);
				worldView[resultx+12][resulty+9].setImage(4);
				
				return worldView;
	}
	
	public TileNode[][] paintBush(TileNode[][] worldView, int resultx, int resulty)
	{
		worldView[resultx+1][resulty+1].setImage(100);
		return worldView;
	}
	
	public TileNode[][] paintLog(TileNode[][] worldView, int resultx, int resulty)
	{
		worldView[resultx+1][resulty+1].setImage(101);
		return worldView;
	}
	
	public TileNode[][] paintHouse(TileNode[][] worldView, int resultx, int resulty)
	{
		//paint stuff
		TileThing[] houseClothingItem = itemL.getCommonEquipment();
		Random r = new Random();
		int result = r.nextInt(houseClothingItem.length);
		//top wall
		worldView[resultx+1][resulty+1].setImage(2);
		worldView[resultx+2][resulty+1].setImage(2);
		worldView[resultx+3][resulty+1].setImage(2);
		worldView[resultx+4][resulty+1].setImage(2);
		worldView[resultx+5][resulty+1].setImage(2);
		worldView[resultx+6][resulty+1].setImage(2);
		worldView[resultx+7][resulty+1].setImage(2);
		
		//side walls
		worldView[resultx][resulty+1].setImage(4);
		worldView[resultx][resulty+2].setImage(3);
		worldView[resultx][resulty+3].setImage(3);
		worldView[resultx][resulty+4].setImage(3);
		worldView[resultx][resulty+5].setImage(3);
		worldView[resultx][resulty+6].setImage(4);
		worldView[resultx+3][resulty+2].addItem(houseClothingItem[result]);
		for(int i = 2;i<6;i++)
		{
			for(int j =1;j<8;j++)
			{
				worldView[resultx+j][resulty+i].setImage(-22);
			}
		}
		worldView[resultx+7][resulty+2].setImage(31);
		worldView[resultx+4][resulty+4].setImage(30);
		worldView[resultx+5][resulty+4].setImage(-21);
		worldView[resultx+3][resulty+4].setImage(-21);
		
		worldView[resultx+8][resulty+1].setImage(4);
		worldView[resultx+8][resulty+2].setImage(3);
		worldView[resultx+8][resulty+3].setImage(3);
		worldView[resultx+8][resulty+4].setImage(3);
		worldView[resultx+8][resulty+5].setImage(3);
		worldView[resultx+8][resulty+6].setImage(4);
		
		//bottom wall
		worldView[resultx+1][resulty+6].setImage(2);
		worldView[resultx+2][resulty+6].setImage(2);
		worldView[resultx+3][resulty+6].setImage(2);
		worldView[resultx+4][resulty+6].setImage(20);
		worldView[resultx+5][resulty+6].setImage(2);
		worldView[resultx+6][resulty+6].setImage(2);
		worldView[resultx+7][resulty+6].setImage(2);
		return worldView;
	}
	
	public TileNode[][] returnAddedModule(TileNode[][] worldView,int resultx, int resulty)
	{
		if(moduleGroup.equals("Village"))
		{
			switch(currentModuleID)
			{
			case 0:
				return paintHouse(worldView, resultx, resulty);
			case 1:
				return paintLargeHouse(worldView, resultx, resulty);
			default:
				return paintHouse(worldView, resultx, resulty);
			}
		}
		if(moduleGroup.equals("Forest"))
		{
			switch(currentModuleID)
			{
			case 0:
				return paintBush(worldView, resultx, resulty);
			case 1:
				return paintLog(worldView, resultx, resulty);
			default:
				return paintBush(worldView, resultx, resulty);
			}
		}
		return paintHouse(worldView, resultx, resulty);
	}
}
