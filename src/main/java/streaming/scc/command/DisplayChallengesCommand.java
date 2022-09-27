package streaming.scc.command;

import jonas.tools.command.Command;
import streaming.scc.gui.MainFrame;
import streaming.scc.gui.challenges.ChallengeTrackerFrame;

/**
 *
 * @author d33pfr13d
 */
public class DisplayChallengesCommand implements Command, Runnable {

    @Override
    public void execute() {
    	
    	ChallengeTrackerFrame ctf = null;
    	if(MainFrame.getTheMainFrame().getChallengeTracker() == null) {
    		ctf = new ChallengeTrackerFrame();
    		MainFrame.getTheMainFrame().setChallengeTracker(ctf);
    	}
    	else {
    		ctf = MainFrame.getTheMainFrame().getChallengeTracker();
    	}
    	
    	ctf.setVisible(false);;
		ctf.reloadContent();
		ctf.setVisible(true);;
    }

    @Override
    public void run() {
        execute();
    }

}
