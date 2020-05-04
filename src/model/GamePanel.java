package model;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

import people.*;
import tools.Dice;
import tools.NameGenerator;
import tools.Spellbook;

import javax.swing.*;
import javax.swing.border.BevelBorder;

import library.CreatureLibrary;
import library.FontLibrary;
import library.ImageLibrary;
import library.ItemLibrary;
import library.ModuleLibrary;


public class GamePanel extends JPanel
{
	//
	//world information
	//
	private Dimension size;
	private String theme;
	private TileNode[][] world;//the world on screen
	private TileNode[][] worldScreen;//the entire world, misleading i know
	private ArrayList<ArrayList<MapNode>> universe = new ArrayList<ArrayList<MapNode>>();
	//private ArrayList<ArrayList<String>> universeNames = new ArrayList<ArrayList<String>>();
	private ArrayList<Quest> log = new ArrayList<Quest>();
	private Dimension mapScreenUniversePosition = new Dimension(0,0);
	
	//creatures
	private CreatureLibrary cl;
	private ArrayList<Creature> creatures = new ArrayList<Creature>();//all creatures
	
	//player
	private Player p; //current position of the player
	
	//Item Stuff
	private ItemLibrary il;
	private FontLibrary fl;
	
	//
	//UI
	//
	
	//health circle
    private int percentage =50;
	//Image stuff
	private ImageLibrary lib;
	//private BufferedImage previousFloorImage;//floor image previous to when player stepped on tile
	//private int previousFloorImageID;//id of that image
	//resolution
	private Dimension res;
	private double aspectRatioScale = 1.0;
	//time
	private boolean night;
	//responses box
	public int responses = 10;
	private JButton[] respondBs = new JButton[10];
	//message,notification box
	private ArrayList<JPanel> messages = new ArrayList<JPanel>();
	private ChatTree ct = new ChatTree(-1);
	public int notifications=0;
	private NameGenerator ng = new NameGenerator();
	
	
	
	/**
	 * Constructor. Sets up the Gameworld and the way we view it.
	 * @param size- dimensions of the world.
	 */
	
	public GamePanel(FontLibrary fl, ImageLibrary lib,ItemLibrary il, Dimension size, String theme, Dimension resolution)
	{
		this.lib = lib;
		this.il = il;
		this.fl = fl;
		res = resolution;
		aspectRatioScale = res.getWidth()/1440;
		universe.add(new ArrayList<MapNode>());//set infinite right screen
		this.setSize((int)(672*aspectRatioScale),(int)(672*aspectRatioScale));

		this.theme = theme;
		//GRAPHICS SETUP//
		//create our library of tiles to paint the world with
		
		//activate messages
		//JPanel panel = new JPanel();
		//messages[0]=panel;
		//messages[1]=panel;
		//messages[2]=panel;
		//messages[3]=panel;
		//messages[4]=panel;
		//messages[5]=panel;
		//messages[6]=panel;
		//messages[7]=panel;
		//activate buttons
		JButton button = new JButton();
		button.setPreferredSize(new Dimension((int)(900*aspectRatioScale),(int)(10*aspectRatioScale)));
		button.setMinimumSize(button.getPreferredSize());
		button.setMaximumSize(button.getPreferredSize());
		respondBs[0]=button;
		respondBs[1]=button;
		respondBs[2]=button;
		respondBs[3]=button;
		respondBs[4]=button;
		respondBs[5]=button;
		respondBs[6]=button;
		respondBs[7]=button;
		respondBs[8]=button;
		respondBs[9]=button;
		//WORLD SETUP//
		//create the new world

		this.size = size;
		Dimension start = new Dimension(size.width/2,size.height/2);
		p = new Player("Laindir",start);
		String mapname = generateNewWorld(start, theme);
		universe.get(mapScreenUniversePosition.width).add(mapScreenUniversePosition.height,new MapNode(worldScreen,theme,mapname,0,0));
		
		
	}
	
	public void greeting()
	{
		String customWelcome = "";
		if(p.getSTR()>p.getCHR()&p.getSTR()>p.getINT()&p.getSTR()>p.getDEX()&p.getSTR()>p.getCON())
			customWelcome = ("Your great strength has carried you this far, but will it be enough to guide you to glory?");
		if(p.getCHR()>p.getSTR()&p.getCHR()>p.getINT()&p.getCHR()>p.getDEX()&p.getCHR()>p.getCON())
			customWelcome = ("Your way with words has lead you to great opportunity, but many on the outside are known to be unforgiving.");
		if(p.getINT()>p.getSTR()&p.getINT()>p.getCHR()&p.getINT()>p.getDEX()&p.getINT()>p.getCON())
			customWelcome = ("Your vast supply of knowledge has saved you more than once, but many a mind has been set to waste in these wild lands.");
		if(p.getCON()>p.getSTR()&p.getCON()>p.getCHR()&p.getCON()>p.getDEX()&p.getCON()>p.getINT())
			customWelcome = ("You have survived the worst your village could throw at you, but death could be only one mistake away.");
		if(p.getDEX()>p.getSTR()&p.getDEX()>p.getCHR()&p.getDEX()>p.getCON()&p.getDEX()>p.getINT())
			customWelcome = ("You have always been quicker and more agile than the rest, yet many do not take kindly to those who run from a fight.");

		String welcome = "You are a "+p.getRace()+" hailing from the village of "+getMapName()+". "+
				"After several years living as a "+p.getJob()+", you have decided to leave your old life behind "+
				"in order to seek adventure out in the world. ";
		addNotification(welcome,"[Internal Narrator]");
		if(customWelcome.length()>1)
			addNotification(customWelcome,"[Internal Narrator]");
	}
	
	
	
