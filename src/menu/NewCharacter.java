package menu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import library.FontLibrary;
import library.ImageLibrary;
import library.Jukebox;

import javax.swing.JLabel;
import java.awt.CardLayout;
import javax.swing.SwingConstants;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Font;

import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.AbstractListModel;

import tools.ListColorer;
import java.awt.ScrollPane;
import javax.swing.JTextArea;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JRadioButton;

public class NewCharacter extends JFrame implements ActionListener
{
	private JTextField nameField;
	
	private JPanel Center;
	
	private JButton btnNextPage;
	private JButton btnFinalize;
	private JButton btnLastPage_2;
	
	private FontLibrary fl;
	private ImageLibrary il;
	private Jukebox jb;
	private Screen s;
	
	private JLabel points ;
	private JLabel sLabel;
	private JLabel cLabel;
	private JLabel chLabel;
	private JLabel iLabel;
	private JLabel dLabel;
	
	private JComboBox raceBox;
	private JComboBox jobBox;
	
	private final JPanel strengthPanel = new JPanel();
	
	private Dimension res;

	
	public NewCharacter(FontLibrary fl, ImageLibrary il, JFrame parent,Dimension resolution, Jukebox jb)
	{
		super();
		s = new Screen(fl,il, this,resolution,jb);
		this.setTitle("New Character");
		res = resolution;
		this.fl = fl;
		this.il = il;
		this.jb =jb;
		this.setBackground(Color.DARK_GRAY);
		//this.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		//this.setUndecorated(true);
		this.setSize(res);
		this.setLocationRelativeTo(parent);
		this.setResizable(false);
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		JPanel Master = new JPanel();
		Master.setBackground(Color.DARK_GRAY);
		getContentPane().add(Master, BorderLayout.CENTER);
		Master.setLayout(new BorderLayout(0, 0));
		
		Center = new JPanel();
		Center.setBackground(Color.WHITE);
		Master.add(Center, BorderLayout.CENTER);
		Center.setLayout(new CardLayout(0, 0));
		
		JPanel raceAndClass = new JPanel();
		raceAndClass.setBackground(Color.DARK_GRAY);
		Center.add(raceAndClass, "name_1598053474146027");
		GridBagLayout gridBagLayout_1 = new GridBagLayout();
		gridBagLayout_1.columnWidths = new int[]{25, 312, 71, 301, 25, 0};
		gridBagLayout_1.rowHeights = new int[]{0, 0, 20, 0, 120, 20, 25, 120, 115, 0, 0};
		gridBagLayout_1.columnWeights = new double[]{0.0, 1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout_1.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		raceAndClass.setLayout(gridBagLayout_1);
		
		JLabel lblRace_1 = new JLabel("Race");
		lblRace_1.setForeground(Color.WHITE);
		lblRace_1.setFont(fl.getFont("medReg18"));
		GridBagConstraints gbc_lblRace_1 = new GridBagConstraints();
		gbc_lblRace_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblRace_1.gridx = 1;
		gbc_lblRace_1.gridy = 2;
		raceAndClass.add(lblRace_1, gbc_lblRace_1);
		
		JLabel lblDescription = new JLabel("Description");
		lblDescription.setFont(fl.getFont("medReg18"));
		lblDescription.setForeground(Color.WHITE);
		GridBagConstraints gbc_lblDescription = new GridBagConstraints();
		gbc_lblDescription.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblDescription.insets = new Insets(0, 0, 5, 5);
		gbc_lblDescription.gridx = 3;
		gbc_lblDescription.gridy = 2;
		raceAndClass.add(lblDescription, gbc_lblDescription);
		
		raceBox = new JComboBox();
		raceBox.setFont(fl.getFont("medReg18"));
		raceBox.setModel(new DefaultComboBoxModel(new String[] {"Human", "Dwarf", "Dryad", "Goblin", "Reptillian"}));
		GridBagConstraints gbc_raceBox = new GridBagConstraints();
		gbc_raceBox.insets = new Insets(0, 0, 5, 5);
		gbc_raceBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_raceBox.gridx = 1;
		gbc_raceBox.gridy = 3;
		raceAndClass.add(raceBox, gbc_raceBox);
		
		JPanel racePanel = new JPanel();
		racePanel.setBackground(Color.LIGHT_GRAY);
		GridBagConstraints gbc_racePanel = new GridBagConstraints();
		gbc_racePanel.insets = new Insets(0, 0, 5, 5);
		gbc_racePanel.fill = GridBagConstraints.BOTH;
		gbc_racePanel.gridx = 1;
		gbc_racePanel.gridy = 4;
		raceAndClass.add(racePanel, gbc_racePanel);
		
		JTextArea raceDescription = new JTextArea();
		raceDescription.setLineWrap(true);
		raceDescription.setWrapStyleWord(true);
		raceDescription.setEditable(false);
		raceDescription.setText("Humans are the most industrious and ambitious of the races. Due to their similar philosophies, humans and dwarves tend to get along together"
				+ ". The humans are not well recieved by dryads due to their general lack of compassion toward wildlife. There is great animosity between humans and reptillians.");
		raceDescription.setBackground(Color.LIGHT_GRAY);
		GridBagConstraints gbc_raceDescription = new GridBagConstraints();
		gbc_raceDescription.insets = new Insets(0, 0, 5, 5);
		gbc_raceDescription.fill = GridBagConstraints.BOTH;
		gbc_raceDescription.gridx = 3;
		gbc_raceDescription.gridy = 4;
		raceAndClass.add(raceDescription, gbc_raceDescription);
		
		JLabel lblClass_1 = new JLabel("Job");
		lblClass_1.setFont(fl.getFont("medReg18"));
		lblClass_1.setForeground(Color.WHITE);
		GridBagConstraints gbc_lblClass_1 = new GridBagConstraints();
		gbc_lblClass_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblClass_1.gridx = 1;
		gbc_lblClass_1.gridy = 5;
		raceAndClass.add(lblClass_1, gbc_lblClass_1);
		
		jobBox = new JComboBox();
		jobBox.setFont(fl.getFont("medReg18"));
		jobBox.setModel(new DefaultComboBoxModel(new String[] {"Vagabond", "Farmer", "Merchant", "Blacksmith", "Doctor", "Teacher", "Noble", "Guard", "Bartender", "Scribe"}));
		GridBagConstraints gbc_jobBox = new GridBagConstraints();
		gbc_jobBox.insets = new Insets(0, 0, 5, 5);
		gbc_jobBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_jobBox.gridx = 1;
		gbc_jobBox.gridy = 6;
		raceAndClass.add(jobBox, gbc_jobBox);
		
		JPanel jobPanel = new JPanel();
		jobPanel.setBackground(Color.LIGHT_GRAY);
		GridBagConstraints gbc_jobPanel = new GridBagConstraints();
		gbc_jobPanel.insets = new Insets(0, 0, 5, 5);
		gbc_jobPanel.fill = GridBagConstraints.BOTH;
		gbc_jobPanel.gridx = 1;
		gbc_jobPanel.gridy = 7;
		raceAndClass.add(jobPanel, gbc_jobPanel);
		
		JTextArea jobDescription = new JTextArea();
		jobDescription.setBackground(Color.LIGHT_GRAY);
		jobDescription.setWrapStyleWord(true);
		jobDescription.setLineWrap(true);
		jobDescription.setEditable(false);
		jobDescription.setText("Vagabonds do not have any particular affinities. They are typically found traveling from place to place, and do not stay in one area for too long a time.");
		GridBagConstraints gbc_jobDescription = new GridBagConstraints();
		gbc_jobDescription.insets = new Insets(0, 0, 5, 5);
		gbc_jobDescription.fill = GridBagConstraints.BOTH;
		gbc_jobDescription.gridx = 3;
		gbc_jobDescription.gridy = 7;
		raceAndClass.add(jobDescription, gbc_jobDescription);
		
		btnNextPage = new JButton("Next Page");
		btnNextPage.setBackground(Color.LIGHT_GRAY);
		btnNextPage.setForeground(Color.BLACK);
		btnNextPage.setFont(fl.getFont("medReg18"));
		btnNextPage.addActionListener(this);
		
		GridBagConstraints gbc_btnNextPage = new GridBagConstraints();
		gbc_btnNextPage.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnNextPage.insets = new Insets(0, 0, 5, 5);
		gbc_btnNextPage.gridx = 3;
		gbc_btnNextPage.gridy = 8;
		raceAndClass.add(btnNextPage, gbc_btnNextPage);
		
		JPanel ability = new JPanel();
		ability.setBackground(Color.DARK_GRAY);
		Center.add(ability, "name_1598126545981979");
		GridBagLayout gbl_ability = new GridBagLayout();
		gbl_ability.columnWidths = new int[]{0, 85, 0, 0, 0, 0};
		gbl_ability.rowHeights = new int[]{0, 40, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 46, 0};
		gbl_ability.columnWeights = new double[]{0.0, 1.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_ability.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 1.0, 0.0, 1.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		ability.setLayout(gbl_ability);
		
		JLabel lblAbilityScores = new JLabel("Ability Scores");
		lblAbilityScores.setFont(fl.getFont("medReg36"));
		lblAbilityScores.setForeground(Color.WHITE);
		GridBagConstraints gbc_lblAbilityScores = new GridBagConstraints();
		gbc_lblAbilityScores.insets = new Insets(0, 0, 5, 5);
		gbc_lblAbilityScores.gridx = 1;
		gbc_lblAbilityScores.gridy = 1;
		ability.add(lblAbilityScores, gbc_lblAbilityScores);
		
		JLabel lblSkills = new JLabel("Skills");
		lblSkills.setFont(fl.getFont("medReg36"));
		lblSkills.setForeground(Color.WHITE);
		GridBagConstraints gbc_lblSkills = new GridBagConstraints();
		gbc_lblSkills.insets = new Insets(0, 0, 5, 5);
		gbc_lblSkills.gridx = 3;
		gbc_lblSkills.gridy = 1;
		ability.add(lblSkills, gbc_lblSkills);
		
		btnLastPage_2 = new JButton("Last Page");
		btnLastPage_2.setBackground(Color.LIGHT_GRAY);
		btnLastPage_2.setFont(fl.getFont("medReg18"));
		btnLastPage_2.addActionListener(this);
		
		///
		/// STRENGFF
		///	strong like makchine
		///
		JLabel lblStrength = new JLabel("Strength");
		lblStrength.setFont(fl.getFont("medReg18"));
		lblStrength.setForeground(Color.WHITE);
		GridBagConstraints gbc_lblStrength = new GridBagConstraints();
		gbc_lblStrength.insets = new Insets(0, 0, 5, 5);
		gbc_lblStrength.gridx = 1;
		gbc_lblStrength.gridy = 2;
		ability.add(lblStrength, gbc_lblStrength);
		
		GridBagConstraints gbc_strengthPanel = new GridBagConstraints();
		gbc_strengthPanel.insets = new Insets(0, 0, 5, 5);
		gbc_strengthPanel.gridx = 1;
		gbc_strengthPanel.gridy = 3;
		strengthPanel.setBackground(Color.LIGHT_GRAY);
		ability.add(strengthPanel, gbc_strengthPanel);
		strengthPanel.setLayout(new BorderLayout(10, 0));
		
		JLabel sLabelD = new JLabel(new ImageIcon(il.get(-995)));
		JLabel sLabelU = new JLabel(new ImageIcon(il.get(-996)));
		
		sLabel = new JLabel("5");
		sLabel.setFont(fl.getFont("medReg18"));
		
		sLabelD.addMouseListener(new MouseAdapter(){

			public void mouseClicked(MouseEvent e) 
		    {
				if(!(Integer.parseInt(sLabel.getText())==1))
				{
					points.setText(Integer.toString(Integer.parseInt(points.getText())+1));
					sLabel.setText(Integer.toString(Integer.parseInt(sLabel.getText())-1));
				}
				else
				{
					
				}
		    }
			
			
		});
		
		sLabelU.addMouseListener(new MouseAdapter(){

			public void mouseClicked(MouseEvent e) 
		    {
				if(!(Integer.parseInt(points.getText())==0)&!(Integer.parseInt(sLabel.getText())==10))
				{
					points.setText(Integer.toString(Integer.parseInt(points.getText())-1));
					sLabel.setText(Integer.toString(Integer.parseInt(sLabel.getText())+1));
				}
				else
				{
					
				}
		    }
			
			
		});
		
		strengthPanel.add(sLabelD, BorderLayout.WEST);
		strengthPanel.add(sLabel, BorderLayout.CENTER);
		strengthPanel.add(sLabelU, BorderLayout.EAST);

		///
		///CUNTSTITUTION
		///healfy
		///
		
		JLabel lblConstitution = new JLabel("Constitution");
		lblConstitution.setForeground(Color.WHITE);
		GridBagConstraints gbc_lblConstitution = new GridBagConstraints();
		gbc_lblConstitution.insets = new Insets(0, 0, 5, 5);
		gbc_lblConstitution.gridx = 1;
		gbc_lblConstitution.gridy = 4;
		ability.add(lblConstitution, gbc_lblConstitution);
		
		JPanel conPanel = new JPanel();
		conPanel.setBackground(Color.LIGHT_GRAY);
		GridBagConstraints gbc_conPanel = new GridBagConstraints();
		gbc_conPanel.insets = new Insets(0, 0, 5, 5);
		gbc_conPanel.gridx = 1;
		gbc_conPanel.gridy = 5;
		ability.add(conPanel, gbc_conPanel);
		
		conPanel.setLayout(new BorderLayout(10, 0));
		
		JLabel cLabelD = new JLabel(new ImageIcon(il.get(-995)));
		JLabel cLabelU = new JLabel(new ImageIcon(il.get(-996)));
		
		cLabel = new JLabel("5");
		cLabel.setFont(fl.getFont("medReg18"));
		
		cLabelD.addMouseListener(new MouseAdapter(){

			public void mouseClicked(MouseEvent e) 
		    {
				if(!(Integer.parseInt(cLabel.getText())==1))
				{
					points.setText(Integer.toString(Integer.parseInt(points.getText())+1));
					cLabel.setText(Integer.toString(Integer.parseInt(cLabel.getText())-1));
				}
				else
				{
					
				}
		    }
			
			
		});
		
		cLabelU.addMouseListener(new MouseAdapter(){

			public void mouseClicked(MouseEvent e) 
		    {
				if(!(Integer.parseInt(points.getText())==0)&!(Integer.parseInt(cLabel.getText())==10))
				{
					points.setText(Integer.toString(Integer.parseInt(points.getText())-1));
					cLabel.setText(Integer.toString(Integer.parseInt(cLabel.getText())+1));
				}
				else
				{
					
				}
		    }
			
			
		});
		
		conPanel.add(cLabelD, BorderLayout.WEST);
		conPanel.add(cLabel, BorderLayout.CENTER);
		conPanel.add(cLabelU, BorderLayout.EAST);


		///
		///RINTELLIGENCE
		///me reikey maff
		///
		
		JLabel lblIntelligence = new JLabel("Intelligence");
		lblIntelligence.setForeground(Color.WHITE);
		GridBagConstraints gbc_lblIntelligence = new GridBagConstraints();
		gbc_lblIntelligence.insets = new Insets(0, 0, 5, 5);
		gbc_lblIntelligence.gridx = 1;
		gbc_lblIntelligence.gridy = 6;
		ability.add(lblIntelligence, gbc_lblIntelligence);
		
		JPanel intPanel = new JPanel();
		intPanel.setBackground(Color.LIGHT_GRAY);
		GridBagConstraints gbc_intPanel = new GridBagConstraints();
		gbc_intPanel.insets = new Insets(0, 0, 5, 5);
		gbc_intPanel.gridx = 1;
		gbc_intPanel.gridy = 7;
		ability.add(intPanel, gbc_intPanel);
		intPanel.setLayout(new BorderLayout(10, 0));
		
		
		JLabel iLabelD = new JLabel(new ImageIcon(il.get(-995)));
		JLabel iLabelU = new JLabel(new ImageIcon(il.get(-996)));
		
		iLabel = new JLabel("5");
		iLabel.setFont(fl.getFont("medReg18"));
		
		iLabelD.addMouseListener(new MouseAdapter(){

			public void mouseClicked(MouseEvent e) 
		    {
				if(!(Integer.parseInt(iLabel.getText())==1))
				{
					points.setText(Integer.toString(Integer.parseInt(points.getText())+1));
					iLabel.setText(Integer.toString(Integer.parseInt(iLabel.getText())-1));
				}
				else
				{
					
				}
		    }
			
			
		});
		
		iLabelU.addMouseListener(new MouseAdapter(){

			public void mouseClicked(MouseEvent e) 
		    {
				if(!(Integer.parseInt(points.getText())==0)&!(Integer.parseInt(iLabel.getText())==10))
				{
					points.setText(Integer.toString(Integer.parseInt(points.getText())-1));
					iLabel.setText(Integer.toString(Integer.parseInt(iLabel.getText())+1));
				}
				else
				{
					
				}
		    }
			
			
		});
		
		intPanel.add(iLabelD, BorderLayout.WEST);
		intPanel.add(iLabel, BorderLayout.CENTER);
		intPanel.add(iLabelU, BorderLayout.EAST);
		
		
		JLabel lblCharisma = new JLabel("Charisma");
		lblCharisma.setForeground(Color.WHITE);
		GridBagConstraints gbc_lblCharisma = new GridBagConstraints();
		gbc_lblCharisma.insets = new Insets(0, 0, 5, 5);
		gbc_lblCharisma.gridx = 1;
		gbc_lblCharisma.gridy = 8;
		ability.add(lblCharisma, gbc_lblCharisma);
		
		JPanel chaPanel = new JPanel();
		chaPanel.setBackground(Color.LIGHT_GRAY);
		GridBagConstraints gbc_chaPanel = new GridBagConstraints();
		gbc_chaPanel.insets = new Insets(0, 0, 5, 5);
		gbc_chaPanel.gridx = 1;
		gbc_chaPanel.gridy = 9;
		ability.add(chaPanel, gbc_chaPanel);
		chaPanel.setLayout(new BorderLayout(10, 0));
		
		JLabel chLabelD = new JLabel(new ImageIcon(il.get(-995)));
		JLabel chLabelU = new JLabel(new ImageIcon(il.get(-996)));
		
		chLabel = new JLabel("5");
		chLabel.setFont(fl.getFont("medReg18"));
		
		chLabelD.addMouseListener(new MouseAdapter(){

			public void mouseClicked(MouseEvent e) 
		    {
				if(!(Integer.parseInt(chLabel.getText())==1))
				{
					points.setText(Integer.toString(Integer.parseInt(points.getText())+1));
					chLabel.setText(Integer.toString(Integer.parseInt(chLabel.getText())-1));
				}
				else
				{
					
				}
		    }
			
			
		});
		
		chLabelU.addMouseListener(new MouseAdapter(){

			public void mouseClicked(MouseEvent e) 
		    {
				if(!(Integer.parseInt(points.getText())==0)&!(Integer.parseInt(chLabel.getText())==10))
				{
					points.setText(Integer.toString(Integer.parseInt(points.getText())-1));
					chLabel.setText(Integer.toString(Integer.parseInt(chLabel.getText())+1));
				}
				else
				{
					
				}
		    }
			
			
		});
		
		chaPanel.add(chLabelD, BorderLayout.WEST);
		chaPanel.add(chLabel, BorderLayout.CENTER);
		chaPanel.add(chLabelU, BorderLayout.EAST);
		
		JLabel lblDexterity = new JLabel("Dexterity");
		lblDexterity.setForeground(Color.WHITE);
		GridBagConstraints gbc_lblDexterity = new GridBagConstraints();
		gbc_lblDexterity.insets = new Insets(0, 0, 5, 5);
		gbc_lblDexterity.gridx = 1;
		gbc_lblDexterity.gridy = 10;
		ability.add(lblDexterity, gbc_lblDexterity);
		
		JPanel dexPanel = new JPanel();
		dexPanel.setBackground(Color.LIGHT_GRAY);
		GridBagConstraints gbc_dexPanel = new GridBagConstraints();
		gbc_dexPanel.insets = new Insets(0, 0, 5, 5);
		gbc_dexPanel.gridx = 1;
		gbc_dexPanel.gridy = 11;
		ability.add(dexPanel, gbc_dexPanel);
		dexPanel.setLayout(new BorderLayout(10, 0));
		
		
		JLabel dLabelD = new JLabel(new ImageIcon(il.get(-995)));
		JLabel dLabelU = new JLabel(new ImageIcon(il.get(-996)));
		
		dLabel = new JLabel("5");
		dLabel.setFont(fl.getFont("medReg18"));
		
		dLabelD.addMouseListener(new MouseAdapter(){

			public void mouseClicked(MouseEvent e) 
		    {
				if(!(Integer.parseInt(dLabel.getText())==1))
				{
					points.setText(Integer.toString(Integer.parseInt(points.getText())+1));
					dLabel.setText(Integer.toString(Integer.parseInt(dLabel.getText())-1));
				}
				else
				{
					
				}
		    }
			
			
		});
		
		dLabelU.addMouseListener(new MouseAdapter(){

			public void mouseClicked(MouseEvent e) 
		    {
				if(!(Integer.parseInt(points.getText())==0)&!(Integer.parseInt(dLabel.getText())==10))
				{
					points.setText(Integer.toString(Integer.parseInt(points.getText())-1));
					dLabel.setText(Integer.toString(Integer.parseInt(dLabel.getText())+1));
				}
				else
				{
					
				}
		    }
			
			
		});
		
		dexPanel.add(dLabelD, BorderLayout.WEST);
		dexPanel.add(dLabel, BorderLayout.CENTER);
		dexPanel.add(dLabelU, BorderLayout.EAST);
		
		
		JPanel pointPanel = new JPanel();
		pointPanel.setBackground(Color.LIGHT_GRAY);
		GridBagConstraints gbc_pointPanel = new GridBagConstraints();
		gbc_pointPanel.insets = new Insets(0, 0, 5, 5);
		gbc_pointPanel.gridx = 1;
		gbc_pointPanel.gridy = 15;
		ability.add(pointPanel, gbc_pointPanel);
		
		JLabel lblPointsLeft = new JLabel("Points Left: ");
		lblPointsLeft.setFont(fl.getFont("medReg18"));
		pointPanel.add(lblPointsLeft);
		
		points = new JLabel("0");
		pointPanel.add(points);
		GridBagConstraints gbc_btnLastPage_2 = new GridBagConstraints();
		gbc_btnLastPage_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnLastPage_2.insets = new Insets(0, 0, 5, 5);
		gbc_btnLastPage_2.gridx = 1;
		gbc_btnLastPage_2.gridy = 18;
		ability.add(btnLastPage_2, gbc_btnLastPage_2);
		
		btnFinalize = new JButton("Finalize");
		btnFinalize.setBackground(Color.LIGHT_GRAY);
		btnFinalize.setFont(fl.getFont("medReg18"));
		btnFinalize.addActionListener(this);
		GridBagConstraints gbc_btnFinalize = new GridBagConstraints();
		gbc_btnFinalize.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnFinalize.insets = new Insets(0, 0, 5, 5);
		gbc_btnFinalize.gridx = 3;
		gbc_btnFinalize.gridy = 18;
		ability.add(btnFinalize, gbc_btnFinalize);
		
		JPanel Title = new JPanel();
		Title.setBackground(Color.GRAY);
		Master.add(Title, BorderLayout.NORTH);
		GridBagLayout gbl_Title = new GridBagLayout();
		gbl_Title.columnWidths = new int[]{126, 0, 49, 300, 226, 0};
		gbl_Title.rowHeights = new int[]{0, 41, 28, 0, 0};
		gbl_Title.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_Title.rowWeights = new double[]{1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		Title.setLayout(gbl_Title);
		
		JLabel lblNewLabel = new JLabel("New Character");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(fl.getFont("medReg36"));
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 3;
		gbc_lblNewLabel.gridy = 1;
		Title.add(lblNewLabel, gbc_lblNewLabel);
		
		JLabel lblName = new JLabel("Name");
		lblName.setForeground(Color.WHITE);
		lblName.setFont(fl.getFont("medReg18"));
		GridBagConstraints gbc_lblName = new GridBagConstraints();
		gbc_lblName.insets = new Insets(0, 0, 5, 5);
		gbc_lblName.gridx = 2;
		gbc_lblName.gridy = 2;
		Title.add(lblName, gbc_lblName);
		lblName.setFont(fl.getFont("medReg18"));
		
		nameField = new JTextField();
		nameField.setBackground(Color.LIGHT_GRAY);
		nameField.setFont(fl.getFont("medReg18"));
		GridBagConstraints gbc_nameField = new GridBagConstraints();
		gbc_nameField.insets = new Insets(0, 0, 5, 5);
		gbc_nameField.fill = GridBagConstraints.HORIZONTAL;
		gbc_nameField.gridx = 3;
		gbc_nameField.gridy = 2;
		Title.add(nameField, gbc_nameField);
		nameField.setColumns(10);
		
	}
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(btnNextPage))
		{
			CardLayout cl = (CardLayout) Center.getLayout();
			
			cl.next(Center);
		}
		
		if(e.getSource().equals(btnLastPage_2))
		{
			CardLayout cl = (CardLayout) Center.getLayout();
			cl.previous(Center);
		}
		
		if(e.getSource().equals(btnFinalize))
		{
			s.playVillageSong();
			s.setLocationRelativeTo(this);
			System.out.println(raceBox.getSelectedItem().toString());
			s.updatePlayer(Integer.parseInt(sLabel.getText()), Integer.parseInt(cLabel.getText()), Integer.parseInt(iLabel.getText()), 
					Integer.parseInt(chLabel.getText()), Integer.parseInt(dLabel.getText()), nameField.getText(), raceBox.getSelectedItem().toString(), jobBox.getSelectedItem().toString());
			s.setVisible(true);
			dispose();
		}
		
	}
}
