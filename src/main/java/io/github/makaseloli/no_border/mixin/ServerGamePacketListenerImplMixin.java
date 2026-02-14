package io.github.makaseloli.no_border.mixin;

import net.minecraft.server.network.ServerGamePacketListenerImpl;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ServerGamePacketListenerImpl.class)
public abstract class ServerGamePacketListenerImplMixin {

    @Inject(method = "clampHorizontal(D)D", at = @At("HEAD"), cancellable = true)
    private static void noBorder$disableHorizontalHardClamp(double value, CallbackInfoReturnable<Double> cir) {
        cir.setReturnValue(value);
    }
}
