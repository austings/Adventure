package menu;

import javax.swing.JDialog;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JScrollPane;
import javax.swing.JButton;

public class MessageArchive extends JDialog
{
	public MessageArchive() {
		
		JPanel center = new JPanel();
		getContentPane().add(center, BorderLayout.CENTER);
		JScrollPane scrollPane = new JScrollPane(center);
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		center.setLayout(new GridLayout(0, 3, 5, 5));
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

		
		
		JPanel optionPane = new JPanel();
		getContentPane().add(optionPane, BorderLayout.SOUTH);
		
		JButton btnExport = new JButton("Export");
		optionPane.add(btnExport);
		
		JButton btnClose = new JButton("Close");
		optionPane.add(btnClose);
	}

}
