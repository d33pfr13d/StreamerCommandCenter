package streaming.scc.services.clips;

import javax.swing.SwingUtilities;

import streaming.scc.gui.VideoFrame;
import uk.co.caprica.vlcj.player.component.EmbeddedMediaPlayerComponent;

/**
 * Thread der prüft, ob das video durch ist um das fenster zu verstecken
 * @author d33pfr13d
 *
 */
public class TimeKeeper extends Thread {

		private final EmbeddedMediaPlayerComponent mediaPlayerComponent;
		private volatile boolean running = true;
		private final VideoFrame vFrame;
		

		public TimeKeeper(EmbeddedMediaPlayerComponent mediaPlayerComponent, VideoFrame vFrame) {
			super();
			this.mediaPlayerComponent = mediaPlayerComponent;
			this.vFrame = vFrame;
		}

		public void run() {
			do {
				
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					// ignore
				}

				// Muss im gui thread passieren
				SwingUtilities.invokeLater(new Runnable() {

					public void run() {

						if(!mediaPlayerComponent.mediaPlayer().status().isPlaying()) {
							//hide frame
							vFrame.unvisible();
							return;
						}
						
					}

				});

			} while (running);
		}

		public void halt() {
			running = false;
		}

	}