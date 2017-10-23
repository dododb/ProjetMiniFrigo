package Controller;

import Model.IModel;
import Model.Model;
import View.IView;
import View.View;

public class Controller implements IController {

	IModel model;
	IView view;
	public Controller()
	{
		this.model = new Model();
		this.view = new View(model);
		this.model.AddObserver(this.view);
	}
	
	public void run() throws InterruptedException
	{
		for(int i = 0; i<10;i++)
		{
			this.model.setText("blabla");
			this.model.NotifyObserver();;
			Thread.sleep(1000);
		}
	}
	
}
