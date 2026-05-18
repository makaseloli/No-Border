package io.github.makaseloli.no_border.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.server.commands.TeleportCommand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(TeleportCommand.class)
public abstract class TeleportCommandMixin {

    @Redirect(
            method = "performTeleport(Lnet/minecraft/commands/CommandSourceStack;Lnet/minecraft/world/entity/Entity;Lnet/minecraft/server/level/ServerLevel;DDDLjava/util/Set;FFLnet/minecraft/server/commands/TeleportCommand$LookAt;)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/level/Level;isInSpawnableBounds(Lnet/minecraft/core/BlockPos;)Z"
            )
    )
    private static boolean noBorder$allowAnyTeleportTarget(BlockPos ignoredPos) {
        return true;
    }
}
