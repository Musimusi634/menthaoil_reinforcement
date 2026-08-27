package com.musimusi634.menthaoilreinforcement.mixin;


import com.musimusi634.menthaoilreinforcement.IMintDamageHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import net.dice7000.menthaoil.mixin.IMenthaOilVictim;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import javax.annotation.Nullable;

@Mixin(value = Entity.class, priority = Integer.MAX_VALUE)
public abstract class EntityMixin implements IMintDamageHolder {
    @Shadow
    @Nullable
    private Entity.RemovalReason removalReason;

    @Unique
    private int mintDamage = 0;

    @Unique
    private int menthaoil$count = 0;

    @Override
    public void setMintDamage(int value){
        mintDamage = value;
    }

    @Override
    public int getMintDamage(){
        return mintDamage;
    }

    @Inject(method = "tick", at = @At("HEAD"))
    private void tickInject(CallbackInfo ci){
        Entity entity = (Entity) (Object) this;
        if (((IMenthaOilVictim) entity).menthaoil$getAffected()) {
            if (menthaoil$count % 10 == 0) {
                ((IMintDamageHolder) entity).setMintDamage(((IMintDamageHolder) entity).getMintDamage() + 1);
            }
            if (!(entity.level().isClientSide()) && !(entity.getType() == EntityType.PLAYER) && mintDamage > 40) entity.remove(removalReason.KILLED);
        }
    }
    @Inject(method = "isRemoved", at = @At("RETURN"), cancellable = true)
    private void isRemovedInject(CallbackInfoReturnable<Boolean> cir){
        Entity entity = (Entity) (Object) this;
            if (!(entity.getType() == EntityType.PLAYER) && (mintDamage > 40)) cir.setReturnValue(true);
    }
    @Inject(method = "getRemovalReason", at = @At("RETURN"), cancellable = true)
    private void getRemovalReasonInject(CallbackInfoReturnable<Entity.RemovalReason> cir){
        Entity entity = (Entity) (Object) this;
        if (!(entity.getType() == EntityType.PLAYER) && (mintDamage > 40)) cir.setReturnValue(removalReason.KILLED);
    }
}
