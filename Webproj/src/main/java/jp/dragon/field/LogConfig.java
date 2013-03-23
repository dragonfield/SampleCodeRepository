package jp.dragon.field;

import java.io.IOException;
import java.util.logging.LogManager;

import jp.dragon.field.Main;

public class LogConfig {
	public LogConfig() {
	    try {
			LogManager.getLogManager().readConfiguration(Main.class.getResourceAsStream("/jullogging.properties"));
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
}
