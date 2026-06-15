package net.nyataro.iceboundmod.registry;

import net.minecraft.world.effect.MobEffect;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.eventbus.api.IEventBus;

import net.nyataro.iceboundmod.IceBound;
import net.nyataro.iceboundmod.effect.FrozenEffect;

public class ModEffects {

    public static final DeferredRegister<MobEffect> EFFECTS =
            DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, IceBound.MOD_ID);

    public static final RegistryObject<MobEffect> FROZEN =
            EFFECTS.register("frozen", FrozenEffect::new);

    public static void register(IEventBus eventBus) {
        EFFECTS.register(eventBus);
    }
}