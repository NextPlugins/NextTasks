package com.nextplugins.tasks.api.model.job;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class Job {

    private List<String> commandList;
    private String description;

}
