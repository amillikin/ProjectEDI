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
	public static final Integer SNARE = 0; //48 ASCII
	public static final Integer CLOSED_HI_HAT = 1; //49 ASCII
	public static final Integer OPEN_HI_HAT = 2; //50 ASCII
	public static final Integer BASS = 3; //51 ASCII
	public static final Integer TOMS = 4; //52 ASCII
	public static final Integer CYMBALS = 5; //53 ASCII

	//41 Low Tom 2
	//43 Low Tom 1
	//45 Mid Tom 2
	//47 Mid Tom 1
	//48 High Tom 2
	//50 High Tom 1
	
	//51 Ride Cymbal 1
	//59 Ride Cymbal 2
	
	//49 Crash Cymbal 1
	//57 Crash Cymbal 2	
	
}
