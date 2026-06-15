package net.nyataro.iceboundmod;

import com.mojang.logging.LogUtils;
import net.nyataro.iceboundmod.registry.ModEffects;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.nyataro.iceboundmod.block.ModBlocks;
import net.nyataro.iceboundmod.entity.ModEntities;
import net.nyataro.iceboundmod.item.ModCreativeModTabs;
import net.nyataro.iceboundmod.item.ModItems;
import net.nyataro.iceboundmod.networking.ModMessages;
import net.nyataro.iceboundmod.worldgen.ModTrunkPlacerTypes;
import net.nyataro.iceboundmod.worldgen.biome.ModTerrablender;
import net.nyataro.iceboundmod.worldgen.biome.surface.ModSurfaceRules;
import net.nyataro.iceboundmod.worldgen.tree.ModFoliagePlacers;
import org.slf4j.Logger;
import terrablender.api.SurfaceRuleManager;


// The value here should match an entry in the META-INF/mods.toml file
@Mod(IceBound.MOD_ID)
public class IceBound {
    public static final String MOD_ID = "iceboundmod";
    private static final Logger LOGGER = LogUtils.getLogger();

    public IceBound() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModCreativeModTabs.register(modEventBus);
        ModEffects.register(modEventBus);
        ModTrunkPlacerTypes.register(modEventBus);
        ModBlocks.register(modEventBus);
        ModItems.register(modEventBus);
        modEventBus.addListener(this::commonSetup);

        MinecraftForge.EVENT_BUS.register(this);
        modEventBus.addListener(this::addCreative);
        ModEntities.register(modEventBus);
        ModFoliagePlacers.register(modEventBus);

        ModTerrablender.registerBiomes();
    }
    private void commonSetup(final FMLCommonSetupEvent event) {
        ModMessages.register();
     event.enqueueWork(() -> {
         ModEntities.registerSpawnPlacements();

        SurfaceRuleManager.addSurfaceRules(SurfaceRuleManager.RuleCategory.OVERWORLD, MOD_ID, ModSurfaceRules.makeRules());
    });
}
    // Add the example block item to the building blocks tab
    private void addCreative(BuildCreativeModeTabContentsEvent event) {

        if(event.getTabKey() == CreativeModeTabs.INGREDIENTS) {
            event.accept(ModItems.CRYSTALLUM_HOE);
             event.accept(ModItems.CRYSTALLUM);
             event.accept(ModItems.RAW_CRYSTALLUM);
             event.accept(ModItems.STRAWBERRY);
             event.accept(ModItems.ICEBERRY);
            event.accept(ModBlocks.CRYSTALLUM_BLOCK);
            event.accept(ModBlocks.SNOWPALM_LOG);
            event.accept(ModBlocks.ICE_SAND);
            event.accept(ModBlocks.PALMSNOW_SAPLING);

            event.accept(ModBlocks.CRYSTALLUM_ORE);
            event.accept(ModItems.WOOL_BOOTS);
            event.accept(ModItems.WOOL_HELMET);
            event.accept(ModItems.WOOL_LEGGINGS);
            event.accept(ModItems.ICE_CREAM);
            event.accept(ModItems.WOOL_CHESTPLATE);
            event.accept(ModItems.HOT_CHOCO);


        }



    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {

    }
    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {

        }
    }
}

