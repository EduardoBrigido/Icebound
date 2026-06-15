package net.nyataro.iceboundmod.util;

import net.minecraft.client.gui.components.tabs.Tab;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.nyataro.iceboundmod.IceBound;

import static net.minecraft.tags.TagEntry.tag;

public class ModTags {
 public static class  Blocks {
  public static final  TagKey<Block> Metal_Detector_Valuables = tag("metal");
  public static final  TagKey<Block> NEEDS_CRYSTALLUM_TOOL= tag("needs_crystallum_tool");



  private static TagKey<Block> tag(String name) {
 return BlockTags.create(new ResourceLocation(IceBound.MOD_ID, name));

}
public static class Items {
 private static TagKey<Item> tag(String name) {
  return ItemTags.create(new ResourceLocation(IceBound.MOD_ID,name));
 }
}

 }

}
