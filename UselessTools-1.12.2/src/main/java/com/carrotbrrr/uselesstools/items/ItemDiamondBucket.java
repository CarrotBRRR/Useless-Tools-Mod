package com.carrotbrrr.uselesstools.items;

import java.util.List;

import com.carrotbrrr.uselesstools.init.ModItems;

import net.minecraft.block.Block;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

// import net.minecraft.util.text.TextComponentString;

public class ItemDiamondBucket extends Item {

    private final Block containedBlock;

    public ItemDiamondBucket(Block containedBlock) {
        this.containedBlock = containedBlock;

        if (containedBlock != Blocks.AIR) {
            this.maxStackSize = 1;
        } else {
            this.maxStackSize = 16;
        }
    }

    @SuppressWarnings("null")
    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
        boolean flag = this.containedBlock == Blocks.AIR;
        ItemStack itemstack = player.getHeldItem(hand);
        RayTraceResult raytraceresult = this.rayTrace(world, player, flag);
        ActionResult<ItemStack> ret = net.minecraftforge.event.ForgeEventFactory.onBucketUse(player, world, itemstack, raytraceresult);

        Vec3d look = player.getLookVec();

        List<Entity> entities = world.getEntitiesWithinAABB(EntityCow.class, player.getEntityBoundingBox().expand(look.x * 4, look.y * 4, look.z * 4).expand(1, 1, 1));
    
        for(Entity entity : entities){
            if(entity instanceof EntityCow){
                EntityCow cow = (EntityCow) entity;
                double distanceToCow = player.getDistance(cow);
                Vec3d cowPos = new Vec3d(cow.posX, cow.posY - 1, cow.posZ);
                Vec3d playerPos = new Vec3d(player.posX, player.posY, player.posZ);
                Vec3d p2cow = cowPos.subtract(playerPos).normalize();

                double dot = p2cow.dotProduct(look);

                // player.sendMessage(new TextComponentString("Dot: " + dot + " Distance: " + distanceToCow));

                if(dot > 0.98 && distanceToCow <= 5){
                    if (this.containedBlock == Blocks.AIR){
                        return new ActionResult<ItemStack>(net.minecraft.util.EnumActionResult.SUCCESS, this.fillBucket(itemstack, player, ModItems.DIAMOND_BUCKET_MILK));
                    }
                }
            }
        }

        if(ret != null) return ret;

