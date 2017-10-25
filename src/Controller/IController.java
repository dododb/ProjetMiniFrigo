package Controller;

public interface IController{

	void run() throws InterruptedException;
	void GetDataFromArduino() throws InterruptedException;
	void SendDataToArduino();
	
	void ClickCan();
	void PressChangeConsigne(Boolean more_less);
	void ClickChangeConsigne(Boolean more_less);
	public void ModeChange(int mode);
}