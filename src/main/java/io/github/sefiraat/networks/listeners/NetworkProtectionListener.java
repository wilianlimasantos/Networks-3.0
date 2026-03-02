package io.github.sefiraat.networks.listeners;

import io.github.sefiraat.networks.NetworkStorage;
import io.github.sefiraat.networks.network.NodeDefinition;
import io.github.sefiraat.networks.slimefun.network.NetworkQuantumStorage;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockExplodeEvent;
import org.bukkit.event.block.BlockPistonExtendEvent;
import org.bukkit.event.block.BlockPistonRetractEvent;
import org.bukkit.event.entity.EntityExplodeEvent;

import javax.annotation.Nonnull;
import java.util.Iterator;
import java.util.List;

/**
 * Protects network blocks from being moved by pistons or destroyed by explosions.
 * Pistons moving network blocks would bypass placement checks and enable dupe exploits.
 * Explosions destroying network blocks without proper cleanup would orphan caches.
 */
public class NetworkProtectionListener implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onPistonExtend(@Nonnull BlockPistonExtendEvent event) {
        for (Block block : event.getBlocks()) {
            if (isNetworkBlock(block.getLocation())) {
                event.setCancelled(true);
                return;
            }
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onPistonRetract(@Nonnull BlockPistonRetractEvent event) {
        for (Block block : event.getBlocks()) {
            if (isNetworkBlock(block.getLocation())) {
                event.setCancelled(true);
                return;
            }
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onEntityExplode(@Nonnull EntityExplodeEvent event) {
        protectFromExplosion(event.blockList());
    }

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onBlockExplode(@Nonnull BlockExplodeEvent event) {
        protectFromExplosion(event.blockList());
    }

    private void protectFromExplosion(@Nonnull List<Block> blockList) {
        Iterator<Block> iterator = blockList.iterator();
        while (iterator.hasNext()) {
            Block block = iterator.next();
            if (isNetworkBlock(block.getLocation())) {
                iterator.remove();
            }
        }
    }

    private boolean isNetworkBlock(@Nonnull Location location) {
        NodeDefinition definition = NetworkStorage.getAllNetworkObjects().get(location);
        if (definition != null) {
            return true;
        }
        return NetworkQuantumStorage.getCaches().containsKey(location);
    }
}