	public String generateNewWorld(Dimension playerStart, String theme)
	{
		int x = size.width;
		int y = size.height;
		this.theme = theme;
		
		worldScreen = new TileNode[x][y];
		//Create a blank world by looping through the size of the map
		//and creating tiles for each grid block
		for(int i = x-1;i>=0;i--)
		{
			for(int j = y-1; j>=0;j--)
			{

				//creates a blank world
				worldScreen[i][j] = new TileNode(-1,i,j);
			}
		}
		//place corner blocks
		for(int i =0;i<100;i++)
		{
			worldScreen[i][1].setImage(-2);
			worldScreen[y-1][i].setImage(-8);
			worldScreen[1][i].setImage(-9);
			worldScreen[i][y-1].setImage(-5);
			
		}
		worldScreen[1][1].setImage(-4);
		worldScreen[y-1][y-1].setImage(-7);
		worldScreen[1][y-1].setImage(-6);
		worldScreen[y-1][1].setImage(-3);
		
		//add player to world
		p.setPosition(playerStart);
		worldScreen[playerStart.width][playerStart.height].addItem(p);
		//paint stuff in the world
		paintModules(theme);
		//addNPCs
		addNPCs(theme);
		worldScreen[playerStart.width+5][playerStart.height+5].addItem(il.getItem("Deremor Vol I"));
		addThings();

		//WORLD VIEW SETUP//
			
		//get screen viewport (player starts at center of map)
		world = new TileNode[21][21];
		
		//this is the x and y coordinates of the top left position
		//the player can see. On a 100x100 grid this is position 40
		x = (playerStart.width)-10;//40 on 100x100
		y = (playerStart.height)-10;//40 on 100x100
		int worldx=0;
		int worldy=0;
		//
		for(int i = x;worldx!=21;i++)
		{
			for(int j = y; worldy!=21;j++)
			{

				//creates a blank world
				TileNode n = worldScreen[i][j];
				world[worldx][worldy] = n;
				worldy++;
			}
			worldy = 0;
			worldx++;
		}
		String mapName = ng.getTownName();
		return mapName;
	}
	
	public void addThings()
	{
		
	}
	
	/*
	 * Method to add Creatures into the world
	 */
	public void addNPCs(String theme)
	{
		CreatureLibrary cl = new CreatureLibrary(il);
		if(theme.equals("Forest"))
		{
			for(int i =0;i<10;i++)
			{
			TileNode spawnNode = getSpawnPoint();
			Dimension spawnLocation = new Dimension(spawnNode.getX(),spawnNode.getY());
			Creature spawn = cl.generateRandomEnemy(spawnLocation);
			creatures.add(spawn);
			worldScreen[(int) spawnLocation.getWidth()][(int) spawnLocation.getHeight()].addItem(spawn);
			}

		}
		else
		{
			for(int i =0;i<10;i++)
			{
				TileNode spawnNode = getSpawnPoint();
				Dimension spawnLocation = new Dimension(spawnNode.getX(),spawnNode.getY());
				Creature spawn = cl.generateRandomNPC(spawnLocation);
				creatures.add(spawn);
				worldScreen[(int) spawnLocation.getWidth()][(int) spawnLocation.getHeight()].addItem(spawn);
			}
		}
	}
	
	/*
	 * Method to paint different modules, (homes, areas, etc).
	 */
	public void paintModules(String type)
	{
		ModuleLibrary ml = new ModuleLibrary(type);
		
		int NumOfFailures = 0;
		while(NumOfFailures<ml.getDensity())
		{
			Dice dice = new Dice(20);
			if(dice.roll()>16)
			{
				ml.setModule(1);
			}
			else
			{
				ml.setModule(0);
			}
			Random r = new Random();
			int resultx = r.nextInt(size.width-1)+1;
			int resulty = r.nextInt(size.height-1)+1;
			
			int xStart = 0;
			int yStart = 0;
			
			Dimension d = ml.getModuleSize();
			
			int modulelength = d.height;
			int modulewidth = d.width;
			boolean fail = false;
			
			if(((resultx+modulewidth)<size.width)&((resulty+modulelength)<size.height))
			{
				while(xStart<modulewidth)
				{
					while(yStart<modulelength)
					{
						if(worldScreen[resultx+xStart][resulty+yStart].isSolid())
						{
							xStart=modulewidth;
							yStart=modulelength;
							fail = true;
						}
						yStart++;
					}
					yStart = 0;
					xStart++;
				}
				xStart=0;
				
				if(!fail)
				{
					worldScreen = ml.returnAddedModule(worldScreen, resultx, resulty);
				}
				else
				{
					NumOfFailures++;
				}
			}
		}
		
	}
	
