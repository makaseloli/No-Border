package io.github.makaseloli.no_border.mixin;

import net.minecraft.world.entity.Entity;
import net.minecraft.util.Mth;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Entity.class)
public abstract class EntityMixin {
    @Inject(method = "absSnapTo(DDD)V", at = @At("HEAD"), cancellable = true)
    private void noBorder$disableAbsoluteCoordinateClamp(double x, double y, double z, CallbackInfo ci) {
        Entity entity = (Entity) (Object) this;
        entity.xo = x;
        entity.yo = y;
        entity.zo = z;
        entity.setPos(x, y, z);
        ci.cancel();
    }

    @Redirect(
            method = "load",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/util/Mth;clamp(DDD)D"
            )
    )
    private double noBorder$disableSavedHorizontalPositionClamp(double value, double min, double max) {
        if (min < -2.999E7D && max > 2.999E7D) {
            return value;
        }

        return Mth.clamp(value, min, max);
    }
}
