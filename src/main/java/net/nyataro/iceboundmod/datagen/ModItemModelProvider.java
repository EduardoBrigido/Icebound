package net.nyataro.iceboundmod.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegistryObject;
import net.nyataro.iceboundmod.IceBound;
import net.nyataro.iceboundmod.block.ModBlocks;
import net.nyataro.iceboundmod.item.ModItems;

public class ModItemModelProvider  extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, IceBound.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
      simpleItem(ModItems.CRYSTALLUM);
        simpleItem(ModItems.ICEBERRY);
        simpleItem(ModItems.ICE_ARROW);

        simpleItem(ModItems.RAW_CRYSTALLUM);
        simpleItem(ModItems.STRAWBERRY);
        simpleItem(ModItems.STRAWBERRY_SEEDS);
        simpleItem(ModItems.ICE_CREAM);
        simpleItem(ModItems.CRYSTALLUM_HOE);
        simpleItem(ModItems.CRYSTALLUM_SWORD);
        armorItem(ModItems.WOOL_HELMET);
        armorItem(ModItems.WOOL_CHESTPLATE);
        armorItem(ModItems.WOOL_LEGGINGS);
        armorItem(ModItems.WOOL_BOOTS);
        simpleItem(ModItems.CRYSTALLUM_SWORD);
        simpleItem(ModItems.HOT_CHOCO);

        saplingItem(ModBlocks.PALMSNOW_SAPLING);
        withExistingParent(ModItems.RACCOON_FIRE_EGG.getId().getPath(), mcLoc("item/template_spawn_egg"));
        withExistingParent(ModBlocks.SNOWPALM_LOG.getId().getPath(),
                new ResourceLocation(IceBound.MOD_ID, "block/snowpalm_log"));
        withExistingParent(ModBlocks.ICE_SAND.getId().getPath(),
                new ResourceLocation(IceBound.MOD_ID, "block/ice_sand"));
        withExistingParent(ModItems.PENGUIN_SPAWN_EGG.getId().getPath(), mcLoc("item/template_spawn_egg"));

    }
    private void armorItem(RegistryObject<Item> itemRegistryObject) {
        this.withExistingParent(
                itemRegistryObject.getId().getPath(),
                mcLoc("item/generated")
        ).texture("layer0",
                new ResourceLocation(
                        IceBound.MOD_ID,
                        "item/" + itemRegistryObject.getId().getPath()
                )
        );
    }
    private ItemModelBuilder simpleItem(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(),
              new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(IceBound.MOD_ID, "item/" + item.getId().getPath()));
    }

    private ItemModelBuilder simpleBlockItem(RegistryObject<Block> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(IceBound.MOD_ID,"item/" + item.getId().getPath()));
    }
    private ItemModelBuilder saplingItem(RegistryObject<Block> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(IceBound.MOD_ID,"block/" + item.getId().getPath()));
    }

}