	/**
	 * Gets each tile's image and repaints it.
	 * https://stackoverflow.com/questions/34883276/java-fill-circle-partially-and-put-percentage-number-inside-it
	 */
	public void paintComponent(Graphics g)
	{
		this.removeAll();
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		//int startLocal = (int) (this.getSize().getWidth()/2)/2;
		//this.updateUI();
		int coordx = 0;
		int coordy = 0;

			
		//go through each tile and draw its image
		for(int x = 0;x!=21;x++)
		{
			for(int y=0;y!=21;y++)
			{

				BufferedImage im = lib.get(world[x][y].getID());
				g2d.drawImage(im, coordx, coordy,null);
				coordy=coordy+(int)(aspectRatioScale*32);
			}
			coordy = 0;
			coordx = coordx+(int)(aspectRatioScale*32);
		}
		//if its night time, draw a transparent dark layer
		if(night)
		{
			int alpha = 127; // 50% transparent
			Color myColour = new Color(0, 0, 50, alpha);
			g2d.setColor(myColour);
			g2d.fillRect(0, 0, (int)(675*aspectRatioScale), (int)(675*aspectRatioScale));
		}
		
		percentage = (int)((getPlayer().getCurrentHP()/getPlayer().getMaxHP())*100);
		g2d.setColor(Color.RED);
		g2d.fillRect((int)(22*(32*aspectRatioScale)), 100-percentage, 100, 100);
		//g2d.setColor(Color.BLACK);
		//g2d.setStroke(new BasicStroke(10));
		//shape = new Ellipse2D.Float((int)(22*(32*aspectRatioScale)), 0, 100, 100);
		g2d.setColor(Color.DARK_GRAY);
		Area a = new Area(new Rectangle((int)(22*(32*aspectRatioScale)), 0, 100, 100));
		a.subtract(new Area(new Ellipse2D.Double((int)(22*(32*aspectRatioScale)), 0, 100, 100)));
		g2d.fill(a);
		g2d.setColor(Color.WHITE);
		g2d.setFont(fl.getFont("medReg18"));
		g2d.drawString(getPlayer().getCurrentHP()+" / "+getPlayer().getMaxHP(),(int)(22*(32*aspectRatioScale))+25 , 50);
		g2d.setFont(fl.getFont("medAlt18"));
		g2d.drawString("Current Location:", (int)(21.5*(32*aspectRatioScale)), (int)(19*(32*aspectRatioScale)));
		g2d.drawString(getMapName(), (int)(22.5*(32*aspectRatioScale)), (int)(20*(32*aspectRatioScale)));
		//gp.getPlayer().getCurrentHP()+" / "+gp.getPlayer().getMaxHP()
		
		this.validate();
	}
	
	
	public ArrayList<JPanel> getNotifications()
	{
		return messages;
	}
	
	public JButton[] getTextOptions()
	{
			return respondBs;
	}
	
	public void removeTextOptions()
	{
		JButton button = new JButton();
		respondBs[0]=button;
		respondBs[1]=button;
		respondBs[2]=button;
		respondBs[3]=button;
		respondBs[4]=button;
		respondBs[5]=button;
		respondBs[6]=button;
		respondBs[7]=button;
		respondBs[8]=button;
		respondBs[9]=button;
		ct = new ChatTree(-1);
	}
	
	public ChatTree getChatTree()
	{
		return ct;
	}
	
	public void closeChatTree()
	{
		for(Creature c: creatures)
		{
			if(c.getCallNum()==ct.getCreature().getCallNum())
			{
				c.setTalking(false);
			}
		}
		ct = new ChatTree(-1);
	}
	
	public void setChatTree(ChatTree ct)
	{
		this.ct = ct;
	}
	
	
	/**
	 * Initiate dialogue with a nonhostile creature
	 */
	public void dialogue(Creature i)
	{
		if(!i.hasAttribute("Hostile"))
		{
			ct = new ChatTree(i);
			addNotification(ct.getResponse(),"["+i.getImageID()+"]");
			ArrayList<String> response = ct.getChoices();
			int k = 0;
			for(String r: response)//for each string in responses create a new button and assign to array 
			{
				respondBs[k] = new JButton(r);
				k++;
			}
				
		}
		else
		{
		}
	}
	
	//update the dialogue buttons when an a dialogue tree changes options to be selected
	public void dialogueUpdate()
	{
		JButton button = new JButton();
		respondBs[0]=button;
		respondBs[1]=button;
		respondBs[2]=button;
		respondBs[3]=button;
		respondBs[4]=button;
		respondBs[5]=button;
		respondBs[6]=button;
		respondBs[7]=button;
		respondBs[8]=button;
		respondBs[9]=button;
		ArrayList<String> response = ct.getChoices();
		int k = 0;
		for(String r: response)//for each string in responses create a new button and assign to array 
		{
			respondBs[k] = new JButton(r);
			k++;
		}
	}
	
