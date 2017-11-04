package org.jfugue.player;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.sound.midi.MidiMessage;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Receiver;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.Synthesizer;

import com.fazecast.jSerialComm.SerialPort;

import instrument.Instrument;

public class PassthroughReceiver implements Receiver {
	private Synthesizer synth;
	private HashMap<Integer,Integer> arduinoNotes;
	private List<SerialPort> arduinoPorts;
	private List<PrintWriter> arduinoPrintWriters;
	
	public PassthroughReceiver(Synthesizer synth) {
		this.synth = synth;
		this.arduinoNotes = getArdNotes();
		this.arduinoPorts = getPorts();
		this.arduinoPrintWriters = getPrintWriters();
		
		try {
			openSynthesizer(getSynthesizer());
		} catch (MidiUnavailableException e) {
			e.printStackTrace();
		}
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
		if (message instanceof ShortMessage && ((ShortMessage) message).getChannel() == 9 && arduinoNotes.containsKey((((ShortMessage) message).getData1()))) {
			if (((ShortMessage) message).getCommand() == 144) {
				PrintWriter pout = new PrintWriter(arduinoPorts.get(arduinoNotes.get(((ShortMessage) message).getData1())).getOutputStream());
				pout.write((char) ((ShortMessage) message).getData1());
				pout.flush();
			}
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
		try {
			closeSynthesizer(getSynthesizer());
		} catch (MidiUnavailableException e) {
			e.printStackTrace();
		}
	}
	
	private Synthesizer getSynthesizer() {
		return this.synth;
	}
	
	private SerialPort getComPort(String portDescription) {
		SerialPort comPort = SerialPort.getCommPort(portDescription);
		if (comPort.isOpen()) comPort.closePort();
		comPort.setBaudRate(57600);
		comPort.setFlowControl(SerialPort.FLOW_CONTROL_DTR_ENABLED|SerialPort.FLOW_CONTROL_DSR_ENABLED);
		comPort.openPort();
		return comPort;
	}
	
	private List<SerialPort> getPorts() {
		List<SerialPort> ports = new ArrayList<SerialPort>();
		ports.add(getComPort("COM6")); //Snare
		ports.add(getComPort("COM4")); //Bass
		return ports;
	}
	
	private List<PrintWriter> getPrintWriters() {
		List<PrintWriter> printWriters = new ArrayList<PrintWriter>();
		for (SerialPort port : arduinoPorts) {
			PrintWriter pw = new PrintWriter(port.getOutputStream());
			printWriters.add(pw);
		}		
		return printWriters;
	}
	
	private HashMap<Integer,Integer> getArdNotes(){
		HashMap<Integer,Integer> ardNotes = new HashMap<Integer,Integer>();
		ardNotes.put(38, 0);
		ardNotes.put(40, 0);
		ardNotes.put(35, 1);
		ardNotes.put(36, 1);
		return ardNotes;
	}
	
	private static void openSynthesizer(Synthesizer synth) throws MidiUnavailableException {
		synth.open();
	}
	private static void closeSynthesizer(Synthesizer synth) throws MidiUnavailableException {
		synth.close();
	}
	
	private static void openArduinoPorts(List<Instrument> arduinoInstruments) {
		for (Instrument instrument : arduinoInstruments) {
			instrument.getPort().openConnection();
		}
	}
	
	private static void closeArduinoPorts(List<Instrument> arduinoInstruments) {
		for (Instrument instrument : arduinoInstruments) {
			instrument.getPort().closeConnection();
		}
	}
}
