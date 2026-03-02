package io.github.sefiraat.networks.network.stackcaches;

import io.github.sefiraat.networks.utils.Theme;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class QuantumCache extends ItemStackCache {

    @Nullable
    private final ItemMeta storedItemMeta;
    private final int limit;
    private final AtomicInteger amount;
    private boolean voidExcess;

    public QuantumCache(@Nullable ItemStack storedItem, int amount, int limit, boolean voidExcess) {
        super(storedItem);
        this.storedItemMeta = storedItem == null ? null : storedItem.getItemMeta();
        this.amount = new AtomicInteger(amount);
        this.limit = limit;
        this.voidExcess = voidExcess;
    }

    @Nullable
    public ItemMeta getStoredItemMeta() {
        return this.storedItemMeta;
    }

    public int getAmount() {
        return amount.get();
    }

    public void setAmount(int amount) {
        this.amount.set(amount);
    }

    public int increaseAmount(int amountToAdd) {
        while (true) {
            int current = this.amount.get();
            long total = (long) current + (long) amountToAdd;
            if (total > this.limit) {
                if (this.amount.compareAndSet(current, this.limit)) {
                    if (!this.voidExcess) {
                        return (int) (total - this.limit);
                    }
                    return 0;
                }
            } else {
                if (this.amount.compareAndSet(current, current + amountToAdd)) {
                    return 0;
                }
            }
        }
    }

    public void reduceAmount(int amountToRemove) {
        int result = this.amount.addAndGet(-amountToRemove);
        // Protect against negative amounts
        if (result < 0) {
            this.amount.compareAndSet(result, 0);
        }
    }

    public int getLimit() {
        return limit;
    }

    public boolean isVoidExcess() {
        return voidExcess;
    }

    public void setVoidExcess(boolean voidExcess) {
        this.voidExcess = voidExcess;
    }

    @Nullable
    public ItemStack withdrawItem(int amount) {
        if (this.getItemStack() == null) {
            return null;
        }
        final ItemStack clone = this.getItemStack().clone();
        // Atomically determine how much we can withdraw
        int withdrawn;
        while (true) {
            int current = this.amount.get();
            withdrawn = Math.min(current, amount);
            if (withdrawn <= 0) {
                clone.setAmount(0);
                return clone;
            }
            if (this.amount.compareAndSet(current, current - withdrawn)) {
                break;
            }
        }
        clone.setAmount(withdrawn);
        return clone;
    }

    @Nullable
    public ItemStack withdrawItem() {
        if (this.getItemStack() == null) {
            return null;
        }
        return withdrawItem(this.getItemStack().getMaxStackSize());
    }

    public void addMetaLore(ItemMeta itemMeta) {
        final List<String> lore = itemMeta.hasLore() ? itemMeta.getLore() : new ArrayList<>();
        lore.add("");
        lore.add(Theme.CLICK_INFO + "Holding: " +
                     (this.getItemMeta() != null && this.getItemMeta().hasDisplayName() ? this.getItemMeta().getDisplayName() : this.getItemStack().getType().name())
        );
        lore.add(Theme.CLICK_INFO + "Amount: " + this.getAmount());
        itemMeta.setLore(lore);
    }

    public void updateMetaLore(ItemMeta itemMeta) {
        final List<String> lore = itemMeta.hasLore() ? itemMeta.getLore() : new ArrayList<>();
        lore.set(lore.size() - 2,Theme.CLICK_INFO + "Holding: " +
                     (this.getItemMeta() != null && this.getItemMeta().hasDisplayName() ? this.getItemMeta().getDisplayName() : this.getItemStack().getType().name())
        );
        lore.set(lore.size() - 1, Theme.CLICK_INFO + "Amount: " + this.getAmount());
        itemMeta.setLore(lore);
    }
}
