package com.nextplugins.tasks.manager;

import com.google.common.collect.Maps;
import com.nextplugins.tasks.api.model.Task;
import com.nextplugins.tasks.api.model.job.Job;
import com.nextplugins.tasks.configuration.TaskConfiguration;
import com.nextplugins.tasks.util.DateFormat;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bukkit.configuration.ConfigurationSection;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
public final class TaskManager {

    @Getter private final Map<String, Task> tasks = Maps.newLinkedHashMap();
    @Getter private final Map<Task, String> dateMap = Maps.newLinkedHashMap();

    public void loadTasks() {
        ConfigurationSection section = TaskConfiguration.get(TaskConfiguration::taskSection);

        for (String key : section.getKeys(false)) {

            List<String> commands = section.getStringList(key + ".commands");
            String description = section.getString(key + ".description");
            String date = section.getString(key + ".scheduler.date");

            Task task = Task.builder()
                    .id(key)
                    .job(Job.builder()
                            .commandList(commands)
                            .description(description)
                            .build()
                    )
                    .formattedExecutionDate(DateFormat.format(date))
                    .dateExpression(date)
                    .build();

            tasks.put(key, task);
            dateMap.put(task, task.getDateExpression());

        }

    }

}
