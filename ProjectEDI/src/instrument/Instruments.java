package instrument;

import java.util.HashMap;

public class Instruments {
	
	// For each instrument available, add its accepted notes
	// and their instrument index to the hash map.
	// Used for quickly checking if a MIDI note belongs to the current
	// set of instruments and sending a message to the correct Arduino
	public static HashMap<Integer, Integer> getInstrumentHashMap() {
		HashMap<Integer, Integer> instrumentHM = new HashMap<Integer, Integer>();
		
		for (Instrument instrument : Settings.loadSettings()) {
			for (Integer instrumentNote : instrument.getAcceptedNotes()) {
				instrumentHM.put(instrumentNote,instrument.getIndex());	
			}
		}
		
		return instrumentHM;
	}
}
