package com.dynamicdroides.tools;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringApplicationContexter {

    private static ApplicationContext CONTEXT;


    public static Object getBean(String beanName) { 
    	if(CONTEXT==null){
    		CONTEXT = 
                new ClassPathXmlApplicationContext("autanacrm-servlet.xml");
    	}
    	
        return CONTEXT.getBean(beanName); 
    } 
}
