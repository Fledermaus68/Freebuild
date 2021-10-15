package net.fledermaus68.freebuild.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

/**
 * @author fledi.dev Production on 14.10.2021
 * @project FreebuildV2
 */
public class DeathListener implements Listener {

    @EventHandler
    public void handleDeath(PlayerDeathEvent playerDeathEvent) {
        playerDeathEvent.setDeathMessage("§a" + playerDeathEvent.getEntity().getName() + " §7ist gestorben.");
    }
}
