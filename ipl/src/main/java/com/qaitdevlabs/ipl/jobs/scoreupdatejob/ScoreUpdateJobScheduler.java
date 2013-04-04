package com.qaitdevlabs.ipl.jobs.scoreupdatejob;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

public class ScoreUpdateJobScheduler extends QuartzJobBean {

	 private ScoreUpdateJob scoreUpdateJob;
	 
	@Override
	protected void executeInternal(JobExecutionContext context)
			throws JobExecutionException {
		scoreUpdateJob.startProcessing();
	}
	public ScoreUpdateJob getScoreUpdateJob() {
		return scoreUpdateJob;
	}

	public void setScoreUpdateJob(ScoreUpdateJob scoreUpdateJob) {
		this.scoreUpdateJob = scoreUpdateJob;
	}
}
