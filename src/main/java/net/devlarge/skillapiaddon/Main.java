package net.devlarge.skillapiaddon;

import net.devlarge.skillapiaddon.Utils.files.FileManager;
import net.devlarge.skillapiaddon.handler.RegistryHandler;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    private final RegistryHandler registryHandler = new RegistryHandler(this);
    @Override
    public void onEnable() {
        getRegistryHandler().registerEvents();
        getRegistryHandler().registerCommands();

        FileManager.initFiles();
    }

    @Override
    public void onDisable() {
    }

    public RegistryHandler getRegistryHandler() {
        return registryHandler;
    }
}
