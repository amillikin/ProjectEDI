package main;

import java.util.ArrayList;
import java.util.List;

public class Instruments {
	// TODO Instrument collection... holds an array of instrument objects
	private List<Instrument> instruments = new ArrayList<Instrument>();
	
	public Instruments() {
		//default constructor
	}
	
	public void addInstrument(Instrument instrument) {
		instruments.add(instrument);
	}
	
	public void removeInstrument(Instrument instrument) {
		instruments.remove(instrument);
	}
	
	public void clearList() {
		instruments.clear();
	}
	
	public Instrument getInstrument(Integer index) {
		return instruments.get(index);
	}
	
	public Integer getSize() {
		return instruments.size();
	}
}
