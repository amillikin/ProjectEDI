package instrument;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Constants {
	//Hardcoded lists of midi (Channel 10) notes for each instrument
	public static List<Integer> snareNotes = new ArrayList<Integer>(Stream.of(38,40).collect(Collectors.toList()));
	public static List<Integer> cHiHatNotes = new ArrayList<Integer>(Stream.of(42,44).collect(Collectors.toList()));
	public static List<Integer> oHiHatNotes = new ArrayList<Integer>(Stream.of(46).collect(Collectors.toList()));
	public static List<Integer> bassNotes = new ArrayList<Integer>(Stream.of(35,36).collect(Collectors.toList()));
	public static List<Integer> tomNotes = new ArrayList<Integer>(Stream.of(41,45,47,43,50,48).collect(Collectors.toList()));
	public static List<Integer> cymbalNotes = new ArrayList<Integer>(Stream.of(49,57,51,59).collect(Collectors.toList()));
	
	//Instrument IDs
	public static final Integer SNARE = 0;
	public static final Integer CLOSED_HI_HAT = 1;
	public static final Integer OPEN_HI_HAT = 2;
	public static final Integer BASS = 3;
	public static final Integer TOMS = 4;
	public static final Integer CYMBALS = 5;

}
