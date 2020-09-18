package streaming.scc.services.clips;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

import jonas.tools.data.SerializationUtils;
import com.thoughtworks.xstream.XStream;


public class VideoClips {
	
	private List<VideoClip> clips;
	
	public VideoClips() {
		clips = new LinkedList<VideoClip>();
	}

	public static VideoClips createOrLoadVideoClips() {
		File dataFile = new File("scc_clips.xml");
		if(!dataFile.exists()) {
			System.out.println("No clip Data file found. Starting with blank!");
			return new VideoClips();
		}
		else {
			try {
				return (VideoClips) SerializationUtils.restoreObjectXml(dataFile);
			} 
			catch (Exception e) {
				throw new RuntimeException("Could not load clip data file", e);
			} 
		}
		
		
	}
	
	public void saveVideoData() {
		File dataXmlFile = new File("scc_clips.xml");
		try {
			SerializationUtils.dumpObjectXml(dataXmlFile, this);
			
			System.out.println("...saved clip data");
		} catch (Exception e) {
			// XXX we don't need to abort anything, but inform the usere stuff was not saved...
			System.err.println("Could not perist video data file: "+e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void addClip(VideoClip clip) {
		getClips().add(clip);
	}

	public List<VideoClip> getClips() {
		return clips;
	}
	
	
	//Test
	public static void main(String[] args) {
		VideoClips vc = new VideoClips();
		vc.addClip(new VideoClip("boat", "C:\\Users\\jonas\\OneDrive\\overlays\\mixitup\\clips_shared\\on-a-boat-clipped.mp4", 'b', true));
		vc.addClip(new VideoClip("sge", "C:\\Users\\jonas\\OneDrive\\overlays\\mixitup\\clips_shared\\sge_pyroshow_short.mp4", 's', true));
		
		vc.saveVideoData();
		
		vc = VideoClips.createOrLoadVideoClips();
		System.out.println(vc.getClips());
		
		
	}

}
