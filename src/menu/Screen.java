package menu;
import java.awt.Dimension;

import javax.swing.*;

import java.awt.CardLayout;
import java.awt.BorderLayout;
import javax.swing.GroupLayout.Alignment;

import people.Creature;
import people.Player;
import tools.Clock;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Font;
import java.awt.ScrollPane;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;
import java.awt.Component;
import javax.swing.border.BevelBorder;
import java.awt.FlowLayout;
import java.io.File;
import javax.swing.border.LineBorder;

import library.FontLibrary;
import library.ImageLibrary;
import library.ItemLibrary;
import library.Jukebox;
import model.GamePanel;
import model.TileNode;
import model.TileThing;


public class Screen extends JFrame implements WindowListener
{
	//panels
	private GamePanel gp ;
	private String gpTheme;
	private JPanel westPanel;
	private JScrollPane scrollMessagePanel;
	private JTextPane healthBar;
	private JTextPane timePane;
	private JTextPane datePane;
	//textoptions
	private JPanel SW_Panel;
	private JLabel lblLaindirTheLost;
	
	//backpack stuff
	private Backpack backpack = new Backpack(this,"Backpack",true);
	private Journal journal;
	private JScrollPane scrollPane = new JScrollPane();
	private DefaultListModel model = new DefaultListModel();
	private JList list;
	
	//Merchant List
	private MerchantScreen ms;
	
	
	//Librarys
	private ImageLibrary lib;
	private ItemLibrary il = new ItemLibrary();
	private FontLibrary fl;
	private Jukebox jb;
	
	//resolutions
	private Dimension res;
	private final Dimension hires = new Dimension(1440,1080);
	private double aspectRatioScale = 1.00;
	//Threads
	private Thread clockThread;
	private Clock clock;
	
	/*
	 * Construct the objects on the Screen
	 * Initiate Variables
	 */
	public Screen(FontLibrary fl, ImageLibrary lib, JFrame parent, Dimension resolution, Jukebox jb)
	{
		//JFRAME SETUP
		super();
		res = resolution;
		getContentPane().setBackground(Color.DARK_GRAY);
		this.setBackground(Color.DARK_GRAY);
		this.setSize(1440,1080);
		//this.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		//this.setUndecorated(true);
		this.setSize(resolution);
		this.lib = lib;
		this.fl = fl;
		this.jb = jb;
		this.setResizable(false);
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		journal = new Journal(this,"Journal",true, lib);	
		//INITIALIZE MAP

		gp = new GamePanel(fl, lib,il, new Dimension(100,100), "Village",resolution);
		gp.setBorder(null);

		//CLOCK SETTINGS//
		clock = new Clock(390,4,20);
		clockThread = new Thread(clock);
		//KEYBOARD SETUP//
		InputMap im = gp.getInputMap(JPanel.WHEN_IN_FOCUSED_WINDOW);
		ActionMap am = gp.getActionMap();
		//movement
		im.put(KeyStroke.getKeyStroke(KeyEvent.VK_D,0), "right");
		im.put(KeyStroke.getKeyStroke(KeyEvent.VK_A,0), "left");
		im.put(KeyStroke.getKeyStroke(KeyEvent.VK_W,0), "up");
		im.put(KeyStroke.getKeyStroke(KeyEvent.VK_S,0), "down");
		im.put(KeyStroke.getKeyStroke(KeyEvent.VK_Z,0), "wait");
		//combat
		im.put(KeyStroke.getKeyStroke(KeyEvent.VK_C,0), "c");
		
		//movement
		am.put("right", new Listener("right"));
		am.put("left", new Listener("left"));
		am.put("up", new Listener("up"));
		am.put("down", new Listener("down"));
		am.put("wait", new Listener("wait"));
		//combat
		am.put("c", new Listener("c"));
		getContentPane().setLayout(new BorderLayout(0, 0));
		JPanel mainPanel = new JPanel();
		mainPanel.setBackground(Color.DARK_GRAY);
		getContentPane().add(mainPanel, BorderLayout.CENTER);
		GridBagLayout gbl_mainPanel = new GridBagLayout();
		aspectRatioScale = res.getWidth()/1440;
		gbl_mainPanel.columnWidths = new int[]{(int) (520*aspectRatioScale), (int) (900*aspectRatioScale), 0};
		gbl_mainPanel.rowHeights = new int[]{(int) (677*aspectRatioScale), (int) (80*aspectRatioScale), (int) (224*aspectRatioScale), 0};
		gbl_mainPanel.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gbl_mainPanel.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};

