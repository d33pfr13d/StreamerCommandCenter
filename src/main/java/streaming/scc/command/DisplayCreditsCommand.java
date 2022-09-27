package streaming.scc.command;

import javax.swing.JOptionPane;

import jonas.tools.command.Command;
import streaming.scc.gui.MainFrame;

/**
 *
 * @author d33pfr13d
 */
public class DisplayCreditsCommand implements Command, Runnable {

    @Override
    public void execute() {
        // Make sure we dont break the gui if alwaysOnTop in the MainFrame is active!!!
        boolean alwaysOnTop = MainFrame.getTheMainFrame().isAlwaysOnTop(); 
    	
        if(alwaysOnTop) {
        	MainFrame.getTheMainFrame().setAlwaysOnTop(false);
        }
    	JOptionPane.showMessageDialog(MainFrame.getTheMainFrame(), "SCC © 2019-2020 d33pfr13d", "Credits",
         JOptionPane.INFORMATION_MESSAGE);
    	
    	if(alwaysOnTop) {
        	MainFrame.getTheMainFrame().setAlwaysOnTop(true);
        }
    	System.out.println("ok");
    	
    }

    @Override
    public void run() {
        execute();
    }

}
