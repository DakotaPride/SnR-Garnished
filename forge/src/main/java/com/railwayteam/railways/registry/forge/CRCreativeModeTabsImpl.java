package com.railwayteam.railways.registry.forge;

import com.railwayteam.railways.compat.tracks.TrackCompatUtils;
import com.railwayteam.railways.registry.CRCreativeModeTabs.RegistrateDisplayItemsGenerator;
import com.simibubi.create.Create;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.DyeColor;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import static com.railwayteam.railways.registry.CRItems.ITEM_CONDUCTOR_CAP;

@EventBusSubscriber(bus = Bus.MOD)
public class CRCreativeModeTabsImpl {

    private static final DeferredRegister<CreativeModeTab> TAB_REGISTER =
        DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Create.ID);

    public static final RegistryObject<CreativeModeTab> MAIN_TAB = TAB_REGISTER.register("main",
        () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup.railways"))
            .withTabsBefore(CreativeModeTabs.SPAWN_EGGS)
            .icon(() -> ITEM_CONDUCTOR_CAP.get(DyeColor.BLUE).asStack())
            .displayItems(new RegistrateDisplayItemsGenerator(true))
            .build());

    public static final RegistryObject<CreativeModeTab> COMPAT_TAB = TrackCompatUtils.anyLoaded() ? TAB_REGISTER.register("compat",
        () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup.railways_compat"))
            .withTabsBefore(MAIN_TAB.getKey())
            .icon(() -> ITEM_CONDUCTOR_CAP.get(DyeColor.PURPLE).asStack())
            .displayItems(new RegistrateDisplayItemsGenerator(false))
            .build()) : MAIN_TAB;

    public static void register(IEventBus modEventBus) {
        TAB_REGISTER.register(modEventBus);
    }

    public static CreativeModeTab getBaseTab() {
        return MAIN_TAB.get();
    }

    public static CreativeModeTab getCompatTracksTab() {
        return COMPAT_TAB.get();
    }

    public static ResourceKey<CreativeModeTab> getBaseTabKey() {
        return MAIN_TAB.getKey();
    }

    public static ResourceKey<CreativeModeTab> getCompatTracksTabKey() {
        return COMPAT_TAB.getKey();
    }
}
