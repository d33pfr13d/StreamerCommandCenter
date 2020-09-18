package streamer.scc.services.twitter;

import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

public class TwitterBot {
	
	//TODO -test switch oder irgendwas erkennen
	private static final boolean TEST_MODE = false;
	
	// The factory instance is re-useable and thread safe.
	private Twitter twitter = TwitterFactory.getSingleton();
	
	public TwitterBot() {
		super();
	}
	
	public void tweet(String txt) {
		try {
			Status status;
			if(!TEST_MODE) {
				status = twitter.updateStatus(txt);
				System.out.println("Successfully updated the status to [" + status.getText() + "].");
			}
			else {
				System.out.println("TEST_MODE: NOT sending text to twitter: "+txt);
			}
		} catch (TwitterException e) {
			throw new RuntimeException(e);
		}
	}

}
