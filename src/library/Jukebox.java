package library;

import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import sun.audio.*;

public class Jukebox
{
	private Clip clip;
	private AudioInputStream ais;
	
	
	public Jukebox()
	{
		try {
			clip = AudioSystem.getClip();
			ais= AudioSystem.getAudioInputStream(this.getClass().getResourceAsStream("/resources/sounds/MainTheme.wav"));
			clip.open(ais);
			clip.loop(Clip.LOOP_CONTINUOUSLY);
			SwingUtilities.invokeLater(new Runnable() {
				public void run()
				{
				}
			});
			
			
			
		} catch (LineUnavailableException| UnsupportedAudioFileException | IOException e) {

			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			JDialog j = new JDialog();
			j.add(new JLabel("No audio device detected."));
			j.setLocationRelativeTo(null);
			j.setVisible(true);
		}

	}
	
	public void playVillageSong()
	{
		clip.close();
		try {
			clip = AudioSystem.getClip();
			ais = AudioSystem.getAudioInputStream(this.getClass().getResourceAsStream("/resources/sounds/travel.wav"));
			clip.open(ais);
			clip.loop(Clip.LOOP_CONTINUOUSLY);
			SwingUtilities.invokeLater(new Runnable() {
				public void run()
				{
				}
			});
		} catch (UnsupportedAudioFileException |LineUnavailableException| IOException e) {
			e.printStackTrace();
		} 
	}
	
	
	
	
	
	/*
	@SuppressWarnings({ "restriction" })
	public  void playMusic()
	{
		AudioPlayer mgp= AudioPlayer.player;
		AudioStream bgm;
		AudioData md;
		ContinuousAudioDataStream loop = null;
		try {
			bgm = new AudioStream(this.getClass().getResourceAsStream("/resources/sounds/menu.wav"));
			md = bgm.getData();
			loop= new ContinuousAudioDataStream(md);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		mgp.start(loop);
	}*/

}
