package mixer.mcc.gui;

import java.awt.Dimension;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.net.MalformedURLException;

import javax.swing.JFrame;
import javax.swing.JPanel;

import jonas.db.DatabaseManager;
import jonas.gui.common.CommandListener;
import jonas.gui.utils.HtmlPanel;
import jonas.tools.swing.ResizeListener;
import mixer.mcc.command.DisplayCreditsCommand;
import mixer.mcc.config.ConfigKey;
import mixer.mcc.config.Configuration;
import net.miginfocom.swing.MigLayout;

/**
 * Starting frame of the Mcc application
 * @author d33pfr13d
 *
 */
public class MainFrame extends JFrame implements WindowListener {

    /**
     *
     */
    private static final String TITLE_TEXT = "MCC: Mixer Command Center";

    /**
     *
     */
    private static final long serialVersionUID = 834544076014120286L;

    private CommandListener commandListener = new CommandListener("mixer.mcc.command.");

    public MainFrame() {
        super(TITLE_TEXT);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        addWindowListener(this);
        setPreferredSize(new Dimension(750, 200));
        addComponentListener(new ResizeListener(this));
        setContentPane(createContent());

        // XXX Experimental: Always on top of other windows
        if(Configuration.getConfiguration().getConfigBoolean(ConfigKey.UI_PRESENTATION_ON_TOP))
        	setAlwaysOnTop(true);

        setMenuBar(createMenuBar());
        // Hilfe anzeigen
//        try {
//            getContentPane().add(new HtmlPanel(new File("help.html").toURI().toURL()), "width 100%, height 100%");
//        } catch (MalformedURLException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
        
        //Lieber gleich das MainPanel anzeigen:
        getContentPane().add(new MainPanel());

        pack();
    }

    private MenuBar createMenuBar() {
        MenuBar mb = new MenuBar();
        mb.add(createToolMenu());

        return mb;
    }

    private Menu createToolMenu() {
        Menu menu = new Menu("Utils");
        {
            MenuItem creditsItem = new MenuItem("Credits");
            creditsItem.setActionCommand(DisplayCreditsCommand.class.getSimpleName());
            creditsItem.addActionListener(this.commandListener);
            menu.add(creditsItem);
        }
        return menu;
    }

    private JPanel createContent() {
        return new JPanel(new MigLayout());
    }

    @Override
    public void windowClosed(WindowEvent e) {
        // XXX If db: Shutdown operations, e.g. close the database...
//        DatabaseManager.shutdownHsqldb();
        // Not actually performed, as hsql already calls it... (as of now)
        System.exit(0);

    }

    @Override
    public void windowOpened(WindowEvent e) {
    }

    @Override
    public void windowClosing(WindowEvent e) {

    }

    @Override
    public void windowIconified(WindowEvent e) {
    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
    }

}
