package edu.marshall.twitterAPI;

import java.io.InputStream;
import java.util.Map;

public class HttpResponse {
private int responseCode;
private InputStream in;
private Map<String, Object> responseObj;
	public HttpResponse(int responseCode,InputStream in) {
		if(in!=null){//parse to string
			
		}
		this.responseCode=responseCode;
		this.in=in;
		
	}

}
