package Vistas;

import java.io.IOException;


import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;


public class pruebaLog {

	private final static Logger log = Logger.getLogger(pruebaLog.class);


	public static void main(String[] args) throws IOException {

		PropertyConfigurator.configure("log4j.properties");
		for(int c = 0; c <=100 ;c++) {
			log.warn("Prueba " + c);
		}
		
		
	}

}
