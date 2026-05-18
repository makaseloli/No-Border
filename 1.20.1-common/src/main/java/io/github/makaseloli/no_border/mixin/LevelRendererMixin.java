package io.github.makaseloli.no_border.mixin;

import net.minecraft.client.Camera;
import net.minecraft.client.renderer.LevelRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LevelRenderer.class)
public abstract class LevelRendererMixin {

    @Inject(
            method = "renderWorldBorder(Lnet/minecraft/client/Camera;)V",
            at = @At("HEAD"),
            cancellable = true
    )
    private void noBorder$skipWorldBorderRendering(Camera camera, CallbackInfo ci) {
        ci.cancel();
    }
}
