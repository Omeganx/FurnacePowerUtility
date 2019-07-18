package com.omeganx.plugin.signalstrenght;


import io.netty.handler.codec.http2.Http2FrameLogger;
import org.bukkit.Material;
import org.bukkit.Rotation;
import org.bukkit.block.Block;

import org.bukkit.block.BlockFace;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;

import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.material.Directional;

public class BlockRotateListener implements Listener {


    @EventHandler(priority= EventPriority.LOW)

    public void onPlayerInteract(PlayerInteractEvent event){


        EquipmentSlot e = event.getHand();
        if(e.equals(EquipmentSlot.HAND)) {
            Player p = event.getPlayer();
            Block b = event.getClickedBlock();
            BlockData bp = b.getBlockData();
            if(event.getAction() == Action.RIGHT_CLICK_BLOCK && p.getInventory().getItemInMainHand().getType() == Material.WOODEN_HOE && false) {


                    if (bp instanceof Directional){
                    BlockFace bf = ((Directional) bp).getFacing();
                    if (bf.equals(BlockFace.NORTH)) {

                        ((Directional) bp).setFacingDirection(BlockFace.EAST);

                    } else if (bf.equals(BlockFace.EAST)) {
                        ((Directional) bp).setFacingDirection(BlockFace.SOUTH);
                    } else if (bf.equals(BlockFace.SOUTH)) {
                        ((Directional) bp).setFacingDirection(BlockFace.WEST);

                    } else if (bf.equals(BlockFace.WEST)) {
                        ((Directional) bp).setFacingDirection(BlockFace.NORTH);
                    }
                    b.setBlockData(bp);
                }
            }

        }
    }
}
