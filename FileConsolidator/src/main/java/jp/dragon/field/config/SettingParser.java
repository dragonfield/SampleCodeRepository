package jp.dragon.field.config;

import java.io.File;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

public class SettingParser extends DefaultHandler {
	private static final Log logger = LogFactory.getLog(SettingParser.class);
		
	private Setting setting_ = null;
	private SystemGroup currentSystem_ = null;
	private Instance currentInstance_ = null;
	
	public SettingParser() {
		
	}
	
	public SettingParser(Setting setting) {
		setting_ = setting;
	}
	
		    
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
    	if ("setting".equalsIgnoreCase(qName)) {
    		setting_.setRoot(attributes.getValue("root"));
    	}
    	
    	if ("system_group".equalsIgnoreCase(qName)) {
    		SystemGroup system = new SystemGroup(attributes.getValue("name"));
    		system.setName(attributes.getValue("name"));
    		setting_.addSystem(system);
    		currentSystem_ = system;
    	}
    	
    	if ("instance".equalsIgnoreCase(qName)) {
    		Instance instance = new Instance(attributes.getValue("name"), attributes.getValue("location"));
    		currentSystem_.addInstance(instance);
    		currentInstance_ = instance;
    	}
    	
    	if ("statsrep".equalsIgnoreCase(qName)) {
    		StatsRep statsrep = new StatsRep(attributes.getValue("dir"), attributes.getValue("prefix"));
    		currentInstance_.setReport(statsrep);
    	}
    	
    	if ("awrrep".equalsIgnoreCase(qName)) {
    		AwrRep awrrep = new AwrRep(attributes.getValue("dir"), attributes.getValue("prefix"));
    		currentInstance_.setReport(awrrep);
    	}
    	
    	if ("ash".equalsIgnoreCase(qName)) {
    		Metric metric = new Metric("ash", attributes.getValue("dir"), attributes.getValue("prefix"), attributes.getValue("date"));
    		currentInstance_.addMetric(metric);
    	}

    	if ("activeses".equalsIgnoreCase(qName)) {
    		Metric metric = new Metric("activeses", attributes.getValue("dir"), attributes.getValue("prefix"), attributes.getValue("date"));
    		currentInstance_.addMetric(metric);
    	}

    	if ("client".equalsIgnoreCase(qName)) {
    		Metric metric = new Metric("client", attributes.getValue("dir"), attributes.getValue("prefix"), attributes.getValue("date"));
    		currentInstance_.addMetric(metric);
    	}
    	
    	if ("lckchk".equalsIgnoreCase(qName)) {
    		Metric metric = new Metric("lckchk", attributes.getValue("dir"), attributes.getValue("prefix"), attributes.getValue("date"));
    		currentInstance_.addMetric(metric);
    	}
    	
    	if ("pgamem".equalsIgnoreCase(qName)) {
    		Metric metric = new Metric("pgamem", attributes.getValue("dir"), attributes.getValue("prefix"), attributes.getValue("date"));
    		currentInstance_.addMetric(metric);
    	}

    	if ("sessionw".equalsIgnoreCase(qName)) {
    		Metric metric = new Metric("sessionw", attributes.getValue("dir"), attributes.getValue("prefix"), attributes.getValue("date"));
    		currentInstance_.addMetric(metric);
    	}

    	if ("sysevent".equalsIgnoreCase(qName)) {
    		Metric metric = new Metric("sysevent", attributes.getValue("dir"), attributes.getValue("prefix"), attributes.getValue("date"));
    		currentInstance_.addMetric(metric);
    	}

    	if ("sysstat".equalsIgnoreCase(qName)) {
    		Metric metric = new Metric("sysstat", attributes.getValue("dir"), attributes.getValue("prefix"), attributes.getValue("date"));
    		currentInstance_.addMetric(metric);
    	}

    	if ("undo".equalsIgnoreCase(qName)) {
    		Metric metric = new Metric("undo", attributes.getValue("dir"), attributes.getValue("prefix"), attributes.getValue("date"));
    		currentInstance_.addMetric(metric);
    	}
    }
    
    public void warning(SAXParseException e) {
    	logger.warn(e);
    }

    public void error(SAXParseException e) {
    	logger.error(e);
    }
    public void fatalError(SAXParseException e) {
    	logger.fatal(e);
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

		parser.parse(new File("src/main/config/setting.xml"), new SettingParser(setting));

		logger.info("finished.");
	}
	
}
