package net.fledermaus68.freebuild.listener;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerTeleportEvent;

/**
 * @author fledi.dev Production on 14.10.2021
 * @project FreebuildV2
 */
public class WorldListener implements Listener {

    @EventHandler
    public void handleChangeEvent(PlayerTeleportEvent playerTeleportEvent) {
        if (playerTeleportEvent.getFrom().getWorld() != playerTeleportEvent.getPlayer().getWorld()) {
            for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
                onlinePlayer.sendMessage("§a" + playerTeleportEvent.getPlayer().getName() + "§7 ist nun in §a" + playerTeleportEvent.getPlayer().getWorld().getName() + "");
            }
        }
    }

}
