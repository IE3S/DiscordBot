package club.ie3s.discord.command;

import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.List;

public class Clear extends Command{
    public Clear() {
        super(mevent -> mevent.getChannel().getName().equals(getAdminChannelName()), "clear");
    }

    @Override
    protected void execute(MessageReceivedEvent mevent, User user, MessageChannel channel, String command, List<String> args) {
        new Thread(() -> {

            channel.getHistory().retrievePast(50).complete().forEach( message -> message.delete().queue());
        }).start();
    }
}
