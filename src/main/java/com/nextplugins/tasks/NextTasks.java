package com.nextplugins.tasks;

import com.nextplugins.tasks.configuration.registry.ConfigurationRegistry;
import com.nextplugins.tasks.job.JobLoader;
import com.nextplugins.tasks.manager.TaskManager;
import com.nextplugins.tasks.parser.TimeExpressionParser;
import lombok.Getter;
import me.bristermitten.pdm.PluginDependencyManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class NextTasks extends JavaPlugin {

    @Getter private TaskManager taskManager;

    public static NextTasks getInstance() {
        return getPlugin(NextTasks.class);
    }

    @Override
    public void onEnable() {
        saveDefaultConfig();
        PluginDependencyManager.of(this).loadAllDependencies().thenRun(() -> {
            try {
                ConfigurationRegistry.of(this).register();

                taskManager = new TaskManager();
                taskManager.loadTasks();

                new TimeExpressionParser(taskManager).parse();

                new JobLoader(taskManager).executeAllJobs();

                getLogger().info("Plugin inicializado com sucesso.");
            } catch (Throwable t) {
                t.printStackTrace();
                getLogger().severe("Ocorreu um erro durante a inicialização do plugin!");
                Bukkit.getPluginManager().disablePlugin(this);
            }
        });
    }

}