		mainPanel.setLayout(gbl_mainPanel);
		
		//BACKPACK STUFF
		backpack.addWindowListener(this);
		
		//listener for pickup menu
		MouseListener mouseListener = new MouseAdapter() {
		    public void mouseClicked(MouseEvent e) {
		    	String selectedItem = (String) (((JList) e.getSource()).getSelectedValue());
		    	LinkedList<TileThing> tt = backpack.getGround();
		        for(TileThing t: tt)
		        {
					String itemName = t.getName();
					if(itemName.length()>20)
					{
						itemName = itemName.substring(0,20)+"...";
					}
		        	if(itemName.equals(selectedItem))
		        	{
		        		gp.addNotification("You picked up the "+selectedItem+".", "[Internal Narrator]");
		        		gp.getPlayer().addToInventory(true, t);
		        		//add it to the backpack
		        		backpack.addToBackpack(t);
		        		//remove it from the ground
		        		tt.remove(t);
		        		//update the ground
		        		backpack.setGround(tt);
		        		//remove it from the groundview, repaint
						model.removeAllElements();
						for(TileThing ttt:backpack.getGround())
						{
							itemName = ttt.getName();
							if(itemName.length()>20)
							{
								itemName = itemName.substring(0,20)+"...";
							}
							if(!(ttt instanceof Creature))
								model.addElement(itemName);
						}
						checks();
		        		break;
		        	}
		        }
				list = new JList(model);
				westPanel.revalidate();
				scrollMessagePanel.revalidate();
				repaint();
		        gp.requestFocus();

		    }
		};
		
		//DRAWING WINDOWS//
		westPanel = new JPanel();
		westPanel.setBackground(Color.DARK_GRAY);
		westPanel.setBorder(new LineBorder(Color.LIGHT_GRAY, 2, true));
		scrollMessagePanel = new JScrollPane(westPanel);
		//getContentPane().add(scrollMessagePanel, BorderLayout.CENTER);
		//westPanel.setLayout(new GridLayout(2, 1, 10, 0));
		scrollMessagePanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollMessagePanel.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollMessagePanel.setViewportView(westPanel);
		GridBagConstraints gbc_westPanel = new GridBagConstraints();
		gbc_westPanel.fill = GridBagConstraints.BOTH;
		gbc_westPanel.insets = new Insets(0, 0, 5, 5);
		gbc_westPanel.gridx = 0;
		gbc_westPanel.gridy = 0;
		mainPanel.add(scrollMessagePanel, gbc_westPanel);
		
		
		
		//NOTIFICATION STANDARD
		/*JPanel panel_4 = new JPanel();
		panel_4.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_4.setBackground(Color.DARK_GRAY);
		westPanel.add(panel_4);
		panel_4.setLayout(new BorderLayout(5, 0));
		
		JLabel lbltag = new JLabel("[Internal Narrator]");
		lbltag.setBackground(Color.DARK_GRAY);
		lbltag.setForeground(Color.WHITE);
		panel_4.add(lbltag, BorderLayout.WEST);
		
		JTextPane txtpnWelcomeToDeremor = new JTextPane();
		txtpnWelcomeToDeremor.setBackground(Color.LIGHT_GRAY);
		txtpnWelcomeToDeremor.setEditable(false);
		txtpnWelcomeToDeremor.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtpnWelcomeToDeremor.setText("Welcome to Deremor. You've just arrived in a new village here so do what you can to make a name for yourself.");
		panel_4.add(txtpnWelcomeToDeremor, BorderLayout.CENTER);*/
		
