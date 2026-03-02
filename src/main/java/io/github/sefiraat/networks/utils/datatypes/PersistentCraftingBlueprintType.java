package io.github.sefiraat.networks.utils.datatypes;

import com.jeff_media.morepersistentdatatypes.DataType;
import io.github.sefiraat.networks.network.stackcaches.BlueprintInstance;
import io.github.sefiraat.networks.network.stackcaches.CardInstance;
import io.github.sefiraat.networks.utils.Keys;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataAdapterContext;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import javax.annotation.Nonnull;

/**
 * A {@link PersistentDataType} for {@link CardInstance}
 * Creatively thieved from {@see <a href=
 * "https://github.com/baked-libs/dough/blob/main/dough-data/src/main/java/io/github/bakedlibs/dough/data/persistent/PersistentUUIDDataType.java">PersistentUUIDDataType}
 *
 * @author Sfiguz7
 * @author Walshy
 */

public class PersistentCraftingBlueprintType implements PersistentDataType<PersistentDataContainer, BlueprintInstance> {

    public static final PersistentDataType<PersistentDataContainer, BlueprintInstance> TYPE = new PersistentCraftingBlueprintType();

    public static final NamespacedKey RECIPE = Keys.newKey("recipe");
    public static final NamespacedKey OUTPUT = Keys.newKey("output");

    @Override
    @Nonnull
    public Class<PersistentDataContainer> getPrimitiveType() {
        return PersistentDataContainer.class;
    }

    @Override
    @Nonnull
    public Class<BlueprintInstance> getComplexType() {
        return BlueprintInstance.class;
    }

    @Override
    @Nonnull
    public PersistentDataContainer toPrimitive(@Nonnull BlueprintInstance complex,
            @Nonnull PersistentDataAdapterContext context) {
        final PersistentDataContainer container = context.newPersistentDataContainer();

        // Convert SlimefunItemStack to vanilla ItemStack to avoid serialization issues
        ItemStack[] recipeItems = complex.getRecipeItems();
        ItemStack[] convertedRecipe = new ItemStack[recipeItems.length];
        for (int i = 0; i < recipeItems.length; i++) {
            convertedRecipe[i] = convertToVanillaItemStack(recipeItems[i]);
        }

        ItemStack output = convertToVanillaItemStack(complex.getItemStack());

        container.set(RECIPE, DataType.ITEM_STACK_ARRAY, convertedRecipe);
        container.set(OUTPUT, DataType.ITEM_STACK, output);
        return container;
    }

    /**
     * Converts a SlimefunItemStack to a vanilla ItemStack to ensure proper
     * serialization.
     * If the item is already a vanilla ItemStack, it is returned as-is.
     *
     * @param itemStack The ItemStack to convert
     * @return A vanilla ItemStack suitable for serialization
     */
    @Nonnull
    private static ItemStack convertToVanillaItemStack(@Nonnull ItemStack itemStack) {
        // Handle null and air items
        if (itemStack == null || itemStack.getType() == org.bukkit.Material.AIR) {
            return new ItemStack(org.bukkit.Material.AIR);
        }

        // Always create a new vanilla ItemStack with just the essential data
        // This forces any SlimefunItemStack or other subclasses to be converted
        ItemStack vanilla = new ItemStack(itemStack.getType(), itemStack.getAmount());

        // Copy item meta if it exists
        if (itemStack.hasItemMeta()) {
            try {
                vanilla.setItemMeta(itemStack.getItemMeta());
            } catch (Exception ignored) {
                // If meta copy fails, continue without it
            }
        }

        return vanilla;
    }

    @Override
    @Nonnull
    public BlueprintInstance fromPrimitive(@Nonnull PersistentDataContainer primitive,
            @Nonnull PersistentDataAdapterContext context) {
        final ItemStack[] recipe = primitive.get(RECIPE, DataType.ITEM_STACK_ARRAY);
        final ItemStack output = primitive.get(OUTPUT, DataType.ITEM_STACK);

        return new BlueprintInstance(recipe, output);
    }
}
