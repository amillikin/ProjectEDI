package arduino;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

import org.jfugue.pattern.Pattern;
import org.jfugue.pattern.Token;

public class ArduinoPattern implements ArduinoPatternProducer {
	//TODO Objects created from jFugue Patterns by the ArduinoPatternProducer and for use to send to Arduinos through ArduinoCOM
	private static final int UNDECLARED_EXPLICIT = -1;
	private Queue<String> patternQueue;
	private List<Token> tokens;
	private int explicitVoice = UNDECLARED_EXPLICIT;
	private int explicitLayer = UNDECLARED_EXPLICIT;
	private int explicitInstrument = UNDECLARED_EXPLICIT;
	private int explicitTempo = UNDECLARED_EXPLICIT;
	
	public ArduinoPattern(Pattern pattern) {
		tokens = new ArrayList<Token>();
		patternQueue = new ArrayDeque<String>();
		pattern.atomize();
		getTokens(pattern);
	}
	
	@Override
	public ArduinoPattern getPattern() {
		return this;
	}
	
	private void getTokens(Pattern pattern) {
		tokens = pattern.getTokens();
		for (Token token : tokens) {
			token.getType();
		}
	}
}
