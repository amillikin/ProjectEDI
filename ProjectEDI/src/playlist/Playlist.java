package playlist;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Playlist {
	public static void savePlaylist(List<Song> playlist, File file) {
		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		try {
			//Make Settings Document
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			Document doc = docBuilder.newDocument();
			doc = makeDocument(doc, playlist);
			
			//Write Settings Document
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(file);
			transformer.transform(source,result);
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (TransformerException te) {
			te.printStackTrace();
		}
	}
	
	public static List<Song> loadPlaylist(File file) {
		List<Song> playlist = new ArrayList<Song>();
		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		try {
			//Read Settings Document
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			if (!file.exists()) return playlist;
			Document doc = docBuilder.parse(file);
			playlist = parseDocPlaylist(doc);
			
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		}
		return playlist;
	}
	
	private static Document makeDocument(Document doc, List<Song> playlist) {
		Element playlistRoot = doc.createElement("PLAYLIST");
		doc.appendChild(playlistRoot);
		for (Song song : playlist) {
			Element newSong = doc.createElement("SONG");
			playlistRoot.appendChild(newSong);
			
			//Song Title
			Element songTitle = doc.createElement("TITLE");
			songTitle.appendChild(doc.createTextNode(song.getSongTitle()));
			newSong.appendChild(songTitle);
			
			//Song Length in Seconds
			Element songLength = doc.createElement("LENGTH");
			songLength.appendChild(doc.createTextNode(String.valueOf(song.getSongLengthSeconds())));
			newSong.appendChild(songLength);
			
			//Song Length Label
			Element songLengthLbl = doc.createElement("LENGTH_LBL");
			songLengthLbl.appendChild(doc.createTextNode(song.getSongLengthLbl()));
			newSong.appendChild(songLengthLbl);
			
			//Song Title
			Element songTickLength = doc.createElement("TICKS");
			songTickLength.appendChild(doc.createTextNode(String.valueOf(song.getTickLength())));
			newSong.appendChild(songTickLength);
			
			//Song Title
			Element songFilePath = doc.createElement("FILEPATH");
			songFilePath.appendChild(doc.createTextNode(song.getSongPath().getAbsolutePath()));
			newSong.appendChild(songFilePath);
		}
		
		return doc;
	}
	
	private static List<Song> parseDocPlaylist(Document doc) {
		List<Song> playlist = new ArrayList<Song>();
		String title, lengthlbl;
		File file;
		int length;
		long ticks;
		
		doc.getDocumentElement().normalize();
		NodeList nList = doc.getElementsByTagName("SONG");
		
		for (int i = 0; i < nList.getLength(); i++) {
			Node node = nList.item(i);
			if (node.getNodeType() == Node.ELEMENT_NODE) {
				Element songElement = (Element) node;
				
				title = songElement.getElementsByTagName("TITLE").item(0).getTextContent();
				length =  Integer.parseInt(songElement.getElementsByTagName("LENGTH").item(0).getTextContent());
				lengthlbl =  songElement.getElementsByTagName("LENGTH_LBL").item(0).getTextContent();
				ticks =  Long.parseLong(songElement.getElementsByTagName("TICKS").item(0).getTextContent());
				file = new File(songElement.getElementsByTagName("FILEPATH").item(0).getTextContent());
				
				if (file.exists()) {
					Song song= new Song(title, length, lengthlbl, ticks, file);
					playlist.add(song);
				}
			}
		}
		return playlist;
	}
}
