package jp.dragon.field;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

public class MyService {

	public void sayHello() {
		System.out.println("Hello World.");
	}

}
