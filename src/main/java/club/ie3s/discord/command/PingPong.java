package club.ie3s.discord.command;

import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.List;

public class PingPong extends Command {

    public PingPong() {
        super(mevent -> mevent.getChannel().getName().equals(getAdminChannelName()), "ping");
    }

    @Override
    protected void execute(MessageReceivedEvent mevent, User user, MessageChannel channel, String command, List<String> args) {
        channel.sendMessage("Pong!").submit();
    }
}
