package com.nextplugins.tasks.configuration.registry;

import com.henryfabio.minecraft.configinjector.bukkit.injector.BukkitConfigurationInjector;
import com.nextplugins.tasks.NextTasks;
import com.nextplugins.tasks.configuration.GeneralConfiguration;
import com.nextplugins.tasks.configuration.TaskConfiguration;
import lombok.Data;

@Data(staticConstructor = "of")
public final class ConfigurationRegistry {

    private final NextTasks plugin;

    public void register() {
        BukkitConfigurationInjector configurationInjector = new BukkitConfigurationInjector(plugin);

        configurationInjector.saveDefaultConfiguration(
                plugin,
                "tasks.yml"
        );

        configurationInjector.injectConfiguration(
                GeneralConfiguration.instance(),
                TaskConfiguration.instance()
        );
    }

    public boolean reload() {
        try {
            register();

            plugin.getJobLoader().clearAllJobs();
            plugin.getTaskManager().clear();

            plugin.getTaskManager().loadTasks();
            plugin.getJobLoader().scheduleAllJobs();

            return true;
        } catch (Throwable t) {
            t.printStackTrace();
            return false;
        }
    }
}
