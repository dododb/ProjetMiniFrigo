package Model;

import java.awt.Image;

public class Can {
	private Boolean can; // 0 - Off | 1 - On
	private Pair images;
	
	public Can()
	{
		this.can = false;
		this.images = new Pair("D:/Exia CESI/A3/Projet/Projet PMF/can");
	}
	
	public void SetCanB(Boolean can)
	{
		this.can = can;
	}
	
	public Boolean GetCanB()
	{
		return this.can;
	}
	
	public Image GetCan()
	{
		return this.images.getImage(this.can);
	}

}
