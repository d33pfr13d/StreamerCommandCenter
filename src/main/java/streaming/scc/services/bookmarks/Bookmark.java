package streaming.scc.services.bookmarks;

/**
 * Logs a certain moment in the stream.
 * 
 * @author d33pfr13d
 *
 */
public class Bookmark {

	/**
	 * When did the moment occur?
	 */
	private long timestamp;
	
	/**
	 * optional comment what happened
	 */
	private String comment;

	public Bookmark(long timestamp, String comment) {
		super();
		this.timestamp = timestamp;
		this.comment = comment;
	}

	public Bookmark(long timestamp) {
		super();
		this.timestamp = timestamp;
		this.comment = "";
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	@Override
	public String toString() {
		return "Bookmark [timestamp=" + timestamp + ", comment=" + comment + "]";
	}
	
	
}
