package streaming.scc.command;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import jonas.tools.command.Command;
import streaming.scc.services.bookmarks.Bookmark;
import streaming.scc.services.bookmarks.BookmarkService;

/**
 * Creates a quick bookmark without any comments what just happened
 * 
 * @author d33pfr13d
 * @version $Id:$
 */
public class CreateQuickBookmarkCommand extends AbstractAction implements Command, Runnable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 3550341500495592647L;

	@Override
    public void execute() {
		BookmarkService.getService().logBookmark(new Bookmark(System.currentTimeMillis()));
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
