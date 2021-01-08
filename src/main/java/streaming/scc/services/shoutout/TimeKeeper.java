package streaming.scc.services.shoutout;

import javax.swing.SwingUtilities;

import streaming.scc.gui.ShoutOutFrame;

/**
 * Thread der den ShoutOut nach ein paar Sekunden wieder versteckt
 * 
 * @author d33pfr13d
 *
 */
public class TimeKeeper extends Thread {

	/**
	 * in ms
	 */
	private static final int SHOUTOUT_DURATION = 5000;

	private final ShoutOutFrame soFrame;

	public TimeKeeper(ShoutOutFrame soFrame) {
		super();
		this.soFrame = soFrame;
	}

	public void run() {

		try {
			Thread.sleep(SHOUTOUT_DURATION);
		} catch (InterruptedException e) {
			// ignore
		}

		// Muss im gui thread passieren
		SwingUtilities.invokeLater(new Runnable() {

			public void run() {

				// hide frame
				soFrame.unvisible();
				return;
			}

		});

	}

}