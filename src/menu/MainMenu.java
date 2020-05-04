package menu;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import library.FontLibrary;
import library.ImageLibrary;
import library.Jukebox;
import tools.PaintPane;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JTextPane;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;

import javax.sound.sampled.AudioInputStream;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferStrategy;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.awt.GridLayout;
import javax.swing.BoxLayout;
import java.awt.Component;
import javax.swing.Box;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.RenderingHints;

public class MainMenu extends JFrame 
{
	private PaintPane center;
	private FontLibrary fl = new FontLibrary();
	private ImageLibrary il = new ImageLibrary();
	private Jukebox jukebox = new Jukebox();
	private OptionsMenu om = new OptionsMenu(fl,il,this);
	private NewCharacter nc;
	
	private JTextPane title;
	private Dimension res = new Dimension(960,720);
	private double aspectRatioScale = 1.00;
	
	public MainMenu()
	{
		super();
		this.setTitle("Deremor");
		aspectRatioScale = res.getWidth()/1440;
		this.setBackground(Color.black);
		nc = new NewCharacter(fl,il,this,res,jukebox);
		//this.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		//this.setUndecorated(true);
		this.setSize(new Dimension(960,720));
		this.setLocationRelativeTo(null);
		om.setLocationRelativeTo(this);
		this.setResizable(false);
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		JPanel Master = new JPanel();
		getContentPane().add(Master, BorderLayout.CENTER);
		Master.setLayout(new BorderLayout());
		
		center = new PaintPane(il.get(-997));
		center.setBackground(Color.BLACK);
		//center.add(Box.createVerticalGlue());
		center.add(Box.createRigidArea(new Dimension(0,(int) (380*aspectRatioScale))));
		Master.add(center, BorderLayout.CENTER);
		
		JButton btnNewButton = new JButton("Start New Game");
		btnNewButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnNewButton.setAlignmentY(Component.CENTER_ALIGNMENT);
		btnNewButton.setFont(fl.getFont("medReg18"));
		(btnNewButton).addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				nc.setVisible(true);
				dispose();
			}
		}
		);
		center.setLayout(new BoxLayout(center, BoxLayout.Y_AXIS));
		center.add(btnNewButton);
		center.add(Box.createRigidArea(new Dimension(0,10)));
		JButton options = new JButton("Options");
		options.setAlignmentX(Component.CENTER_ALIGNMENT);
		options.setAlignmentY(Component.CENTER_ALIGNMENT);
		options.setFont(fl.getFont("medReg18"));
		(options).addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				om.setVisible(true);
				om.setModal(true);
			}
		}
		);
		center.add(options);

		
		JPanel north = new JPanel();
		north.setBackground(Color.BLACK);
		Master.add(north, BorderLayout.NORTH);
		
		title = new JTextPane();
		title.setFont(fl.getFont("medReg36"));
		title.setForeground(Color.WHITE);
		title.setBackground(Color.BLACK);
		title.setText("Deremor");
		title.setEditable(false);
		north.add(title);
		
		//apply basic options... will need to load options from save file
		String[] returnValue = new String[5];
		returnValue[0]="1";
		applyOptions(returnValue);
		this.setVisible(true);
		
	}

	
	
	public void applyOptions(String[] applications)
	{
		om.setEnabled(false);
		String s = applications[0];
			switch(Integer.parseInt(s))
			{
				case 4:
					this.setSize(1440,1080);
					res = new Dimension(1440,1080);
					break;
				case 3:
					this.setSize(1280, 960);
					res = new Dimension(1280,960);
					break;
				case 2:
					this.setSize(1024,768);
					res = new Dimension(1024,768);
					break;
				case 1:
					this.setSize(960,720);
					res = new Dimension(960,720);
					break;
				case 0:
					this.setSize(800,600);
					res = new Dimension(800,600);
					break;
				default:
					this.setSize(960,720);
					res = new Dimension(960,720);
			}
			aspectRatioScale = res.getWidth()/1440;
			il.scaleAll(aspectRatioScale);
			nc = new NewCharacter(fl,il,this,res,jukebox);
			this.setLocationRelativeTo(null);
			om.setLocationRelativeTo(this);
			om.setEnabled(true);
			repaint();
	}

}
