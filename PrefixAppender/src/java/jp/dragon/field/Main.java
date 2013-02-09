package jp.dragon.field;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;
import java.util.StringTokenizer;

public class Main {
	private static final String SEPARATER = "/";
	private static final int PREFIX_INDEX = 3;
	private static final int SOURCE_KEY = 5;
	private static final int TARGET_KEY = 3;

	public static void main(String[] args) throws IOException {
		if ((args.length == 1) && ("-h".equals(args[0]))){
			exit(null);
		}

		Properties arguments = parseArguments(args);
		validateArguments(arguments);
		doProcess(new File(arguments.getProperty("-s")), 
				  new File(arguments.getProperty("-t")), 
				  new File(arguments.getProperty("-o")));
		
		System.out.println("finished.");
	}

	private static void doProcess(File source, File target, File result) {
		HashMap<String, String> prefixMap = buildPrefixMap(source);
		
		BufferedReader reader = null;
		BufferedWriter writer = null;
		
		try {
			reader = new BufferedReader(new FileReader(target));
			writer = new BufferedWriter(new FileWriter(result));

			System.out.println("Start appending prefix.");
			String dataLine = null;
			while ((dataLine = reader.readLine()) != null) {
				System.out.print("Read=" + dataLine);
				String key = extractKey(dataLine, TARGET_KEY);
				
				String prefixData = prefixMap.get(key);
				System.out.print(", source data=" + prefixData);
				
				if (prefixData != null) {
					String prefix = parsePrefix(prefixData);
					String appendData = prefix + dataLine;
					System.out.println(" => " + appendData);
					writer.write(appendData + "\n");
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
			
		} finally {
			Utils.quietClose(reader);
			Utils.quietClose(writer);
		}
					
		System.out.println("Appending prefix finished.\n");		
	}
	
	private static HashMap<String, String> buildPrefixMap(File file) {
		System.out.println("Start generating prefix mapp");
		HashMap<String, String> result = new HashMap<String, String>();
		BufferedReader input = null;
		
		try {
			input = new BufferedReader(new FileReader(file));
			String dataLine = null;

			while ((dataLine = input.readLine()) != null) {
				String key = extractKey(dataLine, SOURCE_KEY);
				
				if (!result.containsKey(key)) { // need??
					result.put(key, dataLine);
					System.out.println("put " + dataLine + " with key=" + key);
				}
			}			
			
		} catch (IOException e) {
			e.printStackTrace();

		} finally {
			Utils.quietClose(input);
			
		}
		
		System.out.println("Generating prefix map finished.\n");
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

	private static Properties parseArguments(String[] argv) {
		Properties result = new Properties();
		String prev = null;
		    
		for (int i = 0; i < argv.length; i++) {
			if ("-s".equals(prev)) {
				result.setProperty(prev, argv[i]);
			}

			if ("-t".equals(prev)) {
				result.setProperty(prev, argv[i]);
			}

			if ("-o".equals(prev)) {
				result.setProperty(prev, argv[i]);
			}
		      		            
			prev = argv[i];
		}
		    
		return result;
	}
	
	private static void validateArguments(Properties arguments) {
		if (arguments.getProperty("-s") == null) {
			exit("argument -s is not specified.");
		} else {
			File file = new File(arguments.getProperty("-s"));
			if (!file.exists()) {
				exit(file.getName() + " specifyed by -s not exist.");
			}
		}
		
		if (arguments.getProperty("-t") == null) {
			exit("argument -t is not specified.");	
		} else {
			File file = new File(arguments.getProperty("-t"));
			if (!file.exists()) {
				exit(file.getName() + " specifyed by -t not exist.");
			}
		}
			
		if (arguments.getProperty("-o") == null) {
			exit("argument -o is not specified.");
		}
		
	}

	private static void exit(String message) {
		if (message != null) {
			System.out.println(message);			
		}
		printUsage();
		System.exit(-1);			
	}

	private static void printUsage() {
		System.out.println("Usage : java -jar appender.jar -s <source_file> -t <target_file> -o <result_file>");
	    System.out.println("  -s [required] : specify the source file to extract prefix.");
	    System.out.println("  -t [required] : specify the target file to append prefix.");
	    System.out.println("  -o [required] : specify the result file.");
	    System.out.println("  -h            : print usage text.");
	}	
	
}
