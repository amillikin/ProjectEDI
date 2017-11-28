package playlist;

import java.io.File;
import java.io.IOException;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;

public class Song {
	private String songTitle;
	private String songLengthLbl;
	private int songLengthSeconds;
	private File songPath;
	
	public Song (File songPath) {
		this.songTitle = songPath.getName().substring(0, songPath.getName().lastIndexOf("."));
		this.songLengthSeconds = calculateSongLength(songPath);
		this.songLengthLbl = formatSongLength(songLengthSeconds);
		this.songPath = songPath;
	}
	
	public String getSongTitle() {
		return songTitle;
	}
	
	public String getSongLengthLbl() {
		return songLengthLbl;
	}
	
	public int getSongLengthSeconds() {
		return songLengthSeconds;
	}
	
	public File getSongPath() {
		return songPath;
	}
	
	private int calculateSongLength(File songPath) {
		long songLength = 0;
		
		try {
			Sequence sequence = MidiSystem.getSequence(songPath);
			Sequencer sequencer = MidiSystem.getSequencer();
			sequencer.setSequence(sequence);
			songLength = sequencer.getMicrosecondLength();
		} catch (InvalidMidiDataException | IOException | MidiUnavailableException e) {
			e.printStackTrace();
		}
		
		return (int) (songLength / 1000000);
	}
	
	private String formatSongLength(int songLengthSeconds) {
		return String.format("%2d:%02d", (songLengthSeconds % 3600) / 60, songLengthSeconds / 60);
	}
}
