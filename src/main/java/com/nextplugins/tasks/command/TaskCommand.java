package com.nextplugins.tasks.command;

import com.nextplugins.tasks.api.model.Task;
import com.nextplugins.tasks.configuration.GeneralConfiguration;
import com.nextplugins.tasks.manager.TaskManager;
import com.nextplugins.tasks.util.TextUtil;
import lombok.RequiredArgsConstructor;
import me.saiintbrisson.minecraft.command.annotation.Command;
import me.saiintbrisson.minecraft.command.command.Context;
import me.saiintbrisson.minecraft.command.target.CommandTarget;
import org.bukkit.entity.Player;

@RequiredArgsConstructor
public final class TaskCommand {

    private final TaskManager taskManager;

    @Command(
            name = "tasks",
            aliases = {"tarefas"},
            permission = "nexttasks.command.tasks",
            description = "Liste todas as tarefas agendadas.",
            target = CommandTarget.PLAYER,
            async = true
    )
    public void taskCommand(Context<Player> context) {
        Player player = context.getSender();

        GeneralConfiguration.get(GeneralConfiguration::taskListHeader).forEach(player::sendMessage);
        for (Task task : taskManager.getTasks().values()) {
            player.spigot().sendMessage(TextUtil.sendTextComponent(GeneralConfiguration.get(GeneralConfiguration::taskListBody)
                    .replace("$executionDate", task.getFormattedExecutionDate())
                    .replace("$taskDescription", task.getJob().getDescription()), "§aId da task:§f #" + task.getId()));

        }
        GeneralConfiguration.get(GeneralConfiguration::taskListFooter).forEach(player::sendMessage);
    }

}
