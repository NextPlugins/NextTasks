package com.nextplugins.tasks.api;

import com.google.common.collect.Sets;
import com.nextplugins.tasks.NextTasks;
import com.nextplugins.tasks.api.model.Task;
import com.nextplugins.tasks.manager.TaskManager;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class NextTasksAPI {

    @Getter private static final NextTasksAPI instance = new NextTasksAPI();

    private final TaskManager taskManager = NextTasks.getInstance().getTaskManager();

    /**
     * Search all tasks to look for one with the entered custom filter.
     *
     * @param filter custom filter to search
     * @return {@link Optional} with the task found
     */
    public Optional<Task> findTaskByFilter(Predicate<Task> filter) {
        return allTasks().stream()
                .filter(filter)
                .findFirst();
    }

    /**
     * Search all tasks to look for every with the entered custom filter.
     *
     * @param filter custom filter to search
     * @return {@link Set} with all tasks found
     */
    public Set<Task> findTasksByFilter(Predicate<Task> filter) {
        return allTasks().stream()
                .filter(filter)
                .collect(Collectors.toSet());
    }

    public Set<Task> allTasks() {
        return Sets.newLinkedHashSet(taskManager.getTasks().values());
    }

}
