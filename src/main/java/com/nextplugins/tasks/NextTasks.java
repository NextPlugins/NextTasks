package com.nextplugins.tasks;

import com.nextplugins.tasks.command.registry.CommandRegistry;
import com.nextplugins.tasks.configuration.registry.ConfigurationRegistry;
import com.nextplugins.tasks.job.JobLoader;
import com.nextplugins.tasks.manager.TaskManager;
import com.nextplugins.tasks.metric.MetricsProvider;
import com.nextplugins.tasks.parser.TimeExpressionParser;
import lombok.Getter;
import me.bristermitten.pdm.PluginDependencyManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class NextTasks extends JavaPlugin {

    @Getter private final TaskManager taskManager = new TaskManager();
    private final JobLoader jobLoader = new JobLoader(taskManager);

    public static NextTasks getInstance() {
        return getPlugin(NextTasks.class);
    }

    @Override
    public void onEnable() {
        saveDefaultConfig();
        PluginDependencyManager.of(this).loadAllDependencies().thenRun(() -> {
            try {
                ConfigurationRegistry.of(this).register();

                taskManager.loadTasks();

                new TimeExpressionParser(taskManager).parse();

                jobLoader.executeAllJobs();

                CommandRegistry.of(this).register();

                MetricsProvider.of(this).configure();

                getLogger().info("Plugin inicializado com sucesso.");
            } catch (Throwable t) {
                t.printStackTrace();
                getLogger().severe("Ocorreu um erro durante a inicialização do plugin!");
                Bukkit.getPluginManager().disablePlugin(this);
            }
        });
    }

    @Override
    public void onDisable() {
        try {
            jobLoader.clearAllJobs();
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

}
