package com.practice.utility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class LoggerUtility {

	public LoggerUtility() {
		super();
		try {
			FileInputStream fileInputStream = new FileInputStream("src/test/java/resources/log4j.properties");
			PropertyConfigurator.configure(fileInputStream);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
