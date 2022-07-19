import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;

public class BodyPart extends Rectangle 
{
	public BodyPart(int x, int y)
	{
		super(x,y,20,20);
	}
	public void drawBody(Graphics2D win) 
	{
		win.setColor(Color.GREEN);
		win.draw(this);
//		win.setColor(Color.green);
//		win.fillOval(this.x,this.y,this.height,this.width);
	}
}
