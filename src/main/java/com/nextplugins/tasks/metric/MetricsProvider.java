package com.nextplugins.tasks.metric;

import com.nextplugins.tasks.NextTasks;
import com.nextplugins.tasks.configuration.GeneralConfiguration;
import lombok.Data;
import org.bstats.bukkit.Metrics;

@Data(staticConstructor = "of")
public final class MetricsProvider {

    private final NextTasks plugin;

    private final int PLUGIN_ID = 10239;

    public void configure() {
        if (GeneralConfiguration.get(GeneralConfiguration::useBStats)) {
            new Metrics(plugin, PLUGIN_ID);
            plugin.getLogger().info("Integração com o bStats configurada com sucesso.");
        } else {
            plugin.getLogger().info("Você desabilitou o uso do bStats, portanto, não utilizaremos dele.");
        }
    }

}
