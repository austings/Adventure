package library;

import java.util.HashMap;

import model.TileThing;

public class ItemLibrary 
{
	private int id;
	private String itemName;
	
	//armor
	private TileThing ironHelm;
	private TileThing steelHelm;
	
	//clothes
	private TileThing fineRBlouse;
	private TileThing bandana;
	private TileThing lglove;
	
	//books
	private TileThing testBook;
	
	//body parts
	private TileThing pelt;
	private TileThing eye;
	private TileThing heart;
	private TileThing liver;
	private TileThing claw;
	private TileThing brain;
	private HashMap<String, TileThing> hm = new HashMap<String, TileThing>(2000);
	
	public ItemLibrary()
	{
		createAllItems();
	}

	public void createAllItems()
	{
		createArmor();
		createClothes();
		createBooks();
		createBodyParts();
	}
	
	public void createArmor()
	{
		ironHelm = new TileThing("Iron Helmet");
		ironHelm.setValue(100);
		ironHelm.setEquippable("helmet");
		ironHelm.setImageID(-62);
		ironHelm.setToolTipText("An iron helmet is commonly used by the pauper classes of society. <br>"+
								"Most were crafted years ago and have aged with rust.");
		hm.put("Iron Helmet", ironHelm);
		
		steelHelm = new TileThing("Steel Helmet");
		steelHelm.setEquippable("helmet");
		steelHelm.setValue(300);
		steelHelm.setToolTipText("The steel helmet is finely polished and ideal for most combat situations.");
		hm.put("Steel Helmet",steelHelm );
		
	}
	
	public void createClothes()
	{
		fineRBlouse = new TileThing("Fine Red Blouse");
		fineRBlouse.setEquippable("shirt");
		fineRBlouse.setValue(150);
		fineRBlouse.setToolTipText("A satin, gender neutral red blouse.");
		hm.put("Fine Red Blouse", fineRBlouse);
		
		bandana = new TileThing("Bandana");
		bandana.setValue(25);
		bandana.setImageID(-63);
		bandana.setEquippable("face");
		bandana.setToolTipText("Typical of many highwaymen, this bandana is very useful in <br>"+
							   "concealing your face.");
		hm.put("Bandana", bandana);
		
		lglove = new TileThing("Leather Gloves");
		lglove.setEquippable("glove");
		lglove.setValue(80);
		lglove.setToolTipText("These firm leather gloves are accented by interwoven threads of <br>"+
							  "cloth for extra flexibility.");
		hm.put("Leather Gloves",lglove);
	}
	
	public void createBodyParts()
	{
		 pelt = new TileThing("Pelt");
		 pelt.setToolTipText("Pelts are skins of slain animals. They are incredibly useful in <br>"+
				 			 "crafting a wide variety of objects from blankets and coats to armors<br> "+
				 			 "and shields.");
		 hm.put("Pelt", pelt);
		 eye = new TileThing("Eye");
		 eye.setToolTipText("The eye is the organ responsible for sight in most animals. They require<br>"+
				 			"a large amount of preparation to be properly cooked, and due to local <br>"+
				 			"superstitions surrounding them, are not commonly consumed by the populace.");
		 hm.put("Eye", eye);
		 heart = new TileThing("Heart");
		 heart.setToolTipText("The heart is the organ responsible for pumping blood throughout the body.<br>"+
				 			  "It was once believed that eating the hearts of one's enemies would allow <br>"+
				 			  "one to gain their strength.");
		 hm.put("Heart", heart);
		 liver = new TileThing("Liver");
		 liver.setToolTipText("The liver detoxifies and metabolizes minerals in the body.");
		 hm.put("Liver", liver);
		 claw = new TileThing("Claw");
		 claw.setToolTipText("A claw is commonly used as a defense mechanism by many creatures of the wilds.<br>"+
		 					 "They can also be used to cut meat for easier consumption.");
		 hm.put("Claw", claw);
		 brain = new TileThing("Brain");
		 brain.setToolTipText("The brain controls all motor and neural functions of an animal.");
		 hm.put("Brain", brain);
	}
	
	public void createBooks()
	{
		testBook = new TileThing("Deremor Vol I");
		testBook.setReadable("book");
		testBook.setValue(500);
		testBook.setToolTipText("This book contains the history of the world. Apparently it comes<br> "+
							    "in many volumes");
		hm.put( "Deremor Vol I",testBook);
	}
	
	public TileThing getItem(String key)
	{
		return hm.get(key);
	}
	
	public TileThing getBodyPart(String key, String animal, int value)
	{
		TileThing corpse = hm.get(key);
		corpse.setName(animal+" "+key);
		corpse.setValue(value);
		//corpse.setToolTipText(hm.get(key).getToolTipText().toString()+"<br>This one belongs to a "+animal+".");
		return corpse;
	}
	
	
	public TileThing[] getCommonEquipment()
	{
		TileThing[] j = new TileThing[5];
		int i =0;
		//Add Armor Items
		
		j[i] = ironHelm;
		i++;
		
		j[i] = steelHelm;
		i++;
		
		j[i] = fineRBlouse;
		i++;
		
		j[i] = bandana;
		i++;
		
		j[i] = lglove;
		i++;
		
		
		return j;
	}
	
	public TileThing[] getCommonBook()
	{
		TileThing[] j = new TileThing[1];
		int i =0;
		//Add Books
		
		j[i] = testBook;
		i++;
		return j;
	}
	
}
