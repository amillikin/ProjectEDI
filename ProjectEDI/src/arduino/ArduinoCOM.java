package arduino;

import java.awt.Dimension;
import java.io.PrintWriter;
import java.util.Scanner;

import com.fazecast.jSerialComm.SerialPort;

public class ArduinoCOM {
	private SerialPort comPort;
	private String portDescription;
	private int baud_rate;
	
	public ArduinoCOM(String portDescription) {
		//57600 baud rate assumed if not provided
		//Must have DTR enabled to prevent Ardunio from resetting when a connection is opened
		this.portDescription = portDescription;
		comPort = SerialPort.getCommPort(this.portDescription);
		this.baud_rate = 57600;
		comPort.setBaudRate(this.baud_rate);
		comPort.setFlowControl(SerialPort.FLOW_CONTROL_DTR_ENABLED|SerialPort.FLOW_CONTROL_DSR_ENABLED);
	}
	
	public ArduinoCOM(String portDescription, int baud_rate) {
		this.portDescription = portDescription;
		comPort = SerialPort.getCommPort(this.portDescription);
		this.baud_rate = baud_rate;
		comPort.setBaudRate(this.baud_rate);
		comPort.setFlowControl(SerialPort.FLOW_CONTROL_DTR_ENABLED|SerialPort.FLOW_CONTROL_DSR_ENABLED);
	}

	public boolean openConnection(){
		if(comPort.openPort()){
			try {Thread.sleep(100);} catch(Exception e){}
			return true;
		}
		else {
			AlertWindow alert = new AlertWindow(new Dimension(400,100),"Error Connecting", "Try Another port");
			alert.display();
			return false;
		}
	}
	
	public void closeConnection() {
		comPort.closePort();
	}
	
	public void setPortDescription(String portDescription){
		this.portDescription = portDescription;
		comPort = SerialPort.getCommPort(this.portDescription);
	}
	public void setBaudRate(int baud_rate){
		this.baud_rate = baud_rate;
		comPort.setBaudRate(this.baud_rate);
	}
	
	public String getPortDescription(){
		return portDescription;
	}
	
	public SerialPort getSerialPort(){
		return comPort;
	}	
	
	public String serialRead(){
		//will be an infinite loop if incoming data is not bound
		comPort.setComPortTimeouts(SerialPort.TIMEOUT_READ_SEMI_BLOCKING, 0, 0);
		String out="";
		Scanner in = new Scanner(comPort.getInputStream());
		try
		{
		   while(in.hasNext())
		      out += (in.next()+"\n");
		   	in.close();
		} catch (Exception e) { e.printStackTrace(); }
		return out;
	}
	
	public String serialRead(int limit){
		//in case of unlimited incoming data, set a limit for number of readings
		comPort.setComPortTimeouts(SerialPort.TIMEOUT_READ_SEMI_BLOCKING, 0, 0);
		String out="";
		int count=0;
		Scanner in = new Scanner(comPort.getInputStream());
		try
		{
		   while(in.hasNext()&&count<=limit){
		      out += (in.next()+"\n");
		      count++;
		   }
		   	in.close();
		} catch (Exception e) { e.printStackTrace(); }
		return out;
	}
	
	public void serialWrite(String s){
		//writes the entire string at once.
		comPort.setComPortTimeouts(SerialPort.TIMEOUT_SCANNER, 0, 0);
		try{Thread.sleep(5);} catch(Exception e){}
		PrintWriter pout = new PrintWriter(comPort.getOutputStream());
		pout.print(s);
		pout.flush();
		
	}
	public void serialWrite(String s,int noOfChars, int delay){
		//writes the entire string, 'noOfChars' characters at a time, 
		//with a delay of 'delay' between each send.
		comPort.setComPortTimeouts(SerialPort.TIMEOUT_SCANNER, 0, 0);
		try{Thread.sleep(5);} catch(Exception e){}
		PrintWriter pout = new PrintWriter(comPort.getOutputStream());
		for(int i=0;i<s.length();i+=noOfChars){
			pout.write(s.substring(i,i+noOfChars));
			pout.flush();
			System.out.println(s.substring(i,i+noOfChars));
			try{Thread.sleep(delay);}catch(Exception e){}
		}
		pout.write(noOfChars);
		pout.flush();
		
	}
	
	public void serialWrite(char c){
		//writes the character to output stream.
		comPort.setComPortTimeouts(SerialPort.TIMEOUT_SCANNER, 0, 0);
		try{Thread.sleep(5);} catch(Exception e){}
		PrintWriter pout = new PrintWriter(comPort.getOutputStream());
		pout.write(c);
		pout.flush();
	}
	
	public void serialWrite(char c, int delay){
		//writes the character followed by a delay of 'delay' milliseconds.
		comPort.setComPortTimeouts(SerialPort.TIMEOUT_SCANNER, 0, 0);
		try{Thread.sleep(25);} catch(Exception e){}
		PrintWriter pout = new PrintWriter(comPort.getOutputStream());
		pout.write(c);
		pout.flush();
		try{Thread.sleep(delay);}catch(Exception e){}
	}
}
