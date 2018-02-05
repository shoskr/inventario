package Vistas;


import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;


import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;



public class pruebaLog {

	private final static Logger log = Logger.getLogger(pruebaLog.class);
	Calendar fecha =  Calendar.getInstance();
	SimpleDateFormat sfd2 = new SimpleDateFormat(" dd/MM/YYYY - HH:mm:ss");
	 
	public static void main(String[] args) throws IOException {
		
		PropertyConfigurator.configure("log4j.properties");
		Calendar fecha =  Calendar.getInstance();
		SimpleDateFormat sfd2 = new SimpleDateFormat(" dd/MM/YYYY - HH:mm:ss");
		
		
		
		
		log.warn("un warning"+ sfd2.format(fecha.getTime()));
		log.error("un error" + sfd2.format(fecha.getTime()));
	}

	
}
