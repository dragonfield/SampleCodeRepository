package sample2;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class MessageBeanImpl implements MessageBean  {

	private Log log = LogFactory.getLog(getClass());

	private String name;

	private String greeting;

	public void setName(String name) {
		this.name = name;
	}

	public void setGreeting(String greeting) {
		this.greeting = greeting;
	}
	
	public void init() {
		log.info("initialized");		
	}
	
	public void destroy() {
		log.info("destroyed");		
	}

	public void sayHello() {
		String message = greeting + name + "!";
		log.info("Hello=" + Thread.currentThread().getId() + ":" + message);
	}
}