package sample1;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class HelloApp {

    public static void main(String[] args) {

        ApplicationContext context = new ClassPathXmlApplicationContext("beans1.xml");

    }
    
}