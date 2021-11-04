package club.ie3s.discord.command;

import club.ie3s.discord.utils.SingleValidation;
import lombok.Getter;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@Getter
public abstract class Command {

    private static final HashMap<String, Command> mappedCommands = new HashMap<>();
    private static final HashMap<String, Command> uniqueCommands = new HashMap<>();

    private final List<String> commands;
    private final SingleValidation<Boolean, MessageReceivedEvent> executeValidation;

    public Command(SingleValidation<Boolean, MessageReceivedEvent> executeValidation, String... commands) {
        this.commands  = new ArrayList<>(Arrays.asList(commands));
        if(this.commands.isEmpty())
            throw new IllegalArgumentException("La cantidad de comandos es invalida.");
        this.executeValidation = executeValidation;
        this.commands.forEach(command -> mappedCommands.put(command, Command.this));
        uniqueCommands.put(this.commands.get(0), this);
    }

    public boolean isValid(MessageReceivedEvent mevent) {
        return executeValidation != null ? executeValidation.validate(mevent) : true;
    }

    protected abstract void execute(MessageReceivedEvent mevent, User user, MessageChannel channel, String command, List<String> args);

    public static boolean findAndExecute(MessageReceivedEvent mevent) {
        String rawTrimMessage = mevent.getMessage().getContentRaw().trim();
        if(rawTrimMessage.equals("!"))
            return false;
        String[] split = rawTrimMessage.split("!");
        String commandMessage = split[1];
        Command command = mappedCommands.getOrDefault(commandMessage, null);
        if(command == null)
            return false;
        command.execute(mevent, mevent.getAuthor(), mevent.getChannel(), commandMessage, List.of(commandMessage).subList(1, split.length - 1));
        return true;
    }


    public static void loadCommands() {
        new PingPong();
        new Clear();
    }

    protected static String getAdminChannelName() {
        return "jarvis-admin";
    }

}
