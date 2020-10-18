package streaming.scc.services.bookmarks;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Service um Bookmarks in live streams zu loggen.
 * 
 * Ich starte mit einem einfachen text format, das aber leicht parseable ist.
 * 
 * Außerdem appende ich einfach alles immer an die selbe Datei. Ich gehe davon
 * aus, dass ich das feature nur spärlich nutzen werde und die Datei daher nicht
 * krass groß wird.
 * 
 * TODO Falls es irgendwann mal riesig ist, könnte man überlegen eine file pro
 * monat oder sowas zu verwenden...
 * 
 * @author d33pfr13d
 *
 */
public class BookmarkService {

	private static BookmarkService service = null;

	private File file = new File("bookmarks.txt");
	private PrintWriter out;

	public BookmarkService() {
		try {
			FileWriter fw = new FileWriter(file, true);
			BufferedWriter bw = new BufferedWriter(fw);
			out = new PrintWriter(bw);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

	}

	public void logBookmark(Bookmark bm) {
		System.out.println("logging " + bm.toString());
		try {
			out.println(bm.getTimestamp()+"|"+bm.getComment());
			out.flush();
		}
		catch(Exception e) {
			System.out.println("ERROR could not save bookmark");
			e.printStackTrace();
		}

	}

	public static BookmarkService getService() {
		if (service == null) {
			service = new BookmarkService();
		}
		return service;
	}

}
