package Model;

import java.awt.Image;

public class ChangeConsigne {
	private Pair more;
	private Pair less;
	private Boolean moreStatus;
	private Boolean lessStatus;
	
	public ChangeConsigne()
	{
		this.moreStatus = true;
		this.lessStatus = true;
		this.more = new Pair("D:/Exia CESI/A3/Projet/Projet PMF/more");
		this.less = new Pair("D:/Exia CESI/A3/Projet/Projet PMF/less");
	}
	
	public Image GetImage(Boolean more_less)
	{
		if(more_less) return this.less.getImage(lessStatus);
		else return this.more.getImage(moreStatus);
	}
	
	public void SetMoreStatus()
	{
		this.moreStatus = !this.moreStatus;
	}
	
	public void SetLessStatus()
	{
		this.lessStatus = !this.lessStatus;
	}
}
