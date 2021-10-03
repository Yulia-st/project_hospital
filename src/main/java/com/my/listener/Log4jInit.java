package com.my.listener;

import java.io.File;
import org.apache.log4j.PropertyConfigurator;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class Log4jInit implements ServletContextListener{
	public void contextInitialized(ServletContextEvent event) {
        //String homeDir=event.getServletContext().getRealPath("/");
        //File propertiesFile=new File(homeDir,"log4j.properties");
        //PropertyConfigurator.configure(propertiesFile.toString());
		PropertyConfigurator.configure(getClass().getResource("/log4j.properties"));
    }
    
    public void contextDestroyed(ServletContextEvent event) {} 
}
