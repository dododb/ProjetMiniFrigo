package Controller;

public interface IController {

	void run() throws InterruptedException;
	void GetDataFromArduino() throws InterruptedException;
	void SendDataToArduino();
}