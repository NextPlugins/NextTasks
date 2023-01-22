package com.nextplugins.tasks.api.model;

import com.nextplugins.tasks.api.model.job.Job;
import com.nextplugins.tasks.api.model.type.ParserType;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Task {

    private final String id;
    private final Job job;
    private final ParserType parserType;
    private String dateExpression, formattedExecutionDate;
}