        if (raytraceresult == null) {
            return new ActionResult<ItemStack>(net.minecraft.util.EnumActionResult.PASS, itemstack);

        } else if(raytraceresult.typeOfHit != RayTraceResult.Type.BLOCK) {
            return new ActionResult<ItemStack>(net.minecraft.util.EnumActionResult.PASS, itemstack);

        } else {
            BlockPos blockpos = raytraceresult.getBlockPos();

            if(!world.isBlockModifiable(player, blockpos)) {
                return new ActionResult<ItemStack>(net.minecraft.util.EnumActionResult.FAIL, itemstack);

            } else if(flag) {
                if(!player.canPlayerEdit(blockpos.offset(raytraceresult.sideHit), raytraceresult.sideHit, itemstack)) {
                    return new ActionResult<ItemStack>(net.minecraft.util.EnumActionResult.FAIL, itemstack);

                } else {
                    Block block = world.getBlockState(blockpos).getBlock();

                    if((block == Blocks.WATER || block == Blocks.FLOWING_WATER) && ((Integer)world.getBlockState(blockpos).getValue(BlockLiquid.LEVEL)).intValue() == 0) {
                        world.setBlockToAir(blockpos);
                        player.addStat(net.minecraft.stats.StatList.getObjectUseStats(this));
                        player.playSound(SoundEvents.ITEM_BUCKET_FILL, 1.0F, 1.0F);
                        return new ActionResult<ItemStack>(net.minecraft.util.EnumActionResult.SUCCESS, this.fillBucket(itemstack, player, ModItems.DIAMOND_BUCKET_WATER));

                    } else if((block == Blocks.LAVA || block == Blocks.FLOWING_LAVA) && ((Integer)world.getBlockState(blockpos).getValue(BlockLiquid.LEVEL)).intValue() == 0){
                        world.setBlockToAir(blockpos);
                        player.addStat(net.minecraft.stats.StatList.getObjectUseStats(this));
                        player.playSound(SoundEvents.ITEM_BUCKET_FILL_LAVA, 1.0F, 1.0F);
                        return new ActionResult<ItemStack>(net.minecraft.util.EnumActionResult.SUCCESS, this.fillBucket(itemstack, player, ModItems.DIAMOND_BUCKET_LAVA));

                    } else {
                        return new ActionResult<ItemStack>(net.minecraft.util.EnumActionResult.FAIL, itemstack);
                    }
                }
            } else {
                boolean flag1 = world.getBlockState(blockpos).getBlock().isReplaceable(world, blockpos);
                BlockPos blockpos1 = flag1 && raytraceresult.sideHit == EnumFacing.UP ? blockpos : blockpos.offset(raytraceresult.sideHit);

                if(!player.canPlayerEdit(blockpos1, raytraceresult.sideHit, itemstack)) {
                    return new ActionResult<ItemStack>(net.minecraft.util.EnumActionResult.FAIL, itemstack);

                } else if(this.tryPlaceContainedLiquid(player, world, blockpos1)) {
                    if(player.capabilities.isCreativeMode) {
                        return new ActionResult<ItemStack>(net.minecraft.util.EnumActionResult.SUCCESS, itemstack);

                    } else {
                        return new ActionResult<ItemStack>(net.minecraft.util.EnumActionResult.SUCCESS, new ItemStack(ModItems.DIAMOND_BUCKET));
                    }
                } else {
                    return new ActionResult<ItemStack>(net.minecraft.util.EnumActionResult.FAIL, itemstack);
                }
            }
        }
    
    }

    private ItemStack fillBucket(ItemStack emptyBuckets, EntityPlayer player, Item fullBucket) {
        if (!player.capabilities.isCreativeMode) {
            emptyBuckets.shrink(1);

            if (emptyBuckets.isEmpty()) {
                return new ItemStack(fullBucket);

            } else if (!player.inventory.addItemStackToInventory(new ItemStack(fullBucket))) {
                player.dropItem(new ItemStack(fullBucket), false);
            }

        }

        return emptyBuckets;
    }

    public boolean tryPlaceContainedLiquid(EntityPlayer player, World world, BlockPos pos) {
        if(this.containedBlock == Blocks.AIR) {
            return false;

        } else {
            IBlockState iblockstate = world.getBlockState(pos);
            Material material = iblockstate.getMaterial();
            boolean flag = !material.isSolid();
            boolean flag1 = iblockstate.getBlock().isReplaceable(world, pos);

            if(!world.isAirBlock(pos) && !(flag || flag1)) {
                return false;

            } else {
                if(world.provider.doesWaterVaporize() && this.containedBlock == Blocks.FLOWING_WATER) {
                    int l = pos.getX();
                    int i = pos.getY();
                    int j = pos.getZ();
                    world.playSound(player, pos, SoundEvents.BLOCK_FIRE_EXTINGUISH, net.minecraft.util.SoundCategory.BLOCKS, 0.5F, 2.6F + (world.rand.nextFloat() - world.rand.nextFloat()) * 0.8F);

                    for(int k = 0; k < 8; ++k) {
                        world.spawnParticle(net.minecraft.util.EnumParticleTypes.SMOKE_LARGE, (double)l + Math.random(), (double)i + Math.random(), (double)j + Math.random(), 0.0D, 0.0D, 0.0D);
                    }

                } else {
                    if((flag || flag1) && !material.isLiquid()) {
                        world.destroyBlock(pos, true);
                    }

                    SoundEvent soundevent = this.containedBlock == Blocks.FLOWING_LAVA ? SoundEvents.ITEM_BUCKET_EMPTY_LAVA : SoundEvents.ITEM_BUCKET_EMPTY;
                    world.playSound(player, pos, soundevent, net.minecraft.util.SoundCategory.BLOCKS, 1.0F, 1.0F);
                    world.setBlockState(pos, this.containedBlock.getDefaultState(), 11);
                }

                return true;
            }
        }
    }
}


