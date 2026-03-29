package noob.ccxug.simpa.mixin.client;

import net.minecraft.client.resources.sounds.SoundInstance;
import net.minecraft.client.sounds.SoundEngine;
import net.minecraft.sounds.SoundSource;
import noob.ccxug.simpa.RedirectedSound;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(SoundEngine.class)
public class SoundEngineMixin {
    @Inject(method = "play", at = @At("HEAD"), cancellable = true)
    private void redirectSound(SoundInstance instance, CallbackInfoReturnable<SoundEngine.PlayResult> cir)
    {
        if (instance instanceof RedirectedSound)
            return;
        if (instance.getSource() == SoundSource.MUSIC)
            return;
        RedirectedSound redirected = new RedirectedSound(instance);
        cir.cancel();
        SoundEngine.PlayResult result = ((SoundEngineAccessor) this).invokePlay(redirected);
        cir.setReturnValue(result);
    }
}
