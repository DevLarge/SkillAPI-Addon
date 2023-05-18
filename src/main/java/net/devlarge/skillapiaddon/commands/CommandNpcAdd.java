package net.devlarge.skillapiaddon.commands;

/*
The code in class CommandAddNpc, is not to be used by anyone, without legitimate permission from https://github.com/DevLarge, Oscar#8373. 
No other versions are to be created from SkillAPI Addon, unless you specifically got permission, and the source code from the author. 
Copyright (C) 2023 DevLarge
*/

import net.devlarge.skillapiaddon.Utils.Permissions;
import net.devlarge.skillapiaddon.Utils.Values;
import net.devlarge.skillapiaddon.Utils.files.FileManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class CommandNpcAdd implements CommandExecutor {
    public final String USAGE = "Usage";
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            YamlConfiguration npcData = FileManager.getYamlConfigurationNpcData();
            if (!player.hasPermission(Permissions.QUESTER)) return false;

            if (args.length < 2) {
                player.sendMessage(Values.PREFIX + "Need to have an argument! " + USAGE);
                return false;
            }
            FileManager.addNpc(args[0], Integer.parseInt(args[1]));
            player.sendMessage(Values.PREFIX + args[0] + " set required level to " + args[1]);
            return false;
        } else {
            // TODO: CONSOLE
        }

        return false;
    }
}
