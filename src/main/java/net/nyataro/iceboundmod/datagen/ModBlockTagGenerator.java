package net.nyataro.iceboundmod.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.nyataro.iceboundmod.IceBound;
import net.nyataro.iceboundmod.block.ModBlocks;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagGenerator extends BlockTagsProvider {

    public ModBlockTagGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, IceBound.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        this.tag(BlockTags.NEEDS_IRON_TOOL)
                .add (ModBlocks.CRYSTALLUM_ORE.get(),
                        ModBlocks.CRYSTALLUM_BLOCK.get(),
                        ModBlocks.DEEPSLATE_CRYSTALLUM_ORE.get());
        this.tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(ModBlocks.CRYSTALLUM_ORE.get(),
                        ModBlocks.CRYSTALLUM_BLOCK.get(),
                        ModBlocks.DEEPSLATE_CRYSTALLUM_ORE.get());
        this.tag(BlockTags.DIRT)
                .add(ModBlocks.ICE_SAND.get());
        this.tag(BlockTags.LOGS)
                .add(ModBlocks.SNOWPALM_LOG.get());

        this.tag(BlockTags.LOGS_THAT_BURN)
                .add(ModBlocks.SNOWPALM_LOG.get());

        this.tag(BlockTags.LEAVES)
                .add(ModBlocks.SNOW_PALM_LEAVES.get());

    }
}
