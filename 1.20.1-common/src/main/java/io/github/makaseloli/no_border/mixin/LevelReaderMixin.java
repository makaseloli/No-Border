package io.github.makaseloli.no_border.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelReader;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LevelReader.class)
public interface LevelReaderMixin {

    @Inject(
            method = "getMaxLocalRawBrightness(Lnet/minecraft/core/BlockPos;I)I",
            at = @At("HEAD"),
            cancellable = true
    )
    private void noBorder$readBrightnessOutsideVanillaBounds(
            BlockPos pos, int skyDarkening, CallbackInfoReturnable<Integer> cir
    ) {
        LevelReader level = (LevelReader) this;
        cir.setReturnValue(level.getRawBrightness(pos, skyDarkening));
    }
}
