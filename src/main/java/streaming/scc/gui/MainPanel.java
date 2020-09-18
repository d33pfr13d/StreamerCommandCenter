package streaming.scc.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import net.miginfocom.swing.MigLayout;
import streaming.scc.command.PlayVideoInInternalCommand;
import streaming.scc.command.PlayVideoInVlcCommand;
import streaming.scc.config.ConfigKey;
import streaming.scc.config.Configuration;
import streaming.scc.services.clips.VideoClip;
import streaming.scc.services.clips.VideoClips;
import streaming.scc.services.mixer.api.MixerInfo;
import streaming.scc.services.twitch.bot.ChatReplicatorBot;
import streaming.scc.services.twitter.TwitterBot;
import streaming.scc.timertasks.TimerThread;
import streaming.scc.timertasks.ViewerCountUpdaterTask;

public class MainPanel extends JPanel {
	
	private Configuration config = Configuration.getConfiguration();
	
	//TODO Das kommt alles noch in ne controller klasse
	private TwitterBot twitterBot = new TwitterBot();
	
	
	
	private MixerInfo mixerInfo = new MixerInfo();
	
//	private TimerThread viewerCounterUpdater;

	/**
	 * 
	 */
	private static final long serialVersionUID = 6829793510139914776L;

	public MainPanel() {
		super(new MigLayout());
		
		/*
		 * TODO Statt hier direkt alles zu definieren ist folgende Aufteilung geplant:
		 * - Triggers (wie ALT + L fuer Live Trigger
		 * - Events ("Live")
		 * - Actions, die ausgeführt werden, wenn ein Trigger einen Event ausgeloest hat (wie tweete auf twitter)
		 * - Parameter (wie fixe Texte oder Texte die über ne textarea eingegben werden muessen)
		 * 
		 * Das ganze soll dann moeglichst dynamisch (ne xml konfig?) konfigurierbar sein und wird dann nur noch eingelesen und verknuepft.
		 * Später kann man dann für die konfig auch noch ein gui bauen...
		 * 
		 */
		
		final JLabel jlViewerCountLabel = new JLabel("Total Viewers: n/a | Current Viewers: n/a");
		//TODO Kann man von twitch die viewer daten auch irgendwie beziehen?
//		viewerCounterUpdater = new TimerThread("ViewerCounterUpdater", new ViewerCountUpdaterTask(mixerInfo, jlViewerCountLabel), config.getConfigInteger(ConfigKey.SERVICE_MIXER_UPDATE_INTERVAL));
		final JButton jbPauseViewerCount = new JButton("(P)ausiere update");
		jbPauseViewerCount.setEnabled(false);//XXX solange der nix macht
		jbPauseViewerCount.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
//				viewerCounterUpdater.togglePauseTask();
				
			}
		});
		jbPauseViewerCount.setMnemonic(KeyEvent.VK_P);
		
		
		final JTextArea jtLive = new JTextArea(config.getConfigValue(ConfigKey.INFO_USER_TWEET_LIVE));
		JButton jbLive = new JButton("(L)IVE GEHEN");
		jbLive.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String liveTxt = jtLive.getText();
				
				//TODO usually that would be a command being executed...
				twitterBot.tweet(liveTxt);
			}
		});
		
		jbLive.setMnemonic(KeyEvent.VK_L);
		
		
		//Layout
		add(jlViewerCountLabel);
		add(jbPauseViewerCount,"wrap");
		
		add(jtLive);
		add(jbLive,"wrap");
		
		//TODO Wahrscheinlich wuerde man das konfiguerbar haben wollen, wobei man
		//Ueberhaupt alle actions konfigurierbar haben will und nicht feste buttons!!!
		// -> generisches "Play Video Command" das man dann per properties x-mal spawnen kann
		// Man braucht auch keinen settings dialog, ne props file finde ich viel komfortabler ^^
		createAndAddClipElements(config.getConfigValue(ConfigKey.SERVICE_VLC_VIDEO_DEFAULT), "Play (C)lip", 'c', null);
		
		//Auto generated elements for more clips
		VideoClips clips = VideoClips.createOrLoadVideoClips();
		for(VideoClip clip : clips.getClips()) {
			createAndAddClipElements(clip.getVideoFile(), clip.getKeyword(), clip.getMnemonic(), clip.getKeyword());
			ChatReplicatorBot.getBot().addVideoClip(clip);
		}
		
		
		
		// Updates
//		viewerCounterUpdater.startTask();
		
	}

	private void createAndAddClipElements(String videoFile, String btnTxt, char mnemonic, String keywordToSend) {
		final JTextArea jtVideo = new JTextArea(videoFile);
		JButton jbClip = new JButton(btnTxt);
		jbClip.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				//TODO commands should be executed in a central place
				//for that we need a "custom" commandListener that would take any command and execute it
				//(the jut commandlistner can just execute commands with default instantiation (no args)
//				PlayVideoInVlcCommand pvc = new PlayVideoInVlcCommand(jtVideo.getText());
				PlayVideoInInternalCommand pvc = new PlayVideoInInternalCommand(videoFile);
				pvc.execute();
				if(keywordToSend != null)
					ChatReplicatorBot.getBot().sendMessageToSecondary("!"+keywordToSend);
			}
		});
		jbClip.setMnemonic(mnemonic);
		add(jtVideo);
		add(jbClip,"wrap");
	}

	@Override
	protected void finalize() throws Throwable {
		//XXX Wird imo beim beenden gar nicht getriggered!!! net schlimm aber...
//		viewerCounterUpdater.destroyTask();
		super.finalize();
	}
	
	
	
	
	

}
