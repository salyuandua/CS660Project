package edu.marshall.twitterAPI;
/**
 * {@link HttpConfig} is to config some changable properties of HTTP request
 * @author Xuejian Li
 *
 */
public class HttpConfig {
//default values
private Integer timeOut=5000;
private boolean cache=false;
private String userAgent="edu.marshall.TwitterAPI(v1.0)";
private String contentType="application/x-www-form-urlencoded";
public HttpConfig(){
	readConfig();
}
private void readConfig(){
	String s;
	if((s=ConfigReader.get("http_time_out"))!=null){
		System.out.println(s);
		timeOut=Integer.valueOf(s);
	}
	if((s=ConfigReader.get("use_cache"))!=null){
		cache=Boolean.valueOf(s);
	}
	if((s=ConfigReader.get("user_agent"))!=null){
		userAgent=s;
	}
	
}
public Integer getTimeOut() {
	return timeOut;
}

public boolean isCache() {
	return cache;
}

public String getUserAgent() {
	return userAgent;
}

public String getContentType() {
	return contentType;
}
public void setContentType(String contentType) {
	this.contentType = contentType;
}
public static void main(String[] args) {
	HttpConfig c=new HttpConfig();
	System.out.println(c.contentType+","+c.userAgent+","+c.timeOut+","+c.cache);
	
}
}
