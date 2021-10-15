package net.fledermaus68.freebuild.commands;

import net.fledermaus68.freebuild.Freebuild;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * @author fledi.dev Production on 15.10.2021
 * @project FreebuildV2
 */
public class BroadcastCommand implements CommandExecutor {
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
        if (!sender.hasPermission("freebuild.broadcast")) {
            sender.sendMessage("§cDu hast keine Berechtigung auf diesen Befehl.");
            return false;
        }


            if (args.length >= 1) {
                String msg = "";

                for (int i = 0; i != args.length; i++){
                    msg = msg + " " + args[i];
                }
                msg = msg.replace("&", "§");
                for(Player players : Bukkit.getOnlinePlayers()) {
                    players.sendMessage("§cAnkündigung §8» §7" + msg);
                }


        } else {
            sender.sendMessage(Freebuild.getPrefix() + "§cSyntax: /broadcast <Nachricht>");
        }



        return false;
    }
}
