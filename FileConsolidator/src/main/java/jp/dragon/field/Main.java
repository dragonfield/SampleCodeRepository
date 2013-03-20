package jp.dragon.field;


import java.io.File;

import org.apache.commons.io.FileUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

	public static void main(String[] args) throws Exception {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		//File dir = new File("C:/work/201303/20130320");
		//FileUtils.copyFileToDirectory(new File("pom.xml"), dir);
	}

}
