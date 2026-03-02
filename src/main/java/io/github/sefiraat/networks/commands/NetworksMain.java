package io.github.sefiraat.networks.commands;

import io.github.sefiraat.networks.network.stackcaches.QuantumCache;
import io.github.sefiraat.networks.slimefun.NetworkSlimefunItems;
import io.github.sefiraat.networks.slimefun.network.NetworkQuantumStorage;
import io.github.sefiraat.networks.utils.Keys;
import io.github.sefiraat.networks.utils.Theme;
import io.github.sefiraat.networks.utils.datatypes.DataTypeMethods;
import io.github.sefiraat.networks.utils.datatypes.PersistentQuantumStorageType;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import javax.annotation.Nonnull;
import java.util.HashMap;
import java.util.Map;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;

import org.bukkit.command.TabExecutor;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.bukkit.util.StringUtil;

public class NetworksMain implements TabExecutor {

    private static final Map<Integer, NetworkQuantumStorage> QUANTUM_REPLACEMENT_MAP = new HashMap<>();

    static {
        QUANTUM_REPLACEMENT_MAP.put(4096, NetworkSlimefunItems.NETWORK_QUANTUM_STORAGE_1);
        QUANTUM_REPLACEMENT_MAP.put(32768, NetworkSlimefunItems.NETWORK_QUANTUM_STORAGE_2);
        QUANTUM_REPLACEMENT_MAP.put(262144, NetworkSlimefunItems.NETWORK_QUANTUM_STORAGE_3);
        QUANTUM_REPLACEMENT_MAP.put(2097152, NetworkSlimefunItems.NETWORK_QUANTUM_STORAGE_4);
        QUANTUM_REPLACEMENT_MAP.put(16777216, NetworkSlimefunItems.NETWORK_QUANTUM_STORAGE_5);
        QUANTUM_REPLACEMENT_MAP.put(134217728, NetworkSlimefunItems.NETWORK_QUANTUM_STORAGE_6);
        QUANTUM_REPLACEMENT_MAP.put(1073741824, NetworkSlimefunItems.NETWORK_QUANTUM_STORAGE_7);
        QUANTUM_REPLACEMENT_MAP.put(Integer.MAX_VALUE, NetworkSlimefunItems.NETWORK_QUANTUM_STORAGE_8);
    }

    @Override
    public boolean onCommand(@Nonnull CommandSender sender, @Nonnull Command command, @Nonnull String label,
            @Nonnull String[] args) {
        if (sender instanceof Player player) {

            if (args.length == 0) {
                sendHelp(player);
                return true;
            }

            if (args[0].equalsIgnoreCase("fillquantum")) {
                if ((player.isOp() || player.hasPermission("networks.admin")) && args.length >= 2) {
                    try {
                        int number = Integer.parseInt(args[1]);
                        fillQuantum(player, number);
                        return true;
                    } catch (NumberFormatException exception) {
                        player.sendMessage(Theme.ERROR + "Número inválido.");
                        return true;
                    }
                } else {
                    player.sendMessage(Theme.ERROR + "Uso: /networks fillquantum <quantidade>");
                    return true;
                }
            }
            if (args[0].equalsIgnoreCase("setquantum")) {
                if ((player.isOp() || player.hasPermission("networks.admin")) && args.length >= 2) {
                    try {
                        int number = Integer.parseInt(args[1]);
                        setQuantum(player, number);
                        return true;
                    } catch (NumberFormatException exception) {
                        player.sendMessage(Theme.ERROR + "Número inválido.");
                        return true;
                    }
                } else {
                    player.sendMessage(Theme.ERROR + "Uso: /networks setquantum <quantidade>");
                    return true;
                }
            }

            sendHelp(player);
        }
        return true;
    }

    private void sendHelp(Player player) {
        player.sendMessage(Theme.mM("       &9Networks Help"));
        if (player.isOp() || player.hasPermission("networks.admin")) {
            player.sendMessage(
                    Theme.mM("&b/networks fillquantum <amount> &7- Define a quantidade no cartão quântico na mão"));
            player.sendMessage(
                    Theme.mM("&b/networks setquantum <amount> &7- Define a quantidade no bloco quântico olhado"));
        } else {
            player.sendMessage(Theme.mM("&cVocê não tem permissão para usar este comando."));
        }
    }

