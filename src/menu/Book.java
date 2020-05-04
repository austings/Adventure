package menu;

import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.Image;
import javax.swing.JSplitPane;
import java.awt.CardLayout;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;

import library.ImageLibrary;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.FlowLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;

public class Book extends JDialog implements MouseListener
{
	private ImageLibrary il = new ImageLibrary();
	private ArrayList<JPanel> pages;
	private JPanel oddPages;
	private JPanel evenPages;
	
	
	public Book(JFrame parentFrame, String frameTitle, boolean tf,String name)
	{
		super(parentFrame,frameTitle,tf);
		this.setSize(new Dimension(1280, 800));
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		//main panel
		JPanelB mainP = new JPanelB(-998);
		mainP.isOptimizedDrawingEnabled();
		mainP.setOpaque(false);
		getContentPane().add(mainP, BorderLayout.CENTER);
		getContentPane().add(mainP);
		mainP.setLayout(new BoxLayout(mainP, BoxLayout.X_AXIS));
		
		//oddpages
		oddPages = new JPanel();
		mainP.add(oddPages);
		oddPages.setLayout(new CardLayout(0, 0));
		
		//even pages
		evenPages = new JPanel();
		mainP.add(evenPages);
		evenPages.setLayout(new CardLayout(0, 0));
		
		evenPages.setOpaque(false);
		oddPages.setOpaque(false);
		
		//the book we are creating
		pages = new ArrayList<JPanel>();

		
		try {
			//custom font
			InputStream is = Book.class.getResourceAsStream("/resources/fonts/boots.ttf");
		    Font font = Font.createFont(Font.TRUETYPE_FONT, is);
		    Font customFont = font.deriveFont(24f);
		    Font customFont2 = font.deriveFont(36f);
		    GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		    //register the font
		    ge.registerFont(customFont);
		
		
		if(name.equals("Deremor Vol I"))
		{
			//page 1
			JPanel page1 = new JPanel();
			page1.setOpaque(false);
			BoxLayout Layout_1 = new BoxLayout(page1,BoxLayout.Y_AXIS);
			page1.setLayout(Layout_1);
			
			//title
			EmptyBorder border = new EmptyBorder(75, 275, 50, 0);
			String text = ("Deremor Volume I");
			JLabel textJ = new JLabel(text);
			textJ.setFont(customFont2);
			textJ.setBorder(border);	
			page1.add(textJ);
			
			//paragraph
			EmptyBorder border1 = new EmptyBorder(15, 125, 0, 100);
			String text1 = ("<html>&nbsp;&nbsp;&nbsp;&nbsp;Lorem ipsum dolor sit amet, consectetur adipiscing elit.<br>" +
					"Sed lectus mi, vestibulum quis nunc eget, dapibus pulvinar " +
					"Nunc in vestibulum ligula, id dapibus augue. In commodo " +
					"pretium orci nec finibus. Aliquam dictum suscipit augue, " +
					"quis euismod dui aliquet sit amet. Curabitur interdum commodo " +
					"pellentesque. Integer cursus consequat orci, a bibendum nisl " +
					"venenatis sit amet. Suspendisse dictum suscipit tincidunt. " +
					"Maecenas porttitor elit ut tortor vehicula, non elementum dui " +
					"scelerisque. Proin at eleifend sapien. Praesent imperdiet, " +
					"elit a luctus ullamcorper, velit diam lobortis nisl, ac " +
					"facilisis mi purus a purus. Ut euismod ultricies magna. Etiam " +
					"aliquet risus aliquam ipsum vehicula, vel commodo magna facilisis. " +
					"Donec id sem vitae diam mollis scelerisque at id turpis. " +
					"Phasellus libero eros, cursus eget rutrum nec, cursus id " +
					"tortor. Aliquam eu accumsan orci.</html>");

			JLabel textJ1 = new JLabel(text1);
			textJ1.setBorder(border1);
			textJ1.setFont(customFont);
			page1.add(textJ1);
			//add words to page
			pages.add(page1);
			
			//page 2
			JPanel page2 = new JPanel();
			page2.setOpaque(false);
			EmptyBorder border2	 = new EmptyBorder(100, 0, 150, 100);
			BoxLayout Layout_2 = new BoxLayout(page2,BoxLayout.Y_AXIS);
			page2.setLayout(Layout_2);
			 
			//paragraph
			String text2 = ("<html>&nbsp;&nbsp;&nbsp;&nbsp;Nullam metus diam, hendrerit non eleifend vel, " +
					"ullamcorper faucibus ipsum. Nulla condimentum turpis a arcu " +
					"tincidunt, mattis sodales arcu suscipit. Vivamus ut mattis turpis, " +
					"at porta libero. Vestibulum ante ipsum primis in faucibus orci luctus " +
					"et ultrices posuere cubilia Curae; Morbi lobortis consectetur quam. " +
					"Sed vel sem est. Ut quis lacinia mi, ut iaculis tortor. Quisque " +
					"lacinia tortor ut enim tempus, a ultricies orci cursus. Aenean " +
					"consectetur ex vitae bibendum congue. Etiam consectetur posuere tortor, " +
					"ut maximus est scelerisque sit amet. Ut scelerisque sodales nulla sit amet " +
					"mollis. In condimentum risus eu tincidunt molestie. Proin sit amet interdum " +
					"urna, et varius nibh. Praesent ut pellentesque quam. Sed lacinia blandit " +
					"velit, sed interdum purus egestas eget.Donec vel sollicitudin mauris. " +
					"In tempor magna ipsum, nec luctus est facilisis in. Aenean scelerisque, " +
					"dolor consectetur feugiat tempus, felis nulla ornare dolor, lobortis " +
					"aliquam dui turpis sed diam. Etiam nec est ipsum. Nulla malesuada " +
					"faucibus blandit. Integer eget tempus erat, et imperdiet lacus. Maecenas " +
					"erat lorem, aliquet tincidunt odio sed, hendrerit dignissim enim. Quisque " +
					"ac libero quis diam suscipit bibendum sed blandit ipsum. Phasellus cursus " +
					"odio ex, sed fringilla lorem porttitor condimentum. Maecenas at nibh vitae " +
					"nisl rhoncus eleifend in sit amet sem.</html>");
			
			JLabel textJ2 = new JLabel(text2);
			textJ2.setBorder(border2);
			textJ2.setFont(customFont);
			page2.add(textJ2);
			
			pages.add(page2);
			
			//page 3
			JPanel page3 = new JPanel();
			page3.setOpaque(false);
			EmptyBorder border3 = new EmptyBorder(75, 125, 50, 100);
			BoxLayout Layout_3 = new BoxLayout(page3,BoxLayout.Y_AXIS);
			page3.setLayout(Layout_3);
			
			String text3 = ("<html>&nbsp;&nbsp;&nbsp;&nbsp;Lorem ipsum dolor sit amet, consectetur adipiscing elit.<br>" +
					"Sed lectus mi, vestibulum quis nunc eget, dapibus pulvinar " +
					"Nunc in vestibulum ligula, id dapibus augue. In commodo " +
					"pretium orci nec finibus. Aliquam dictum suscipit augue, " +
					"quis euismod dui aliquet sit amet. Curabitur interdum commodo " +
					"pellentesque. Integer cursus consequat orci, a bibendum nisl " +
					"venenatis sit amet. Suspendisse dictum suscipit tincidunt. " +
					"Maecenas porttitor elit ut tortor vehicula, non elementum dui " +
					"scelerisque. Proin at eleifend sapien. Praesent imperdiet, " +
					"elit a luctus ullamcorper, velit diam lobortis nisl, ac " +
					"facilisis mi purus a purus. Ut euismod ultricies magna. Etiam " +
					"aliquet risus aliquam ipsum vehicula, vel commodo magna facilisis. " +
					"Donec id sem vitae diam mollis scelerisque at id turpis. " +
					"Phasellus libero eros, cursus eget rutrum nec, cursus id " +
					"tortor. Aliquam eu accumsan orci.</html>");

			JLabel textJ3 = new JLabel(text3);
			textJ3.setBorder(border3);
			textJ3.setFont(customFont);
			page3.add(textJ3);
			pages.add(page3);
			
			//page 4
			JPanel page4 = new JPanel();
			page4.setOpaque(false);
			EmptyBorder border4 = new EmptyBorder(100, 0, 150, 100);
			BoxLayout Layout_4 = new BoxLayout(page4,BoxLayout.Y_AXIS);
			page4.setLayout(Layout_4);
			 
			//paragraph
			String text4 = ("<html>&nbsp;&nbsp;&nbsp;&nbsp;Class aptent taciti sociosqu ad litora " +
					"torquent per conubia nostra, per inceptos himenaeos. Orci varius " +
					"natoque penatibus et magnis dis parturient montes, nascetur ridiculus " +
					"mus. Aliquam sed consequat dolor. Donec volutpat cursus ipsum vitae eleifend. " +
					"Nullam dictum maximus tellus, vitae ultrices lacus tristique quis. Curabitur " +
					"accumsan volutpat neque nec euismod. Sed elementum volutpat dolor nec molestie. " +
					"Etiam aliquet mauris in ante dictum, quis mollis turpis tempor. Integer elit sem, " +
					"mattis nec interdum vitae, interdum at ipsum. Sed eu tempor tortor.</html>");
			
			JLabel textJ4 = new JLabel(text4);
			textJ4.setBorder(border4);
			textJ4.setFont(customFont);
			page4.add(textJ4);
			
			pages.add(page4);
				
		}
		//font exception
		} catch (Exception e) {
		    e.printStackTrace();
		}
		
		int i =0;
		for(JPanel p: pages)
		{
			i++;
			if(i>1&i!=pages.size())
			{
				p.addMouseListener(this);
			}
			
			if(i%2==0)
				evenPages.add(p);
			else
				oddPages.add(p);
		}
		
	}
	public class JPanelB extends JPanel {

		  /**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private Image backgroundImage;
		  
		  public JPanelB(int id)
		  {
			  super();
			  backgroundImage = il.get(id);
		  }

		  public void paintComponent(Graphics g) 
		  {
		    super.paintComponent(g);
		    g.drawImage(backgroundImage, 0, 0, this);
		  }
		  
		}
	public void mouseClicked(MouseEvent e) 
	{
		int i =0;
		for(JPanel p:pages)
		{
			i++;
			if(e.getSource().equals(p))
			{
				if(i%2==0)
				{
					CardLayout cl = (CardLayout) evenPages.getLayout();
					cl.next(evenPages);
					CardLayout clo = (CardLayout) oddPages.getLayout();
					clo.next(oddPages);
				}
				else
				{
					CardLayout cl = (CardLayout) evenPages.getLayout();
					cl.previous(evenPages);
					CardLayout clo = (CardLayout) oddPages.getLayout();
					clo.previous(oddPages);
				}
			}
		}
		
	}
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
