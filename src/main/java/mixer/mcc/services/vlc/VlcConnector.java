package mixer.mcc.services.vlc;

import java.io.IOException;

import jonas.tools.execution.CommandLineUtils;

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
	
	/**
	 * TODO Das soll aus ner properties Datei kommen, wir brauchen dafuer ein Konfigurations-Modul.
	 * 
	 * TODO FIXME KOMMT NICHT MIT SPACES KLAR -> MUESSTE RUNTIME.EXEC in JUT mit array aufrufen!!!
	 * -> workaorund mit mklink /J VLC <ACTUAL-PATH> nen kurzen pfad erstellen in cmd!!!
	 */
	private static String VLC_PATH = "D:\\Programme\\VLC\\";
	
	private CommandLineUtils cli = new CommandLineUtils();
	
	public void playVideo(String videoPath) {
		try {
			cli.executeCommand("cmd /k "+ VLC_PATH+"vlc.exe --play-and-exit \""+videoPath+"\"");
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	
	

}
