package library;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

public class ImageLibrary 
{
    private Map<Integer, BufferedImage> b;
	
    /*
     * Negative values are not solid objects.
     * Not valid codes
     * 994400___ attack and miss codes
     * 984400___ death code
     * -994400
     * Image Codes
     * -20 Open Door
     * -1 through -9 Grass
     * 0  Blank Tile
     * 1  Player Tile
     * 2  Wall1A; brown wall LR
     * 3  Wall1B; brown wall UD
     * 4  Wall1C; full brown 
     * 20 Door
     */
	
	public ImageLibrary()
	{
		//create new hashmap
		b = new HashMap<Integer,BufferedImage>();
		
		try {
			//load images for tiles
			BufferedImage player = ImageIO.read(getClass().getResourceAsStream("/resources/tiles/player.png"));
			BufferedImage wall1a = ImageIO.read(getClass().getResourceAsStream("/resources/tiles/wall1a.png"));
			BufferedImage wall1b = ImageIO.read(getClass().getResourceAsStream("/resources/tiles/wall1b.png"));
			BufferedImage wall1c = ImageIO.read(getClass().getResourceAsStream("/resources/tiles/wall1c.png"));
			
			BufferedImage green = ImageIO.read(getClass().getResourceAsStream("/resources/tiles/grassM.png"));
			BufferedImage greenT = ImageIO.read(getClass().getResourceAsStream("/resources/tiles/grassT.png"));
			BufferedImage greenB = ImageIO.read(getClass().getResourceAsStream("/resources/tiles/grassB.png"));
			BufferedImage greenBL = ImageIO.read(getClass().getResourceAsStream("/resources/tiles/grassBL.png"));
			BufferedImage greenBR = ImageIO.read(getClass().getResourceAsStream("/resources/tiles/grassBR.png"));
			BufferedImage greenTR = ImageIO.read(getClass().getResourceAsStream("/resources/tiles/grassTR.png"));
			BufferedImage greenTL = ImageIO.read(getClass().getResourceAsStream("/resources/tiles/grassTL.png"));
			BufferedImage greenL = ImageIO.read(getClass().getResourceAsStream("/resources/tiles/grassL.png"));
			BufferedImage greenR = ImageIO.read(getClass().getResourceAsStream("/resources/tiles/grassR.png"));
			
			BufferedImage homeTile = ImageIO.read(getClass().getResourceAsStream("/resources/tiles/hometile.png"));
			
			BufferedImage sBush = ImageIO.read(getClass().getResourceAsStream("/resources/tiles/bush.png"));
			BufferedImage log = ImageIO.read(getClass().getResourceAsStream("/resources/tiles/log.png"));
			
			BufferedImage blankTile = ImageIO.read(getClass().getResourceAsStream("/resources/tiles/blanktile.png"));
			BufferedImage blankHelm = ImageIO.read(getClass().getResourceAsStream("/resources/tiles/blankhelm.png"));
			BufferedImage blankChest = ImageIO.read(getClass().getResourceAsStream("/resources/tiles/blankchest.png"));
			BufferedImage blankPant = ImageIO.read(getClass().getResourceAsStream("/resources/tiles/blankpants.png"));
			BufferedImage blankBoot = ImageIO.read(getClass().getResourceAsStream("/resources/tiles/blankshoe.png"));
			BufferedImage blankNeck = ImageIO.read(getClass().getResourceAsStream("/resources/tiles/blankneck.png"));
			BufferedImage blankShoulder = ImageIO.read(getClass().getResourceAsStream("/resources/tiles/blankshoulder.png"));
			BufferedImage blankarml = ImageIO.read(getClass().getResourceAsStream("/resources/tiles/blankarml.png"));
			BufferedImage blankarmr = ImageIO.read(getClass().getResourceAsStream("/resources/tiles/blankarmr.png"));
			BufferedImage blankface = ImageIO.read(getClass().getResourceAsStream("/resources/tiles/blankface.png"));
			BufferedImage blankhandl = ImageIO.read(getClass().getResourceAsStream("/resources/tiles/blankhandl.png"));
			BufferedImage blankhandr = ImageIO.read(getClass().getResourceAsStream("/resources/tiles/blankhandr.png"));
			
			BufferedImage stool = ImageIO.read(getClass().getResourceAsStream("/resources/tiles/stool.png"));
			BufferedImage stone = ImageIO.read(getClass().getResourceAsStream("/resources/tiles/stone.png"));
			BufferedImage table = ImageIO.read(getClass().getResourceAsStream("/resources/tiles/table.png"));
			BufferedImage shelf = ImageIO.read(getClass().getResourceAsStream("/resources/tiles/shelf.png"));
			
			
			BufferedImage none = ImageIO.read(getClass().getResourceAsStream("/resources/tiles/none.png"));
			
			BufferedImage elf = ImageIO.read(getClass().getResourceAsStream("/resources/tiles/elf.png"));
			BufferedImage elfcu = ImageIO.read(getClass().getResourceAsStream("/resources/tiles/elfcu.png"));
			BufferedImage man = ImageIO.read(getClass().getResourceAsStream("/resources/tiles/man.png"));
			BufferedImage mancu = ImageIO.read(getClass().getResourceAsStream("/resources/tiles/mancu.png"));
			BufferedImage cowboy = ImageIO.read(getClass().getResourceAsStream("/resources/tiles/cowboy.png"));
			BufferedImage cowboycu = ImageIO.read(getClass().getResourceAsStream("/resources/tiles/cowboycu.png"));
			BufferedImage princesscu = ImageIO.read(getClass().getResourceAsStream("/resources/tiles/princesscu.png"));
			BufferedImage princess = ImageIO.read(getClass().getResourceAsStream("/resources/tiles/princess.png"));
			BufferedImage wolf = ImageIO.read(getClass().getResourceAsStream("/resources/tiles/wolf.png"));
			BufferedImage bookFace = ImageIO.read(getClass().getResourceAsStream("/resources/tiles/book.png"));
			BufferedImage tombstone = ImageIO.read(getClass().getResourceAsStream("/resources/tiles/tombstone.png"));
			
			BufferedImage fireball = ImageIO.read(getClass().getResourceAsStream("/resources/tiles/fireball.png"));
			BufferedImage door1 = ImageIO.read(getClass().getResourceAsStream("/resources/tiles/door.png"));
			BufferedImage door1O = ImageIO.read(getClass().getResourceAsStream("/resources/tiles/dooropen.png"));
			
			BufferedImage chest = ImageIO.read(getClass().getResourceAsStream("/resources/tiles/chest2.png"));
			BufferedImage bandana = ImageIO.read(getClass().getResourceAsStream("/resources/tiles/bandana.png"));
			BufferedImage ironhelm = ImageIO.read(getClass().getResourceAsStream("/resources/tiles/ironhelm.png"));
			
			BufferedImage bag = ImageIO.read(getClass().getResourceAsStream("/resources/images/bpack.png"));
			BufferedImage x = ImageIO.read(getClass().getResourceAsStream("/resources/images/x.png"));
			BufferedImage openbook = ImageIO.read(getClass().getResourceAsStream("/resources/images/OpenBook.png"));
			BufferedImage brickMenu = ImageIO.read(getClass().getResourceAsStream("/resources/images/block.png"));
			BufferedImage upArrow = ImageIO.read(getClass().getResourceAsStream("/resources/images/uparrow.png"));
			BufferedImage downArrow = ImageIO.read(getClass().getResourceAsStream("/resources/images/downarrow.png"));
			BufferedImage bg = ImageIO.read(getClass().getResourceAsStream("/resources/images/backgroundgame.png"));
			
			//solid//other
			b.put(1, player);
			b.put(2, wall1a);
			b.put(3, wall1b);
			b.put(4, wall1c);
			//20-29doors
			b.put(20, door1);
			b.put(30, table);
			b.put(31, shelf);
			b.put(100, sBush);
			b.put(101, log);
			//npc
			b.put(1000, elf);
			b.put(1001, man);
			b.put(1002, cowboy);
			b.put(2000, princess);
			//3000+ are hostile 
			b.put(3001, wolf);
			b.put(5000, tombstone);
			b.put(9999, bookFace);
			//close ups should be the npc code followed by a 0
			b.put(10000, elfcu);
			b.put(10010, mancu);
			b.put(10020, cowboycu);
			b.put(20000, princesscu);

			//non solid
			b.put(0, none);
			b.put(-1, green);
			b.put(-2, greenT);
			b.put(-3, greenTR);
			b.put(-4, greenTL);
			b.put(-5, greenB);
			b.put(-6, greenBL);
			b.put(-7, greenBR);
			b.put(-8, greenR);
			b.put(-9, greenL);
			b.put(-20,door1O);
			b.put(-21, stool);
			b.put(-22, stone);
			b.put(-25,chest);
			b.put(-50, blankTile);
			b.put(-51, blankHelm);
			b.put(-52, blankPant);
			b.put(-53, blankBoot);
			b.put(-54, blankNeck);
			b.put(-55, blankShoulder);
			b.put(-56, blankChest);
			b.put(-57, blankarml);
			b.put(-58, blankarmr);
			b.put(-59, blankhandl);
			b.put(-60, blankhandr);
			b.put(-61, blankface);
			b.put(-62, ironhelm);
			b.put(-63, bandana);
			
			
			b.put(-600, fireball);
			b.put(-900, homeTile);
			//images
			b.put(-994,x);
			b.put(-995, downArrow);
			b.put(-996, upArrow);
			b.put(-997,brickMenu);
			b.put(-998,openbook);
			b.put(-999,bag);
			b.put(-1000, bg);
			
		} catch (IOException e) {
			System.out.println("Failed to load images");
		}
		//associate term with each tile
	}

