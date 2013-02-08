package jp.dragon.field;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
	private static final String SEPARATER = "/";
	private static final int PREFIX_INDEX = 3;
	private static final int PREFIX_KEY_DATA = 5;
	private static final int APPENDER_KEY_DATA = 3;

	public static void main(String[] args) throws IOException {
		File prefix_file = new File("data/hw_sample.txt");
		File append_file = new File("data/mw_sample.txt");
		File result_file = new File("data/result.txt");

		HashMap<String, String> prefixMap = buildPrefixMap(prefix_file);
		
		BufferedReader reader = null;
		BufferedWriter writer = null;
		
		reader = new BufferedReader(new FileReader(append_file));
		writer = new BufferedWriter(new FileWriter(result_file));
		
		System.out.println("Start appending prefix.");
		String dataLine = null;
		while ((dataLine = reader.readLine()) != null) {
			System.out.print("Read=" + dataLine);
			String key = extractKey(dataLine, APPENDER_KEY_DATA);
			
			String prefixData = prefixMap.get(key);
			System.out.print(", Prefix data=" + prefixData);
			
			if (prefixData != null) {
				String prefix = parsePrefix(prefixData);
				String appendData = prefix + dataLine;
				System.out.println(" => " + appendData);
				writer.write(appendData + "\n");
			}
		}
		
		reader.close();
		writer.close();
		
		System.out.println("Appending prefix finished.\n");
	}
	
	
	private static HashMap<String, String> buildPrefixMap(File file) {
		System.out.println("Start generating prefix mapper");
		HashMap<String, String> result = new HashMap<String, String>();
		BufferedReader input = null;
		
		try {
			input = new BufferedReader(new FileReader(file));
			String dataLine = null;

			while ((dataLine = input.readLine()) != null) {
				String key = extractKey(dataLine, PREFIX_KEY_DATA);
				
				if (!result.containsKey(key)) { // need??
					result.put(key, dataLine);
					System.out.println("put " + dataLine + " with key=" + key);
				}
			}			
			
		} catch (IOException e) {
			e.printStackTrace();

		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (Exception e) {
					//
				}
			}
			
		}
		
		System.out.println("Generating prefix mapper finished.\n");
		return result;
	}
	
	private static String extractKey(String line, int key) {
		String result = null;
		
		StringTokenizer tokenizer = new StringTokenizer(line, SEPARATER);

		int counter = 1;
		String current = null;

		while (tokenizer.hasMoreTokens()) {
			current = tokenizer.nextToken();
			
			if (counter == key) {
				result = current;
				break;
			}
			counter++;
		}				
		
		return result;
	}
	
	private static String parsePrefix(String line) {
		StringBuffer result = new StringBuffer();
		
		StringTokenizer tokenizer = new StringTokenizer(line, SEPARATER);

		int counter = 1;

		while (tokenizer.hasMoreTokens()) {
			result.append(SEPARATER).append(tokenizer.nextToken());
			if (counter == PREFIX_INDEX) {
				break;
			}
			counter++;
		}		
		
		return result.toString();
	}	
}
