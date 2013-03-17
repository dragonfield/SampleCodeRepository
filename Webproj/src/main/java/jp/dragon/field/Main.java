package jp.dragon.field;

import jp.dragon.field.filter.FilterManager;
import jp.dragon.field.store.DataStore;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

	public static void main(String[] args) {
		String[] record = new String[]{"aaa", "bbb", "ccc"};
		
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		FilterManager manager = context.getBean(FilterManager.class);
		String[] result = manager.doFilter(record);
		
		for (int i = 0; i < result.length; i++) {
			System.out.println(result[i]);
		}
	}

}
