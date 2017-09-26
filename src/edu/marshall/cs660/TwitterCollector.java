package edu.marshall.cs660;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import twitter4j.GeoLocation;
import twitter4j.GeoQuery;
import twitter4j.PagableResponseList;
import twitter4j.Place;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.RateLimitStatus;
import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.User;

public class TwitterCollector {
	private Twitter twitter;
	public TwitterCollector() {
		twitter=new TwitterFactory().getInstance();
	}
/**
 * 
 * @param IDs user's IDs
 * 			filePath the path of file where data will be written
 */
	public void processUsersProfile(long[] IDs,String filePath){
		File file=new File(filePath);
		BufferedWriter bWriter = null;
		try {
			bWriter=new BufferedWriter(new FileWriter(file));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			ResponseList<User> userList=twitter.lookupUsers(IDs);
			Iterator<User> it=userList.listIterator();
			//scan all users
			while(it.hasNext()){
				User user=it.next();//user's information
				bWriter.append("\n");
				bWriter.append("\nUser ID:"+user.getId());
				bWriter.append("\nScreen Name:"+user.getScreenName());
				bWriter.append("\nUser Name:"+user.getName());
				bWriter.append("\nUser Location:"+user.getLocation());
				bWriter.append("\nUser Description:"+user.getDescription());
				bWriter.append("\nThe Number of Followers:"+user.getFollowersCount());
				bWriter.append("\nThe Number of Statuses:"+user.getStatusesCount());
				bWriter.append("\nUser URL:+"+user.getURL());
				System.out.println(user.getScreenName());
				//write to file
				bWriter.flush();
			}
			bWriter.close();
		} catch (TwitterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 
	 * @param IDs user's IDs
	 * @param filePath the path of file where data will be written
	 */
	public void processSocialNet(long[] IDs,String filePath){
		long cursor=-1;
		File f=new File(filePath);
		BufferedWriter bWriter=null;
		try {
			bWriter=new BufferedWriter(new FileWriter(f));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		for(long userID:IDs){
			try{
			//collect followers
			PagableResponseList<User> followersList=twitter.getFollowersList(userID, cursor, 20);
			Iterator<User> it=followersList.iterator();
			//scan followers list

			bWriter.append("The Followers List(ID:"+userID+"):\n\n");
			while(it.hasNext()){
				User follower=it.next();
				bWriter.append(follower.getScreenName()+"\n");
			}
			//writing followers end
			bWriter.flush();
			//collect friends
			PagableResponseList<User> friendsList=twitter.getFriendsList(userID, cursor, 20);
			Iterator<User> it2=followersList.iterator();
			bWriter.append("\nThe Friends List(ID:"+userID+"):\n\n");
			while(it2.hasNext()){
				User friend=it2.next();
				bWriter.append(friend.getScreenName()+"\n");
			}
			bWriter.append("\n\n\n");
			//writing friends end
			bWriter.flush();
			}catch(TwitterException e){
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			bWriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 
	 * @param keyWord a keyWord used search
	 */
	public void searchTweet(String keyWord,String filePath){
		File f=new File(filePath);
		BufferedWriter bWriter=null;
		try {
			bWriter=new BufferedWriter(new OutputStreamWriter(new FileOutputStream(f),"UTF-8"));
			
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//prepare for query
		Query q=new Query(keyWord);
		q.setCount(50);
		try {
		//get query result
		QueryResult queryResult=twitter.search(q);
		List<Status> statusList=queryResult.getTweets();
		bWriter.append("\n\n========================Keyword:"+keyWord+"=======================\n");
		for(Status status:statusList){
			System.out.println(status.getText());
			String newStr=new String(status.getText().getBytes("UTF-8"), "UTF-8");
			//System.out.println(newStr);
			bWriter.append(newStr+"\n");
			
		}
		//writing end
		bWriter.flush();
		bWriter.close();
		} catch (TwitterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void searchTweet(List<double[]> locations){
		for(double[] location:locations){
			GeoLocation l=new GeoLocation(location[0], location[1]);
			
			GeoQuery g=new GeoQuery(l);
			
			try {
				ResponseList<Place> resultList=twitter.searchPlaces(g);

				for(Place place:resultList){
					RateLimitStatus rs=place.getRateLimitStatus();
					System.out.println(place.getName());
				}
			} catch (TwitterException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		

	}
	public static void main(String[] args) {
//		String a="asd无";
//		try {
//			byte[] b=a.getBytes("GBK");
//			String c=new String(b, "UTF-8");
//			System.out.println(a);
//			System.out.println(c);
//		} catch (UnsupportedEncodingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

		TwitterCollector t=new TwitterCollector();
		//t.searchTweet("Indiana", System.getProperty("user.dir")+"/file/tweet_keyword.txt");
		t.searchTweet("weather,", System.getProperty("user.dir")+"/file/tweet_keyword.txt");
		//long[] ids={34373370,26257166,12579252};	
		//t.processUsersProfile(ids,System.getProperty("user.dir")+"/file/user_profile.txt");
		//t.processSocialNet(ids, System.getProperty("user.dir")+"/file/followers_and_friends.txt");
		
	}
}
