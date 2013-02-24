package jp.dragon.field.jdk7;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.spi.FileSystemProvider;
import java.util.HashMap;
import java.util.Map;

public class JDK7ZipWalker {

	private static FileVisitor<Path> visitor_ = new SimpleFileVisitor<Path>() {
		@Override
		public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
			System.out.println("Visit File: " + file);
			
			try (BufferedReader reader = Files.newBufferedReader(file, StandardCharsets.UTF_8)) {
			
			String dataLine = null;
			while ((dataLine = reader.readLine()) != null) {
				System.out.println(dataLine);
			}
		}
			
			return FileVisitResult.CONTINUE;
		}
	};
	
	public static void main(String[] args) throws Exception {
		URI zipfile = URI.create("jar:" + new File("data/sample.zip").toURI().toString());
		Map<String, String> env = new HashMap<>();
		env.put("create", "false");
		try (FileSystem zipfs = FileSystems.newFileSystem(zipfile, env)) {
			Files.walkFileTree(zipfs.getPath("/"), visitor_);			
		}		
	}

}
