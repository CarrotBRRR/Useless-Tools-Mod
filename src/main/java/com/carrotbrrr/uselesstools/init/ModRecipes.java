package com.carrotbrrr.uselesstools.init;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class ModRecipes {
    public static void init() {
        // Diamond Bucket Recipe
        GameRegistry.addRecipe(new ItemStack(ModItems.diamond_bucket), 
            "DXD", 
            "XDX",
            "XXX",
            'D', new ItemStack(net.minecraft.init.Items.diamond));

        GameRegistry.addRecipe(new ItemStack(ModItems.diamond_bucket),
            "XXX",
            "DXD",
            "XDX",
            'D', new ItemStack(net.minecraft.init.Items.diamond));
        
        GameRegistry.addShapelessRecipe(new ItemStack(ModItems.diamond_bucket_milk),
            ModItems.diamond_bucket, Items.milk_bucket);
    }
}
