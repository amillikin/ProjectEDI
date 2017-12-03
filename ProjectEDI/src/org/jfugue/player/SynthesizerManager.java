/*
 * JFugue, an Application Programming Interface (API) for Music Programming
 * http://www.jfugue.org
 *
 * Copyright (C) 2003-2014 David Koelle
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.jfugue.player;

import java.io.File;
import java.io.IOException;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Receiver;
import javax.sound.midi.Soundbank;
import javax.sound.midi.Synthesizer;

import instrument.Settings;

public class SynthesizerManager {
	private static SynthesizerManager instance;
	
	public static SynthesizerManager getInstance() throws MidiUnavailableException, InvalidMidiDataException, IOException {
		if (instance == null) {
			instance = new SynthesizerManager();
		}
		return instance;
	}
	
	private Synthesizer synth;
	private Receiver passthroughRec;

	private SynthesizerManager() throws MidiUnavailableException, InvalidMidiDataException, IOException {
		this.synth = getDefaultSynthesizer();
		this.passthroughRec = new PassthroughReceiver(getSynthesizer());
	}
	
	public Synthesizer getDefaultSynthesizer() throws MidiUnavailableException {
		Synthesizer newSynth = MidiSystem.getSynthesizer();
		String soundFontPath = Settings.loadSoundFontPath();
		
		newSynth.open();
		if (!soundFontPath.equals("")) {
			String executionDir = System.getProperty("user.dir");
			File soundFont = new File(executionDir.replace("\\", "/") + "/soundfonts/" + soundFontPath);
			try {
				if (soundFont.exists() ) {
					Soundbank soundBank = MidiSystem.getSoundbank(soundFont);
					if (newSynth.isSoundbankSupported(soundBank)) {
						newSynth.unloadAllInstruments(newSynth.getDefaultSoundbank());
						newSynth.loadAllInstruments(MidiSystem.getSoundbank(soundFont));	
					}
				};
			}
			catch (InvalidMidiDataException | IOException e) {
				e.printStackTrace();
				return newSynth;
			}
		}
		return newSynth;
	}
	
	public void setSynthesizer(Synthesizer synth) {
		this.synth = synth;
	}
	
	public Synthesizer getSynthesizer() {
		return this.synth;
	}
	
	public void setDefaultPassthroughReceiver() {
		this.passthroughRec = new PassthroughReceiver(getSynthesizer());
	}
	
	public Receiver getPassthroughReceiver() {
		return this.passthroughRec;
	}	
	
}
