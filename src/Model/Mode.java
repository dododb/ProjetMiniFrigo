package Model;

import java.awt.Image;
import java.util.List;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class Mode {
	private int activeMode; // 0 - Off | 1 - Active | 2 - Stand By
	private List<Pair> images = new ArrayList<Pair>(); 
	
	public Mode() {
		this.activeMode = 0;
		this.images.add(new Pair("Projet PMF/off"));
		this.images.add(new Pair("Projet PMF/cold"));
		this.images.add(new Pair("Projet PMF/sleep"));
	}
	
	public Image GetImageMode(int mode)
	{
		if(this.activeMode == mode) return this.images.get(mode).getImage(true);
		else return this.images.get(mode).getImage(false);
	}
	
	public void SetMode(int mode)
	{
		if(mode <= 2 && mode >= 0)
		{
			this.activeMode = mode;
		}
	}
	
	public int GetMode()
	{
		return this.activeMode;
	}
}


class Pair
{
	private Image img1;
	private Image img2;
	
	public Pair(String path)
	{
		try 
		{
			this.img1 = ImageIO.read(new File(path + "1.png"));
			this.img2 = ImageIO.read(new File(path + "2.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public Image getImage(Boolean active)
	{
		if(active) return this.img1;
		else return this.img2;
	}
}