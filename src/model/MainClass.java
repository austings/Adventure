package model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JScrollPane;

import menu.Backpack;
import menu.Journal;
import menu.MainMenu;
import menu.Screen;

public class MainClass 
{
	
	public void start()
	{
		MainMenu mm = new MainMenu();
		
	}
	
	public static void main(String[] args)
	{
		MainClass c = new MainClass();

		c.start();
	}
}
