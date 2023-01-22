package com.nextplugins.tasks.manager;

import com.nextplugins.tasks.api.model.Task;
import com.nextplugins.tasks.api.model.job.Job;
import com.nextplugins.tasks.api.model.type.ParserType;
import com.nextplugins.tasks.configuration.TaskConfiguration;
import com.nextplugins.tasks.parser.TimeExpressionParser;
import com.nextplugins.tasks.util.DateFormat;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bukkit.configuration.ConfigurationSection;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
public final class TaskManager {

    @Getter
    private final Map<String, Task> tasks = new LinkedHashMap<>();
    @Getter
    private final Map<Task, String> dateMap = new LinkedHashMap<>();

    public void loadTasks() {
        ConfigurationSection section = TaskConfiguration.get(TaskConfiguration::taskSection);

        for (String key : section.getKeys(false)) {
            final List<String> commands = section.getStringList(key + ".commands");
            final String description = section.getString(key + ".description");
            final String date = section.getString(key + ".scheduler.date");

            ParserType parserType = null;

            try {
                parserType = ParserType.valueOf(section.getString(key + ".scheduler.parser-type"));
            } catch (Throwable t) {
                t.printStackTrace();
            }

            String formattedData;

            try {
                formattedData = DateFormat.format(date);
            } catch (Throwable t) {
                formattedData = "Utilizando uma expressão avançada";
            }

            Task task = Task.builder()
                    .id(key)
                    .job(Job.builder()
                            .commandList(commands)
                            .description(description)
                            .build()
                    )
                    .formattedExecutionDate(formattedData)
                    .dateExpression(date)
                    .parserType(parserType)
                    .build();

            tasks.put(key, task);
            dateMap.put(task, task.getDateExpression());
        }

        TimeExpressionParser.of(this).parse();
    }

    public void clear() {
        tasks.clear();
        dateMap.clear();
    }
}
