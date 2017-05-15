package hp.simulation;

public abstract class Order {
	protected long arrivalTime;
	protected long enterResourceTime;
	protected long leaveResourceTime;

	public void setArrivalTime(long time) {
		this.arrivalTime = time;
	}

	public void setEnterResourceTime(long time) {
		this.enterResourceTime = time;
	}

	public void setLeaveResourceTime(long time) {
		this.leaveResourceTime = time;
	}

	public long getDurationInScenario() {
		return leaveResourceTime - arrivalTime;
	}

	public long getDurationInResource() {
		 return leaveResourceTime - enterResourceTime;
	 }

	public String toString() {
		return "Order[arrivalTime = " + arrivalTime + ", enterResourceTime = " + enterResourceTime
				+ ", leaveResourceTime = " + leaveResourceTime + "]";
	}
}
