package com.wangyupu.mcmod.msrpp.func;

import net.minecraft.client.MinecraftClient;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import com.wangyupu.mcmod.msrpp.utils.text;
import com.wangyupu.mcmod.msrpp.utils.system;
import net.minecraft.sound.SoundEvents;
import com.wangyupu.mcmod.msrpp.utils.sound;
import com.wangyupu.mcmod.msrpp.mcmodapi.mcmodAPIAccess;
import net.minecraft.text.Text;
import org.apache.commons.io.IOUtils;

import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class searchItem {
    private static final MinecraftClient client = MinecraftClient.getInstance();
    public static void openMCMODForItem(ItemStack itemOnHand) {
        String itemID = null;
        try {
        if (itemOnHand != null){
            // 处理物品
            if (itemOnHand.isEmpty()){
                return;
            };
            Item item = itemOnHand.getItem(); // 获取Item对象
            itemID = Registries.ITEM.getId(item).toString(); // 获取物品的ID
        }} catch (Exception exception) {return;}
        if (itemID != null) {
            // 搜索 mcmod
            int MCMODItemNum = 0;
            try {
                URL MCMODItemIDUrl = mcmodAPIAccess.getMCMODItemIdByItem(itemID);
                MCMODItemNum = Integer.parseInt(IOUtils.readLines(MCMODItemIDUrl.openStream(), StandardCharsets.UTF_8).get(0));
            } catch (Exception exception){
                text.messageToPlayer(text.formatModMessage("出现错误"));
                sound.playVanillaSound(SoundEvents.BLOCK_ANVIL_BREAK, 1f);
            };
            if (MCMODItemNum==0){
                text.messageToPlayer(text.formatModMessage("MC百科可能没有收录这个物品"));
                sound.playVanillaSound(SoundEvents.AMBIENT_UNDERWATER_ENTER, 1f);
                String itemDisplayName = Text.translatable(itemOnHand.getItem().getTranslationKey()).getString();
                system.openURLInBrowser("https://search.mcmod.cn/s?filter=3&key="+URLEncoder.encode(itemDisplayName, StandardCharsets.UTF_8));
            } else {
                system.openURLInBrowser("https://www.mcmod.cn/item/"+ MCMODItemNum +".html");
            }
        } else {
            text.messageToPlayer(text.formatModMessage("出现错误"));
            sound.playVanillaSound(SoundEvents.BLOCK_ANVIL_BREAK, 1f);
        }
    }
}
