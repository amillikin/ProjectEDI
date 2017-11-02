package instrument;

import java.util.HashSet;

import arduino.ArduinoCOM;

public class Instrument {
	// TODO Instrument object class
	//		Possible attributes: position on staff?
	private Integer	index;
	private String instrumentName;
	private Integer instrumentID;
	private ArduinoCOM port;
	private String channel;
	private HashSet<Integer> acceptedNotes = new HashSet<Integer>();
	
	public Instrument(Integer index, String instrumentName, Integer instrumentID, String portName, String channel) {
		this.index = index; //Location in Instruments collection
		this.instrumentName = instrumentName; //Name of instrument
		this.instrumentID = instrumentID;
		this.port = new ArduinoCOM(portName); //Port instrument is located on
		this.channel = channel; //Channel of instrument A, B, or BOTH
		this.acceptedNotes = lookUpNotes(this.instrumentID); //List of midi notes this instrument will be triggered for
	}
	public void setName(String instrumentName) {
		this.instrumentName = instrumentName;
	}
	
	public void setInstrumentID(Integer instrumentID) {
		this.instrumentID = instrumentID;
	}
	
	public void setPort(String portName) {
		this.port = new ArduinoCOM(portName);
	}
	
	public void setChannel(String channel) {
		this.channel = channel;
	}
	
	public Integer getIndex() {
		return this.index;
	}
	
	public String getName() {
		return this.instrumentName;
	}
	
	public Integer getInstrumentID() {
		return this.instrumentID;
	}
	
	public ArduinoCOM getPort() {
		return this.port;
	}
	
	public String getChannel() {
		return this.channel;
	}
	public HashSet<Integer> getAcceptedNotes(){
		return this.acceptedNotes;
	}
	
	private HashSet<Integer> lookUpNotes(Integer instrumentID){
		HashSet<Integer> noteHS = new HashSet<Integer>();
		switch (instrumentID) {
		case 1: 
			for (Integer note:Constants.snareNotes) {
				noteHS.add(note);
			}
			break;
		case 2: 
			for (Integer note:Constants.cHiHatNotes) {
				noteHS.add(note);
			}
			break;
		case 3:
			for (Integer note:Constants.oHiHatNotes) {
				noteHS.add(note);
			}
			break;
		case 4:
			for (Integer note:Constants.bassNotes) {
				noteHS.add(note);
			}
			break;
		case 5:
			for (Integer note:Constants.lowTomNotes) {
				noteHS.add(note);
			}
			break;
		case 6:
			for (Integer note:Constants.highTomNotes) {
				noteHS.add(note);
			}
			break;
		case 7:
			for (Integer note : Constants.rideNotes) {
				noteHS.add(note);
			}
			break;
		case 8:
			for (Integer note : Constants.crashNotes) {
				noteHS.add(note);
			}
			break;
		}
		return noteHS;
	}
}
