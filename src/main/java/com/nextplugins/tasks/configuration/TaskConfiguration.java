package com.nextplugins.tasks.configuration;

import com.henryfabio.minecraft.configinjector.common.annotations.ConfigField;
import com.henryfabio.minecraft.configinjector.common.annotations.ConfigFile;
import com.henryfabio.minecraft.configinjector.common.annotations.ConfigSection;
import com.henryfabio.minecraft.configinjector.common.annotations.TranslateColors;
import com.henryfabio.minecraft.configinjector.common.injector.ConfigurationInjectable;
import java.util.function.Function;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.bukkit.configuration.ConfigurationSection;

@Getter
@TranslateColors
@Accessors(fluent = true)
@ConfigSection("tasks")
@ConfigFile("tasks.yml")
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class TaskConfiguration implements ConfigurationInjectable {

    @Getter
    private static final TaskConfiguration instance = new TaskConfiguration();

    // tasks

    @ConfigField("task-list")
    private ConfigurationSection taskSection;

    public static <T> T get(Function<TaskConfiguration, T> function) {
        return function.apply(instance);
    }
}
