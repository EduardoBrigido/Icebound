package net.nyataro.iceboundmod.cold;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.nyataro.iceboundmod.IceBound;
import net.nyataro.iceboundmod.registry.ModEffects;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
public class PlayerColdProvider implements ICapabilityProvider, INBTSerializable<CompoundTag> {
    public static final Capability<PlayerCold> PLAYER_COLD = CapabilityManager.get(new CapabilityToken<PlayerCold>(){});
    private PlayerCold cold = null;
    private final LazyOptional<PlayerCold> optional = LazyOptional.of(this::createPlayerCold);

    private PlayerCold createPlayerCold() {
        if (this.cold == null) {
            this.cold = new PlayerCold();
        }
        return this.cold;
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if (cap == PLAYER_COLD)  {
            return optional.cast();
        }
        return LazyOptional.empty();
    }


        @Override
    public CompoundTag serializeNBT() {
        CompoundTag nbt = new CompoundTag();
        createPlayerCold().saveNBTData(nbt);
        return nbt;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        createPlayerCold().loadNBTData(nbt);
    }

    @Mod.EventBusSubscriber(modid = IceBound.MOD_ID, value = Dist.CLIENT)
        public static class FrozenRenderHandler {

            @SubscribeEvent
            public static void onRenderLiving(RenderLivingEvent.Pre<?, ?> event) {

                LivingEntity entity = event.getEntity();

                if (entity.hasEffect(ModEffects.FROZEN.get())) {

                    int amp = entity.getEffect(ModEffects.FROZEN.get()).getAmplifier();

                    float intensity = 0.5F + (0.15F * amp);

                    RenderSystem.setShaderColor(0.5F, intensity, 1.0F, 1.0F);
                }
            }

            @SubscribeEvent
            public static void onRenderLivingPost(RenderLivingEvent.Post<?, ?> event) {

                RenderSystem.setShaderColor(1F, 1F, 1F, 1F);
            }
        }
}
