package jp.dragon.field;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.logging.Level;

final class Utils {

	public static void quietClose(Reader reader) {
		if (reader != null) {
			try {
				reader.close();
			} catch (IOException e) {
				Main.logger_.log(Level.SEVERE, "closing file error." ,e);
			}
		}
	}
	
	public static void quietClose(Writer writer) {
		if (writer != null) {
			try {
				writer.close();
			} catch (IOException e) {
				Main.logger_.log(Level.SEVERE, "closing file error." ,e);
			}
		}
	}
}
