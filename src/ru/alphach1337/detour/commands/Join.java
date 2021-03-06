package ru.alphach1337.detour.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Statistic;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import ru.alphach1337.detour.Detour;
import ru.alphach1337.detour.Settings;
import ru.alphach1337.detour.managers.DetourManager;

import java.util.List;

public class Join implements Command {
    @Override
    public String getPermission() {
        return "detour.join";
    }

    @Override
    public String getHelp() {
        return null;
    }

    @Override
    public void execute(CommandSender commandSender, org.bukkit.command.Command command, String[] args) {
        Player player = (Player) commandSender;
        FileConfiguration config = DetourManager.getInstance().config;

        if(DetourManager.getInstance().getIsDetour()) {
            if (player.getStatistic(Statistic.PLAY_ONE_TICK) * 50L <= (config.getInt("hoursToAllowDetour") * 60 * 60 * 1000)) {
                player.sendMessage(Settings.time + ChatColor.YELLOW + config.getInt("hoursToAllowDetour"));
            } else {
                if (DetourManager.getInstance().addPlayer(player)) {
                    player.sendMessage(Settings.addedToList);
                } else {
                    player.sendMessage(Settings.alreadyInTheList);
                }
            }
        }else{
            player.sendMessage(Settings.notStarted);
        }
    }

    @Override
    public void ExecuteConsole(CommandSender sen, org.bukkit.command.Command cmd, String[] args) {

    }
}
