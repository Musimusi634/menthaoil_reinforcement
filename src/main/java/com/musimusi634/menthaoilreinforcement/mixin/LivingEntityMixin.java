package com.musimusi634.menthaoilreinforcement.mixin;

import com.musimusi634.menthaoilreinforcement.IMintDamageHolder;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = LivingEntity.class, priority = Integer.MAX_VALUE)
public class LivingEntityMixin {
    @Inject(method = "getHealth", at = @At("RETURN"), cancellable = true)
    private void getHealthInject(CallbackInfoReturnable<Float> cir) {
        LivingEntity entity = (LivingEntity) (Object) this;
        int mintdamage = ((IMintDamageHolder) entity).getMintDamage();
        float health = cir.getReturnValue();
        float replacedHealth = (float) (entity.getMaxHealth() * (1 - (mintdamage * 0.1)));
        if ((mintdamage > 0) && (health > replacedHealth)) cir.setReturnValue(replacedHealth);
    }
    @Inject(method = "isAlive", at = @At("RETURN"), cancellable = true)
    private void isAliveInject(CallbackInfoReturnable<Boolean> cir) {
        LivingEntity entity = (LivingEntity) (Object) this;
        int mintdamage = ((IMintDamageHolder) entity).getMintDamage();
        if (mintdamage > 20) cir.setReturnValue(false);
    }
    @Inject(method = "isDeadOrDying", at = @At("RETURN"), cancellable = true)
    private void isDeadOrDyingInject(CallbackInfoReturnable<Boolean> cir) {
        LivingEntity entity = (LivingEntity) (Object) this;
        int mintdamage = ((IMintDamageHolder) entity).getMintDamage();
        if (mintdamage > 20) cir.setReturnValue(true);
    }
}
