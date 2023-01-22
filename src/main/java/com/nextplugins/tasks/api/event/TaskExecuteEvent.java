package com.nextplugins.tasks.api.event;

import com.nextplugins.tasks.api.model.Task;
import java.util.Date;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public final class TaskExecuteEvent extends CustomEvent {

    private final Task task;
    private final Date executedAt;
}
