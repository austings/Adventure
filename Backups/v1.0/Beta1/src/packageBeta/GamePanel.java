package packageBeta;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

public class GamePanel extends JPanel
{
	private ImageLibrary lib;//contains all tiles
	private TileNode[][] world;//the world on screen
	private TileNode[][] worldView;//the entire world
	private BufferedImage previousFloorImage;//floor image previous to when player stepped on tile
	private int previousFloorImageID;//id of that image
	private Dimension playerAddress; //current position of the player
	
	public GamePanel(Dimension size)
	{
		//create our library of tiles to paint the world with
		lib = new ImageLibrary();
		previousFloorImage = lib.get(-1);
		
		//create the new world

		int x = size.width;
		int y = size.height;
		playerAddress = new Dimension (x/2,y/2);
		
		worldView = new TileNode[x][y];
		for(int i = x-1;i!=0;i--)
		{
			for(int j = y-1; j!=0;j--)
			{

				//creates a blank world
				worldView[i][j] = new TileNode(lib.get(-1),-1,i,j);
			}
		}
		
		//add player to world
		worldView[x/2][y/2].setImage(lib.get(1),1);
		
		paintHouse();

			
		//get screen viewport (player starts at center of map)
		world = new TileNode[21][21];
		
		//this is the x and y coordinates of the top left position
		//the player can see. On a 100x100 grid this is position 40
		x = (x/2)-10;//40 on 100x100
		y = (y/2)-10;//40 on 100x100
		int worldx=0;
		int worldy=0;
		//
		for(int i = x;worldx!=21;i++)
		{
			for(int j = y; worldy!=21;j++)
			{
				//System.out.println(i+", "+j);
				//System.out.println(worldx+" "+worldy);
				//creates a blank world
				TileNode n = worldView[i][j];
				world[worldx][worldy] = n;
				worldy++;
			}
			worldy = 0;
			worldx++;
		}

		repaint();
	}
	
	public void paintHouse()
	{
		//paint stuff
		//top wall
		worldView[51][51].setImage(lib.get(2),2);
		worldView[52][51].setImage(lib.get(2),2);
		worldView[53][51].setImage(lib.get(2),2);
		worldView[54][51].setImage(lib.get(2),2);
		worldView[55][51].setImage(lib.get(2),2);
		worldView[56][51].setImage(lib.get(2),2);
		worldView[57][51].setImage(lib.get(2),2);
		
		//side walls
		worldView[50][51].setImage(lib.get(4),4);
		worldView[50][52].setImage(lib.get(3),3);
		worldView[50][53].setImage(lib.get(3),3);
		worldView[50][54].setImage(lib.get(3),3);
		worldView[50][55].setImage(lib.get(3),3);
		worldView[50][56].setImage(lib.get(4),4);
		
		worldView[58][51].setImage(lib.get(4),4);
		worldView[58][52].setImage(lib.get(3),3);
		worldView[58][53].setImage(lib.get(3),3);
		worldView[58][54].setImage(lib.get(3),3);
		worldView[58][55].setImage(lib.get(3),3);
		worldView[58][56].setImage(lib.get(4),4);
		
		//bottom wall
		worldView[51][56].setImage(lib.get(2),2);
		worldView[52][56].setImage(lib.get(2),2);
		worldView[53][56].setImage(lib.get(2),2);
		worldView[54][56].setImage(lib.get(2),2);
		worldView[55][56].setImage(lib.get(2),2);
		worldView[56][56].setImage(lib.get(2),2);
		worldView[57][56].setImage(lib.get(2),2);
	}
	
	
	public void paint(Graphics g)
	{
		this.removeAll();
		this.updateUI();
		int coordx = 0;
		int coordy = 0;
		//g.drawImage(lib.get(0),0,0,null);
			
		for(int x = 0;x!=21;x++)
		{
			for(int y=0;y!=21;y++)
			{
				g.drawImage(world[x][y].getImage(), coordx, coordy,null);
				coordy= coordy+32;
			}
			coordy = 0;
			coordx = coordx+32;
		}
	}
	
