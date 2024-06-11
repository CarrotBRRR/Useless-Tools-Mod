package com.carrotbrrr.uselesstools.blocks;

import com.carrotbrrr.uselesstools.UselessTools;

import net.minecraft.block.Block;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.material.Material;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.IForgeRegistry;

public class BlockHalfBed extends BlockHorizontal implements IForgeRegistry<Block>{

    public BlockHalfBed(String name, Material material) {
        super(material);
        setUnlocalizedName(name);
        setRegistryName(name);
    }



}
