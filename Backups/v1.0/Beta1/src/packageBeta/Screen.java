package packageBeta;
import java.awt.Dimension;

import javax.swing.*;

import java.awt.CardLayout;
import java.awt.BorderLayout;
import javax.swing.GroupLayout.Alignment;

import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Font;
import java.awt.event.*;


public class Screen extends JFrame
{
	private JTextField txtTypeTheCommand;
	private GamePanel gp ;
	
	public Screen()
	{
		
		super();
		this.setSize(new Dimension(951,854));
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		
		JPanel mainPanel = new JPanel();
		getContentPane().add(mainPanel, BorderLayout.CENTER);
		mainPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel westPanel = new JPanel();
		mainPanel.add(westPanel, BorderLayout.WEST);
		GridBagLayout gbl_westPanel = new GridBagLayout();
		gbl_westPanel.columnWidths = new int[]{257, 0};
		gbl_westPanel.rowHeights = new int[]{391, 0, 0};
		gbl_westPanel.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_westPanel.rowWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
		westPanel.setLayout(gbl_westPanel);
		
		JPanel statesPanel = new JPanel();
		GridBagConstraints gbc_statesPanel = new GridBagConstraints();
		gbc_statesPanel.fill = GridBagConstraints.BOTH;
		gbc_statesPanel.insets = new Insets(0, 0, 5, 0);
		gbc_statesPanel.gridx = 0;
		gbc_statesPanel.gridy = 0;
		westPanel.add(statesPanel, gbc_statesPanel);
		GridBagLayout gbl_statesPanel = new GridBagLayout();
		gbl_statesPanel.columnWidths = new int[]{34, 48, 0};
		gbl_statesPanel.rowHeights = new int[]{56, 0, 0, 0, 0, 0, 0};
		gbl_statesPanel.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_statesPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		statesPanel.setLayout(gbl_statesPanel);
		
		JButton btnNewButton_2 = new JButton("Exit");
		GridBagConstraints gbc_btnNewButton_2 = new GridBagConstraints();
		gbc_btnNewButton_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnNewButton_2.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_2.anchor = GridBagConstraints.NORTH;
		gbc_btnNewButton_2.gridx = 0;
		gbc_btnNewButton_2.gridy = 0;
		statesPanel.add(btnNewButton_2, gbc_btnNewButton_2);
		
		JLabel lblLaindirTheLost = new JLabel("Laindir the Lost");
		GridBagConstraints gbc_lblLaindirTheLost = new GridBagConstraints();
		gbc_lblLaindirTheLost.insets = new Insets(0, 0, 5, 0);
		gbc_lblLaindirTheLost.gridx = 1;
		gbc_lblLaindirTheLost.gridy = 0;
		statesPanel.add(lblLaindirTheLost, gbc_lblLaindirTheLost);
		
		JLabel lblHp = new JLabel("HP");
		GridBagConstraints gbc_lblHp = new GridBagConstraints();
		gbc_lblHp.insets = new Insets(0, 0, 5, 5);
		gbc_lblHp.gridx = 0;
		gbc_lblHp.gridy = 1;
		statesPanel.add(lblHp, gbc_lblHp);
		
		JTextPane textPane_3 = new JTextPane();
		textPane_3.setText("1");
		GridBagConstraints gbc_textPane_3 = new GridBagConstraints();
		gbc_textPane_3.insets = new Insets(0, 0, 5, 0);
		gbc_textPane_3.gridx = 1;
		gbc_textPane_3.gridy = 1;
		statesPanel.add(textPane_3, gbc_textPane_3);
		
		JLabel lblStr = new JLabel("STR");
		GridBagConstraints gbc_lblStr = new GridBagConstraints();
		gbc_lblStr.insets = new Insets(0, 0, 5, 5);
		gbc_lblStr.gridx = 0;
		gbc_lblStr.gridy = 2;
		statesPanel.add(lblStr, gbc_lblStr);
		
		JTextPane textPane = new JTextPane();
		textPane.setText("1");
		GridBagConstraints gbc_textPane = new GridBagConstraints();
		gbc_textPane.insets = new Insets(0, 0, 5, 0);
		gbc_textPane.gridx = 1;
		gbc_textPane.gridy = 2;
		statesPanel.add(textPane, gbc_textPane);
		
		JLabel lblDex = new JLabel("DEX");
		GridBagConstraints gbc_lblDex = new GridBagConstraints();
		gbc_lblDex.insets = new Insets(0, 0, 5, 5);
		gbc_lblDex.gridx = 0;
		gbc_lblDex.gridy = 3;
		statesPanel.add(lblDex, gbc_lblDex);
		
		JTextPane textPane_1 = new JTextPane();
		textPane_1.setText("1");
		GridBagConstraints gbc_textPane_1 = new GridBagConstraints();
		gbc_textPane_1.insets = new Insets(0, 0, 5, 0);
		gbc_textPane_1.gridx = 1;
		gbc_textPane_1.gridy = 3;
		statesPanel.add(textPane_1, gbc_textPane_1);
		
		JLabel lblInt = new JLabel("INT");
		GridBagConstraints gbc_lblInt = new GridBagConstraints();
		gbc_lblInt.insets = new Insets(0, 0, 5, 5);
		gbc_lblInt.gridx = 0;
		gbc_lblInt.gridy = 4;
		statesPanel.add(lblInt, gbc_lblInt);
		
		JTextPane textPane_2 = new JTextPane();
		textPane_2.setText("1");
		GridBagConstraints gbc_textPane_2 = new GridBagConstraints();
		gbc_textPane_2.insets = new Insets(0, 0, 5, 0);
		gbc_textPane_2.gridx = 1;
		gbc_textPane_2.gridy = 4;
		statesPanel.add(textPane_2, gbc_textPane_2);
		
		JPanel buttonWestPanel = new JPanel();
		GridBagConstraints gbc_buttonWestPanel = new GridBagConstraints();
		gbc_buttonWestPanel.anchor = GridBagConstraints.SOUTH;
		gbc_buttonWestPanel.fill = GridBagConstraints.HORIZONTAL;
		gbc_buttonWestPanel.gridx = 0;
		gbc_buttonWestPanel.gridy = 1;
		westPanel.add(buttonWestPanel, gbc_buttonWestPanel);
		buttonWestPanel.setLayout(new GridLayout(2, 3, 1, 0));
		
		JButton btnItems = new JButton("Items");
		buttonWestPanel.add(btnItems);
		
		JButton btnContacts = new JButton("Contacts");
		buttonWestPanel.add(btnContacts);
		
		JButton btnLocations = new JButton("Locations");
		buttonWestPanel.add(btnLocations);
		
		JButton btnStats = new JButton("Stats");
		buttonWestPanel.add(btnStats);
		
		JButton btnSleep = new JButton("Sleep");
		buttonWestPanel.add(btnSleep);
		
		JButton btnNewButton = new JButton("Save");
		buttonWestPanel.add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		
		JPanel messagePanel = new JPanel();
		mainPanel.add(messagePanel, BorderLayout.SOUTH);
		messagePanel.setLayout(new BorderLayout(0, 0));
		
		
		JTextArea textArea = new JTextArea(5,32);
		textArea.setText("you were attacked\r\noh no\r\nit hurts\r\njust about\r\nfive lines ought to do it");
		messagePanel.add(textArea, BorderLayout.NORTH);
		
		JScrollPane scrollPane_2 = new JScrollPane(textArea);
		scrollPane_2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane_2.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		messagePanel.add(scrollPane_2, BorderLayout.CENTER);
		
		txtTypeTheCommand = new JTextField();
		txtTypeTheCommand.setText("type the command");
		messagePanel.add(txtTypeTheCommand, BorderLayout.SOUTH);
		txtTypeTheCommand.setColumns(10);
		
		JPanel titlePanel = new JPanel();
		mainPanel.add(titlePanel, BorderLayout.NORTH);
		
		JLabel lblNewLabel = new JLabel("The Horn of Jormungr");
		lblNewLabel.setFont(new Font("Yu Gothic Medium", Font.BOLD, 20));
		titlePanel.add(lblNewLabel);
		
		gp = new GamePanel(new Dimension(100,100));
		InputMap im = gp.getInputMap(JPanel.WHEN_IN_FOCUSED_WINDOW);
		ActionMap am = gp.getActionMap();
		
		im.put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT,0), "right");
		im.put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT,0), "left");
		im.put(KeyStroke.getKeyStroke(KeyEvent.VK_UP,0), "up");
		im.put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN,0), "down");
		
		am.put("right", new Listener("right"));
		am.put("left", new Listener("left"));
		am.put("up", new Listener("up"));
		am.put("down", new Listener("down"));
		
		mainPanel.add(gp, BorderLayout.CENTER);
		gp.setLayout(new BorderLayout(0, 0));
		gp.setFocusable(true);
		gp.setDoubleBuffered(true);
		this.setVisible(true);

	}
	
	public class Listener extends AbstractAction
	{
		private String btn ="";
		
		public Listener(String s)
		{
			btn = s;
		}
		
		public void actionPerformed(ActionEvent e) {
			if(btn.equalsIgnoreCase("right")||
					btn.equalsIgnoreCase("left")||
					btn.equalsIgnoreCase("up")||
					btn.equalsIgnoreCase("down"))
			{
				gp.movePlayer(btn);
			}
			
			
		}
		
	}


}
