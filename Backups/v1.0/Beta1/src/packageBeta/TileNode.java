package packageBeta;

import java.awt.image.BufferedImage;

public class TileNode 
{
	private BufferedImage image;
	private int x;
	private int y;
	private int ImageID;
	private Item[] items;
	
	public TileNode(BufferedImage i,int id,int x,int y)
	{
		image = i;
		this.ImageID = id;
		this.x = x;
		this.y = y;
	}
	
	public BufferedImage getImage()
	{
		return image;
	}
	
	public void setImage(BufferedImage image, int id)
	{
		this.image = image;
		this.ImageID = id;
	}

	public int getID() {
		return ImageID;
		
	}

	public int getX()
	{
		return x;
	}
	
	public int getY()
	{
		return y;
	}
	
	public boolean isSolid()
	{
		if(ImageID>0)
		{
			System.out.println("true");
			return true;
		}
		System.out.println("false");
		return false;
	}
}
