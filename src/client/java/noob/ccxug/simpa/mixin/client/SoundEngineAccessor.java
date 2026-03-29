package noob.ccxug.simpa.mixin.client;

import net.minecraft.client.resources.sounds.SoundInstance;
import net.minecraft.client.sounds.SoundEngine;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(SoundEngine.class)
public interface SoundEngineAccessor {
    @Invoker("play")
    SoundEngine.PlayResult invokePlay(SoundInstance instance);
}
