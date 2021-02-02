package com.nextplugins.tasks.configuration;

import com.henryfabio.minecraft.configinjector.common.annotations.ConfigField;
import com.henryfabio.minecraft.configinjector.common.annotations.ConfigFile;
import com.henryfabio.minecraft.configinjector.common.annotations.ConfigSection;
import com.henryfabio.minecraft.configinjector.common.annotations.TranslateColors;
import com.henryfabio.minecraft.configinjector.common.injector.ConfigurationInjectable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;
import java.util.function.Function;

@Getter
@TranslateColors
@Accessors(fluent = true)
@ConfigSection("plugin")
@ConfigFile("config.yml")
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class GeneralConfiguration implements ConfigurationInjectable {

    @Getter private static final GeneralConfiguration instance = new GeneralConfiguration();

    // messages

    @ConfigField("messages.task-list.header") private List<String> taskListHeader;
    @ConfigField("messages.task-list.body") private String taskListBody;
    @ConfigField("messages.task-list.footer") private List<String> taskListFooter;

    @ConfigField("messages.no-permission") private String noPermission;

    public static <T> T get(Function<GeneralConfiguration, T> function) {
        return function.apply(instance);
    }

}
