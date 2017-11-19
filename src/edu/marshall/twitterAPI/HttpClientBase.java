package edu.marshall.twitterAPI;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class HttpClientBase implements HttpClient{
private HttpURLConnection con;
private URL url;
	public HttpClientBase() {
		
	}
	private void connect(HttpRequest req, Authentication auth){
		try {
			//set public properties
			con=(HttpURLConnection) url.openConnection();
			con.setConnectTimeout(req.getConf().getTimeOut());
			con.setUseCaches(req.getConf().isCache());
			con.setRequestProperty("User-Agent", req.getConf().getUserAgent());
			con.setRequestProperty("Content-Type", req.getConf().getContentType());
			//set authentication
			
			
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private String getAuthString(){
		
	}
	
	
	@Override
	public HttpResponse get(HttpRequest req, Authentication auth) {
		try {
			url=new URL(req.getUrl(HttpRequest.GET));
			connect(req, auth);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public HttpResponse post(HttpRequest req, Authentication auth) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HttpResponse delete(HttpRequest req, Authentication auth) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HttpResponse put(HttpRequest req, Authentication auth) {
		// TODO Auto-generated method stub
		return null;
	}

}
