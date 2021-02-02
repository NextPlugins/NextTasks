package com.nextplugins.tasks.command.registry;

import com.nextplugins.tasks.NextTasks;
import com.nextplugins.tasks.command.TaskCommand;
import lombok.Data;
import me.saiintbrisson.bukkit.command.BukkitFrame;

@Data(staticConstructor = "of")
public final class CommandRegistry {

    private final NextTasks plugin;

    public void register() {
        BukkitFrame bukkitFrame = new BukkitFrame(plugin);

        bukkitFrame.registerCommands(
                new TaskCommand(plugin.getTaskManager())
        );

    }

}
