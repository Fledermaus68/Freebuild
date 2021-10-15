package net.fledermaus68.freebuild.utils;

import Clans.ClanConfiguration;
import net.fledermaus68.freebuild.Freebuild;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ScoreboardManager
{
    public void set() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            Scoreboard scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
            Objective objective = scoreboard.registerNewObjective("mysb", "dummy");
            ClanConfiguration clanConfiguration = new ClanConfiguration();

            Team admin = scoreboard.registerNewTeam("0000admin");
            Team user = scoreboard.registerNewTeam("0001user");
            admin.setPrefix("§cAdmin • §c");
            user.setPrefix("§7Mitglied • §c");

            String clanName = clanConfiguration.getClan(player);

            Bukkit.getOnlinePlayers().forEach(p -> {
                if (p.isOp()) {
                    admin.addEntry(p.getName());
                } else {
                    user.addEntry(p.getName());
                }
            });
            objective.setDisplayName("§8» §6Survival Server");
            objective.setDisplaySlot(DisplaySlot.SIDEBAR);
            objective.getScore("§4 ").setScore(10);
            objective.getScore("§8» §7Level").setScore(9);
            objective.getScore(updateTeam(scoreboard, "level", "§8➥ §aLevel " + player.getLevel() + "", "§a", ChatColor.RED)).setScore(8);
            objective.getScore("§8  ").setScore(7);
            objective.getScore("§8» §7Clan").setScore(6);
            objective.getScore("§8➥ §a" + clanName).setScore(5);
            player.setScoreboard(scoreboard);
        }
    }
    public static void updateScoreboard(Player player) {
        Scoreboard scoreboard = player.getScoreboard();
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
        Objective objective = scoreboard.getObjective("mysb");
        objective.getScore(updateTeam(scoreboard, "level", "§8➥ §aLevel " + player.getLevel() + "", "§a", ChatColor.RED)).setScore(8);
    }
    public static String updateTeam(Scoreboard sb, String Team, String prefix, String suffix, ChatColor entry){
        Team team = sb.getTeam(Team);
        if(team == null){
            team = sb.registerNewTeam(Team);
        }
        team.setPrefix(prefix);
        team.setSuffix(suffix);
        team.addEntry(entry.toString());
        return entry.toString();
    }
    public static void startScheduler(){
        new BukkitRunnable() {

            @Override
            public void run() {
                for(Player on : Bukkit.getOnlinePlayers()){
                    updateScoreboard(on);
                }

            }
        }.runTaskTimer(Freebuild.plugin, 20, 20);
    }
}
