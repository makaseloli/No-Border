package io.github.makaseloli.no_border.mixin;

import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.server.commands.WorldBorderCommand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(WorldBorderCommand.class)
public abstract class WorldBorderCommandMixin {

    @Inject(method = "register(Lcom/mojang/brigadier/CommandDispatcher;)V", at = @At("HEAD"), cancellable = true)
    private static void noBorder$disableWorldBorderCommand(CommandDispatcher<CommandSourceStack> dispatcher, CallbackInfo ci) {
        ci.cancel();
    }
}
