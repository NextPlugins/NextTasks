package com.nextplugins.tasks.job.executor;

import org.quartz.Job;
import org.quartz.JobExecutionContext;

public class JobExecutor implements Job {

    @Override
    public void execute(JobExecutionContext context) {
        System.out.println(" ------ SERVIÇO EXECUTADO ------ ");

        // todo: execute task's command list
    }

}
