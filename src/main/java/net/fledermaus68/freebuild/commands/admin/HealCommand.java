package net.fledermaus68.freebuild.commands.admin;

import net.fledermaus68.freebuild.Freebuild;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HealCommand implements CommandExecutor
{

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

        final Player player = (Player) sender;

        if (!player.hasPermission("freebuild.heal"))
        {
            sender.sendMessage(Freebuild.getPrefix() + "§cDu hast keine Berechtigung dazu!");
            return false;
        }

        if (args.length == 0)
        {
            player.setHealth(20);
            sender.sendMessage(Freebuild.getPrefix() + "§7Du wurdest erfolgreich §ageheilt§7.");
            return true;
        }
        else
        {
            String targetName = args[0];
            if (Bukkit.getPlayer(targetName) != null)
            {
                Player targetPlayer = Bukkit.getPlayer(targetName);
                targetPlayer.setHealth(20);
                sender.sendMessage(Freebuild.getPrefix() + "§7Du hast §a" + targetPlayer.getDisplayName() + " §7erfolgreich §ageheilt§7.");
            }
            else
            {
                sender.sendMessage(Freebuild.getPrefix() + "§cEs konnte kein Spieler mit diesem Namen gefunden werden.");
                return false;
            }
        }

        return false;
    }
}
