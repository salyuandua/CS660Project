package test;

import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.User;
import twitter4j.auth.RequestToken;

public class TweetTest {

public static void main(String[] args) {
	Twitter twitter=TwitterFactory.getSingleton();
	try {
		ResponseList<User> userList=twitter.lookupUsers("xuejian_li");
		System.out.println(userList.size());
		User user=userList.get(0);
		System.out.println(user.getName());
		Status status=user.getStatus();
	} catch (TwitterException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}

}
