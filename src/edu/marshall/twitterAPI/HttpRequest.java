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
		this.conf=httpConfig;
		this.url=url;
		if(params!=null){
			this.params=params;
			encodeParams();
		}
		
		
	}
	public HttpRequest(String url,Map<String, String> params){
		this(url,params,new HttpConfig());
	}
	private void encodeParams(){
		//parse params in url
		if(url.contains("?")){
			String paramStr=url.split("\\?")[1];
			url=url.split("\\?")[0];
			System.out.println(url);
			for(String pair:paramStr.split("&")){
				String key=pair.split("=")[0];
				String value=pair.split("=")[1];
				params.put(key, value);
			}
		}
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
	public String getFinalUrl(HttpMethods method) {
		if(params.isEmpty()){
			return url;
		}
		//have paramters
		
		StringBuilder urlStr=new StringBuilder(url);
		if(method==HttpMethods.GET){//get 
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
		}else if(method==HttpMethods.POST){//post
			
		}
		return urlStr.toString();
	}

	public HttpConfig getConf() {
		return conf;
	}
	public static void main(String[] args) {
		String url="http://wocao.com?q=*/sd";
		Map<String, String> params=new HashMap<String,String>();
		params.put("name", "lixuejian");
		params.put("age", "&$#13");
		HttpRequest req=new HttpRequest(url, params);
		System.out.println(req.getConf());
//		String[] s="httt&sdsa".split("&");
//		System.out.println(s[1]);
	}

}
