package streaming.scc.services.mixer.api;

import java.util.concurrent.ExecutionException;

import com.mixer.api.MixerAPI;
import com.mixer.api.http.SortOrderMap;
import com.mixer.api.resource.channel.MixerChannel;
import com.mixer.api.response.channels.ShowChannelsResponse;
import com.mixer.api.services.impl.ChannelsService;

import streaming.scc.config.ConfigKey;
import streaming.scc.config.Configuration;

/**
 * Ruft infos über die Mixer REST API ab.
 * 
 * @author d33pfr13d
 *
 */
public class MixerInfo {
	
	private Configuration config = Configuration.getConfiguration();
	
	private MixerAPI mixer;
	
	private String username;
	
	private int totalViewers;
	private int currentViewers;
	private int myRank;

	public MixerInfo() {
		username = config.getConfigValue(ConfigKey.SERVICE_MIXER_USER_NAME);
		String clientID = config.getConfigValue(ConfigKey.SERVICE_MIXER_CLIENT_ID);
		String key = config.getConfigValue(ConfigKey.SERVICE_MIXER_CLIENT_KEY);
		mixer = new MixerAPI(clientID, key);
		
	}
	
	public void updateViewers() {
		try {
			MixerChannel channel;
			channel = mixer.use(ChannelsService.class).findOneByToken(username).get();
			
			totalViewers = channel.viewersTotal;
			currentViewers = channel.viewersCurrent;
			System.out.println("Total viewers: " + totalViewers);
			System.out.println("Current viewers: " + currentViewers);
			
		} catch (InterruptedException | ExecutionException e) {
			System.err.println("Problem while checking viewers: " + e.getMessage());
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	public void updateRank() {
		try {
			MixerChannel channel;
			channel = mixer.use(ChannelsService.class).findOneByToken(username).get();
			
			myRank = checkRank(0,channel.viewersTotal,1);
			
		} catch (InterruptedException | ExecutionException e) {
			System.err.println("Problem while checking viewers: " + e.getMessage());
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	public void updateChannelInfo(String streamName) {
		try {
			MixerChannel channel;
			channel = mixer.use(ChannelsService.class).findOneByToken(username).get();
			System.out.println("Updating streamName from "+channel.name+" to "+streamName);
			
			channel.name = streamName;
			//channel.type =... //TODO Game Type laden!
			
			// FIXME funktioniert nicht. Evtl. erst Oauth noetig?
			mixer.use(ChannelsService.class).update(channel).get();
			
		} catch (InterruptedException | ExecutionException e) {
			System.err.println("Problem while updating channelInfo: " + e.getMessage());
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	private int checkRank(int page, int viewers, int rank) throws ExecutionException,InterruptedException {
	    ShowChannelsResponse channels = getChannelsPage(page);
	    for (int i = 0; i < channels.size(); i++) {
	        MixerChannel channel = channels.get(i);
	        if (channel.viewersTotal <= viewers) {
	            System.out.format("Your rank on Mixer is %d!\n", rank);
	            return rank;
	        }
	        rank++;
	    }
	    System.out.format("Your rank is at least %d...\n", rank);
	    return checkRank(page + 1, viewers, rank);
	}
	
	private ShowChannelsResponse getChannelsPage(int page) throws ExecutionException,InterruptedException  {
	    SortOrderMap<ShowChannelsResponse.Attributes, ShowChannelsResponse.Ordering> map = new SortOrderMap<>();
	    map.put(ShowChannelsResponse.Attributes.VIEWERS_TOTAL, ShowChannelsResponse.Ordering.DESCENDING);
	    return mixer.use(ChannelsService.class).show(map,page,100).get();
	}

	public int getTotalViewers() {
		return totalViewers;
	}

	public int getCurrentViewers() {
		return currentViewers;
	}

	public int getMyRank() {
		return myRank;
	}
	
	
}
