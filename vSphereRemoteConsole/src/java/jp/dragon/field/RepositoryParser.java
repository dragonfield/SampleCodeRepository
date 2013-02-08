package jp.dragon.field;

import java.io.File;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import jp.dragon.field.model.Server;
import jp.dragon.field.model.Setting;
import jp.dragon.field.model.Vm;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;



public class RepositoryParser extends DefaultHandler {
		
	private Setting setting_ = null;
	private String current_ = null;
	
	public RepositoryParser() {
		
	}
	
	public RepositoryParser(Setting setting) {
		setting_ = setting;
	}
	
		    
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
    	if ("setting".equalsIgnoreCase(qName)) {
    		setting_.setHome(attributes.getValue("home"));    	    		
    		
    	} else if ("vsphereserver".equalsIgnoreCase(qName)) {
    		Server server = new Server(attributes.getValue("host"), attributes.getValue("version"), attributes.getValue("user"), attributes.getValue("password"));
    		setting_.addServer(server);
    		current_ = attributes.getValue("host");
    	    
    	} else if ("vm".equalsIgnoreCase(qName)) {    		
    		Vm vm = new Vm(attributes.getValue("name"), attributes.getValue("datastore"));
    		setting_.addVm(current_, vm);
    	}

    }
    
    public void warning(SAXParseException e) {
        System.out.println(e.getMessage());
    }
    public void error(SAXParseException e) {
        System.out.println(e.getMessage());
    }
    public void fatalError(SAXParseException e) {
        System.out.println(e.getMessage());
    }

	public Setting getSetting() {
		return setting_;
	}

	public void setSetting(Setting setting) {
		setting_ = setting;
	}

	public static void main(String[] args) throws Exception {
		SAXParserFactory spfactory = SAXParserFactory.newInstance();
		SAXParser parser = spfactory.newSAXParser();
		Setting setting = new Setting();

		parser.parse(new File("data/repository.xml"), new RepositoryParser(setting));

		System.out.println("finished.");
	}
	
}
