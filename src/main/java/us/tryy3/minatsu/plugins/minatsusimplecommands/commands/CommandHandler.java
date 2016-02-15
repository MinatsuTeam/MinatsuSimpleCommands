package us.tryy3.minatsu.plugins.minatsusimplecommands.commands;

import com.google.gson.JsonArray;
import us.tryy3.java.minatsu.Bot;
import us.tryy3.java.minatsu.TCPServer;
import us.tryy3.java.minatsu.command.Command;
import us.tryy3.java.minatsu.utils.MessageBuilder;
import us.tryy3.minatsu.plugins.minatsupermissions.PermissionsApi;
import us.tryy3.minatsu.plugins.minatsusimplecommands.SimpleCommands;

import java.util.Arrays;

/**
 * Created by tryy3 on 2016-02-14.
 */
public class CommandHandler extends Command {
    PermissionsApi permissionsApi;
    SimpleCommands simpleCommands;

    public CommandHandler(String name, PermissionsApi permissionsApi, SimpleCommands simpleCommands) {
        super(name);

        this.permissionsApi = permissionsApi;
        this.simpleCommands = simpleCommands;

        this.setUsage("sc [list|add|del|check [name] [message]]");
        this.setDescription("Main command to add/remove new simple commands.");
        this.setAliases(Arrays.asList("minatsusimplecmd", "msc", "simplecommand", "simplecmd", "sc"));
    }

    @Override
    public Boolean onCommand(TCPServer.Connection connection, String user, String channel, Command command, String label, String[] args) {
        if (args == null) {
            JsonArray array = new MessageBuilder().addMessage(channel, "Not enough arguments, please check the help page.").build();
            connection.sendMessage(array);
            return true;
        }

        if (args.length == 1) {
            // cmd list
            if (args[0].toLowerCase().equalsIgnoreCase("list")) {

            }
        } else if (args.length == 2) {
            // cmd del <name>
            if (args[0].toLowerCase().equalsIgnoreCase("del")) {
                connection.sendMessage(new MessageBuilder().addMessage(channel,
                        (simpleCommands.getManager().removeCmd(args[1].toLowerCase())) ?
                                "The command " + args[1] + " has now been removed" :
                                "There is no registered command called " + args[1]
                ).build());
                return true;
            }
        } else if (args.length > 2) {
            // cmd add <name> <message>
            if (args[0].toLowerCase().equalsIgnoreCase("add")) {
                connection.sendMessage(new MessageBuilder().addMessage(channel,
                        (simpleCommands.getManager().addCmd(args[1].toLowerCase(), joinArgs(args, 2))) ?
                            "The command " + args[1] + " has now been added" :
                            "There is already a registered command called " + args[1]
                ).build());
                return true;
            }
        }

        JsonArray array = new MessageBuilder().addMessage(channel, "Not enough arguments, please check the help page.").build();
        connection.sendMessage(array);
        return true;
    }

    public String joinArgs(String[] args, int from) {
        StringBuilder builder = new StringBuilder();

        for (int i = from; i < args.length; i++) {
            builder.append(args[i] + " ");
        }

        return builder.substring(0, builder.length()-1);
    }
}
