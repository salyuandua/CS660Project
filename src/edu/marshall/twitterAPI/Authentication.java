package edu.marshall.twitterAPI;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.Timestamp;
import java.util.Date;
import java.util.Formatter;
import java.util.UUID;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import twitter4j.BASE64Encoder;

public class Authentication {
	public static final String oauthSignatureMethod="HMAC-SHA1";
	public static final String oauthVersion="1.0";
	private String oauthConsumerKey;
	private String oauthSignature;

	private String oauthToken;
	private String oauthConsumerSecret;
	private String oauthTokenSecret;
	private String oauthTimeStamp;
	private String oauthNonce;
	private String url;
	public Authentication(String url) {
		//read consumer key and token
		this(url,ConfigReader.get("consumerKey"), ConfigReader.get("accessToken"),
				ConfigReader.get("consumerSecret"),ConfigReader.get("accessTokenSecret"));

	}
	public Authentication(String url,String oauthConsumerKey,String oauthToken,
			String oauthConsumerSecret,String oauthTokenSecret){
		this.oauthConsumerKey=oauthConsumerKey;
		this.oauthToken=oauthToken;
		this.oauthConsumerSecret=oauthConsumerSecret;
		this.oauthTokenSecret=oauthTokenSecret;
		this.url=url;
		generateSignature();
	}
	
	private void generateSignature(){
		StringBuilder base=new StringBuilder(url);
		base.append("&include_entities=true");
		base.append("&oauth_consumer_key="+oauthConsumerKey);
		base.append("&oauth_nonce="+getOauthNonce());
		base.append("&oauth_signature_method="+oauthSignatureMethod);
		base.append("&oauth_timestamp="+getTimeStamp());
		base.append("&oauth_token="+oauthToken);
		base.append("&oauth_version="+oauthVersion);
		System.out.println(base.toString());
		try {
			String encodedBase="GET&"+URLEncoder.encode(base.toString(), "UTF-8");
			//System.out.println(encodedBase);
			String key=URLEncoder.encode(oauthConsumerSecret, "UTF-8")+"&"
			+URLEncoder.encode(oauthTokenSecret, "UTF-8");
			//System.out.println(key);
			SecretKeySpec sKeySpec=new SecretKeySpec(key.getBytes(), "HmacSHA1");
			Mac mac=Mac.getInstance("HmacSHA1");
			mac.init(sKeySpec);
			byte[] bArray=mac.doFinal(encodedBase.getBytes());
			oauthSignature=BASE64Encoder.encode(bArray);
			
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public String getTimeStamp(){
		if(oauthTimeStamp==null){
			oauthTimeStamp=(System.currentTimeMillis()/1000)+"";
		}
		return oauthTimeStamp;
	}
	public String getOauthNonce() {
		if(oauthNonce==null){
			oauthNonce=UUID.randomUUID().toString().replace("-", "");
		}
		return oauthNonce;
	}
	public String getOauthConsumerKey() {
		return oauthConsumerKey;
	}

	public String getOauthSignature() {
		return oauthSignature;
	}

	public String getOauthToken() {
		return oauthToken;
	}


public static void main(String[] args) {
//	System.out.println("kYjzVBB8Y0ZFabxSWbWovY3uYSQ2pTgmZeNu2VS4cg".length());
//	System.out.println(UUID.randomUUID().toString().replace("-", "").length());
	//System.out.println(System.currentTimeMillis()/1000);
	Authentication a=new Authentication("http://www.baidu.com?q=erer");
	System.out.println(a.getOauthSignature());
}
}
