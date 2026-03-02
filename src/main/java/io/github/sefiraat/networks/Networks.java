package io.github.sefiraat.networks;

import io.github.sefiraat.networks.commands.NetworksMain;
import io.github.sefiraat.networks.managers.ListenerManager;
import io.github.sefiraat.networks.managers.SupportedPluginManager;
import io.github.sefiraat.networks.integrations.HudCallbacks;
import io.github.sefiraat.networks.integrations.NetheoPlants;
import io.github.sefiraat.networks.slimefun.NetworkSlimefunItems;
import io.github.sefiraat.networks.slimefun.network.NetworkController;
import io.github.thebusybiscuit.slimefun4.api.SlimefunAddon;
import io.github.thebusybiscuit.slimefun4.libraries.dough.updater.BlobBuildUpdater;

import org.bstats.bukkit.Metrics;
import org.bstats.charts.AdvancedPie;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.HashSet;
import java.util.Set;
import org.bukkit.Location;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import io.github.thebusybiscuit.slimefun4.implementation.Slimefun;
import io.github.sefiraat.networks.utils.NetworkUtils;
import java.util.concurrent.TimeUnit;
import org.bukkit.Bukkit;

public class Networks extends JavaPlugin implements SlimefunAddon {

    private static Networks instance;

    private final String username;
    private final String repo;
    private final String branch;

    private ListenerManager listenerManager;
    private SupportedPluginManager supportedPluginManager;

    public Networks() {
        this.username = "Sefiraat";
        this.repo = "Networks";
        this.branch = "master";
    }

    @Override
    public void onEnable() {
        instance = this;

        getLogger().info("########################################");
        getLogger().info("         Networks - By Sefiraat         ");
        getLogger().info("########################################");

        saveDefaultConfig();
        tryUpdate();

        this.supportedPluginManager = new SupportedPluginManager();

        setupSlimefun();

        this.listenerManager = new ListenerManager();
        this.getCommand("networks").setExecutor(new NetworksMain());

        setupMetrics();

        // Fix dupe bug which break the network controller data without player
        // interaction
        try {
            long tickRate = Slimefun.getTickerTask().getTickRate();
            getServer().getScheduler().runTaskTimer(this, this::cleanupCorruptedNetworks, 1L, tickRate);
        } catch (UnsupportedOperationException e) {
            // Folia detected - Folia doesn't support global scheduler
            getLogger().info("Folia detected - scheduling dupe fix task using RegionScheduler");
            startFoliaDupeFixTask();
        }
    }

    public void tryUpdate() {
        if (getConfig().getBoolean("auto-update") && getDescription().getVersion().startsWith("Dev")) {
            new BlobBuildUpdater(this, getFile(), "Networks", "Dev").start();
        }
    }

    private void cleanupCorruptedNetworks() {
        Set<Location> wrongs = new HashSet<>();
        Set<Location> controllers = new HashSet<>(NetworkController.getNetworks().keySet());
        for (Location controller : controllers) {
            io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem item = BlockStorage.check(controller);
            if (item == null || !NetworkSlimefunItems.NETWORK_CONTROLLER.getId().equals(item.getId())) {
                wrongs.add(controller);
            }
        }

        for (Location wrong : wrongs) {
            NetworkUtils.clearNetwork(wrong);
        }
    }

    private void startFoliaDupeFixTask() {
        long tickRate = Slimefun.getTickerTask().getTickRate();

        Bukkit.getGlobalRegionScheduler().runAtFixedRate(this, (task) -> {
            Set<Location> controllers = new HashSet<>(NetworkController.getNetworks().keySet());
            for (Location controller : controllers) {
                if (controller == null || controller.getWorld() == null) {
                    continue;
                }

                if (!controller.getWorld().isChunkLoaded(controller.getBlockX() >> 4, controller.getBlockZ() >> 4)) {
                    continue;
                }

                Bukkit.getRegionScheduler().run(this, controller, (regionTask) -> {
                    io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem item = BlockStorage.check(controller);
                    if (item == null || !NetworkSlimefunItems.NETWORK_CONTROLLER.getId().equals(item.getId())) {
                        NetworkUtils.clearNetwork(controller);
                    }
                });
            }
        }, 1L, tickRate);
    }

    public void setupSlimefun() {
        NetworkSlimefunItems.setup();
        if (supportedPluginManager.isNetheopoiesis()) {
            try {
                NetheoPlants.setup();
            } catch (NoClassDefFoundError e) {
                getLogger().severe("Netheopoiesis must be updated to meet Networks' requirements.");
            }
        }
        if (supportedPluginManager.isSlimeHud()) {
            try {
                HudCallbacks.setup();
            } catch (NoClassDefFoundError e) {
                getLogger().severe("SlimeHUD must be updated to meet Networks' requirements.");
            }
        }
    }

    public void setupMetrics() {
        final Metrics metrics = new Metrics(this, 13644);

        AdvancedPie networksChart = new AdvancedPie("networks", () -> {
            Map<String, Integer> networksMap = new HashMap<>();
            networksMap.put("Number of networks", NetworkController.getNetworks().size());
            return networksMap;
        });

        metrics.addCustomChart(networksChart);
    }

    @Nonnull
    @Override
    public JavaPlugin getJavaPlugin() {
        return this;
    }

    @Nullable
    @Override
    public String getBugTrackerURL() {
        return MessageFormat.format("https://github.com/{0}/{1}/issues/", this.username, this.repo);
    }

    @Nonnull
    public static PluginManager getPluginManager() {
        return Networks.getInstance().getServer().getPluginManager();
    }

    public static Networks getInstance() {
        return Networks.instance;
    }

    public static SupportedPluginManager getSupportedPluginManager() {
        return Networks.getInstance().supportedPluginManager;
    }

    public static ListenerManager getListenerManager() {
        return Networks.getInstance().listenerManager;
    }
}
