package streaming.scc.model.challenges;

/**
 * Data Klasse für eine Challenge die im Stream erfüllt (abgehakt) werden muss/soll
 * 
 * @author jonas
 *
 */
public class Challenge {
	
	private String text;
	
	private boolean unlocked;
	
	private boolean checked;
	
	
	public Challenge() {
		super();
	}

	public Challenge(String text, boolean checked) {
		this.text = text;
		this.checked = checked;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
	public boolean isUnlocked() {
		return unlocked;
	}

	public void setUnlocked(boolean unlocked) {
		this.unlocked = unlocked;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	@Override
	public String toString() {
		return "Challenge [text=" + text + ", unlocked=" + unlocked + ", checked=" + checked + "]";
	}


}
