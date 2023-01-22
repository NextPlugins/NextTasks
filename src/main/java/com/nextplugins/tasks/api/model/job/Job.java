package com.nextplugins.tasks.api.model.job;

import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Job {

    private List<String> commandList;
    private String description;
}
