package jp.dragon.field.truezip;

import java.io.BufferedReader;
import java.io.IOException;

import org.apache.commons.io.IOUtils;

import de.schlichtherle.truezip.file.TFile;
import de.schlichtherle.truezip.file.TFileReader;

public class TrueZipWalker {

	public static void main(String[] args) throws Exception {
		TFile zipFile = new TFile("data/sample2.zip");
		fileWalk(zipFile);
				
	}

	public static void fileWalk(TFile file) throws IOException {
		TFile[] children = file.listFiles();
		
		for (int i = 0; i < children.length; i++) {
			if (children[i].isFile()) {
				System.out.println(children[i].getName());
				
				BufferedReader reader = new BufferedReader(new TFileReader(children[i]));
				try {
					System.out.println(IOUtils.toString(reader));
					
				} finally {
					reader.close();
				}
				
			} else if (children[i].isDirectory()) {
				fileWalk(children[i]);
				
			} else {
				System.out.println(children[i].getAbsolutePath());
			}
			
		}

	}
	
}
