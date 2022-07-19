import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;

import utilities.GDV5;

public class Apple extends Rectangle 
{
	Images i = new Images();
	public Apple(int x, int y) 
	{
		super(x,y,20,20);
	}
	public void drawApple(Graphics2D win) 
	{
		win.drawImage(i.apple,this.x,this.y,20,20, null);
//		win.setColor(Color.red);
//		win.draw(this);
//		win.fill(this);
	}
	
}
