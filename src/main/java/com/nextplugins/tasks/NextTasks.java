package com.nextplugins.tasks;

import com.nextplugins.tasks.configuration.registry.ConfigurationRegistry;
import com.nextplugins.tasks.manager.TaskManager;
import com.nextplugins.tasks.parser.DateParser;
import lombok.Getter;
import me.bristermitten.pdm.PluginDependencyManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

@Getter
public final class NextTasks extends JavaPlugin {

    private TaskManager taskManager;
    private DateParser dateParser;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        PluginDependencyManager.of(this).loadAllDependencies().thenRun(() -> {
            try {
                ConfigurationRegistry.of(this).register();

                taskManager = new TaskManager();
                taskManager.loadTasks();

                dateParser = new DateParser(taskManager);
                dateParser.parse();

                getLogger().info("Plugin inicializado com sucesso.");
            } catch (Throwable t) {
                t.printStackTrace();
                getLogger().severe("Ocorreu um erro durante a inicialização do plugin!");
                Bukkit.getPluginManager().disablePlugin(this);
            }
        });
    }

}
