package org.jfugue.player;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;

import javax.sound.midi.MidiMessage;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Receiver;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.Synthesizer;

import arduino.ArduinoCOM;
import instrument.Instrument;
import instrument.Instruments;
import instrument.Settings;

public class PassthroughReceiver implements Receiver {
	private Synthesizer synth;
	private HashMap<Integer,Integer> arduinoNotes;
	private List<Instrument> arduinoInstruments;
	private List<ArduinoCOM> arduinoPorts;
	//snarePort,closeHiHatPort,openHiHatPort,bassPort,lowTomPort,highTomPort,ridePort,crashPort;
	
	public PassthroughReceiver(Synthesizer synth) {
		this.synth = synth;
		this.arduinoNotes = Instruments.getInstrumentHashMap();
		this.arduinoInstruments = Settings.loadSettings();
		openArduinoPorts(getArduinoInstruments());
	}
	
	public void send(MidiMessage message, long lTimeStamp) {
		/* If the message sent by the sequencer:
		 * 		Is a ShortMessage
		 * 		&& On Channel 10
		 * 		&& Note On
		 * 		&& Supported by Arduinos
		 * Then
		 * 		Get instrument at relevant index
		 * 		Write to relevant port
		 * 		And relevant channel
		 * Else
		 * 		Send message to synthesizer
		*/ 
		if (message instanceof ShortMessage && ((ShortMessage) message).getCommand() == 144) {// && 
		  //((ShortMessage) message).getChannel() == 9 &&
		  //((ShortMessage) message).getCommand() == 0x90 &&
		  //arduinoNotes.containsKey(((ShortMessage) message).getData1())) {
			//channel = ((ShortMessage) message).getChannel();
			//command = ((ShortMessage) message).getCommand();
			//note = ((ShortMessage) message).getData1();
			for (Instrument instrument : arduinoInstruments) {
				instrument.getPort().serialWrite(((ShortMessage) message).getData1());
			}
			//arduinoInstruments.get(
			//	arduinoNotes.get(
			//		((ShortMessage) message).getData1()))
			//			.getPort().serialWrite(arduinoInstruments.get(
			//					arduinoNotes.get(
			//						((ShortMessage) message).getData1())).getChannel());			
		}
		else {
			try {
				synth.getReceiver().send(message, lTimeStamp);
			} catch (MidiUnavailableException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void close(){
		closeArduinoPorts(getArduinoInstruments());
	}
	
	public List<Instrument> getArduinoInstruments() {
		return this.arduinoInstruments;
	}
	
	public static void openArduinoPorts(List<Instrument> arduinoInstruments) {
		for (Instrument instrument : arduinoInstruments) {
			instrument.getPort().openConnection();
		}
	}
	
	public static void closeArduinoPorts(List<Instrument> arduinoInstruments) {
		for (Instrument instrument : arduinoInstruments) {
			instrument.getPort().closeConnection();
		}
	}
}
