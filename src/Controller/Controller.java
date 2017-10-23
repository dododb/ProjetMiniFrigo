package Controller;

import ArduinoCommunication.Arduino2;
import Model.IModel;
import Model.Model;
import View.IView;
import View.View;
import arduino.Arduino;

public class Controller implements IController {

	IModel model;
	IView view;
	Arduino2 arduino;
	public Controller()
	{
		this.arduino = new Arduino2("", 9600);
		this.model = new Model();
		this.view = new View(model);
		this.model.AddObserver(this.view);
	}
	
	public void run() throws InterruptedException
	{
		for(int j = 0; j<20;j++)
		for(int i = 0; i<20;i++)
		{
			//this.model.setText(arduino.getReadedValue());
			int a = 1;
			a >>= 7;
			this.arduino.serialWrite((i*10));
			//this.model.setText(this.arduino.serialRead());
			this.model.NotifyObserver();
			//this.model.setText(this.arduino.serialRead());
			Thread.sleep(1000);
		}
	}
	
	public void GetDataFromArduino() throws InterruptedException
	{
		
	}
	
	public void SendDataToArduino()
	{
		
	}	
}
