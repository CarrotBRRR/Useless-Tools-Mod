package com.carrotbrrr.uselesstools.items;

import java.util.List;

import com.carrotbrrr.uselesstools.UselessTools;
import com.carrotbrrr.uselesstools.init.ModItems;

import cpw.mods.fml.common.eventhandler.Event.Result;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.FillBucketEvent;

public class DiamondBucket extends Item{
    private Block isFull;
 
    public DiamondBucket(Block block) {
        this.isFull = block;
        if(this.isFull != Blocks.air){
            this.setMaxStackSize(1);
        } else {
            this.setMaxStackSize(16);
        }

        this.setCreativeTab(UselessTools.tabUselessToolsMod);
    }

    public ItemStack onItemRightClick(ItemStack is, World world, EntityPlayer player){
        boolean flag = this.isFull == Blocks.air;
        MovingObjectPosition movingobjectposition = this.getMovingObjectPositionFromPlayer(world, player, flag);
        
        Vec3 look = player.getLookVec();

        @SuppressWarnings("unchecked")
        List<Entity> entities = world.getEntitiesWithinAABB(EntityCow.class, player.boundingBox.addCoord(look.xCoord * 4, look.yCoord * 4, look.zCoord * 4).expand(1, 1, 1));
    
        for(Entity entity : entities){
            if(entity instanceof EntityCow){
                EntityCow cow = (EntityCow) entity;
                double distanceToCow = player.getDistanceToEntity(cow);
                Vec3 cowPos = Vec3.createVectorHelper(cow.posX, cow.posY - 1, cow.posZ);
                Vec3 playerPos = Vec3.createVectorHelper(player.posX, player.posY, player.posZ);
                Vec3 p2cow = cowPos.subtract(playerPos).normalize();

                double dot = p2cow.dotProduct(look);

                if(dot < -0.99 && distanceToCow <= 5){
                    if (this.isFull == Blocks.air){
                        return this.tryFill(is, player, ModItems.diamond_bucket_milk);
                    }
                }
            }
        }

        if (movingobjectposition == null){
            return is;
        } else {
            FillBucketEvent event = new FillBucketEvent(player, is, world, movingobjectposition);
            if (net.minecraftforge.common.MinecraftForge.EVENT_BUS.post(event)){
                return is;
            } else if (event.getResult() == Result.ALLOW){
                if (player.capabilities.isCreativeMode){
                    return is;
                } else if (--is.stackSize <= 0){
                    return event.result;
                } else {
                    if (!player.inventory.addItemStackToInventory(event.result)){
                        player.dropPlayerItemWithRandomChoice(event.result, false);
                    }
                    return is;
                }
            } else {
                if(movingobjectposition.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK){
                    int x = movingobjectposition.blockX;
                    int y = movingobjectposition.blockY;
                    int z = movingobjectposition.blockZ;

                    if (!world.canMineBlock(player, x, y, z)){
                        return is;
                    }
                    if (flag){
                        if (!player.canPlayerEdit(x, y, z, movingobjectposition.sideHit, is)){
                            return is;
                        }
                        
                        Material material = world.getBlock(x, y, z).getMaterial();
                        int l = world.getBlockMetadata(x, y, z);
                        if (material == Material.water && l == 0){
                            world.setBlockToAir(x, y, z);
                            return this.tryFill(is, player, ModItems.diamond_bucket_water);
                        }

                        if (material == Material.lava && l == 0){
                            world.setBlockToAir(x, y, z);
                            return this.tryFill(is, player, ModItems.diamond_bucket_lava);
                        }
                    } else {
                        if (this.isFull == Blocks.air){
                            return new ItemStack(ModItems.diamond_bucket);
                        }
                        
                        if (movingobjectposition.sideHit == 0){
                            --y;
                        }
                        if (movingobjectposition.sideHit == 1){
                            ++y;
                        }
                        if (movingobjectposition.sideHit == 2){
                            --z;
                        }
                        if (movingobjectposition.sideHit == 3){
                            ++z;
                        }
                        if (movingobjectposition.sideHit == 4){
                            --x;
                        }
                        if (movingobjectposition.sideHit == 5){
                            ++x;
                        }
                        if (!player.canPlayerEdit(x, y, z, movingobjectposition.sideHit, is)){
                            return is;
                        }
                        if (this.tryPlaceContainedLiquid(world, x, y, z) && !player.capabilities.isCreativeMode){
                            return new ItemStack(ModItems.diamond_bucket);
                        }
                    }
                }
                return is;
            }
        }
    }

    private ItemStack tryFill(ItemStack is, EntityPlayer player, Item item){
        if (player.capabilities.isCreativeMode){
            return is;
        } else if (--is.stackSize <= 0){
            return new ItemStack(item);
        } else {
            if (!player.inventory.addItemStackToInventory(new ItemStack(item))){
                player.dropPlayerItemWithRandomChoice(new ItemStack(item), false);
            }
            return is;
        }
    }

    public boolean tryPlaceContainedLiquid(World world, int x, int y, int z){
        if (this.isFull == Blocks.air){
            return false;
        } else {
            Material material = world.getBlock(x, y, z).getMaterial();
            boolean flag = !material.isSolid();
            if (!world.isAirBlock(x, y, z) && !flag){
                return false;
            } else {
                // UNCOMMENT THIS IF JORDAN DISCOVERS
                // if (world.provider.isHellWorld && this.isFull == Blocks.flowing_water){

                //     world.playSoundEffect((double)((float)x + 0.5F), (double)((float)y + 0.5F), (double)((float)z + 0.5F), "random.fizz", 0.5F, 2.6F + (world.rand.nextFloat() - world.rand.nextFloat()) * 0.8F);
                //     for (int l = 0; l < 8; ++l){
                //         world.spawnParticle("largesmoke", (double)x + Math.random(), (double)y + Math.random(), (double)z + Math.random(), 0.0D, 0.0D, 0.0D);
                //     }

                // } else 
                {
                    if (!material.isLiquid()){
                        world.func_147480_a(x, y, z, true);
                    }

                    world.setBlock(x, y, z, this.isFull, 0, 3);
                }

                return true;
            }
        }
    }
}
