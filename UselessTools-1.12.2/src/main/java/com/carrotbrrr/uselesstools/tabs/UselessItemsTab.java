package com.carrotbrrr.uselesstools.tabs;

import com.carrotbrrr.uselesstools.init.ModItems;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class UselessItemsTab extends CreativeTabs{

    public UselessItemsTab() {
        super("uselessitemstab");
//        this.setBackgroundImageName("diamond_bucket_milk.png");
    }

    public ItemStack getTabIconItem() {
        return new ItemStack(ModItems.DIAMOND_BUCKET_MILK);
    }
}
