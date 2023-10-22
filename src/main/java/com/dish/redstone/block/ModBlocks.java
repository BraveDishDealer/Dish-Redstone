package com.dish.redstone.block;

import com.dish.redstone.RedstoneDish;
import com.dish.redstone.block.custom.Detector;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModBlocks {

    public static final Block DETECTOR = registerBlock("detector", new Detector(FabricBlockSettings.copy(Blocks.STONE)));
    public static final Block BROKEN_DETECTOR = registerBlock("broken_detector", new Block(FabricBlockSettings.copy(Blocks.STONE)));

    private static Block registerBlock(String name, Block block)
    {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, new Identifier(RedstoneDish.MOD_ID, name), block);
    }

    private static Item registerBlockItem(String name, Block block)
    {
        Item item = Registry.register(Registries.ITEM, new Identifier(RedstoneDish.MOD_ID, name), new BlockItem(block, new FabricItemSettings()));
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.REDSTONE).register(entries -> entries.add(item));
        return item;
    }

    public static void registerBlocks()
    {

    }
}
