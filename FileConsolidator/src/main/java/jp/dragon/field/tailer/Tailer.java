package jp.dragon.field.tailer;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;

import org.apache.commons.io.IOUtils;

public class Tailer {

	private static final String POINTER_FILE_NAME = ".pointer_file";
	private File file_ = null;
	private File destination_ = null;
	private static String lineSeparator_ = null;
	
	public Tailer(File file, File destination) {
		file_ = file;
		destination_ = destination;
		lineSeparator_ = System.getProperty("line.separator");
	}
	
	public void readLines() {
		long fileLength = file_.length();
		long filePointer = retrievePointer(destination_);
		long currentPosition = filePointer;
		byte[] input = new byte[4096];
		BufferedOutputStream writer = null;
		RandomAccessFile reader = null;
		
		try {
			if (fileLength > filePointer) {
				File destFile = new File(destination_.getAbsoluteFile() + File.separator + file_.getName());
				writer = new BufferedOutputStream(new FileOutputStream(destFile, true));
				reader = new RandomAccessFile(file_, "r");
				reader.seek(filePointer);
				
				int length = 0;
				boolean seenCR = false;
				ByteArrayOutputStream buffer = new ByteArrayOutputStream();
		        while ((length = reader.read(input)) != -1) {
		            for (int i = 0; i < length; i++) {
		                byte ch = input[i];
		                switch (ch) {
			                case '\n':
			                    seenCR = false;
			                    buffer.write(ch);
			                    IOUtils.write(buffer.toByteArray(), writer);
			                    System.out.print(buffer.toString());
			                    buffer.reset();
			                    currentPosition = filePointer + i + 1;
			                    break;
			                case '\r':
			                    seenCR = true;
			                    buffer.write(ch);
			                    break;
			                default:
			                    if (seenCR) {
			                        seenCR = false;
				                    IOUtils.write(buffer.toByteArray(), writer);
				                    System.out.print(buffer.toString());
			                        buffer.reset();
			                        currentPosition = filePointer + i + 1;
			                    }
			                    buffer.write(ch);
		                }
		            }
		            
		            filePointer = reader.getFilePointer();
		        }
				
		        reader.seek(currentPosition);
		        persistPointer(currentPosition, destination_);
		        System.out.println(currentPosition + "," +filePointer);
			} else {
	        	// rotate
	        }
		} catch (IOException e) {
			e.printStackTrace();
			
		} finally {
			IOUtils.closeQuietly(reader);
			IOUtils.closeQuietly(writer);
			
		}
	}
	
	private void persistPointer(long filePointer, File destination) {
		File pointerFile = new File(destination_.getAbsoluteFile() + File.separator + POINTER_FILE_NAME);
		BufferedWriter output = null;
		try {
			output = new BufferedWriter(new FileWriter(pointerFile));
			output.write(Long.toString(filePointer));
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			IOUtils.closeQuietly(output);
		}
	}
	
	private long retrievePointer(File destination) {
		long result = 0;
		
		File pointerFile = new File(destination_.getAbsoluteFile() + File.separator + POINTER_FILE_NAME);
		
		if (pointerFile.exists()) { 		
			BufferedReader input = null;
			
			try {
				input = new BufferedReader(new FileReader(pointerFile));
				String dataLine = input.readLine();
				
				if (dataLine != null) {
					result = Long.parseLong(dataLine);
				}
				
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				IOUtils.closeQuietly(input);
			}
		}
		
		return result;
	}
	
	static class MyThread extends Thread {
		private long interval_ = 0;
		private Tailer tailer_ = null;

		public MyThread(Tailer tailer, long interval) {
			interval_ = interval;
			tailer_ = tailer;
		}
		
		@Override
		public void run() {
			
			while (true) {
				try {
					tailer_.readLines();
					sleep(interval_);

				} catch (InterruptedException e) {
					return;
				}
			}
		}
		
	}

	public static void main(String[] args) {
		Tailer tailer = new Tailer(new File("data/sample.log"), new File("./dest"));
		MyThread thread = new MyThread(tailer, 30000);
		thread.start();
	}
}
