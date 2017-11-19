package edu.marshall.twitterAPI;

import java.util.Date;

public class Authentication {
	private String oauthConsumerKey;
	private String oauthNonce;
	private String oauthSignature;
	private String oauthSignatureMethod;
	private String oauthToken;
	private String oauthVersion;
	public Authentication() {
		
	}
	public String getTimeStamp(){
		return (""+new Date().getTime()/1000);
	}
	public String getOauthConsumerKey() {
		return oauthConsumerKey;
	}
	public void setOauthConsumerKey(String oauthConsumerKey) {
		this.oauthConsumerKey = oauthConsumerKey;
	}
	public String getOauthNonce() {
		return oauthNonce;
	}
	public void setOauthNonce(String oauthNonce) {
		this.oauthNonce = oauthNonce;
	}
	public String getOauthSignature() {
		return oauthSignature;
	}
	public void setOauthSignature(String oauthSignature) {
		this.oauthSignature = oauthSignature;
	}
	public String getOauthSignatureMethod() {
		return oauthSignatureMethod;
	}
	public void setOauthSignatureMethod(String oauthSignatureMethod) {
		this.oauthSignatureMethod = oauthSignatureMethod;
	}
	public String getOauthToken() {
		return oauthToken;
	}
	public void setOauthToken(String oauthToken) {
		this.oauthToken = oauthToken;
	}
	public String getOauthVersion() {
		return oauthVersion;
	}
	public void setOauthVersion(String oauthVersion) {
		this.oauthVersion = oauthVersion;
	}

}
