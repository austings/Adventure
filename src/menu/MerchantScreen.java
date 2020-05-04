package menu;

import java.util.ArrayList;
import java.util.LinkedList;

import javax.swing.*;

import library.ImageLibrary;
import model.TileThing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;

public class MerchantScreen extends JDialog implements ActionListener
{
	private LinkedList<TileThing> forSale;//their inv
	private LinkedList<TileThing> bag;//players inv
	private int mG;
	private int tG;
	private JPanel sellModel;
	private JScrollPane sellPane;
	private JPanel buyModel;
	private JPanel selection;
	private JScrollPane buyPane;
	private ImageLibrary il = new ImageLibrary();
	private MouseAdapter mouseListener;
	
	private ArrayList<Object> returns = new ArrayList<Object>();
	private JPanel sellerGoldPanel;
	private JPanel confirmPanel;
	private JPanel differencePanel;
	private JPanel playerGoldPanel;
	private JButton btnTrade;
	private JLabel lblTheirGold;
	private JLabel lblYourGold;
	private JLabel theirGold;
	private JLabel yourGold;
	private JLabel lblDifference;
	private JLabel difference;
	private JPanel center;
	private JLabel lblBuy;
	
	public MerchantScreen(JFrame parentFrame, String frameTitle, boolean tf,LinkedList<TileThing> bagFrom,LinkedList<TileThing> sale,int myGold, int theyGold) 
	{
		super(parentFrame,frameTitle,tf);
		this.addWindowListener((WindowListener) parentFrame);
		this.setName("merchant");
		this.setUndecorated(true);
		this.getRootPane().setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
		JPanel north = new JPanel();
		this.setModal(true);
		getContentPane().add(north, BorderLayout.NORTH);
		
		lblBuy = new JLabel("\"Welcome to my shop, I'm sure my wares will be to your liking.\"");
		north.add(lblBuy);
		
		JPanel south = new JPanel();
		JButton approve = new JButton("Accept");
		approve.setActionCommand("approve");
		approve.addActionListener(this);
		
		center = new JPanel();
		getContentPane().add(center, BorderLayout.CENTER);
		center.setLayout(new BorderLayout(0, 0));
		
		sellModel = new JPanel();
		sellModel.setLayout(new GridLayout(0,1,10,0));
		
		buyModel = new JPanel();
		buyModel.setLayout(new GridLayout(0,1,10,0));
	

		getContentPane().add(south, BorderLayout.SOUTH);
		south.setLayout(new GridLayout(1, 4, 0, 0));
		
		sellerGoldPanel = new JPanel();
		south.add(sellerGoldPanel);
		sellerGoldPanel.setLayout(new BorderLayout(0, 0));
		
		lblTheirGold = new JLabel("Their Gold:");
		sellerGoldPanel.add(lblTheirGold, BorderLayout.NORTH);
		
		theirGold = new JLabel(Integer.toString(theyGold));
		theirGold.setFont(new Font("Tahoma", Font.BOLD, 20));
		theirGold.setHorizontalAlignment(SwingConstants.RIGHT);
		sellerGoldPanel.add(theirGold, BorderLayout.SOUTH);
		
		confirmPanel = new JPanel();
		south.add(confirmPanel);
		
		btnTrade = new JButton("Accept");
		btnTrade.addActionListener(this);
		btnTrade.setActionCommand("approve");
		confirmPanel.add(btnTrade);
		
		differencePanel = new JPanel();
		difference = new JLabel("0");
		differencePanel.add(difference);
		south.add(differencePanel);
		
		playerGoldPanel = new JPanel();
		south.add(playerGoldPanel);
		playerGoldPanel.setLayout(new BorderLayout(0, 0));
		
		lblYourGold = new JLabel("Your Gold:");
		playerGoldPanel.add(lblYourGold, BorderLayout.NORTH);
		
		yourGold = new JLabel(Integer.toString(myGold));
		yourGold.setFont(new Font("Tahoma", Font.BOLD, 20));
		yourGold.setHorizontalAlignment(SwingConstants.RIGHT);
		playerGoldPanel.add(yourGold, BorderLayout.SOUTH);
		
		this.forSale = sale;
		this.bag = bagFrom;
		mouseListener = new MouseAdapter() {
		    public void mouseClicked(MouseEvent e) 
		    {
				if(e.getComponent() instanceof JPanel) {
				selection = (JPanel)e.getComponent();
				if(selection.getName().startsWith("S00")) {
						
					for(TileThing t: bag)
					{
						if(t.getCallNum().equals(selection.getName().substring(3)))
						{
							bag.remove(t);
							forSale.add(t);
							difference.setText(Integer.toString(Integer.parseInt(difference.getText())+t.getValue()));
							yourGold.setText(Integer.toString(Integer.parseInt(yourGold.getText())+t.getValue()));
							theirGold.setText(Integer.toString(Integer.parseInt(theirGold.getText())-t.getValue()));
							MerchantScreen topFrame = (MerchantScreen) SwingUtilities.getWindowAncestor(selection);
							topFrame.refresh();
						}
					}
						
				}
				else {
					for(TileThing t: forSale)
					{
						if(t.getCallNum().equals(selection.getName().substring(3)))
						{
							forSale.remove(t);
							bag.add(t);
							difference.setText(Integer.toString(Integer.parseInt(difference.getText())-t.getValue()));
							yourGold.setText(Integer.toString(Integer.parseInt(yourGold.getText())-t.getValue()));
							theirGold.setText(Integer.toString(Integer.parseInt(theirGold.getText())+t.getValue()));
							MerchantScreen topFrame = (MerchantScreen) SwingUtilities.getWindowAncestor(selection);
							topFrame.refresh();
						}
					}
				}
			}
		    	//final TileThing g = (selection.getToolTipText());
		        final JPopupMenu menu = new JPopupMenu();
		        JMenuItem desc = new JMenuItem("Info");
		        menu.add(desc);
		       // if(g.readable)
		        //{
		        	
		        //}
		        
		        
		    }
		};
		for(TileThing t: bag)
		{
			int i = 0;
			JPanel newBox = new JPanel();
			newBox.setLayout(new BorderLayout());
			BufferedImage myPicture = il.get(t.getImageID());
			JLabel picLabel = new JLabel(new ImageIcon(myPicture));
			JLabel jl = new JLabel(t.getName());
			newBox.add(BorderLayout.WEST,picLabel);
			newBox.add(BorderLayout.CENTER,jl);
			newBox.addMouseListener(mouseListener);
			newBox.setName("S00"+t.getCallNum());
			sellModel.add(newBox);
			i++;
		}
		for(TileThing t: forSale)
		{
			JPanel newBox = new JPanel();
			newBox.setLayout(new BorderLayout());
			BufferedImage myPicture = il.get(t.getImageID());
			JLabel picLabel = new JLabel(new ImageIcon(myPicture));
			JLabel jl = new JLabel(t.getName());
			newBox.add(BorderLayout.WEST,picLabel);
			newBox.add(BorderLayout.CENTER,jl);
			newBox.addMouseListener(mouseListener);
			newBox.setName("B00"+t.getCallNum());
			buyModel.add(newBox);
		}
		
		sellPane = new JScrollPane(sellModel);
		buyPane = new JScrollPane(buyModel);
		
		center.add(sellPane, BorderLayout.EAST);
		center.add(buyPane, BorderLayout.WEST);
		
		this.requestFocus();
		
		this.setSize(new Dimension(500,500));
		this.setLocationRelativeTo(parentFrame);
		this.setResizable(false);
		this.requestFocus();
		this.setVisible(true);
		
	}
	
