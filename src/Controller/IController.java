package Controller;

public interface IController {

	void run() throws InterruptedException;
	void GetDataFromArduino();
	void SendDataToArduino();
}