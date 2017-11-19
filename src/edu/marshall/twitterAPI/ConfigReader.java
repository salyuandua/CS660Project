package edu.marshall.twitterAPI;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
/**
 * {@link ConfigReader} should be used for load configuration file "twitter_conf.properties"
 * @author Xuejian Li
 *
 */
public class ConfigReader {
private static Properties properties;
	static{
		File file=new File(Test.class.getResource("/twitter_conf.properties").getPath());
		if(file.exists()){
			properties=new Properties();
			try {
				properties.load(new FileReader(file));
			} catch (IOException e) {
				
				e.printStackTrace();
			}
		}
	}
/**
 * 	get specific value depends on given key from properties file
 * @param key
 * @return value
 */
public static String get(String key){
	if(properties==null||key==null){
		throw new NullPointerException();
	}
	return properties.getProperty(key);
}
}
