package net.nyataro.iceboundmod.event;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import net.nyataro.iceboundmod.IceBound;
import net.nyataro.iceboundmod.registry.ModEffects;

@Mod.EventBusSubscriber(modid = IceBound.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class FrozenAttackHandler {

    @SubscribeEvent
    public static void onEntityHit(LivingDamageEvent event) {

        if (!(event.getSource().getEntity() instanceof Player))
            return;

        LivingEntity target = event.getEntity();

        if (target.hasEffect(ModEffects.FROZEN.get())) {

            int amp = target.getEffect(ModEffects.FROZEN.get()).getAmplifier();
            int newAmp = Math.min(amp + 1, 3);

            target.addEffect(new MobEffectInstance(
                    ModEffects.FROZEN.get(),
                    100,
                    newAmp,
                    false,
                    true,
                    true
            ));

        } else {

            target.addEffect(new MobEffectInstance(
                    ModEffects.FROZEN.get(),
                    100,
                    0,
                    false,
                    true,
                    true
            ));
        }
    }
}