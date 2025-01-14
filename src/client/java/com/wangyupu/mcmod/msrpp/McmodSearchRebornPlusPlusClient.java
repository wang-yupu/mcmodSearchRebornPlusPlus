package com.wangyupu.mcmod.msrpp;

import net.fabricmc.api.ClientModInitializer;
import com.wangyupu.mcmod.msrpp.utils.keybinding;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class McmodSearchRebornPlusPlusClient implements ClientModInitializer {
	public static final Logger LOGGER = LoggerFactory.getLogger("MSRPP");
	@Override
	public void onInitializeClient() {
		if(Boolean.parseBoolean(System.getProperty("java.awt.headless"))){
			System.setProperty("java.awt.headless","false");
		};
		keybinding.initKeybinding();
	}
}