	/*
	public void pNotify(String text)
	{
		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension((int)(900*aspectRatioScale),(int)(200*aspectRatioScale)));
		panel.setLayout(new BorderLayout(5, 0));
		
		BufferedImage myPicture = lib.get(p.getImageID());
		JLabel lbltag = new JLabel(new ImageIcon(myPicture));
		panel.add(lbltag, BorderLayout.EAST);
		JTextPane txt = new JTextPane();
		txt.setSize(txt.getPreferredSize());
		txt.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txt.setText(text);
		txt.setEditable(false);
		panel.add(txt, BorderLayout.CENTER);
		messages.add(0, panel);
		notifications++;
		
		
	}*/
	
	//NOTIFICATIONS
	//PROBABLY SHOULD BE IN THE SCREEN CLASS BUT I FUCKED IT UP
	public void addNotification(String message, String tag)
	{
		JPanel panel = new JPanel();
		//panel.setMaximumSize(new Dimension(1000,200));
		panel.setPreferredSize(new Dimension((int)(100*aspectRatioScale),(int)(120*aspectRatioScale)));
		//panel.setMinimumSize(panel.getPreferredSize());
		//panel.setMinimumSize(new Dimension((int)(100*aspectRatioScale),(int)(100*aspectRatioScale)));
		
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setBackground(Color.DARK_GRAY);
		

		panel.setLayout(new BorderLayout(5, 0));
		
		//add pictures
		if(tag.equals("[1000]"))//noble
		{
			BufferedImage myPicture = lib.get(10000);
			JLabel lbltag = new JLabel(new ImageIcon(myPicture));
			panel.add(lbltag, BorderLayout.WEST);
		}else{
		if(tag.equals("[2000]"))//princess
		{
			BufferedImage myPicture = lib.get(20000);
			JLabel lbltag = new JLabel(new ImageIcon(myPicture));
			panel.add(lbltag, BorderLayout.WEST);
		}else{
		if(tag.equals("[1002]"))//cowboy
		{
			BufferedImage myPicture = lib.get(10020);
			JLabel lbltag = new JLabel(new ImageIcon(myPicture));
			panel.add(lbltag, BorderLayout.WEST);
		}else{
		if(tag.equals("[player]"))//player
		{
			BufferedImage myPicture = lib.get(1);
			JLabel lbltag = new JLabel(new ImageIcon(myPicture));
			panel.add(lbltag, BorderLayout.WEST);
		}else {
		if(tag.equals("[Internal Narrator]"))//player
		{
			BufferedImage myPicture = lib.get(9999);
			JLabel lbltag = new JLabel(new ImageIcon(myPicture));
			panel.add(lbltag, BorderLayout.WEST);
		}
		else
		{//will just use the object icon
			BufferedImage myPicture = lib.get(Integer.parseInt(tag.substring(1, tag.length()-1)));
			JLabel lbltag = new JLabel(new ImageIcon(myPicture));
			panel.add(lbltag, BorderLayout.WEST);
		}}}}}
		
		JTextPane txt = new JTextPane();

		//if(res.equals(hires))
		//	txt.setFont(new Font("Tahoma", Font.PLAIN, (int)(20*aspectRatioScale)));
		txt.setAlignmentY(CENTER_ALIGNMENT);
		txt.setBackground(Color.LIGHT_GRAY);
		txt.setText(message);
		txt.setEditable(false);
		panel.add(txt, BorderLayout.CENTER);
		panel.updateUI();
		messages.add(0,panel);
		notifications++;
		if(notifications>50){
			notifications--;}
	}
	
	/*
	 * Change the night on or off
	 */
	public void setNight(boolean t)
	{
		night = t;
	}
	
	/*
	 * INCOMPLETE 
	 * cast a spell 
	 */
	public void castSpell(Creature c)
	{
		//int x= c.getPosition().width;
		//int y = c.getPosition().height;
		//String lld = c.getLastLookedDirection();
		//Spellbook sb = c.getSpellbook();
		//sb.castSpell("Fireball", worldScreen, x, y, lld);
		
	}
	
