package jp.dragon.field.truezip;

import de.schlichtherle.truezip.file.TFile;
import de.schlichtherle.truezip.file.TFileWriter;

public class TrueZipArchiver {

	public static void main(String[] args) throws Exception {
		TFile entry = new TFile("result.zip/sample1.csv");
		TFileWriter writer = null;
				
		try {
			writer = new TFileWriter(entry);
			writer.write("0001,Taro,9\n");
			writer.write("0002,Jiro,22\n");
		} finally {
			writer.close();
		}
		
		entry = new TFile("result.zip/sample2.csv");

		try {
			writer = new TFileWriter(entry);
			writer.write("0003,Shintaro,8\n");
			writer.write("0004,Tatsuya,24\n");
		} finally {
			writer.close();
		}
	}

}