	public void actionPerformed(ActionEvent e)
	{
		if(e.getActionCommand().equals("approve"))
		{	
			if(Integer.parseInt(yourGold.getText())>=0&0<=Integer.parseInt(theirGold.getText()))
			{		
				buyModel.removeAll();
				sellModel.removeAll();
				sellPane.removeAll();
				buyPane.removeAll();
				refresh();
				this.dispose();
			}
			else
			{
				if(Integer.parseInt(yourGold.getText())<0)
					lblBuy.setText("\"You do not have enough gold for that.\"");
				else
					lblBuy.setText("\"I cannot afford this.\"");
			}
			
		}
	}
	
	public LinkedList<TileThing> getBag()
	{
		return bag;
	}
	
	public LinkedList<TileThing> getSellerInv()
	{
		return forSale;
	}
	
	public int getSellerGold()
	{
		return Integer.parseInt(theirGold.getText());
	}
	
	public int getYourGold()
	{
		return Integer.parseInt(yourGold.getText());
	}
	
	
	public void refresh()
	{
		buyModel.removeAll();
		sellModel.removeAll();
		center.removeAll();
		for(TileThing t: bag)
		{
			int i = 0;
			JPanel newBox = new JPanel();
			newBox.setLayout(new BorderLayout());
			BufferedImage myPicture = il.get(t.getImageID());
			JLabel picLabel = new JLabel(new ImageIcon(myPicture));
			JLabel jl = new JLabel(t.getName());
			newBox.add(BorderLayout.WEST,picLabel);
			newBox.add(BorderLayout.CENTER,jl);
			newBox.addMouseListener(mouseListener);
			newBox.setName("S00"+t.getCallNum());
			sellModel.add(newBox);
			i++;
		}
		for(TileThing t: forSale)
		{
			JPanel newBox = new JPanel();
			newBox.setLayout(new BorderLayout());
			BufferedImage myPicture = il.get(t.getImageID());
			JLabel picLabel = new JLabel(new ImageIcon(myPicture));
			JLabel jl = new JLabel(t.getName());
			newBox.add(BorderLayout.WEST,picLabel);
			newBox.add(BorderLayout.CENTER,jl);
			newBox.addMouseListener(mouseListener);
			newBox.setName("B00"+t.getCallNum());
			buyModel.add(newBox);
		}
		sellPane = new JScrollPane(sellModel);
		buyPane = new JScrollPane(buyModel);
		
		center.add(sellPane, BorderLayout.EAST);
		center.add(buyPane, BorderLayout.WEST);
		
		validate();
		revalidate();
		repaint();
	}
}
