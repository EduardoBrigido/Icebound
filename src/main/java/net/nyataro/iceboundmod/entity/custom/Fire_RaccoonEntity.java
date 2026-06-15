package net.nyataro.iceboundmod.entity.custom;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.nyataro.iceboundmod.item.ModItems;
import org.jetbrains.annotations.Nullable;

    public class Fire_RaccoonEntity extends TamableAnimal {

        public Fire_RaccoonEntity(EntityType<? extends TamableAnimal> pEntityType, Level pLevel) {
            super(pEntityType, pLevel);
        }

        public final AnimationState idleAnimationState = new AnimationState();
        private int idleAnimationTimeout = 0;


        @Override
        public void tick() {
            super.tick();

            if (this.level().isClientSide()) {
                setupAnimationStates();
            }
        }

        private void setupAnimationStates() {
            if (this.idleAnimationTimeout <= 0) {
                this.idleAnimationTimeout = this.random.nextInt(40) + 80;
                this.idleAnimationState.start(this.tickCount);
            } else {
                --this.idleAnimationTimeout;
            }
        }

            @Override
            protected void updateWalkAnimation( float pPartialTick) {
                float speed = (float) this.getDeltaMovement().horizontalDistance();

                if (this.onGround() && speed > 0.003F) {
                    this.walkAnimation.update(speed * 10F, 0.4F);
                } else {
                    this.walkAnimation.update(0F, 0.4F);
                }
            }


        private static final Ingredient foodRacoon = Ingredient.of(
                ModItems.ICEBERRY.get(),
                ModItems.STRAWBERRY.get()
        );
        @Override
        protected void registerGoals() {
            this.goalSelector.addGoal(0, new FloatGoal(this)); // evita que o mob afunde
            this.goalSelector.addGoal(0, new SitWhenOrderedToGoal(this));
            this.goalSelector.addGoal(1, new RandomStrollGoal(this, 1.0D)); // anda de forma aleatória
            this.goalSelector.addGoal(2, new FollowOwnerGoal(this, 1.1D, 5F, 2F, false));
            this.goalSelector.addGoal(3, new TemptGoal(this, 1.2D, foodRacoon,false));
            this.goalSelector.addGoal(4, new LookAtPlayerGoal(this, ServerPlayer.class,6F));
            this.goalSelector.addGoal(5, new RandomLookAroundGoal(this)); // olha aleatoriamente


        }



        public InteractionResult mobInteract(Player player, InteractionHand hand) {
            InteractionResult result = super.mobInteract(player, hand);
            if (result.consumesAction()) return result;

            ItemStack itemStack = player.getItemInHand(hand);
            if (foodRacoon.test(itemStack)) {
                if (!this.level().isClientSide()) {
                    itemStack.shrink(1);

                    if (!this.isTame()) {

                        // Chance de domesticar
                        if (this.random.nextInt(4) == 0) { // 25%
                            this.tame(player);
                            this.level().broadcastEntityEvent(this, (byte) 7); // coração
                        } else {
                            this.level().broadcastEntityEvent(this, (byte) 6); // fumaça
                        }
                    } else {
                        if (this.getHealth() < this.getMaxHealth()) {
                            this.heal(4f);
                            this.level().broadcastEntityEvent(this, (byte) 7);
                        }
                    }

                    return InteractionResult.SUCCESS;
                }
                return InteractionResult.CONSUME;
            }
            if (itemStack.isEmpty() && this.isTame() && this.isOwnedBy(player)) {
                if (!this.level().isClientSide()) {
                    this.setOrderedToSit(!this.isInSittingPose());
                }
                return InteractionResult.sidedSuccess(this.level().isClientSide());
            }

            return super.mobInteract(player, hand);
        }



        public static AttributeSupplier.Builder createAttributes() {
            return Mob.createMobAttributes()
                    .add(Attributes.MAX_HEALTH, 70)
                    .add(Attributes.MOVEMENT_SPEED, 0.30D)
                    .add(Attributes.FOLLOW_RANGE, 16.0D)
                    .add(Attributes.JUMP_STRENGTH, 5D);

        }


        @Override
        public @Nullable AgeableMob getBreedOffspring(ServerLevel serverLevel, AgeableMob ageableMob) {
            return null;
        }
    }


