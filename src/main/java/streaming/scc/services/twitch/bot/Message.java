package streaming.scc.services.twitch.bot;

public class Message {
	
	private String name;
	
	private String message;
	
	private String channel;
	
	
	public Message(String channel, String name, String message) {
		super();
		this.channel = channel;
		this.name = name;
		this.message = message;
	}

	public String getName() {
		return name;
	}


	public String getMessage() {
		return message;
	}
	
	public String getChannel() {
		return channel;
	}

	@Override
	public String toString() {
		return "["+channel+"] "+ name + ": "+ message;
	}
	
	


}
