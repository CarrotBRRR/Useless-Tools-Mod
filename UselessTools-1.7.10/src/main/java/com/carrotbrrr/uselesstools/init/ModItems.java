package com.carrotbrrr.uselesstools.init;

import com.carrotbrrr.uselesstools.UselessTools;
import com.carrotbrrr.uselesstools.items.DiamondBucket;
import com.carrotbrrr.uselesstools.items.DiamondBucketMilk;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;

public class ModItems {

    public static Item diamond_bucket;
    public static Item diamond_bucket_water;
    public static Item diamond_bucket_lava;
    public static Item diamond_bucket_milk;

    public static void init(){
        diamond_bucket = new DiamondBucket(Blocks.air).setUnlocalizedName("diamond_bucket").setTextureName(UselessTools.MODID + ":diamond_bucket");
        diamond_bucket_water = new DiamondBucket(Blocks.flowing_water).setUnlocalizedName("diamond_bucket_water").setTextureName(UselessTools.MODID + ":diamond_bucket_water");
        diamond_bucket_lava = new DiamondBucket(Blocks.flowing_lava).setUnlocalizedName("diamond_bucket_lava").setTextureName(UselessTools.MODID + ":diamond_bucket_lava");
        diamond_bucket_milk = new DiamondBucketMilk().setUnlocalizedName("diamond_bucket_milk").setTextureName(UselessTools.MODID + ":diamond_bucket_milk");

        GameRegistry.registerItem(diamond_bucket, diamond_bucket.getUnlocalizedName());
        GameRegistry.registerItem(diamond_bucket_water, diamond_bucket_water.getUnlocalizedName());
        GameRegistry.registerItem(diamond_bucket_lava, diamond_bucket_lava.getUnlocalizedName());
        GameRegistry.registerItem(diamond_bucket_milk, diamond_bucket_milk.getUnlocalizedName());
    }
}
