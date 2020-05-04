package tools;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class PaintPane extends JPanel
{
	private BufferedImage tile;
	
	public PaintPane(BufferedImage t) {
	    tile = t;
	}
	
	@Override
	protected void paintComponent(Graphics g) {
	    super.paintComponent(g);
	    Graphics2D g2d = (Graphics2D) g.create();
	    int tileWidth = tile.getWidth();
	    int tileHeight = tile.getHeight();
	    for (int y = 0; y < getHeight(); y += tileHeight) {
	        for (int x = 0; x < getWidth(); x += tileWidth) {
	            g2d.drawImage(tile, x, y, this);
	        }
	    }
	    g2d.dispose();
	}

}
