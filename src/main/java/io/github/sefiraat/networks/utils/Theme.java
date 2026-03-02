package io.github.sefiraat.networks.utils;

import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import lombok.Getter;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.ArrayList;
import java.util.List;

@Getter
public enum Theme {
    WARNING(ChatColor.YELLOW, "Atenção"),
    ERROR(ChatColor.RED, "Erro"),
    NOTICE(ChatColor.WHITE, "Aviso"),
    PASSIVE(ChatColor.GRAY, ""),
    SUCCESS(ChatColor.GREEN, "Sucesso"),
    MAIN(ChatColor.of("#21588f"), "Principal"),
    CLICK_INFO(ChatColor.of("#e4ed32"), "Clique aqui"),
    RESEARCH(ChatColor.of("#a60e03"), "Pesquisa"),
    CRAFTING(ChatColor.of("#dbcea9"), "Material de Fabricação"),
    MACHINE(ChatColor.of("#3295a8"), "Máquina"),
    TOOL(ChatColor.of("#6b32a8"), "Ferramenta"),
    MECHANISM(ChatColor.of("#3295a8"), "Mecanismo"),
    FUEL(ChatColor.of("#112211"), "Combustível Fóssil"),
    MATERIAL_CLASS(ChatColor.of("#a4c2ba"), "Classe de Material"),
    RECIPE_TYPE(ChatColor.of("#ffe89c"), "Tipo de Receita"),
    GUIDE(ChatColor.of("#444444"), "Guia");

    @Getter
    protected static final Theme[] cachedValues = values();
    private final ChatColor color;
    private final String loreLine;

    @ParametersAreNonnullByDefault
    Theme(ChatColor color, String loreLine) {
        this.color = color;
        this.loreLine = loreLine;

    }

    @Nonnull
    public Particle.DustOptions getDustOptions(float size) {
        return new Particle.DustOptions(
                Color.fromRGB(
                        color.getColor().getRed(),
                        color.getColor().getGreen(),
                        color.getColor().getBlue()),
                size);
    }

    @Override
    public String toString() {
        return this.color.toString();
    }

    /**
     * Gets a SlimefunItemStack with a pre-populated lore and name with themed
     * colors.
     *
     * @param id        The ID for the new {@link SlimefunItemStack}
     * @param itemStack The vanilla {@link ItemStack} used to base the
     *                  {@link SlimefunItemStack} on
     * @param themeType The {@link Theme} {@link ChatColor} to apply to the
     *                  {@link SlimefunItemStack} name
     * @param name      The name to apply to the {@link SlimefunItemStack}
     * @param lore      The lore lines for the {@link SlimefunItemStack}. Lore is
     *                  book-ended with empty strings.
     * @return Returns the new {@link SlimefunItemStack}
     */
    @Nonnull
    @ParametersAreNonnullByDefault
    public static SlimefunItemStack themedSlimefunItemStack(String id, ItemStack itemStack, Theme themeType,
            String name, String... lore) {
        ChatColor passiveColor = Theme.PASSIVE.getColor();
        List<String> finalLore = new ArrayList<>();
        finalLore.add("");
        for (String s : lore) {
            finalLore.add(passiveColor + s);
        }
        finalLore.add("");
        finalLore.add(applyThemeToString(Theme.CLICK_INFO, themeType.getLoreLine()));
        return new SlimefunItemStack(
                id,
                itemStack,
                Theme.applyThemeToString(themeType, name),
                finalLore.toArray(new String[finalLore.size() - 1]));
    }

    /**
     * Applies the theme color to a given string
     *
     * @param themeType The {@link Theme} to apply the color from
     * @param string    The string to apply the color to
     * @return Returns the string provides preceded by the color
     */
    @Nonnull
    @ParametersAreNonnullByDefault
    public static String applyThemeToString(Theme themeType, String string) {
        return themeType.getColor() + string;
    }

    /**
     * Gets an ItemStack with a pre-populated lore and name with themed colors.
     *
     * @param material  The {@link Material} used to base the {@link ItemStack} on
     * @param themeType The {@link Theme} {@link ChatColor} to apply to the
     *                  {@link ItemStack} name
     * @param name      The name to apply to the {@link ItemStack}
     * @param lore      The lore lines for the {@link ItemStack}. Lore is book-ended
     *                  with empty strings.
     * @return Returns the new {@link ItemStack}
     */
    @Nonnull
    @ParametersAreNonnullByDefault
    public static ItemStack themedItemStack(Material material, Theme themeType, String name, String... lore) {
        ChatColor passiveColor = Theme.PASSIVE.getColor();
        List<String> finalLore = new ArrayList<>();
        finalLore.add("");
        for (String s : lore) {
            finalLore.add(passiveColor + s);
        }
        finalLore.add("");
        finalLore.add(applyThemeToString(Theme.CLICK_INFO, themeType.getLoreLine()));
        return new CustomItemStack(
                material,
                Theme.applyThemeToString(themeType, name),
                finalLore.toArray(new String[finalLore.size() - 1]));
    }

    public static String mM(String string) {
        return ChatColor.translateAlternateColorCodes('&', string);
    }

}
