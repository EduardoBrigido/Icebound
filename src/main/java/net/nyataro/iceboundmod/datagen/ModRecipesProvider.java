package net.nyataro.iceboundmod.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import net.minecraftforge.fml.common.Mod;
import net.nyataro.iceboundmod.IceBound;
import net.nyataro.iceboundmod.block.ModBlocks;
import net.nyataro.iceboundmod.item.ModItems;

import java.util.List;
import java.util.function.Consumer;

public class ModRecipesProvider extends RecipeProvider implements IConditionBuilder {
    private static final List<ItemLike>CRYSTALLUM_SMELTABLES= List.of(ModItems.RAW_CRYSTALLUM.get(),
            ModBlocks.CRYSTALLUM_ORE.get(), ModBlocks.DEEPSLATE_CRYSTALLUM_ORE.get());

    public ModRecipesProvider(PackOutput pOutput) {

        super(pOutput);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> pWriter) {
        oreSmelting(pWriter, CRYSTALLUM_SMELTABLES, RecipeCategory.MISC, ModItems.CRYSTALLUM.get(), 0,100, "crystallum");
        oreBlasting(pWriter, CRYSTALLUM_SMELTABLES, RecipeCategory.MISC, ModItems.CRYSTALLUM.get(), 0,100, "crystallum");
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.HOT_CHOCO.get())
                .requires(Items.COCOA_BEANS)
                .requires(Items.GLASS_BOTTLE)
                .requires(Items.MILK_BUCKET)
                .unlockedBy(getHasName(Items.COCOA_BEANS), has(Items.COCOA_BEANS))
                .save(pWriter);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.ICE_CREAM.get())
                .requires(ModItems.STRAWBERRY.get())
                .requires(ModItems.ICEBERRY.get())
                .requires(Items.MILK_BUCKET)
                .unlockedBy(getHasName(ModItems.STRAWBERRY.get()), has(ModItems.STRAWBERRY.get()))
                .save(pWriter);




        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.WOOL_HELMET.get())
                .pattern("WWW")
                .pattern("WHW")
                .pattern("WWW")
                .define('W', Items.WHITE_WOOL)
                .define('H', Items.LEATHER_HELMET)
                .unlockedBy(getHasName(Items.WHITE_WOOL), has(Items.WHITE_WOOL))
                .save(pWriter);


        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItems.CRYSTALLUM_SWORD.get())
                .pattern(" C ")
                .pattern(" C ")
                .pattern(" S ")
                .define('C', ModItems.CRYSTALLUM.get())
                .define('S', Items.STICK)
                .unlockedBy(getHasName(ModItems.CRYSTALLUM.get()), has(ModItems.CRYSTALLUM.get()))
                .save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModItems.CRYSTALLUM_HOE.get())
                .pattern("CC ")
                .pattern(" S ")
                .pattern(" S ")
                .define('C', ModItems.CRYSTALLUM.get())
                .define('S', Items.STICK)
                .unlockedBy(getHasName(ModItems.CRYSTALLUM.get()), has(ModItems.CRYSTALLUM.get()))
                .save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.WOOL_CHESTPLATE.get())
                .pattern("WWW")
                .pattern("WCW")
                .pattern("WWW")
                .define('W', Items.WHITE_WOOL)
                .define('C', Items.LEATHER_CHESTPLATE)
                .unlockedBy(getHasName(Items.WHITE_WOOL), has(Items.WHITE_WOOL))
                .save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.WOOL_LEGGINGS.get())
                .pattern("WWW")
                .pattern("WLW")
                .pattern("WWW")
                .define('W', Items.WHITE_WOOL)
                .define('L', Items.LEATHER_LEGGINGS)
                .unlockedBy(getHasName(Items.WHITE_WOOL), has(Items.WHITE_WOOL))
                .save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.WOOL_BOOTS.get())
                .pattern("WWW")
                .pattern("WBW")
                .pattern("WWW")
                .define('W', Items.WHITE_WOOL)
                .define('B', Items.LEATHER_BOOTS)
                .unlockedBy(getHasName(Items.WHITE_WOOL), has(Items.WHITE_WOOL))
                .save(pWriter);



    }
    protected static void oreSmelting(Consumer<FinishedRecipe> pFinishedRecipeConsumer, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTIme, String pGroup) {
        oreCooking(pFinishedRecipeConsumer, RecipeSerializer.SMELTING_RECIPE, pIngredients, pCategory, pResult, pExperience, pCookingTIme, pGroup, "_from_smelting");
    }

    protected static void oreBlasting(Consumer<FinishedRecipe> pFinishedRecipeConsumer, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup) {
        oreCooking(pFinishedRecipeConsumer, RecipeSerializer.BLASTING_RECIPE, pIngredients, pCategory, pResult, pExperience, pCookingTime, pGroup, "_from_blasting");
    }

    protected static void oreCooking(Consumer<FinishedRecipe> pFinishedRecipeConsumer, RecipeSerializer<? extends AbstractCookingRecipe> pCookingSerializer, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup, String pRecipeName) {
        for(ItemLike itemlike : pIngredients) {
            SimpleCookingRecipeBuilder.generic(Ingredient.of(new ItemLike[]{itemlike}), pCategory, pResult,
                    pExperience, pCookingTime, pCookingSerializer)
                    .group(pGroup).unlockedBy(getHasName(itemlike), has(itemlike))
                    .save(pFinishedRecipeConsumer, IceBound.MOD_ID + ":" + getItemName(pResult) + pRecipeName + "_" + getItemName(itemlike));
        }

    }
}
