package io.github.makaseloli.no_border.mixin;

import net.minecraft.world.level.border.WorldBorder;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(WorldBorder.class)
public abstract class WorldBorderMixin {

    @Inject(
            method = {
                    "isWithinBounds(Lnet/minecraft/core/BlockPos;)Z",
                    "isWithinBounds(Lnet/minecraft/world/level/ChunkPos;)Z",
                    "isWithinBounds(DD)Z",
                    "isWithinBounds(DDD)Z",
                    "isWithinBounds(Lnet/minecraft/world/phys/AABB;)Z"
            },
            at = @At("HEAD"),
            cancellable = true
    )
    private void noBorder$alwaysWithinBounds(CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue(true);
    }

    @Inject(method = "getDistanceToBorder(DD)D", at = @At("HEAD"), cancellable = true)
    private void noBorder$maxDistanceToBorder(CallbackInfoReturnable<Double> cir) {
        cir.setReturnValue(Double.MAX_VALUE);
    }

    @Inject(
            method = "getDistanceToBorder(Lnet/minecraft/world/entity/Entity;)D",
            at = @At("HEAD"),
            cancellable = true
    )
    private void noBorder$maxDistanceToBorderEntity(CallbackInfoReturnable<Double> cir) {
        cir.setReturnValue(Double.MAX_VALUE);
    }

    @Inject(
            method = "getCollisionShape()Lnet/minecraft/world/phys/shapes/VoxelShape;",
            at = @At("HEAD"),
            cancellable = true
    )
    private void noBorder$emptyCollisionShape(CallbackInfoReturnable<VoxelShape> cir) {
        cir.setReturnValue(Shapes.empty());
    }
}