	/*
	 * Method called when user presses one of the arrow keys, or
	 * a send it a creature and it will move the creature instead.
	 * 
	 * This method should move the player tile on the worldScreen, then 
	 * adjust the world on the screen 
	 * @parameter String string- the direction of the arrow key pressed
	 * 
	 */
	public int movePlayer(String string) 
	{
		//get players int coords
		int x = p.getPosition().width;
		int y = p.getPosition().height;
		
		
		//
		//
		//Basically each of these codes strings are the same, just for different directions. The comments here apply to 
		//the rest of the IF statement just modified for the respective direction
		//
		//
		
		//dont move if at end of map, or if in the middle of a conversation
		if(!(x==worldScreen.length-1)&ct.getResponse().equals("NOT A RESPONSE"))
		{
			//if this is the direction we want
			if(string =="right")
			{
				//and if we can move onto that tile
				if(!(worldScreen[x+1][y].isSolid())&&(worldScreen[x+1][y].getID()<1))//if its solid OR someone is standing there already
				{
					//move onto it
					String uniqueid = p.getCallNum();
					worldScreen[x][y].removeItem(uniqueid);
					worldScreen[x+1][y].addItem(p);
					p.moveRight();
					//if we aren't near the end of the map, scroll
					if(x<worldScreen.length-11&!(x<11))
						scrollRight();
					return 1;
				}
				//poke the attached tile. Poke is a method used to open doors but also attack hostile creatures. It is called whenever the player tries to
				//move into another tile.
				Creature c = worldScreen[x+1][y].getCreature();
				int attacked = worldScreen[x+1][y].poke(p);//poke returns an integer code to determine what the player was poking
				
				if(attacked>999999) {
					if(Integer.toString(attacked).startsWith("994400"))//hit code. It means it was a successful attack
					{
						attacked = Integer.parseInt(Integer.toString(attacked).substring(6));
						addNotification("You hit "+c.getName()+" for "+attacked+" damage.","[Internal Narrator]");
					}
					if(Integer.toString(attacked).startsWith("984400"))//death code. means it was a successful attack and the creature died.
					{
						attacked = Integer.parseInt(Integer.toString(attacked).substring(6));
						addNotification("You hit "+c.getName()+" for "+attacked+" damage. "+c.getName()+" has died!","[Internal Narrator]");
						creatures.remove(c);
					}
				}

				if(attacked==-994400)//miss code
				{	
					addNotification("You missed "+c.getName()+".","[Internal Narrator]");
				}
				//if trying to touch a creature that is not hostile, initiate dialogue
				if(c !=null)
				{
					if(!c.hasAttribute("hostile"))
						dialogue(c);
				}
			}
		}
		if(!(x<=1)&ct.getResponse().equals("NOT A RESPONSE"))
		{
			if(string =="left")
			{
				if(!(worldScreen[x-1][y].isSolid())&&(worldScreen[x-1][y].getID()<1))
				{
					String uniqueid = p.getCallNum();
					worldScreen[x][y].removeItem(uniqueid);
					worldScreen[x-1][y].addItem(p);
					p.moveLeft();
					if(x>11&!(x>worldScreen.length-11))
						scrollLeft();
					return 1;
				}
				Creature c = worldScreen[x-1][y].getCreature();
				int attacked = worldScreen[x-1][y].poke(p);
				
				if(attacked>999999) {
					if(Integer.toString(attacked).startsWith("994400"))//hit
					{
						attacked = Integer.parseInt(Integer.toString(attacked).substring(6));
						addNotification("You hit "+c.getName()+" for "+attacked+" damage.","[Internal Narrator]");
					}
					if(Integer.toString(attacked).startsWith("984400"))
					{
						attacked = Integer.parseInt(Integer.toString(attacked).substring(6));
						addNotification("You hit "+c.getName()+" for "+attacked+" damage. "+c.getName()+" d!","[Internal Narrator]");
						creatures.remove(c);
					}
				}
				if(attacked==-994400)//miss
				{	
					addNotification("You missed "+c.getName()+".","[Internal Narrator]");
				}
				if(c !=null)
				{
					if(!c.hasAttribute("hostile"))
						dialogue(c);
				}
			}
		}
		if(!(y<=1)&ct.getResponse().equals("NOT A RESPONSE"))
		{
			if(string =="up")
			{
				if(!(worldScreen[x][y-1].isSolid())&(worldScreen[x][y-1].getID()<1))
				{
					String uniqueid = p.getCallNum();
					worldScreen[x][y].removeItem(uniqueid);
					worldScreen[x][y-1].addItem(p);
					p.moveUp();
					if(y>11&!(y>worldScreen[1].length-11))
						scrollUp();
					return 1;
				}
				Creature c = worldScreen[x][y-1].getCreature();
				int attacked = worldScreen[x][y-1].poke(p);
				
				if(attacked>999999) {
					if(Integer.toString(attacked).startsWith("994400"))//hit
					{
						attacked = Integer.parseInt(Integer.toString(attacked).substring(6));
						addNotification("You hit "+c.getName()+" for "+attacked+" damage.","[Internal Narrator]");
					}
					if(Integer.toString(attacked).startsWith("984400"))
					{
						attacked = Integer.parseInt(Integer.toString(attacked).substring(6));
						addNotification("You hit "+c.getName()+" for "+attacked+" damage. "+c.getName()+" died!","[Internal Narrator]");
						creatures.remove(c);
					}
				}
				
				if(attacked==-994400)//miss
				{	
					addNotification("You missed "+c.getName()+".","[Internal Narrator]");
				}
				if(c !=null)
				{
					if(!c.hasAttribute("hostile"))
						dialogue(c);
				}
			}
		}
		if(!(y==worldScreen[1].length-1)&ct.getResponse().equals("NOT A RESPONSE"))
		{
			if(string =="down")
			{
				if(!(worldScreen[x][y+1].isSolid())&(worldScreen[x][y+1].getID()<1))
				{
					String uniqueid = p.getCallNum();
					worldScreen[x][y].removeItem(uniqueid);
					worldScreen[x][y+1].addItem(p);
					p.moveDown();
					if(y<worldScreen[1].length-11&!(y<11))
						scrollDown();
					return 1;
				}
				Creature c = worldScreen[x][y+1].getCreature();
				int attacked = worldScreen[x][y+1].poke(p);
				
				if(attacked>999999) {
					if(Integer.toString(attacked).startsWith("994400"))//hit
					{
						attacked = Integer.parseInt(Integer.toString(attacked).substring(6));
						addNotification("You hit "+c.getName()+" for "+attacked+" damage.","[Internal Narrator]");
					}
					if(Integer.toString(attacked).startsWith ("984400"))
					{
						attacked = Integer.parseInt(Integer.toString(attacked).substring(6));
						addNotification("You hit "+c.getName()+" for "+attacked+" damage. "+c.getName()+" died!","[Internal Narrator]");
						creatures.remove(c);
					}
				}
				
				if(attacked==-994400)//miss
				{	
					addNotification("You missed "+c.getName()+".","[Internal Narrator]");
				}
				
				if(c !=null)
				{
					if(!c.hasAttribute("hostile"))
						dialogue(c);
				}
			}
		}
		return 0;
	}

	
	/*
	 * Method to have each creature run their turn 
	 */
	public void moveCreatures()
	{
		//creatures = returnCreaturesByDexterity();
		for(Creature c: creatures)
		{
			c.setWorldView(worldScreen);
			int x = c.getPosition().width;
			int y = c.getPosition().height;
			boolean swb = c.swapSlowWalkBool();
			if(swb==false&c.getTalking()==false)
			{
				c.run();
				String s = c.getLastLookedDirection();
				if(s.equals("down"))
				{
					if(!(worldScreen[x][y+1].getID()>0))//if not solidobject
					{
						String uniqueid = c.getCallNum();
						worldScreen[x][y].removeItem(uniqueid);
						worldScreen[x][y+1].addItem(c);
					}
				}
				if(s.equals("right"))
				{
					if(!(worldScreen[x+1][y].getID()>0))//if not solidobject
					{
						String uniqueid = c.getCallNum();
						worldScreen[x][y].removeItem(uniqueid);
						worldScreen[x+1][y].addItem(c);
					}
				}
				if(s.equals("left"))
				{
					if(!(worldScreen[x-1][y].getID()>0))//if not solidobject
					{
						String uniqueid = c.getCallNum();
						worldScreen[x][y].removeItem(uniqueid);
						worldScreen[x-1][y].addItem(c);
					}
				}
				if(s.equals("up"))
				{
					if(!(worldScreen[x][y-1].getID()>0))//if not solidobject
					{
						String uniqueid = c.getCallNum();
						worldScreen[x][y].removeItem(uniqueid);
						worldScreen[x][y-1].addItem(c);
					}
				}
			}
		}
	}

