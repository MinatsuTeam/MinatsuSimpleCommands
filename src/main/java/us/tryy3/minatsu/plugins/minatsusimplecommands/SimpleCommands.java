package us.tryy3.minatsu.plugins.minatsusimplecommands;

import us.tryy3.java.minatsu.Bot;
import us.tryy3.java.minatsu.command.CommandManager;
import us.tryy3.java.minatsu.plugins.Plugin;
import us.tryy3.java.minatsu.plugins.PluginDescription;
import us.tryy3.minatsu.plugins.minatsupermissions.MinatsuPermissions;
import us.tryy3.minatsu.plugins.minatsupermissions.PermissionsApi;
import us.tryy3.minatsu.plugins.minatsusimplecommands.commands.CommandHandler;
import us.tryy3.minatsu.plugins.minatsusimplecommands.commands.SimpleCommandManager;
import us.tryy3.minatsu.plugins.minatsusimplecommands.events.ChatEvent;

import java.io.File;

/**
 * Created by tryy3 on 2016-02-14.
 */
public class SimpleCommands extends Plugin {

    SimpleCommandManager manager;

    @Override
    public void init(Bot bot, File datafolder) {
        PluginDescription description = new PluginDescription.DescriptionBuilder("Simple Commands", "0.0.1")
                .description("Add simple commands easy.")
                .authors("tryy3")
                .dependency("MinatsuPermissions")
                .build();

        super.init(bot, datafolder, description);
    }

    @Override
    public void onStart() {
        super.onStart();

        CommandManager manager = getBot().getCommandManager();

        Bot bot = getBot();
        if (bot.getPluginManager().getPlugin("MinatsuPermissions") == null || !(bot.getPluginManager().getPlugin("MinatsuPermissions") instanceof MinatsuPermissions)) {
            this.unload();
            throw new Error("Can't find MinatsuPermissions");
        }
        PermissionsApi api = ((MinatsuPermissions) bot.getPluginManager().getPlugin("MinatsuPermissions")).getPermissionsApi();

        manager.registerCommand(new CommandHandler("minatsusimplecommands", api, this));
        this.manager = new SimpleCommandManager(this);
        getBot().getEvent().registerEvents(new ChatEvent(this));
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    public SimpleCommandManager getManager() {
        return manager;
    }
}
