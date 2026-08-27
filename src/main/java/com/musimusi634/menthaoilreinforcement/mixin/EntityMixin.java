package com.musimusi634.menthaoilreinforcement.mixin;


import com.musimusi634.menthaoilreinforcement.IMintDamageHolder;
import net.minecraft.world.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import net.dice7000.menthaoil.mixin.IMenthaOilVictim;

@Mixin(value = Entity.class, priority = Integer.MAX_VALUE)
public abstract class EntityMixin implements IMintDamageHolder {
    @Unique
    private int mintDamage = 0;

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
            if (entity.getMenthaoilCount() % 10 == 0) {
                ((IMintDamageHolder) entity).setMintDamage(((IMintDamageHolder) entity).getMintDamage() + 1);
            }
        }
    }
}
