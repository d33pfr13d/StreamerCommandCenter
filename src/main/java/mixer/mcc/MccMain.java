package mixer.mcc;

import java.awt.Container;
import java.sql.SQLException;

import javax.persistence.EntityManager;

import jonas.db.DatabaseManager;
import jonas.main.AbstractMainClass;
import mixer.mcc.gui.MainFrame;

/**
 * Startup/launcher class of the Mcc application
 *
 * @author d33pfr13d
 *
 */
public class MccMain extends AbstractMainClass<MainFrame> {

    // Singleton
    public static MccMain singleton;

    /**
     * Launches the app
     * @param args
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public static void main(String[] args) throws Exception {
    	//TODO If we want the database, we need a persistence.xml with a persistence-unit by that name!!! 
        DatabaseManager.persistenceUnit = "mcc";
        singleton = new MccMain();
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
