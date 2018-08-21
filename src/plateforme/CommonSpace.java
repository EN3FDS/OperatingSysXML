package plateforme;

public class CommonSpace {

	private boolean automaticMode;
	private boolean manualmode;
	private boolean runningIOHandler = false;
	private boolean runningExecutor = false;
	private boolean runningGenerator = false;
	
	
	/*
	 * @ Getters and Setters
	 */
	public boolean isAutomaticMode() {
		return automaticMode;
	}

	public void setAutomaticMode(boolean automaticMode) {
		this.automaticMode = automaticMode;
	}

	public boolean isManualmode() {
		return manualmode;
	}

	public void setManualmode(boolean manualmode) {
		this.manualmode = manualmode;
	}

	
	public boolean isRunningIOHandler() {
		return runningIOHandler;
	}

	public void setRunningIOHandler(boolean runningIOHandler) {
		this.runningIOHandler = runningIOHandler;
	}

	public boolean isRunningExecutor() {
		return runningExecutor;
	}

	public void setRunningExecutor(boolean runningExecutor) {
		this.runningExecutor = runningExecutor;
	}

	public boolean isRunningGenerator() {
		return runningGenerator;
	}

	public void setRunningGenerator(boolean runningGenerator) {
		this.runningGenerator = runningGenerator;
	}

	/*
	 * @ Constructors
	 */
	public CommonSpace() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CommonSpace(boolean automatic) {
		automaticMode = automatic;
	}

	
}