	public void scaleAll(double aspectRatioScale)
	{
	    Map<Integer, BufferedImage> temp = new HashMap<Integer,BufferedImage>();
	    int ratioscale = (int) (32*aspectRatioScale);
	    
		for(Map.Entry<Integer, BufferedImage> entry: b.entrySet())
		{
			temp.put(entry.getKey(), getScaledImage((BufferedImage)entry.getValue(),ratioscale,ratioscale));
		}
		
		b= temp;
	}
	
	//https://stackoverflow.com/questions/16497853/scale-a-bufferedimage-the-fastest-and-easiest-way
	private BufferedImage getScaledImage(BufferedImage src, int w, int h){
	    int finalw = w;
	    int finalh = h;
	    double factor = 1.0d;
	    if(src.getWidth() > src.getHeight()){
	        factor = ((double)src.getHeight()/(double)src.getWidth());
	        finalh = (int)(finalw * factor);                
	    }else{
	        factor = ((double)src.getWidth()/(double)src.getHeight());
	        finalw = (int)(finalh * factor);
	    }   

	    BufferedImage resizedImg = new BufferedImage(finalw, finalh, BufferedImage.TRANSLUCENT);
	    Graphics2D g2 = resizedImg.createGraphics();
	    g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
	    g2.drawImage(src, 0, 0, finalw, finalh, null);
	    g2.dispose();
	    return resizedImg;
	}
	
	public BufferedImage get(int s)
	{
		return b.get(s);
	}
}
