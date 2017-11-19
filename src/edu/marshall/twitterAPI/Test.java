package edu.marshall.twitterAPI;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Date;
import java.util.Properties;

import javax.net.ssl.HttpsURLConnection;

import twitter4j.HttpClientBase;

public class Test {

public static void main(String[] args) {
//	File file=new File(Test.class.getResource("/twitter_conf.properties").getPath());
//	System.out.println(file.exists());
//	Properties p=new Properties();
//	try {
//		p.load(new FileReader(file));
//		System.out.println(p.getProperty("sds"));
//	} catch (IOException e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	}
//	Date d=new Date();
//	System.out.println(d.getTime()/1000);
	try {
		System.out.println(URLEncoder.encode("?httt//","UTF-8"));
	} catch (UnsupportedEncodingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
}