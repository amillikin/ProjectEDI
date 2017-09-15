package arduino;

import java.util.ArrayDeque;
import java.util.Queue;

public class ArduinoPattern implements ArduinoPatternProducer {
	//TODO Objects created from jFugue Patterns by the ArduinoPatternProducer and for use to send to Arduinos through ArduinoCOM
	protected Queue<String> patternQueue;
	
	public ArduinoPattern() {
		patternQueue = new ArrayDeque<String>();
	}

	@Override
	public ArduinoPattern getPattern() {
		return this;
	}
}
