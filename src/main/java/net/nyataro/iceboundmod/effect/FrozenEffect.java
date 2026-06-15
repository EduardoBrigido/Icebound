package net.nyataro.iceboundmod.effect;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

public class FrozenEffect extends MobEffect {

    public FrozenEffect() {
        super(MobEffectCategory.HARMFUL, 0x9fdfff);
        this.addAttributeModifier(Attributes.MOVEMENT_SPEED, "7107DE5E-7CE8-4030-940E-514C1F160890", 0.0D, AttributeModifier.Operation.ADDITION);
    }

    @Override
    public void applyEffectTick(LivingEntity entity, int amplifier) {
        if (!entity.level().isClientSide) {
            double slowFactor = Math.max(0, 1.0 - (0.25 * (amplifier + 1)));
            entity.setDeltaMovement(entity.getDeltaMovement().multiply(slowFactor, 1.0, slowFactor));

            if (amplifier >= 3 && entity instanceof Mob mob) {
                mob.setNoAi(true);
            }
        }
        else {
            for (int i = 0; i < 1 + amplifier; i++) {
                entity.level().addParticle(ParticleTypes.SNOWFLAKE,
                        entity.getRandomX(0.5D), entity.getRandomY(), entity.getRandomZ(0.5D),
                        0, 0.01, 0);
            }
        }
    }

    @Override
    public void removeAttributeModifiers(LivingEntity entity, AttributeMap map, int amplifier) {
        if (entity instanceof Mob mob) {
            mob.setNoAi(false);
        }
        super.removeAttributeModifiers(entity, map, amplifier);
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return true;
    }
}