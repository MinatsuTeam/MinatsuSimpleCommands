package us.tryy3.minatsu.plugins.minatsusimplecommands.commands;

import us.tryy3.minatsu.plugins.minatsusimplecommands.SimpleCommands;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by tryy3 on 2016-02-14.
 */
public class SimpleCommandManager {
    Map<String, String> map;
    SimpleCommands simpleCommands;
    File file;

    public SimpleCommandManager(SimpleCommands simpleCommands) {
        File file = new File(simpleCommands.getPluginFolder() + "/commands.json");

        if (!file.exists()) {
            map = new HashMap<String, String>();
            simpleCommands.saveConfig(file, map);
        }

        map = simpleCommands.loadConfig(file);

        if (map == null) {
            map = new HashMap<String, String>();
            simpleCommands.saveConfig(file, map);
        }

        this.file = file;
        this.simpleCommands = simpleCommands;
    }

    public Map<String, String> getMap() {
        return map;
    }

    public String getCmd(String cmd) {
        return map.get(cmd);
    }

    public boolean checkCmd(String cmd) {
        return map.containsKey(cmd);
    }

    public boolean removeCmd(String cmd) {
        if (!checkCmd(cmd)) return false;

        map.remove(cmd);
        simpleCommands.saveConfig(file, map);
        return true;
    }

    public boolean addCmd(String cmd, String message) {
        if (checkCmd(cmd)) return false;

        map.put(cmd, message);
        simpleCommands.saveConfig(file, map);
        return true;
    }
}