		//DISPLAY
		GridBagConstraints gbc_gp = new GridBagConstraints();
		gbc_gp.fill = GridBagConstraints.BOTH;
		gbc_gp.insets = new Insets(0, 0, 5, 0);
		gbc_gp.gridx = 1;
		gbc_gp.gridy = 0;
		mainPanel.add(gp, gbc_gp);
		gp.setFocusable(true);
		gp.setDoubleBuffered(true);
		gp.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JPanel timeDatePanel = new JPanel();
		timeDatePanel.setBackground(Color.DARK_GRAY);
		FlowLayout fl_timeDatePanel = (FlowLayout) timeDatePanel.getLayout();
		GridBagConstraints gbc_timeDatePanel = new GridBagConstraints();
		gbc_timeDatePanel.insets = new Insets(10, 0, 5, 5);
		gbc_timeDatePanel.fill = GridBagConstraints.BOTH;
		gbc_timeDatePanel.gridx = 0;
		gbc_timeDatePanel.gridy = 1;
		mainPanel.add(timeDatePanel, gbc_timeDatePanel);
		
		JPanel timePanel = new JPanel();
		timePanel.setBorder(new LineBorder(Color.LIGHT_GRAY, 2, true));
		timePanel.setBackground(Color.DARK_GRAY);
		timeDatePanel.add(timePanel);
		
		JLabel lblTime = new JLabel("Time");
		lblTime.setForeground(Color.WHITE);
		timePanel.add(lblTime);
		lblTime.setFont(new Font("Franklin Gothic Medium", Font.BOLD | Font.ITALIC, 12));
		
		timePane = new JTextPane();
		timePane.setBackground(Color.LIGHT_GRAY);
		timePanel.add(timePane);
		timePane.setFont(new Font("Franklin Gothic Medium", Font.BOLD | Font.ITALIC, 12));
		timePane.setEditable(false);
		timePane.setText(clock.getTime());
		
		JPanel datePanel = new JPanel();
		datePanel.setBorder(new LineBorder(Color.LIGHT_GRAY, 2, true));
		datePanel.setBackground(Color.DARK_GRAY);
		timeDatePanel.add(datePanel);
		
		JLabel lblDate = new JLabel("Date");
		lblDate.setForeground(Color.WHITE);
		datePanel.add(lblDate);
		lblDate.setFont(new Font("Franklin Gothic Medium", Font.BOLD | Font.ITALIC, 12));
		lblDate.setHorizontalAlignment(SwingConstants.LEFT);
		
		//DISPLAY TIME/DATE
		datePane = new JTextPane();
		datePane.setBackground(Color.LIGHT_GRAY);
		datePanel.add(datePane);
		datePane.setEditable(false);
		datePane.setFont(new Font("Franklin Gothic Medium", Font.BOLD | Font.ITALIC, 12));
		datePane.setText(clock.getDate());
		
		JPanel centerpanel = new JPanel();
		centerpanel.setBackground(Color.DARK_GRAY);
		FlowLayout fl_centerpanel = (FlowLayout) centerpanel.getLayout();
		GridBagConstraints gbc_centerpanel = new GridBagConstraints();
		gbc_centerpanel.fill = GridBagConstraints.HORIZONTAL;
		gbc_centerpanel.insets = new Insets(0, 0, 5, 0);
		gbc_centerpanel.gridx = 1;
		gbc_centerpanel.gridy = 1;
		mainPanel.add(centerpanel, gbc_centerpanel);
		
		JPanel infoPanel = new JPanel();
		infoPanel.setBackground(Color.LIGHT_GRAY);
		centerpanel.add(infoPanel);
		infoPanel.setLayout(new BorderLayout(5, 0));
		
		lblLaindirTheLost = new JLabel("Laindir the Lost");
		infoPanel.add(lblLaindirTheLost, BorderLayout.NORTH);
		
		
		//ADD BUTTONS
		JButton btnItems = new JButton("Items");
		btnItems.setBackground(Color.LIGHT_GRAY);
		centerpanel.add(btnItems);
		
		JButton btnJrnl = new JButton("Journal");
		btnJrnl.setBackground(Color.LIGHT_GRAY);
		centerpanel.add(btnJrnl);
		
		JButton btnStats = new JButton("Stats");
		btnStats.setBackground(Color.LIGHT_GRAY);
		centerpanel.add(btnStats);
		
