package streaming.scc.gui;

import java.awt.Dimension;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;
import streaming.scc.config.ConfigKey;
import streaming.scc.config.Configuration;
import uk.co.caprica.vlcj.player.component.EmbeddedMediaPlayerComponent;

public class VideoFrame extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1234834618749053108L;

	private static String hiddenTitle = "SCC hidden";
	private static String visibleTitle = "SCC VideoClip";
	
	public static final EmbeddedMediaPlayerComponent mediaPlayerComponent = new EmbeddedMediaPlayerComponent();
	
	private int preferedWidth = Configuration.getConfiguration().getConfigInteger(ConfigKey.UI_VIDEO_FRAME_WIDTH);
	private int preferedHieght = Configuration.getConfiguration().getConfigInteger(ConfigKey.UI_VIDEO_FRAME_HEIGHT);

	public VideoFrame() {
		super(hiddenTitle);
		setLayout(new MigLayout());
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		setPreferredSize(new Dimension(preferedWidth, preferedHieght));
		
		JPanel videoPanel = new JPanel(new MigLayout());
		videoPanel.add(mediaPlayerComponent, "width 100%, height 100%, span, wrap");
		add(videoPanel,"width 100%, height 100%");
		
		//stays invis at first
	}
	
	public void playVideo(String videoFile) {
		
		setVisible(true);
		pack();
		
		mediaPlayerComponent.mediaPlayer().media().play(videoFile);
		setTitle(visibleTitle);
		
	}
	
	public void unvisible() {
		setTitle(hiddenTitle);
		setVisible(false);
		//actually properly close that frame - seems to be the only thing that will "remove" it from being visible in OBS
		dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
	}
	
	public static EmbeddedMediaPlayerComponent getMediaPlayerComponent() {
		return mediaPlayerComponent;
	}

	//Test
	public static void main(String[] args) {
		
		new VideoFrame().playVideo("C:\\Users\\jonas\\OneDrive\\overlays\\mixitup\\clips_shared\\on-a-boat-clipped.mp4");
	}

}
