package Controller;

import ArduinoCommunication.Arduino2;
import Model.IModel;
import Model.Model;
import Ordre.Order;
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
		this.view = new View(model, this);
		this.model.AddObserver(this.view);
	}
	
	public void run() throws InterruptedException
	{
		for(int j = 1; j<4;j++)
		for(int i = 0; i<20;i++)
		{
			//this.model.setText(arduino.getReadedValue());
			//int a = 1;
			//a >>= 7;
			//this.arduino.serialWrite((i*10));
			int a = 1;
			a >>= 7;
			int[] o = { 7, 1 };
			//System.out.println("envoie : " + Order.OrdreToSend(o, Order.ORDRE_TYPE_CONSIGNE));
			this.arduino.serialWrite(Order.OrdreToSend(o));
			Thread.sleep(1000);
			String reception = this.arduino.serialRead();
			this.model.TranslateDataFromArduino(reception);
			this.model.NotifyObserver();
			System.out.println(reception);
			//this.model.setText(this.arduino.serialRead());
			//this.model.NotifyObserver();
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

	@Override
	public void ClickCan() {
		this.model.SetCan();
	}

	@Override
	public void PressChangeConsigne(Boolean more_less) {
		if(more_less) this.model.PressedIncrementConsigne();
		else this.model.PressedDecrementConsigne();
	}

	@Override
	public void ClickChangeConsigne(Boolean more_less) {
		if(!more_less) this.model.IncrementConsigne();
		else this.model.DecrementConsigne();
	}	
	
	@Override
	public void ModeChange(int mode)
	{
		this.model.SetMode(mode);
	}

}
