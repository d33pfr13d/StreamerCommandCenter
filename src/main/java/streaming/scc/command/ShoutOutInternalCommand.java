package streaming.scc.command;

import java.io.File;

import jonas.tools.command.Command;
import streaming.scc.gui.MainFrame;
import streaming.scc.gui.ShoutOutFrame;
import streaming.scc.services.shoutout.TimeKeeper;
import streaming.scc.services.twitch.api.TwitchApiCaller;

/**
 * Command to shoutout to Streamers in swing window
 */
public class ShoutOutInternalCommand implements Command, Runnable {
	
	private TwitchApiCaller twitchApi = new TwitchApiCaller();
	
    private String userName;

	public ShoutOutInternalCommand(String userName) {
		super();
		this.userName = userName;
	}

	@Override
    public void execute() {
		System.out.println("Shouting out for "+userName);
		
		File profileImage = twitchApi.getProfileImage(userName);
		
		ShoutOutFrame soFrame = new ShoutOutFrame(MainFrame.getTheMainFrame());
		soFrame.displayProfile(userName, profileImage);
		
		new TimeKeeper(soFrame).start();
    }

    @Override
    public void run() {
        execute();
    }

}
