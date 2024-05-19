package com.carrotbrrr.uselesstools.handlers;

import com.carrotbrrr.uselesstools.UselessTools;
import com.carrotbrrr.uselesstools.item.ItemDiamondBucket;
import com.carrotbrrr.uselesstools.item.ItemDiamondBucketMilk;
import com.carrotbrrr.uselesstools.util.RegistryUtil;

import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@EventBusSubscriber(modid = UselessTools.MODID)
public class RegistrationHandler {
    
    @SubscribeEvent
    public static void registerItems(Register<Item> event) {
        final Item[] items = {
            
            RegistryUtil.setItemName(new ItemDiamondBucket(Blocks.AIR), "diamond_bucket").setUnlocalizedName("diamond_bucket").setCreativeTab(UselessTools.USELESS_ITEMS_TAB),
            RegistryUtil.setItemName(new ItemDiamondBucket(Blocks.FLOWING_WATER), "diamond_bucket_water").setUnlocalizedName("diamond_bucket_water").setCreativeTab(UselessTools.USELESS_ITEMS_TAB),
            RegistryUtil.setItemName(new ItemDiamondBucket(Blocks.FLOWING_LAVA), "diamond_bucket_lava").setUnlocalizedName("diamond_bucket_lava").setCreativeTab(UselessTools.USELESS_ITEMS_TAB),
            
            RegistryUtil.setItemName(new ItemDiamondBucketMilk(), "diamond_bucket_milk").setUnlocalizedName("diamond_bucket_milk").setCreativeTab(UselessTools.USELESS_ITEMS_TAB)
        
        };

        event.getRegistry().registerAll(items);
    }
    
}
