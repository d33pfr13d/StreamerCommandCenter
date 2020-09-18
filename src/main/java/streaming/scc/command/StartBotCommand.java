package streaming.scc.command;

import java.io.IOException;

import javax.swing.JOptionPane;

import org.jibble.pircbot.IrcException;
import org.jibble.pircbot.NickAlreadyInUseException;

import jonas.tools.command.Command;
import streaming.scc.services.twitch.bot.MyBot;

/**
 * Starts up the twitch bot
 */
public class StartBotCommand implements Command, Runnable {

    @Override
    public void execute() {
    	try {
			MyBot.startBot();
		} catch (NickAlreadyInUseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IrcException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @Override
    public void run() {
        execute();
    }

}
