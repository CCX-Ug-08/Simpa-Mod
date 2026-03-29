package noob.ccxug.simpa;

import net.minecraft.client.resources.sounds.Sound;
import net.minecraft.client.resources.sounds.SoundInstance;
import net.minecraft.client.sounds.SoundManager;
import net.minecraft.client.sounds.WeighedSoundEvents;
import net.minecraft.resources.Identifier;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import org.jspecify.annotations.Nullable;

public class RedirectedSound implements SoundInstance {
    private final SoundInstance original;
    private static final Identifier IDENTIFIER = Identifier.fromNamespaceAndPath(Simpa.MOD_ID, "simpa");
    private Sound customSound;
    private final RandomSource random = RandomSource.create();
    public RedirectedSound(SoundInstance original)
    {
        this.original = original;
    }
    @Override
    public Identifier getIdentifier()
    {
        return IDENTIFIER;
    }
    @Override
    public @Nullable WeighedSoundEvents resolve(SoundManager soundManager)
    {
        WeighedSoundEvents events = original.resolve(soundManager);
        if (events != null)
            this.customSound = events.getSound(random);
        return events;
    }
    @Override
    public @Nullable Sound getSound() {
        return customSound;
    }
    @Override
    public SoundSource getSource() {
        return original.getSource();
    }
    @Override
    public boolean isLooping() {
        return original.isLooping();
    }
    @Override
    public boolean isRelative() {
        return original.isRelative();
    }
    @Override
    public int getDelay() {
        return original.getDelay();
    }
    @Override
    public float getVolume() {
        return original.getVolume();
    }
    @Override
    public float getPitch() {
        return original.getPitch();
    }
    @Override
    public double getX() {
        return original.getX();
    }
    @Override
    public double getY() {
        return original.getY();
    }
    @Override
    public double getZ() {
        return original.getZ();
    }
    @Override
    public Attenuation getAttenuation() {
        return original.getAttenuation();
    }
    @Override
    public boolean canStartSilent() {
        return original.canStartSilent();
    }
    @Override
    public boolean canPlaySound() {
        return original.canPlaySound();
    }
}