    @Override
    public List<String> onTabComplete(@Nonnull CommandSender sender, @Nonnull Command command, @Nonnull String alias,
            @Nonnull String[] args) {
        if (args.length == 1) {
            List<String> completions = new ArrayList<>();
            if (sender.hasPermission("networks.admin")) {
                completions.add("fillquantum");
                completions.add("setquantum");
            }
            return StringUtil.copyPartialMatches(args[0], completions, new ArrayList<>());
        }
        return Collections.emptyList();
    }

    public void fillQuantum(Player player, int amount) {
        final ItemStack itemStack = player.getInventory().getItemInMainHand();
        if (itemStack == null || itemStack.getType() == Material.AIR) {
            player.sendMessage(Theme.ERROR + "O item na mão deve ser um Armazenamento Quântico.");
            return;
        }

        SlimefunItem slimefunItem = SlimefunItem.getByItem(itemStack);

        if (!(slimefunItem instanceof NetworkQuantumStorage)) {
            player.sendMessage(Theme.ERROR + "O item na mão deve ser um Armazenamento Quântico.");
            return;
        }

        ItemMeta meta = itemStack.getItemMeta();
        final QuantumCache quantumCache = DataTypeMethods.getCustom(
                meta,
                Keys.QUANTUM_STORAGE_INSTANCE,
                PersistentQuantumStorageType.TYPE);

        if (quantumCache == null || quantumCache.getItemStack() == null) {
            player.sendMessage(Theme.ERROR
                    + "Este cartão ainda não foi definido para um item ou é um Armazenamento Quântico corrompido.");
            return;
        }

        quantumCache.setAmount(amount);
        DataTypeMethods.setCustom(meta, Keys.QUANTUM_STORAGE_INSTANCE, PersistentQuantumStorageType.TYPE, quantumCache);
        quantumCache.updateMetaLore(meta);
        itemStack.setItemMeta(meta);
        player.sendMessage(Theme.SUCCESS + "Item atualizado");
    }

    public void setQuantum(Player player, int amount) {
        final org.bukkit.block.Block targetBlock = player.getTargetBlockExact(8, org.bukkit.FluidCollisionMode.NEVER);
        if (targetBlock == null || targetBlock.getType() == Material.AIR) {
            player.sendMessage(Theme.ERROR + "Você deve olhar para um Armazenamento Quântico.");
            return;
        }

        if (!BlockStorage.hasBlockInfo(targetBlock.getLocation())) {
            player.sendMessage(Theme.ERROR + "Você deve olhar para um Armazenamento Quântico.");
            return;
        }

        final SlimefunItem slimefunItem = BlockStorage.check(targetBlock.getLocation());

        if (slimefunItem == null || !(slimefunItem instanceof NetworkQuantumStorage)) {
            player.sendMessage(Theme.ERROR + "Bloco inválido. Deve ser um Armazenamento Quântico da Networks.");
            return;
        }

        if (amount < 0) {
            player.sendMessage(Theme.ERROR + "A quantidade não pode ser negativa.");
            return;
        }

        final org.bukkit.Location targetLocation = targetBlock.getLocation();
        final BlockMenu blockMenu = BlockStorage.getInventory(targetLocation);

        if (blockMenu == null) {
            player.sendMessage(Theme.ERROR + "Menu do bloco não encontrado.");
            return;
        }

        // Update cache map in NetworkQuantumStorage
        QuantumCache cache = NetworkQuantumStorage.getCaches().get(targetLocation);
        if (cache == null) {
            player.sendMessage(Theme.ERROR + "Cache não encontrado para este bloco.");
            return;
        }

        cache.setAmount(amount);

        // Update visual display and block storage
        NetworkQuantumStorage.updateDisplayItem(blockMenu, cache);
        NetworkQuantumStorage.syncBlock(targetLocation, cache);
        NetworkQuantumStorage.getCaches().put(targetLocation, cache);

        player.sendMessage(Theme.SUCCESS + "Quantidade definida para: " + amount);
    }
}
