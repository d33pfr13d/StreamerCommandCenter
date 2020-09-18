package streaming.scc;

import java.awt.Container;
import java.sql.SQLException;

import javax.persistence.EntityManager;
import javax.swing.JComponent;
import javax.swing.plaf.synth.Region;
import javax.swing.plaf.synth.SynthLookAndFeel;
import javax.swing.plaf.synth.SynthStyle;
import javax.swing.plaf.synth.SynthStyleFactory;

import jonas.db.DatabaseManager;
import jonas.main.AbstractMainClass;
import streamer.scc.gui.MainFrame;

/**
 * Startup/launcher class of the SCC application
 *
 * @author d33pfr13d
 *
 */
public class SccMain extends AbstractMainClass<MainFrame> {

    // Singleton
    public static SccMain singleton;

    /**
     * Launches the app
     * @param args
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public static void main(String[] args) throws Exception {
    	//TODO If we want the database, we need a persistence.xml with a persistence-unit by that name!!! 
        DatabaseManager.persistenceUnit = "mcc";
        singleton = new SccMain();
        // singleton.initApplication();
        // XXX Ohne DB initialisieren:
        singleton.mainFrame = singleton.initMainFrame();

        // XXX Initial was laden?
//        SwingUtilities.invokeLater(new LoadTodoBoardCommand());
    }

    /**
     * Imports base data
     */
    @Override
    protected void initBaseData(EntityManager em) throws Exception {
        // XXX If database is needed
        // em.getTransaction().begin();
        // // Initialize basedata by loading singletons
        // em.getTransaction().commit();

    }

    @Override
    protected MainFrame createMainFrame() {
    	// Komponenten groesser machen fuer kleine displays
    	// -> TODO prop value, ob dies passieren soll
    	final SynthStyleFactory styleFactory = SynthLookAndFeel.getStyleFactory();
    	  SynthLookAndFeel.setStyleFactory(new SynthStyleFactory() {
    	    @Override
    	    public SynthStyle getStyle(JComponent c, Region id) {
    	      c.putClientProperty("JComponent.sizeVariant", "large");
    	      return styleFactory.getStyle(c, id);
    	    }
    	  });
    	
    	
        return new MainFrame();
    }

    /**
     * Updates what is displayed in the mainFrame, i.e. shows the given panel instead of the current one
     * @param panel
     *            with content
     */
    public static void updateFrameContent(Container panel) {
        singleton.updateMainFrameContent(panel);
    }

    public static void packFrame() {
        singleton.packMainFrame();
    }

}
