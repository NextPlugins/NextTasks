package com.nextplugins.tasks;

import com.google.common.base.Stopwatch;
import com.nextplugins.tasks.command.registry.CommandRegistry;
import com.nextplugins.tasks.configuration.registry.ConfigurationRegistry;
import com.nextplugins.tasks.job.JobLoader;
import com.nextplugins.tasks.manager.TaskManager;
import com.nextplugins.tasks.metric.MetricsProvider;
import com.nextplugins.tasks.parser.TimeExpressionParser;
import lombok.Getter;
import lombok.val;
import me.bristermitten.pdm.PluginDependencyManager;
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
        getLogger().info("Baixando e carregando dependências necessárias...");

        val downloadTime = Stopwatch.createStarted();

        PluginDependencyManager.of(this)
                .loadAllDependencies()
                .exceptionally(throwable -> {

                    throwable.printStackTrace();

                    getLogger().severe("Ocorreu um erro durante a inicialização do plugin!");
                    Bukkit.getPluginManager().disablePlugin(this);

                    return null;

                })
                .join();

        downloadTime.stop();

        getLogger().log(Level.INFO, "Dependências carregadas com sucesso! ({0})", downloadTime);
        getLogger().info("Iniciando carregamento do plugin.");

        val loadTime = Stopwatch.createStarted();
        try {
            ConfigurationRegistry.of(this).register();

            taskManager.loadTasks();

            new TimeExpressionParser(taskManager).parse();

            jobLoader.executeAllJobs();

            CommandRegistry.of(this).register();

            MetricsProvider.of(this).configure();

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
