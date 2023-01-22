package com.nextplugins.tasks.command;

import com.nextplugins.tasks.api.model.Task;
import com.nextplugins.tasks.configuration.GeneralConfiguration;
import com.nextplugins.tasks.configuration.registry.ConfigurationRegistry;
import com.nextplugins.tasks.manager.TaskManager;
import com.nextplugins.tasks.util.TextUtil;
import lombok.RequiredArgsConstructor;
import me.saiintbrisson.minecraft.command.annotation.Command;
import me.saiintbrisson.minecraft.command.command.Context;
import me.saiintbrisson.minecraft.command.target.CommandTarget;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

@RequiredArgsConstructor
public final class TaskCommand {

    private final TaskManager taskManager;
    private final ConfigurationRegistry configurationRegistry;

    @Command(
            name = "tasks",
            aliases = {"tarefas"},
            permission = "nexttasks.command.tasks",
            description = "Liste todas as tarefas agendadas.",
            target = CommandTarget.PLAYER,
            async = true)
    public void taskCommand(Context<Player> context) {
        final Player sender = context.getSender();

        GeneralConfiguration.get(GeneralConfiguration::taskListHeader).forEach(sender::sendMessage);

        for (Task task : taskManager.getTasks().values()) {
            final String description =
                    ChatColor.translateAlternateColorCodes('&', task.getJob().getDescription());

            sender.spigot()
                    .sendMessage(TextUtil.sendTextComponent(
                            GeneralConfiguration.get(GeneralConfiguration::taskListBody)
                                    .replace("$executionDate", task.getFormattedExecutionDate())
                                    .replace("$taskDescription", description),
                            "§aId da task:§f #" + task.getId()));
        }

        GeneralConfiguration.get(GeneralConfiguration::taskListFooter).forEach(sender::sendMessage);
    }

    @Command(
            name = "tasks.reload",
            aliases = {"recarregar"},
            permission = "nexttasks.command.reload",
            description = "Recarregue os arquivos de configurações do plugin.",
            async = true)
    public void taskReloadCommand(Context<Player> context) {
        final boolean reloadResult = configurationRegistry.reload();

        if (reloadResult) {
            context.sendMessage(ChatColor.GREEN + "Arquivos de configuração recarregados com sucesso.");
        } else {
            context.sendMessage(
                    ChatColor.RED + "Não foi possível recarregar os arquivos de configuração, veja o console.");
        }
    }
}
