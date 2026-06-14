package com.example.notes_api.components;


import org.springframework.batch.core.job.Job;
import org.springframework.batch.core.job.parameters.JobParameters;
import org.springframework.batch.core.job.parameters.JobParametersBuilder;
import org.springframework.batch.core.launch.JobOperator;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

@Component
public class GenericScheduler {
    @Autowired //not found, not yet
    JobOperator jobOperator;
    @Autowired
    private Job job;

    @Scheduled(cron = "*/60 * * * * ?")
    public void performBatchJob() throws Exception{
        JobParameters params = new JobParametersBuilder()
                .addString("JobID", String.valueOf(System.currentTimeMillis()))
                .toJobParameters();
        jobOperator.start(job,params);
    }



}