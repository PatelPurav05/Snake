import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.ImageObserver;
import java.util.ArrayList;
import utilities.GDV5;

public class Slither {
 ArrayList<BodyPart> sbody = new ArrayList<BodyPart>();
 Images i = new Images();
 static int powerIncrement = 0;
 int applex = 0; 
 int appley = 0;
 int tAdd = 0;
 int prevx = 0;
 static int lvlRate;
 static int ticks;
 int prevy = 0;
 boolean up = false;
 boolean down = false;
 boolean right = false;
 boolean left = true;
 boolean Collision = false;
 static int eaten = 0;
 Apple apple = new Apple(applex,appley);
 
 
 public Slither(int x, int y) 
 {
	 int num = 0;
	 while(num<3) 
	 {
		 sbody.add(new BodyPart(x+(num*20),y));
		 num++;
	 }
 }
 public void update() 
 {
	tAdd++;
	if (sbody.size()==1600) 
	{
		Snake.win=true;
	}
	if (sbody.get(0).intersects(apple)) 
	{
		Collision();
	}
	if (!Snake.goThrough) {
		for (int i = 0; i<sbody.size();i++) 
		{
			for (int n = i+1; n<sbody.size();n++) 
			{
				if (sbody.get(i).intersects(sbody.get(n))) 
				{
						Snake.lose = true;
				}
			}
		}
	}
	if (Snake.goThrough) 
	{
		ticks++;
		if (ticks==1000) 
		{
			Snake.goThrough = false;
			powerIncrement=0;
			ticks=0;
		}
	}



	if ((sbody.get(0).x==-20)||(sbody.get(0).x==820)||(sbody.get(0).y==-20)||(sbody.get(0).y==820)) 
	Snake.lose = true;
	
	if (tAdd==lvlRate) 
	{
		int prevx = sbody.get(0).x;
		int prevy = sbody.get(0).y;
		for (int i=1; i<sbody.size();i++) 
		{
			int currx = sbody.get(i).x;
			int curry = sbody.get(i).y;
			sbody.get(i).x=prevx;
			sbody.get(i).y=prevy;
			prevx = currx;
			prevy = curry;
		}
			if (up) 
			{
				sbody.get(0).translate(0,-20);
			}
			if (down) 
			{
				sbody.get(0).translate(0,20);
			}
			if (left) 
			{
				sbody.get(0).translate(-20,0);
			}
			
			if (right) 
			{	
				sbody.get(0).translate(20,0);
			}
	
		tAdd  = 0;
	}
 
}
 public void draw(Graphics2D win) 
 {
	win.drawImage(i.head,sbody.get(0).x,sbody.get(0).y,20,20, null);
	for (int i = 1; i<sbody.size(); i++) 
	{
		sbody.get(i).drawBody(win);
	}
	apple.drawApple(win);
	
 }
 public void Collision() 
 {
	
		Snake.s1.play(0);
		eaten++;
		sbody.add(new BodyPart((sbody.get(sbody.size()-1).x)+20,(sbody.get(sbody.size()-1).y+20)));
		applex = (int)(Math.random()*26)*20;
		appley= (int)(Math.random()*26)*20;
		apple = new Apple (applex,appley);
		powerIncrement+=10;
	 
 }
 public void keys() 
 {
	 if((GDV5.KeysPressed[KeyEvent.VK_W])&&down==false) 
	 {
		 up = true;
		 down = false;
		 right = false;
		 left = false;
	 }
	 if(GDV5.KeysPressed[KeyEvent.VK_S]&&up==false) 
	 {
		 down = true;
		 up = false;
		 right = false;
		 left = false;
	 }
	 if(GDV5.KeysPressed[KeyEvent.VK_D]&&left==false) 
	 {
		 right = true;
		 up = false;
		 down = false;
		 left = false;
	 }
	 if(GDV5.KeysPressed[KeyEvent.VK_A]&&right==false) 
	 {
		 left = true;
		 right = false;
		 up = false;
		 down = false;
	 }
 }
 
}
