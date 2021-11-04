package club.ie3s.discord.event;

import club.ie3s.discord.command.Command;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class MessageListener extends ListenerAdapter {

    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        if(event.getAuthor().isBot()) return;
        MessageChannel channel = event.getChannel();
        String message = event.getMessage().getContentRaw();
        if(message.trim().startsWith("!") && !Command.findAndExecute(event))
            channel.sendMessage("¡No se encontro este comando!").submit();

    }
}
