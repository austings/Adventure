package menu;

import javax.swing.JDialog;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.LinkedList;


import javax.swing.SwingConstants;

import library.ImageLibrary;
import model.TileThing;

public class Equipment extends JDialog
{
	private ImageLibrary il = new ImageLibrary();
	private JPanel helmPane = new JPanel();
	private JLabel helmLabel;
	private JPanel neckPane = new JPanel();
	private JLabel neckLabel;
	private JPanel facePane = new JPanel();
	private JLabel faceLabel;
	private JPanel shoulderPane = new JPanel();
	private JLabel shoulderLabel;
	private JPanel lArmPane = new JPanel();
	private JLabel larmLabel;
	private JPanel bootPane = new JPanel();
	private JLabel bootLabel;
	private JPanel lHandPane = new JPanel();
	private JLabel lhandLabel;
	private JPanel rHandPane = new JPanel();
	private JLabel rhandLabel;
	private JPanel rArmPane = new JPanel();
	private JLabel rarmLabel;
	private JPanel chestPane = new JPanel();
	private JLabel chestLabel;
	private JPanel greavesPane = new JPanel();
	private JLabel greavesLabel;
	private HashMap<String,TileThing> slots = new HashMap<String,TileThing>();
	
	public Equipment(JFrame parentFrame, String frameTitle, boolean tf)
	{
		super(parentFrame,frameTitle,tf);
		this.setSize(new Dimension(300, 300));
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		getContentPane().setLayout(new GridLayout(5, 3, 0, 0));
		
		JPanel panel = new JPanel();
		getContentPane().add(panel);
		
		getContentPane().add(helmPane);
		helmPane.setLayout(new BorderLayout(0, 0));
		
		JLabel lblHelm = new JLabel("Helm");
		lblHelm.setHorizontalAlignment(SwingConstants.CENTER);
		helmPane.add(lblHelm, BorderLayout.NORTH);
		BufferedImage myPicture = il.get(-51);
		helmLabel = new JLabel(new ImageIcon(myPicture));
		helmLabel.setToolTipText("You have nothing on your head.");
		helmPane.add(helmLabel,BorderLayout.CENTER);
		
		JPanel panel_2 = new JPanel();
		getContentPane().add(panel_2);
		
		getContentPane().add(neckPane);
		neckPane.setLayout(new BorderLayout(0, 0));
		
		JLabel lblBack = new JLabel("Neck");
		lblBack.setHorizontalAlignment(SwingConstants.CENTER);
		neckPane.add(lblBack, BorderLayout.NORTH);
		myPicture = il.get(-54);
		neckLabel = new JLabel(new ImageIcon(myPicture));
		neckLabel.setToolTipText("You have nothing on your neck.");
		neckPane.add(neckLabel,BorderLayout.CENTER);
		
		getContentPane().add(facePane);
		facePane.setLayout(new BorderLayout(0, 0));
		
		JLabel lblFace = new JLabel("Face");
		lblFace.setHorizontalAlignment(SwingConstants.CENTER);
		facePane.add(lblFace, BorderLayout.NORTH);
		myPicture = il.get(-61);
		faceLabel = new JLabel(new ImageIcon(myPicture));
		faceLabel.setToolTipText("You have nothing on your face.");
		facePane.add(faceLabel,BorderLayout.CENTER);
		
		getContentPane().add(shoulderPane);
		shoulderPane.setLayout(new BorderLayout(0, 0));
		
		JLabel lblShoulder = new JLabel("Shoulder");
		lblShoulder.setHorizontalAlignment(SwingConstants.CENTER);
		shoulderPane.add(lblShoulder, BorderLayout.NORTH);
		myPicture = il.get(-55);
		shoulderLabel = new JLabel(new ImageIcon(myPicture));
		shoulderLabel.setToolTipText("You have nothing on your shoulders.");
		shoulderPane.add(shoulderLabel,BorderLayout.CENTER);
		
		getContentPane().add(rArmPane);
		rArmPane.setLayout(new BorderLayout(0, 0));
		
		JLabel lblRightArm = new JLabel("Right Arm");
		lblRightArm.setHorizontalAlignment(SwingConstants.CENTER);
		rArmPane.add(lblRightArm, BorderLayout.NORTH);
		myPicture = il.get(-58);
		rarmLabel = new JLabel(new ImageIcon(myPicture));
		rarmLabel.setToolTipText("You have nothing equipped on your primary hand.");
		rArmPane.add(rarmLabel,BorderLayout.CENTER);
		
		getContentPane().add(chestPane);
		chestPane.setLayout(new BorderLayout(0, 0));
		
		JLabel lblChest = new JLabel("Chest");
		lblChest.setHorizontalAlignment(SwingConstants.CENTER);
		chestPane.add(lblChest, BorderLayout.NORTH);
		myPicture = il.get(-56);
		chestLabel = new JLabel(new ImageIcon(myPicture));
		chestLabel.setToolTipText("You have nothing on your chest.");
		chestPane.add(chestLabel,BorderLayout.CENTER);
		
		getContentPane().add(lArmPane);
		lArmPane.setLayout(new BorderLayout(0, 0));
		
		JLabel lblLeftArm = new JLabel("Left Arm");
		lblLeftArm.setHorizontalAlignment(SwingConstants.CENTER);
		lArmPane.add(lblLeftArm, BorderLayout.NORTH);
		myPicture = il.get(-57);
		larmLabel = new JLabel(new ImageIcon(myPicture));
		larmLabel.setToolTipText("You have nothing for your off hand.");
		lArmPane.add(larmLabel,BorderLayout.CENTER);
		

		getContentPane().add(rHandPane);
		rHandPane.setLayout(new BorderLayout(0, 0));
		
		JLabel lblRightHand = new JLabel("Right Hand");
		lblRightHand.setHorizontalAlignment(SwingConstants.CENTER);
		rHandPane.add(lblRightHand, BorderLayout.NORTH);
		myPicture = il.get(-60);
		rhandLabel = new JLabel(new ImageIcon(myPicture));
		rhandLabel.setToolTipText("You have nothing for gloves.");
		rHandPane.add(rhandLabel,BorderLayout.CENTER);
		
		getContentPane().add(greavesPane);
		greavesPane.setLayout(new BorderLayout(0, 0));
		
		JLabel lblGreaves = new JLabel("Greaves");
		lblGreaves.setHorizontalAlignment(SwingConstants.CENTER);
		greavesPane.add(lblGreaves, BorderLayout.NORTH);
		myPicture = il.get(-52);
		greavesLabel = new JLabel(new ImageIcon(myPicture));
		greavesLabel.setToolTipText("You have nothing on your legs.");
		greavesPane.add(greavesLabel,BorderLayout.CENTER);
		
		getContentPane().add(lHandPane);
		lHandPane.setLayout(new BorderLayout(0, 0));
		
		JLabel lblLeftHand = new JLabel("Left Hand");
		lblLeftHand.setHorizontalAlignment(SwingConstants.CENTER);
		lHandPane.add(lblLeftHand, BorderLayout.NORTH);
		myPicture = il.get(-59);
		lhandLabel = new JLabel(new ImageIcon(myPicture));
		lhandLabel.setToolTipText("You have nothing to put on your finger.");
		lHandPane.add(lhandLabel,BorderLayout.CENTER);
		
		JPanel panel_12 = new JPanel();
		getContentPane().add(panel_12);
		
		getContentPane().add(bootPane);
		bootPane.setLayout(new BorderLayout(0, 0));
		
		JLabel lblBoots = new JLabel("Boots");
		lblBoots.setHorizontalAlignment(SwingConstants.CENTER);
		bootPane.add(lblBoots, BorderLayout.NORTH);
		myPicture = il.get(-53);
		bootLabel = new JLabel(new ImageIcon(myPicture));
		bootLabel.setToolTipText("You have nothing on your feet.");
		bootPane.add(bootLabel,BorderLayout.CENTER);
		
		JPanel panel_14 = new JPanel();
		getContentPane().add(panel_14);
	}
	
