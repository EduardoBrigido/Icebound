package net.nyataro.iceboundmod.entity.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.phys.Vec3;
import net.nyataro.iceboundmod.entity.ai.PenguinAttackGoal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PenguinEntity extends PathfinderMob {

    private static final EntityDataAccessor<Boolean> ATTACKING =
            SynchedEntityData.defineId(PenguinEntity.class, EntityDataSerializers.BOOLEAN);


    public final AnimationState idleAnimationState = new AnimationState();
    private int idleAnimationTimeout = 0;

    public final AnimationState attackAnimationState = new AnimationState();
    public int attackAnimationTimeout = 0;


    private static final Logger log = LoggerFactory.getLogger(PenguinEntity.class);

    public PenguinEntity(EntityType<? extends PathfinderMob> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    public void tick() {
        super.tick();

        if(this.level().isClientSide()) {
            setupAnimationStates();
        }
    }
    private void rotateBodyTowardsTarget() {
        if (this.getTarget() != null) {
            Vec3 targetPos = this.getTarget().position();
            Vec3 myPos = this.position();
            double dx = targetPos.x - myPos.x;
            double dz = targetPos.z - myPos.z;
            float angle = (float) (Math.atan2(dz, dx) * (180D / Math.PI)) - 90F;
            this.setYRot(angle); // gira o corpo da entidade

        }
    }






    private void setupAnimationStates() {
        if(this.idleAnimationTimeout <= 0) {
            this.idleAnimationTimeout = this.random.nextInt(40) + 80;
            this.idleAnimationState.start(this.tickCount);
        } else {
            --this.idleAnimationTimeout;
        }



        if(this.isAttacking() && attackAnimationTimeout <= 0) {
            attackAnimationTimeout = 50; // Length in ticks of your animation
            attackAnimationState.start(this.tickCount);
        } else {
            --this.attackAnimationTimeout;
        }

        if(!this.isAttacking()) {
            attackAnimationState.stop();
        }
    }


    @Override
    protected void updateWalkAnimation(float pPartialTick) {
        float f;
        if(this.getPose() == Pose.STANDING) {
            f = Math.min(pPartialTick * 6F, 1f);
        } else {
            f = 0f;
        }
        this.walkAnimation.update(f, 0.2f);
    }


    public void  setAttacking(Boolean attacking)  {
        this.entityData.set(ATTACKING, attacking);
    }

    public boolean isAttacking() {
        return this.entityData.get(ATTACKING);
    }


    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(ATTACKING, false);
    }





    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this)); // evita que o mob afunde
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this)); // ataca quem o atacou
        this.goalSelector.addGoal(2, new PenguinAttackGoal(this, 1, true)); // Goal personalizado
        this.goalSelector.addGoal(3, new LeapAtTargetGoal(this, 0.4F)); // Pula em direção ao alvo
        this.goalSelector.addGoal(4, new RandomStrollGoal(this, 1.0D)); // anda de forma aleatória
        this.goalSelector.addGoal(5, new RandomLookAroundGoal(this)); // olha aleatoriamente



    }

    public static boolean canSpawn(
            EntityType<PenguinEntity> type,
            LevelAccessor level,
            MobSpawnType spawnType,
            BlockPos pos,
            RandomSource random
    ) {
        boolean isSolid = level.getBlockState(pos.below()).isSolid();

        boolean cold = level.getBiome(pos).value().coldEnoughToSnow(pos);

        return isSolid && cold;
    }


    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 25D)
                .add(Attributes.ATTACK_DAMAGE, 5f)
                .add(Attributes.MOVEMENT_SPEED, 0.50D)
                .add(Attributes.ATTACK_KNOCKBACK, 0.5f)
                .add(Attributes.FOLLOW_RANGE, 16.0D)
                .add(Attributes.JUMP_STRENGTH, 5D);

    }









}
