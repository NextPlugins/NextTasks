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

    public void executeAllJobs() throws Exception {
        SchedulerFactory factory = new StdSchedulerFactory();
        Scheduler scheduler = factory.getScheduler();

        scheduler.start();

        for (Task task : taskManager.getTasks().values()) {

            JobDetail detail = JobBuilder.newJob(JobExecutor.class)
                    .withIdentity(task.getId(), "NextPlugins")
                    .build();

            System.out.println(detail.toString());

            CronTrigger trigger = TriggerBuilder.newTrigger()
                    .withIdentity(task.getId() + "-TRIGGER", "NextPlugins")
                    .withSchedule(CronScheduleBuilder.cronSchedule(task.getDateExpression()))
                    .forJob(task.getId(), "NextPlugins")
                    .build();

            System.out.println(trigger.toString());

            scheduler.scheduleJob(detail, trigger);

        }
    }

}
