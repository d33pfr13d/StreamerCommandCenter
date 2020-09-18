package streaming.scc.services.twitch.bot;

import java.io.IOException;

import org.jibble.pircbot.*;

import streaming.scc.config.ConfigKey;
import streaming.scc.config.Configuration;

/**
 * credit where credit is due:
 * https://stackoverflow.com/questions/51484971/java-twitch-irc-bot
 * 
 * @author jonas
 *
 */
public class MyBot extends PircBot {
	
	private static String mainChannelname;

	private static String secondaryChannelname;

	private MessageSplitter ms = new MessageSplitter();
	
	//TODO in config file auslagern
	private static final String[] specialCommands = { "!sge", "!boat"};
	
	public MyBot() {
//		this.setName("d33pfr13d");
//		this.setLogin("d33pfr13d");
	}

	//XXX Wont trigger with twitch
	public void onMessage(String channel, String sender, String login, String hostname, String message) {
		System.out.println("Received: " + message);
//		if (message.equalsIgnoreCase("time")) {
//			String time = new java.util.Date().toString();
//			sendMessage(channel, sender + ": The time is now " + time);
//		}
	}

	@Override
	protected void handleLine(String line) {
		super.handleLine(line);
		//XXX just for debugging
//		System.out.println("incoming: " + line);

		Message msg = ms.splittIt(line);
		if (msg != null) {
			handleMessage(msg);
		}

	}

	private void handleMessage(Message msg) {
		System.out.println(msg);

		// Bot commands
		String channel = msg.getChannel();
		if(msg.getMessage().equalsIgnoreCase("time")) {
			String time = new java.util.Date().toString();
			sendMessage("#"+channel, "The time is now " + time);
		}
		else if(isSpecialCommand(msg)){
			// replicate command directly, not with name of original typer
			replicate(channel, msg.getMessage());
		}
		else {
			//Replicator
			String msgString = msg.toString();
			replicate(channel, msgString);
			
			
		}
	}

	private boolean isSpecialCommand(Message msg) {
		for(String sc : specialCommands) {
			if(msg.getMessage().equalsIgnoreCase(sc)) {
				return true;
			}
		}
		
		return false;
	}

	private void replicate(String channel, String msgString) {
		if(secondaryChannelname.isEmpty()) {
			// only one channel configured = no replication
			return;
		}
		
		if(("#"+channel).equalsIgnoreCase(mainChannelname)) {
			sendMessage(secondaryChannelname, msgString);
		}
		else if(("#"+channel).equalsIgnoreCase(secondaryChannelname)) {
			sendMessage(mainChannelname, msgString);
		}
	}

	public static void main(String[] args) throws Exception {

		startBot();

	}

	public static void startBot() throws IOException, IrcException, NickAlreadyInUseException {
		// Now start our bot up.
		MyBot bot = new MyBot();

		// Enable debugging output.
		bot.setVerbose(true);

		// Connect to the IRC server.
		bot.connect("irc.chat.twitch.tv", 6667, Configuration.getConfiguration().getConfigValue(ConfigKey.SERVICE_TWITCH_BOT_OAUTH));

		// Sending special capabilities.
		bot.sendRawLine("CAP REQ :twitch.tv/membership");
		bot.sendRawLine("CAP REQ :twitch.tv/commands");
		bot.sendRawLine("CAP REQ :twitch.tv/tags");

		mainChannelname = Configuration.getConfiguration().getConfigValue(ConfigKey.SERVICE_TWITCH_BOT_PRIMARY_CHANNEL);
		bot.joinChannel(mainChannelname);
		bot.sendMessage(mainChannelname, "bot joined the party ;-)");

		secondaryChannelname = Configuration.getConfiguration().getConfigValue(ConfigKey.SERVICE_TWITCH_BOT_SECONDARY_CHANNEL);
		if(!secondaryChannelname.isEmpty()) {
			bot.joinChannel(secondaryChannelname);
			bot.sendMessage(secondaryChannelname, "bot joined the party ;-)");
		}
	}

}