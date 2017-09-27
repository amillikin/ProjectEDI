package instrument;

import java.util.ArrayList;
import java.util.List;
import arduino.ArduinoCOM;

public class Instrument {
	// TODO Instrument object class
	//		Possible attributes: instrument name, A/B channel, Serial Port,
	//							 position on staff?
	private Integer	index;
	private String instrumentName;
	private Integer instrumentID;
	private ArduinoCOM port;
	private String channel;
	private List<Integer> acceptedNotes = new ArrayList<Integer>();
	
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
	public List<Integer> getAcceptedNotes(){
		return this.acceptedNotes;
	}
	
	private List<Integer> lookUpNotes(Integer instrumentID){
		List <Integer> noteList = new ArrayList<Integer>();
		switch (instrumentID) {
		case 1: 
			noteList = Constants.snareNotes;
		case 2: 
			noteList = Constants.cHiHatNotes;
		case 3: 
			noteList = Constants.oHiHatNotes;
		case 4: 
			noteList = Constants.bassNotes;
		case 5: 
			noteList = Constants.lowTomNotes;
		case 6: 
			noteList = Constants.highTomNotes;
		case 7: 
			noteList = Constants.rideNotes;
		case 8: 
			noteList = Constants.crashNotes;
		}
		return noteList;
	}
}
