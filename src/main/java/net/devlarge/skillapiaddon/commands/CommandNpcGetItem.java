package net.devlarge.skillapiaddon.commands;

/*
The code in class CommandGetItemNpcId, is not to be used by anyone, without legitimate permission from https://github.com/DevLarge, Oscar#8373. 
No other versions are to be created from SkillAPI Addon, unless you specifically got permission, and the source code from the author. 
Copyright (C) 2023 DevLarge
*/

import net.devlarge.skillapiaddon.Utils.Permissions;
import net.devlarge.skillapiaddon.Utils.Values;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Collections;

public class CommandNpcGetItem implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {
        if (!(sender instanceof Player)) return false;

        Player player = (Player) sender;
        if (!player.hasPermission(Permissions.QUESTER)) return false;
        Inventory inv = player.getInventory();
        if (inv.firstEmpty() == -1) {
            player.sendMessage(Values.PREFIX + "Inventory can't be full!");
            return false;
        }
        if (args.length == 0) {
            player.sendMessage(Values.PREFIX + "&cNeed arguments! <id/add/del>");
            return false;
        }

        if (args[0].equalsIgnoreCase("id")) {
            inv.addItem(generateItemStackId());
            player.sendMessage(Values.PREFIX + "Added item stack Npc ID");
        } else if (args[0].equalsIgnoreCase("add")) {
            inv.addItem(generateItemStackAdd());
            player.sendMessage(Values.PREFIX + "Added item stack &5Npc Adder");
        } else if (args[0].equalsIgnoreCase("del")) {
            inv.addItem(generateItemStackDel());
            player.sendMessage(Values.PREFIX + "Added item stack &cNpc Deleter");
        } else {
            player.sendMessage(Values.PREFIX + "&cHas to be a valid argument!");
            return false;
        }

        return false;
    }

    private ItemStack generateItemStackId() {
        ItemStack itemNpcId = new ItemStack(Material.COMPASS, 1);
        ItemMeta itemMeta = itemNpcId.getItemMeta();

        itemMeta.setLocalizedName(Values.ITEM_NPC_ID_NAME);
        itemMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&bNpc Id"));
        itemMeta.setLore(Collections.singletonList(ChatColor.translateAlternateColorCodes('&', "Right-click to get the NPC ID")));
        itemNpcId.setItemMeta(itemMeta);

        return itemNpcId;
    }

    private ItemStack generateItemStackAdd() {
        ItemStack itemNpcId = new ItemStack(Material.BOOK, 1);
        ItemMeta itemMeta = itemNpcId.getItemMeta();

        itemMeta.setLocalizedName(Values.ITEM_NPC_ADD_NAME);
        itemMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&b&5Npc Adder"));
        itemMeta.setLore(Collections.singletonList(ChatColor.translateAlternateColorCodes('&', "Right-click to add a level requirement")));
        itemNpcId.setItemMeta(itemMeta);

        return itemNpcId;
    }

    private ItemStack generateItemStackDel() {
        ItemStack itemNpcId = new ItemStack(Material.BARRIER, 1);
        ItemMeta itemMeta = itemNpcId.getItemMeta();

        itemMeta.setLocalizedName(Values.ITEM_NPC_DEL_NAME);
        itemMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&b&cNpc Deleter"));
        itemMeta.setLore(Collections.singletonList(ChatColor.translateAlternateColorCodes('&', "Right-click to delete the level requirement")));
        itemNpcId.setItemMeta(itemMeta);

        return itemNpcId;
    }

}
