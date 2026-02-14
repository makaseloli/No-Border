package io.github.makaseloli.no_border.mixin;

import net.minecraft.world.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Entity.class)
public abstract class EntityMixin {
    @Shadow
    public double xo;

    @Shadow
    public double yo;

    @Shadow
    public double zo;

    @Shadow
    public abstract void setPos(double x, double y, double z);

    @Inject(method = "absMoveTo(DDD)V", at = @At("HEAD"), cancellable = true)
    private void noBorder$disableAbsoluteCoordinateClamp(double x, double y, double z, CallbackInfo ci) {
        this.xo = x;
        this.yo = y;
        this.zo = z;
        this.setPos(x, y, z);
        ci.cancel();
    }
}
