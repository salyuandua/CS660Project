package edu.marshall.twitterAPI;
/**
 * 
 * @author Xuejian Li
 *
 */
public interface HttpClient {
	public HttpResponse get(HttpRequest req,Authentication auth);
	public HttpResponse post(HttpRequest req,Authentication auth);
	public HttpResponse delete(HttpRequest req,Authentication auth);
	public HttpResponse put(HttpRequest req,Authentication auth);
}
