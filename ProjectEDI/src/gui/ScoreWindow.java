package gui;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;

import jm.music.data.Part;
import jm.music.data.Phrase;
import jm.music.data.Score;

public class ScoreWindow extends JInternalFrame {

	public ScoreWindow() {
		initialize();
	}
	
	private void initialize() {
		setBounds(100, 100, 450, 300);
		Score score = new Score("New Score");
		Part part1 = new Part("Snare", 1, 0);
		Phrase phrase1 = new Phrase(0.0);
		part1.addPhrase(phrase1);
		score.addPart(part1);
	}

}
