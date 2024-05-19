package com.carrotbrrr.uselesstools;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import com.carrotbrrr.uselesstools.tabs.UselessItemsTab;

@Mod(modid = UselessTools.MODID, name = UselessTools.NAME, version = UselessTools.VERSION, useMetadata = true)
public class UselessTools {
    public static final String MODID = "uselesstools";
    public static final String NAME = "Useless Tools";
    public static final String VERSION = "0.1.0";

    public static final Logger LOGGER = LogManager.getLogger(MODID);

    public static final UselessItemsTab USELESS_ITEMS_TAB = new UselessItemsTab();

    @Instance(MODID)
    public static UselessTools instance;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        LOGGER.info(MODID + " is loading!");
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        LOGGER.info(MODID + "loaded!");
    }

    @EventHandler
    public void postInit(FMLInitializationEvent event) {
    }
}
