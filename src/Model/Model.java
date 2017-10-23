package Model;

import java.util.Observable;
import java.util.Observer;

public class Model extends Observable implements IModel {

	String text = "";
	public Model()
	{
		
	}

	@Override
	public void AddObserver(Observer o) {
		this.addObserver(o);
	}
	
	public String getText()
	{
		return this.text;
	}
	
	public void setText(String text)
	{
		this.text = text;
		this.setChanged();
	}

	@Override
	public void NotifyObserver() {
		this.notifyObservers();
	}
}
