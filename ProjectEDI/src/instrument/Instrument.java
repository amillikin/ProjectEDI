package instrument;

import java.util.HashSet;

import arduino.ArduinoCOM;

public class Instrument {
	// TODO Instrument object class
	//		Possible attributes: position on staff?
	
	private Integer instrumentID;
	private ArduinoCOM port;
	private HashSet<Integer> acceptedNotes = new HashSet<Integer>();
	
	public Instrument(Integer instrumentID, String portName) {
		this.instrumentID = instrumentID;
		this.port = new ArduinoCOM(portName); //Port instrument is located on
		this.acceptedNotes = lookUpNotes(this.instrumentID); //List of midi notes this instrument will be triggered for
	}
	public void setInstrumentID(Integer instrumentID) {
		this.instrumentID = instrumentID;
	}
	
	public void setPort(String portName) {
		this.port = new ArduinoCOM(portName);
	}
	
	public Integer getInstrumentID() {
		return this.instrumentID;
	}
	
	public ArduinoCOM getPort() {
		return this.port;
	}
	
	public HashSet<Integer> getAcceptedNotes(){
		return this.acceptedNotes;
	}
	
	private HashSet<Integer> lookUpNotes(Integer instrumentID){
		HashSet<Integer> noteHS = new HashSet<Integer>();
		switch (instrumentID) {
		case 0: 
			for (Integer note:Constants.snareNotes) {
				noteHS.add(note);
			}
			break;
		case 1: 
			for (Integer note:Constants.cHiHatNotes) {
				noteHS.add(note);
			}
			break;
		case 2:
			for (Integer note:Constants.oHiHatNotes) {
				noteHS.add(note);
			}
			break;
		case 3:
			for (Integer note:Constants.bassNotes) {
				noteHS.add(note);
			}
			break;
		case 4:
			for (Integer note:Constants.tomNotes) {
				noteHS.add(note);
			}
			break;
		case 5:
			for (Integer note:Constants.cymbalNotes) {
				noteHS.add(note);
			}
			break;
		}
		return noteHS;
	}
}
