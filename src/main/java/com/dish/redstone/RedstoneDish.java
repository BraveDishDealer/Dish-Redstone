package com.dish.redstone;

import com.dish.redstone.block.ModBlocks;
import net.fabricmc.api.ModInitializer;

import net.minecraft.server.MinecraftServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RedstoneDish implements ModInitializer {
    public static final Logger LOGGER = LoggerFactory.getLogger("redstonedish");
	public static final String MOD_ID = "redstonedish";

	@Override
	public void onInitialize() {
		ModBlocks.registerBlocks();
	}
}