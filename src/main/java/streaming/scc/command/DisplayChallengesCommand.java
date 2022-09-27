package streaming.scc.command;

import jonas.tools.command.Command;
import streaming.scc.gui.challenges.ChallengeTrackerFrame;

/**
 *
 * @author d33pfr13d
 */
public class DisplayChallengesCommand implements Command, Runnable {

    @Override
    public void execute() {
    	
    	new ChallengeTrackerFrame().setVisible(true);;
    }

    @Override
    public void run() {
        execute();
    }

}
