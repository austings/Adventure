package menu;


import javax.swing.JFrame;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JRootPane;
import javax.swing.JSplitPane;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.border.Border;

import people.Creature;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.Random;

import javax.swing.border.CompoundBorder;

import library.ImageLibrary;
import model.TileThing;

import javax.swing.border.BevelBorder;
import java.awt.GridBagLayout;
import javax.swing.JScrollPane;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;

public class Backpack extends JDialog
{
	private ImageLibrary il = new ImageLibrary();
	private Equipment eq;
	private Book book;
	private LinkedList<TileThing> bag = new LinkedList<TileThing>();
	private LinkedList<TileThing> ground;
	private LinkedList<TileThing> dropList = new LinkedList<TileThing>();
	
	private JPanel panel= new JPanel();//main panel
	private JScrollPane scrollPane;
	private JPanel jp;
	private MouseListener mouseListener ;
	
	public Backpack(JFrame parentFrame, String frameTitle, boolean tf)
	{
		super(parentFrame,frameTitle,tf);
		this.addWindowListener((WindowListener) parentFrame);
		this.getRootPane().setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
		this.setName("backpack");
		eq = new Equipment(parentFrame,"Equipment",tf);
		book = new Book(parentFrame, "",tf,"Deremor Vol I");
		this.setSize(new Dimension(300, 200));
		this.setLocationRelativeTo(parentFrame);
		this.setResizable(false);
		//this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.requestFocus();
		mouseListener = new MouseAdapter() {
		    public void mouseClicked(MouseEvent e) 
		    {
		        if(e.getButton() == MouseEvent.BUTTON3)
			    {
		        	jp = (JPanel)e.getComponent();
		        	final TileThing g = getFromBackpack(jp.getToolTipText());
		            final JPopupMenu menu = new JPopupMenu();
		            JMenuItem drop = new JMenuItem("Drop");
		            JMenuItem desc = new JMenuItem("Info");
		            menu.add(desc);
		            menu.add(drop);
		            if(g.readable){
			            JMenuItem read = new JMenuItem("Read");
			            menu.add(read);
			            read.addActionListener(new ActionListener(){
			            	
			            	public void actionPerformed(ActionEvent ez)
			            	{
								book.setVisible(true);
			            	}
			            });
		            }
		            if(g.equippable){
			            JMenuItem equip = new JMenuItem("Equip");
			            menu.add(equip);
			            equip.addActionListener(new ActionListener(){
			            	
			            	public void actionPerformed(ActionEvent ez)
			            	{
								TileThing s = eq.addToEquipmentSlot(g.getType(), g);
								if(s!=null)
									addToBackpack(s);
								removeFromBackpack(jp.getToolTipText());
								
								validate();
			            	}
			            });
		            }
		            desc.addActionListener(new ActionListener(){

						public void actionPerformed(ActionEvent arg0) {
							JOptionPane.showMessageDialog(null, g.getToolTipText(),g.getName(), JOptionPane.INFORMATION_MESSAGE);
							
						}
		            });
		            drop.addActionListener(new ActionListener(){

						public void actionPerformed(ActionEvent ez) {
							dropFromBackpack(jp.getToolTipText());
							validate();
						}
		            	
		            });

		            repaint();
		            menu.show(e.getComponent(), e.getX(), e.getY());
			    }
		    }
		};
		JScrollPane scrollPane = new JScrollPane(panel);
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		panel.setLayout(new GridLayout(0, 3, 5, 5));
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		JPanel panel_2 = new JPanel();
		getContentPane().add(panel_2, BorderLayout.SOUTH);
		FlowLayout flowLayout = (FlowLayout) panel_2.getLayout();
		flowLayout.setHgap(50);
		flowLayout.setAlignment(FlowLayout.RIGHT);
		
		JButton btnNewButton = new JButton("Equipment");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				eq.setVisible(true);
				
			}
		});
		btnNewButton.setHorizontalAlignment(SwingConstants.LEFT);
		panel_2.add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("Weight 0/0");
		panel_2.add(lblNewLabel);
		

	}
	
	public void setGround(LinkedList<TileThing> g )
	{
		ground = g;
	}
	
	public LinkedList<TileThing> getGround()
	{
		return ground;
	}
	
	public void setInventory(LinkedList<TileThing> newInventory)
	{
		if(newInventory.size()<1)
		{
			for (Component panelOfOurItem : panel.getComponents()) {
				if (panelOfOurItem instanceof JPanel) { 
					panel.remove(panelOfOurItem);
					bag.clear();
					break;
				}
			}

		}
		for(TileThing t: bag)
		{
			removeAll();
		}
		for(TileThing t: newInventory)
		{
			addToBackpack(t);
		}
	}
	
	
	public void addToBackpack(TileThing c)
	{
		bag.add(c);
		
		JPanel newBox = new JPanel();
		newBox.setLayout(new BorderLayout());
		BufferedImage myPicture = il.get(c.getImageID());
		JLabel picLabel = new JLabel(new ImageIcon(myPicture));
		newBox.add(picLabel,BorderLayout.CENTER);
		String itemName = c.getName();
		newBox.setToolTipText(itemName);
		if(itemName.length()>12)
		{
			itemName = itemName.substring(0,10)+"...";
		}
		JLabel text = new JLabel(itemName,SwingConstants.CENTER);
		newBox.add(text,BorderLayout.SOUTH);
		newBox.addMouseListener(mouseListener);
		panel.add(newBox);
		
		repaint();
	}
	
	public void setBackpack()
	{
		
	}
	
	public LinkedList<TileThing> getDropList()
	{
		return dropList;
	}
	
	public void clearDropList()
	{
		dropList.clear();
	}
	
	public LinkedList<TileThing> getBag()
	{
		return bag;
	}
	
	public TileThing dropFromBackpack(String name)
	{
		TileThing n = null;
		//panel.removeAll();

		for (int i = 0; i < bag.size(); i++)
		{

			if(bag.get(i).getName().equals(name))
			{
				panel.remove(jp);
				n = bag.get(i);
				dropList.add(bag.get(i));
				bag.remove(i);
				i--;
				break;
			}
		}
		repaint();
		
		return n;
	}
	
	public TileThing removeFromBackpack(String name)
	{
		TileThing n = null;
		//panel.removeAll();
		for (int i = 0; i < bag.size(); i++)
		{
			if(bag.get(i).getName().equals(name))
			{

				panel.remove(jp);
				n = bag.get(i);
				bag.remove(i);
				i--;
				break;
			}
		}
		repaint();
		
		return n;
	}
	
	public void removeAll()
	{
		for (Component panelOfOurItem : panel.getComponents()) {
			if (panelOfOurItem instanceof JPanel) { 
				panel.remove(panelOfOurItem);
				break;
			}
		}
		bag.clear();
		repaint();
		
	}
	
	
	
	public TileThing getFromBackpack(String name)
	{
		for (int i = 0; i < bag.size(); i++)
		{
			if(bag.get(i).getName().equals(name))
			{
				return bag.get(i);
			}
		}
		return null;
		
	}
	
}
