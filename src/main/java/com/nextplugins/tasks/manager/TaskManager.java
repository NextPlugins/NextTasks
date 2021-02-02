package com.nextplugins.tasks.manager;

import com.google.common.collect.Maps;
import com.nextplugins.tasks.api.model.Task;
import com.nextplugins.tasks.api.model.job.Job;
import com.nextplugins.tasks.configuration.TaskConfiguration;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bukkit.configuration.ConfigurationSection;

import java.util.Map;

@RequiredArgsConstructor
public final class TaskManager {

    @Getter private final Map<String, Task> tasks = Maps.newLinkedHashMap();
    @Getter private final Map<Task, String> dateMap = Maps.newLinkedHashMap();

    public void loadTasks() {
        ConfigurationSection section = TaskConfiguration.get(TaskConfiguration::taskSection);

        for (String key : section.getKeys(false)) {

            Task task = Task.builder()
                    .id(key)
                    .job(Job.builder()
                            .commandList(section.getStringList(key + ".commands"))
                            .build()
                    )
                    .dateExpression(section.getString(key + ".scheduler.date"))
                    .build();

            tasks.put(key, task);
            dateMap.put(task, task.getDateExpression());

        }

    }

}
