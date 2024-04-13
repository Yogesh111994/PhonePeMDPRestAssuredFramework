package com.qa.phonepemdp.configuration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import com.qa.phonepemdp.frameworkexception.APIException;

public class ConfigurationManager {

	private Properties prop;
	private FileInputStream fis;

	public Properties init() {

		String envName = System.getProperty("env");
		prop = new Properties();

		try {
			if (envName == null) {
				fis = new FileInputStream("./src/main/resources/config/config.properties");
				System.out.println("The test case are running on default env.......qa");
			} else {
				System.out.println("Running test on enviournment: " + envName);
				switch (envName.toLowerCase().trim()) {
				case "qa":
					fis = new FileInputStream("./src/main/resources/config/qa.config.properties");
					break;
				case "dev":
					fis = new FileInputStream("./src/main/resources/config/dev.config.properties");
					break;
				case "stage":
					fis = new FileInputStream("./src/main/resources/config/stage.config.properties");
					break;
				case "prod":
					fis = new FileInputStream("./src/main/resources/config/config.properties");
					break;
				default:
					System.out.println("Please pass the right envName");
					throw new APIException("Wrong EnvName");

				}
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			prop.load(fis);
		} catch (IOException e) {

			e.printStackTrace();
		}
		return prop;
	}
}
