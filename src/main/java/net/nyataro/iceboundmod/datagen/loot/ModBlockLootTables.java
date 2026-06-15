package net.nyataro.iceboundmod.datagen.loot;

import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegistryObject;
import net.nyataro.iceboundmod.block.ModBlocks;
import net.nyataro.iceboundmod.block.custom.StrawberryCropBlock;
import net.nyataro.iceboundmod.item.ModItems;

import java.util.Set;

public class ModBlockLootTables extends BlockLootSubProvider {
    public ModBlockLootTables() {

        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    protected void generate() {
        this.dropSelf(ModBlocks.CRYSTALLUM_BLOCK.get());
        this.dropSelf(ModBlocks.CRYSTALLUM_ORE.get());
        this.dropSelf(ModBlocks.SNOW_PALM_LEAVES.get());


        this.add(ModBlocks.CRYSTALLUM_ORE.get(),
              block -> createCopperLikeOreDrops(ModBlocks.CRYSTALLUM_ORE.get(),
                      ModItems.RAW_CRYSTALLUM.get()));
        this.add(ModBlocks.DEEPSLATE_CRYSTALLUM_ORE.get(),
                block -> createCopperLikeOreDrops(ModBlocks.DEEPSLATE_CRYSTALLUM_ORE.get()
                        ,ModItems.RAW_CRYSTALLUM.get()));
        this.add(ModBlocks.SNOW_PALM_LEAVES.get(), block ->
                createLeavesDrops(block, ModBlocks.PALMSNOW_SAPLING.get(), NORMAL_LEAVES_SAPLING_CHANCES));

        LootItemCondition.Builder lootitemcondition$builder = LootItemBlockStatePropertyCondition
                .hasBlockStateProperties(ModBlocks.STRAWBERRY_CROP.get())
                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(StrawberryCropBlock.AGE, 5));

        this.add(ModBlocks.STRAWBERRY_CROP.get(), createCropDrops(ModBlocks.STRAWBERRY_CROP.get(), ModItems.STRAWBERRY.get(),
                ModItems.STRAWBERRY_SEEDS.get(), lootitemcondition$builder));
        this.dropSelf(ModBlocks.PALMSNOW_SAPLING.get());
        this.dropSelf(ModBlocks.ICE_SAND.get());
        this.dropSelf(ModBlocks.SNOWPALM_LOG.get());


    }
    protected LootTable.Builder createCopperLikeOreDrops(Block pBlock, Item item) {
        return createSilkTouchDispatchTable
                (pBlock, this.applyExplosionDecay
                        (pBlock, LootItem.lootTableItem(item)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 3.0F)))
                                .apply(ApplyBonusCount.addOreBonusCount(Enchantments.BLOCK_FORTUNE))));
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }
}
