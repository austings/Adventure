package menu;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JTextField;

import library.FontLibrary;
import library.ImageLibrary;

import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JRadioButton;
import javax.swing.DefaultComboBoxModel;
import java.awt.Font;

public class OptionsMenu extends JDialog
{
	public OptionsMenu(FontLibrary fl, ImageLibrary il, JFrame parentFrame)
	{
		
		JPanel centerPanel = new JPanel();
		getContentPane().add(centerPanel, BorderLayout.NORTH);
		this.setLocationRelativeTo(parentFrame);
		centerPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		centerPanel.add(panel, BorderLayout.NORTH);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{50, 100, 150, 50, 0};
		gbl_panel.rowHeights = new int[]{47, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 1.0, 0.0, 1.0, 1.0, 1.0, 0.0, 1.0, 1.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JLabel lblOptions = new JLabel("Options");
		lblOptions.setFont(new Font("Tahoma", Font.BOLD, 18));
		GridBagConstraints gbc_lblOptions = new GridBagConstraints();
		gbc_lblOptions.fill = GridBagConstraints.VERTICAL;
		gbc_lblOptions.insets = new Insets(0, 0, 5, 5);
		gbc_lblOptions.gridx = 1;
		gbc_lblOptions.gridy = 0;
		panel.add(lblOptions, gbc_lblOptions);
		
		/*
		JRadioButton rdbtnFullScreen = new JRadioButton("Full Screen");
		GridBagConstraints gbc_rdbtnFullScreen = new GridBagConstraints();
		gbc_rdbtnFullScreen.fill = GridBagConstraints.VERTICAL;
		gbc_rdbtnFullScreen.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnFullScreen.gridx = 3;
		gbc_rdbtnFullScreen.gridy = 3;
		panel.add(rdbtnFullScreen, gbc_rdbtnFullScreen);*/
		
		
		JLabel lblResolution = new JLabel("Resolution");
		GridBagConstraints gbc_lblResolution = new GridBagConstraints();
		gbc_lblResolution.fill = GridBagConstraints.VERTICAL;
		gbc_lblResolution.insets = new Insets(0, 0, 5, 5);
		gbc_lblResolution.gridx = 1;
		gbc_lblResolution.gridy = 3;
		panel.add(lblResolution, gbc_lblResolution);
		
		JComboBox resolution = new JComboBox();
		resolution.setModel(new DefaultComboBoxModel(new String[] {"800x600","960x720", "1024x768", "1280x960", "1440x1080"}));
		resolution.setSelectedIndex(1);
		GridBagConstraints gbc_resolution = new GridBagConstraints();
		gbc_resolution.insets = new Insets(0, 0, 5, 5);
		gbc_resolution.fill = GridBagConstraints.BOTH;
		gbc_resolution.gridx = 2;
		gbc_resolution.gridy = 3;
		panel.add(resolution, gbc_resolution);
		JButton btnApply = new JButton("Apply");
		btnApply.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) 
			{
				String[] returnValue = new String[5];
				returnValue[0]=Integer.toString(resolution.getSelectedIndex());
				((MainMenu)parentFrame).applyOptions(returnValue);
				
			}

		});
		GridBagConstraints gbc_btnApply = new GridBagConstraints();
		gbc_btnApply.fill = GridBagConstraints.VERTICAL;
		gbc_btnApply.insets = new Insets(0, 0, 5, 5);
		gbc_btnApply.gridx = 2;
		gbc_btnApply.gridy = 7;
		panel.add(btnApply, gbc_btnApply);
		this.pack();
		
		
	}
}
