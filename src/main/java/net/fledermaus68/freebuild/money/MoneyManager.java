package net.fledermaus68.freebuild.money;

import net.fledermaus68.freebuild.Freebuild;
import net.fledermaus68.freebuild.database.MySQL;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author fledi.dev Production on 15.10.2021
 * @project FreebuildV2
 */
public class MoneyManager {

    public static void addMoneyToUser(String uuid, Integer amount) {
        if (existsUser(uuid)) {
           long money = getMoneyFromUser(uuid);
           long moneyNew = money + amount;

           Freebuild.getDatabase().update("UPDATE `users` SET money='" + moneyNew + "' WHERE uuid='" + uuid + "'");
        }
    }

    public static void setMoneyFromUser(String uuid, Integer amount) {
        if (existsUser(uuid)) {
            Freebuild.getDatabase().update("UPDATE `users` SET 'money'='" + amount + "' WHERE uuid='" + uuid + "'");
        }
    }

    public static void removeMoneyFromUser(String uuid, Integer amount) {
        if (existsUser(uuid)) {
            long money = getMoneyFromUser(uuid);
            long moneyNew = money - amount;

            Freebuild.getDatabase().update("UPDATE `users` SET money='" + moneyNew + "' WHERE uuid='" + uuid + "'");
        }
    }

    public static Long getMoneyFromUser(String uuid) {
        if (existsUser(uuid)) {
            try {
                ResultSet resultSet = MySQL.getResult("SELECT money FROM `users` WHERE uuid='" + uuid + "'");
                if (resultSet.next()) {
                    return resultSet.getLong("money");
                } else {
                    return null;
                }
            } catch (SQLException sqlException) {
                sqlException.printStackTrace();
            }
        }
        return null;
    }

    public static void createUser(String uuid) {
        if (!existsUser(uuid)) {
            Freebuild.getDatabase().update("INSERT INTO users (uuid) VALUES ('" + uuid + "')");
        }
    }

    public static boolean existsUser(String uuid) {
        try {
            ResultSet resultSet = MySQL.getResult("SELECT uuid FROM users WHERE uuid='" + uuid + "'");
            if (resultSet.next()) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return false;
    }

}
