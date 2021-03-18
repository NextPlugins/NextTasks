package com.nextplugins.tasks.job.executor;

import com.nextplugins.tasks.NextTasks;
import com.nextplugins.tasks.api.event.TaskExecuteEvent;
import com.nextplugins.tasks.api.model.Task;
import com.nextplugins.tasks.manager.TaskManager;
import org.bukkit.Bukkit;
import org.quartz.Job;
import org.quartz.JobExecutionContext;

import java.util.Date;

public class JobExecutor implements Job {

    private final TaskManager taskManager = NextTasks.getInstance().getTaskManager();

    @Override
    public void execute(JobExecutionContext context) {
        Bukkit.getScheduler().runTask(NextTasks.getInstance(), () -> {
            Task task = taskManager.getTasks().get(context.getMergedJobDataMap().getString("task"));

            for (String command : task.getJob().getCommandList()) {
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command);
            }

            Bukkit.getPluginManager().callEvent(new TaskExecuteEvent(
                    task,
                    new Date()
            ));
        });
    }

}
