package com.nextplugins.tasks.api.model;

import com.nextplugins.tasks.api.model.job.Job;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Task {

    private String id;
    private Job job;
    private String dateExpression;

}
