package net.nyataro.iceboundmod.item;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.nyataro.iceboundmod.IceBound;
import net.nyataro.iceboundmod.block.ModBlocks;

public class ModCreativeModTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
             DeferredRegister.create(Registries.CREATIVE_MODE_TAB, IceBound.MOD_ID);

    public static final RegistryObject<CreativeModeTab> ICEBOUND_TAB = CREATIVE_MODE_TABS.register("icebound_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack (ModItems.CRYSTALLUM.get()))
                    .title(Component.translatable("creativetab.icebound_tab"))
                    .displayItems((itemDisplayParameters, output) -> {
                      output.accept(ModItems.CRYSTALLUM.get());
                      output.accept(ModItems.RAW_CRYSTALLUM.get());
                      output.accept(ModItems.STRAWBERRY.get());
                        output.accept(ModItems.ICE_ARROW.get());

                        output.accept(ModItems.ICEBERRY.get());
                      output.accept(ModItems.ICE_CREAM.get());
                        output.accept(ModItems.CRYSTALLUM_SWORD.get());
                        output.accept(ModItems.CRYSTALLUM_HOE.get());
                        output.accept(ModItems.STRAWBERRY_SEEDS.get());
                        output.accept(ModItems.PENGUIN_SPAWN_EGG.get());
                        output.accept(ModItems.HOT_CHOCO.get());
                        output.accept(ModItems.WOOL_CHESTPLATE.get());
                        output.accept(ModItems.WOOL_HELMET.get());
                        output.accept(ModItems.WOOL_BOOTS.get());
                        output.accept(ModItems.WOOL_LEGGINGS.get());
                        output.accept(ModItems.RACCOON_FIRE_EGG.get());

                        output.accept(ModBlocks.PALMSNOW_SAPLING.get());
                        output.accept(ModBlocks.SNOWPALM_LOG.get());
                        output.accept(ModBlocks.ICE_SAND.get());
                        output.accept(ModBlocks.CRYSTALLUM_ORE.get());
                        output.accept(ModBlocks.CRYSTALLUM_BLOCK.get());
                        output.accept(ModBlocks.DEEPSLATE_CRYSTALLUM_ORE.get());

                    })
                    .build());

    public static void register (IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
