package mixer.mcc.command;

import jonas.tools.command.Command;
import mixer.mcc.services.vlc.VlcConnector;

/**
 * Command to play videos in vlc
 */
public class PlayVideoCommand implements Command, Runnable {
	
	private VlcConnector vlc = new VlcConnector();
	
	private String videoPath;
	
	

    public PlayVideoCommand(String videoPath) {
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
