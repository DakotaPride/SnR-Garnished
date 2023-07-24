package com.railwayteam.railways.registry.fabric;

import com.railwayteam.railways.Railways;
import com.railwayteam.railways.compat.tracks.TrackCompatUtils;
import com.railwayteam.railways.registry.CRCreativeModeTabs.RegistrateDisplayItemsGenerator;
import com.railwayteam.railways.registry.CRCreativeModeTabs.TabInfo;
import com.railwayteam.railways.registry.CRCreativeModeTabs.Tabs;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.DyeColor;

import java.util.function.Supplier;

import static com.railwayteam.railways.registry.CRItems.ITEM_CONDUCTOR_CAP;

public class CRCreativeModeTabsImpl {

    private static final TabInfo MAIN_TAB = register("main",
        () -> FabricItemGroup.builder()
            .title(Component.translatable("itemGroup.railways"))
            .icon(() -> ITEM_CONDUCTOR_CAP.get(DyeColor.BLUE).asStack())
            .displayItems(new RegistrateDisplayItemsGenerator(Tabs.MAIN))
            .build());

    private static final TabInfo COMPAT_TAB = TrackCompatUtils.anyLoaded() ? register("compat",
        () -> FabricItemGroup.builder()
            .title(Component.translatable("itemGroup.railways_compat"))
            .icon(() -> ITEM_CONDUCTOR_CAP.get(DyeColor.PURPLE).asStack())
            .displayItems(new RegistrateDisplayItemsGenerator(Tabs.COMPAT))
            .build()) : MAIN_TAB;

    private static final TabInfo CAPS_TAB = register("caps",
        () -> FabricItemGroup.builder()
            .title(Component.translatable("itemGroup.railways_caps"))
            .icon(() -> ITEM_CONDUCTOR_CAP.get(DyeColor.GREEN).asStack())
            .displayItems(new RegistrateDisplayItemsGenerator(Tabs.CAPS))
            .build());

    public static CreativeModeTab getBaseTab() {
        return MAIN_TAB.tab();
    }

    public static CreativeModeTab getCompatTracksTab() {
        return COMPAT_TAB.tab();
    }

    public static CreativeModeTab getCapsTab() {
        return CAPS_TAB.tab();
    }

    public static ResourceKey<CreativeModeTab> getBaseTabKey() {
        return MAIN_TAB.key();
    }

    public static ResourceKey<CreativeModeTab> getCompatTracksTabKey() {
        return COMPAT_TAB.key();
    }

    public static ResourceKey<CreativeModeTab> getCapsTabKey() {
        return CAPS_TAB.key();
    }

    private static TabInfo register(String name, Supplier<CreativeModeTab> supplier) {
        ResourceLocation id = Railways.asResource(name);
        ResourceKey<CreativeModeTab> key = ResourceKey.create(Registries.CREATIVE_MODE_TAB, id);
        CreativeModeTab tab = supplier.get();
        Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, key, tab);
        return new TabInfo(key, tab);
    }
}
