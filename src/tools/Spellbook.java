package tools;

import java.util.ArrayList;

import library.ImageLibrary;
import model.TileNode;
import model.TileThing;
import people.Creature;
import people.Player;

/*
 * Class for drawing spells
 */
public class Spellbook extends TileThing
{
	private ArrayList<Spell> book;
	private ImageLibrary il = new ImageLibrary();
	private TileNode[][]worldView = null;
	private Creature owner;
	
	/*
	 * Collection of spells for each character
	 */
	public Spellbook(String name, Creature c)
	{
		super(name);
		this.owner = c;
		if(owner instanceof Player)
		{
			book = new ArrayList<Spell>();
			book.add(new Spell("Fireball",-600));
		}
	}
	
}
