package streaming.scc.command;

import jonas.tools.command.Command;
import streaming.scc.services.vlc.VlcConnector;

/**
 * Command to play videos in vlc
 */
public class PlayVideoInVlcCommand implements Command, Runnable {
	
	private VlcConnector vlc = new VlcConnector();
	
	private String videoPath;
	
	

    public PlayVideoInVlcCommand(String videoPath) {
		super();
		this.videoPath = videoPath;
	}

	@Override
    public void execute() {
    	vlc.playVideo(videoPath);
    }

    @Override
    public void run() {
        execute();
    }

}
