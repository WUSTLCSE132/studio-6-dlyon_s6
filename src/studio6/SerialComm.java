package studio6;

import jssc.*;

public class SerialComm {

	SerialPort port;

	private boolean debug;  // Indicator of "debugging mode"
	
	// This function can be called to enable or disable "debugging mode"
	void setDebug(boolean mode) {
		debug = mode;
	}	
	

	// Constructor for the SerialComm class
	public SerialComm(String name) throws SerialPortException {
		port = new SerialPort(name);		
		port.openPort();
		port.setParams(SerialPort.BAUDRATE_9600,
			SerialPort.DATABITS_8,
			SerialPort.STOPBITS_1,
			SerialPort.PARITY_NONE);
		
		debug = false; // Default is to NOT be in debug mode
	}
		
	// TODO: Add writeByte() method from Studio 5
	public boolean writeByte(byte singleByte) {
		if(debug) {
			System.out.print("<0x");
			System.out.print(Integer.toHexString(singleByte));
			System.out.println(">");
		}
		try {
			port.writeByte(singleByte);
			return true;
		} catch (SerialPortException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	// TODO: Add available() method
	public boolean available() throws SerialPortException {
		return(port.getInputBufferBytesCount() != 0);
	}
	// TODO: Add readByte() method	
	public byte readByte() throws SerialPortException {
		byte myByte = port.readBytes(1)[0];
		if(debug) {
			if(myByte!=10&&myByte!=13) {
				System.out.println();
				System.out.print("<0x");
				System.out.print(Integer.toHexString(myByte));
				System.out.println(">");
			}
		}
				
		return(myByte);
	}
	// TODO: Add a main() method
	public static void main(String[] args) throws SerialPortException {
		SerialComm myPort = new SerialComm("COM3");
		while(true) {
			
			if(myPort.available()) {
				System.out.print((char) myPort.readByte());
			}
			
		}
		
	}
}
