package io.github.makaseloli.no_border.mixin;

import net.minecraft.client.renderer.WorldBorderRenderer;
import net.minecraft.client.renderer.state.level.WorldBorderRenderState;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(WorldBorderRenderer.class)
public abstract class LevelRendererMixin {

    @Inject(
            method = "render(Lnet/minecraft/client/renderer/state/level/WorldBorderRenderState;Lnet/minecraft/world/phys/Vec3;DD)V",
            at = @At("HEAD"),
            cancellable = true
    )
    private void noBorder$skipWorldBorderRendering(WorldBorderRenderState state, Vec3 cameraPosition, double renderDistance, double warningBlocks, CallbackInfo ci) {
        ci.cancel();
    }
}
