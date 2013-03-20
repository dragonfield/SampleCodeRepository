package jp.dragon.file.copy;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import java.io.File;
import java.util.Date;

import jp.dragon.field.config.Metric;

import org.apache.commons.lang3.time.DateUtils;
import org.junit.Test;

public class FileUtilsTest {

	@Test
	public void testGetMetricFiles() throws Exception {
		Date date = DateUtils.parseDate("20130311", "yyyyMMdd");

		File[] expected = new File[] {new File("./data/server/activeses/activeses_instance.log")};
		File[] actual = FileUtils.getMetricFiles(new File("./data/server/activeses"), "activeses", "instance", date, null);
		assertThat(actual, is(expected));
		
		expected = new File[] {new File("./data/server/activeses/activeses_instance.log_20130311000002")};
		actual = FileUtils.getMetricFiles(new File("./data/server/activeses"), "activeses", "instance", date, Metric.DATE_POST);
		assertThat(actual, is(expected));		
	}
	
	@Test
	public void testGetMetricFileName() {
		String expected = "activeses_instance.log";
		String actual = FileUtils.getMetricFileName("activeses", "instance");
		assertThat(actual, is(expected));
	}
	
	@Test
	public void testGetMetricPreWildcardMatcher() throws Exception {
		Date date = DateUtils.parseDate("20130130", "yyyyMMdd");
		
		String expected = "activeses_instance_20130130*.log";
		String actual = FileUtils.getMetricPreWildcardMatcher("activeses", "instance", date);
		assertThat(actual, is(expected));
	}	
	
	@Test
	public void testGetMetricPostWildcardMatcher() throws Exception {
		Date date = DateUtils.parseDate("20130130", "yyyyMMdd");
		
		String expected = "activeses_instance.log_20130130??????";
		String actual = FileUtils.getMetricPostWildcardMatcher("activeses", "instance", date);
		assertThat(actual, is(expected));
	}
	
	@Test
	public void testGetReportWildcardMatcher() throws Exception  {
		Date date = DateUtils.parseDate("20130130", "yyyyMMdd");
		
		String expected = "instance_*-20130130*.lst";
		String actual = FileUtils.getReportWildcardMatcher("instance", date);
		assertThat(actual, is(expected));
	}

}
