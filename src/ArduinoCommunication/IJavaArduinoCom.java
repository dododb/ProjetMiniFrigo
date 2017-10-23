package ArduinoCommunication;

import gnu.io.SerialPortEvent;

public interface IArduino {

	void initialize();

	void close();

	void serialEvent(SerialPortEvent oEvent);

	boolean getPort();

	String getReadedValue();

}