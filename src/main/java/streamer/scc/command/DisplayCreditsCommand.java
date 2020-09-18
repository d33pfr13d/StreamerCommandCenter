package streamer.scc.command;

import javax.swing.JOptionPane;

import jonas.tools.command.Command;

/**
 *
 * @author Capgemini, Jonas Flick
 * @version $Id:$
 */
public class DisplayCreditsCommand implements Command, Runnable {

    @Override
    public void execute() {
        // XXX Breaks the gui if alwaysOnTop in the MainFrame is active!!!
         JOptionPane.showMessageDialog(null, "SCC © 2019-2020 d33pfr13d", "Credits",
         JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    public void run() {
        execute();
    }

}
