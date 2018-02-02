package Vistas;


import org.apache.log4j.PropertyConfigurator;

import java.io.IOException;

import org.apache.log4j.FileAppender;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;


public class pruebaLog {

	private final static Logger log = Logger.getLogger(pruebaLog.class);
	 
	public static void main(String[] args) throws IOException {
		log.addAppender(new FileAppender(new PatternLayout(),"prueba.log", false));
		PropertyConfigurator.configure("log.log4j.properties");
 
		log.warn("un warning");
		log.error("un error");
	}

	
}
