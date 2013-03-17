package jp.dragon.field.filter;

public class MyFilterA implements Filter {

	public String[] doFilter(String[] record) {
		String[] result = new String[record.length];
		System.arraycopy(record, 0, result, 0, record.length);
		result[0] = "Hello";
		
		return result;
	}
}
