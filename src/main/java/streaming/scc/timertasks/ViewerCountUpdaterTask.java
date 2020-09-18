package streaming.scc.timertasks;

import java.time.LocalDateTime;

import javax.swing.JLabel;
import javax.swing.SwingUtilities;

import streaming.scc.services.mixer.api.MixerInfo;

/**
 * Holt den neusten count von mixer und schreibt ihn ins label
 * 
 * Deprecated da es mixer nicht mehr gibt...
 * 
 * @author d33pfr13d
 *
 */
@Deprecated
public class ViewerCountUpdaterTask implements Runnable {

	private final MixerInfo mixerInfo;
	private final JLabel jlViewerCountLabel;

	public ViewerCountUpdaterTask(MixerInfo mixerInfo, JLabel jlViewerCountLabel) {
		this.mixerInfo = mixerInfo;
		this.jlViewerCountLabel = jlViewerCountLabel;
	}

	@Override
	public void run() {
		mixerInfo.updateViewers();
		final int totalViews = mixerInfo.getTotalViewers();
		final int currentViews = mixerInfo.getCurrentViewers();
		
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				String zeit = LocalDateTime.now().toString();
				jlViewerCountLabel.setText(zeit+" | Total Viewers: "+totalViews+" | Current Viewers: "+currentViews);
			}
		});

	}

}
