package com.carrotbrrr.uselesstools.items;

import com.carrotbrrr.uselesstools.init.ModItems;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class ItemDiamondBucketMilk extends Item{

    public ItemDiamondBucketMilk() {
        this.maxStackSize = 1;
    }

    @SuppressWarnings("null")
    public ItemStack onItemUseFinish(ItemStack stack, World world, EntityLivingBase entityLiving){
        if (entityLiving instanceof EntityPlayer){
            ((EntityPlayer)entityLiving).curePotionEffects(new ItemStack(Items.MILK_BUCKET));
        }

        if (entityLiving instanceof EntityPlayerMP)
        {
            EntityPlayerMP entityplayermp = (EntityPlayerMP)entityLiving;
            CriteriaTriggers.CONSUME_ITEM.trigger(entityplayermp, stack);
            entityplayermp.addStat(StatList.getObjectUseStats(this));
        }

        if (!((EntityPlayer)entityLiving).capabilities.isCreativeMode)
        {
            stack.shrink(1);
            return new ItemStack(ModItems.DIAMOND_BUCKET);

        } else {
            return stack;
        }
    }

    @SuppressWarnings("null")
    public int getMaxItemUseDuration(ItemStack stack)
    {
        return 32;
    }
    
    @SuppressWarnings("null")
    public EnumAction getItemUseAction(ItemStack stack)
    {
        return EnumAction.DRINK;
    }

    @SuppressWarnings("null")
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer playerIn, EnumHand handIn)
    {
        playerIn.setActiveHand(handIn);
        return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, playerIn.getHeldItem(handIn));
    }

}
