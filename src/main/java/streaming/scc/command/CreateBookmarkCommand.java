package streaming.scc.command;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JOptionPane;

import jonas.tools.command.Command;
import streaming.scc.gui.MainFrame;
import streaming.scc.services.bookmarks.Bookmark;
import streaming.scc.services.bookmarks.BookmarkService;

/**
 * Creates a specialized bookmark prompting for "what just happened"?
 * 
 * @author d33pfr13d
 * @version $Id:$
 */
public class CreateBookmarkCommand extends AbstractAction implements Command, Runnable, Action {

    /**
	 * 
	 */
	private static final long serialVersionUID = 5946331907093120071L;

	@Override
    public void execute() {
        // Make sure we dont break the gui if alwaysOnTop in the MainFrame is active!!!
        boolean alwaysOnTop = MainFrame.getTheMainFrame().isAlwaysOnTop(); 
    	
        if(alwaysOnTop) {
        	MainFrame.getTheMainFrame().setAlwaysOnTop(false);
        }
    	Object val = JOptionPane.showInputDialog(MainFrame.getTheMainFrame(), "What just happened?", "Prompt", JOptionPane.INFORMATION_MESSAGE, null, null, "");

    	String comment = val != null ? val.toString() : "";
		BookmarkService.getService().logBookmark(new Bookmark(System.currentTimeMillis(), comment));
    	
    	if(alwaysOnTop) {
        	MainFrame.getTheMainFrame().setAlwaysOnTop(true);
        }
    	
    }

    @Override
    public void run() {
        execute();
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		execute();
		
	}

}
