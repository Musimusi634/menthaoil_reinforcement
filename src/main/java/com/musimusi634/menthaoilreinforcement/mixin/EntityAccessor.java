package com.musimusi634.menthaoilreinforcement.mixin;

import net.minecraft.world.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(value = Entity.class, priority = Integer.MAX_VALUE)
public interface EntityAccessor {
    @Accessor(value = "menthaoil$count")
    int getMenthaoilCount();
}
