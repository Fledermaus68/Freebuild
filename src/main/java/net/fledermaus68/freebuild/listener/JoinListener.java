package net.fledermaus68.freebuild.listener;

import net.fledermaus68.freebuild.money.MoneyManager;
import net.fledermaus68.freebuild.utils.ScoreboardManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class JoinListener implements Listener
{

    @EventHandler
    public void handlePlayerJoin(PlayerJoinEvent event)
    {
        Player player = event.getPlayer();
        new ScoreboardManager().set();
        event.setJoinMessage("§7Der Spieler §a" + player.getName() + " §7hat den Server betreten.");
        player.sendMessage("§7Deine §aDaten §7wurden §aerfolgreich §7geladen.");
        player.setPlayerListFooter("§ffledi.dev");
        player.setPlayerListHeader("§6Survival Server");

        if (!player.hasPlayedBefore()) {
            player.sendMessage("§7Du hast §a20 Taler§7, als §6Startgeld §7erhalten.");
            MoneyManager.createUser(player.getUniqueId().toString());
            MoneyManager.addMoneyToUser(player.getUniqueId().toString(),  50);
        }
    }

    @EventHandler
    public void handlePlayerQuit(PlayerQuitEvent playerQuitEvent)
    {
         Player player = playerQuitEvent.getPlayer();
        playerQuitEvent.setQuitMessage("§7Der Spieler §c" + player.getName() + "§7 hat den Server verlassen.");
    }

}
