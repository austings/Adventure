package menu;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.JSplitPane;
import java.awt.CardLayout;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.FlowLayout;
import javax.swing.JList;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import library.ImageLibrary;
import model.MapNode;
import model.Quest;
import people.Player;
import tools.PaintPane;

import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.awt.Color;
import java.awt.Font;
import javax.swing.Icon;
import javax.swing.AbstractListModel;
import javax.swing.BoxLayout;

public class Journal extends JDialog implements ActionListener
{
	private ImageLibrary il;
	private MapPaintPane mapMain;
	
	private JPanel cardPane;
	
	private JButton btnGen;
	private JButton btnLocations;
	private JButton btnQuests ;
	
	private JLabel strVal;
	private JLabel conVal;
	private JLabel intVal;
	private JLabel chaVal;
	private JLabel dexVal;
	private JLabel level;
	private JLabel currentExp;
	private JLabel maxExp;
	private JLabel currentHP;
	private JLabel maxHP;
	private JLabel job;
	private JLabel race;
	private JLabel lblCharName;
	
	private JList activeList;
	private JList completeList;
	
	private JTextPane questTextPane;
	
	
	public Journal(JFrame parentFrame, String frameTitle, boolean tf, ImageLibrary lib) 
	{
		super(parentFrame,frameTitle,tf);
		this.setSize(600,450);
		this.setResizable(false);
		il = lib;
		/*
		this.setUndecorated(true);
		this.setLocationRelativeTo(parentFrame);
		this.setResizable(false);
		this.requestFocus();*/
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel paneButtons = new JPanel();
		getContentPane().add(paneButtons, BorderLayout.NORTH);
		paneButtons.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		btnGen = new JButton("General");
		btnGen.addActionListener(this);
		paneButtons.add(btnGen);
		btnGen.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 20));
		
		btnLocations = new JButton("Map");
		btnLocations.addActionListener(this);
		paneButtons.add(btnLocations);
		btnLocations.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 20));
		
		btnQuests = new JButton("Quests\r\n");
		btnQuests.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 20));
		paneButtons.add(btnQuests);
		
		JButton btnChars = new JButton("Characters");
		paneButtons.add(btnChars);
		btnChars.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 20));
		
		JButton btnBeastiary = new JButton("Beastiary");
		paneButtons.add(btnBeastiary);
		btnBeastiary.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 20));
		
		cardPane = new JPanel();
		getContentPane().add(cardPane, BorderLayout.CENTER);
		cardPane.setLayout(new CardLayout(0, 0));
		
		JPanel genMain = new JPanel();
		cardPane.add(genMain, "gen");
		genMain.setLayout(new BorderLayout(0, 0));
		
		lblCharName = new JLabel("Name");
		lblCharName.setHorizontalAlignment(SwingConstants.CENTER);
		lblCharName.setFont(new Font("Gabriola", Font.PLAIN, 30));
		genMain.add(lblCharName, BorderLayout.NORTH);
		
		JPanel stat_West = new JPanel();
		genMain.add(stat_West, BorderLayout.WEST);
		stat_West.setLayout(new BorderLayout(0, 0));
		
		JPanel appearance = new JPanel();
		stat_West.add(appearance, BorderLayout.NORTH);
		
		JLabel profile = new JLabel((Icon) null);
		appearance.add(profile);
		
		JLabel closeUp = new JLabel((Icon) null);
		appearance.add(closeUp);
		
		JPanel bio = new JPanel();
		stat_West.add(bio, BorderLayout.CENTER);
		bio.setLayout(new BorderLayout(0, 0));
		
		JPanel levelInfor = new JPanel();
		bio.add(levelInfor, BorderLayout.NORTH);
		
		JLabel lblLevel = new JLabel("Level");
		lblLevel.setHorizontalAlignment(SwingConstants.LEFT);
		levelInfor.add(lblLevel);
		
		level = new JLabel((Icon) null);
		levelInfor.add(level);
		
		race = new JLabel((Icon) null);
		levelInfor.add(race);
		
		job = new JLabel((Icon) null);
		levelInfor.add(job);
		
		JPanel biography = new JPanel();
		bio.add(biography, BorderLayout.CENTER);
		biography.setLayout(new BorderLayout(0, 0));
		
		JPanel hpPn = new JPanel();
		biography.add(hpPn, BorderLayout.NORTH);
		
		JLabel hpLabel = new JLabel("HP:");
		hpLabel.setHorizontalAlignment(SwingConstants.CENTER);
		hpPn.add(hpLabel);
		
		currentHP = new JLabel("0");
		hpPn.add(currentHP);
		
		JLabel label = new JLabel("/");
		hpPn.add(label);
		
		maxHP = new JLabel("0");
		hpPn.add(maxHP);
		
		JLabel lblBio = new JLabel("Biography");
		lblBio.setHorizontalAlignment(SwingConstants.CENTER);
		biography.add(lblBio, BorderLayout.CENTER);
		
		JPanel experience = new JPanel();
		bio.add(experience, BorderLayout.SOUTH);
		
		JLabel lblExp = new JLabel("Experience: ");
		experience.add(lblExp);
		
		currentExp = new JLabel((Icon) null);
		experience.add(currentExp);
		
		JLabel lblDash = new JLabel("/");
		experience.add(lblDash);
		
		maxExp = new JLabel((Icon) null);
		experience.add(maxExp);
		
		JPanel stat_Center = new JPanel();
		genMain.add(stat_Center, BorderLayout.CENTER);
		stat_Center.setLayout(new GridLayout(5, 5, 0, 0));
		
		JPanel strPn = new JPanel();
		stat_Center.add(strPn);
		strPn.setLayout(new BorderLayout(0, 0));
		
		JLabel lblStrength = new JLabel("Strength");
		lblStrength.setHorizontalAlignment(SwingConstants.CENTER);
		strPn.add(lblStrength, BorderLayout.NORTH);
		
		JLabel strDown = new JLabel((Icon) null);
		strPn.add(strDown, BorderLayout.WEST);
		
		strVal = new JLabel();
		strVal.setText("5");
		strVal.setHorizontalAlignment(SwingConstants.CENTER);
		strPn.add(strVal, BorderLayout.CENTER);
		
		JLabel strUp = new JLabel((Icon) null);
		strPn.add(strUp, BorderLayout.EAST);
		
		JPanel conPn = new JPanel();
		stat_Center.add(conPn);
		conPn.setLayout(new BorderLayout(0, 0));
		
		JLabel lblCon = new JLabel("Constitution");
		lblCon.setHorizontalAlignment(SwingConstants.CENTER);
		conPn.add(lblCon, BorderLayout.NORTH);
		
		conVal = new JLabel("5");
		conVal.setHorizontalAlignment(SwingConstants.CENTER);
		conPn.add(conVal, BorderLayout.CENTER);
		
		JLabel conDown = new JLabel((Icon) null);
		conPn.add(conDown, BorderLayout.WEST);
		
		JLabel conUp = new JLabel((Icon) null);
		conPn.add(conUp, BorderLayout.EAST);
		
		JPanel intPn = new JPanel();
		stat_Center.add(intPn);
		intPn.setLayout(new BorderLayout(0, 0));
		
		JLabel lblIntel = new JLabel("Intelligence");
		lblIntel.setHorizontalAlignment(SwingConstants.CENTER);
		intPn.add(lblIntel, BorderLayout.NORTH);
		
		JLabel intDown = new JLabel((Icon) null);
		intPn.add(intDown, BorderLayout.WEST);
		
		intVal = new JLabel("5");
		intVal.setHorizontalAlignment(SwingConstants.CENTER);
		intPn.add(intVal, BorderLayout.CENTER);
		
		JLabel intUp = new JLabel((Icon) null);
		intPn.add(intUp, BorderLayout.EAST);
		
		JPanel chaPn = new JPanel();
		stat_Center.add(chaPn);
		chaPn.setLayout(new BorderLayout(0, 0));
		
		JLabel lblCha = new JLabel("Charisma");
		lblCha.setHorizontalAlignment(SwingConstants.CENTER);
		chaPn.add(lblCha, BorderLayout.NORTH);
		
		JLabel chaDown = new JLabel((Icon) null);
		chaPn.add(chaDown, BorderLayout.WEST);
		
		chaVal = new JLabel("5");
		chaVal.setHorizontalAlignment(SwingConstants.CENTER);
		chaPn.add(chaVal, BorderLayout.CENTER);
		
		JLabel chaUp = new JLabel((Icon) null);
		chaPn.add(chaUp, BorderLayout.EAST);
		
		JPanel dexPn = new JPanel();
		stat_Center.add(dexPn);
		dexPn.setLayout(new BorderLayout(0, 0));
		
		JLabel lblDex = new JLabel("Dexterity");
		lblDex.setHorizontalAlignment(SwingConstants.CENTER);
		dexPn.add(lblDex, BorderLayout.NORTH);
		
		JLabel dexDown = new JLabel((Icon) null);
		dexPn.add(dexDown, BorderLayout.WEST);
		
		dexVal = new JLabel("5");
		dexVal.setHorizontalAlignment(SwingConstants.CENTER);
		dexPn.add(dexVal, BorderLayout.CENTER);
		
		JLabel dexUp = new JLabel((Icon) null);
		dexPn.add(dexUp, BorderLayout.EAST);
		
		mapMain = new MapPaintPane();
		cardPane.add(mapMain, "map");
		mapMain.setLayout(new BorderLayout(0, 0));
		
		JPanel questMain = new JPanel();
		cardPane.add(questMain, "quest");
		questMain.setLayout(new BorderLayout(0, 0));
		
		JPanel questList = new JPanel();
		questMain.add(questList, BorderLayout.WEST);
		questList.setLayout(new BoxLayout(questList, BoxLayout.Y_AXIS));
		
		JLabel lblActive = new JLabel("Active");
		lblActive.setFont(new Font("Poor Richard", Font.PLAIN, 20));
		questList.add(lblActive);
		
		activeList = new JList();
		questList.add(activeList);
		activeList.setFont(new Font("Poor Richard", Font.ITALIC, 19));
		activeList.setModel(new AbstractListModel() {
			String[] values = new String[] {};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		
		JLabel lblComplete = new JLabel("Complete");
		lblComplete.setFont(new Font("Poor Richard", Font.PLAIN, 20));
		questList.add(lblComplete);
		
		completeList = new JList();
		completeList.setFont(new Font("Poor Richard", Font.ITALIC, 19));
		questList.add(completeList);
		
		questTextPane = new JTextPane();
		questMain.add(questTextPane, BorderLayout.CENTER);
		
		
		
	}
	
	//should be called once made visible.
	public void update(Player p, ArrayList<ArrayList<MapNode>> universe)
	{
		lblCharName.setText(p.getName());
		chaVal.setText(Integer.toString(p.getCHR()));
		conVal.setText(Integer.toString(p.getCON()));
		dexVal.setText(Integer.toString(p.getDEX()));
		intVal.setText(Integer.toString(p.getINT()));
		strVal.setText(Integer.toString(p.getSTR()));
		level.setText(Integer.toString(p.getLevel()));
		currentHP.setText(Integer.toString(p.getCurrentHP()));
		maxHP.setText(Integer.toString(p.getMaxHP()));
		currentExp.setText(Integer.toString(p.getCurrentXP()));
		maxExp.setText(Integer.toString(p.getXPtoLvl()));
		mapMain.setThemeArray(universe);
		repaint();
		validate();
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(btnGen))
		{
			CardLayout cl = (CardLayout) cardPane.getLayout();
			
			cl.show(cardPane, "gen");
		}
		if(e.getSource().equals(btnLocations))
		{
			CardLayout cl = (CardLayout) cardPane.getLayout();
			
			cl.show(cardPane, "map");
		}
		if(e.getSource().equals(btnQuests))
		{
			CardLayout cl = (CardLayout) cardPane.getLayout();
			cl.show(cardPane, "quest");
		}
		
	}

	public void processQuests(ArrayList<Quest> log)
	{
		activeList.removeAll();
		for(Quest q: log)
		{
			boolean flag = true;
			
			if(q.getStages().get(q.getCurrentStage()).isComplete())
			{
				q.incrementCurrentStage(1);
				if(q.getStages().size()>=q.getCurrentStage()+1)
				{
					q.incrementCurrentStage(-1);
					completeList.add(new JLabel(q.getQuestName()));
					flag = false;
				}
			}
			
			if(flag)
				activeList.add(new JLabel(q.getQuestName()));
			
			completeList.addListSelectionListener(new ListSelectionListener() {

				@Override
				public void valueChanged(ListSelectionEvent e) {
					//questTextPane.setText(q.getStages().get(q.getCurrentStage()).getQuestText());
					
				}
			});
			
			activeList.addListSelectionListener(new ListSelectionListener() {

				@Override
				public void valueChanged(ListSelectionEvent e) {
					//questTextPane.setText(q.getStages().get(q.getCurrentStage()).getQuestText());
					
				}
			});
		}
	}
	
	
	public class MapPaintPane extends JPanel
	{
		private BufferedImage tile;
		private ArrayList<ArrayList<String>> themes = new ArrayList<ArrayList<String>>();
		
		public MapPaintPane() {}
		
		
		public void setThemeArray(ArrayList<ArrayList<MapNode>> universe)
		{
			int y=0;
			themes = new ArrayList<ArrayList<String>>();
			for(ArrayList<MapNode> mna: universe)
			{
				themes.add(new ArrayList<String>());
				for(MapNode mn: mna)
				{
					if(mn != null)
					{
						themes.get(y).add(mn.getTheme());
					}
					else
					{
						themes.get(y).add("None");
					}
				}
				y++;
			}
			
		}
		
		@Override
		protected void paintComponent(Graphics g) {
		    super.paintComponent(g);
		    Graphics2D g2d = (Graphics2D) g.create();
		    int x=0;
		    int prevX = 0;
			int y=0;
			BufferedImage im = il.get(0);
		    for(ArrayList<String> t: themes)
			{
				for(String s: t)
				{
					if(s.equals("Village"))
					{
						im = il.get(-900);
					}
					else
					{
						if(s.equals("Forest"))
						{
							im = il.get(100);
						}
						else
						{
							if(s.equals("None"))
							{
								im = il.get(0);
							}
						}
					}
					g2d.drawImage(im, x*32, y*32, this);//paint this image
					im = il.get(0);
					g2d.drawImage(im, x*32, (y+1)*32, this);//also paint none spaces around the space.
					g2d.drawImage(im, (x+1)*32, y*32, this);
					g2d.drawImage(im, (x+1)*32, (y+1)*32, this);
					x++;
				
				}
				int index = 1;
				//this creates blank spaces to the top of long X rows. 
				while(prevX!=0&prevX+index<=x)
				{
					im = il.get(0);
					g2d.drawImage(im, (prevX+index)*32, (y-1)*32, this);
					index++;
				}
				prevX=x;
				x=0;
				y++;
			}
		    g2d.dispose();

		}
	}


}
