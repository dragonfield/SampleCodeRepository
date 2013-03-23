package jp.dragon.field.task;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.CSVWriter;

public class CsvTaskPersister implements TaskPersister {
	private static final Log logger = LogFactory.getLog(CsvTaskPersister.class);
	static final String DATE_FORMAT = "yyyy/MM/dd";
	private static final String FILE_NAME = "persistedTask.csv";

	private String filePath_ = null;
	
	public CsvTaskPersister() {
		
	}
	
	public CsvTaskPersister(String filePath) {
		filePath_ = filePath;
	}

	@Override
	public void persist(List<Task> tasks) {
		CSVWriter writer = null;
		File file = new File(filePath_ + "/" + FILE_NAME);
		
		try {
			if (!file.exists()) {
				file.createNewFile();
			}
			
			writer = new CSVWriter(new BufferedWriter(new FileWriter(file)));
			Task task = null;
		
			for (int i = 0; i < tasks.size(); i++) {
				task = tasks.get(i);
				writer.writeNext(fromTask(task));
			}
			
		} catch (IOException e) {
			logger.warn("persisting tasks is failed.", e);
		} finally {
			closeQuietly(writer);
		}

	}

	@Override
	public List<Task> retrieve() {
		List<Task> result = new ArrayList<Task>();
		
		CSVReader reader = null;
		File file = new File(filePath_ + "/" + FILE_NAME);
		
		try {
			reader = new CSVReader(new BufferedReader(new FileReader(file)));
			String[] data = null;
			
			while ((data = reader.readNext()) != null) {
				result.add(toTask(data));
			}
			
		} catch (IOException e) {
			logger.warn("retrieving tasks is failed.", e);
		} finally {
			closeQuietly(reader);
		}
		
		return result;
	}
	
	private static String[] fromTask(Task task) throws IOException {
		String[] result = new String[3];
		result[0] = DateFormatUtils.format(task.getDate(), DATE_FORMAT);
		result[1] = task.getSource().getCanonicalPath();
		result[2] = task.getDestination().getCanonicalPath();
		
		return result;
	}
	
	private static Task toTask(String[] data) {
		Task result = null;
		
		try {
			result = new Task(DateUtils.parseDate(data[0], DATE_FORMAT), new File(data[1]), new File(data[2]));
		} catch (ParseException e) {
			logger.warn("retrieving data is failed.", e);
		}
			
		return result;
	}
	
	private static void closeQuietly(CSVWriter writer) {
		if (writer != null) {
			try {
				writer.close();
			} catch (IOException e) {
				logger.warn("closing file is faile.", e);
			}
		}
	}
	
	private static void closeQuietly(CSVReader reader) {
		if (reader != null) {
			try {
				reader.close();
			} catch (IOException e) {
				logger.warn("closing file is faile.", e);
			}
		}	
	}

	public String getFilePath() {
		return filePath_;
	}

	public void setFilePath(String filePath) {
		filePath_ = filePath;
	}
}
