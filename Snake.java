import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import utilities.GDV5;
import utilities.SoundDriverHo;

public class Snake extends GDV5 {
	Images i = new Images();
	int gridx = 0;
	int gridy = 0;
	Slither s = new Slither(GDV5.getMaxWindowX()/2,GDV5.getMaxWindowY()/2);
	ArrayList<Rectangle> r = new ArrayList <Rectangle>();
	boolean settings = false;
	boolean splash = true;
	boolean easy = false;
	boolean medium = false;
	boolean hard = false;
	boolean wasEasy = false;
	boolean wasMedium = false;
	boolean wasHard = false;
	static boolean win = false;
	static boolean lose = false; 
	static boolean goThrough = false;
	static SoundDriverHo s1;
	String[] filenames = new String[2];
	String audioFilePath;
	File audioFile = null;
	public Snake() 
	{
		filenames[0] = "chomp.wav";
		filenames[1] = "back.wav";
		s1 = new SoundDriverHo (filenames,this);
		setFrames(120);
		setTitle("Snake");
	}	
	
	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		Snake snake =null;
		try 
		{		
			snake = new Snake();
			snake.start();
		}
		catch(Exception ex) 
		{
			System.out.println("Main Method " + ex.getMessage());
		}
		finally {
			snake = null;
		}
		s1.loop(1);

	}

		
	public void keysPressed() {
		if (GDV5.KeysTyped[KeyEvent.VK_1]&&(splash==true||settings==true))
		{
			splash = false;
			settings = false;
			easy = true;
			Slither.lvlRate = 30;
			lose = false;
		}
		if (GDV5.KeysTyped[KeyEvent.VK_2]&&(splash==true||settings==true)) 
		{
			settings = false;
			splash = false;
			medium = true;
			Slither.lvlRate = 15;
			lose = false;
		}		
		if (GDV5.KeysTyped[KeyEvent.VK_3]&&(splash==true||settings==true)) 
		{
			settings = false;
			splash = false;
			hard = true;
			Slither.lvlRate = 7;
			lose = false;
		}
		if (GDV5.KeysPressed[KeyEvent.VK_ESCAPE]) 
		{
			settings = false;
			splash = true;
			easy = false;
			medium = false;
			hard = false;
			lose = false;
		}
		if (GDV5.KeysPressed[KeyEvent.VK_SPACE]&&(easy==true||medium==true||hard==true)&&Slither.powerIncrement>=100) 
		{
			goThrough = true;
		}
		if (GDV5.KeysPressed[KeyEvent.VK_ENTER]&&lose==true) 
		{
			lose = false;
			if (wasEasy)
			{
				easy = true;
				wasEasy = false;
			}
			if (wasMedium)
			{
				medium = true;
				wasMedium = false;
			}
			if (wasHard)
			{
				hard = true;
				wasHard = false;
			}
		}
		if (GDV5.KeysPressed[KeyEvent.VK_Q]&&splash==true) 
		{
			settings = true;
			splash = false;
		}
	}
	@Override
	public void update() 
	{
		// TODO Auto-generated method stub {
		keysPressed();
		if (easy) 
		{
		s.update();
		s.keys();
		}
		if (medium) 
		{
		s.update();
		s.keys();
		}
		if (hard) 
		{
		s.update();
		s.keys();
		}
		
	}

	@Override
	public void draw(Graphics2D win) 
	{
		if (splash) {
			win.setColor(Color.DARK_GRAY);
			win.fillRect(0, 0, 800, 800);
			win.setColor(Color.blue);
			win.setFont(new Font("Courier",Font.ITALIC,80));
			win.drawString("Purav Patel", 135, 200);
			win.setColor(Color.cyan);
			win.setFont(new Font("Courier",Font.ITALIC,30));
			win.drawString("Press 1 for Easy", 135, 300);
			win.drawString("Press 2 for Medium", 135, 400);
			win.drawString("Press 3 for Hard", 135, 500);
			win.drawString("Press Q for Settings", 135, 600);
		}
		if (settings) 
		{
			win.setColor(Color.DARK_GRAY);
			win.fillRect(0, 0, 800, 800);
			win.setColor(Color.cyan);
			win.setFont(new Font("Courier",Font.ITALIC,45));
			win.drawString("OBJECTIVE: eat as many apples", 0, 40);
			win.drawString(" as you can without dying", 50, 80);
			win.setFont(new Font("Courier",Font.ITALIC,30));
			win.drawString("Use WASD for snake movement", 135, 300);
			win.drawString("Press Space to activate the Powerup", 105, 400);
			win.setFont(new Font("Courier",Font.ITALIC,27));
			win.drawString("The Powerup will give you the ability to go ", 30, 500);
			win.drawString("through the snake until the timer reaches 1000", 30, 530);
		}
		if (easy) 
		{
			s.draw(win);
			win.setColor(Color.cyan);
			win.drawRect(25,500,15,180);
			if (Slither.powerIncrement>=10) {
					win.drawRect(25, 680, 15, 20);
					win.fillRect(25,680,15,20);
				}
			if (Slither.powerIncrement>=20) {
				win.drawRect(25, 660, 15, 20);
				win.fillRect(25,660,15,20);
			}
			if (Slither.powerIncrement>=30) {
				win.drawRect(25, 640,15, 20);
				win.fillRect(25,640,15,20);
			}
			if (Slither.powerIncrement>=40) {
				win.drawRect(25, 620, 15, 20);
				win.fillRect(25,620,15,20);
			}
			if (Slither.powerIncrement>=50) {
				win.drawRect(25, 600, 15, 20);
				win.fillRect(25,600,15,20);
			}
			if (Slither.powerIncrement>=60) {
				win.drawRect(25, 580, 15, 20);
				win.fillRect(25,580,15,20);
			}
			if (Slither.powerIncrement>=70) {
				win.drawRect(25, 560, 15, 20);
				win.fillRect(25,560,15,20);
			}
			if (Slither.powerIncrement>=80) {
				win.drawRect(25, 540, 15, 20);
				win.fillRect(25,540,15,20);
			}
			if (Slither.powerIncrement>=90) {
				win.drawRect(25, 520, 15, 20);
				win.fillRect(25,520,15,20);
			}
			if (Slither.powerIncrement>=100) {
				win.drawRect(25, 500, 15, 20);
				win.fillRect(25,500,15,20);
			}
			win.setColor(Color.white); 
			win.setFont(new Font("Courier",Font.ITALIC,30));
			win.drawString((Slither.eaten*10) + "", 50, 30);
			if (goThrough) {
				win.setColor(Color.white); 
				win.setFont(new Font("Courier",Font.ITALIC,30));
				win.drawString((Slither.ticks) + "", GDV5.getMaxWindowX()-70, 30);
			}
		}
		if (medium) 
		{
			s.draw(win);
			win.setColor(Color.cyan);
			win.drawRect(25,500,15,180);
			if (Slither.powerIncrement>=10) {
					win.drawRect(25, 680, 15, 20);
					win.fillRect(25,680,15,20);
				}
			if (Slither.powerIncrement>=20) {
				win.drawRect(25, 660, 15, 20);
				win.fillRect(25,660,15,20);
			}
			if (Slither.powerIncrement>=30) {
				win.drawRect(25, 640,15, 20);
				win.fillRect(25,640,15,20);
			}
			if (Slither.powerIncrement>=40) {
				win.drawRect(25, 620, 15, 20);
				win.fillRect(25,620,15,20);
			}
			if (Slither.powerIncrement>=50) {
				win.drawRect(25, 600, 15, 20);
				win.fillRect(25,600,15,20);
			}
			if (Slither.powerIncrement>=60) {
				win.drawRect(25, 580, 15, 20);
				win.fillRect(25,580,15,20);
			}
			if (Slither.powerIncrement>=70) {
				win.drawRect(25, 560, 15, 20);
				win.fillRect(25,560,15,20);
			}
			if (Slither.powerIncrement>=80) {
				win.drawRect(25, 540, 15, 20);
				win.fillRect(25,540,15,20);
			}
			if (Slither.powerIncrement>=90) {
				win.drawRect(25, 520, 15, 20);
				win.fillRect(25,520,15,20);
			}
			if (Slither.powerIncrement>=100) {
				win.drawRect(25, 500, 15, 20);
				win.fillRect(25,500,15,20);
			}
			win.setColor(Color.white); 
			win.setFont(new Font("Courier",Font.ITALIC,30));
			win.drawString((Slither.eaten*10) + "", 50, 30);
			if (goThrough) {
				win.setColor(Color.white); 
				win.setFont(new Font("Courier",Font.ITALIC,30));
				win.drawString((Slither.ticks) + "", GDV5.getMaxWindowX()-70, 30);
			}
		}
		if (hard) 
		{
			s.draw(win);
			win.setColor(Color.cyan);
			win.drawRect(25,500,15,180);
			if (Slither.powerIncrement>=10) {
					win.drawRect(25, 680, 15, 20);
					win.fillRect(25,680,15,20);
				}
			if (Slither.powerIncrement>=20) {
				win.drawRect(25, 660, 15, 20);
				win.fillRect(25,660,15,20);
			}
			if (Slither.powerIncrement>=30) {
				win.drawRect(25, 640,15, 20);
				win.fillRect(25,640,15,20);
			}
			if (Slither.powerIncrement>=40) {
				win.drawRect(25, 620, 15, 20);
				win.fillRect(25,620,15,20);
			}
			if (Slither.powerIncrement>=50) {
				win.drawRect(25, 600, 15, 20);
				win.fillRect(25,600,15,20);
			}
			if (Slither.powerIncrement>=60) {
				win.drawRect(25, 580, 15, 20);
				win.fillRect(25,580,15,20);
			}
			if (Slither.powerIncrement>=70) {
				win.drawRect(25, 560, 15, 20);
				win.fillRect(25,560,15,20);
			}
			if (Slither.powerIncrement>=80) {
				win.drawRect(25, 540, 15, 20);
				win.fillRect(25,540,15,20);
			}
			if (Slither.powerIncrement>=90) {
				win.drawRect(25, 520, 15, 20);
				win.fillRect(25,520,15,20);
			}
			if (Slither.powerIncrement>=100) {
				win.drawRect(25, 500, 15, 20);
				win.fillRect(25,500,15,20);
			}
			win.setColor(Color.white); 
			win.setFont(new Font("Courier",Font.ITALIC,30));
			win.drawString((Slither.eaten*10) + "", 50, 30);
			if (goThrough) {
				win.setColor(Color.white); 
				win.setFont(new Font("Courier",Font.ITALIC,30));
				win.drawString((Slither.ticks) + "", GDV5.getMaxWindowX()-70, 30);
			}
		}
		
	 	if (lose) 
	 	{ 
	 		if (easy) 
	 		{
	 			easy = false;
	 			wasEasy = true;
	 		}
	 		if (medium)
	 		{
	 			medium = false;
	 			wasMedium = true;
	 		}
	 		if (hard)
	 		{
	 			hard = false;
	 			wasHard = true;
	 		}
			goThrough = false; 
			Slither.eaten=0;
			win.setColor(Color.white); 
			win.setFont(new Font("Courier",Font.ITALIC,80));
			win.drawString("You Lose", 200, GDV5.getMaxWindowY()/2); 
			win.setFont(new Font("Courier",Font.ITALIC,30));
			win.drawString("Press Enter to Restart", 190, 450);
			resetGame();
		 }
	 	if (this.win) {
	 		win.setFont(new Font("Courier",Font.ITALIC,80));
			win.drawString("You Win", 200, GDV5.getMaxWindowY()/2); 
	 	}
	}
	
	void resetGame() 
	{
		s = new Slither (GDV5.getMaxWindowX()/2,GDV5.getMaxWindowY()/2);
		Slither.powerIncrement = 0;
	}

}
