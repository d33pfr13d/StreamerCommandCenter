package mixer.mcc.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import mixer.mcc.services.twitter.TwitterBot;
import net.miginfocom.swing.MigLayout;

public class MainPanel extends JPanel {
	
	//TODO Das kommt alles noch in ne controller klasse
	private TwitterBot twitterBot = new TwitterBot();

	/**
	 * 
	 */
	private static final long serialVersionUID = 6829793510139914776L;
	
	public MainPanel() {
		super(new MigLayout());
		
		final JTextArea jtLive = new JTextArea("Ich streame JETZT live auf Mixer. Schaut doch mal rein: http://mixer.com/d33pfr13d");
		JButton jbLive = new JButton("LIVE GEHEN");
		jbLive.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String liveTxt = jtLive.getText();
				
				twitterBot.tweet(liveTxt);
			}
		});
		
		jbLive.setMnemonic(KeyEvent.VK_L);
		
		
		add(jtLive);
		add(jbLive);
		
		
	}
	
	
	

}
