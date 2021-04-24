package streaming.scc.gui;

import java.awt.Dimension;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;
import streaming.scc.config.ConfigKey;
import streaming.scc.config.Configuration;
import uk.co.caprica.vlcj.player.component.EmbeddedMediaPlayerComponent;

public class ShoutOutFrame extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1234834618749053108L;

	/**
	 * Wir sollten den gleichen Text verwenden wie beim VideoFrame damit in Obs beides gleich identifiziert wird
	 * TODO Den Titel aber konfigurierbar machen
	 */
	private static String hiddenTitle = "SCC hidden";
	private static String visibleTitle = "SCC VideoClip";
	
	private int preferedWidth = (Configuration.getConfiguration().getConfigInteger(ConfigKey.UI_VIDEO_FRAME_WIDTH)/2)+100;
	private int preferedHieght = Configuration.getConfiguration().getConfigInteger(ConfigKey.UI_VIDEO_FRAME_HEIGHT);

	private JPanel imagePanel;

	public ShoutOutFrame(JFrame reference) {
		super(hiddenTitle, reference!=null?reference.getGraphicsConfiguration():null);
		setLayout(new MigLayout());
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		setPreferredSize(new Dimension(preferedWidth, preferedHieght));
		
		imagePanel = new JPanel(new MigLayout("center"));
//		imagePanel.add(mediaPlayerComponent, "width 100%, height 100%, span, wrap");
		add(imagePanel,"width 100%, height 100%");
		
		//stays invis at first
	}
	
	public void displayProfile(String userName, File imageFile) {
		
		
		try {
			imagePanel.removeAll();
			
			imagePanel.add(new JLabel("<html><span style='font-size:16px'> </span></html>"), "center, wrap");
			imagePanel.add(new JLabel("<html><span style='font-size:16px'>"+"SHOUT OUT to "+userName+"</span></html>"), "center, wrap");
			
			if(imageFile.exists()) {
				BufferedImage picture = ImageIO.read(imageFile);
				JLabel picLabel = new JLabel(new ImageIcon(picture));
				imagePanel.add(picLabel,"wrap");
			}
			else {
				imagePanel.add(new JLabel(""),"wrap");
			}
			
			imagePanel.add(new JLabel("<html><span style='font-size:16px'>"+"Support ist kein Mord ;-)"+"</span></html>"), "center, wrap");
			
			setTitle(visibleTitle);
			setVisible(true);
			pack();
			
			//TODO Nach 6 Sekunden wieder hiden
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	public void unvisible() {
		setTitle(hiddenTitle);
		setVisible(false);
		//actually properly close that frame - seems to be the only thing that will "remove" it from being visible in OBS
		dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
	}
	

	//Test
	public static void main(String[] args) {
		
		new ShoutOutFrame(null).displayProfile("brentarus", new File("./twitchProfiles/brentarus.png"));
	}

}
