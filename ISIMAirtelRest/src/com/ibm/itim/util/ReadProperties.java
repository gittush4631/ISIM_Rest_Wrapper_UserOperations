package com.ibm.itim.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ReadProperties {
	public static Properties getProperties() throws IOException {
	String filename="isimcon.properties";
    InputStream input = null;        
    input=ReadProperties.class.getClassLoader().getResourceAsStream(filename);
    if(input==null){
        System.out.println("Sorry, unable to find " + filename);

    }
    
    Properties env = new Properties();
    env.load(input);
    return env;
	}
	
}
