package mixer.mcc.command;

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
         JOptionPane.showMessageDialog(null, "MCC © 2019 d33pfr13d", "Credits",
         JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    public void run() {
        execute();
    }

}
