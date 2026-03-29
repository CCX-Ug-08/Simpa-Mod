package noob.ccxug.simpa.mixin.client;

import net.minecraft.client.resources.sounds.SoundInstance;
import net.minecraft.client.sounds.SoundEngine;
import net.minecraft.sounds.SoundSource;
import noob.ccxug.simpa.RedirectMarker;
import noob.ccxug.simpa.RedirectedSound;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(SoundEngine.class)
public class SoundEngineMixin {
    @Inject(method = "play", at = @At("HEAD"), cancellable = true)
    public void redirectSound(SoundInstance instance, CallbackInfoReturnable<SoundEngine.PlayResult> cir)
    {
        if (instance instanceof RedirectMarker)
            return;
        if (instance.getSource() == SoundSource.MUSIC)
            return;
        cir.cancel();
        RedirectedSound redirectedSound = new RedirectedSound(instance);
        SoundEngine.PlayResult result = ((SoundEngineAccessor) this).invokePlay(redirectedSound);
        cir.setReturnValue(result);
    }
}
