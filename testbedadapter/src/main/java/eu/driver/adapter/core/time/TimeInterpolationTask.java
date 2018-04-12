package eu.driver.adapter.core.time;

import eu.driver.adapter.core.CISAdapter;
import eu.driver.model.core.Timing;

public class TimeInterpolationTask implements Runnable {

	private CISAdapter adaptor = null;
	private long timeSpan = 0L;
	
	public TimeInterpolationTask(CISAdapter adaptor, long timeSpan) {
		this.adaptor = adaptor;
		this.timeSpan = timeSpan;
	}
	
	@Override
	public void run() {
		Timing timing = this.adaptor.getTimeInfo();
		if (timing != null) {
			Long speed = timing.getTrialTimeSpeed().longValue();
			Long timeElapsed = timing.getTimeElapsed() + (speed * this.timeSpan);
			Long trialTime = timing.getTrialTime() + (speed * this.timeSpan);
			timing.setTimeElapsed(timeElapsed);
			timing.setTrialTime(trialTime);
		}
	}

}
