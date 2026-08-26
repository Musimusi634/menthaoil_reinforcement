package com.musimusi634.menthaoilreinforcement.mixin;

import com.musimusi634.menthaoilreinforcement.IMintDamageHolder;
import net.minecraft.nbt.CompoundTag;
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
        CompoundTag persistentData = entity.getPersistentData();
        int mintdamage = ((IMintDamageHolder) entity).getMintDamage();
        if (mintdamage != 0) {
            cir.setReturnValue((float) (cir.getReturnValue() - (entity.getMaxHealth() * (1 - mintdamage * 0.1))));
        }
    }
}
