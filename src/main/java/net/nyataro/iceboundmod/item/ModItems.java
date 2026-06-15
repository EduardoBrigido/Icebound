package net.nyataro.iceboundmod.item;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegisterEvent;
import net.minecraftforge.registries.RegistryObject;
import net.nyataro.iceboundmod.IceBound;
import net.nyataro.iceboundmod.block.ModBlocks;
import net.nyataro.iceboundmod.entity.ModEntities;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, IceBound.MOD_ID);

    public static final RegistryObject<Item> CRYSTALLUM = ITEMS.register("crystallum",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> RAW_CRYSTALLUM = ITEMS.register("raw_crystallum",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> STRAWBERRY = ITEMS.register("strawberry",
            () -> new Item(new Item.Properties().food(ModFoods.STRAWBERRY)));
    public static final RegistryObject<Item> ICEBERRY = ITEMS.register("iceberry",
            () -> new Item(new Item.Properties().food(ModFoods.ICEBERRY)));

    public static final RegistryObject<Item> ICE_CREAM = ITEMS.register("ice_cream",
            () -> new Item(new Item.Properties().food(ModFoods.ICE_CREAM)));

    public static final RegistryObject<Item> CRYSTALLUM_SWORD = ITEMS.register("crystallum_sword",
            () -> new SwordItem(ModToolTiers.CRYSTALLUM, 1  , 5, new Item.Properties()));

    public static final RegistryObject<Item> CRYSTALLUM_HOE = ITEMS.register("crystallum_hoe",
            () -> new HoeItem(ModToolTiers.CRYSTALLUM, 1, 2, new Item.Properties()));
    public static final RegistryObject<Item> STRAWBERRY_SEEDS = ITEMS.register("strawberry_seeds",
            () -> new ItemNameBlockItem(ModBlocks.STRAWBERRY_CROP.get(), new Item.Properties()));

    public static final RegistryObject<Item> ICE_ARROW = ITEMS.register("ice_arrow",
            () -> new ArrowItem(new Item.Properties()) {
                @Override
                public AbstractArrow createArrow(Level level, ItemStack stack, LivingEntity shooter) {
                    Arrow arrow = new Arrow(level, shooter);
                    arrow.addTag("is_ice_arrow"); //  uma Tag para identificar a flecha
                    return arrow;
                }
                @Override
                public boolean isInfinite(ItemStack stack, ItemStack bow, net.minecraft.world.entity.player.Player player) {
                    return false; // Define se a flecha é infinita cm encantamento Afinidade
                }
            });

    public static final RegistryObject<Item> PENGUIN_SPAWN_EGG = ITEMS.register("penguin_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntities.PENGUIN, 0xc6e7ff, 0x2986cc, new Item.Properties()));

    public static final RegistryObject<Item> RACCOON_FIRE_EGG = ITEMS.register("fire_raccoon_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntities.FIRE_RACCOON, 0xf9cb9c, 0xf6b26b, new Item.Properties()));

    public static final RegistryObject<Item> HOT_CHOCO= ITEMS.register("hot_choco",
            () -> new ColdFoodItem(new Item.Properties().food(ModFoods.STRAWBERRY).stacksTo(16), 1.0));

    public static final RegistryObject<Item> WOOL_HELMET = ITEMS.register("wool_helmet",
            () -> new ColdArmorItem(ModArmorMaterials.WOOL, ArmorItem.Type.HELMET,
                    new Item.Properties(), 0.15f));

    public static final RegistryObject<Item> WOOL_CHESTPLATE = ITEMS.register("wool_chestplate",
            () -> new ColdArmorItem(ModArmorMaterials.WOOL, ArmorItem.Type.CHESTPLATE,
                    new Item.Properties(), 0.30f));

    public static final RegistryObject<Item> WOOL_LEGGINGS = ITEMS.register("wool_leggings",
            () -> new ColdArmorItem(ModArmorMaterials.WOOL, ArmorItem.Type.LEGGINGS,
                    new Item.Properties(), 0.20f));

    public static final RegistryObject<Item> WOOL_BOOTS = ITEMS.register("wool_boots",
            () -> new ColdArmorItem(ModArmorMaterials.WOOL, ArmorItem.Type.BOOTS,
                    new Item.Properties(), 0.10f));

    public static  void register (IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
