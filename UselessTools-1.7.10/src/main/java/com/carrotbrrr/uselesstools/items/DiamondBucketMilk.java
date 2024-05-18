package com.carrotbrrr.uselesstools.items;

import com.carrotbrrr.uselesstools.UselessTools;
import com.carrotbrrr.uselesstools.init.ModItems;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.*;
import net.minecraft.world.World;

public class DiamondBucketMilk extends Item{

   public DiamondBucketMilk() {
      this.setMaxStackSize(1);
      this.setCreativeTab(UselessTools.tabUselessToolsMod);
      this.setContainerItem(ModItems.diamond_bucket);
   }
  
   public ItemStack onEaten(ItemStack is, World world, EntityPlayer player) {
      if (!player.capabilities.isCreativeMode) {
         --is.stackSize;
      }

      ItemStack milk = new ItemStack(Items.milk_bucket);
      player.curePotionEffects(milk);

      return is.stackSize <= 0 ? new ItemStack(ModItems.diamond_bucket) : is;
   }

   public int getMaxItemUseDuration(ItemStack is) {
      return 32;
   }

   public EnumAction getItemUseAction(ItemStack is) {
      return EnumAction.drink;
   }

   public ItemStack onItemRightClick(ItemStack is, World world, EntityPlayer player) {
      player.setItemInUse(is, this.getMaxItemUseDuration(is));
      return is;
   }
}
