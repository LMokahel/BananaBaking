package millllionwith4ls.bananabreadmod.core.entities;

import javax.annotation.Nullable;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.item.PrimedTnt;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.ExplosionDamageCalculator;
import net.minecraft.world.level.Level;


public class PrimedBananaNuke extends PrimedTnt {
    private static final EntityDataAccessor<Integer> DATA_FUSE_ID = SynchedEntityData.defineId(PrimedBananaNuke.class, EntityDataSerializers.INT);
    private static final int DEFAULT_FUSE_TIME = 80;
    @Nullable
    private LivingEntity owner;

    public PrimedBananaNuke(EntityType<PrimedBananaNuke> pEntityType, Level pLevel) {
        super(entities.PRIMED_BANANA_NUKE.get(), pLevel);
        this.blocksBuilding = true;
    }

    public PrimedBananaNuke(Level pLevel, double pX, double pY, double pZ, @Nullable LivingEntity pOwner) {
        this(entities.PRIMED_BANANA_NUKE.get(), pLevel);
        this.setPos(pX-0.4, pY, pZ);
        double d0 = pLevel.random.nextDouble() * (double)((float)Math.PI * 2F);
        this.setDeltaMovement(-Math.sin(d0) * 0.02D, (double)0.2F, -Math.cos(d0) * 0.02D);
        this.setFuse(80);
        this.xo = pX;
        this.yo = pY;
        this.zo = pZ;
        this.owner = pOwner;
    }

    protected void defineSynchedData() {
        this.entityData.define(DATA_FUSE_ID, 80);
    }


    protected void explode() {
        float f = 4.0F;
        assert this.getOwner() != null;
        this.getOwner().hurt(DamageSource.explosion(this.level.explode(this,DamageSource.explosion(this.getOwner()), new ExplosionDamageCalculator(),
                this.getX(), this.getY(0.0625D), this.getZ(),40F,true, Explosion.BlockInteraction.DESTROY)), 1000);
    }

    @Nullable
    public LivingEntity getOwner() {
        return this.owner;
    }

    public void setFuse(int pLife) {this.entityData.set(DATA_FUSE_ID, pLife);}

    public int getFuse() {return this.entityData.get(DATA_FUSE_ID);}
}