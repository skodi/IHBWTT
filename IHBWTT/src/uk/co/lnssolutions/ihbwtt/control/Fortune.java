package uk.co.lnssolutions.ihbwtt.control;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Fortune {

	    private static Properties prop = new Properties();
	    private static Boolean jsonRpcRequest;
	    private static String applicationKey;
	    private static String sessionToken;
	    private static String jsonOrRescript;
	    private static boolean debug;

	    static {
	        try {
	        		
	            //InputStream in = Fortune.class.getResourceAsStream("resources/app.properties");
	            FileInputStream fis = new FileInputStream("resources/app.properties");
	        	prop.load(fis);
	            fis.close();

	            debug = new Boolean(prop.getProperty("DEBUG"));
	            System.out.println("Properties file loaded");
	            
	        } catch (IOException e) {
	            System.out.println("Error loading the properties file: "+e.toString());
	        }
	    }

		public static Properties getProp() {
			return prop;
		}

		public static void setProp(Properties prop) {
			Fortune.prop = prop;
		}

}
