package us.tryy3.minatsu.plugins.minatsusimplecommands.events;

import us.tryy3.java.minatsu.events.Listener;
import us.tryy3.java.minatsu.events.onChatEvent;
import us.tryy3.java.minatsu.utils.MessageBuilder;
import us.tryy3.minatsu.plugins.minatsusimplecommands.SimpleCommands;

/**
 * Created by tryy3 on 2016-02-14.
 */
public class ChatEvent implements Listener {
    SimpleCommands simpleCommands;

    public ChatEvent(SimpleCommands simpleCommands) {
        this.simpleCommands = simpleCommands;
    }

    public void chatEvent(onChatEvent event) {
        if (event.getMessage().startsWith(".")) {
            String[] messages = event.getMessage().split(" ");

            String message = simpleCommands.getManager().getCmd(messages[0].substring(1));

            if (message != null && !message.equalsIgnoreCase("")) {
                event.getConnection().sendMessage(new MessageBuilder().addMessage(event.getId(), message).build());
            }
        }
    }
}
