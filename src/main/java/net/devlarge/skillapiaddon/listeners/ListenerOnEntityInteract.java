package net.devlarge.skillapiaddon.listeners;

/*
The code in class ListenerOnEntityInteract, is not to be used by anyone, without legitimate permission from https://github.com/DevLarge, Oscar#8373. 
No other versions are to be created from SkillAPI Addon, unless you specifically got permission, and the source code from the author. 
Copyright (C) 2023 DevLarge
*/

import com.sucy.skill.SkillAPI;
import com.sucy.skill.api.player.PlayerData;
import net.devlarge.skillapiaddon.Utils.Values;
import net.devlarge.skillapiaddon.Utils.files.FileManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.util.HashMap;

public class ListenerOnEntityInteract implements Listener {

    private final HashMap<Player, String> promptMap = new HashMap<>();

    @EventHandler
    public void onLevelInteract(PlayerInteractEntityEvent event) {
        Player player = event.getPlayer();
        PlayerData playerData = SkillAPI.getPlayerData(Bukkit.getOfflinePlayer(player.getUniqueId()));
        if (playerData.getMainClass() == null) {
            player.sendMessage(Values.PREFIX + "You need a class!");
            return;
        }
        int level = playerData.getMainClass().getLevel();
        String uuid = event.getRightClicked().getUniqueId().toString();
        if (FileManager.getNpc(uuid) == null) {
            player.sendMessage("Entity not registered. ");
            return;
        }
        if (Integer.parseInt(FileManager.getNpc(uuid)) > level) {
            player.sendMessage("Your level is too low to interact!");
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onItemInteract(PlayerInteractEntityEvent event) {
        Player player = event.getPlayer();
        String uuid = event.getRightClicked().getUniqueId().toString();
        if (player.getInventory().getItemInMainHand().getItemMeta().getLocalizedName() == null) return;
        String localizedName = player.getInventory().getItemInMainHand().getItemMeta().getLocalizedName();

        switch (localizedName) {
            case Values.ITEM_NPC_ID_NAME:
                player.sendMessage(Values.PREFIX + "The npc id is: " + uuid);
                StringSelection stringSelection = new StringSelection(uuid);
                if (stringSelection == null) {
                    System.out.println("NULL");
                    return;
                }
                Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                clipboard.setContents(stringSelection, stringSelection);
                player.sendMessage(Values.PREFIX + "Copied to clipboard");
                break;
            case Values.ITEM_NPC_ADD_NAME:
                promptMap.put(player, uuid);
                player.sendMessage(Values.PREFIX + "Write the level requirement in chat...");
                break;
            case Values.ITEM_NPC_DEL_NAME:
                FileManager.delNpc(uuid);
                player.sendMessage(Values.PREFIX + "&cThe npc: &f" + uuid + " &cno longer has a level requirement!");
                break;
        }
    }

    @EventHandler
    public void chatPromptEvent(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        if (promptMap.containsKey(player)) {
            FileManager.addNpc(promptMap.get(player), Integer.parseInt(event.getMessage()));
            event.setCancelled(true);
            promptMap.remove(player);
            player.sendMessage(Values.PREFIX + promptMap.get(player) + " set required level to " + event.getMessage());
        }

    }

}