		JButton btnSleep = new JButton("Sleep");
		btnSleep.setBackground(Color.LIGHT_GRAY);
		centerpanel.add(btnSleep);
		
		JButton btnNewButton = new JButton("Save");
		btnNewButton.setBackground(Color.LIGHT_GRAY);
		centerpanel.add(btnNewButton);
		
		JButton btnNewButton_2 = new JButton("Exit");
		btnNewButton_2.setBackground(Color.LIGHT_GRAY);
		centerpanel.add(btnNewButton_2);
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		//sleeping commands
		btnSleep.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Random r = new Random();
				int c = r.nextInt(2000)+500;
				int hours = ((15*c)/60)/60;
				int minutes=((15*c)/60)-(60*hours);
				while(c!=0)
				{
					clock.run();
					gp.moveCreatures();
					c--;
				}
				gp.addNotification("You slept for "+hours+" hours and "+minutes+" minutes.", "[Internal Narrator]");
				checks();
				
			}
		});
		btnJrnl.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e)
			{
				journal.update(gp.getPlayer(),gp.getUniverse());
				journal.setVisible(true);
				
				
			}
			
		});
		btnItems.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				backpack.setVisible(true);
				backpack.setModal(true);
				
			}
		});
		
		SW_Panel = new JPanel();
		SW_Panel.setBackground(Color.DARK_GRAY);
		//SW_Panel.setLayout(new BoxLayout(SW_Panel, BoxLayout.Y_AXIS));
		SW_Panel.setLayout(new GridLayout(5,2));
		GridBagConstraints gbc_SW_Panel = new GridBagConstraints();
		gbc_SW_Panel.fill = GridBagConstraints.BOTH;
		gbc_SW_Panel.insets = new Insets(0, 0, 0, 5);
		gbc_SW_Panel.gridx = 0;
		gbc_SW_Panel.gridy = 2;
		mainPanel.add(SW_Panel,gbc_SW_Panel);
		//SW_Panel.setLayout(new GridLayout(10, 1, 0, 0));
		
		JPanel southPanel = new JPanel();
		GridBagConstraints gbc_southPanel = new GridBagConstraints();
		gbc_southPanel.anchor = GridBagConstraints.CENTER;
		gbc_southPanel.fill = GridBagConstraints.BOTH;
		gbc_southPanel.gridx = 1;
		gbc_southPanel.gridy = 2;
		mainPanel.add(southPanel, gbc_southPanel);
		southPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_1 = new JPanel();
		southPanel.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new BorderLayout(5, 50));
		//pickup menu
		list	   = new JList(model);
		list.setBackground(Color.LIGHT_GRAY);
		list.setFont(new Font("Franklin Gothic Medium", Font.BOLD, (int) (25*aspectRatioScale)));

		scrollPane = new JScrollPane(list);
		panel_1.add(scrollPane);
		list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		list.setLayoutOrientation(JList.HORIZONTAL_WRAP);
		list.setVisibleRowCount(10);
		
		JLabel lblNearbyItems = new JLabel("Ground");
		lblNearbyItems.setHorizontalAlignment(SwingConstants.CENTER);
		scrollPane.setColumnHeaderView(lblNearbyItems);
		list.addMouseListener(mouseListener);
	}
	
	public void updatePlayer(int STR, int CON, int INT, int CHR, int DEX, String name, String race, String job)
	{
		lblLaindirTheLost.setText(name);
		gp.getPlayer().setName(name);
		gp.getPlayer().setSTR(STR);
		gp.getPlayer().setCON(CON);
		gp.getPlayer().setINT(INT);
		gp.getPlayer().setDEX(DEX);
		gp.getPlayer().setCHR(CHR);
		gp.getPlayer().setRace(race);
		gp.getPlayer().setJob(job);
		gp.greeting();
		this.setTitle("Deremor");
		checks();
	}
	
	//
	//Checks for night time
	//
	private boolean night= false;//boolean for night method
	public void nightCheck()
	{
		if( (((clock.hours>=8)&&(!clock.am))&&(clock.hours!=12))
				| (((clock.hours<5)&&(clock.am))|((clock.hours==12)&(clock.am))) )
		{
			if(!night)
			{
				gp.setNight(true);
				night = true;
				gp.addNotification("Night has fallen.","[Internal Narrator]");
			}
		}
		else
		{
			if(night)
			{
				night = false;
				gp.setNight(false);
				gp.addNotification("Dawn has broken.","[Internal Narrator]");
			}
		}
	}
	
	/*
	 * Keyboard listener
	 */
	public class Listener extends AbstractAction
	{
		private String btn ="";
		private boolean running;
		
		public Listener(String s)
		{
			requestFocus();
			btn = s;
			running = false;
		}
		
		public void actionPerformed(ActionEvent e) {
			
			gp.getAlignmentX();
			//arrow keys
			if(btn.equalsIgnoreCase("right")||
					btn.equalsIgnoreCase("left")||
					btn.equalsIgnoreCase("up")||
					btn.equalsIgnoreCase("down"))
			{
	
				//move the player, and get the tile they move to
				gp.movePlayer(btn);
			}
			//wait a turn
			if(btn.equalsIgnoreCase("wait"))
			{
			}
			//cast a spell
			if(btn.equalsIgnoreCase("c"))
			{
				gp.castSpell(gp.getPlayer());
			}
			gp.repaint();
			checks();
		}
	}
	
	@SuppressWarnings("deprecation")
	public void checks()
	{
		
		//
		//CHECKS AFTER EACH TURN
		//
		
		//change to new screen
		//System.out.print(gpsPos.height);
		boolean attempt = false;
		//if left side
		if(gp.getPlayer().getPosition().getWidth()==1)
		{
			attempt = gp.moveToNextWorld(4);
		}
		//if bottom side
		if(gp.getPlayer().getPosition().getHeight()==99)
		{
			attempt =gp.moveToNextWorld(3);
		}
		//if right side
		if(gp.getPlayer().getPosition().getWidth()==99)
		{
			attempt =gp.moveToNextWorld(2);
		}
		//if Top side
		if(gp.getPlayer().getPosition().getHeight()==1)
		{
			attempt =gp.moveToNextWorld(1);
		}
		//run clock if we moved. takes 1 hour to
		if(attempt)
		{
			int i = 0;
			while(i<240) {
				clock.run();
				i++;
			}
		}
		//run 15 seconds
		clock.run();
		//DIALOGUE CHECKS
		//get dialogue options if any
		JButton[] textChoice = gp.getTextOptions();
		//clear chat buttons
		SW_Panel.removeAll();
		//
		//For each one of the text option sections (there are ten), see if 
		//there is an available dialogue option to select (len >2), and if it doesn't have a actionlistener yet (len <1), add them.
		//
		for(int i =gp.responses-1;i>-1;i--)
		{
			if((textChoice[i].getText().length())>2)
			{
				final int j = i;
				if(textChoice[i].getActionListeners().length<1)
				{
					
					//goodbye has its own action to do
					if(textChoice[i].getText().equals("Goodbye.")|textChoice[i].getText().equals("Nevermind, goodbye.")|textChoice[i].getText().equals("I don't have time for this."))
					{
						//text choice action, if it is clicked.
						(textChoice[i]).addActionListener(new ActionListener() 
						{
							public void actionPerformed(ActionEvent e) 
							{
								gp.addNotification(textChoice[j].getText(),"[player]");
								gp.removeTextOptions();
								checks();
							}
						}
						);
					}
					else
					{
						(textChoice[i]).addActionListener(new ActionListener() 
						{
							
							public void actionPerformed(ActionEvent e) 
							{
								gp.addNotification(textChoice[j].getText(),"[player]");
								String dialogue = gp.getChatTree().readFromFile(textChoice[j].getText());
								gp.addNotification(dialogue,"["+gp.getChatTree().getCreature().getImageID()+"]");
								//Open the merchant window if the dialogue has to do with trading
								if(dialogue.contains("Take a look at my wares."))
								{
									//i needed to store the players backpack in both in the player object (inherited from creature) and backpack object so be sure to update both.
									//its really not needed, as long as i always reference the backpack object instead of the player. The problem was that the backpack was created
									//before i decided i needed to give creatures an inventory aswell. Because player is a creature it inherited that list
									checks();
									ms = new MerchantScreen(Screen.this, gp.getChatTree().getCreature().getName()+"'s Shop",true,backpack.getBag(),gp.getChatTree().getCreature().getInventory(),
											gp.getPlayer().getGold(),gp.getChatTree().getCreature().getGold());
									gp.addNotification("Thank you.", "["+gp.getChatTree().getCreature().getImageID()+"]");
								}
								if(textChoice[j].getText().contains("Tell me about yourself.")) {
									gp.getChatTree().setOptions(2);
									gp.dialogueUpdate();
								}
								if(textChoice[j].getText().contains("Do you have any work for me?"))
								{
									gp.getChatTree().setOptions(3);
									gp.dialogueUpdate();
								}
								if(textChoice[j].getText().contains("I'll do it."))
								{
									gp.addQuestFromCharacter();
									gp.getChatTree().setOptions(1);
									
								}
								checks();
							}
						}
						);
					}
				}
				SW_Panel.add(textChoice[i]);
			}
		}
		
		ArrayList<JPanel> messages = gp.getNotifications();
		westPanel.removeAll();//
		//westPanel.setLayout(new GridLayout(gp.notifications, 1, 50, (int) (20*aspectRatioScale)));//
		westPanel.setLayout(new BoxLayout(westPanel, BoxLayout.Y_AXIS));
		
		for(int i =gp.notifications-1;i>-1;i--)
		{
			westPanel.add(messages.get(i));//westPanel
		}
		scrollMessagePanel.repaint();
		validate();
		//update player position
		Dimension xy = gp.getPlayer().getPosition();
		TileNode n = gp.getNode(xy);
		
		//item view list
		//update the view for what items the player sees
		backpack.setGround(n.getItems());
		
		model.removeAllElements();
		for(TileThing t:backpack.getGround())
		{
			if(!(t instanceof Creature)){
				String itemName = t.getName();
				if(itemName.length()>20)
				{
					itemName = itemName.substring(0,20)+"...";
				}
				model.addElement(itemName);}
		}
		list = new JList(model);
		
		
		//update quest log
		journal.processQuests(gp.getQuestLog());
		
		//MoveMonsters
		gp.moveCreatures();
		
		//pass time and continue. each action takes 15 seconds
		nightCheck();
		datePane.setText(clock.getDate());
		timePane.setText(clock.getTime());
		
		//set scrollbar to bottom
		JScrollBar vertical = scrollMessagePanel.getVerticalScrollBar();
		vertical.setValue( vertical.getMaximum() );

		
		repaint();	
		westPanel.validate();
		westPanel.revalidate();
		gp.validate();
		validate();
		revalidate();
		//repaint();
	}
	
	public void playVillageSong()
	{
		jb.playVillageSong();
	}
	

	/*
	 * This may need to be modified to only occur when accessing the backpack
	 */
	public void windowClosing(WindowEvent e) 
	{
		if(e.getWindow().getName().equals("backpack")) {
			TileNode n = gp.getNode(new Dimension(gp.getPlayer().getPosition()));
			for(TileThing t: backpack.getDropList())
			{
				gp.getPlayer().addToInventory(false, t);
				n.addItem(t);
			}
			backpack.clearDropList();
			gp.setNode(new Dimension(gp.getPlayer().getPosition()),n);
		}
		validate();
		
	}


	@Override
	public void windowActivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void windowClosed(WindowEvent e) {
		
		if(e.getWindow().getName().equals("merchant"))
		{
			//gp.getPlayer().setInventory(ms.getBag()); the player uses the backpack object
			LinkedList<TileThing> newBag = new LinkedList<TileThing>();
			for(TileThing t: ms.getBag())
			{
				newBag.add(t);
			}
			backpack.setInventory(newBag);
			backpack.repaint();
			backpack.validate();
			gp.getPlayer().setGold(ms.getYourGold());
			gp.getChatTree().getCreature().setInventory(ms.getSellerInv());
			gp.getChatTree().getCreature().setGold(ms.getSellerGold());
		}
		validate();
	}


	@Override
	public void windowDeactivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void windowDeiconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void windowIconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void windowOpened(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	



}
