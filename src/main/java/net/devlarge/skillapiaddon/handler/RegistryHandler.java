package net.devlarge.skillapiaddon.handler;

/*
The code in class RegistryHandler, is not to be used by anyone, without legitimate permission from https://github.com/DevLarge, Oscar#8373. 
No other versions are to be created from SkillAPI Addon, unless you specifically got permission, and the source code from the author. 
Copyright (C) 2023 DevLarge
*/

import net.devlarge.skillapiaddon.Main;
import net.devlarge.skillapiaddon.commands.CommandNpcAdd;
import net.devlarge.skillapiaddon.commands.CommandNpcDel;
import net.devlarge.skillapiaddon.commands.CommandNpcGetItem;
import net.devlarge.skillapiaddon.listeners.ListenerOnEntityInteract;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;

public class RegistryHandler {

    private final Main main;
    public RegistryHandler(Main main) {
        this.main = main;
    }

    public void registerEvents() {
        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new ListenerOnEntityInteract(), main);
    }

    public void registerCommands() {
        main.getCommand("npcadd").setExecutor(new CommandNpcAdd());
        main.getCommand("npcdel").setExecutor(new CommandNpcDel());
        main.getCommand("npcgetitem").setExecutor(new CommandNpcGetItem());
    }

}