	/*
	 * Returns a free tile for the creaturelibrary to generate a creature
	 */
	public TileNode getSpawnPoint()
	{
		int x;
		int y;
		do {
			Random r = new Random();
			x = r.nextInt(worldScreen.length)+10;
			y = r.nextInt(worldScreen[1].length)+10;
			if(x>=worldScreen.length)
				x = worldScreen.length-10;
			if(y>=worldScreen[1].length-1)
				y = worldScreen[x].length-10;
		}while(worldScreen[x][y].isSolid());
		TileNode returnNode = worldScreen[x][y];
		return returnNode;
	}
	
	
	/*
	 * Return each creature by dexterity
	 */
	public Creature[] returnCreaturesByDexterity()
	{
		ArrayList<Creature> list = new ArrayList<Creature>();
		
		//
		//retrieve each creature in the world
		//
		
		list = creatures;
		
		//
		//sort by highest dex next
		//
		//not efficent but easy to implement.
		//
		Creature[] rList = new Creature[list.size()];
		
		int iterator = 0;
		int indexOfHighest = 0;
		int count = 0;
		while(list.size()!=0)
		{
			for(Creature buddy : list)
			{
				if(list.get(indexOfHighest).getDEX()<buddy.getDEX())
				{
					indexOfHighest = count;
				}
				count++;
			}
			rList[iterator]=list.get(indexOfHighest);
			list.remove(indexOfHighest);
			iterator++;
		}
		return rList;
		
		
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
			//last column needs to be pulled from worldScreen
			int wvx = world[x][y].getX();
			int wvy = world[x][y].getY();
			
			world[x][y]=worldScreen[wvx+1][wvy];
			x=0;
			y++;
		}
		//redraw

	}
	
	public TileNode getNode(Dimension xy)
	{
		return worldScreen[xy.width][xy.height];
	}
	
	public void setNode(Dimension xy, TileNode t)
	{
		worldScreen[xy.width][xy.height]= t;
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
			
			world[x][y]=worldScreen[wvx-1][wvy];
			x=20;
			y++;
		}
		
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
			
			world[x][y]=worldScreen[wvx][wvy-1];
			y=20;
			x++;
		}
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
			
			world[x][y]=worldScreen[wvx][wvy+1];
			y=0;
			x++;
		}
	}

	public Player getPlayer()
	{
		return p;
	}
	
	/*
	 * 1- up
	 * 2-right
	 * 3-down
	 * 4-left
	 * 
	 * height and width may be backwards
	 */
	public boolean moveToNextWorld(int direction)
	{
		boolean success = false;
		universe.get(mapScreenUniversePosition.width).get(mapScreenUniversePosition.height).setWorldScreen(worldScreen);
		switch(direction)
		{
			case 1:
				//if discovered and not top of the map
				if(!(mapScreenUniversePosition.width==0))
				{
					if(universe.get(mapScreenUniversePosition.width-1).size()<=mapScreenUniversePosition.height)
					{
						while(universe.get(mapScreenUniversePosition.width-1).size()<mapScreenUniversePosition.height)
						{
							universe.get(mapScreenUniversePosition.width-1).add(null);
						}
						mapScreenUniversePosition.width=mapScreenUniversePosition.width-1;
						worldScreen[p.getPosition().width][p.getPosition().height].removeItem(p.getCallNum());
						createNextWorld(1);
						success = true;
					}
					else
					{
						if(universe.get(mapScreenUniversePosition.width-1).get(mapScreenUniversePosition.height) instanceof MapNode)
						{
							mapScreenUniversePosition.width=mapScreenUniversePosition.width-1;
							worldScreen[p.getPosition().width][p.getPosition().height].removeItem(p.getCallNum());
							grabNextWorld(1);
							success = true;
						}
						else
						{
							mapScreenUniversePosition.width=mapScreenUniversePosition.width-1;
							worldScreen[p.getPosition().width][p.getPosition().height].removeItem(p.getCallNum());
							createNextWorld(1);
							success = true;
						}
					}
				}

				break;
			case 2:
				//if its not discovered
				
				if(mapScreenUniversePosition.height+1==universe.get(mapScreenUniversePosition.width).size())
				{
					mapScreenUniversePosition.height=mapScreenUniversePosition.height+1;
					creatures = new ArrayList<Creature>();
					worldScreen[p.getPosition().width][p.getPosition().height].removeItem(p.getCallNum());
					createNextWorld(2);
					success = true;
				}
				else
				{
					//if it was instantiated or not
					if(universe.get(mapScreenUniversePosition.width).get(mapScreenUniversePosition.height+1) instanceof MapNode)
					{
						mapScreenUniversePosition.height = mapScreenUniversePosition.height+1;
						creatures = new ArrayList<Creature>();
						worldScreen[p.getPosition().width][p.getPosition().height].removeItem(p.getCallNum());
						grabNextWorld(2);
						success = true;
					}
					else
					{
						mapScreenUniversePosition.height=mapScreenUniversePosition.height+1;
						creatures = new ArrayList<Creature>();
						worldScreen[p.getPosition().width][p.getPosition().height].removeItem(p.getCallNum());
						createNextWorld(2);
						success = true;
					}
				}
				break;
			case 3:
				// the arraylist is created
				if(!(mapScreenUniversePosition.width+1==universe.size()))
				{
					//while the worlds from the size are not initiated
					while(universe.get(mapScreenUniversePosition.width+1).size()<=mapScreenUniversePosition.height)
					{
						universe.get(mapScreenUniversePosition.width+1).add(null);
					}
					//the world is not discovered
					if(!(universe.get(mapScreenUniversePosition.width+1).get(mapScreenUniversePosition.height) instanceof MapNode))
					{
						mapScreenUniversePosition.width=mapScreenUniversePosition.width+1;
						creatures = new ArrayList<Creature>();
						worldScreen[p.getPosition().width][p.getPosition().height].removeItem(p.getCallNum());
						createNextWorld(3);
						success = true;
					}
					else//is discovered
					{
						mapScreenUniversePosition.width=mapScreenUniversePosition.width+1;
						creatures = new ArrayList<Creature>();
						worldScreen[p.getPosition().width][p.getPosition().height].removeItem(p.getCallNum());
						grabNextWorld(3);
						success = true;
					}
				}
				else//the arraylist is not created
				{
					universe.add(new ArrayList<MapNode>());
					//while the worlds from the size are not initiated
					while(universe.get(mapScreenUniversePosition.width+1).size()<mapScreenUniversePosition.height)
					{
						universe.get(mapScreenUniversePosition.width+1).add(null);
					}
					mapScreenUniversePosition.width=mapScreenUniversePosition.width+1;
					creatures = new ArrayList<Creature>();
					worldScreen[p.getPosition().width][p.getPosition().height].removeItem(p.getCallNum());
					createNextWorld(3);
					success = true;
				}

				break;
			case 4:
				//if its not the end of the map and the world is discovered
				if(!(mapScreenUniversePosition.height==0))
				{
					if(universe.get(mapScreenUniversePosition.width).get(mapScreenUniversePosition.height-1) instanceof MapNode)
					{
						mapScreenUniversePosition.height=mapScreenUniversePosition.height-1;
						creatures = new ArrayList<Creature>();
						worldScreen[p.getPosition().width][p.getPosition().height].removeItem(p.getCallNum());
						grabNextWorld(4);
						success = true;
					}
					else
					{
						mapScreenUniversePosition.height=mapScreenUniversePosition.height-1;
						creatures = new ArrayList<Creature>();
						worldScreen[p.getPosition().width][p.getPosition().height].removeItem(p.getCallNum());
						universe.get(mapScreenUniversePosition.width).remove(mapScreenUniversePosition.height);//
						createNextWorld(4);
						success = true;
					}
				}

				break;
		}
		return success;
	}
	
	public void grabNextWorld(int direction)
	{
		worldScreen = universe.get(mapScreenUniversePosition.width).get(mapScreenUniversePosition.height).getWorldScreen();
		switch(direction)
		{
		case 1:
			p.setPosition(new Dimension(p.getPosition().width,89));
			worldScreen[p.getPosition().width][p.getPosition().height].addItem(p);
			addNotification("You traveled north.","[Internal Narrator]");
			break;
		case 2:
			p.setPosition(new Dimension(11,p.getPosition().height));
			worldScreen[p.getPosition().width][p.getPosition().height].addItem(p);
			addNotification("You traveled east.","[Internal Narrator]");
			break;
		case 3:
			p.setPosition(new Dimension(p.getPosition().width,11));
			worldScreen[p.getPosition().width][p.getPosition().height].addItem(p);
			addNotification("You traveled south.","[Internal Narrator]");
			break;
		case 4:
			p.setPosition(new Dimension(89,p.getPosition().height));
			worldScreen[p.getPosition().width][p.getPosition().height].addItem(p);
			addNotification("You traveled west.","[Internal Narrator]");
			break;
		
		}
		
		//this is the x and y coordinates of the top left position
		//the player can see. On a 100x100 grid this is position 40
		int x = (p.getPosition().width)-10;//40 on 100x100
		int y = (p.getPosition().height)-10;//40 on 100x100
		int worldx=0;
		int worldy=0;
		//
		for(int i = x;worldx!=21;i++)
		{
			for(int j = y; worldy!=21;j++)
			{

				//creates a blank world
				TileNode n = worldScreen[i][j];
				world[worldx][worldy] = n;
				worldy++;
			}
			worldy = 0;
			worldx++;
		}

	}

	
	public void createNextWorld(int direction)
	{
		int x = size.width;
		int y = size.height;
		
		worldScreen = new TileNode[x][y];
		//Create a blank world by looping through the size of the map
		//and creating tiles for each grid block
		for(int i = x-1;i>=0;i--)
		{
			for(int j = y-1; j>=0;j--)
			{

				//creates a blank world
				worldScreen[i][j] = new TileNode(-1,i,j);
			}
		}
		//place corner blocks
		for(int i =0;i<100;i++)
		{
			worldScreen[i][1].setImage(-2);
			worldScreen[y-1][i].setImage(-8);
			worldScreen[1][i].setImage(-9);
			worldScreen[i][y-1].setImage(-5);
			
		}
		worldScreen[1][1].setImage(-4);
		worldScreen[y-1][y-1].setImage(-7);
		worldScreen[1][y-1].setImage(-6);
		worldScreen[y-1][1].setImage(-3);
		
		//add player to world
		switch(direction)
		{
			case 1:
				p.setPosition(new Dimension(p.getPosition().width,89));
				worldScreen[p.getPosition().width][p.getPosition().height].addItem(p);
				addNotification("You traveled north.","[Internal Narrator]");
				break;
			case 2:
				p.setPosition(new Dimension(11,p.getPosition().height));
				worldScreen[p.getPosition().width][p.getPosition().height].addItem(p);
				addNotification("You traveled east.","[Internal Narrator]");
				break;
			case 3:
				p.setPosition(new Dimension(p.getPosition().width,11));
				worldScreen[p.getPosition().width][p.getPosition().height].addItem(p);
				addNotification("You traveled south.","[Internal Narrator]");
				break;
			case 4:
				p.setPosition(new Dimension(89,p.getPosition().height));
				worldScreen[p.getPosition().width][p.getPosition().height].addItem(p);
				addNotification("You traveled west.","[Internal Narrator]");
				break;
		}
		
		//WORLD VIEW SETUP//
			
		//get screen viewport (player starts at center of map)
		world = new TileNode[21][21];
		
		//this is the x and y coordinates of the top left position
		//the player can see. On a 100x100 grid this is position 40
		x = (p.getPosition().width)-10;//40 on 100x100
		y = (p.getPosition().height)-10;//40 on 100x100
		int worldx=0;
		int worldy=0;
		//
		for(int i = x;worldx!=21;i++)
		{
			for(int j = y; worldy!=21;j++)
			{

				//creates a blank world
				TileNode n = worldScreen[i][j];
				world[worldx][worldy] = n;
				worldy++;
			}
			worldy = 0;
			worldx++;
		}
		theme = "Forest";
		String mapName = ng.getForestName();
		universe.get(mapScreenUniversePosition.width).add(mapScreenUniversePosition.height,new MapNode(worldScreen,theme,mapName,mapScreenUniversePosition.width,mapScreenUniversePosition.height));
		
		addModulesToNewWorld("Forest");
		repaint();
	}
	
	public void addModulesToNewWorld(String theme)
	{
		//paint stuff in the world
				paintModules(theme);
				//addNPCs
				addNPCs(theme);
				addThings();

	}
	
	public String getMapName()
	{
		return universe.get(mapScreenUniversePosition.width).get(mapScreenUniversePosition.height).getName();
	}
	
	public ArrayList<ArrayList<MapNode>> getUniverse()
	{
		return universe;
	}
	
	public void addQuestFromCharacter()
	{
		log.add(ct.getQuest());
	}
	
	public ArrayList<Quest> getQuestLog()
	{
		return log;
	}

}
