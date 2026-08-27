package com.musimusi634.menthaoilreinforcement.mixin;

import net.dice7000.menthaoil.MORegistry;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.Consumer;

@Mixin(value =  ItemStack.class, priority =  Integer.MAX_VALUE)
public class ItemStackMixin {
    @Inject(method = "hurtAndBreak", at = @At("HEAD"), cancellable = true)
    public  void hurtAndBreakInject(int value, LivingEntity entity, Consumer<LivingEntity>  onBroken, CallbackInfo ci) {
        ItemStack itemstack = (ItemStack) (Object) this;
        Item item = itemstack.getItem();
        if ((item instanceof MORegistry.MenthaOilItem) || (item instanceof MORegistry.MenthaSprayItem)) ci.cancel();
    }
}
