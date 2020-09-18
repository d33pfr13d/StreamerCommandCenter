package streamer.scc.services.vlc;

import java.io.IOException;

import jonas.tools.execution.CommandLineUtils;
import streamer.scc.config.ConfigKey;
import streamer.scc.config.Configuration;

/**
 * Connects the mcc to your local vlc player.
 * 
 * The vision is to have playback control and everyhting via a cli api, not sure
 * if vlc offers all that, we might have to switch to mplayer which I have used
 * that way in the past.
 * 
 * For starters we just start videos and make vlc close after, so we can add a
 * capture input to slobs that shows the active vlc window ;-)
 * 
 * 
 * @author d33pfr13d
 *
 */
public class VlcConnector {
	
	private static Configuration config = Configuration.getConfiguration();
	
	/**
	 * TODO Das soll aus ner properties Datei kommen, wir brauchen dafuer ein Konfigurations-Modul.
	 * 
	 * TODO FIXME KOMMT NICHT MIT SPACES KLAR -> MUESSTE RUNTIME.EXEC in JUT mit array aufrufen!!!
	 * -> workaorund mit mklink /J VLC <ACTUAL-PATH> nen kurzen pfad erstellen in cmd!!!
	 */
	private static String VLC_PATH = config.getConfigValue(ConfigKey.SERVICE_VLC_BIN_PATH);
	
	private static String VLC_WIDTH = config.getConfigValue(ConfigKey.SERVICE_VLC_VIDEO_WIDTH);
	
	private CommandLineUtils cli = new CommandLineUtils();
	
	public void playVideo(String videoPath) {
		try {
		//--width=800
			//FIXME IT WONT M*****F***ING WORK WITH SPACES IN THE PATH TO VLC
			// When we put the "" around JUST Program Files it DOES open vlc
			//but it will not handle the second pair of "" around the f***ing file name
			// -> IT ALL DOES NOT MATTER BECAUSE WE ARE GOING TO REPLACE THIS SHIT WITH A JVLC VIEW ANYWAYS
//			VLC_PATH="C:\\\"Program Files\"\\VideoLAN\\VLC\\vlc.exe";
//			String[] cmdar = {"cmd", "/k", VLC_PATH, "--play-and-exit", "--width="+VLC_WIDTH, "\""+videoPath+"\""};
//			String cmdar = "cmd /k "+VLC_PATH+" --width="+VLC_WIDTH+" --play-and-exit"+" \""+videoPath+"\"";
//			cli.executeCommand(cmdar, false, true);
			cli.executeCommand("cmd /k "+ VLC_PATH+"vlc.exe --play-and-exit --width="+VLC_WIDTH+" \""+videoPath+"\"");
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private String escapeSpaces(String string) {
		if(string.contains(" ")) {
			return "\""+string+"\"";
//			return string.replaceAll(" ", "\" \"");
		}
		else
			return string;
	}
	
	
	

}
