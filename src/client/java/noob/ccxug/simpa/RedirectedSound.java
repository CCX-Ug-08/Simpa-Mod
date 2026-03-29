package noob.ccxug.simpa;

import net.minecraft.client.resources.sounds.AbstractSoundInstance;
import net.minecraft.client.resources.sounds.Sound;
import net.minecraft.client.resources.sounds.SoundInstance;
import net.minecraft.client.sounds.SoundManager;
import net.minecraft.client.sounds.WeighedSoundEvents;
import net.minecraft.resources.Identifier;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.levelgen.PositionalRandomFactory;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Field;

public class RedirectedSound extends AbstractSoundInstance implements RedirectMarker {
    private static final Identifier IDENTIFIER = Identifier.fromNamespaceAndPath(Simpa.MOD_ID, "simpa");
    public RedirectedSound(SoundInstance original)
    {
        super(IDENTIFIER, original.getSource(), new RandomSource() {
            @Override
            public RandomSource fork() {
                return null;
            }

            @Override
            public PositionalRandomFactory forkPositional() {
                return null;
            }

            @Override
            public void setSeed(long seed) {

            }

            @Override
            public int nextInt() {
                return 0;
            }

            @Override
            public int nextInt(int bound) {
                return 0;
            }

            @Override
            public long nextLong() {
                return 0;
            }

            @Override
            public boolean nextBoolean() {
                return false;
            }

            @Override
            public float nextFloat() {
                return 0;
            }

            @Override
            public double nextDouble() {
                return 0;
            }

            @Override
            public double nextGaussian() {
                return 0;
            }
        });
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