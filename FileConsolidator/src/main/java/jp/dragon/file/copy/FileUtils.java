package jp.dragon.file.copy;

import java.io.File;
import java.io.FileFilter;
import java.io.ObjectInputStream.GetField;
import java.util.Date;

import jp.dragon.field.config.Metric;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOCase;
import org.apache.commons.io.filefilter.FileFilterUtils;
import org.apache.commons.io.filefilter.NameFileFilter;
import org.apache.commons.io.filefilter.WildcardFileFilter;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;

public final class FileUtils {

	private static final String DATE_FORMAT = "yyyyMMdd";
	private static final String DETAIL_DATE_FORMAT = "yyyyMMddHHmm";
	
	public static void main(String[] args) throws Exception {
		Date date = DateUtils.parseDate("20130311", "yyyyMMdd");
		String test = getMetricPostWildcardMatcher("activeses", "instance", date);
		System.out.println(test);
		System.out.println(FilenameUtils.wildcardMatch("activeses_instance.log_20130311000002", test, IOCase.INSENSITIVE));
	}
	
	public static Date getToday() {
		return new Date();
	}

	public static Date getYesterday() {
		return DateUtils.addDays(new Date(), -1);
	}
	
	public static File[] getReportFiles(File dir, String instance, Date date) {
		File[] result = null;
		FileFilter fileFilter = getReportFileFilter(instance, date);
		result = dir.listFiles(fileFilter);
		return result;
	}
	
	public static File[] getMetricFiles(File dir, String metric, String instance, Date date, String prepost) {
		File[] result = null;
		FileFilter fileFilter = null;
		
		if (Metric.DATE_PRE.equals(prepost)) {
			fileFilter = getMetricPreFileFilter(metric, instance, date);
		} else if (Metric.DATE_POST.equals(prepost)) {
			fileFilter = getMetricPostFileFilter(metric, instance, date);
		} else {
			fileFilter = getMetricFileFilter(metric, instance);
		}

		result = dir.listFiles(fileFilter);
		return result;
		
	}

	public static FileFilter getReportFileFilter(String instance, Date date) {
		return new WildcardFileFilter(getReportWildcardMatcher(instance, date), IOCase.INSENSITIVE);
	}
	
	public static FileFilter getMetricFileFilter(String metric, String instance) {
		return new NameFileFilter(getMetricFileName(metric, instance), IOCase.INSENSITIVE);
	}
	
	public static FileFilter getMetricPreFileFilter(String metric, String instance, Date date) {
		return new WildcardFileFilter(getMetricPreWildcardMatcher(metric, instance, date), IOCase.INSENSITIVE);
	}
	
	public static FileFilter getMetricPostFileFilter(String metric, String instance, Date date) {
		return new WildcardFileFilter(getMetricPostWildcardMatcher(metric, instance, date), IOCase.INSENSITIVE);
	}
	
	static String getReportWildcardMatcher(String instance, Date date) {
		StringBuffer result = new StringBuffer();
		result.append(instance).append("_");
		result.append("*");
		result.append("-").append(DateFormatUtils.format(date, DATE_FORMAT));
		result.append("*").append(".lst");
		
		return result.toString();
	}
	
	static String getMetricFileName(String metric, String instance) {
		StringBuffer result = new StringBuffer();
		result.append(metric).append("_");
		result.append(instance).append(".log");

		return result.toString();
	}

	static String getMetricPreWildcardMatcher(String metric, String instance, Date date) {
		StringBuffer result = new StringBuffer();
		result.append(metric).append("_");
		result.append(instance).append("_");
		result.append(DateFormatUtils.format(date, DATE_FORMAT));
		result.append("*").append(".log");
		
		return result.toString();
	}
	
	static String getMetricPostWildcardMatcher(String metric, String instance, Date date) {
		StringBuffer result = new StringBuffer(getMetricFileName(metric, instance));
		result.append("_");
		result.append(DateFormatUtils.format(date, DATE_FORMAT));
		result.append("??????");
		
		return result.toString();
	}
}
