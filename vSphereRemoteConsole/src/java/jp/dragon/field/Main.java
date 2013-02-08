package jp.dragon.field;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.io.File;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import jp.dragon.field.model.Setting;



public class Main {

	public static void main(String[] args) throws Exception {		
		String userHome = System.getProperty("user.home");

		File clientHome = new File(userHome + File.separator + ".vmrc");
		
		if (!clientHome.exists()) {
			clientHome.mkdir();
		}
		
		File repository = new File(userHome + File.separator + ".vmrc" + File.separator + "repository.xml");
		
		if (!repository.exists()) {
			throw new RuntimeException(repository.toString() + " is not found.");
		}
		
		SAXParserFactory factory = SAXParserFactory.newInstance();
		factory.setValidating(true);
		SAXParser parser = factory.newSAXParser();
		Setting setting = new Setting();

		parser.parse(repository, new RepositoryParser(setting));
				
		JFrame console = new LoginConsole(setting);
		
		UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel");
		SwingUtilities.updateComponentTreeUI(console);
		
		Rectangle bounds = console.getBounds();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();		
		console.setBounds(screenSize.width/4, screenSize.height/4, bounds.width, bounds.height);
		
		URL url = Thread.currentThread().getContextClassLoader().getResource("icon/vmrc.png");
		ImageIcon icon = new ImageIcon(url);
		console.setIconImage(icon.getImage());
		
		console.setVisible(true);
	}

}
