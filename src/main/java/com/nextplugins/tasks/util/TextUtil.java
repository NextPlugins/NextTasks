package com.nextplugins.tasks.util;

import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;

public class TextUtil {

    public static TextComponent sendTextComponent(final String message, final String hoverMessage) {
        final BaseComponent[] hoverEventComponents = {
                new TextComponent(hoverMessage)
        };
        final HoverEvent event = new HoverEvent(HoverEvent.Action.SHOW_TEXT, hoverEventComponents);
        final TextComponent component = new TextComponent(message);
        component.setHoverEvent(event);
        return component;
    }

}
