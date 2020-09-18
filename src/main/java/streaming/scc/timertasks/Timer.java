package streaming.scc.timertasks;

public interface Timer {
	
	/**
	 * Setzte alle wieviel sekunden der Task ausgeführt werden soll.
	 * @param sec
	 */
	public void setInterval(int sec);
	
	/**
	 * Startet die wiederholte Taskausführung
	 */
	public void startTask();
	
	/**
	 * Un/Pausiert die wiederholte Taskausführung.
	 */
	public void togglePauseTask();
	
	/**
	 * Stopt die AUsführung komplett. Der Timer muss dann neu erstellt werden!
	 */
	public void destroyTask();

	
}
