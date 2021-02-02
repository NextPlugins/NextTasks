package com.nextplugins.tasks.api.event;

import com.nextplugins.tasks.api.model.Task;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = true)
public final class TaskExecuteEvent extends CustomEvent {

    private final Task task;
    private final Date executedAt;

}
