package com.carrotbrrr.uselesstools.handlers;

import com.carrotbrrr.uselesstools.UselessTools;
import com.carrotbrrr.uselesstools.init.ModItems;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

@EventBusSubscriber(value = Side.CLIENT, modid = UselessTools.MODID)
public class ModelRegistrationHandler {
    
    @SubscribeEvent
    public static void registerModels(ModelRegistryEvent event) {
        registerModel(ModItems.DIAMOND_BUCKET, 0);
        registerModel(ModItems.DIAMOND_BUCKET_WATER, 0);
        registerModel(ModItems.DIAMOND_BUCKET_LAVA, 0);
        registerModel(ModItems.DIAMOND_BUCKET_MILK, 0);
    }

    private static void registerModel(Item item, int meta) {
        ModelLoader.setCustomModelResourceLocation(
            item, 
            meta, 
            new ModelResourceLocation(item.getRegistryName(), 
            "inventory"
        ));
    }
}
