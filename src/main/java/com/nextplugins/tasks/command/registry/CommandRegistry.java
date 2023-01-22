package com.nextplugins.tasks.command.registry;

import com.nextplugins.tasks.NextTasks;
import com.nextplugins.tasks.command.TaskCommand;
import com.nextplugins.tasks.configuration.GeneralConfiguration;
import lombok.Data;
import me.saiintbrisson.bukkit.command.BukkitFrame;
import me.saiintbrisson.minecraft.command.message.MessageType;

@Data(staticConstructor = "of")
public final class CommandRegistry {

    private final NextTasks plugin;

    public void register() {
        BukkitFrame bukkitFrame = new BukkitFrame(plugin);

        bukkitFrame.registerCommands(
                new TaskCommand(plugin.getTaskManager(), plugin.getConfigurationRegistry())
        );

        bukkitFrame.getMessageHolder().setMessage(MessageType.NO_PERMISSION, GeneralConfiguration.get(GeneralConfiguration::noPermission));
    }
}
