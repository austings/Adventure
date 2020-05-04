package library;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class FontLibrary 
{
	Font medReg12;
	Font medReg18;
	Font medReg36;
	
	Font medAlt12;
	Font medAlt18;
	Font medAlt36;
	
	public FontLibrary()
	{
		System.out.println("Loading Fonts");
		try {
		    //create the font to use. Specify the size!
		    GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		    medReg12 = Font.createFont(Font.TRUETYPE_FONT,  this.getClass().getResourceAsStream("/resources/fonts/medreg.ttf")).deriveFont(12f);
		    medReg18 = Font.createFont(Font.TRUETYPE_FONT, this.getClass().getResourceAsStream("/resources/fonts/medreg.ttf")).deriveFont(18f);
		    medReg36 = Font.createFont(Font.TRUETYPE_FONT, this.getClass().getResourceAsStream("/resources/fonts/medreg.ttf")).deriveFont(36f);
		    medAlt12 = Font.createFont(Font.TRUETYPE_FONT,  this.getClass().getResourceAsStream("/resources/fonts/medalt.ttf")).deriveFont(12f);
		    medAlt18 = Font.createFont(Font.TRUETYPE_FONT, this.getClass().getResourceAsStream("/resources/fonts/medalt.ttf")).deriveFont(18f);
		    medAlt36 = Font.createFont(Font.TRUETYPE_FONT, this.getClass().getResourceAsStream("/resources/fonts/medalt.ttf")).deriveFont(36f);
		   
		    //register the font
		    ge.registerFont(medReg12);
		    ge.registerFont(medReg18);
		    ge.registerFont(medReg36);
		    
		    ge.registerFont(medAlt12);
		    ge.registerFont(medAlt18);
		    ge.registerFont(medAlt36);
		    
		} catch (Exception e) {
			System.out.println("ERROR");
		    e.printStackTrace();
		} 
	}
	
	public Font getFont(String name)
	{
		if(name.equals("medReg12"))
			return medReg12;
		if(name.equals("medReg18"))
			return medReg18;
		if(name.equals("medReg36"))
			return medReg36;
		if(name.equals("medAlt12"))
			return medAlt12;
		if(name.equals("medAlt18"))
			return medAlt18;
		if(name.equals("medAlt36"))
			return medAlt36;
		
		return medReg12;
	}
}
