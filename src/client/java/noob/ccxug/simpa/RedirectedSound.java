package noob.ccxug.simpa;

import net.minecraft.client.resources.sounds.AbstractSoundInstance;
import net.minecraft.client.resources.sounds.SoundInstance;
import net.minecraft.resources.Identifier;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.levelgen.PositionalRandomFactory;

import java.lang.reflect.Field;

public class RedirectedSound extends AbstractSoundInstance implements RedirectMarker {
    private static final Identifier IDENTIFIER = Identifier.fromNamespaceAndPath(Simpa.MOD_ID, "simpa");
    public RedirectedSound(SoundInstance original)
    {
        super(IDENTIFIER, original.getSource(), getRandomFromSoundInstance(original));
        float origVolume = getVolumeField(original);
        float origPitch = getPitchField(original);
        this.volume = origVolume;
        this.pitch = origPitch;
        this.x = original.getX();
        this.y = original.getY();
        this.z = original.getZ();
        this.relative = original.isRelative();
        this.looping = original.isLooping();
        this.delay = original.getDelay();
        this.attenuation = original.getAttenuation();
    }
    private static float getVolumeField(SoundInstance instance) {
        if (instance instanceof AbstractSoundInstance) {
            try {
                Field field = AbstractSoundInstance.class.getDeclaredField("volume");
                field.setAccessible(true);
                return field.getFloat(instance);
            } catch (Exception e) {
            }
        }
        return 1.0f;
    }

    private static float getPitchField(SoundInstance instance) {
        if (instance instanceof AbstractSoundInstance) {
            try {
                Field field = AbstractSoundInstance.class.getDeclaredField("pitch");
                field.setAccessible(true);
                return field.getFloat(instance);
            } catch (Exception e) {
            }
        }
        return 1.0f;
    }
    private static RandomSource getRandomFromSoundInstance(SoundInstance instance) {
        if (instance instanceof AbstractSoundInstance) {
            try {
                Field field = AbstractSoundInstance.class.getDeclaredField("random");
                field.setAccessible(true);
                return (RandomSource) field.get(instance);
            } catch (Exception e) {
            }
        }
        return RandomSource.create();
    }
    @Override
    public float getVolume()
    {
        return this.volume;
    }
    @Override
    public float getPitch()
    {
        return this.pitch;
    }
    @Override
    public boolean isRedirected()
    {
        return true;
    }
}