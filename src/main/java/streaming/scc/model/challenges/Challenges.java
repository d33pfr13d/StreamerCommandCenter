package streaming.scc.model.challenges;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

import jonas.tools.data.SerializationUtils;

public class Challenges {

	private List<Challenge> challenges;

	public Challenges() {
		challenges = new LinkedList<Challenge>();
	}

	public static Challenges createOrLoadChallenges() {
		File dataFile = new File("scc_challenges.xml");
		if (!dataFile.exists()) {
			System.out.println("No challenge Data file found. Starting with blank!");
			return new Challenges();
		} else {
			try {
				System.out.println("Loading challenges...");
				return (Challenges) SerializationUtils.restoreObjectXml(dataFile);
			} catch (Exception e) {
				throw new RuntimeException("Could not load challenge data file", e);
			}
		}

	}

	public void saveData() {
		File dataXmlFile = new File("scc_challenges.xml");
		try {
			SerializationUtils.dumpObjectXml(dataXmlFile, this);

			System.out.println("...saved challenge data");
		} catch (Exception e) {
			// XXX we don't need to abort anything, but inform the usere stuff was not
			// saved...
			System.err.println("Could not perist challenge data file: " + e.getMessage());
			e.printStackTrace();
		}
	}

	public void addChallenge(Challenge clip) {
		getChallenges().add(clip);
	}

	public List<Challenge> getChallenges() {
		return challenges;
	}

	// Test
	public static void main(String[] args) {
		Challenges vc;
//		vc = new Challenges();
//		vc.addChallenge(new Challenge("Solo Chicken Dinner in Pubg", false));
//		vc.addChallenge(new Challenge("Krone in Fall Guys", true));
//		
//		vc.saveData();
		vc = Challenges.createOrLoadChallenges();
		vc.saveData();
		System.out.println(vc.getChallenges());

	}

	public Challenge getChallenge(String challengeTxt) {
		for (Challenge c : challenges) {
			if (challengeTxt.equalsIgnoreCase(c.getText())) {
				return c;
			}

		}
		return null;

	}

}
