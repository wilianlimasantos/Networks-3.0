package io.github.sefiraat.networks.slimefun.network;

import io.github.sefiraat.networks.NetworkStorage;
import io.github.sefiraat.networks.network.NetworkNode;
import io.github.sefiraat.networks.network.NetworkRoot;
import io.github.sefiraat.networks.network.NodeDefinition;
import io.github.sefiraat.networks.network.NodeType;
import io.github.sefiraat.networks.utils.Theme;
import io.github.thebusybiscuit.slimefun4.api.events.PlayerRightClickEvent;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.ItemSetting;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.items.settings.IntRangeSetting;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config;
import me.mrCookieSlime.Slimefun.Objects.handlers.BlockTicker;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class NetworkController extends NetworkObject {

    private static final String CRAYON = "crayon";
    private static final Map<Location, NetworkRoot> NETWORKS = new ConcurrentHashMap<>();
    private static final Set<Location> CRAYONS = ConcurrentHashMap.newKeySet();
    // Global set to track all locations currently claimed by any network
    private static final Set<Location> CLAIMED_LOCATIONS = ConcurrentHashMap.newKeySet();

    private final ItemSetting<Integer> maxNodes;
    protected final Map<Location, Boolean> firstTickMap = new HashMap<>();

    public NetworkController(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe, NodeType.CONTROLLER);

        maxNodes = new IntRangeSetting(this, "max_nodes", 10, 2000, 5000);
        addItemSetting(maxNodes);

        addItemHandler(
                new BlockTicker() {
                    @Override
                    public boolean isSynchronized() {
                        return true;
                    }

                    @Override
                    public void tick(Block block, SlimefunItem item, Config data) {

                        if (!firstTickMap.containsKey(block.getLocation())) {
                            onFirstTick(block, data);
                            firstTickMap.put(block.getLocation(), true);
                        }

                        // Check if another controller has already claimed our adjacent nodes
                        // If so, this controller is a duplicate and should be killed
                        if (isClaimedByAnotherController(block.getLocation())) {
                            return;
                        }

                        // Remove old claimed locations for this controller before rebuilding
                        NetworkRoot oldRoot = NETWORKS.get(block.getLocation());
                        if (oldRoot != null) {
                            CLAIMED_LOCATIONS.removeAll(oldRoot.getNodeLocations());
                        }

                        addToRegistry(block);
                        NetworkRoot networkRoot = new NetworkRoot(block.getLocation(), NodeType.CONTROLLER,
                                maxNodes.getValue());
                        networkRoot.addAllChildren();

                        // Update claimed locations with the new network's nodes
                        CLAIMED_LOCATIONS.addAll(networkRoot.getNodeLocations());

                        NodeDefinition definition = NetworkStorage.getAllNetworkObjects().get(block.getLocation());
                        if (definition != null) {
                            definition.setNode(networkRoot);
                        }

                        boolean crayon = CRAYONS.contains(block.getLocation());
                        if (crayon) {
                            networkRoot.setDisplayParticles(true);
                        }

                        NETWORKS.put(block.getLocation(), networkRoot);
                    }
                });
    }

    @Override
    protected void prePlace(@Nonnull PlayerRightClickEvent event) {
        Optional<Block> blockOptional = event.getClickedBlock();

        if (blockOptional.isPresent()) {
            Block block = blockOptional.get();
            Block target = block.getRelative(event.getClickedFace());

            for (BlockFace checkFace : CHECK_FACES) {
                Block checkBlock = target.getRelative(checkFace);
                SlimefunItem slimefunItem = BlockStorage.check(checkBlock);

                // For directly adjacent controllers
                if (slimefunItem instanceof NetworkController) {
                    cancelPlace(event);
                    return;
                }

                // Check the global claimed locations set - this is the most reliable check
                // as it persists across ticks and doesn't depend on node definitions being set
                if (CLAIMED_LOCATIONS.contains(checkBlock.getLocation())) {
                    cancelPlace(event);
                    return;
                }

                // Check for node definitions. If there isn't one, we don't care
                NodeDefinition definition = NetworkStorage.getAllNetworkObjects().get(checkBlock.getLocation());
                if (definition == null) {
                    continue;
                }

                // There is a definition, if it has a node, then it's part of an active network.
                if (definition.getNode() != null) {
                    cancelPlace(event);
                    return;
                }

                // Even if the node hasn't been set yet (between ticks), check if any
                // active network claims this location to prevent dual-controller exploits
                for (NetworkRoot existingNetwork : NETWORKS.values()) {
                    if (existingNetwork.getNodeLocations().contains(checkBlock.getLocation())) {
                        cancelPlace(event);
                        return;
                    }
                }
            }
        }
    }

    @Override
    protected void cancelPlace(PlayerRightClickEvent event) {
        event.getPlayer().sendMessage(Theme.ERROR.getColor() + "Esta rede já possui um controlador!");
        event.cancel();
    }

    private void onFirstTick(@Nonnull Block block, @Nonnull Config data) {
        final String crayon = data.getString(CRAYON);
        if (Boolean.parseBoolean(crayon)) {
            CRAYONS.add(block.getLocation());
        }
    }

    public static Map<Location, NetworkRoot> getNetworks() {
        return NETWORKS;
    }

    public static Set<Location> getClaimedLocations() {
        return CLAIMED_LOCATIONS;
    }

    /**
     * Checks if the nodes adjacent to this controller are already claimed
     * by another controller's network. If so, this is a duplicate controller.
     */
    private static boolean isClaimedByAnotherController(@Nonnull Location controllerLocation) {
        for (Map.Entry<Location, NetworkRoot> entry : NETWORKS.entrySet()) {
            if (entry.getKey().equals(controllerLocation)) {
                continue;
            }
            // Check if any of our adjacent blocks belong to another active network
            for (BlockFace face : CHECK_FACES) {
                Location adjacent = controllerLocation.clone().add(face.getDirection());
                if (entry.getValue().getNodeLocations().contains(adjacent)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static Set<Location> getCrayons() {
        return CRAYONS;
    }

    public static void addCrayon(@Nonnull Location location) {
        BlockStorage.addBlockInfo(location, CRAYON, String.valueOf(true));
        CRAYONS.add(location);
    }

    public static void removeCrayon(@Nonnull Location location) {
        BlockStorage.addBlockInfo(location, CRAYON, null);
        CRAYONS.remove(location);
    }

    public static boolean hasCrayon(@Nonnull Location location) {
        return CRAYONS.contains(location);
    }

    public static void wipeNetwork(@Nonnull Location location) {
        NetworkRoot networkRoot = NETWORKS.remove(location);
        if (networkRoot != null) {
            // Clean up claimed locations for this network
            CLAIMED_LOCATIONS.removeAll(networkRoot.getNodeLocations());
            for (NetworkNode node : networkRoot.getChildrenNodes()) {
                NetworkStorage.removeNode(node.getNodePosition());
            }
        }
    }
}
