package mixer.mcc.config;

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
