package com.wangyupu.mcmod.msrpp.utils;

import com.wangyupu.mcmod.msrpp.McmodSearchRebornPlusPlusClient;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.runtime.IJeiRuntime;
import mezz.jei.api.constants.VanillaTypes;

import net.minecraft.util.Identifier;

import org.jetbrains.annotations.Nullable;

@JeiPlugin
public class JEIUtils implements IModPlugin {
    public static IJeiRuntime JEIRuntime = null;

    @Override
    public Identifier getPluginUid() {
        return new Identifier("msrpp", "msrpp");
    }

    @Override
    public void onRuntimeAvailable(IJeiRuntime jeiRuntime) {
        McmodSearchRebornPlusPlusClient.LOGGER.warn("JEI 运行时就绪");
        JEIRuntime = jeiRuntime;
    }

    @Nullable
    public static Object getStackUnderMouse(){
        if (JEIRuntime == null) {
            McmodSearchRebornPlusPlusClient.LOGGER.error("JEI 运行时不存在");
            return null;
        } else {
            return JEIRuntime.getIngredientListOverlay().getIngredientUnderMouse(VanillaTypes.ITEM_STACK);
        }
    }
}
