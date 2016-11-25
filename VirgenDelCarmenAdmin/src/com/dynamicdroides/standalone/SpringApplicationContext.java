package com.dynamicdroides.standalone;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringApplicationContext implements ApplicationContextAware {

    private static ApplicationContext CONTEXT;

    public void setApplicationContext(ApplicationContext context) throws BeansException { 
        CONTEXT = context; 
    }

    public static Object getBean(String beanName) { 
    	if(CONTEXT==null){
    		CONTEXT = 
                new ClassPathXmlApplicationContext("virgin-servlet.xml");
    	}
    	
        return (CONTEXT != null) ? CONTEXT.getBean(beanName) : null; 
    } 
}