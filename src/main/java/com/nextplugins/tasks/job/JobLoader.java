package com.nextplugins.tasks.job;

import com.nextplugins.tasks.api.model.Task;
import com.nextplugins.tasks.job.executor.JobExecutor;
import com.nextplugins.tasks.manager.TaskManager;
import lombok.RequiredArgsConstructor;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

@RequiredArgsConstructor
public final class JobLoader {

    private final TaskManager taskManager;

    private final SchedulerFactory factory = new StdSchedulerFactory();

    public void executeAllJobs() throws Exception {
        Scheduler scheduler = factory.getScheduler();

        scheduler.start();

        for (Task task : taskManager.getTasks().values()) {

            JobDetail detail = JobBuilder.newJob(JobExecutor.class)
                    .withIdentity(task.getId(), "NextPlugins")
                    .usingJobData("task", task.getId())
                    .build();

            CronTrigger trigger = TriggerBuilder.newTrigger()
                    .withIdentity(task.getId() + "-TRIGGER", "NextPlugins")
                    .withSchedule(CronScheduleBuilder.cronSchedule(task.getDateExpression()))
                    .forJob(detail)
                    .build();

            scheduler.scheduleJob(detail, trigger);

        }
    }

    public void clearAllJobs() throws SchedulerException {
        Scheduler scheduler = factory.getScheduler();

        scheduler.clear();
    }

}
