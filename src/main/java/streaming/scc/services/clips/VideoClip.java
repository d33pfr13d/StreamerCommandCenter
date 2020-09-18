package streaming.scc.services.clips;

/**
 * Data structure for video clips.
 * To be read from settings file...
 * 
 * @author d33pfr13d
 *
 */
public class VideoClip {
	
	private String keyword;
	
	private String videoFile;
	
	private String mnemonic;
	
	private boolean replicate = true;

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getVideoFile() {
		return videoFile;
	}

	public void setVideoFile(String videoFile) {
		this.videoFile = videoFile;
	}

	public String getMnemonic() {
		return mnemonic;
	}

	public void setMnemonic(String mnemonic) {
		this.mnemonic = mnemonic;
	}

	public boolean isReplicate() {
		return replicate;
	}

	public void setReplicate(boolean replicate) {
		this.replicate = replicate;
	}
	
	

}
