package net.fledermaus68.freebuild.commands.money;

import net.fledermaus68.freebuild.money.MoneyManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * @author fledi.dev Production on 15.10.2021
 * @project FreebuildV2
 */
public class MoneyCommand implements CommandExecutor {
    /**
     * Executes the given command, returning its success.
     * <br>
     * If false is returned, then the "usage" plugin.yml entry for this command
     * (if defined) will be sent to the player.
     *
     * @param sender  Source of the command
     * @param command Command which was executed
     * @param label   Alias of the command which was used
     * @param args    Passed command arguments
     * @return true if a valid command, otherwise false
     */
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player))
        {
            sender.sendMessage("§cDieser Befehl kann nur von einem Spieler ausgeführt werden!");
            return false;
        }
        Player player = (Player) sender;
        sender.sendMessage("§7Du hast §6" + MoneyManager.getMoneyFromUser(player.getUniqueId().toString()) + " Taler§7.");
        return true;
    }
}
