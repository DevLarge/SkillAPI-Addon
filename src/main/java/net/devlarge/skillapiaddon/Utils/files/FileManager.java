package net.devlarge.skillapiaddon.Utils.files;

/*
The code in class FileManager, is not to be used by anyone, without legitimate permission from https://github.com/DevLarge, Oscar#8373. 
No other versions are to be created from SkillAPI Addon, unless you specifically got permission, and the source code from the author. 
Copyright (C) 2023 DevLarge
*/

import net.devlarge.skillapiaddon.Main;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;

public class FileManager {

    private static File fileNpcData;
    private static YamlConfiguration yamlConfigurationNpcData;

    public static void initFiles() {
        fileNpcData = new File(Bukkit.getServer().getPluginManager().getPlugin("SkillAPIAddon").getDataFolder(), "npcdata.yml");

        if (!fileNpcData.exists()) {
            try {
                fileNpcData.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        yamlConfigurationNpcData = YamlConfiguration.loadConfiguration(fileNpcData);

    }

    public static void saveFileNpcData() {
        try {
            FileManager.yamlConfigurationNpcData.save(fileNpcData);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getNpc(String npcUuid) {
        if (getYamlConfigurationNpcData().get(npcUuid) == null) {
            System.out.println("NULL!");
            return null;
        }
        return getYamlConfigurationNpcData().get(npcUuid).toString();
    }

    public static void addNpc(String npcUuid, int levelRequired) {
        getYamlConfigurationNpcData().set(npcUuid, levelRequired);
        saveFileNpcData();
    }

    public static void delNpc(String npcUuid) {
        getYamlConfigurationNpcData().set(npcUuid, null);
        saveFileNpcData();
    }

    public static File getFileNpcData() {
        return fileNpcData;
    }

    public static YamlConfiguration getYamlConfigurationNpcData() {
        return yamlConfigurationNpcData;
    }
}
