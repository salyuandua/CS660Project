package edu.marshall.twitterAPI;

import java.awt.image.DataBuffer;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Base64;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import twitter4j.BASE64Encoder;

public class HttpClientBase implements HttpClient{
private HttpURLConnection con;
private URL url;
	public HttpClientBase() {
		
	}
	private void connect(HttpRequest req, Authentication auth){
		try {
			//set public properties
			con=(HttpURLConnection) url.openConnection();
			con.setDoInput(true);
			con.setDoOutput(true);
			con.setConnectTimeout(req.getConf().getTimeOut());
			con.setUseCaches(req.getConf().isCache());
			con.setRequestProperty("User-Agent", req.getConf().getUserAgent());
			con.setRequestProperty("Content-Type", req.getConf().getContentType());
			//set authentication
			String str= getAuthString(auth);
			con.addRequestProperty("Authorization",str);
			//System.out.println("*****"+con.getRequestProperty("Authorization"));
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private String getAuthString(Authentication auth){
//		StringBuilder authString=new StringBuilder("OAuth oauth_consumer_key='"+auth.getOauthConsumerKey()+"',");
//		authString.append("\noauth_nonce='"+auth.getOauthNonce()+"',");
//		authString.append("\noauth_signature='"+auth.getOauthSignature()+"',");
//		authString.append("\noauth_signature_method='"+Authentication.oauthSignatureMethod+"',");
//		authString.append("\noauth_timestamp='"+auth.getTimeStamp()+"',");
//		authString.append("\noauth_token='"+auth.getOauthToken()+"',");
//		authString.append("\noauth_version='"+Authentication.oauthVersion+"'");
		StringBuilder authString=new StringBuilder("OAuth oauth_consumer_key=\""+auth.getOauthConsumerKey()+"\",");
		authString.append("oauth_nonce=\""+auth.getOauthNonce()+"\",");
		authString.append("oauth_signature=\""+auth.getOauthSignature()+"\",");
		authString.append("oauth_signature_method=\""+Authentication.oauthSignatureMethod+"\",");
		authString.append("oauth_timestamp=\""+auth.getTimeStamp()+"\",");
		authString.append("oauth_token=\""+auth.getOauthToken()+"\",");
		authString.append("oauth_version=\""+Authentication.oauthVersion+"\"");
		
		
		try {
			String str= URLEncoder.encode(authString.toString(), "UTF-8");
			System.out.println("&&&&&&&&&&&&&&&&&&&&&&"+str);
			return str;
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
	@Override
	public HttpResponse get(HttpRequest req, Authentication auth) {
		try {
			url=new URL(req.getFinalUrl(HttpMethods.GET));
			
			connect(req, auth);
			System.out.println(this.toString());
			System.out.println("code: "+con.getResponseCode());
			System.out.println(con.getResponseMessage());
			
			InputStream in=con.getErrorStream();
			byte[] b=new byte[1024];
			int len=0;
			StringBuilder str=new StringBuilder();
			while((len=in.read(b))!=-1){
				System.out.print((char)in.read(b, 0, len));
			}
			//System.out.println("&&&"+str.toString());
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
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
	public String toString(){
		StringBuilder str=new StringBuilder("Request Header:\n");
		for(Entry<String, List<String>> map:con.getRequestProperties().entrySet()){
			String key=map.getKey();
			List<String> values=map.getValue();
			str.append(key+":");
			for(String value:values){
				str.append(value+",");
			}
			str.append("\n");
		}
		return str.toString();
	}

public static void main(String[] args) {
	String url="https://api.twitter.com/1.1/users/search.json";
	HttpRequest req=new HttpRequest(url, null);
	System.out.println(req.getConf().getTimeOut());
	Authentication auth=new Authentication(url);
	HttpClientBase http=new HttpClientBase();
	http.get(req, auth);
	
}
}
