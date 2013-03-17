package jp.dragon.field.filter;

public class MyFilterB implements Filter {

	public String[] doFilter(String[] record) {
		String[] result = new String[record.length];
		
		for (int i = 0; i < record.length; i++ ) {
			result[i] = record[i].toUpperCase();
		}
		
		return result;
	}

}
