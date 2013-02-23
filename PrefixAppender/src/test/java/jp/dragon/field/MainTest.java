package jp.dragon.field;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import java.io.File;
import java.util.HashMap;
import java.util.Properties;

import org.junit.Test;

public class MainTest {

	@Test
	public void testParsePrefix() {
		String expected = "/Domain1/System1/subSystem1";
		String actual = Main.parsePrefix("/Domain1/System1/subSystem1/HW/host1");		
		assertThat(actual, is(expected));
	}
	
	@Test
	public void testExtractKey() {
		String expected = "host1";
		String actual = Main.extractKey("/MW/DB/host1/instanceA", 3);
		assertThat(actual, is(expected));
	}

	@Test
	public void testBuildPrefixMap() {
		HashMap<String, String> expected = new HashMap<String, String>();
		expected.put("host1", "/Domain1/System1/subSystem1/HW/host1");
		expected.put("host2", "/Domain2/System2/subSystem1/HW/host2");
		expected.put("host3", "/Domain3/System3/subSystem1/HW/host3");
		expected.put("host4", "/Domain4/System4/subSystem1/HW/host4");

		HashMap<String, String> actual = Main.buildPrefixMap(new File("data/hw_sample.txt"));

		assertThat(actual, is(expected));
	}
	
	@Test
	public void testParseArguments() {
		Properties expected = new Properties();
		expected.put("-s", "source.txt");
		expected.put("-t", "target.txt");
		expected.put("-o", "result.txt");

		Properties actual = Main.parseArguments(new String[]{"-s", "source.txt", "-t", "target.txt", "-o", "result.txt"});
		
		assertThat(actual, is(expected));
	}
}
