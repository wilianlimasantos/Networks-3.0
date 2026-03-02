package io.github.sefiraat.networks.slimefun;

import io.github.sefiraat.networks.slimefun.network.NetworkQuantumStorage;
import io.github.sefiraat.networks.slimefun.tools.NetworkRemote;
import io.github.sefiraat.networks.utils.Theme;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.libraries.dough.collections.Pair;
import lombok.experimental.UtilityClass;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import javax.annotation.Nonnull;
import java.text.MessageFormat;

/**
 * Creating SlimefunItemstacks here due to some items being created in Enums so this will
 * act as a one-stop-shop for the stacks themselves.
 */
@UtilityClass
public class NetworksSlimefunItemStacks {

    // Materials
    public static final SlimefunItemStack SYNTHETIC_EMERALD_SHARD;
    public static final SlimefunItemStack OPTIC_GLASS;
    public static final SlimefunItemStack OPTIC_CABLE;
    public static final SlimefunItemStack OPTIC_STAR;
    public static final SlimefunItemStack RADIOACTIVE_OPTIC_STAR;
    public static final SlimefunItemStack SHRINKING_BASE;
    public static final SlimefunItemStack SIMPLE_NANOBOTS;
    public static final SlimefunItemStack ADVANCED_NANOBOTS;
    public static final SlimefunItemStack AI_CORE;
    public static final SlimefunItemStack EMPOWERED_AI_CORE;
    public static final SlimefunItemStack PRISTINE_AI_CORE;
    public static final SlimefunItemStack INTERDIMENSIONAL_PRESENCE;

    // Network Items
    public static final SlimefunItemStack NETWORK_CONTROLLER;
    public static final SlimefunItemStack NETWORK_BRIDGE;
    public static final SlimefunItemStack NETWORK_MONITOR;
    public static final SlimefunItemStack NETWORK_IMPORT;
    public static final SlimefunItemStack NETWORK_EXPORT;
    public static final SlimefunItemStack NETWORK_GRABBER;
    public static final SlimefunItemStack NETWORK_PUSHER;
    public static final SlimefunItemStack NETWORK_CONTROL_X;
    public static final SlimefunItemStack NETWORK_CONTROL_V;
    public static final SlimefunItemStack NETWORK_VACUUM;
    public static final SlimefunItemStack NETWORK_VANILLA_GRABBER;
    public static final SlimefunItemStack NETWORK_VANILLA_PUSHER;
    public static final SlimefunItemStack NETWORK_WIRELESS_TRANSMITTER;
    public static final SlimefunItemStack NETWORK_WIRELESS_RECEIVER;
    public static final SlimefunItemStack NETWORK_PURGER;
    public static final SlimefunItemStack NETWORK_GRID;
    public static final SlimefunItemStack NETWORK_CRAFTING_GRID;
    public static final SlimefunItemStack NETWORK_CELL;
    public static final SlimefunItemStack NETWORK_GREEDY_BLOCK;
    public static final SlimefunItemStack NETWORK_QUANTUM_WORKBENCH;
    public static final SlimefunItemStack NETWORK_QUANTUM_STORAGE_1;
    public static final SlimefunItemStack NETWORK_QUANTUM_STORAGE_2;
    public static final SlimefunItemStack NETWORK_QUANTUM_STORAGE_3;
    public static final SlimefunItemStack NETWORK_QUANTUM_STORAGE_4;
    public static final SlimefunItemStack NETWORK_QUANTUM_STORAGE_5;
    public static final SlimefunItemStack NETWORK_QUANTUM_STORAGE_6;
    public static final SlimefunItemStack NETWORK_QUANTUM_STORAGE_7;
    public static final SlimefunItemStack NETWORK_QUANTUM_STORAGE_8;
    public static final SlimefunItemStack NETWORK_CAPACITOR_1;
    public static final SlimefunItemStack NETWORK_CAPACITOR_2;
    public static final SlimefunItemStack NETWORK_CAPACITOR_3;
    public static final SlimefunItemStack NETWORK_CAPACITOR_4;
    public static final SlimefunItemStack NETWORK_POWER_OUTLET_1;
    public static final SlimefunItemStack NETWORK_POWER_OUTLET_2;
    public static final SlimefunItemStack NETWORK_POWER_DISPLAY;
    public static final SlimefunItemStack NETWORK_RECIPE_ENCODER;
    public static final SlimefunItemStack NETWORK_AUTO_CRAFTER;
    public static final SlimefunItemStack NETWORK_AUTO_CRAFTER_WITHHOLDING;

