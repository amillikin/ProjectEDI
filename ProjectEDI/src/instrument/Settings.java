package instrument;

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

public class Settings {
	public static void saveSettings(List<Instrument> instruments, String delay, String fontPath) {
		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		try {
			//Make Settings Document
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			Document doc = docBuilder.newDocument();
			doc = makeDocument(doc, instruments, delay, fontPath);
			
			//Write Settings Document
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			String executionDir = System.getProperty("user.dir");
			StreamResult result = new StreamResult(new File(executionDir.replace("\\", "/") + "/settings.xml"));
			transformer.transform(source,result);
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (TransformerException te) {
			te.printStackTrace();
		}
	}
	
	public static List<Instrument> loadInstruments() {
		List<Instrument> instruments = new ArrayList<Instrument>();
		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		try {
			//Read Settings Document
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			File settingsFile = new File(System.getProperty("user.dir").replace("\\", "/") + "/settings.xml");
			if (!settingsFile.exists()) return instruments;
			Document doc = docBuilder.parse(settingsFile);
			instruments = parseDocInstruments(doc);
			
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		}
		return instruments;
	}
	
	public static int loadDelay() {
		int delay = 0;
		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		try {
			//Read Settings Document
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			File settingsFile = new File(System.getProperty("user.dir").replace("\\", "/") + "/settings.xml");
			if (!settingsFile.exists()) return delay;
			Document doc = docBuilder.parse(settingsFile);
			delay = parseDocDelay(doc);
			
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		}
		return delay;
	}
	
	public static String loadSoundFontPath() {
		String fontPath = "";
		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		try {
			//Read Settings Document
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			File settingsFile = new File(System.getProperty("user.dir").replace("\\", "/") + "/settings.xml");
			if (!settingsFile.exists()) return fontPath;
			Document doc = docBuilder.parse(settingsFile);
			fontPath= parseDocSoundFontPath(doc);
			
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		}
		return fontPath;
	}
	
	private static Document makeDocument(Document doc, List<Instrument> instruments, String delay, String fontPath) {
		Element instrumentsRoot = doc.createElement("INSTRUMENTS");
		doc.appendChild(instrumentsRoot);
		for (Instrument instrument : instruments) {
			Element newInstrument = doc.createElement("INSTRUMENT");
			String id = instrument.getInstrumentID().toString();
			
			//Sets instrument id attribute to Current instrument index
			newInstrument.setAttribute("id", id);
			instrumentsRoot.appendChild(newInstrument);
			
			//Port Name
			Element portName = doc.createElement("PORT");
			portName.appendChild(doc.createTextNode(instrument.getPort().getPortDescription()));
			newInstrument.appendChild(portName);

		}
		Element instrumentDelay = doc.createElement("DELAY");
		instrumentDelay.setAttribute("val", delay);
		instrumentsRoot.appendChild(instrumentDelay);
		
		Element midiSoundFont = doc.createElement("SOUNDFONT");
		midiSoundFont.setAttribute("fontPath", fontPath);
		instrumentsRoot.appendChild(midiSoundFont);
		
		return doc;
	}
	
	private static List<Instrument> parseDocInstruments(Document doc) {
		List<Instrument> instruments = new ArrayList<Instrument>();
		doc.getDocumentElement().normalize();
		NodeList nList = doc.getElementsByTagName("INSTRUMENT");
		
		for (int i = 0; i < nList.getLength(); i++) {
			Node node = nList.item(i);
			if (node.getNodeType() == Node.ELEMENT_NODE) {
				Element instrumentElement = (Element) node;
				Integer id;
				String portName;
				
				//Index
				id = Integer.parseInt(instrumentElement.getAttribute("id"));
				
				//Port Name
				portName = instrumentElement.getElementsByTagName("PORT").item(0).getTextContent();
				
				Instrument instrument = new Instrument(id, portName);
				instruments.add(instrument);
			}
		}
		return instruments;
	}
	
	private static int parseDocDelay(Document doc) {
		int delay = 0;
		doc.getDocumentElement().normalize();
		NodeList nList = doc.getElementsByTagName("DELAY");
		
		for (int i = 0; i < nList.getLength(); i++) {
			Node node = nList.item(i);
			if (node.getNodeType() == Node.ELEMENT_NODE) {
				Element delayElement = (Element) node;
				delay = Integer.parseInt(delayElement.getAttribute("val"));
			}
		}
		return delay;
	}
	
	private static String parseDocSoundFontPath(Document doc) {
		String fontPath = "";
		doc.getDocumentElement().normalize();
		NodeList nList = doc.getElementsByTagName("SOUNDFONT");
		
		for (int i = 0; i < nList.getLength(); i++) {
			Node node = nList.item(i);
			if (node.getNodeType() == Node.ELEMENT_NODE) {
				Element soundFontElement = (Element) node;
				fontPath = soundFontElement.getAttribute("fontPath");
			}
		}
		return fontPath;
	}
}
