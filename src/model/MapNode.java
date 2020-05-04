package model;

import java.awt.Dimension;

public class MapNode {
	private TileNode[][] worldScreen;
	private String theme;
	private String name;
	private Dimension position;
	
	
	public MapNode(TileNode[][] ws, String t, String n, int x, int y)
	{
		setWorldScreen(ws);
		setTheme(t);
		setName(n);
		setPosition(new Dimension(x,y));
	}
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the theme
	 */
	public String getTheme() {
		return theme;
	}
	/**
	 * @param theme the theme to set
	 */
	public void setTheme(String theme) {
		this.theme = theme;
	}
	/**
	 * @return the worldScreen
	 */
	public TileNode[][] getWorldScreen() {
		return worldScreen;
	}
	/**
	 * @param worldScreen the worldScreen to set
	 */
	public void setWorldScreen(TileNode[][] worldScreen) {
		this.worldScreen = worldScreen;
	}

	/**
	 * @return the position
	 */
	public Dimension getPosition() {
		return position;
	}

	/**
	 * @param position the position to set
	 */
	public void setPosition(Dimension position) {
		this.position = position;
	}
}
