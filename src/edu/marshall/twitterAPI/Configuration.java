package edu.marshall.twitterAPI;

import org.junit.Test;

public class Configuration {
public static final String baseURL="https://api.twitter.com"; 
//	public Configuration(Authentication auth,HttpConfig httpConf) {
//		
//	}
//	
//	public Configuration(Authentication auth){
//		
//	}
//	public Configuration(){
//		
//	}
/**
 * remove paramters located ? in url
 * @param url
 * @return
 */

public static String constructureURL(String url){
	if(url.contains("?")){
		int index=url.indexOf("?");
		url=url.substring(0, index);
	}
	return url;
}
public static void main(String[] args) {
	System.out.println(	constructureURL("http://dsada.com?ds"));

}
}
