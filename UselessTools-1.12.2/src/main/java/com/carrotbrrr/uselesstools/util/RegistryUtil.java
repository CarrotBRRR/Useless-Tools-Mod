package com.carrotbrrr.uselesstools.util;

import com.carrotbrrr.uselesstools.UselessTools;

import net.minecraft.block.Block;
import net.minecraft.item.Item;

public class RegistryUtil {

    public static Item setItemName(final Item item, final String name) {
        item.setRegistryName(UselessTools.MODID + ":" + name);
        return item;

    }

    public static Block setBlockName(final Block block, final String name) {
        block.setRegistryName(UselessTools.MODID, name);
        return block;
    }
    
}
