package com.wangyupu.mcmod.msrpp.mixin.client;

import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.slot.Slot;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.ingame.AbstractInventoryScreen;

import com.wangyupu.mcmod.msrpp.McmodSearchRebornPlusPlusClient;
import com.wangyupu.mcmod.msrpp.utils.keybinding;
import com.wangyupu.mcmod.msrpp.func.searchItem;
import com.wangyupu.mcmod.msrpp.utils.JEIUtils;

import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.fabricmc.loader.api.FabricLoader;

import mezz.jei.api.runtime.IJeiRuntime;


@Mixin(HandledScreen.class)
public abstract class GUIKeybinding {
    @Shadow @Nullable protected Slot focusedSlot;
    @Unique private final MinecraftClient myClientVar = MinecraftClient.getInstance();

    @Inject(method = "keyPressed", at = @At("TAIL"))
    public void keyPressed(int keyCode, int scanCode, int modifiers, CallbackInfoReturnable<Boolean> cir) {
        if (keybinding.binding1.equals(new KeyBinding("kb.msrpp.key.lookItem", InputUtil.Type.KEYSYM, keyCode, "kb.msrpp.category.msrpp"))) {
            try {
                if (myClientVar.currentScreen instanceof AbstractInventoryScreen && this.focusedSlot != null) {
                    ItemStack item = this.focusedSlot.getStack();
                    if (item.isEmpty()) {
                        item = ((AbstractInventoryScreen<?>) myClientVar.currentScreen).getScreenHandler().getCursorStack();
                    };
                    if (item.isEmpty()){return;}
                    searchItem.openMCMODForItem(item);
                } else {
                    if (FabricLoader.getInstance().isModLoaded("jei")){ // 获取 JEI 上聚焦的物品
                        try {
                            ItemStack item = (ItemStack) JEIUtils.getStackUnderMouse();
                            if (item == null){return;}
                            searchItem.openMCMODForItem(item);
                        } catch (Exception exception1){
                            McmodSearchRebornPlusPlusClient.LOGGER.error("获取物品时发生错误 (JEI)", exception1);
                        }}
                }
            } catch (Exception exception) {
                    McmodSearchRebornPlusPlusClient.LOGGER.error("获取物品时发生错误", exception);
                    return;
            }
        }
    }
}