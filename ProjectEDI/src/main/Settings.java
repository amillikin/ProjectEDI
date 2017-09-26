package main;

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
	public static void saveSettings(Instruments instruments) {
		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		try {
			//Make Settings Document
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			Document doc = docBuilder.newDocument();
			doc = makeDocument(doc, instruments);
			
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
	
	public Instruments loadSettings() {
		Instruments instruments = new Instruments();
		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		try {
			//Read Settings Document
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			File settingsFile = new File(System.getProperty("user.dir").replace("\\", "/") + "/settings.xml");
			if (!settingsFile.exists()) return instruments;
			Document doc = docBuilder.parse(settingsFile);
			instruments = parseDoc(doc);
			
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} catch (SAXException se) {
			se.printStackTrace();
		}
		return instruments;
	}
	
	private static Document makeDocument(Document doc, Instruments instruments) {
		Element instrumentsRoot = doc.createElement("INSTRUMENTS");
		doc.appendChild(instrumentsRoot);
		for (Instrument instrument : instruments) {
			Element newInstrument = doc.createElement("INSTRUMENT");
			doc.appendChild(newInstrument);
			
			//Sets instrument id attribute to Current instrument index
			newInstrument.setAttribute("id", instrument.getInstrumentID().toString());
			
			//Instrument Name
			Element instrumentName = doc.createElement("NAME");
			instrumentName.appendChild(doc.createTextNode(instrument.getName()));
			newInstrument.appendChild(instrumentName);
			
			//Port Name
			Element portName = doc.createElement("PORT");
			portName.appendChild(doc.createTextNode(instrument.getPort().getPortDescription()));
			newInstrument.appendChild(portName);
			
			//Channel
			Element channel = doc.createElement("CHANNEL");
			channel.appendChild(doc.createTextNode(instrument.getChannel()));
			newInstrument.appendChild(channel);
			
			//Accepted Notes
			Element notes = doc.createElement("NOTES");
			for (Integer note : instrument.getAcceptedNotes()) {
				notes.appendChild(doc.createTextNode(note.toString()));
			}
			newInstrument.appendChild(notes);
		}
		return doc;
	}
	
	private Instruments parseDoc(Document doc) {
		Instruments instruments = new Instruments();
		doc.getDocumentElement().normalize();
		NodeList nList = doc.getElementsByTagName("INSTRUMENTS");
		
		for (int i = 0; i < nList.getLength(); i++) {
			Node node = nList.item(i);
			if (node.getNodeType() == Node.ELEMENT_NODE) {
				Element instrumentElement = (Element) node;
				Integer id;
				String instrumentName;
				String portName;
				String channel;
				List<Integer> acceptedNotes = new ArrayList<Integer>();
				
				//Index
				id = Integer.parseInt(instrumentElement.getAttribute("id"));
				
				//Instrument Name
				instrumentName = instrumentElement.getElementsByTagName("NAME").item(0).getTextContent();
				
				//Port Name
				portName = instrumentElement.getElementsByTagName("PORT").item(0).getTextContent();
				
				//Channel
				channel = instrumentElement.getElementsByTagName("CHANNEL").item(0).getTextContent();
				
				//Accepted Notes
				for (int j = 0; j < instrumentElement.getElementsByTagName("NOTES").getLength(); j++) {
					acceptedNotes.add(Integer.parseInt(instrumentElement.getElementsByTagName("NOTES").item(j).getTextContent()));
				}
				
				Instrument instrument = new Instrument(i, instrumentName, id, portName, channel, acceptedNotes);
				instruments.add(instrument);
			}
		}
		
		return instruments;
	}
}
