package com.practice.utility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigPropertiesUtility {
	private Properties properties;

	public ConfigPropertiesUtility() {
		super();
		try {
			properties = new Properties();
			FileInputStream fileInputStream = new FileInputStream("src/test/java/resources/config.properties");
			properties.load(fileInputStream);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Properties getPropertyInstance(){
		return properties;
	}
	
}
