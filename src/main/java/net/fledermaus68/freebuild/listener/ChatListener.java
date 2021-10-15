package net.fledermaus68.freebuild.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatListener implements Listener
{
    @EventHandler
    public void handleAsyncChat(AsyncPlayerChatEvent asyncPlayerChatEvent)
    {
        if (asyncPlayerChatEvent.getPlayer().isOp()) {
            asyncPlayerChatEvent.setFormat("§cAdmin • " + asyncPlayerChatEvent.getPlayer().getName() + " §7» " + asyncPlayerChatEvent.getMessage().replace("&", "§"));
        } else {
            asyncPlayerChatEvent.setFormat("§7Mitglied • " + asyncPlayerChatEvent.getPlayer().getName() + " §7» " + asyncPlayerChatEvent.getMessage().replace("&", "§"));
        }
    }
}
