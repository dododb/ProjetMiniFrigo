package Controller;

import ArduinoCommunication.Arduino;
import ArduinoCommunication.IArduino;
import Model.IModel;
import Model.Model;
import View.IView;
import View.View;

public class Controller implements IController {

	IModel model;
	IView view;
	IArduino arduino;
	public Controller()
	{
		this.arduino = new Arduino();
		this.model = new Model();
		this.view = new View(model);
		this.model.AddObserver(this.view);
	}
	
	public void run() throws InterruptedException
	{
		for(int i = 0; i<10;i++)
		{
			//this.model.setText(arduino.getReadedValue());
			this.model.NotifyObserver();
			this.GetDataFromArduino();
			Thread.sleep(1000);
		}
	}
	
	public void GetDataFromArduino() throws InterruptedException
	{
		Thread t=new Thread()
		{
			public synchronized void run() {
			//the following line will keep this app alive for 1000 seconds,
			//waiting for events to occur and responding to them (printing incoming messages to console).
			try {Thread.sleep(1000000);} catch (InterruptedException ie) { notify();
			}
			notify();
			}
		};
		System.out.println("test");
		t.start();
		System.out.println("test");
		t.wait();
		
		String data  = arduino.getReadedValue();
		System.out.println("test : " + data);
		//traitement de la string reçu
	}
	
	public void SendDataToArduino()
	{
		
	}	
}
