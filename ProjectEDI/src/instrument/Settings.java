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
	public static void saveSettings(List<Instrument> instruments) {
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
	
	public static List<Instrument> loadSettings() {
		List<Instrument> instruments = new ArrayList<Instrument>();
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
	
	private static Document makeDocument(Document doc, List<Instrument> instruments) {
		Element instrumentsRoot = doc.createElement("INSTRUMENTS");
		doc.appendChild(instrumentsRoot);
		for (Instrument instrument : instruments) {
			Element newInstrument = doc.createElement("INSTRUMENT");
			
			//Sets instrument id attribute to Current instrument index
			newInstrument.setAttribute("id", instrument.getInstrumentID().toString());
			instrumentsRoot.appendChild(newInstrument);
			
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
		}
		return doc;
	}
	
	private static List<Instrument> parseDoc(Document doc) {
		List<Instrument> instruments = new ArrayList<Instrument>();
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
				
				//Index
				id = Integer.parseInt(instrumentElement.getAttribute("id"));
				
				//Instrument Name
				instrumentName = instrumentElement.getElementsByTagName("NAME").item(0).getTextContent();
				
				//Port Name
				portName = instrumentElement.getElementsByTagName("PORT").item(0).getTextContent();
				
				//Channel
				channel = instrumentElement.getElementsByTagName("CHANNEL").item(0).getTextContent();
				
				Instrument instrument = new Instrument(i, instrumentName, id, portName, channel);
				instruments.add(instrument);
			}
		}
		
		return instruments;
	}
}
