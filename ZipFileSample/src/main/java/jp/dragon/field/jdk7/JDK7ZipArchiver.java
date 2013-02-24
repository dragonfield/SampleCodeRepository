package jp.dragon.field.jdk7;

import java.io.BufferedWriter;
import java.io.File;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class JDK7ZipArchiver {

	public static void main(String[] args) throws Exception {
		URI zipfile = URI.create("jar:" + new File("result.zip").toURI().toString());
        Map<String, String> env = new HashMap<>();

        env.put("create", "true");
        env.put("encoding", "MS932");
        try (FileSystem zipfs = FileSystems.newFileSystem(zipfile, env)) {        
			Path newFile = zipfs.getPath("sample1.csv");
			
			try (BufferedWriter writer = Files.newBufferedWriter(newFile, StandardCharsets.UTF_8)) {
				writer.write("0001,Taro,9\n");
				writer.write("0002,Jiro,22\n");
			}
			
			newFile = zipfs.getPath("sample2.csv");
			
			try (BufferedWriter writer = Files.newBufferedWriter(newFile, StandardCharsets.UTF_8)) {
				writer.write("0003,Shintaro,8\n");
				writer.write("0004,Tatsuya,24\n");
			}
			
			Path dir = zipfs.getPath("test");
			Files.createDirectory(dir);
			newFile = zipfs.getPath("test/MANIFEST.MF");

			try (BufferedWriter writer = Files.newBufferedWriter(newFile, StandardCharsets.UTF_8)) {
				writer.write("Hello World\n");
			}
			
        }		
	}

}
