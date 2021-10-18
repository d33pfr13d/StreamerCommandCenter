package streaming.scc.services.twitch.bot;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.jibble.pircbot.*;

import jonas.tools.time.TimeStampOperations;
import streaming.scc.command.PlayVideoInInternalCommand;
import streaming.scc.command.ShoutOutInternalCommand;
import streaming.scc.config.ConfigKey;
import streaming.scc.config.Configuration;
import streaming.scc.services.clips.VideoClip;

/**
 * credit where credit is due:
 * https://stackoverflow.com/questions/51484971/java-twitch-irc-bot
 * 
 * @author d33pfr13d
 *
 */
public class ChatReplicatorBot extends PircBot {
	
	private static ChatReplicatorBot bot;
	
	private static String mainChannelname;

	private static String secondaryChannelname;

	private MessageSplitter ms = new MessageSplitter();
	
	private static String replicationMode = Configuration.getConfiguration().getConfigValue(ConfigKey.SERVICE_TWITCH_BOT_REPLICATION_MODE);
	
	private static final Map<String, VideoClip> videoCommands = new HashMap<String, VideoClip>();
	
	private BufferedWriter chatLogger;
	
	public ChatReplicatorBot() {
//		this.setName("d33pfr13d");
//		this.setLogin("d33pfr13d");
		try {
			chatLogger = new BufferedWriter(new FileWriter(new File("./logs/twitch_chat_"+TimeStampOperations.formatFilename(System.currentTimeMillis()))));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
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
		try {
			long ts = System.currentTimeMillis();
			String formattedMsg = TimeStampOperations.formatDate(ts)+" "+TimeStampOperations.formatTime(ts)+" "+msg.toString();
			System.out.println(formattedMsg);
			chatLogger.newLine();
			chatLogger.write(formattedMsg);
			chatLogger.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Bot commands
		String channel = msg.getChannel();
		if(msg.getMessage().equalsIgnoreCase("time")) {
			String time = new java.util.Date().toString();
			sendMessage("#"+channel, "The time is now " + time);
		}
		else if(isVideoCommand(msg)){
			// replicate command directly, not with name of original typer
			replicate(channel, msg.getMessage());
			
			
			//trigger command - TODO commands are videoclips right now, might be extended to more...
			if(rightToTriggerCommand(msg.getName())) {
				VideoClip clip = videoCommands.get(msg.getMessage());
				PlayVideoInInternalCommand pvc = new PlayVideoInInternalCommand(clip.getVideoFile());
				pvc.execute();
			}
			else {
				System.out.println("Access to video denied for "+msg.getName());
			}
		}
		else if(msg.getMessage().startsWith("!so")) {
			// Shoutout
			// TODO Introduce proper access control list - for now only the streamer can do it
			if(("#"+msg.getName()).equalsIgnoreCase(mainChannelname)) {
				if(msg.getMessage().length()> 3) {
					String promoteName = msg.getMessage().substring(3).trim();
					new ShoutOutInternalCommand(promoteName).execute();
				}
			}
		}
		else {
			//Replicator
			String msgString = msg.toString();
			replicate(channel, msgString);
		}
	}

	/**
	 * For starters we only allow the primary and secondary to trigger videos.
	 * TODO Is it possible to retrieve a list of mods from twitch and check that here? 
	 * @param name
	 * @return
	 */
	private boolean rightToTriggerCommand(String name) {
		return ("#"+name).equalsIgnoreCase(mainChannelname) || ("#"+name).equalsIgnoreCase(secondaryChannelname); 
	}

	private boolean isVideoCommand(Message msg) {
		return videoCommands.containsKey(msg.getMessage());
	}

	private void replicate(String channel, String msgString) {
		if(secondaryChannelname.isEmpty()) {
			// only one channel configured = no replication
			return;
		}
		
		if(("#"+channel).equalsIgnoreCase(mainChannelname) && shouldWriteToSecondary()) {
			sendMessage(secondaryChannelname, msgString);
		}
		else if(("#"+channel).equalsIgnoreCase(secondaryChannelname) && shouldReadFromSecondary()) {
			sendMessage(mainChannelname, msgString);
		}
	}
	
	public void sendMessageToSecondary(String msg) {
		sendMessage(secondaryChannelname, msg);
	}
	
	
	public void addVideoClip(VideoClip clip) {
		videoCommands.put("!"+clip.getKeyword(), clip);
	}

	public static void main(String[] args) throws Exception {

		startBot();

	}

	public static void startBot() throws IOException, IrcException, NickAlreadyInUseException {
		// Now start our bot up.
		getBot();

		// Enable debugging output.
		bot.setVerbose(true);
		
		mainChannelname = Configuration.getConfiguration().getConfigValue(ConfigKey.SERVICE_TWITCH_BOT_PRIMARY_CHANNEL);
		secondaryChannelname = Configuration.getConfiguration().getConfigValue(ConfigKey.SERVICE_TWITCH_BOT_SECONDARY_CHANNEL);

		bot.connect();
	}

	private void connect() throws IOException, IrcException, NickAlreadyInUseException {
		// Connect to the IRC server.
		this.connect("irc.chat.twitch.tv", 6667, Configuration.getConfiguration().getConfigValue(ConfigKey.SERVICE_TWITCH_BOT_OAUTH));

		// Sending special capabilities.
		this.sendRawLine("CAP REQ :twitch.tv/membership");
		this.sendRawLine("CAP REQ :twitch.tv/commands");
		this.sendRawLine("CAP REQ :twitch.tv/tags");

		this.joinChannel(mainChannelname);
		this.sendMessage(mainChannelname, "bot joined the party ;-)");

		if(!secondaryChannelname.isEmpty()) {
			//auch wenn nicht replicated wird, muss man immer dem secondary joinen um commands mit zu bekommen ;)
			this.joinChannel(secondaryChannelname);
			this.sendMessage(secondaryChannelname, "bot joined the party ;-)");
		}
	}
	
	@Override
	protected void onDisconnect() {
		super.onDisconnect();
		try {
			this.connect();
		} catch (IOException | IrcException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static boolean shouldReadFromSecondary() {
		return replicationMode.equalsIgnoreCase("bidirectional") || replicationMode.equalsIgnoreCase("reading");
	}
	
	private static boolean shouldWriteToSecondary() {
		return replicationMode.equalsIgnoreCase("bidirectional") || replicationMode.equalsIgnoreCase("writing");
	}

	//Singleton
	public static ChatReplicatorBot getBot() {
		if(bot == null) {
			bot = new ChatReplicatorBot();
		}
					
		return bot;
	}

}