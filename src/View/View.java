package View;

import java.util.Observable;
import java.util.Observer;

import Model.IModel;

public class View implements IView {
	
	IModel model;
	public View(IModel model)
	{
		this.model = model;
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		System.out.println(this.model.getText());
		
		
	}
	
	public Observer GetObserver()
	{
		return this;
	}
}
