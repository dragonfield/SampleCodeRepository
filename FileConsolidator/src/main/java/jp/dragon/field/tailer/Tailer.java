package jp.dragon.field.tailer;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class Tailer {

	private long filePointer_ = 0;
	private File file_ = null;
	private static String lineSeparator_ = null;
	
	public Tailer(File file) {
		file_ = file;
		lineSeparator_ = System.getProperty("line.separator");
	}
	
	public void readLines() throws IOException {
		long fileLength = file_.length();
		long currentPosition = filePointer_;
		byte[] buffer = new byte[4096];
		
		if (fileLength > filePointer_) {
			RandomAccessFile reader = new RandomAccessFile(file_, "r");
			reader.seek(filePointer_);
			
			int length = 0;
			boolean seenCR = false;
			StringBuffer line = new StringBuffer();
	        while ((length = reader.read(buffer)) != -1) {
	            for (int i = 0; i < length; i++) {
	                byte ch = buffer[i];
	                switch (ch) {
	                case '\n':
	                    seenCR = false;
                        System.out.println(line.toString());
	                    currentPosition = reader.getFilePointer();
	                    line.setLength(0);
	                    break;
	                case '\r':
	                    seenCR = true;
	                    currentPosition = reader.getFilePointer();
	                    break;
	                default:
	                    if (seenCR) {
	                        seenCR = false;
	                        System.out.println(line.toString());
	                        line.setLength(0);
	                    }
	                    line.append((char) ch);
	                }
	            }
	        }
			
			filePointer_ = currentPosition;
		}
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
				} catch (IOException e) {
					e.printStackTrace();
				} catch (InterruptedException e) {
					return;
				}
			}
		}
		
	}

	public static void main(String[] args) {
		Tailer tailer = new Tailer(new File("data/sample.log"));
		MyThread thread = new MyThread(tailer, 30000);
		thread.start();
	}
}
