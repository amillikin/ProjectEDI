package instrument;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.fazecast.jSerialComm.SerialPort;

import arduino.ArduinoCOM;

public class Instruments {
	
	// For each instrument available, add its accepted notes
	// and their instrument index to the hash map.
	// Used for quickly checking if a MIDI note belongs to the current
	// set of instruments and sending a message to the correct Arduino
	public static HashMap<Integer, Integer> getInstrumentHashMap() {
		HashMap<Integer, Integer> instrumentHM = new HashMap<Integer, Integer>();
		
		for (Instrument instrument : Settings.loadSettings()) {
			for (Integer instrumentNote : instrument.getAcceptedNotes()) {
				instrumentHM.put(instrumentNote,instrument.getInstrumentID());	
			}
		}
		
		return instrumentHM;
	}
	
	public static List<Instrument> getInstruments(){
		List<Instrument> savedInstruments = new ArrayList<Instrument>();
		savedInstruments = Settings.loadSettings();
		if (savedInstruments.isEmpty()) {
			return autoFindInstruments();
		}
		else {
			return savedInstruments;
		}
	}
	
	public static List<Instrument> autoFindInstruments(){
		List<Instrument> instruments = new ArrayList<Instrument>();
		String portReturn = null;
		String systemPortName = null;
		
		//For each serial port connected, send the universal value 82 ('R')
		//This will have the arduino connected to that port actuate and send back
		//a byte of data with its respective instrument.Constants InstrumentID
		for (SerialPort port : SerialPort.getCommPorts()) {
			systemPortName = port.getSystemPortName();
			ArduinoCOM portToTest = new ArduinoCOM(systemPortName);
			portToTest.openConnection();
			portToTest.serialWrite('R');
			portReturn = portToTest.serialRead();
			portToTest.closeConnection();
			int instrumentID = Integer.parseInt(portReturn.substring(0, 1));
			if (instrumentID >= 0 && instrumentID <= 5) {
				Instrument instrument = new Instrument(instrumentID, systemPortName);
				instruments.add(instrument);
			}
		}
		Settings.saveSettings(instruments);
		return instruments;
	}
}
