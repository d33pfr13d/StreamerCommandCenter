package streaming.scc.gui.challenges;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import jonas.main.AbstractMainClass;
import jonas.tools.swing.ResizeListener;
import net.miginfocom.swing.MigLayout;
import streaming.scc.SccMain;
import streaming.scc.gui.utils.RoundedPanel;
import streaming.scc.model.challenges.Challenge;
import streaming.scc.model.challenges.Challenges;

/**
 * Frame dass die Challenges zur Anzeige im Stream präsentiert. Das abhaken der
 * Challenges soll aber möglichst direkt in diesem Frame möglich sein!!!
 *
 * Die Konfiguration soll über ein separates "ChallengeKonfiguratorFrame"
 * passieren ODER BESSER einfach direkt aus nem XML kommen.
 * 
 * @author jonas
 *
 */
public class ChallengeTrackerFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2096422304975676183L;

	/**
	*
	*/
	private static final String TITLE_TEXT = "SCC: Challenge Tracker";

	private static final Color COLOR_CHROMA_GREEN = new Color(0, 255, 0);

	private static final Color COLOR_RED = new Color(67, 0, 0);

	private Challenges challenges;

	public ChallengeTrackerFrame() {

		super(TITLE_TEXT);
		setLayout(new MigLayout());
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
//	        addWindowListener(this);
		setContentPane(createContent());

//	        setDefaultLookAndFeelDecorated(true);

		setPreferredSize(new Dimension(650, 650));
		addComponentListener(new ResizeListener(this));
		setBackground(COLOR_CHROMA_GREEN);
//	        setUndecorated(true);
//	        setBackground(new Color(0, 0, 0, 0));
		pack();
	}

	private Container createContent() {
		JPanel panel = new JPanel(new MigLayout());
		panel.setBackground(COLOR_CHROMA_GREEN);
		panel.add(new JLabel(" "), "wrap");
//		panel.add(new JLabel(" "), "wrap");

		List<JPanel> challengePannels = new ArrayList<JPanel>();

		challenges = Challenges.createOrLoadChallenges();
		for (Challenge c : challenges.getChallenges()) {
			if(c.isUnlocked()) {
			challengePannels.add(addChallengePanel(panel, c.getText(), true, c.isChecked()));
			}
		}
		challengePannels.add(addChallengePanel(panel, "Extra Challenge alle 10 Subs (max 100)", false, false));

//		panel.setOpaque(false);

		return panel;
	}

	public JPanel addChallengePanel(JPanel panel, String challengeTxt, boolean mitCheckmark, boolean isChecked) {
//		JPanel jpChallenge = new RoundedPanel(new MigLayout("debug"));
		JPanel jpChallenge = new RoundedPanel(new MigLayout(""));

//		jpChallenge.setBackground(COLOR_CHROMA_GREEN);
		jpChallenge.setBackground(Color.LIGHT_GRAY);
//		jpChallenge.setBounds(10,10,100,60);
//		jpChallenge.setOpaque(false);

		if (mitCheckmark) {
			JCheckBox jcbChallenge = new JCheckBox();
			jcbChallenge.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					Challenge c = challenges.getChallenge(challengeTxt);
					if(c!= null) {
						c.setChecked(jcbChallenge.isSelected());
						challenges.saveData();
					}
					
				}
			});
			jcbChallenge.setSelected(isChecked);
			jpChallenge.add(jcbChallenge, "shrinkx, width 5%");
		} else {
			jpChallenge.add(new JLabel(" "), "shrinkx, width 5%");
		}

		JLabel jlChallenge = new JLabel(challengeTxt);
		jlChallenge.setForeground(COLOR_RED);
		jlChallenge.setBackground(COLOR_RED);
		int sz = mitCheckmark ? 28 : 32;
		Font challengeFont = new Font("Arial", Font.PLAIN, sz);
		jlChallenge.setFont(challengeFont);

		jpChallenge.add(jlChallenge, "alignx left, width 95%");
//		jpChallenge.setOpaque(false);

		panel.add(new JLabel("     "), "width 10%");
		panel.add(jpChallenge, "width 90%, wrap");

		return jpChallenge;
	}

	// Nur zum testen, soll natürlich per Menü aus dem SCC Main heraus geöffnet
	// werden
	public static void main(String[] args) {
		AbstractMainClass.initLookAndFeel();
		SccMain.setupStyle();
		new ChallengeTrackerFrame().setVisible(true);
	}

}
