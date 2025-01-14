package com.wangyupu.mcmod.msrpp.utils;

import net.minecraft.client.MinecraftClient;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

public class text {
    public static Text formatModMessage(String rawMessage){
        MutableText part0 = Text.literal("[")
                .formatted(Formatting.WHITE, Formatting.BOLD);
        MutableText part1 = Text.literal("MCMOD 搜索++")
                .formatted(Formatting.AQUA);
        MutableText part2 = Text.literal("] ")
                .formatted(Formatting.WHITE, Formatting.BOLD);
        MutableText part3 = Text.literal(rawMessage)
                .formatted(Formatting.RESET,Formatting.GRAY);


        return part0.append(part1).append(part2).append(part3);
    };

    private static final MinecraftClient client = MinecraftClient.getInstance();
    public static void messageToPlayer(Text text){
        if (client.player != null) {
            client.player.sendMessage(text, false);
        }
    };
}
