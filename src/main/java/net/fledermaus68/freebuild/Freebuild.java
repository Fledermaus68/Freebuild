package net.fledermaus68.freebuild;

import net.fledermaus68.freebuild.commands.*;
import net.fledermaus68.freebuild.commands.money.MoneyCommand;
import net.fledermaus68.freebuild.commands.money.PayCommand;
import net.fledermaus68.freebuild.database.MySQL;
import net.fledermaus68.freebuild.listener.ChatListener;
import net.fledermaus68.freebuild.listener.DeathListener;
import net.fledermaus68.freebuild.listener.JoinListener;
import net.fledermaus68.freebuild.listener.WorldListener;
import net.fledermaus68.freebuild.utils.ScoreboardManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class Freebuild extends JavaPlugin {

    public static String prefix = "";
    public static Freebuild plugin;
    private static MySQL database;

    @Override
    public void onEnable() {
        // Plugin startup logic
        plugin = this;
        Bukkit.getPluginManager().registerEvents(new JoinListener(), this);
        Bukkit.getPluginManager().registerEvents(new DeathListener(), this);
        Bukkit.getPluginManager().registerEvents(new WorldListener(), this);
        Bukkit.getPluginManager().registerEvents(new ChatListener(), this);
        getCommand("heal").setExecutor(new HealCommand());
        getCommand("money").setExecutor(new MoneyCommand());
        getCommand("pay").setExecutor(new PayCommand());
        getCommand("kick").setExecutor(new KickCommand());
        getCommand("feed").setExecutor(new FeedCommand());
        getCommand("broadcast").setExecutor(new BroadcastCommand());
        getCommand("bc").setExecutor(new BroadcastCommand());
        getCommand("enderchest").setExecutor(new EnderChestCommand());
        getCommand("ec").setExecutor(new EnderChestCommand());
        ScoreboardManager.startScheduler();

        try {
            database = new MySQL("localhost", "root", "", "test");
            database.connect();
            database.update("CREATE TABLE IF NOT EXISTS users (uuid LONGTEXT, money BIGINT DEFAULT 0)");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static String getPrefix() {
        return prefix;
    }

    public static MySQL getDatabase() {
        return database;
    }
}
