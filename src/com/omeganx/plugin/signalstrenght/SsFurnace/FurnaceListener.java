package com.omeganx.plugin.signalstrenght.SsFurnace;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Furnace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class FurnaceListener implements Listener {



    @EventHandler(priority= EventPriority.LOW)

    public void onPlayerInteract(PlayerInteractEvent event){

        EquipmentSlot e = event.getHand();
        if(e != null && e.equals(EquipmentSlot.HAND)){
            Player p = event.getPlayer();

            Block b = event.getClickedBlock();

            if(p.isSneaking() && event.getAction() == Action.RIGHT_CLICK_BLOCK && p.getInventory().getItemInMainHand().getType() == Material.WOODEN_HOE  && b.getType() == Material.FURNACE) {


                Furnace f = (Furnace) b.getState();

                Inventory inv = f.getInventory();

                ItemStack[] is = inv.getContents();

                double count = 0;
                int a = 0;
                for (int i = 0; i < 3; i++) {
                    if (is[i] != null) {

                        a = is[i].getAmount();
                        if(is[i].getMaxStackSize() == 1)
                        {
                            a *= 64;
                        }else if (is[i].getMaxStackSize() == 16)
                        {
                            a *=4;
                        }

                        count += a;
                    }
                }
                int c = (int)Math.ceil(15f*(count-1) / (3f*64f));



                if (count == 1) c = 1;

                if(c == 15){
                    inv.clear();
                }
                else{
                    c += 1;

                    inv.clear();
                    ItemStack is_ = new ItemStack(Material.REDSTONE, 1);
                    int i = 0;
                    while (((float) i / (3 * 64)) < ((float) c / 15)) {
                        inv.addItem(is_);
                        i++;
                    }
                }
            }

        }
    }

}
