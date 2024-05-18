package com.carrotbrrr.uselesstools;

import net.minecraft.init.Blocks;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;

@Mod(modid = UselessTools.MODID, name = UselessTools.NAME, version = UselessTools.VERSION, useMetadata = true)
public class UselessTools {
    public static final String MODID = "uselesstools";
    public static final String NAME = "Useless Tools";
    public static final String VERSION = "0.1.0";

    private static Logger logger;

    @Instance(MODID)
    public static UselessTools instance;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        logger = event.getModLog();
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        
    }

    @EventHandler
    public void postInit(FMLInitializationEvent event) {
    }
}
