package com.musimusi634.menthaoilreinforcement.mixin;

import net.minecraft.world.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import net.dice7000.menthaoil.MORegistry;

@Mixin(value = Item.class, priority = Integer.MAX_VALUE)
public class ItemMixin {
    @Inject(method = "getMaxDamage",at = @At("RETURN"),cancellable = true)
    public void getMaxDamageInject(CallbackInfoReturnable<Integer> cir) {
        Item item = (Item) (Object) this;
        if (item instanceof MORegistry.MenthaOilItem || item instanceof MORegistry.MenthaSprayItem) cir.setReturnValue(Integer.MAX_VALUE);
    }
}
