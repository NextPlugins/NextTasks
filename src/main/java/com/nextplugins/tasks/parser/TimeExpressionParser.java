package com.nextplugins.tasks.parser;

import com.nextplugins.tasks.manager.TaskManager;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class TimeExpressionParser {

    private final String MODEL = "0 M H ? * D";

    private final TaskManager taskManager;

    public void parse() {

        taskManager.getDateMap().forEach((task, expression) -> {

            String[] split = expression.split(":");

            String rawDayValue = split[0];
            String day = rawDayValue.equalsIgnoreCase("EVERYDAY") ? "*" : rawDayValue.substring(0, 3);

            String hour = split[1];
            String minute = split[2];

            task.setDateExpression(MODEL
                    .replace("M", minute)
                    .replace("H", hour)
                    .replace("D", day)
            );

        });

    }

}
