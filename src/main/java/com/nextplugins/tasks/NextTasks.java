package com.nextplugins.tasks;

import com.google.common.base.Stopwatch;
import com.nextplugins.tasks.command.registry.CommandRegistry;
import com.nextplugins.tasks.configuration.registry.ConfigurationRegistry;
import com.nextplugins.tasks.job.JobLoader;
import com.nextplugins.tasks.manager.TaskManager;
import com.nextplugins.tasks.api.metric.MetricProvider;
import com.nextplugins.tasks.parser.TimeExpressionParser;
import lombok.Getter;
import lombok.val;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Level;

public final class NextTasks extends JavaPlugin {

    @Getter
    private final TaskManager taskManager = new TaskManager();
    private final JobLoader jobLoader = new JobLoader(taskManager);

    public static NextTasks getInstance() {
        return getPlugin(NextTasks.class);
    }

    @Override
    public void onEnable() {
        saveDefaultConfig();
        getLogger().info("Iniciando carregamento do plugin.");

        val loadTime = Stopwatch.createStarted();
        try {
            ConfigurationRegistry.of(this).register();

            taskManager.loadTasks();

            new TimeExpressionParser(taskManager).parse();

            jobLoader.executeAllJobs();

            CommandRegistry.of(this).register();
            MetricProvider.of(this).register();

            loadTime.stop();
            getLogger().log(Level.INFO, "Plugin inicializado com sucesso. ({0})", loadTime);

        } catch (Throwable t) {
            t.printStackTrace();
            getLogger().severe("Ocorreu um erro durante a inicialização do plugin!");
            Bukkit.getPluginManager().disablePlugin(this);
        }
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
