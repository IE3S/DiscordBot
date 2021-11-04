package club.ie3s.discord;

import club.ie3s.discord.command.Command;
import club.ie3s.discord.event.MessageListener;
import lombok.SneakyThrows;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.utils.Compression;
import net.dv8tion.jda.api.utils.cache.CacheFlag;

public class Main {

    private final static String TOKEN = "OTA1NjU1MzM1ODMyOTQ0NjYw.YYNPQg.bGVTYCAlsADmqUY-gL54mVWhfF0";

    @SneakyThrows
    public static void main(String[] args) {
        JDABuilder builder = JDABuilder.createDefault(TOKEN).addEventListeners(new MessageListener());
        builder.disableCache(CacheFlag.MEMBER_OVERRIDES, CacheFlag.VOICE_STATE);
        builder.setBulkDeleteSplittingEnabled(false);
        builder.setCompression(Compression.NONE);
        builder.setActivity(Activity.watching("IE3S Community"));
        builder.build();
        Command.loadCommands();
    }

}
