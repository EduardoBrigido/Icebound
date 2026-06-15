package net.nyataro.iceboundmod.item;

import net.minecraft.client.tutorial.Tutorial;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;
import net.minecraftforge.common.TierSortingRegistry;
import net.nyataro.iceboundmod.IceBound;
import net.nyataro.iceboundmod.util.ModTags;

import java.util.List;

public class ModToolTiers {
   public  static final Tier CRYSTALLUM = TierSortingRegistry.registerTier(
           new ForgeTier(3, 1500, 10f, 1f, 20,
                   ModTags.Blocks.NEEDS_CRYSTALLUM_TOOL,() -> Ingredient.of(ModItems.CRYSTALLUM.get())),
           new ResourceLocation(IceBound.MOD_ID, "crystallum"), List.of(Tiers.IRON), List.of());


}
