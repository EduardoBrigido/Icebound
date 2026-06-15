package net.nyataro.iceboundmod.cold;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;


public class PlayerCold {

    private double cold;
    private final double MIN_COLD = 0;
    private final double MAX_COLD = 10;



    public double getCold() {
        return cold;
    }

    public void addCold(double add, Player player) {
        this.cold = Math.min(cold + add, MAX_COLD);
        checkColdEffects(player);

    }

    public void subCold(double sub, Player player) {
        this.cold = Math.max(cold - sub, MIN_COLD);
        checkColdEffects(player);

    }

    public void copyFrom(PlayerCold source) {
        this.cold = source.cold;

    }

    public void saveNBTData(CompoundTag nbt) {
        nbt.putDouble("cold", cold);

    }


    public void loadNBTData(CompoundTag nbt) {
        cold = nbt.getDouble("cold");

    }

    // Checa os efeitos do player e ativa ou remove de acordo com o cold
    private void checkColdEffects(Player player) {
        applyOrRemoveEffect(player, MobEffects.WEAKNESS, 5, 0);
        applyOrRemoveEffect(player, MobEffects.MOVEMENT_SLOWDOWN, 8, 1);
        applyOrRemoveEffect(player, MobEffects.MOVEMENT_SLOWDOWN, 10, 2);

        if (cold >= 10) {
            player.hurt(player.damageSources().freeze(), 4f);
            player.getFoodData().eat(-4, 0.1f);
        }
    }

    private void applyOrRemoveEffect(Player player, MobEffect effect, double threshold, int amplifier) {
        if (cold >= threshold) {
            // Aplica efeito
            if (!player.hasEffect(effect) || player.getEffect(effect).getAmplifier() < amplifier) {
                player.addEffect(new MobEffectInstance(effect, Integer.MAX_VALUE, amplifier, false, false, true));
            }
        } else {
            // Remove efeito
            if (player.hasEffect(effect) && player.getEffect(effect).getAmplifier() == amplifier) {
                player.removeEffect(effect);
            }
        }
    }
}
