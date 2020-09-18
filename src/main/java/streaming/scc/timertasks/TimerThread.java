package streaming.scc.timertasks;

/**
 * Erlaubt das regelmäßige Ausführen von beliebigen Runnables alle x Sekunden.
 * 
 * @author d33pfr13d
 *
 */
public class TimerThread extends Thread implements Timer {

	private String name;

	private Runnable task;

	private volatile int intervalInSecounds;

	private volatile boolean paused = false;

	private volatile boolean ending = false;

	public TimerThread(String name, Runnable task, int intervalInSecounds) {
		super();
		this.name = name;
		this.task = task;
		this.intervalInSecounds = intervalInSecounds;
	}

	@Override
	public void setInterval(int sec) {
		intervalInSecounds = sec;
	}

	@Override
	public void startTask() {
		super.start();

	}

	@Override
	public void togglePauseTask() {
		System.out.println(paused?"Unpausing...":"Pausing...");
		paused = !paused;
	}

	@Override
	public void destroyTask() {
		ending = true;
	}

	@Override
	public void run() {
		System.out.println("Starting task " + name + "...");

		do {

			if (!paused) {
				task.run();
			}

			try {
				Thread.sleep(intervalInSecounds * 1000);
			} catch (InterruptedException e) {
				// Ignore
			}

		} while (!ending);

		System.out.println("Ending task " + name + "...");
	}

}
