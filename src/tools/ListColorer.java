package tools;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

//https://stackoverflow.com/questions/1076473/how-to-generate-a-jlist-with-alternating-colors
public class ListColorer extends JLabel implements ListCellRenderer {

	private int switchInt =0;
	
    public ListColorer(int sI) {
    	switchInt = sI;
        setOpaque(true);
    }

    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        // Assumes the stuff in the list has a pretty toString
        setText(value.toString());

        // based on the index you set the color.  This produces the every other effect.
        if (value.toString().contains("-")) setForeground(Color.RED);
        else setForeground(Color.BLUE);

        return this;
    }
}