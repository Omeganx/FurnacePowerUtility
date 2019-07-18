package com.omeganx.plugin.signalstrenght;

import com.github.intellectualsites.plotsquared.bukkit.util.BukkitUtil;
import com.github.intellectualsites.plotsquared.plot.object.Location;
import com.github.intellectualsites.plotsquared.plot.object.Plot;
import com.github.intellectualsites.plotsquared.plot.object.PlotArea;
import com.github.intellectualsites.plotsquared.plot.object.PlotPlayer;
import com.github.intellectualsites.plotsquared.plot.util.Permissions;

import com.omeganx.plugin.signalstrenght.SsFurnace.FurnaceListener;
import com.omeganx.plugin.signalstrenght.SsFurnace.ssFurnace;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import static com.github.intellectualsites.plotsquared.plot.config.Captions.*;
import com.github.intellectualsites.plotsquared.plot.PlotSquared;

public class MainTopredStoneTweaks extends JavaPlugin
{

    @Override

    public void onEnable()
    {
        System.out.println("FurnacePowerPlugin enabled.");

        registerCommands();
        registerEvents();
    }

    @Override

    public void onDisable()
    {
        System.out.println("FurnacePowerPlugin disabled.");
    }


    public void registerEvents()
    {
        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(new FurnaceListener(), this);
    }


    public void registerCommands()
    {
        this.getCommand("f").setExecutor(new ssFurnace());
    }

    public static boolean canBuild(PlayerInteractEvent event) {

        Player player = event.getPlayer();
        Location location = BukkitUtil.getLocation(event.getClickedBlock().getLocation());

        Plot plot = location.getPlot();

        if (plot != null) {
            PlotPlayer plotPlayer = BukkitUtil.getPlayer(player);
            if (!plot.hasOwner()) {
                return Permissions.hasPermission(plotPlayer, PERMISSION_ADMIN_DESTROY_UNOWNED);
            }
            if (plot.getOwners().contains(player.getUniqueId())) return true;
            if (!plot.isAdded(plotPlayer.getUUID())) {

                return Permissions.hasPermission(plotPlayer, PERMISSION_ADMIN_DESTROY_OTHER);
            }
            return false;
        }
        PlotPlayer pp = BukkitUtil.getPlayer(player);
        return Permissions.hasPermission(pp, PERMISSION_ADMIN_DESTROY_ROAD);
    }

    public static void main(String[] args) {
        System.out.println("Hello World!");
    }
}