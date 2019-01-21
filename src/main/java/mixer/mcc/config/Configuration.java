package mixer.mcc.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Kapselt die Logik um Konfigurationswerte abzufragen.
 * 
 * Die Idee ist es mehrere Dateien auf der Platte zu erlauben, die
 * hierarchisch geladen werden.
 * 
 * D.h. die normalen Properties werden in der "main" Datei konfiguriert.
 * Alles was da drinnen steht lässt sich im einzelnen überschreiben
 * durch Werte die in die "developer" Datei geschrieben werden.
 * Das sind Werte die man in der Enticklung ändert, aber nicht veröffentlichen will.
 * Diese lassen sich wiederrum noch mals über die Werte in der "secret" File überschreiben.
 * Da kommen nur die geheimemn Schlüssel rein.
 * 
 * Die letzten beiden Dateien werden beide vom version control ausgenommen.
 * So kann da nichts versehentlich eingecheckt werden.
 * Ich frag mich seit Jahren, wieso das nicht überall so gehandhabt wird.
 * 
 * @author d33pfr13d
 *
 */
public class Configuration {
	
	/**
	 * XXX Singleton, später evtl. durch Spring Beans und autowiring ablösen!
	 */
	private static Configuration configuration;
	
	private Properties secretProps = new Properties();
	
	private Properties developerProps = new Properties();
	
	private Properties mainProps = new Properties();
	
	public Configuration() {
		reloadConfiguration();
	}
	
	public void reloadConfiguration() {
		secretProps = loadProperties("mcc.properties.secret");
		developerProps = loadProperties("mcc.properties.dev");
		mainProps = loadProperties("mcc.properties");
	}
	
	public int getConfigInteger(ConfigKey key) {
		//TODO Fehlermledung falls kein Int!
		return Integer.parseInt(getConfigValue(key));
	}
	
	public String getConfigValue(ConfigKey key) {
		String theKey = key.getKey();
		if(secretProps.containsKey(theKey)) {
			return secretProps.getProperty(theKey);
		}
		if(developerProps.containsKey(theKey)) {
			return developerProps.getProperty(theKey);
		}
		if(mainProps.containsKey(theKey)) {
			return mainProps.getProperty(theKey);
		}
		
		// Empty statt null bei leerem Wert!
		System.out.println("WARNUNG: Kein KonfigWert fuer "+theKey);
		return "";
	}
	
	private Properties loadProperties(String propsFileName) {
		
		File propsFile = new File(propsFileName);
		if(!propsFile.exists()) {
			// Aus src Ordner versuchen für IDE-Umgebung
			propsFile = new File("./src/main/resources/", propsFileName);
		}
		
		Properties props = new Properties();
		if(!propsFile.exists()) {
			System.out.println("WARNUNG: "+propsFileName+" nicht gefunden. Es werden entsprechend keine Werte eingelesen!");
		}
		else {
			try {
				props.load(new FileInputStream(propsFile));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.err.println("ERROR: Loading file "+propsFileName+" failed: "+e.getMessage());
				e.printStackTrace();
			}
		}
		
		return props;
	}
	
	public static Configuration getConfiguration() {
		if(configuration == null) {
			configuration = new Configuration();
		}
		
		return configuration;
	}
	

}