	public TileThing addToEquipmentSlot(String slot, TileThing c)
	{
		TileThing n = null;
		JPanel newBox = new JPanel();
		
		if(slot.equals("helmet"))
		{
			if(slots.get("helmet")!=null)
			{
				n=slots.remove("helmet");
				
			}
			slots.put(slot, c);
			BufferedImage myPicture = il.get(c.getImageID());
			helmLabel.setIcon(new ImageIcon(myPicture));
			helmLabel.setToolTipText(c.getToolTipText());
			helmPane.repaint();
		}
		if(slot.equals("necklace"))
		{
			if(slots.get("necklace")!=null)
			{
				n=slots.remove("necklace");
				
			}
			slots.put(slot, c);
			BufferedImage myPicture = il.get(c.getImageID());
			neckLabel.setIcon(new ImageIcon(myPicture));
			neckLabel.setToolTipText(c.getToolTipText());
			neckPane.repaint();
		}
		if(slot.equals("chest")|slot.equals("armor")|slot.equals("shirt"))
		{
			if(slots.get("chest")!=null)
			{
				n=slots.remove("chest");
				
			}
			slots.put("chest", c);
			BufferedImage myPicture = il.get(c.getImageID());
			chestLabel.setIcon(new ImageIcon(myPicture));
			chestLabel.setToolTipText(c.getToolTipText());
			chestPane.repaint();
		}
		if(slot.equals("greaves"))
		{
			if(slots.get("greaves")!=null)
			{
				n=slots.remove("greaves");
				
			}
			slots.put(slot, c);
			BufferedImage myPicture = il.get(c.getImageID());
			greavesLabel.setIcon(new ImageIcon(myPicture));
			greavesLabel.setToolTipText(c.getToolTipText());
			greavesPane.repaint();
		}
		if(slot.equals("boots"))
		{
			if(slots.get("boots")!=null)
			{
				n=slots.remove("boots");
				
			}
			slots.put(slot, c);
			BufferedImage myPicture = il.get(c.getImageID());
			bootLabel.setIcon(new ImageIcon(myPicture));
			bootLabel.setToolTipText(c.getToolTipText());
			bootPane.repaint();
		}
		if(slot.equals("primary arm"))
		{
			if(slots.get("parimary arm")!=null)
			{
				n=slots.remove("primary arm");
				
			}
			slots.put(slot, c);
			BufferedImage myPicture = il.get(c.getImageID());
			rarmLabel.setIcon(new ImageIcon(myPicture));
			rarmLabel.setToolTipText(c.getToolTipText());
			rArmPane.repaint();
		}
		if(slot.equals("glove"))
		{
			if(slots.get("glove")!=null)
			{
				n=slots.remove("glove");
				
			}
			slots.put(slot, c);
			BufferedImage myPicture = il.get(c.getImageID());
			rhandLabel.setIcon(new ImageIcon(myPicture));
			rhandLabel.setToolTipText(c.getToolTipText());
			rHandPane.repaint();
		}
		if(slot.equals("pauldron")|slot.equals("shoulder"))
		{
			if(slots.get("shoulder")!=null)
			{
				n=slots.remove("shoulder");
				
			}
			slots.put(slot, c);
			BufferedImage myPicture = il.get(c.getImageID());
			shoulderLabel.setIcon(new ImageIcon(myPicture));
			shoulderLabel.setToolTipText(c.getToolTipText());
			shoulderPane.repaint();
		}
		if(slot.equals("mask")|slot.equals("face"))
		{
			if(slots.get("face")!=null)
			{
				n=slots.remove("face");
				
			}
			slots.put(slot, c);
			BufferedImage myPicture = il.get(c.getImageID());
			faceLabel.setIcon(new ImageIcon(myPicture));
			faceLabel.setToolTipText(c.getToolTipText());
			facePane.repaint();
		}
		if(slot.equals("finger"))
		{
			if(slots.get("finger")!=null)
			{
				n=slots.remove("finger");
				
			}
			slots.put(slot, c);
			BufferedImage myPicture = il.get(c.getImageID());
			lhandLabel.setIcon(new ImageIcon(myPicture));
			lhandLabel.setToolTipText(c.getToolTipText());
			lHandPane.repaint();
		}
		if(slot.equals("off hand"))
		{
			if(slots.get("off hand")!=null)
			{
				n=slots.remove("off hand");
				
			}
			slots.put(slot, c);
			BufferedImage myPicture = il.get(c.getImageID());
			larmLabel.setIcon(new ImageIcon(myPicture));
			larmLabel.setToolTipText(c.getToolTipText());
			lArmPane.repaint();
		}
		repaint();
		this.repaint();
		return n;
	}
}
