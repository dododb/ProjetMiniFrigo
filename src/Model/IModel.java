package Model;

import java.util.Observer;

public interface IModel {

	public void AddObserver(Observer o);
	void setText(String text);
	String getText();
	void NotifyObserver();
}