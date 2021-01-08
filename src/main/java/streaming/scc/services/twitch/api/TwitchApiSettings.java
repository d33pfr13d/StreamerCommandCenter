package streaming.scc.services.twitch.api;

import streaming.scc.config.ConfigKey;
import streaming.scc.config.Configuration;

public class TwitchApiSettings {
	
	private Configuration config = Configuration.getConfiguration();
	
	private String accessToken;
	
	private String clientId;
	
	public TwitchApiSettings() {
		accessToken = config.getConfigValue(ConfigKey.SERVICE_TWITCH_API_ACCESS_TOKEN);
		clientId = config.getConfigValue(ConfigKey.SERVICE_TWITCH_API_ACCESS_CLIENTID);
	}

	public String getAccessToken() {
		return accessToken;
	}

	public String getClientId() {
		return clientId;
	}
	
	

}