	/*
	 * Method called when user presses one of the arrow keys.
	 * 
	 * This method should move the player tile on the worldView, then 
	 * adjust the world on the screen 
	 * @parameter String string- the direction of the arrow key pressed
	 * 
	 */
	public void movePlayer(String string) 
	{
		//get players int coords
		int x = playerAddress.width;
		int y = playerAddress.height;
		System.out.println(x+" ,"+y);
		//dont move if at end of map
		if(!(x==worldView.length-1))
		{
			//if this is the direction we want
			if(string =="right")
			{
				//and if we can move onto that tile
				if(!(worldView[x+1][y].isSolid()))
				{
					//move onto it
					worldView[x][y].setImage(previousFloorImage,previousFloorImageID);
					previousFloorImage = worldView[x+1][y].getImage();
					previousFloorImageID = worldView[x+1][y].getID();
					worldView[x+1][y].setImage(lib.get(1),1);
					playerAddress.width=playerAddress.width+1;
					//if we aren't near the end of the map, scroll
					if(playerAddress.width<worldView.length-10&&!(x<11))
						scrollRight();
					else
						repaint();
				}
			}
		}
		if(!(x<=1))
		{
			if(string =="left")
			{
				if(!(worldView[x-1][y].isSolid()))
				{
					worldView[x][y].setImage(previousFloorImage,previousFloorImageID);
					previousFloorImage = worldView[x-1][y].getImage();
					previousFloorImageID = worldView[x-1][y].getID();
					worldView[x-1][y].setImage(lib.get(1),1);
					playerAddress.width=playerAddress.width-1;
					if(playerAddress.width>10&&!(x>worldView.length-11))
						scrollLeft();
					else
						repaint();
				}
			}
		}
		if(!(y<=1))
		{
			if(string =="up")
			{
				if(!(worldView[x][y-1].isSolid()))
				{
					worldView[x][y].setImage(previousFloorImage,previousFloorImageID);
					previousFloorImage = worldView[x][y-1].getImage();
					previousFloorImageID = worldView[x][y-1].getID();
					worldView[x][y-1].setImage(lib.get(1),1);
					playerAddress.height=playerAddress.height-1;
					
					if(playerAddress.height>10&&!(y>worldView[1].length-11))
						scrollUp();
					else
						repaint();
				}
			}
		}
		if(!(y==worldView[1].length-1))
		{
			if(string =="down")
			{
				if(!(worldView[x][y+1].isSolid()))
				{
					worldView[x][y].setImage(previousFloorImage,previousFloorImageID);
					previousFloorImage = worldView[x][y+1].getImage();
					previousFloorImageID = worldView[x][y+1].getID();
					worldView[x][y+1].setImage(lib.get(1),1);
					playerAddress.height=playerAddress.height+1;
					if(playerAddress.height<worldView[1].length-10&&!(y<11))
						scrollDown();
					else
						repaint();
				}
			}
		}
	}
	
	public void scrollRight()
	{
		//loop through each row/column
		int x=0;
		int y=0;
		while(y<21)
		{
			//set to next image
			while(x!=20)
			{
				world[x][y] = world[x+1][y];
				x=x+1;
			}
			//last column needs to be pulled from worldview
			int wvx = world[x][y].getX();
			int wvy = world[x][y].getY();
			
			world[x][y]=worldView[wvx+1][wvy];
			x=0;
			y++;
		}
		//redraw
		repaint();

	}
	
	public void scrollLeft()
	{
		//loop through each row/column
		int x=20;
		int y=0;
		while(y<21)
		{
			//set to next image
			while(x!=0)
			{
				world[x][y] = world[x-1][y];
				x=x-1;
			}
			int wvx = world[x][y].getX();
			int wvy = world[x][y].getY();
			
			world[x][y]=worldView[wvx-1][wvy];
			x=20;
			y++;
		}
		//redraw
		repaint();
		
	}
	
	public void scrollUp()
	{
		//loop through each row/column
		int x=0;
		int y=20;
		while(x<21)
		{
			//set to next image
			while(y!=0)
			{
				world[x][y] = world[x][y-1];
				y=y-1;
			}
			int wvx = world[x][y].getX();
			int wvy = world[x][y].getY();
			
			world[x][y]=worldView[wvx][wvy-1];
			y=20;
			x++;
		}
		//redraw
		repaint();
	}
	
	public void scrollDown()
	{
		//loop through each row/column
		int x=0;
		int y=0;
		while(x<21)
		{
			//set to next image
			while(y!=20)
			{
				world[x][y] = world[x][y+1];
				y=y+1;
			}
			int wvx = world[x][y].getX();
			int wvy = world[x][y].getY();
			
			world[x][y]=worldView[wvx][wvy+1];
			y=0;
			x++;
		}
		//redraw
		repaint();
	}

		


}
