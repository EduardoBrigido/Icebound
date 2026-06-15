package net.nyataro.iceboundmod.block;

import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.nyataro.iceboundmod.IceBound;
import net.nyataro.iceboundmod.block.custom.StrawberryCropBlock;
import net.nyataro.iceboundmod.item.ModItems;
import net.nyataro.iceboundmod.worldgen.tree.SnowPalmGrower;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, IceBound.MOD_ID);


    public  static final RegistryObject<Block> CRYSTALLUM_BLOCK= registerBlock("crystallum_block",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).sound(SoundType.NETHER_GOLD_ORE)));

    public  static final RegistryObject<Block> PALMSNOW_SAPLING= registerBlock("palm_snow_sapling",
            () -> new SaplingBlock(new SnowPalmGrower(), BlockBehaviour.Properties.copy(Blocks.SPRUCE_SAPLING)));
    public static final RegistryObject<Block> SNOWPALM_LOG = registerBlock("snowpalm_log",
            () -> new RotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LOG).strength(3f)));

    public static final RegistryObject<Block> ICE_SAND = registerBlock("ice_sand",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.DIRT).strength(3f)));

    public static final RegistryObject<Block> SNOW_PALM_LEAVES = registerBlock("snow_palm_leaves",
            () -> new LeavesBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LEAVES)));



    public static final RegistryObject<Block> CRYSTALLUM_ORE = registerBlock("crystallum_ore",
            () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.STONE)
                    .strength(1f).requiresCorrectToolForDrops(), UniformInt.of(3,6)));

    public static final RegistryObject<Block> DEEPSLATE_CRYSTALLUM_ORE = registerBlock("deepslate_crystallum_ore",
            () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE)
                    .strength(1f).requiresCorrectToolForDrops(), UniformInt.of(3,6)));

    public static final RegistryObject<Block> STRAWBERRY_CROP = BLOCKS.register("strawberry_crop",
            () -> new StrawberryCropBlock(BlockBehaviour.Properties.copy(Blocks.WHEAT).noOcclusion().noCollission()));



    private static <T extends  Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
     RegistryObject<T> toReturn = BLOCKS.register(name, block);
     registerBlockItem(name, toReturn);
     return toReturn;
   }

    private static <T extends Block>RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block) {
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(),new Item.Properties()));
    }

    public static void register (IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
