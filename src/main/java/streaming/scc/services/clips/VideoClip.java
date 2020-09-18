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
	
	private char mnemonic;
	
	private boolean replicate = true;
	
	public VideoClip() {
		super();
	}
	
	public VideoClip(String keyword, String videoFile, char mnemonic, boolean replicate) {
		super();
		this.keyword = keyword;
		this.videoFile = videoFile;
		this.mnemonic = mnemonic;
		this.replicate = replicate;
	}



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

	public char getMnemonic() {
		return mnemonic;
	}

	public void setMnemonic(char mnemonic) {
		this.mnemonic = mnemonic;
	}

	public boolean isReplicate() {
		return replicate;
	}

	public void setReplicate(boolean replicate) {
		this.replicate = replicate;
	}

	@Override
	public String toString() {
		return "VideoClip [keyword=" + keyword + ", videoFile=" + videoFile + "]";
	}
	
	
	
	

}
