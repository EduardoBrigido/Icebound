package net.nyataro.iceboundmod.entity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.nyataro.iceboundmod.IceBound;
import net.nyataro.iceboundmod.entity.custom.Fire_RaccoonEntity;
import net.nyataro.iceboundmod.entity.custom.PenguinEntity;

public class ModEntities {
    public static void registerSpawnPlacements() {
        SpawnPlacements.register(PENGUIN.get(),
                SpawnPlacements.Type.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                PenguinEntity::canSpawn);
    }
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPE =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, IceBound.MOD_ID);

    public static final RegistryObject<EntityType<PenguinEntity>> PENGUIN =
            ENTITY_TYPE.register("penguin",
                    () -> EntityType.Builder.of(PenguinEntity::new, MobCategory.CREATURE)
                            .sized(0.7f, 1f)
                            .build("penguin"));

    public static final RegistryObject<EntityType<Fire_RaccoonEntity>> FIRE_RACCOON =
            ENTITY_TYPE.register("fire_raccoon",
                    () -> EntityType.Builder.of(Fire_RaccoonEntity::new, MobCategory.CREATURE)
                            .sized(0.7f, 1f)
                            .build("fire_raccoon"));



    public static void register(IEventBus eventBus) {
        ENTITY_TYPE.register(eventBus);
    }
}