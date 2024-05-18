package com.carrotbrrr.uselesstools;

import com.carrotbrrr.uselesstools.init.ModItems;
import com.carrotbrrr.uselesstools.init.ModRecipes;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.*;
import cpw.mods.fml.common.event.*;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

@Mod(modid = UselessTools.MODID, version = UselessTools.VERSION)
public class UselessTools {
    public static final String MODID = "UselessTools";
    public static final String VERSION = "0.1.2";

    @Instance(MODID)
    public static UselessTools instance;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event){
        ModItems.init();
        ModRecipes.init();
    }

    @EventHandler
    public void init(FMLInitializationEvent event){

    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event){

    }

    public static CreativeTabs tabUselessToolsMod = new CreativeTabs("tab_uselesstoolsmod"){
        @Override
        public Item getTabIconItem(){
            return new ItemStack(ModItems.diamond_bucket).getItem();
        }
    };

}
