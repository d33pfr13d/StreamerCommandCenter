package streaming.scc.services.kofi;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import streaming.scc.services.bookmarks.BookmarkService;

public class KofiAlertManager {
	
	private static KofiAlertManager manager;
	
	// TODO Muss über config file geloest werden
	private String labelPath = "C:\\Users\\jonas\\OneDrive\\scc_labels";
	
	/**
	 * For now we just update the most_recent_donator.txt
	 * 
	 * TODO Later we want to add it to a local Database to automatically compute top 10 lists in background.
	 * (That db also needs to be filled with donations of streamlabs that i used to use)
	 */
	public void logLatestDonation(KofiDonationDataObject dono) {
		
		File file = new File(labelPath, "most_recent_donator.txt");
		
		String donoString = dono.getFrom_name()+": "+dono.getAmount()+" "+ computeCurrencyString(dono.getCurrency());
		
		try {
			Files.write(file.toPath(), donoString.getBytes());
		} catch (IOException e) {
			System.err.println("Problem while logging donation: "+e.getMessage());
			e.printStackTrace();
		}
		
	}




	private String computeCurrencyString(String currency) {
		
		if(currency.equalsIgnoreCase("USD")) {
			return "$";
		}
		else if(currency.equalsIgnoreCase("EUR")) {
			return "€";
		}
		else {
			System.out.println("Unknown currency: "+currency);
			return "";
		}
	}
	
	
	
	
	
	
	public static KofiAlertManager getManager() {
		if (manager == null) {
			manager = new KofiAlertManager();
		}
		return manager;
	}

}
