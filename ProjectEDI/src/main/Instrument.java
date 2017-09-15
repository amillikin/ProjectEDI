package main;

import java.util.ArrayList;
import java.util.List;
import arduino.ArduinoCOM;

public class Instrument {
	// TODO Instrument object class
	//		Possible attributes: instrument name, A/B channel, Serial Port,
	//							 position on staff?
	private Integer	index;
	private String instrumentName;
	private ArduinoCOM port;
	private String channel;
	private List<Integer> acceptedNotes = new ArrayList<Integer>();
	
	public Instrument(Integer index, String instrumentName, String portName, String channel, List<Integer> acceptedNotes) {
		this.index = index; //Location in Instruments collection
		this.instrumentName = instrumentName; //Name of instrument
		this.port = new ArduinoCOM(portName); //Port instrument is located on
		this.channel = channel; //Channel of instrument A, B, or BOTH
		this.acceptedNotes = acceptedNotes; //List of midi notes this instrument will be triggered for
	}
	public void setName(String instrumentName) {
		this.instrumentName = instrumentName;
	}
	
	public void setPort(String portName) {
		this.port = new ArduinoCOM(portName);
	}
	
	public void setChannel(String channel) {
		this.channel = channel;
	}
	
	public void setAcceptedNotes(List<Integer> acceptedNotes) {
		this.acceptedNotes = acceptedNotes;
	}
	
	public Integer getIndex() {
		return this.index;
	}
	
	public String getName() {
		return this.instrumentName;
	}
	
	public ArduinoCOM getPort() {
		return this.port;
	}
	
	public String getChannel() {
		return this.channel;
	}
	public List<Integer> getAcceptedNotes(){
		return this.acceptedNotes;
	}
}
