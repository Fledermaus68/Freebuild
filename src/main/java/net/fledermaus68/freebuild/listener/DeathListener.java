package net.fledermaus68.freebuild.listener;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
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
        playerDeathEvent.setDeathMessage(null);
        Player victim = playerDeathEvent.getEntity();
        Player killer = victim.getKiller();

        if (playerDeathEvent.getEntity().getKiller() != null) {
            Bukkit.broadcastMessage("§c" + victim.getName() + " §7wurde von §a" + killer.getName() + " §7getötet.");
        } else {
            Bukkit.broadcastMessage("3c" + victim.getName() + " §7ist gestorben.");
        }
    }
}
