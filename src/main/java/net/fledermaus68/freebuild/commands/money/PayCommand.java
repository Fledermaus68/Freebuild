package net.fledermaus68.freebuild.commands.money;

import net.fledermaus68.freebuild.money.MoneyManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.text.ParseException;

/**
 * @author fledi.dev Production on 15.10.2021
 * @project FreebuildV2
 */
public class PayCommand implements CommandExecutor {

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

        if (!(args.length >= 2)) {
            player.sendMessage("§cSyntax: /pay <Name> <Anzahl>");
            return false;
        }

        String targetName = args[0];
        String amountString = args[1];
        int amount;

        try {
             amount = Integer.parseInt(amountString);
        } catch (NumberFormatException numberFormatException) {
            player.sendMessage("§cDu musst eine gültige Zahl angeben!");
            return false;
        }

        long currentMoney = MoneyManager.getMoneyFromUser(player.getUniqueId().toString());

        if (!(currentMoney >= amount)) {
            player.sendMessage("§cDu hast nicht genügend Taler!");
            return false;
        }

        if (Bukkit.getPlayer(targetName) == null) {
            player.sendMessage("§cDieser Spieler konnte nicht gefunden werden!");
            return false;
        }

        Player targetPlayer = Bukkit.getPlayer(targetName);

        MoneyManager.removeMoneyFromUser(player.getUniqueId().toString(), amount);
        MoneyManager.addMoneyToUser(targetPlayer.getUniqueId().toString(), amount);

        player.sendMessage("§7Du hast §a" + targetPlayer.getName() + " §7erfolgreich §a" + amount + " Taler §7gegeben.");
        targetPlayer.sendMessage("§7Du hast von §a" + player.getName() + " §7erfolgreich §a" + amount + " Taler §7erhalten.");

        return false;
    }
}
