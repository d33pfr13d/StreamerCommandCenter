package mixer.mcc.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import mixer.mcc.services.twitter.TwitterBot;
import mixer.mcc.services.vlc.VlcConnector;
import net.miginfocom.swing.MigLayout;

public class MainPanel extends JPanel {
	
	//TODO Das kommt alles noch in ne controller klasse
	private TwitterBot twitterBot = new TwitterBot();
	
	private VlcConnector vlc = new VlcConnector();

	/**
	 * 
	 */
	private static final long serialVersionUID = 6829793510139914776L;

	public MainPanel() {
		super(new MigLayout());
		
		final JTextArea jtLive = new JTextArea("Ich streame JETZT live auf Mixer. Schaut doch mal rein: http://mixer.com/d33pfr13d");
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
		
		//TODO Wahrscheinlich würde man das konfiguerbar haben wollen, wobei man
		//überhaupt alle actions konfigurierbar haben will und nicht feste buttons!!!
		// -> generisches "Play Video Command" das man dann per properties x-mal spawnen kann
		// Man braucht auch keinen settings dialog, ne props file finde ich viel komfortabler ^^
		final JTextArea jtVideo = new JTextArea("D:\\data\\Programme\\Telegram\\media\\on-a-boat-clipped.mp4");
		JButton jbBoat = new JButton("ON A (B)OAT");
		jbBoat.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				//TODO usually that would be a command being executed...
				vlc.playVideo(jtVideo.getText());
			}
		});
		jbBoat.setMnemonic(KeyEvent.VK_B);
		
		
		add(jtLive);
		add(jbLive,"wrap");
		
		add(jtVideo);
		add(jbBoat);
		
		
		
		
	}
	
	
	

}
