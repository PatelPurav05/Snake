import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.File;
import java.io.FileInputStream;

import javax.imageio.ImageIO;


public class Images 
{
		BufferedImage head; {
		try {
			File file = new File("/C:/Users/purav/eclipse-workspace/Snake/snakehead.png");
			FileInputStream fis = new FileInputStream(file);
			head = ImageIO.read(fis);
		} catch (IOException e) {
			System.err.println(e);
		}
		}
		BufferedImage back; {
			try {
				File file = new File("C:/Users/purav/eclipse-workspace/Snake/SnakeBackground.jpg");
				FileInputStream fis = new FileInputStream(file);
				back = ImageIO.read(fis);
			} catch (IOException e) {
				System.err.println(e);
			}
			}
		BufferedImage apple; {
			try {
				File file = new File("C:/Users/purav/eclipse-workspace/Snake/2076366.png");
				FileInputStream fis = new FileInputStream(file);
				apple = ImageIO.read(fis);
			} catch (IOException e) {
				System.err.println(e);
			}
			}

}
