package streamer.scc.services.mixer.api;

import static org.junit.Assert.*;

import org.junit.Test;

import streamer.scc.services.mixer.api.MixerInfo;

public class MixerInfoTest {
	
	private MixerInfo mixerInfo = new MixerInfo();
	
	@Test
	public void testViewerCount() throws Exception {
		mixerInfo.updateViewers();
	}
	
	@Test
	public void testRank() throws Exception {
		mixerInfo.updateRank();
	}
	
	@Test
	public void testUpdateName() throws Exception {
		mixerInfo.updateChannelInfo("[XB1S] Some Title");
	}

}
