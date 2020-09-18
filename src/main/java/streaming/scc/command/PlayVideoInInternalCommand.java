package streaming.scc.command;

import jonas.tools.command.Command;
import streaming.scc.gui.VideoFrame;
import streaming.scc.services.clips.TimeKeeper;

/**
 * Command to play videos in swing window
 */
public class PlayVideoInInternalCommand implements Command, Runnable {
	
	private String videoPath;
	
	

    public PlayVideoInInternalCommand(String videoPath) {
		super();
		this.videoPath = videoPath;
	}

	@Override
    public void execute() {
		
		if(!VideoFrame.getMediaPlayerComponent().mediaPlayer().status().isPlaying()) {
			VideoFrame videoFrame = new VideoFrame();
			videoFrame.playVideo(videoPath);
			new TimeKeeper(videoFrame.getMediaPlayerComponent(), videoFrame).start();
		}
		else {
			//XXX Sollange playback läuft, darf kein anderes video gestartet werden
			//ODER soll das zweite das erste abbrechen? ...
			System.out.println("VIDEO STILL PLAYING. WON'T LAUNCH SECOND CLIP!");
			
		}
    	
    }

    @Override
    public void run() {
        execute();
    }

}