    // Tools
    public static final SlimefunItemStack CRAFTING_BLUEPRINT;
    public static final SlimefunItemStack NETWORK_PROBE;
    public static final SlimefunItemStack NETWORK_REMOTE;
    public static final SlimefunItemStack NETWORK_REMOTE_EMPOWERED;
    public static final SlimefunItemStack NETWORK_REMOTE_PRISTINE;
    public static final SlimefunItemStack NETWORK_REMOTE_ULTIMATE;
    public static final SlimefunItemStack NETWORK_CRAYON;
    public static final SlimefunItemStack NETWORK_CONFIGURATOR;
    public static final SlimefunItemStack NETWORK_WIRELESS_CONFIGURATOR;
    public static final SlimefunItemStack NETWORK_RAKE_1;
    public static final SlimefunItemStack NETWORK_RAKE_2;
    public static final SlimefunItemStack NETWORK_RAKE_3;
    public static final SlimefunItemStack NETWORK_DEBUG_STICK;

    static {

        SYNTHETIC_EMERALD_SHARD = Theme.themedSlimefunItemStack(
            "NTW_SYNTHETIC_EMERALD_SHARD",
            new ItemStack(Material.LIME_DYE),
            Theme.CRAFTING,
            "Fragmento de Esmeralda Sintética",
            "Um fragmento de esmeralda sintética que",
            "é a base para transferência de",
            "informações."
        );

        OPTIC_GLASS = Theme.themedSlimefunItemStack(
            "NTW_OPTIC_GLASS",
            new ItemStack(Material.GLASS),
            Theme.CRAFTING,
            "Vidro Óptico",
            "Um vidro simples capaz de",
            "transferir pequenos bits de informação."
        );

        OPTIC_CABLE = Theme.themedSlimefunItemStack(
            "NTW_OPTIC_CABLE",
            new ItemStack(Material.STRING),
            Theme.CRAFTING,
            "Cabo Óptico",
            "Um fio simples capaz de",
            "transferir grandes quantidades de informação."
        );

        OPTIC_STAR = Theme.themedSlimefunItemStack(
            "NTW_OPTIC_STAR",
            new ItemStack(Material.NETHER_STAR),
            Theme.CRAFTING,
            "Estrela Óptica",
            "Uma estrutura cristalina em forma de estrela que",
            "pode transferir grandes quantidades de informação."
        );

        RADIOACTIVE_OPTIC_STAR = Theme.themedSlimefunItemStack(
            "NTW_RADIOACTIVE_OPTIC_STAR",
            getPreEnchantedItemStack(Material.NETHER_STAR, true, new Pair<>(Enchantment.POWER, 1)),
            Theme.CRAFTING,
            "Estrela Óptica Radioativa",
            "Uma estrutura cristalina em forma de estrela que",
            "pode armazenar quantidades insanas de informação."
        );

        SHRINKING_BASE = Theme.themedSlimefunItemStack(
            "NTW_SHRINKING_BASE",
            getPreEnchantedItemStack(Material.PISTON, true, new Pair<>(Enchantment.POWER, 1)),
            Theme.CRAFTING,
            "Base de Encolhimento",
            "Uma construção avançada capaz de",
            "deixar coisas grandes pequenas."
        );

        SIMPLE_NANOBOTS = Theme.themedSlimefunItemStack(
            "NTW_SIMPLE_NANOBOTS",
            new ItemStack(Material.MELON_SEEDS),
            Theme.CRAFTING,
            "Nanobots Simples",
            "Bots minúsculos que podem",
            "ajudar em tarefas precisas."
        );

        ADVANCED_NANOBOTS = Theme.themedSlimefunItemStack(
            "NTW_ADVANCED_NANOBOTS",
            getPreEnchantedItemStack(Material.MELON_SEEDS, true, new Pair<>(Enchantment.POWER, 1)),
            Theme.CRAFTING,
            "Nanobots Avançados",
            "Bots minúsculos que podem",
            "ajudar em tarefas precisas.",
            "Esta versão é mais esperta e rápida."
        );

        AI_CORE = Theme.themedSlimefunItemStack(
            "NTW_AI_CORE",
            new ItemStack(Material.BRAIN_CORAL_BLOCK),
            Theme.CRAFTING,
            "Núcleo de I.A.",
            "Uma inteligência artificial em ascensão",
            "reside dentro desta casca fraca."
        );

        EMPOWERED_AI_CORE = Theme.themedSlimefunItemStack(
            "NTW_EMPOWERED_AI_CORE",
            new ItemStack(Material.TUBE_CORAL_BLOCK),
            Theme.CRAFTING,
            "Núcleo de I.A. Aprimorado",
            "Uma inteligência artificial próspera",
            "reside dentro desta casca."
        );

        PRISTINE_AI_CORE = Theme.themedSlimefunItemStack(
            "NTW_PRISTINE_AI_CORE",
            getPreEnchantedItemStack(Material.TUBE_CORAL_BLOCK, true, new Pair<>(Enchantment.POWER, 1)),
            Theme.CRAFTING,
            "Núcleo de I.A. Imaculado",
            "Uma inteligência artificial perfeita",
            "reside dentro desta casca definida."
        );

        INTERDIMENSIONAL_PRESENCE = Theme.themedSlimefunItemStack(
            "NTW_INTERDIMENSIONAL_PRESENCE",
            getPreEnchantedItemStack(Material.ARMOR_STAND, true, new Pair<>(Enchantment.POWER, 1)),
            Theme.CRAFTING,
            "Presença Interdimensional",
            "Uma inteligência artificial que",
            "se tornou poderosa demais para",
            "apenas uma dimensão."
        );

        NETWORK_CONTROLLER = Theme.themedSlimefunItemStack(
            "NTW_CONTROLLER",
            new ItemStack(Material.BLACK_STAINED_GLASS),
            Theme.MACHINE,
            "Controlador de Rede"
            , "O controlador de rede é o cérebro",
            "de toda a rede. Máx. 1 por rede."
        );

        NETWORK_BRIDGE = Theme.themedSlimefunItemStack(
            "NTW_BRIDGE",
            new ItemStack(Material.WHITE_STAINED_GLASS),
            Theme.MACHINE,
            "Ponte de Rede"
            , "A ponte permite conectar",
            "objetos da rede com baixo custo."
        );

        NETWORK_MONITOR = Theme.themedSlimefunItemStack(
            "NTW_MONITOR",
            new ItemStack(Material.GREEN_STAINED_GLASS),
            Theme.MACHINE,
            "Monitor de Rede",
            "O Monitor de Rede permite interação",
            "simples de importação/exporação com",
            "objetos adjacentes.",
            "",
            "Suporte Atual:",
            "Infinity Barrels",
            "Network Shells"
        );

        NETWORK_IMPORT = Theme.themedSlimefunItemStack(
            "NTW_IMPORT",
            new ItemStack(Material.RED_STAINED_GLASS),
            Theme.MACHINE,
            "Importador de Rede",
            "O Importador de Rede traz",
            "qualquer item dentro dele para a rede,",
            "até 9 pilhas por tick do SF.",
            "Aceita itens de carga."
        );

        NETWORK_EXPORT = Theme.themedSlimefunItemStack(
            "NTW_EXPORT",
            new ItemStack(Material.BLUE_STAINED_GLASS),
            Theme.MACHINE,
            "Exportador de Rede",
            "O Exportador de Rede pode ser",
            "configurado para exportar",
            "constantemente 1 pilha de qualquer",
            "item definido.",
            "Aceita retirada de itens da carga."
        );

        NETWORK_GRABBER = Theme.themedSlimefunItemStack(
            "NTW_GRABBER",
            new ItemStack(Material.MAGENTA_STAINED_GLASS),
            Theme.MACHINE,
            "Coletor de Rede",
            "O Coletor de Rede tentará",
            "pegar o primeiro item que encontrar",
            "dentro da máquina selecionada."
        );

        NETWORK_PUSHER = Theme.themedSlimefunItemStack(
            "NTW_PUSHER",
            new ItemStack(Material.BROWN_STAINED_GLASS),
            Theme.MACHINE,
            "Empurrador de Rede",
            "O Empurrador de Rede tentará",
            "inserir um item correspondente",
            "na máquina escolhida."
        );

        NETWORK_CONTROL_X = Theme.themedSlimefunItemStack(
            "NTW_CONTROL_X",
            new ItemStack(Material.WHITE_GLAZED_TERRACOTTA),
            Theme.MACHINE,
            "Controle de Rede: X",
            "O Controle de Rede: X tentará",
            "‘cortar’ um bloco do mundo",
            "e colocá-lo na Rede.",
            "Funciona apenas em blocos Vanilla",
            "sem inventários.",
            "",
            MessageFormat.format("{0}Dreno da Rede: {1}{2}/corte", Theme.CLICK_INFO, Theme.PASSIVE, 100)
        );

        NETWORK_CONTROL_V = Theme.themedSlimefunItemStack(
            "NTW_CONTROL_V",
            new ItemStack(Material.PURPLE_GLAZED_TERRACOTTA),
            Theme.MACHINE,
            "Controle de Rede: V",
            "O Controle de Rede: V tentará",
            "‘colar’ um bloco da Rede",
            "no mundo.",
            "Funciona apenas com blocos Vanilla.",
            "",
            MessageFormat.format("{0}Dreno da Rede: {1}{2}/colar", Theme.CLICK_INFO, Theme.PASSIVE, 100)
        );

        NETWORK_VACUUM = Theme.themedSlimefunItemStack(
            "NTW_VACUUM",
            new ItemStack(Material.ORANGE_GLAZED_TERRACOTTA),
            Theme.MACHINE,
            "Vácuo de Rede",
            "O Vácuo de Rede irá sugar",
            "itens para si mesmo dentro de",
            "uma área 4 x 4 centrada nele.",
            "Os itens sugados tentarão ser",
            "empurrados para a Rede.",
            "",
            MessageFormat.format("{0}Dreno da Rede: {1}{2}/tick", Theme.CLICK_INFO, Theme.PASSIVE, 100)
        );

        NETWORK_VANILLA_GRABBER = Theme.themedSlimefunItemStack(
            "NTW_VANILLA_GRABBER",
            new ItemStack(Material.ORANGE_STAINED_GLASS),
            Theme.MACHINE,
            "Coletor Vanilla de Rede",
            "O Coletor Vanilla de Rede tentará",
            "pegar o primeiro item possível do",
            "inventário vanilla escolhido.",
            "Você precisa coletar itens deste",
            "nó usando um Coletor."
        );

        NETWORK_VANILLA_PUSHER = Theme.themedSlimefunItemStack(
            "NTW_VANILLA_PUSHER",
            new ItemStack(Material.LIME_STAINED_GLASS),
            Theme.MACHINE,
            "Empurrador Vanilla de Rede",
            "O Empurrador Vanilla de Rede tentará",
            "inserir qualquer item dentro dele",
            "no inventário vanilla escolhido.",
            "Você precisa empurrar itens para este",
            "nó a partir de um Empurrador."
        );

        NETWORK_WIRELESS_TRANSMITTER = Theme.themedSlimefunItemStack(
            "NTW_NETWORK_WIRELESS_TRANSMITTER",
            new ItemStack(Material.CYAN_STAINED_GLASS),
            Theme.MACHINE,
            "Transmissor Sem Fio de Rede",
            "O Transmissor Sem Fio de Rede irá",
            "tentar transmitir qualquer item dentro dele",
            "para um Receptor Sem Fio de Rede",
            "vinculado no mesmo mundo.",
            "Use o Configurador Sem Fio para",
            "configurar o Transmissor Sem Fio.",
            "Requer 500 de Energia da Rede por transferência."
        );

        NETWORK_WIRELESS_RECEIVER = Theme.themedSlimefunItemStack(
            "NTW_NETWORK_WIRELESS_RECEIVER",
            new ItemStack(Material.PURPLE_STAINED_GLASS),
            Theme.MACHINE,
            "Receptor Sem Fio de Rede",
            "O Receptor Sem Fio de Rede é",
            "capaz de receber itens de um transmissor",
            "sem fio vinculado no mesmo mundo.",
            "Ele tentará empurrar os itens recebidos",
            "para a Rede a cada tick."
        );

        NETWORK_PURGER = Theme.themedSlimefunItemStack(
            "NTW_TRASH",
            new ItemStack(Material.OBSERVER),
            Theme.MACHINE,
            "Purificador de Rede",
            "O Purificador de Rede puxará",
            "itens correspondentes da rede",
            "e os descartará instantaneamente.",
            "Use com muito cuidado!"
        );

        NETWORK_GRID = Theme.themedSlimefunItemStack(
            "NTW_GRID",
            new ItemStack(Material.NOTE_BLOCK),
            Theme.MACHINE,
            "Grade da Rede",
            "A Grade da Rede mostra todos",
            "os itens na rede e permite",
            "inserir ou retirar diretamente."
        );

        NETWORK_CRAFTING_GRID = Theme.themedSlimefunItemStack(
            "NTW_CRAFTING_GRID",
            new ItemStack(Material.REDSTONE_LAMP),
            Theme.MACHINE,
            "Grade de Fabricação da Rede",
            "A Grade de Fabricação da Rede funciona",
            "como uma grade normal mas exibe menos",
            "itens, permitindo fabricar usando itens",
            "diretamente da rede."
        );

        NETWORK_CELL = Theme.themedSlimefunItemStack(
            "NTW_CELL",
            new ItemStack(Material.HONEYCOMB_BLOCK),
            Theme.MACHINE,
            "Célula de Rede",
            "A Célula de Rede é um",
            "inventário grande (baú duplo) que pode",
            "ser acessado pela rede e no mundo."
        );

        NETWORK_GREEDY_BLOCK = Theme.themedSlimefunItemStack(
            "NTW_GREEDY_BLOCK",
            new ItemStack(Material.SHROOMLIGHT),
            Theme.MACHINE,
            "Bloco Guloso de Rede",
            "O Bloco Guloso de Rede pode",
            "ser configurado para um item que",
            "manterá avidamente uma única pilha.",
            "Se mais itens não couberem,",
            "eles não entrarão na rede."
        );

        NETWORK_QUANTUM_WORKBENCH = Theme.themedSlimefunItemStack(
            "NTW_QUANTUM_WORKBENCH",
            new ItemStack(Material.DRIED_KELP_BLOCK),
            Theme.MACHINE,
            "Bancada Quântica da Rede",
            "Permite fabricar Armazenamentos Quânticos."
        );


        NETWORK_QUANTUM_STORAGE_1 = Theme.themedSlimefunItemStack(
            "NTW_QUANTUM_STORAGE_1",
            new ItemStack(Material.WHITE_TERRACOTTA),
            Theme.MACHINE,
            "Armazenamento Quântico de Rede (4K)",
            "Armazena " + NetworkQuantumStorage.getSizes()[0] + " itens",
            "",
            "Armazena itens em grandes quantidades",
            "dentro de uma singularidade quântica."
        );

        NETWORK_QUANTUM_STORAGE_2 = Theme.themedSlimefunItemStack(
            "NTW_QUANTUM_STORAGE_2",
            new ItemStack(Material.LIGHT_GRAY_TERRACOTTA),
            Theme.MACHINE,
            "Armazenamento Quântico de Rede (32K)",
            "Armazena " + NetworkQuantumStorage.getSizes()[1] + " itens",
            "",
            "Armazena itens em grandes quantidades",
            "dentro de uma singularidade quântica."
        );

        NETWORK_QUANTUM_STORAGE_3 = Theme.themedSlimefunItemStack(
            "NTW_QUANTUM_STORAGE_3",
            new ItemStack(Material.GRAY_TERRACOTTA),
            Theme.MACHINE,
            "Armazenamento Quântico de Rede (262K)",
            "Armazena " + NetworkQuantumStorage.getSizes()[2] + " itens",
            "",
            "Armazena itens em grandes quantidades",
            "dentro de uma singularidade quântica."
        );

        NETWORK_QUANTUM_STORAGE_4 = Theme.themedSlimefunItemStack(
            "NTW_QUANTUM_STORAGE_4",
            new ItemStack(Material.BROWN_TERRACOTTA),
            Theme.MACHINE,
            "Armazenamento Quântico de Rede (2M)",
            "Armazena " + NetworkQuantumStorage.getSizes()[3] + " itens",
            "",
            "Armazena itens em grandes quantidades",
            "dentro de uma singularidade quântica."
        );

        NETWORK_QUANTUM_STORAGE_5 = Theme.themedSlimefunItemStack(
            "NTW_QUANTUM_STORAGE_5",
            new ItemStack(Material.BLACK_TERRACOTTA),
            Theme.MACHINE,
            "Armazenamento Quântico de Rede (16M)",
            "Armazena " + NetworkQuantumStorage.getSizes()[4] + " itens",
            "",
            "Armazena itens em grandes quantidades",
            "dentro de uma singularidade quântica."
        );

        NETWORK_QUANTUM_STORAGE_6 = Theme.themedSlimefunItemStack(
            "NTW_QUANTUM_STORAGE_6",
            new ItemStack(Material.PURPLE_TERRACOTTA),
            Theme.MACHINE,
            "Armazenamento Quântico de Rede (134M)",
            "Armazena " + NetworkQuantumStorage.getSizes()[5] + " itens",
            "",
            "Armazena itens em grandes quantidades",
            "dentro de uma singularidade quântica."
        );

        NETWORK_QUANTUM_STORAGE_7 = Theme.themedSlimefunItemStack(
            "NTW_QUANTUM_STORAGE_7",
            new ItemStack(Material.MAGENTA_TERRACOTTA),
            Theme.MACHINE,
            "Armazenamento Quântico de Rede (1B)",
            "Armazena " + NetworkQuantumStorage.getSizes()[6] + " itens",
            "",
            "Armazena itens em grandes quantidades",
            "dentro de uma singularidade quântica."
        );

        NETWORK_QUANTUM_STORAGE_8 = Theme.themedSlimefunItemStack(
            "NTW_QUANTUM_STORAGE_8",
            new ItemStack(Material.RED_TERRACOTTA),
            Theme.MACHINE,
            "Armazenamento Quântico de Rede (∞)",
            "Armazena ∞ itens... quase",
            "",
            "Armazena itens em grandes quantidades",
            "dentro de uma singularidade quântica."
        );

        NETWORK_CAPACITOR_1 = Theme.themedSlimefunItemStack(
            "NTW_CAPACITOR_1",
            new ItemStack(Material.BROWN_GLAZED_TERRACOTTA),
            Theme.MACHINE,
            "Capacitor de Rede (1)",
            "O Capacitor de Rede pode receber",
            "energia e armazená-la para uso",
            "dentro da rede.",
            "",
            MessageFormat.format("{0}Capacidade: {1}{2}", Theme.CLICK_INFO, Theme.PASSIVE, 1000)
        );

        NETWORK_CAPACITOR_2 = Theme.themedSlimefunItemStack(
            "NTW_CAPACITOR_2",
            new ItemStack(Material.GREEN_GLAZED_TERRACOTTA),
            Theme.MACHINE,
            "Capacitor de Rede (2)",
            "O Capacitor de Rede pode receber",
            "energia e armazená-la para uso",
            "dentro da rede.",
            "",
            MessageFormat.format("{0}Capacidade: {1}{2}", Theme.CLICK_INFO, Theme.PASSIVE, 10000)
        );

        NETWORK_CAPACITOR_3 = Theme.themedSlimefunItemStack(
            "NTW_CAPACITOR_3",
            new ItemStack(Material.BLACK_GLAZED_TERRACOTTA),
            Theme.MACHINE,
            "Capacitor de Rede (3)",
            "O Capacitor de Rede pode receber",
            "energia e armazená-la para uso",
            "dentro da rede.",
            "",
            MessageFormat.format("{0}Capacidade: {1}{2}", Theme.CLICK_INFO, Theme.PASSIVE, 100000)
        );

        NETWORK_CAPACITOR_4 = Theme.themedSlimefunItemStack(
                "NTW_CAPACITOR_4",
                new ItemStack(Material.GRAY_GLAZED_TERRACOTTA),
                Theme.MACHINE,
                "Capacitor de Rede (4)",
                "O Capacitor de Rede pode receber",
                "energia e armazená-la para uso",
                "dentro da rede.",
                "",
                MessageFormat.format("{0}Capacidade: {1}{2}", Theme.CLICK_INFO, Theme.PASSIVE, 1000000)
        );

        NETWORK_POWER_OUTLET_1 = Theme.themedSlimefunItemStack(
            "NTW_POWER_OUTLET_1",
            new ItemStack(Material.YELLOW_GLAZED_TERRACOTTA),
            Theme.MACHINE,
            "Tomada de Energia da Rede (1)",
            "A Tomada de Energia da Rede pode retirar",
            "energia da Rede para alimentar",
            "máquinas ou retornar a uma rede",
            "EnergyNet.",
            "",
            "Opera com taxa de perda de 20%.",
            "",
            MessageFormat.format("{0}Transferência Máxima: {1}{2}", Theme.CLICK_INFO, Theme.PASSIVE, 500)
        );

        NETWORK_POWER_OUTLET_2 = Theme.themedSlimefunItemStack(
            "NTW_POWER_OUTLET_2",
            new ItemStack(Material.RED_GLAZED_TERRACOTTA),
            Theme.MACHINE,
            "Tomada de Energia da Rede (2)",
            "A Tomada de Energia da Rede pode retirar",
            "energia da Rede para alimentar",
            "máquinas ou retornar a uma rede",
            "EnergyNet.",
            "",
            "Opera com taxa de perda de 20%.",
            "",
            MessageFormat.format("{0}Transferência Máxima: {1}{2}", Theme.CLICK_INFO, Theme.PASSIVE, 2000)
        );

        NETWORK_POWER_DISPLAY = Theme.themedSlimefunItemStack(
            "NTW_POWER_DISPLAY",
            new ItemStack(Material.TINTED_GLASS),
            Theme.MACHINE,
            "Exibição de Energia da Rede",
            "A Exibição de Energia da Rede irá",
            "mostrar a energia na rede.",
            "Simples, né?"
        );

        NETWORK_RECIPE_ENCODER = Theme.themedSlimefunItemStack(
            "NTW_RECIPE_ENCODER",
            new ItemStack(Material.TARGET),
            Theme.MACHINE,
            "Codificador de Receita da Rede",
            "Usado para formar um Plano de Fabricação",
            "a partir dos itens de entrada.",
            "",
            MessageFormat.format("{0}Dreno da Rede: {1}{2}/codificar", Theme.CLICK_INFO, Theme.PASSIVE, 20000)
        );

        NETWORK_AUTO_CRAFTER = Theme.themedSlimefunItemStack(
            "NTW_AUTO_CRAFTER",
            new ItemStack(Material.BLACK_GLAZED_TERRACOTTA),
            Theme.MACHINE,
            "Auto Fabricador de Rede",
            "O Auto Fabricador de Rede aceita",
            "um plano de fabricação. Quando o",
            "item do plano é solicitado e",
            "não há nenhum na rede,",
            "ele será fabricado se você tiver",
            "materiais.",
            "",
            MessageFormat.format("{0}Dreno da Rede: {1}{2}/fabricar", Theme.CLICK_INFO, Theme.PASSIVE, 64)
        );

        NETWORK_AUTO_CRAFTER_WITHHOLDING = Theme.themedSlimefunItemStack(
            "NTW_AUTO_CRAFTER_WITHHOLDING",
            new ItemStack(Material.WHITE_GLAZED_TERRACOTTA),
            Theme.MACHINE,
            "Auto Fabricador de Rede (Retenção)",
            "O Auto Fabricador de Rede aceita",
            "um plano de fabricação. Quando o",
            "item do plano é solicitado e",
            "não há nenhum na rede,",
            "ele será fabricado se você tiver",
            "materiais.",
            "",
            "Um Fabricador com Retenção manterá",
            "uma pilha na saída e parará",
            "de fabricar. A pilha pode ser vista",
            "na Rede e também permite",
            "carga.",
            "",
            MessageFormat.format("{0}Dreno da Rede: {1}{2}/fabricar", Theme.CLICK_INFO, Theme.PASSIVE, 128)
        );

        CRAFTING_BLUEPRINT = Theme.themedSlimefunItemStack(
            "NTW_CRAFTING_BLUEPRINT",
            new ItemStack(Material.BLUE_DYE),
            Theme.TOOL,
            "Plano de Fabricação",
            "Um plano em branco que pode",
            "ser usado para armazenar uma",
            "receita de fabricação."
        );

        NETWORK_PROBE = Theme.themedSlimefunItemStack(
            "NTW_PROBE",
            new ItemStack(Material.CLOCK),
            Theme.TOOL,
            "Sonda da Rede",
            "Quando usado em um controlador,",
            "mostrará os nós da rede."
        );

        NETWORK_REMOTE = Theme.themedSlimefunItemStack(
            "NTW_REMOTE",
            new ItemStack(Material.PAINTING),
            Theme.TOOL,
            "Controle Remoto da Rede",
            "Abre uma grade vinculada sem fio.",
            "A grade deve estar carregada no chunk.",
            "",
            MessageFormat.format("{0}Alcance: {1}{2}", Theme.CLICK_INFO, Theme.PASSIVE, NetworkRemote.getRanges()[0])
        );

        NETWORK_REMOTE_EMPOWERED = Theme.themedSlimefunItemStack(
            "NTW_REMOTE_EMPOWERED",
            new ItemStack(Material.ITEM_FRAME),
            Theme.TOOL,
            "Controle Remoto da Rede Aprimorado",
            "Abre uma grade vinculada sem fio.",
            "A grade deve estar carregada no chunk.",
            "",
            MessageFormat.format("{0}Alcance: {1}{2}", Theme.CLICK_INFO, Theme.PASSIVE, NetworkRemote.getRanges()[1])
        );

        NETWORK_REMOTE_PRISTINE = Theme.themedSlimefunItemStack(
            "NTW_REMOTE_PRISTINE",
            new ItemStack(Material.GLOW_ITEM_FRAME),
            Theme.TOOL,
            "Controle Remoto da Rede Imaculado",
            "Abre uma grade vinculada sem fio.",
            "A grade deve estar carregada no chunk.",
            "",
            MessageFormat.format("{0}Alcance: {1}{2}", Theme.CLICK_INFO, Theme.PASSIVE, "Ilimitado")
        );

        NETWORK_REMOTE_ULTIMATE = Theme.themedSlimefunItemStack(
            "NTW_REMOTE_ULTIMATE",
            getPreEnchantedItemStack(Material.GLOW_ITEM_FRAME, true, new Pair<>(Enchantment.POWER, 1)),
            Theme.TOOL,
            "Controle Remoto da Rede Supremo",
            "Abre uma grade vinculada sem fio.",
            "A grade deve estar carregada no chunk.",
            "",
            MessageFormat.format("{0}Alcance: {1}{2}", Theme.CLICK_INFO, Theme.PASSIVE, "Transdimensional")
        );

        NETWORK_CRAYON = Theme.themedSlimefunItemStack(
            "NTW_CRAYON",
            new ItemStack(Material.RED_CANDLE),
            Theme.TOOL,
            "Giz da Rede",
            "Quando usado em um controlador,",
            "ativa a exibição de partículas de",
            "blocos específicos ao funcionar."
        );

        NETWORK_CONFIGURATOR = Theme.themedSlimefunItemStack(
            "NTW_CONFIGURATOR",
            new ItemStack(Material.BLAZE_ROD),
            Theme.TOOL,
            "Configurador de Rede",
            "Usado para copiar e colar as",
            "configurações de interfaces",
            "direcionais.",
            "",
            MessageFormat.format("{0}Clique Direito: {1}{2}", Theme.CLICK_INFO, Theme.PASSIVE, "Aplicar Configuração"),
            MessageFormat.format("{0}Shift + Clique Direito: {1}{2}", Theme.CLICK_INFO, Theme.PASSIVE, "Armazenar Configuração")
        );

        NETWORK_WIRELESS_CONFIGURATOR = Theme.themedSlimefunItemStack(
            "NTW_WIRELESS_CONFIGURATOR",
            new ItemStack(Material.BLAZE_ROD),
            Theme.TOOL,
            "Configurador Sem Fio da Rede",
            "Usado para armazenar a localização",
            "de um Receptor e aplicar a um Transmissor",
            "",
            MessageFormat.format("{0}Clique Direito: {1}{2}", Theme.CLICK_INFO, Theme.PASSIVE, "Armazenar Local do Receptor"),
            MessageFormat.format("{0}Shift + Clique Direito: {1}{2}", Theme.CLICK_INFO, Theme.PASSIVE, "Definir Local no Transmissor")
        );

        NETWORK_RAKE_1 = Theme.themedSlimefunItemStack(
            "NTW_RAKE_1",
            new ItemStack(Material.TWISTING_VINES),
            Theme.TOOL,
            "Ancinho da Rede (1)",
            "Clique direito em um Objeto da Rede para",
            "quebrá-lo instantaneamente.",
            "",
            ChatColor.YELLOW + "250 Usos " + ChatColor.GRAY + "restantes"
        );

        NETWORK_RAKE_2 = Theme.themedSlimefunItemStack(
            "NTW_RAKE_2",
            new ItemStack(Material.WEEPING_VINES),
            Theme.TOOL,
            "Ancinho da Rede (2)",
            "Clique direito em um Objeto da Rede para",
            "quebrá-lo instantaneamente.",
            "",
            ChatColor.YELLOW + "1000 Usos " + ChatColor.GRAY + "restantes"
        );

        NETWORK_RAKE_3 = Theme.themedSlimefunItemStack(
            "NTW_RAKE_3",
            getPreEnchantedItemStack(Material.WEEPING_VINES, true, new Pair<>(Enchantment.LUCK_OF_THE_SEA, 1)),
            Theme.TOOL,
            "Ancinho da Rede (3)",
            "Clique direito em um Objeto da Rede para",
            "quebrá-lo instantaneamente.",
            "",
            ChatColor.YELLOW + "9999 Usos " + ChatColor.GRAY + "restantes"
        );

        NETWORK_DEBUG_STICK = Theme.themedSlimefunItemStack(
            "NTW_DEBUG_STICK",
            getPreEnchantedItemStack(Material.STICK, true, new Pair<>(Enchantment.LUCK_OF_THE_SEA, 1)),
            Theme.TOOL,
            "Bastão de Depuração da Rede",
            "Clique direito em um Objeto da Rede para",
            "ativar a depuração."
        );
    }

    @Nonnull
    @SafeVarargs
    public static ItemStack getPreEnchantedItemStack(Material material, boolean hide, @Nonnull Pair<Enchantment, Integer>... enchantments) {
        ItemStack itemStack = new ItemStack(material);
        ItemMeta itemMeta = itemStack.getItemMeta();
        for (Pair<Enchantment, Integer> pair : enchantments) {
            itemMeta.addEnchant(pair.getFirstValue(), pair.getSecondValue(), true);
        }
        if (hide) {
            itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        }
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }
}
