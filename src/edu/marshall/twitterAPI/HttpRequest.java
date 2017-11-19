package edu.marshall.twitterAPI;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class HttpRequest {
public static final int GET=0;
public static final int POST=1;
private Map<String, String> params=new HashMap<String,String>();
private String url;
private HttpConfig conf;
	public HttpRequest(String url,Map<String, String> params, HttpConfig httpConfig) {
		if(httpConfig==null){
			throw new NullPointerException("httpConfig should not be null");
		}
		if(params!=null){
			this.params=params;
			encodeParams();
		}
		this.url=url;
		
	}
	public HttpRequest(String url,Map<String, String> params){
		this(url,params,new HttpConfig());
	}
	private void encodeParams(){
		for(Entry<String, String> e:params.entrySet()){
			try {
				String value=URLEncoder.encode(e.getValue(), "UTF-8");
				params.put(e.getKey(), value);
			} catch (UnsupportedEncodingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	public Map<String, String> getParams() {
		return params;
	}
	public void setParams(Map<String, String> params) {
		this.params = params;
	}
	public String getUrl(int httpMethod) {
		if(params.isEmpty()){
			return url;
		}
		//have paramters
		
		StringBuilder urlStr=new StringBuilder(url);
		if(httpMethod==GET){//get 
			boolean isFirst=true;
			for(Entry<String, String> e:params.entrySet()){
				if(isFirst){
					urlStr.append("?");
					isFirst=false;
				}else{
					urlStr.append("&");
				}
				urlStr.append(e.getKey()+"="+e.getValue());
			}
		}
		return urlStr.toString();
	}

	public HttpConfig getConf() {
		return conf;
	}
	public static void main(String[] args) {
		String url="http://wocao.com";
		Map<String, String> params=new HashMap<String,String>();
		params.put("name", "lixuejian");
		params.put("age", "&$#13");
		HttpRequest req=new HttpRequest(url, params);
		System.out.println(req.getUrl(GET));
	}

}
