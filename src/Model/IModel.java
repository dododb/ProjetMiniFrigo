package Model;

import java.util.Observer;

public interface IModel {

	public void AddObserver(Observer o);
	void NotifyObserver();
	
	public Mode GetMode();
	public Can GetCan();
	public ChangeConsigne GetChangeConsigne();
	
	public Temp GettempConsigne();
	public Temp GettempCan();
	public Temp GettempFridge();
	public Temp GettempModule();
	
	public void SettempConsigne(float temp);
	public void SettempCan(float temp);
	public void SettempFridge(float temp);
	public void SettempModule(float temp);
	
	public void IncrementConsigne();
	public void DecrementConsigne();
	public void PressedIncrementConsigne();
	public void PressedDecrementConsigne();

	public void TranslateDataFromArduino(String data);
	
	public void SetCan();
	public void SetMode(int mode);
}