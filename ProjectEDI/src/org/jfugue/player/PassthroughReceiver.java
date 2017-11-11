package org.jfugue.player;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;

import javax.sound.midi.MidiMessage;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Receiver;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.Synthesizer;

import instrument.Instrument;
import instrument.Instruments;

public class PassthroughReceiver implements Receiver {
	private Synthesizer synth;
	private HashMap<Integer,Integer> arduinoNotes;
	private HashMap<Integer,PrintWriter> arduinoPrintWriters;
	private List<Instrument> arduinoInstruments;
	private int printWriterIndex;
	private int synthDelay;
	
	public PassthroughReceiver(Synthesizer synth) {
		this.synth = synth;
		this.arduinoInstruments = Instruments.getInstruments();
		this.arduinoNotes = fillArduinoNotes();
		this.arduinoPrintWriters = fillPrintWriters();
		this.synthDelay = 0;
		
		try {
			getSynthesizer().open();
		} catch (MidiUnavailableException e) {
			e.printStackTrace();
		}
	}
	
	public PassthroughReceiver(Synthesizer synth, int delay) {
		this.synth = synth;
		this.arduinoInstruments = Instruments.getInstruments();
		this.arduinoNotes = fillArduinoNotes();
		this.arduinoPrintWriters = fillPrintWriters();
		this.synthDelay = delay;
		
		try {
			getSynthesizer().open();
		} catch (MidiUnavailableException e) {
			e.printStackTrace();
		}
	}
	
	public void send(MidiMessage message, long lTimeStamp) {
		/* If the message sent by the sequencer:
		 * 		Is a ShortMessage
		 * 		&& On Channel 10
		 * 		&& Note Supported by Arduinos
		 * Then
		 * 		If Note On
		 * 			Write
		 * 		Else
		 * 			Do Nothing i.e. don't handle anything but note ons
		 * Else
		 * 		Send message to synthesizer
		*/		
		if (message instanceof ShortMessage && ((ShortMessage) message).getChannel() == 9 && arduinoNotes.containsKey((((ShortMessage) message).getData1()))) {
			if (((ShortMessage) message).getCommand() == 144 && ((ShortMessage) message).getData2() > 0) {
				printWriterIndex = arduinoNotes.get(((((ShortMessage) message).getData1())));
				try{Thread.sleep(getDelay());} catch(Exception e){}
				getPrintWriters().get(printWriterIndex).write(((ShortMessage) message).getData1());
				getPrintWriters().get(printWriterIndex).flush();
			}
		}
		else {

		}
	}
	
	public void close(){
		getSynthesizer().close();
		for (Instrument instrument : getInstruments()) {
			instrument.getPort().closeConnection();
		}
	}
	
	private Synthesizer getSynthesizer() {
		return this.synth;
	}
	
	private int getDelay() {
		return this.synthDelay;
	}
	
	private List<Instrument> getInstruments(){
		return this.arduinoInstruments;
	}
	
	private HashMap<Integer,PrintWriter> getPrintWriters(){
		return this.arduinoPrintWriters;
	}
	
	private HashMap<Integer,PrintWriter> fillPrintWriters() {
		HashMap<Integer,PrintWriter> printWriters = new HashMap<Integer,PrintWriter>();
		for (Instrument instrument : getInstruments()) {
			if (instrument.getPort().openConnection()) {
				PrintWriter pw = new PrintWriter(instrument.getPort().getSerialPort().getOutputStream());
				printWriters.put(instrument.getInstrumentID(), pw);
			}
			else {
				//Error opening port...
			}
		}		
		return printWriters;
	}
	
	private HashMap<Integer,Integer> fillArduinoNotes(){
		HashMap<Integer,Integer> arduinoNotes = new HashMap<Integer,Integer>();
		for (Instrument instrument : getInstruments()) {
			for (Integer note : instrument.getAcceptedNotes()) {
				arduinoNotes.put(note, instrument.getInstrumentID());
			}
		}
		return arduinoNotes;
	}
}
