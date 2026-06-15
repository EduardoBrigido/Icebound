package net.nyataro.iceboundmod.item;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import net.nyataro.iceboundmod.cold.PlayerColdProvider;

public class ColdFoodItem extends Item {

    private final double coldDecrease; // quanto de cold diminui ao comer

    public ColdFoodItem(Properties properties, double coldDecrease) {
        super(properties);
        this.coldDecrease = coldDecrease;
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity entity) {
        if (entity instanceof Player player) {
            player.getCapability(PlayerColdProvider.PLAYER_COLD).ifPresent(cold -> {
                cold.subCold(coldDecrease, player);
            });
        }
        return super.finishUsingItem(stack, level, entity);
    }

    @Override
    public UseAnim getUseAnimation(ItemStack stack) {
        return UseAnim.EAT;
    }
}