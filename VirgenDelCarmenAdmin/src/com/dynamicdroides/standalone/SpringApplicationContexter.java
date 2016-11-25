package com.dynamicdroides.standalone;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringApplicationContexter {

    private static ApplicationContext CONTEXT;


    public static Object getBean(String beanName) { 
    	if(CONTEXT==null){
    		CONTEXT = 
                new ClassPathXmlApplicationContext("virgin-servlet.xml");
    	}
    	
        return CONTEXT; 
    } 
}
