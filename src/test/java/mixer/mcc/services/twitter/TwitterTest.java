package mixer.mcc.services.twitter;

import org.junit.Test;

public class TwitterTest {
	
	@Test
	public void testTweeting() throws Exception {
		new TwitterBot().tweet("d33pfr13d's Chatbot haelt euch informiert, wann es auf Mixer ab geht ;-)");
		
	}

}
