package streaming.scc.config;

/**
 * Enthaelt alle moeglichen konfigurierbaren Konfigurationsschluessel.
 * 
 * Idee ist, dass man dan hier auch zentral dokumentieren kann,
 * was diese Schlüssel bewirken.
 * 
 * 
 * @author d33pfr13d
 *
 */
public enum ConfigKey {
	
	/**
	 * Soll das UI "Always on top" gezeigt werden?
	 */
	UI_PRESENTATION_ON_TOP("ui.presentation.on.top"),
	
	/**
	 * width of the main frame
	 */
	UI_MAIN_FRAME_WIDTH("ui.main.frame.width"),
	
	/**
	 * height of the main frame
	 */
	UI_MAIN_FRAME_HEIGHT("ui.main.frame.height"),
	
	/**
	 * width of the video frame
	 */
	UI_VIDEO_FRAME_WIDTH("ui.video.frame.width"),
	
	/**
	 * height of the video frame
	 */
	UI_VIDEO_FRAME_HEIGHT("ui.video.frame.height"),
	
	/**
	 * Username on Mixer
	 */
	SERVICE_MIXER_USER_NAME("service.mixer.user.name"),
	
	/**
	 * Client_ID für den Login mit dem Mixer REST Service
	 */
	SERVICE_MIXER_CLIENT_ID("service.mixer.client.id"),
	
	/**
	 * Client_Key für den Login mit dem Mixer REST Service
	 */
	SERVICE_MIXER_CLIENT_KEY("service.mixer.client.key"),
	
	/**
	 * Alle wieviel Sekunden sollen die Infos aus Mixer bezogen werden?
	 */
	SERVICE_MIXER_UPDATE_INTERVAL("service.mixer.update.interval"),
	
	/**
	 * Pfad zum VLC Programm Ordner
	 */
	SERVICE_VLC_BIN_PATH("service.vlc.bin.path"),
	
	/**
	 * Pfad zum video was vor angegeben sein soll im UI.
	 * (Temporaer da das zukuenftig dynamischer konfiguriert werden soll)
	 */
	SERVICE_VLC_VIDEO_DEFAULT("service.vlc.video.default"),
	
	/** Breite für die Video Wiedergabe */
	SERVICE_VLC_VIDEO_WIDTH("service.vlc.video.width"),
	
	/** Oauth token for the twitch bot */
	SERVICE_TWITCH_BOT_OAUTH("service.twitch.bot.oauth"),

	/** Name of the primary twitch channel of the broadcaster with leading # */
	SERVICE_TWITCH_BOT_PRIMARY_CHANNEL("service.twitch.bot.primary.channel"),
	
	/** Name of the secondary twitch channel of the broadcasters co-streamer with leading # */
	SERVICE_TWITCH_BOT_SECONDARY_CHANNEL("service.twitch.bot.secondary.channel"),
	
	/** Mode of chat replication:
	 * off = no replication
	 * bidirectional = reads from and to secondary channel
	 * reading = reads from secondary channel and posts to own
	 * writing = writes own messages to secondary channel
	 */
	SERVICE_TWITCH_BOT_REPLICATION_MODE("service.twitch.bot.replication.mode"),
	
	/**
	 * Standard Tweet beim live gehen
	 * (Auch temporaer da das alles dynamischer werden wird)
	 */
	INFO_USER_TWEET_LIVE("info.user.tweet.live");
	
	
	
	
	private final String key;
	
	private ConfigKey(String key) {
		this.key = key;
	}

	public String getKey() {
		return key;
	}

}
