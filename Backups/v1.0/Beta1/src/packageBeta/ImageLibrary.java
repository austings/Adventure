package packageBeta;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

public class ImageLibrary 
{
    private Map<Integer, BufferedImage> b;
	
    /*
     * Image Codes
     * -1 Grass
     * 0  Blank Tile
     * 1  Player Tile
     * 2  Wall1A; brown wall LR
     * 3  Wall1B; brown wall UD
     * 4  Wall1C; full brown
     */
	
	public ImageLibrary()
	{
		//create new hashmap
		b = new HashMap<Integer,BufferedImage>();
		
		try {
			//load images for tiles
			BufferedImage player = ImageIO.read(getClass().getResourceAsStream("/tiles/player.png"));
			BufferedImage wall1a = ImageIO.read(getClass().getResourceAsStream("/tiles/wall1a.png"));
			BufferedImage wall1b = ImageIO.read(getClass().getResourceAsStream("/tiles/wall1b.png"));
			BufferedImage wall1c = ImageIO.read(getClass().getResourceAsStream("/tiles/wall1c.png"));
			BufferedImage green = ImageIO.read(getClass().getResourceAsStream("/tiles/green.png"));
			BufferedImage none = ImageIO.read(getClass().getResourceAsStream("/tiles/none.png"));
			
			b.put(1, player);
			b.put(2, wall1a);
			b.put(3, wall1b);
			b.put(4, wall1c);
			b.put(0, none);
			b.put(-1, green);
		} catch (IOException e) {
			e.printStackTrace();
		}
		//associate term with each tile
	}
	
	public BufferedImage get(int s)
	{
		return b.get(s);
	}
}
