package Vistas;


import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.log4j.BasicConfigurator;
//import org.apache.log4j.FileAppender;
import org.apache.log4j.Logger;
//import org.apache.log4j.PatternLayout;


public class pruebaLog {

	private final static Logger log = Logger.getLogger(pruebaLog.class);
	 
	public static void main(String[] args) throws IOException {
		
		
		Calendar fecha =  Calendar.getInstance();
		SimpleDateFormat sfd = new SimpleDateFormat(" dd/MM/YYYY - HH:mm:ss");
		BasicConfigurator.configure();
		
		
		log.warn("un warning"+ sfd.format(fecha.getTime()));
		log.error("un error" + sfd.format(fecha.getTime()));
	}

	
}
